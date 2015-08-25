package com.xe.zk2.map;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.graphics.Canvas;
//import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
//import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.ItemizedOverlay;
import com.baidu.mapapi.MapView;
import com.xe.zk2.R;

public class FriendsOverlay extends ItemizedOverlay<FriendItem> {

	private List<FriendItem> mFriendsList = new ArrayList<FriendItem>();
	private Context mContext;

	private static FriendsOverlay mInstance;
	private MapView mMapView;

	private Drawable mMarker;
	private View mLastPopView;

	public static FriendsOverlay getInstance() {
		return mInstance;
	}

	public FriendsOverlay(Drawable marker, Context context, MapView mapView) {
		super(boundCenterBottom(marker));

		mInstance = this;
		this.mMarker = marker;
		this.mContext = context;
		this.mMapView = mapView;
		
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		
		super.draw(canvas, mapView, shadow);
		boundCenterBottom(mMarker);
	}

	@Override
	protected FriendItem createItem(int arg0) {
		FriendItem item = mFriendsList.get(arg0);
		return item;
	}

	@Override
	public int size() {
		return mFriendsList.size();
	}

	@Override
	protected boolean onTap(int i) {
		FriendItem item = mFriendsList.get(i);
		GeoPoint point = item.getPoint();
		mLastPopView = createView(item);
		mMapView.addView(mLastPopView, new MapView.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, point,
				MapView.LayoutParams.BOTTOM_CENTER));
		mLastPopView.setVisibility(View.VISIBLE);
		return true;
	}

	@Override
	public boolean onTap(GeoPoint arg0, MapView arg1) {
		if (null != mLastPopView) {
			mMapView.removeView(mLastPopView);
			mLastPopView.setVisibility(View.GONE);
			mLastPopView = null;
		}
		return super.onTap(arg0, arg1);
	}

	public void update(ArrayList<FriendItem> friends) {
		mFriendsList = friends;
		populate();
	}

	private View createView(FriendItem item) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View popView = inflater.inflate(R.layout.friend_pop_view, null, false);
		TextView text = (TextView) popView.findViewById(R.id.friendsDes);
		text.setText(item.getDes());
		return popView;
	}
}
