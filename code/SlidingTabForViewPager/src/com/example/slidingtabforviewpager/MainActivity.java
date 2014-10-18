package com.example.slidingtabforviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.slidingtabforviewpager.FixedSlideTab.OnTabClickListener;

/**
 * @Date 2014年10月13日 11:11:53
 * 
 * @author 门前大桥下 ❀
 * 
 *         主要程序类
 *
 */
public class MainActivity extends FragmentActivity implements OnTabClickListener {

	private ViewPager mViewpager;

	private FixedSlideTab mFixedSlideTab;

	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final float density = getResources().getDisplayMetrics().density;

		adapter = new MyAdapter(getSupportFragmentManager());

		mViewpager = (ViewPager) findViewById(R.id.viewpager);
		mViewpager.setAdapter(adapter);
		mViewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// super.onPageScrolled(position, positionOffset,
				// positionOffsetPixels);
				mFixedSlideTab.onTabScrolled(position, positionOffset);
			}
		});

		mFixedSlideTab = (FixedSlideTab) findViewById(R.id.tab);
		mFixedSlideTab.setTextColor(0xFFFFFFFF);
		mFixedSlideTab.setTextSelectedColor(0xfff33c7a);
		mFixedSlideTab.setTextSize(16);
		mFixedSlideTab.setIndicatorColor(0xfff33c7a);
		mFixedSlideTab.setIndicatorHeight((int) (3 * density));
		mFixedSlideTab.setBottomLineColor(0xffb3b3b3);
		mFixedSlideTab.setPressingColor(0xffdfdfdf);
		// mFixedSlideTab.setBackgroundResource(R.color.white);
		mFixedSlideTab.setOnTabClickListener(this);
		
		int[] ii= new int[]{R.drawable.common_problems_ic,R.drawable.login_phone_ic,R.drawable.login_pwd_ic,R.drawable.nickname};
		for (int i = 0; i < adapter.getCount(); i++) {
			mFixedSlideTab.addTab(adapter.getPageTitle(i).toString(),ii[i]);
		}

	}

	@Override
	public void onTabClick(int position) {
		// TODO Auto-generated method stub
		if (mViewpager.getCurrentItem() != position) {
			mViewpager.setCurrentItem(position, true);
		}
	}

	/**
	 * @Date 2014年10月13日 11:11:33
	 * 
	 * @author 门前大桥下 ❀
	 * 
	 *         ViewPager 适配器
	 *
	 */
	public class MyAdapter extends FragmentPagerAdapter {

		private final int[] PAGE_TITLES = new int[] { R.string.tab1, R.string.tab2, R.string.tab3, R.string.tab4 };

		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// return PlaceholderFragment.newInstance(30 + position);
			return Fragment_Test.newInstance(position);

		}

		@Override
		public int getCount() {
			return PAGE_TITLES.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return getString(PAGE_TITLES[position]);
		}
	}

}
