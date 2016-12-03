package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private ProgressBar bar;
    DBHelper db;
    SessionManager manager;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        bar = (ProgressBar) findViewById(R.id.progressBar);
        bar.setProgress(0);

        this.deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        db.importUsersFromCSV("User.csv");
        db.importEventsFromCSV("Events.csv");
        db.importParticipationsFromCSV("participations.csv");

        manager = new SessionManager();

            TimerTask mediaTask = new TimerTask() {
                @Override
                public void run() {

                    String status = manager.getPreferences(SplashActivity.this, "status");
                    int userIdPref = manager.getIdPreferences(SplashActivity.this, "id");


                    Log.d("status", status);

                    if (status.equals("admin"))
                    {
                       user = db.getUser(userIdPref);
                        Intent intentAdmin = new Intent(SplashActivity.this, AdminActivity.class);
                        intentAdmin.putExtra("selectedUser",user);
                        startActivity(intentAdmin);

                    }
                    else if(status.equals("user"))
                    {
                        user = db.getUser(userIdPref);
                        Intent intentUser = new Intent(SplashActivity.this, MainActivity.class);
                        intentUser.putExtra("selectedUser",user);
                        startActivity(intentUser);
                    }
                    else
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                    finish();
                }
            };

        //Define the TimeTask to launch media activity
        Timer timer = new Timer();
        timer.schedule(mediaTask,1000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
