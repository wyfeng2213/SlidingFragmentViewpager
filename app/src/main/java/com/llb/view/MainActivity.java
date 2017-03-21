package com.llb.view;

import java.util.ArrayList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import com.llb.adapter.MyViewPagerAdapter;
import com.llb.fragment.ActivityFragment;
import com.llb.fragment.FriendFragment;
import com.llb.fragment.JobFragment;

public class MainActivity extends FragmentActivity implements OnPageChangeListener {
	private ViewPager pager;
	private PagerAdapter mAdapter;

	private ArrayList<Fragment> fragments;
	private ArrayList<RadioButton> title = new ArrayList<RadioButton>();// 三个标题

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);// /slidingmenu里面重写了
		initView();// 初始化控件
		initTitle();
		initViewPager();
	}

	/**
	 * 初始化视图
	 */
	private void initView() {
		pager = (ViewPager) findViewById(R.id.pager);// 初始化控件
		fragments = new ArrayList<Fragment>();// 初始化数据
		fragments.add(new ActivityFragment());
		fragments.add(new JobFragment());
		fragments.add(new FriendFragment());
	}

	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
		mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), fragments);
		pager.setAdapter(mAdapter);
		pager.setOnPageChangeListener(this);
		pager.setCurrentItem(0);// 设置成当前第一个
	}

	/**
	 * 初始化几个用来显示title的RadioButton
	 */
	private void initTitle() {
		title.add((RadioButton) findViewById(R.id.title1));// 三个title标签
		title.add((RadioButton) findViewById(R.id.title2));
		title.add((RadioButton) findViewById(R.id.title3));
		title.get(0).setOnClickListener(new MyOnClickListener(0));// 设置响应
		title.get(1).setOnClickListener(new MyOnClickListener(1));
		title.get(2).setOnClickListener(new MyOnClickListener(2));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * 重写OnClickListener的响应函数，主要目的就是实现点击title时，pager会跟着响应切换
	 * 
	 * @author llb
	 * 
	 */
	private class MyOnClickListener implements OnClickListener {
		private int index;

		public MyOnClickListener(int index) {
			this.index = index;
		}

		@Override
		public void onClick(View v) {
			pager.setCurrentItem(index);// 把viewpager的视图切过去，实现捏造title跟pager的联动
			title.get(index).setChecked(true);// 设置被选中，否则布局里面的背景不会切换
		}

	}

	/**
	 * 下面三个是OnPageChangeListener的接口函数
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
		Log.i("slide", "onPageSelected+agr0=" + arg0);
		title.get(arg0).setChecked(true);// 保持页面跟按钮的联动
	}
}
