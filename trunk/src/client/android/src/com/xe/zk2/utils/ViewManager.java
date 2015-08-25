package com.xe.zk2.utils;

import java.util.Stack;

import android.view.View;

public class ViewManager {
	private Stack<View> mStack = new Stack<View>();
	
	public void pushView(View view){
		mStack.push(view);
	}
	
	public View popView(){
		return mStack.pop();
	}
	
	public void clear(){
		mStack.removeAllElements();
	}
	
	public View popView(int num){
		int back;
		View ret = null;
		if(num <= 0 || num >= mStack.size()){
			back = mStack.size()-1;
		}else{
			back = num;
		}
		
		for(int i=0; i<back ;i++){
			ret = mStack.pop();
		}
		return ret;
	}
}
