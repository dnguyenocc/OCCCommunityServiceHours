package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MainFragment.OnFragmentInteractionListener {

    DBHelper db;
    SessionManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Hide the title on app bar for Search View
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        db = new DBHelper(this);
        manager = new SessionManager();
        //TODO get user from Login table
        User user = db.getLoginUser();


        //TODO Set the main fragment
        Class fragmentClass = AllEventListFragment.class;
        transitionFragment(fragmentClass);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        //TODO update the header navigation by user interface
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_user);
        View headerView =  navigationView.getHeaderView(0);
        TextView usernameAccountTextView = (TextView)headerView.findViewById(R.id.usernameHeaderTextView);
        ImageView profileImageView = (ImageView) headerView.findViewById(R.id.profileHeaderImageView);
        TextView hoursPointHeaderTextView = (TextView) headerView.findViewById(R.id.hoursPointHeaderTextView);
        profileImageView.setImageURI(user.getmImageUri());
        usernameAccountTextView.setText(user.getmUserName());
        hoursPointHeaderTextView.setText("Hour points: " + String.valueOf(user.getmHours()));
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            startActivity(intent);
            finish();
            System.exit(0);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Class fragmentClass = null;

        if (id == R.id.nav_profile) {
            fragmentClass = AccountDetailsFragment.class;// transition fragment
            transitionFragment(fragmentClass);

        } else if (id == R.id.nav_attending_events) {
            //TODO put fragment want to be transition here
        } else if (id == R.id.nav_upcoming_events) {
            //TODO put fragment want to be transition here
        } else if (id == R.id.nav_passed_events) {
            fragmentClass = AttendedEventListFragment.class;
            transitionFragment(fragmentClass);
            //TODO put fragment want to be transition here
        } else if (id == R.id.nav_share) {
            //TODO put fragment want to be transition here
        } else if (id == R.id.nav_help) {
            //TODO put fragment want to be transition here
        }else if (id == R.id.nav_exist) {

            db.logout(db.getLoginUser());
            manager.setPreferences(MainActivity.this, "status", "0");
            finish();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void transitionFragment(Class fragmentClass)
    {
        try {
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewEventDetails(View view) {
        if (view instanceof LinearLayout) {
            LinearLayout selectedLayout = (LinearLayout) view;
            Event selectedEvent = (Event) selectedLayout.getTag();
            Log.i("OCC Community Service", selectedEvent.toString());
            Intent detailsIntent = new Intent(this, EventDetailsActivity.class);
            detailsIntent.putExtra("SelectedEvent",selectedEvent);
            startActivity(detailsIntent);
        }
    }
    public void viewRequestDetails(View view) {
        if (view instanceof LinearLayout) {
            LinearLayout selectedLayout = (LinearLayout) view;
            Participation selectedParticipation = (Participation) selectedLayout.getTag();
            Log.i("OCC Community Service", selectedParticipation.toString());
            Intent detailsIntent = new Intent(this, ParticipationValidationActivity.class);
            detailsIntent.putExtra("SelectedParticipation",selectedParticipation);
            startActivity(detailsIntent);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}