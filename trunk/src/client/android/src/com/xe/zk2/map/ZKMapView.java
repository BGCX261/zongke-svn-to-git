package com.xe.zk2.map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MapView;

public class ZKMapView extends MapView {
	
	public ZKMapView(Context context) {
		super(context);
	}

	public ZKMapView(Context context, AttributeSet attrs){
		super(context, attrs);
	}
	
	public ZKMapView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent evt) {
		//获得屏幕点击的位置
		int x = (int)evt.getX();
		int y = (int)evt.getY();
		//将像素坐标转为地址坐标
		GeoPoint pt = getProjection().fromPixels(x,y);
		
		
		
		return super.onTouchEvent(evt);
	}
}
