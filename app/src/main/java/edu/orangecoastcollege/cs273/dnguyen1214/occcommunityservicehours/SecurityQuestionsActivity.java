package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecurityQuestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_questions);
        User user = getIntent().getExtras().getParcelable("User");



    }
}
