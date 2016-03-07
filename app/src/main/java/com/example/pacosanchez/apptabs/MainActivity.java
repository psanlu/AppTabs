package com.example.pacosanchez.apptabs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import com.example.adapters.MyFragmentPagerAdapter;
import com.example.fragments.Fragment1;
import com.example.fragments.Fragment2;
import com.example.fragments.Fragment3;
import com.example.fragments.Fragment4;
import com.example.fragments.Fragment5;
import com.example.fragments.Fragment6;
import com.example.fragments.Fragment7;
import com.example.fragments.Fragment8;
import com.example.fragments.Fragment9;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {

    ViewPager viewPager;
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);

        initViewPager();
        initTabHost();


    }

    private void initTabHost() {
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        String[] tabNames = {"Morcilla Team","Edu","Jose Miguel","Javi","Andres","Victor","Iago","Carlos","Paco"};
        for(int i=0;i<tabNames.length;i++)
        {
            TabHost.TabSpec tabSpec;
            tabSpec = tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(getApplicationContext()));
            tabHost.addTab(tabSpec);


        }

            tabHost.setOnTabChangedListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int selectedItem) {
        tabHost.setCurrentTab(selectedItem);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String tabId) {
        int selectedItem = tabHost.getCurrentTab();
        viewPager.setCurrentItem(selectedItem);

        HorizontalScrollView hScrollView = (HorizontalScrollView) findViewById(R.id.h_scroll_view);
        View tabView = tabHost.getCurrentTabView();
        int scrollPos = tabView.getLeft()-(hScrollView.getWidth()-tabView.getWidth()) / 2;
        hScrollView.smoothScrollTo(scrollPos,0);

    }

    public class FakeContent implements TabHost.TabContentFactory {

        Context context;
        public FakeContent(Context mcontext){
          context = mcontext;
        }
        @Override
        public View createTabContent(String tag) {

            View fakeView = new View(context);
            fakeView.setMinimumHeight(0);
            fakeView.setMinimumWidth(0);
            return fakeView;
        }
    }

    private void initViewPager() {

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        List<Fragment> listFragments= new ArrayList<Fragment>();
        listFragments.add(new Fragment1());
        listFragments.add(new Fragment2());
        listFragments.add(new Fragment3());
        listFragments.add(new Fragment4());
        listFragments.add(new Fragment5());
        listFragments.add(new Fragment6());
        listFragments.add(new Fragment7());
        listFragments.add(new Fragment8());
        listFragments.add(new Fragment9());

        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager(),listFragments);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }
}
