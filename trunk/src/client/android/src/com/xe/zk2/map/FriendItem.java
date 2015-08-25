package com.xe.zk2.map;

import java.io.Serializable;

import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.OverlayItem;

public class FriendItem extends OverlayItem implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -1501149797495788093L;
		
	public FriendItem(GeoPoint p, String title, String snippet) {
		super(p, title, snippet);
	}

	public String getDes(){
		return "什么乱七八糟的东西啊，我疯都疯了";
	}

	public void setHeader(int i){
		//mHeaderPng = i;
	}

}
