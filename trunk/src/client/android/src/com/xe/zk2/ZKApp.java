package com.xe.zk2;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;

public class ZKApp extends Application {

	private ArrayList<Activity> activityList = new ArrayList<Activity>();

	public ZKApp() {
		super();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	public void exit() {
		if (activityList != null) {
			Activity activity;

			for (int i = 0; i < activityList.size(); i++) {
				activity = activityList.get(i);

				if (activity != null) {
					if (!activity.isFinishing()) {
						activity.finish();
					}

					activity = null;
				}
			}
		}
	}
}
