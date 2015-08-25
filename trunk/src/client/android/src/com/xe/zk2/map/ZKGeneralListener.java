package com.xe.zk2.map;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.baidu.mapapi.MKEvent;
import com.baidu.mapapi.MKGeneralListener;

public class ZKGeneralListener implements MKGeneralListener{

	private Context mContext;

	public ZKGeneralListener(Context context){
		mContext = context;
	}
	
	@Override
	public void onGetNetworkState(int iError) {
		Log.d("MyGeneralListener", "onGetNetworkState error is " + iError);
		Toast.makeText(mContext, "获取网络状态错误", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void onGetPermissionState(int iError) {
		Log.d("MyGeneralListener", "onGetPermissionState error is "
				+ iError);
		if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
			Toast.makeText(mContext, "百度授权错误",
					Toast.LENGTH_SHORT).show();
		}
	}

}
