package com.llb.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llb.view.R;

public class JobFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("slide", "JobFragment--onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.job_fragment, container, false);
		Log.i("slide", "JobFragment-rootView=null");
		// 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生IllegalStateException。
		ViewGroup parent = (ViewGroup) view.getParent();
		if (parent != null) {
			parent.removeView(view);
			Log.i("slide", "JobFragment-removeView");
		}
		return view;
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.i("slide", "JobFragment--onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.i("slide", "JobFragment--onStop");
	}
}
