package com.example.kimhao.first_project.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.kimhao.first_project.Adapter.ViewPagerAdapter;
import com.example.kimhao.first_project.R;

public class FragmentActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private ViewPager mViewPager;
    private TabLayout.Tab Home;
    private TabLayout.Tab ListUser;
    private TabLayout.Tab User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        TabLayout mTabLayout =(TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

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
