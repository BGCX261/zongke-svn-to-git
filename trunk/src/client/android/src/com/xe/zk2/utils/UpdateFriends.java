package com.xe.zk2.utils;

import java.util.ArrayList;

import com.baidu.mapapi.GeoPoint;
import com.xe.zk2.map.FriendItem;

public class UpdateFriends {

	public static int i = 0;
	
	public static ArrayList<FriendItem> update(){
		ArrayList<FriendItem> ret = new ArrayList<FriendItem>();
		int tag = i%3;
		switch (tag){
			case 0:
			{
				GeoPoint p1 = new GeoPoint((int) (30.67 * 1E6)+i, (int) (104.07 * 1E6));
				GeoPoint p2 = new GeoPoint((int) (30.68 * 1E6)+i, (int) (104.08 * 1E6));
				GeoPoint p3 = new GeoPoint((int) (30.69 * 1E6)+i, (int) (104.09 * 1E6));
		
				ret.add(new FriendItem(p1, "P1", "point1"));
				ret.add(new FriendItem(p2, "P2", "point2"));
				ret.add(new FriendItem(p3, "P3", "point3"));
			}
				break;
			case 1:
			{
				GeoPoint p1 = new GeoPoint((int) (30.67 * 1E6), (int) (104.07 * 1E6)+i);
				GeoPoint p2 = new GeoPoint((int) (30.68 * 1E6), (int) (104.08 * 1E6)+i);
				
				ret.add(new FriendItem(p1, "P1", "point1"));
				ret.add(new FriendItem(p2, "P2", "point2"));
			}
			case 2:
			{
				GeoPoint p1 = new GeoPoint((int) (30.67 * 1E6)+i, (int) (104.07 * 1E6));
				
				ret.add(new FriendItem(p1, "P1", "point1"));
			}
			default:
				break;
		}
		i++;
		return ret;
	}
}
