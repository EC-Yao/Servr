package com.example.eddy.servr.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.eddy.servr.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout myTabs;
    ViewPager myPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myTabs = (TabLayout) findViewById(R.id.MyTabs);
        myPage = (ViewPager) findViewById(R.id.MyPage);

        myTabs.setupWithViewPager(myPage);
        setUpViewPager(myPage);

    }

    public void setUpViewPager (ViewPager viewPage){
        myViewPageAdapter adapter =new myViewPageAdapter(getSupportFragmentManager());
        adapter.addFragmentPage(new Fragment_1(), "Stream");
        adapter.addFragmentPage(new Fragment_2(), "Search");
        adapter.addFragmentPage(new Fragment_3(), "Profile");

        viewPage.setAdapter(adapter);
    }

    public class myViewPageAdapter extends FragmentPagerAdapter{
        private List<Fragment> myFragment = new ArrayList<>();
        private List<String>myPageTitle = new ArrayList<>();

        public myViewPageAdapter(FragmentManager manager){
            super(manager);
        }

        public void addFragmentPage(Fragment frag, String title){
            myFragment.add(frag);
            myPageTitle.add(title);
        }

        @Override
        public Fragment getItem(int position){
            return myFragment.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position){
            return myPageTitle.get(position);
        }

        @Override
        public int getCount(){
            return 3;
        }


    }

}
