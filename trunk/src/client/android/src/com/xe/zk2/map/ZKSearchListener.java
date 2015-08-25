package com.xe.zk2.map;

import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKBusLineResult;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKPoiResult;
import com.baidu.mapapi.MKSearchListener;
import com.baidu.mapapi.MKSuggestionResult;
import com.baidu.mapapi.MKTransitRouteResult;
import com.baidu.mapapi.MKWalkingRouteResult;
import com.baidu.mapapi.MapView;

public class ZKSearchListener implements MKSearchListener {

	private BMapManager mMapMan;
	private MapView mMapView;
	
	public ZKSearchListener(BMapManager man, MapView view) {
		super();
		this.mMapMan = man;
		this.mMapView = view;
	}

	@Override
	public void onGetAddrResult(MKAddrInfo info, int arg1) {
		// TODO Auto-generated method stub
		if(null != info && null != info.strAddr && !info.strAddr.equals("")){
			Toast.makeText(mMapView.getContext(), info.strAddr, Toast.LENGTH_SHORT);
		} else {
			Toast.makeText(mMapView.getContext(), "未找到相关地址", Toast.LENGTH_SHORT);	
		}
	}

	@Override
	public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetDrivingRouteResult(MKDrivingRouteResult result, int arg1) {
		// TODO Auto-generated method stub
		if (result == null) {
	        return;
	    }
//	    RouteOverlay routeOverlay = new RouteOverlay(, mMapView);
	    // 此处仅展示一个方案作为示例
//	    routeOverlay.setData(result.getPlan(0).getRoute(0));
//	    mMapView.getOverlays().add(routeOverlay);
	}

	@Override
	public void onGetPoiResult(MKPoiResult arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetTransitRouteResult(MKTransitRouteResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetWalkingRouteResult(MKWalkingRouteResult arg0, int arg1) {
		// TODO Auto-generated method stub

	}

}
