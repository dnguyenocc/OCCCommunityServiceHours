package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

/**
 * A class that handles the logic portion behind Admin's activities
 * and sets up the approp settings and pref for when an admin logs in.
 *
 */
public class AdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    SessionManager manager;
    DBHelper db;

    /**
     * onCreate generates the drawer_layout when the user either swipes the screen from the fat left
     * or clicks on the collapsed navigation menu
     *
     * All setups are made in this method.
     * @param savedInstanceState The saved state to restore (not being used)
     *
     **/
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Hide the title on app bar for Search View
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        manager = new SessionManager();
        db = new DBHelper(this);
        //TODO get user from Login table
        User user = db.getLoginUser();


        //Set the main fragment
        transitionFragment(new HomeFragment(),"Homepage");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //TODO update the header navigation by user interface
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_admin);
        View headerView =  navigationView.getHeaderView(0);
        TextView usernameAccountTextView = (TextView)headerView.findViewById(R.id.usernameHeaderTextView);
        ImageView profileImageView = (ImageView) headerView.findViewById(R.id.profileHeaderImageView);
        TextView hoursPointHeaderTextView = (TextView) headerView.findViewById(R.id.hoursPointHeaderTextView);
        profileImageView.setImageURI(user.getmImageUri());
        usernameAccountTextView.setText(user.getmUserName());
        hoursPointHeaderTextView.setText(getString(R.string.hour_points)+" "+ String.valueOf(db.getHoursbyUserId(user.getmId())));

        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Goes back to the fragment that was displayed previous to the current fragment
     *
     * Ex. Frag A --> User goes to Frag B --> Frag B displayed -->
     *     User clicks back button --> Frag A is displayed
     */
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag("Homepage") != null) {
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
        else
        {
            transitionFragment(new HomeFragment(),"Homepage");
        }
    }

    /**
     * Inflates the menu and handles search inputs from the user
     *
     * @param menu The menu to create.
     * @return
     */
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

    /**
     * Handles action bar item clicks
     *
     * @param item
     * @return
     */
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


    /**
     * Handles click events for navigational view items.
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        doNavigate(id);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Transitions from fragment to fragment
     * inflates the approp fragment that the user wants to view
     *
     * @param fragmentClass The name of the fragment class that the user wants to transition to.
     * @param tag A tag name for that fragment.
     */
    public void transitionFragment(Fragment fragmentClass, String tag)
    {
        //create tag for fragment so we can look up fragment later by tag
        // for example: DemoFragment fragmentDemo = (DemoFragment) getSupportFragmentManager().findFragmentByTag("TAG NAME");
        try {
            FragmentTransaction fragment = getSupportFragmentManager().beginTransaction();
            // Insert the fragment by replacing any existing fragment
            fragment.replace(R.id.fragmentContent, fragmentClass,tag).commit();
            //fragment.add(R.id.fragmentContent, fragmentClass).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles which fragment to go to to depending on which layout is selected
     *
     * @param id The id of the item that was selected from the drawer menu
     */
    public void doNavigate(int id){
        if (id == R.id.nav_profile) {
            // transition fragment
            transitionFragment( new  AccountDetailsFragment(), "AccountDetail");

        }
        else if (id == R.id.nav_create_events) {
            //TODO put  fragment here
            startActivity(new Intent(this, HostEventActivity.class));


        }else if (id == R.id.nav_home) {
            //TODO put  fragment here
            transitionFragment(new HomeFragment(),"Homepage");
        }
        else if (id == R.id.nav_all_events) {
            //TODO put  fragment here
            transitionFragment(new AllEventListFragment(), "AllEventList");
        }
        else if (id == R.id.nav_upcoming_events) {
            //TODO put  fragment here
            transitionFragment(new AttendingEventListFragment(),"UpcomingEventList");
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
        }else if (id == R.id.nav_faq) {
            //TODO put fragment want to be transition here
            startActivity(new Intent(this,FAQActivity.class));
        } else if (id == R.id.nav_feedback) {
            transitionFragment(new FeedbackFragment(),"Feedback");
        }
        else if (id == R.id.nav_exist) {
            db.logout(db.getLoginUser());
            manager.setPreferences(AdminActivity.this, "status", "0");
            finish();
            startActivity(new Intent(AdminActivity.this, LoginActivity.class));
        }


       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;*/
    }

    /**
     * Starts an intent to the Event details view when the user clicks
     * on an item in the events list view
     *
     * @param view The item that was clicked on from the custom list view
     *
     */
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

    /**
     * Starts an intent to the participation validation activity passing the information
     * of an item that was clicked from a list view
     *
     * @param view The item that was clicked on from the custom list view
     */
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

    public void reset(View view)
    {
    }

}
