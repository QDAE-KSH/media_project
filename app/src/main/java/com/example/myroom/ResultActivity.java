package com.example.myroom;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.myroom.Adapter.ContentsPagerAdapter;
import com.gigamole.navigationtabstrip.NavigationTabStrip;

/**
 * Created by GIGAMOLE on 28.03.2016.
 */
public class ResultActivity extends AppCompatActivity {
    private String dest_keyword;
    private String dest_address;
    private Integer rushHour;
    private ViewPager mViewPager;
    private NavigationTabStrip mTopNavigationTabStrip;
    private ContentsPagerAdapter mContentPagerAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent=getIntent();
        dest_keyword=intent.getExtras().getString("dest_keyword");
        dest_address=intent.getExtras().getString("dest_address");
        rushHour=intent.getExtras().getInt("rushHour");

        initUI();
        setUI();

        mContentPagerAdapter = new ContentsPagerAdapter( getSupportFragmentManager(), 3,dest_keyword,dest_address,rushHour);
        mViewPager.setAdapter(mContentPagerAdapter);
    }

    private void initUI() {
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mTopNavigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts_top);
    }

    private void setUI() {
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                final View view = new View(getBaseContext());
                container.addView(view);
                return view;
            }
        });

        mTopNavigationTabStrip.setTabIndex(1, true);
        mTopNavigationTabStrip.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {

            }
            @Override
            public void onEndTabSelected(String title, int index) {
                mViewPager.setCurrentItem(index);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mTopNavigationTabStrip.setTabIndex(position,true);
            }

            @Override
            public void onPageSelected(int position) {
                mTopNavigationTabStrip.setTabIndex(position,true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        final NavigationTabStrip navigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts);
//        navigationTabStrip.setTitles("Nav", "Tab", "Strip");
//        navigationTabStrip.setTabIndex(0, true);
//        navigationTabStrip.setTitleSize(15);
//        navigationTabStrip.setStripColor(Color.RED);
//        navigationTabStrip.setStripWeight(6);
//        navigationTabStrip.setStripFactor(2);
//        navigationTabStrip.setStripType(NavigationTabStrip.StripType.LINE);
//        navigationTabStrip.setStripGravity(NavigationTabStrip.StripGravity.BOTTOM);
//        navigationTabStrip.setTypeface("fonts/typeface.ttf");
//        navigationTabStrip.setCornersRadius(3);
//        navigationTabStrip.setAnimationDuration(300);
//        navigationTabStrip.setInactiveColor(Color.GRAY);
//        navigationTabStrip.setActiveColor(Color.WHITE);
//        navigationTabStrip.setOnPageChangeListener(...);
//        navigationTabStrip.setOnTabStripSelectedIndexListener(...);
    }
}
