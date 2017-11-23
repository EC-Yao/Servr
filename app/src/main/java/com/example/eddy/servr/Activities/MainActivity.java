package com.example.eddy.servr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.eddy.servr.R;
import com.example.eddy.servr.fragments.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout myTabs;
    ViewPager myPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myTabs = findViewById(R.id.MyTabs);
        myPage = findViewById(R.id.MyPage);

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
                Intent i = new Intent(getApplicationContext(), ServiceActivityTest.class);
                startActivity(i);


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

        private void startServiceActivity() {
            Intent i = new Intent(getApplicationContext(), ServiceActivityTest.class);
            startActivity(i);
        }



    }

}
