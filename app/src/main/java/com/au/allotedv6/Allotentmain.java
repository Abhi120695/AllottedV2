package com.au.allotedv6;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.backendless.Backendless;
import com.quickblox.core.QBSettings;

public class Allotentmain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG ="level400" ;
    public static final int MY_REQUEST_CODE = 123;
    private static final int MY_REQUEST_CODE2 =124 ;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    Tabfragment mTabfragment;
    private static final long DRAWER_CLOSE_DELAY_MS = 10;
    private static final String NAV_ITEM_ID = "navItemId";
    // private ActionBarDrawerToggle mDrawerToggle;
    private final Handler mDrawerActionHandler = new Handler();

    public ViewDatabase mViewDatabase=new ViewDatabase();
    public Getstudents mGetstudents=new Getstudents();

    private int mNavItemId;
    private ActionBarDrawerToggle mDrawerToggle;
    static final String APP_ID = "36287";
    static final String AUTH_KEY = "B2Z8RjJR9aTB5uW";
    static final String AUTH_SECRET = "OzdP4Mq3dqZHzkK";
    static final String ACCOUNT_KEY = "bsaMK5iraB2G6VTX9JMH";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allotentmain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Backendless.initApp(getApplicationContext(), "CA422B34-F29D-105D-FF75-AADF25000500", "737E46FA-05A1-D547-FFAA-17D97421CF00", "v1");

        Intent intent = getIntent();


        QBSettings.getInstance().init(getApplicationContext(), APP_ID, AUTH_KEY, AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(ACCOUNT_KEY);




        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;
        mNavigationView.inflateHeaderView(R.layout.header);

        if (null == savedInstanceState) {
            mNavItemId = R.id.Register;
        } else {
            mNavItemId = savedInstanceState.getInt(NAV_ITEM_ID);
        }





        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        // mFragmentTransaction = mFragmentManager.beginTransaction();
        //mFragmentTransaction.replace(R.id.containerView,new Tabfragment()).commit();

        mNavigationView.setNavigationItemSelectedListener(this);

        // select the correct nav menu item
        mNavigationView.getMenu().findItem(mNavItemId).setChecked(true);



        // set up the hamburger icon to open and close the drawer







        /**
         * Setup click events on the Navigation View Items.
         */
        /*

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if(menuItem.getItemId()==R.id.data_manager){
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new Tabfragment()).commit();
                    //fab.setVisibility(View.VISIBLE);
                }

                if(menuItem.getItemId()==R.id.Settings)
                {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new Settings()).commit();
                   // fab.setVisibility(View.INVISIBLE);

                }
                if(menuItem.getItemId()==R.id.About)
                {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new About()).commit();
                    //fab.setVisibility(View.INVISIBLE);

                }







                return false;
            }

        });
        */


        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar1 = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();
        navigate(mNavItemId);


    }
    private void navigate(final int itemId) {
        switch (itemId) {

            case R.id.Register:
                mFragmentManager
                        .beginTransaction().replace(R.id.containerView, new Tabfragmen2()).commit();
                //fab.setVisibility(View.VISIBLE);
                break;

            case R.id.Settings:

                mFragmentManager
                        .beginTransaction().replace(R.id.containerView, new com.au.allotedv6.Settings()).add(new Settings(),"settings").addToBackStack("settings").commit();
                // fab.setVisibility(View.INVISIBLE);
                break;

            case R.id.About:

                mFragmentManager
                        .beginTransaction().replace(R.id.containerView, new About()).add(new About(), "About").addToBackStack("About").commit();
                //fab.setVisibility(View.INVISIBLE);

                break;

        }
    }
    @Override
    public boolean onNavigationItemSelected(final MenuItem menuItem) {
        // update highlighted item in the navigation menu
        menuItem.setChecked(true);
        mNavItemId = menuItem.getItemId();

        // allow some time after closing the drawer before performing real navigation
        // so the user can see what is happening
        mDrawerLayout.closeDrawer(GravityCompat.START);
        mDrawerActionHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navigate(menuItem.getItemId());
            }
        }, DRAWER_CLOSE_DELAY_MS);
        return true;
    }
    @Override
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.support.v7.appcompat.R.id.home) {
            return mDrawerToggle.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {

        }
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
        }


    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NAV_ITEM_ID, mNavItemId);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Inflate the menu; this adds items to the action bar if it is present.


        return true;
    }
}
