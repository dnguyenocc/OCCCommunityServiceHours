package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ParticipationValidationActivity extends AppCompatActivity {
    private Participation selectedParticipation;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participation_validation);

        ImageView requestUserImageView = (ImageView) findViewById(R.id.requestUserImageView);
        TextView requestUserNameTextView = (TextView) findViewById(R.id.requestUserNameTextView);
        TextView requestUserMailTextView = (TextView) findViewById(R.id.requestUserEmailTextView);
        TextView requestUserPhoneTextView = (TextView) findViewById(R.id.requestUserPhoneTextView);
        TextView requestEventNameTextView = (TextView) findViewById(R.id.requestEventNameTextView);
        TextView requestEventTimeTextView = (TextView) findViewById(R.id.requestEventTimeTextView);
        TextView requestEventLocationTextView = (TextView) findViewById(R.id.requestEventLocationTextView);
        TextView hoursTextView = (TextView) findViewById(R.id.hoursTextView);
        TextView responsibilitiesTextView = (TextView) findViewById(R.id.responsibilitiesTextView);
        db = new DBHelper(this);
        Intent detailsIntent = getIntent();
        selectedParticipation = detailsIntent.getExtras().getParcelable("SelectedParticipation");
        Event event = selectedParticipation.getEvent();
        User  user = selectedParticipation.getUser();
        requestUserImageView.setImageURI(user.getmImageUri());
        requestUserNameTextView.setText(user.getLastName()+", "+user.getFirstName());
        requestUserMailTextView.setText(user.getmEmail());
        requestUserPhoneTextView.setText(user.getmPhoneNum());

        requestEventNameTextView.setText(event.getName());
        requestEventTimeTextView.setText(event.getStartDate() + " to " +event.getEndDate());
        requestEventLocationTextView.setText(event.getLocation());
        hoursTextView.setText(String.valueOf(selectedParticipation.getServiceHours()));
        responsibilitiesTextView.setText(selectedParticipation.getResponsibilities());

    }
    public void acceptRequest(View view)
    {
        if (view instanceof Button)
        {
            selectedParticipation.setStatusCode(Participation.VALIDATED);
            selectedParticipation.setValidationRequested(false);
            db.updateParticipation(selectedParticipation);
        }
    }
    public void denyRequest(View view)
    {
        if (view instanceof Button)
        {
            selectedParticipation.setStatusCode(Participation.VALIDATION_DENY);
            selectedParticipation.setValidationRequested(false);
            db.updateParticipation(selectedParticipation);
        }
    }

}
