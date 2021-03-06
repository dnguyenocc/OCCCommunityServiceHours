package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class RecoveryActivity extends AppCompatActivity {

    SessionManager sessionManager;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);

        transitionFragment(AskEmailRecoveryFragment.class);
        sessionManager = new SessionManager();
        db = new DBHelper(this);

    }



    public void transitionFragment(Class fragmentClass)
    {
        try {
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            // Insert the fragment by adding any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.recoveryContent, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
