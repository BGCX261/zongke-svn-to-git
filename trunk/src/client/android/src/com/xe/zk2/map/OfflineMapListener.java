package com.xe.zk2.map;

import android.util.Log;

import com.baidu.mapapi.MKOfflineMap;
import com.baidu.mapapi.MKOfflineMapListener;

public class OfflineMapListener implements MKOfflineMapListener{
	
	@Override
	public void onGetOfflineMapState(int type, int state) {
		switch (type) {
		case MKOfflineMap.TYPE_DOWNLOAD_UPDATE: 
			{
			//MKOLUpdateElement update = mOffline.getUpdateInfo(state);
			// mText.setText(String.format("%s : %d%%", update.cityName,
			// update.ratio));
			}
			break;
		case MKOfflineMap.TYPE_NEW_OFFLINE:
			Log.d("OfflineDemo",
					String.format("add offlinemap num:%d", state));
			break;
		case MKOfflineMap.TYPE_VER_UPDATE:
			Log.d("OfflineDemo", String.format("new offlinemap ver"));
			break;
		}
	}
}
