package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EventDetailsActivity extends AppCompatActivity {

    private TextView eventDetailsNameTextView;
    private TextView eventDetailsTimeTextView;
    private TextView eventDetailsDescriptionTextView;
    private TextView eventDetailsLocationTextView;
    private ImageView eventDetailsImageView;
    private Event selectedEvent;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        eventDetailsImageView = (ImageView) findViewById(R.id.eventDetailsImageView);
        eventDetailsNameTextView = (TextView) findViewById(R.id.eventDetailsNameTextView);
        eventDetailsTimeTextView = (TextView) findViewById(R.id.eventDetailsTimeTextView);
        eventDetailsLocationTextView = (TextView) findViewById(R.id.eventDetailsLocationTextView);
        eventDetailsDescriptionTextView = (TextView) findViewById(R.id.eventDetailsDescriptionTextView);

        db = new DBHelper(this);
        Intent detailsIntent = getIntent();
        selectedEvent = detailsIntent.getExtras().getParcelable("SelectedEvent");
        Button selectedButton = (Button) findViewById(R.id.eventDetailsButton);
        User loginUser = db.getLoginUser();
        if (db.checkParticipation(loginUser.getmId(), selectedEvent.getId())) {
            Participation participation = db.getParticipation(loginUser.getmId(),selectedEvent.getId());
            if (participation.getStatusCode() == Participation.REGISTERED )
                if (selectedEvent.eventPassed())
                    selectedButton.setText(R.string.request_for_validation);
                else
                    selectedButton.setText(R.string.registered);
            else if (participation.getStatusCode() == Participation.VALIDATED)
                selectedButton.setText(R.string.validated);
        }
        else {
            selectedButton.setText(R.string.register);
        }

        eventDetailsImageView.setImageURI(selectedEvent.getImageUri());
        eventDetailsNameTextView.setText(selectedEvent.getName());
        eventDetailsTimeTextView.setText(selectedEvent.getStartDate() + " - " + selectedEvent.getEndDate());
        eventDetailsLocationTextView.setText(selectedEvent.getLocation());
        eventDetailsDescriptionTextView.setText(selectedEvent.getDescription());
    }

    public void eventDetailsButtonOnClick(View view) {
        if (view instanceof Button) {
            Button selectedButton = (Button) view;

            User loginUser = db.getLoginUser();
            if (db.checkParticipation(loginUser.getmId(), selectedEvent.getId())) {
                Participation participation = db.getParticipation(loginUser.getmId(),selectedEvent.getId());
                if (participation.getStatusCode() == Participation.REGISTERED )
                    if (selectedEvent.eventPassed()) {
                        participation.setValidationRequested(true);
                        db.updateParticipation(participation);
                        selectedButton.setText(R.string.request_for_validation);
                    }
                    else {
                        selectedButton.setText(R.string.registered);
                    }
                else if (participation.getStatusCode() == Participation.VALIDATED)
                    selectedButton.setText(R.string.validated);
            }
            else {
                db.addParicipation(Participation.REGISTERED,false,0.0f,"",loginUser.getmId(),selectedEvent.getId());
                selectedButton.setText(R.string.registered);
            }
        }
    }
}
