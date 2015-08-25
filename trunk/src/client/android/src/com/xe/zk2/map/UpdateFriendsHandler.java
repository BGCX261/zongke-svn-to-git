package com.xe.zk2.map;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class UpdateFriendsHandler extends Handler {
	
	public static final String ARG_FRIENDS = "FRIENDS";
	
	private FriendsOverlay mFriends;
	
	public UpdateFriendsHandler(FriendsOverlay friends){
		this.mFriends = friends;
	}
	
	@Override
	public void handleMessage(Message msg) {
		Bundle data = msg.getData();
		ArrayList<FriendItem> friends = (ArrayList<FriendItem>) data.getSerializable(ARG_FRIENDS);
		mFriends.update(friends);
	}
	
}
