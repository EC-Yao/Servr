package com.example.eddy.servr.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.example.eddy.servr.R;
import com.example.eddy.servr.ServerConnection;
import com.example.eddy.servr.fragments.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  November 13th, 2017
 *  Darren Liu
 *
 *      Main activity acting as a home page and housing different fragments
 */

public class MainActivity extends AppCompatActivity {

    TabLayout myTabs;
    ViewPager myPage;
    ProfileFragment profileFragment;
    StreamFragment streamFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializes the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Sets up the swipe layout

        myTabs = findViewById(R.id.MyTabs);
        myPage = findViewById(R.id.MyPage);

        myTabs.setupWithViewPager(myPage);

        setUpViewPager(myPage);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menuSearch).getActionView();


        //Eddy wtf do we do this is bothering me;already tried a trycatch
        if(searchManager.getSearchableInfo(getComponentName())==null){
            System.err.println("MainActivity.java @ searchManager.getSearchableInfo is equal to NULL");
        }else{
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }

        final MenuItem searchItem = menu.findItem(R.id.menuSearch);
        final MenuItem settingItem = menu.findItem(R.id.action_settings);

        //Sets the visibility of the settings icon according to whether the search icon was expanded
        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(final MenuItem item) {
                settingItem.setVisible(false);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(final MenuItem item) {
                settingItem.setVisible(true);
                return true;
            }
        });


        //Action listeners for the search bar
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                BufferingActivity.servr.searchServices(query);
                streamFragment.refreshView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equals("")){
                    BufferingActivity.servr.getServiceStream();
                    streamFragment.refreshView();
                } else {
                    BufferingActivity.servr.searchServices(newText);
                    streamFragment.refreshView();
                }
                return false;
            }
        });

        return true;
    }

    //Designates what occurs when an actionBar icon is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSearch:
                System.err.println("Search Pressed");
                break;

            case R.id.action_settings:
                Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(i);
                break;

            default: System.err.println("App Bar Failure: GO TO MainActivity.Java");
        }
        return super.onOptionsItemSelected(item);
    }

    public void setUpViewPager (ViewPager viewPage){
        myViewPageAdapter adapter =new myViewPageAdapter(getSupportFragmentManager());

        streamFragment = new StreamFragment();
        profileFragment = new ProfileFragment();

        adapter.addFragmentPage(streamFragment, "Stream");
        adapter.addFragmentPage(profileFragment, "Profile");

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
            return 2;
        }
    }

}
