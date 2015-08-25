package com.xe.zk2.map;

import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MapView;

import android.location.Location;
import android.util.Log;

public class ZKLocationListener implements LocationListener {

	public final static int NOTHTING = 0;
	public final static int FOLLOW_MYSELF = 1;
	
	private MapView mMapView;
	private int mTodo = NOTHTING;
	
	public ZKLocationListener(MapView mapView) {
		this.mMapView = mapView;
	}

	public void setTodo(int todo){
		mTodo = todo;
	}
	
	@Override
	public void onLocationChanged(Location cur) {
		Log.i("LOCATION",
				"toDo: "+ mTodo +"维度：" + cur.getLatitude() + " 经度：" + cur.getLongitude());
		GeoPoint p = new GeoPoint((int) (cur.getLatitude() * 1E6),
				(int) (cur.getLongitude() * 1E6));
		switch (mTodo) {
		case FOLLOW_MYSELF:
			mMapView.getController().animateTo(p);
			//mMapView.invalidate();
			break;
		default:
			break;
		}
		
	}

}
