package com.example.eddy.servr.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
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


import com.example.eddy.servr.R;
import com.example.eddy.servr.fragments.*;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    /*
    user
    pin
    email
    phone
    city
    country

     */

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
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.navigation_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView =
                (SearchView) menu.findItem(R.id.menuSearch).getActionView();
        try{
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        } catch (NullPointerException e){
            System.err.println();
        }


        //((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setTextColor(Color.BLACK);
        //((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(Color.GRAY);

        final MenuItem searchItem = menu.findItem(R.id.menuSearch);
        final MenuItem settingItem = menu.findItem(R.id.action_settings);

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


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //On submit
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //add text change stuff here
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSearch:
                System.err.println("Search Pressed");
                break;

            case R.id.action_settings:
//                Intent i = new Intent(getApplicationContext(), ServiceItemActivity.class);
//                startActivity(i);
                System.out.println("Action! Settings! Lights!");
//                View popupView = LayoutInflater.from(this).inflate(R.layout.fragment_settings, null);
//                final PopupWindow popupSettings = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//                popupSettings.showAsDropDown(popupView,0,0);
                break;
            default: System.err.println("App Bar Failure: GO TO MainActivity.Java");
        }
        return super.onOptionsItemSelected(item);
    }

    public void setUpViewPager (ViewPager viewPage){
        myViewPageAdapter adapter =new myViewPageAdapter(getSupportFragmentManager());
        adapter.addFragmentPage(new StreamFragment(), "Stream");
       // adapter.addFragmentPage(new SearchFragment(), "Search");
        adapter.addFragmentPage(new ProfileFragment(), "Profile");

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
