package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class EventDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

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
    private GoogleMap mMap;

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
        // Hook up our support map fragment to this activity
        SupportMapFragment caffeineMapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.eventMapFragment);

        caffeineMapFragment.getMapAsync(this);


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
            db.addParticipation(Participation.REGISTERED,false,0.0f,"",loginUser.getmId(),selectedEvent.getId());
            updateButton();
            updateStatus();
        }
    }

    public void requestValidation(View view) {
        if (view instanceof Button) {
            Participation participation = db.getParticipation(loginUser.getmId(),selectedEvent.getId());
            Intent detailsIntent = new Intent(this, requestValidationActivity.class);
            detailsIntent.putExtra("SelectedParticipation",participation);
            startActivity(detailsIntent);
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
            if (participation.getStatusCode() == Participation.REGISTERED) {
                if (participation.getValidationRequested())
                    eventDetailsStatusTextView.setText(R.string.validation_request_sent);
                else
                eventDetailsStatusTextView.setText(R.string.registered);
            }
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
            else if (participation.getStatusCode()==Participation.VALIDATION_DENY)
                buttonLinearLayouts[2].setVisibility(View.VISIBLE);
        }
        else {
           buttonLinearLayouts[0].setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Loop through each Location

        //LatLng coordinate = new LatLng(33.671028, -117.911305);
        LatLng currentPosition = getLocationFromAddress(this,selectedEvent.getLocation());
            // Add a marker at that coordinate
        if (currentPosition == null)
            currentPosition = new LatLng(33.671028, -117.911305);
        mMap.addMarker(new MarkerOptions().position(currentPosition).title(selectedEvent.getName()));


        // Change the camera view to our current position:
        //LatLng currentPosition = new LatLng(33.671028, -117.911305);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(currentPosition).zoom(14.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return p1;
    }
}
