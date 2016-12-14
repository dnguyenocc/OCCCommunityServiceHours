package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FAQAnswerDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqanswer_detail);

        TextView descriptionFAQTextView =(TextView) findViewById(R.id.descriptionFAQTextView);
        FAQAnswer answer = getIntent().getExtras().getParcelable("selectedAnswer");
        descriptionFAQTextView.setText(answer.getDescription());
    }
}
