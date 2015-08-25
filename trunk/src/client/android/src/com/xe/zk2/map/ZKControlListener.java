package com.xe.zk2.map;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MapView;
import com.xe.zk2.R;

import android.location.Location;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ZKControlListener implements OnClickListener {

	private BMapManager mMapMan;
	private MapView mMapView;
	
	public ZKControlListener(BMapManager man, MapView view){
		super();
		this.mMapMan = man;
		this.mMapView = view;
	}
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.back2me:
			Location lastLoca = mMapMan.getLocationManager().getLocationInfo();
			if(null != lastLoca){
				GeoPoint p = new GeoPoint((int) (lastLoca.getLatitude() * 1E6), (int) (lastLoca.getLongitude() * 1E6));
				mMapView.getController().animateTo(p);
			}else{
				Toast.makeText(mMapView.getContext(), "亲，还没找到您的位置，请稍候再试", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.refresh:
			//TODO
			break;
		default:
			break;
		}
	}

}
