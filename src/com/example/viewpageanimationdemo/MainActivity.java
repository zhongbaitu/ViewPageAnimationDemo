package com.example.viewpageanimationdemo;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	private ViewPager mViewPager;
	private List<RelativeLayout> dataList;
	private ViewPagerAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mViewPager = new ViewPager(this);
		dataList = new ArrayList<RelativeLayout>();
		mAdapter = new ViewPagerAdapter();
		mViewPager.setAdapter(mAdapter);
		mViewPager.setPageTransformer(true, new TestTransformer());
		setContentView(mViewPager);
		
		test();
	}
	
	private void test(){
		RelativeLayout mLayout = new RelativeLayout(this);
		mLayout.setBackgroundColor(0xff666666);
		Button mButton = new Button(this);
		mButton.setText("123");
		mLayout.addView(mButton);
		dataList.add(mLayout);
		
		mLayout = new RelativeLayout(this);
		mLayout.setBackgroundColor(0xff883311);
		mButton = new Button(this);
		mButton.setText("54543");
		mLayout.addView(mButton);
		dataList.add(mLayout);
		
		mLayout = new RelativeLayout(this);
		mLayout.setBackgroundColor(0xff334455);
		mButton = new Button(this);
		mButton.setText("5757");
		mLayout.addView(mButton);
		dataList.add(mLayout);
		
		mAdapter.notifyDataSetChanged();
	}
	
	public class TestTransformer implements ViewPager.PageTransformer {

		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		@Override
		public void transformPage(View page, float position) {
			page.setRotationY(position * -30);
		}
	}

	private class ViewPagerAdapter extends PagerAdapter{

	    @Override
	    public void destroyItem(View view, int i, Object object) {
	        ((ViewPager) view).removeView(dataList.get(i));
	    }

		@Override
		public int getCount() {
			if (dataList != null) {
				return dataList.size();
			}
			return 0;
		}

	    @Override
	    public Object instantiateItem(View view, int i) {
	        ((ViewPager) view).addView(dataList.get(i), 0);
	        return dataList.get(i);
	    }

	    @Override
	    public boolean isViewFromObject(View view, Object object) {
	        return view == (object);
	    }
		
	}
}
