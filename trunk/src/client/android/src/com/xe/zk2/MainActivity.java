package com.xe.zk2;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MKOfflineMap;
import com.baidu.mapapi.MKSearch;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.xe.zk2.map.FriendItem;
import com.xe.zk2.map.FriendsOverlay;
import com.xe.zk2.map.MyselfOverlay;
import com.xe.zk2.map.OfflineMapListener;
import com.xe.zk2.map.UpdateFriendsHandler;
import com.xe.zk2.map.ZKControlListener;
import com.xe.zk2.map.ZKGeneralListener;
import com.xe.zk2.map.ZKLocationListener;
import com.xe.zk2.map.ZKMapView;
import com.xe.zk2.map.ZKSearchListener;
import com.xe.zk2.utils.UpdateFriends;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * 主Activity
 * @author xujh
 *
 */
public class MainActivity extends MapActivity {
	
	private BMapManager mBMapMan;
	private MapView mMapView;
	private ZKLocationListener mLocationListener;
	private MyselfOverlay mMyself;
	private FriendsOverlay mFriendsOverlay;
	private static final String BAIDUMAP_KEY = "355B9BE9F5975B2EED6EB4DEF23CB6E9F1AD5ABC";
	
	private Toast mCloseToast;	  //关闭提示
	private boolean mCloseStatus; //true 为待关闭状态， false未非关闭状态
	private UpdateFriendsHandler mHandler;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mInstance = this;
        ((ZKApp)getApplication()).addActivity(this);
        setContentView(R.layout.activity_main);
        
        initMap(savedInstanceState);
        initUpdateFriends();
        initButton();
        initExit();
    }

	private void initButton() {
		OnClickListener listener = new ZKControlListener(mBMapMan, mMapView);
		Button back2me = (Button) findViewById(R.id.back2me);
		Button refresh = (Button) findViewById(R.id.refresh);
		back2me.setOnClickListener(listener);
		refresh.setOnClickListener(listener);
	}

	private void initUpdateFriends() {
		mHandler = new UpdateFriendsHandler(mFriendsOverlay);
		Timer timer = new Timer(true);
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
			    ArrayList<FriendItem> ret = UpdateFriends.update();
			    Message msg = new Message();
			    Bundle args = new Bundle();
			    args.putSerializable(UpdateFriendsHandler.ARG_FRIENDS, ret);
			    msg.setData(args);
			    mHandler.sendMessage(msg);
			}
		};
		timer.schedule(task, 0, 60000);
	}

	private void initMap(Bundle savedInstanceState) {
		mBMapMan = new BMapManager(this);
		mBMapMan.init(BAIDUMAP_KEY, new ZKGeneralListener(this));
		super.initMapActivity(mBMapMan);
		
		mMapView = (ZKMapView) findViewById(R.id.mapView);
		//mMapView.setBuiltInZoomControls(true); //设置启用内置的缩放控件
		//设置在缩放动画过程中也显示overlay,默认为不绘制
		mMapView.setDrawOverlayWhenZooming(true);
		MapController mapControl = mMapView.getController();
		GeoPoint lastPoint = null;
		if(null != savedInstanceState){
			//TODO: 从最近一次的定位地址初始化;
			lastPoint = new GeoPoint((int) (30.67 * 1E6), (int) (104.06 * 1E6)); // 默认设置成都
		} else {
			Location cur = mBMapMan.getLocationManager().getLocationInfo();
			if (null != cur) {
				lastPoint = new GeoPoint((int) (cur.getLatitude() * 1E6),
						(int) (cur.getLongitude() * 1E6));
			} else {
				lastPoint = new GeoPoint((int) (30.67 * 1E6), (int) (104.06 * 1E6)); // 默认设置成都
			}
		}
		mapControl.setCenter(lastPoint);
		mapControl.setZoom(12);
		
		mLocationListener = new ZKLocationListener(mMapView);
		mBMapMan.getLocationManager().setNotifyInternal(10, 5);
		
		//将自己加入地图覆盖层
		mMyself = new MyselfOverlay(this, mMapView);
		mMapView.getOverlays().add(mMyself);
		
		//将好友加入地图覆盖层
		Drawable marker = getResources().getDrawable(R.drawable.friend_overlay);  //得到需要标在地图上的资源
		marker.setBounds(0, 0, marker.getIntrinsicWidth(), marker
				.getIntrinsicHeight());   //为maker定义位置和边界,以便被点击
		mFriendsOverlay = new FriendsOverlay(marker, this, mMapView);
		mMapView.getOverlays().add(mFriendsOverlay); //添加ItemizedOverlay实例到mMapView
		
		MKSearch mSearch = new MKSearch(); 
		mSearch.init(mBMapMan, new ZKSearchListener(mBMapMan , mMapView));
		
		//初始化离线地图
		MKOfflineMap offlineMap = new MKOfflineMap();
		offlineMap.init(mBMapMan, new OfflineMapListener());
		int num = offlineMap.scan();
		Log.i("my", ""+num);
	}

	private void initExit() {
		mCloseToast = Toast.makeText(this, "亲,再按一次就要退出了哦", Toast.LENGTH_LONG);
		mCloseStatus = false;
	}

	@Override
	protected void onDestroy() {
		if(null != mBMapMan){
			mBMapMan.getLocationManager().removeUpdates(mLocationListener);
			mMyself.disableCompass();
			mMyself.disableMyLocation();
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		if(null != mBMapMan){
			mBMapMan.getLocationManager().removeUpdates(mLocationListener);
			mMyself.disableCompass();
			mMyself.disableMyLocation();
			mBMapMan.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		if (null != mBMapMan) {
			mBMapMan.getLocationManager().requestLocationUpdates(mLocationListener);
			mMyself.enableMyLocation(); // 启用定位
			mMyself.enableCompass(); // 启用指南针
			mBMapMan.start();
		}
		super.onResume();
	}
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	//TODO: 保存最近一次的定位地址初始化;
    	Location location = mBMapMan.getLocationManager().getLocationInfo();
    	if(null != location){
    		outState.putDouble("LATITUDE", location.getLatitude());
    		outState.putDouble("LONGITUDE", location.getLongitude());
    	}
    	super.onSaveInstanceState(outState);
    }
    
	/**
	 * 是否显示线路
	 * @author xujh
	 */
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//实现退出功能
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(mCloseStatus){
				mCloseToast.cancel();
				finish();
				((ZKApp)getApplication()).exit();
				return true;
			} else {
				mCloseToast.show();
				mCloseStatus = true;
				Handler exitHandler = new Handler();
				exitHandler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						mCloseStatus = false;
					}
				}, 3000);
				return true;
			}
		}
		
		return super.onKeyDown(keyCode, event);
	}
}
