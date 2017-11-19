package com.example.eddy.servr.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            default:
                Log.d("hey", "hey");
        }
        return super.onOptionsItemSelected(item);
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

        private myViewPageAdapter(FragmentManager manager){
            super(manager);
        }

        private void addFragmentPage(Fragment frag, String title){
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
