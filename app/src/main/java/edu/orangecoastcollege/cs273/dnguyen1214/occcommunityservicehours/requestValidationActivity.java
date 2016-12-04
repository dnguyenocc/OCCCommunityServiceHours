package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class requestValidationActivity extends AppCompatActivity {
    private EditText hoursEditText;
    private EditText responsibilitiesEditText;
    private DBHelper db;
    private Participation selectedParticipation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_validation);
        ImageView requestEventImageView = (ImageView) findViewById(R.id.requestEventImageView);
        TextView requestEventNameTextView = (TextView) findViewById(R.id.requestEventNameTextView);
        TextView requestEventTimeTextView = (TextView) findViewById(R.id.requestEventTimeTextView);
        TextView requestEventLocationTextView = (TextView) findViewById(R.id.requestEventLocationTextView);
        hoursEditText = (EditText) findViewById(R.id.hoursEditText);
        responsibilitiesEditText = (EditText) findViewById(R.id.responsibilitiesEditText);
        db = new DBHelper(this);

        Intent detailsIntent = getIntent();
        selectedParticipation = detailsIntent.getExtras().getParcelable("SelectedParticipation");
        Event event = selectedParticipation.getEvent();
        requestEventImageView.setImageURI(event.getImageUri());
        requestEventNameTextView.setText(event.getName());
        requestEventTimeTextView.setText(event.getStartDate() + " to " +event.getEndDate());
        requestEventLocationTextView.setText(event.getLocation());
        hoursEditText.setHint(String.valueOf(selectedParticipation.getServiceHours()));
        responsibilitiesEditText.setHint(selectedParticipation.getResponsibilities());

    }
    public void sendRequestValidation(View view){
        String hoursText = hoursEditText.getText().toString();
        String responsibilities = responsibilitiesEditText.getText().toString();
        if (responsibilities.isEmpty()||hoursText.isEmpty())
        {
            Toast.makeText(this,"Hours vonlunteered and Responsibilities cannot be empty",Toast.LENGTH_LONG).show();
        }
        else {
            float hours = Float.parseFloat(hoursText);
            selectedParticipation.setResponsibilities(responsibilities);
            selectedParticipation.setServiceHours(hours);
            selectedParticipation.setValidationRequested(true);

            db.updateParticipation(selectedParticipation);
            Intent detailsIntent = new Intent(this,EventDetailsActivity.class);
            detailsIntent.putExtra("SelectedEvent",selectedParticipation.getEvent());
            startActivity(detailsIntent);
        }
    }
}
