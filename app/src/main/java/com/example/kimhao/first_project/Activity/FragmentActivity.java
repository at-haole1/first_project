package com.example.kimhao.first_project.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.kimhao.first_project.Adapter.ViewPagerAdapter;
import com.example.kimhao.first_project.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_fragment)
public class FragmentActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    @ViewById(R.id.viewPager)
    ViewPager mViewPager;

    @ViewById(R.id.tabLayout)
    TabLayout mTabLayout;

    TabLayout.Tab Home;
    TabLayout.Tab ListUser;
    TabLayout.Tab User;

    @AfterViews
    void pagerChange(){
        Home = mTabLayout.newTab();
        ListUser = mTabLayout.newTab();
        User = mTabLayout.newTab();

        Home.setText("Home");
        ListUser.setText("ListUser");
        User.setText("User");

        mTabLayout.addTab(Home);
        mTabLayout.addTab(ListUser);
        mTabLayout.addTab(User);

        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setOnTabSelectedListener(this);

        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),mTabLayout.getTabCount());
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
