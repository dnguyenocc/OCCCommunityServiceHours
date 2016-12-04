package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EventDetailsActivity extends AppCompatActivity {

    private TextView eventDetailsNameTextView;
    private TextView eventDetailsTimeTextView;
    private TextView eventDetailsDescriptionTextView;
    private TextView eventDetailsLocationTextView;
    private ImageView eventDetailsImageView;
    private TextView eventDetailsStatusTextView;
    private LinearLayout[] buttonLinearLayouts;
    private Event selectedEvent;
    private User loginUser;
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
        eventDetailsStatusTextView = (TextView) findViewById(R.id.eventDetailsStatusTextView);
        buttonLinearLayouts = new LinearLayout[3];
        buttonLinearLayouts[0] = (LinearLayout) findViewById(R.id.eventDetailsRegisterLinearLayout);
        buttonLinearLayouts[1] = (LinearLayout) findViewById(R.id.eventDetailsRequestLinearLayout);
        buttonLinearLayouts[2] = (LinearLayout) findViewById(R.id.eventDetailsCancelLinearLayout);


        db = new DBHelper(this);
        Intent detailsIntent = getIntent();
        selectedEvent = detailsIntent.getExtras().getParcelable("SelectedEvent");
        loginUser = db.getLoginUser();
        updateStatus();
        updateButton();
        eventDetailsImageView.setImageURI(selectedEvent.getImageUri());
        eventDetailsNameTextView.setText(selectedEvent.getName());
        eventDetailsTimeTextView.setText(selectedEvent.getStartDate() + " - " + selectedEvent.getEndDate());
        eventDetailsLocationTextView.setText(selectedEvent.getLocation());
        eventDetailsDescriptionTextView.setText(selectedEvent.getDescription());
    }

    public void eventRegister(View view) {
        if (view instanceof Button) {
            db.addParicipation(Participation.REGISTERED,false,0.0f,"",loginUser.getmId(),selectedEvent.getId());
            updateButton();
            updateStatus();
        }
    }

    public void requestValidation(View view) {
        if (view instanceof Button) {
            db.addParicipation(Participation.REGISTERED,false,0.0f,"",loginUser.getmId(),selectedEvent.getId());
            updateButton();
            updateStatus();
        }
    }

    public void cancelEvent(View view) {
        if (view instanceof Button) {
            Participation participation = db.getParticipation(loginUser.getmId(),selectedEvent.getId());
            db.deleteParticipation(participation);
            updateButton();
            updateStatus();
        }
    }




    private void updateStatus(){

        if (db.checkParticipation(loginUser.getmId(), selectedEvent.getId())) {
            Participation participation = db.getParticipation(loginUser.getmId(),selectedEvent.getId());
            if (participation.getStatusCode() == Participation.REGISTERED)
                eventDetailsStatusTextView.setText(R.string.registered);
            else if (participation.getStatusCode()==Participation.VALIDATED)
                eventDetailsStatusTextView.setText(R.string.validated);
        }
        else {
            eventDetailsStatusTextView.setText("");
        }
    }
    private void updateButton(){
        // hide all button LinearLayouts
        for (LinearLayout layout : buttonLinearLayouts)
            layout.setVisibility(View.GONE);

        if (db.checkParticipation(loginUser.getmId(), selectedEvent.getId())) {
            Participation participation = db.getParticipation(loginUser.getmId(),selectedEvent.getId());
            if (participation.getStatusCode() == Participation.REGISTERED ) {
                if (selectedEvent.eventPassed())
                    buttonLinearLayouts[1].setVisibility(View.VISIBLE);
                else
                    buttonLinearLayouts[2].setVisibility(View.VISIBLE);
            }
        }
        else {
           buttonLinearLayouts[0].setVisibility(View.VISIBLE);
        }
    }
}
