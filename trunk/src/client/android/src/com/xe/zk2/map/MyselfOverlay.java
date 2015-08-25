package com.xe.zk2.map;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.Location;

import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.MyLocationOverlay;
import com.xe.zk2.R;

/**
 * 自己的覆盖物
 * 
 * @author xujh
 * 
 */
public class MyselfOverlay extends MyLocationOverlay {

	private Context mContext;
	private Bitmap mRawImage;
	private Matrix mMartix;
	private Paint mPaint;

	public MyselfOverlay(Context context, MapView mapView) {
		super(context, mapView);
		this.mContext = context;
		mMartix = new Matrix();
		InputStream inputStream = mContext.getResources().openRawResource(
				R.drawable.myself_overlay);
		mRawImage = BitmapFactory.decodeStream(inputStream);
	}

	@Override
	protected void drawMyLocation(Canvas canvas, MapView mapView,
			Location myLocation, GeoPoint myGeoPoint, long when) {
		try {
			// 获取方向
			float degree = getOrientation();
			mMartix.setRotate(degree);
			// 旋转图片角度，指明方向
			Bitmap mDrawBitMap = Bitmap.createBitmap(mRawImage, 0, 0,
					mRawImage.getWidth(), mRawImage.getHeight(), mMartix, true);

			Point p = mapView.getProjection().toPixels(myGeoPoint, null);
			// 以图片中心点对到定位点上
			canvas.drawBitmap(mDrawBitMap, p.x - mDrawBitMap.getHeight() / 2,
					p.y - mDrawBitMap.getWidth() / 2, mPaint);

		} catch (Exception e) {
			super.drawMyLocation(canvas, mapView, myLocation, myGeoPoint, when);
		}

	}
}
