package com.example.myroom


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.myroom.Adapter.ContentsPagerAdapter
import com.gigamole.navigationtabstrip.NavigationTabStrip
import kotlinx.android.synthetic.main.activity_test.*

/**
 * Created by GIGAMOLE on 28.03.2016.
 */
class TestActivity : AppCompatActivity() {
    private var dest_keyword: String? = null
    private var dest_address: String? = null

    private var rushHour: Int? = null
    private var mViewPager: ViewPager? = null
    private var mTopNavigationTabStrip: NavigationTabStrip? = null
    private var mContentPagerAdapter: ContentsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val intent = intent
        dest_keyword = intent.extras!!.getString("dest_keyword")
        dest_address = intent.extras!!.getString("dest_address")
        rushHour = intent.extras!!.getInt("rushHour")
        txt_title.text=dest_keyword
        txt_time.text=rushHour.toString()+"분 이내"

        initUI()
        setUI()

        mContentPagerAdapter = ContentsPagerAdapter(supportFragmentManager, 3, dest_keyword,dest_address, rushHour!!)
        mViewPager!!.adapter = mContentPagerAdapter

    }

    private fun initUI() {
        mViewPager = findViewById(R.id.vp) as ViewPager
        mTopNavigationTabStrip = findViewById(R.id.nts_top) as NavigationTabStrip
    }

    private fun setUI() {
        mViewPager!!.adapter = object : PagerAdapter() {
            override fun getCount(): Int {
                return 3
            }

            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view == `object`
            }

            override fun destroyItem(container: View, position: Int, `object`: Any) {
                (container as ViewPager).removeView(`object` as View)
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val view = View(baseContext)
                container.addView(view)
                return view
            }
        }

        mTopNavigationTabStrip!!.setTabIndex(1, true)
        mTopNavigationTabStrip!!.onTabStripSelectedIndexListener =
            object : NavigationTabStrip.OnTabStripSelectedIndexListener {
                override fun onStartTabSelected(title: String, index: Int) {

                }

                override fun onEndTabSelected(title: String, index: Int) {
                    mViewPager!!.currentItem = index
                }
            }
        mViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                mTopNavigationTabStrip!!.setTabIndex(position, true)
            }

            override fun onPageSelected(position: Int) {
                mTopNavigationTabStrip!!.setTabIndex(position, true)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
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
