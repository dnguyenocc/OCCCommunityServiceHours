package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AttendingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SessionManager manager;
    DBHelper db;

    // Keys for reading data from SharedPreferences
    public static final String CHOICES = "pref_numberOfChoices", REGIONS = "pref_regionsToInclude";

    // Force portrait mode and check for preferences change
    private boolean phoneDevice = true, preferencesChanged = true;

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * onCreate generates the appropriate layout to inflate, depending on the
     * screen size. If the device is large or x-large, it will load the content_quiz.xml
     * (sv700dp-land) which includes both the fragment_quiz.xml and fragment_settings.xml.
     * Otherwise, it just inflates the standard content_main.xml with the fragment_quiz.
     *
     * All default preferences are set using the preferences.xml file.
     * @param savedInstanceState The saved state to restore (not being used)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attending);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().show();

        db = new DBHelper(this);
        //TODO get user from Login table
        User user = db.getLoginUser();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        try {
            drawer.addDrawerListener(toggle);
            toggle.syncState();
        } catch (NullPointerException e) {
            Log.i("NullPtr Exception", "Attempting add a listener on drawer");}

        //TODO update the header navigation by user interface
        NavigationView navigationView;
        try {
            navigationView = (NavigationView) findViewById(R.id.nav_view_admin);
            View headerView =  navigationView.getHeaderView(0);
            TextView usernameAccountTextView = (TextView)headerView.findViewById(R.id.usernameHeaderTextView);
            ImageView profileImageView = (ImageView) headerView.findViewById(R.id.profileHeaderImageView);
            profileImageView.setImageURI(user.getmImageUri());
            usernameAccountTextView.setText(user.getmUserName());
            navigationView.setNavigationItemSelectedListener(this);
        } catch (NullPointerException e) {Log.i("NullPtr Exception", "Attempting to access methods on a null NavigationView");}

        // determine screen size
        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        // if device is a tablet, set phoneDevice to false
        if (screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE ||
                screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE)
            phoneDevice = false; // not a phone-sized device

        // if running on phone-sized device, allow only portrait orientation
        if (phoneDevice) setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void editEvent(View view) {
        if (view instanceof LinearLayout) {
            LinearLayout selectedLayout = (LinearLayout) view;
            Event selectedEvent = (Event) selectedLayout.getTag();
            Log.i("OCC Community Service", selectedEvent.toString());

            Bundle bundle = new Bundle();
            bundle.putParcelable("SelectedEvent", selectedEvent);

            Fragment fragment = null;

            try {
                fragment = EditEventFragment.class.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            fragment.setArguments(bundle);

            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.host_fragment, fragment).commit();
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);


        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView search = (SearchView) menu.findItem(R.id.menu_search).getActionView();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //TODO write your code what you want to perform on search
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //TODO write your code what you want to perform on search text change
                return false;
            }
        });

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_profile) {
            // transition fragment
            transitionFragment( new  AccountDetailsFragment(), "AccountDetail");

        }
        else if (id == R.id.nav_create_events) {
            //TODO put  fragment here
            startActivity(new Intent(this, HostEventActivity.class));


        }else if (id == R.id.nav_home) {
            //TODO put  fragment here
            transitionFragment(new AllEventListFragment(),"Homepage");
        }
        else if (id == R.id.nav_all_events) {
            //TODO put  fragment here
            transitionFragment(new AllEventListFragment(), "AllEventList");
        }
        else if (id == R.id.nav_past_events) {
            //TODO put  fragment here
            transitionFragment(new AttendedEventListFragment(),"AttendedEventList");
        }
        else if (id == R.id.nav_validate_requests) {
            //TODO put  fragment here
            transitionFragment(new ValidationRequestListFragment(), "ValidationRequestList");
        } else if (id == R.id.nav_point) {
            //TODO put fragment want to be transition here
            startActivity(new Intent(this, PointAwardActivity.class));
        }
        else if (id == R.id.nav_feedback) {
            transitionFragment(new FeedbackFragment(),"Feedback");
        }
        else if (id == R.id.nav_exist) {
            db.logout(db.getLoginUser());
            manager.setPreferences(AttendingActivity.this, "status", "0");
            finish();
            startActivity(new Intent(AttendingActivity.this, LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //create tag for fragment so we can look up fragment later by tag
    // for example: DemoFragment fragmentDemo = (DemoFragment) getSupportFragmentManager().findFragmentByTag("TAG NAME");
    public void transitionFragment(Fragment fragmentClass, String tag)
    {
        try {
            FragmentTransaction fragment = getSupportFragmentManager().beginTransaction();
            // Insert the fragment by replacing any existing fragment
            fragment.replace(R.id.fragmentContent, fragmentClass,tag).commit();
            //fragment.add(R.id.fragmentContent, fragmentClass).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
