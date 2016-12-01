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

    SessionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        bar = (ProgressBar) findViewById(R.id.progressBar);
        bar.setProgress(0);

        manager = new SessionManager();

            TimerTask mediaTask = new TimerTask() {
                @Override
                public void run() {

                    String status = manager.getPreferences(SplashActivity.this, "status");
                    Log.d("status", status);

                    if (status.equals("1"))
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    else
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                    finish();
                }
            };

        //Define the TimeTask to launch media activity
        Timer timer = new Timer();
        timer.schedule(mediaTask,3000);
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