package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.*;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ParticipationValidationActivity extends AppCompatActivity {
    private Participation selectedParticipation;
    private DBHelper db;
    private User user;
    private Event event;
    private static final int REQUEST_CODE_SEND_SMS = 101;

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
        event = selectedParticipation.getEvent();
        user = selectedParticipation.getUser();
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

    public void emailUser(View view)
    {
        if (view instanceof TextView)
        {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"+user.getmEmail())); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{user.getmEmail()});
            intent.putExtra(Intent.EXTRA_SUBJECT, event.getName());
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }
    public void acceptRequest(View view)
    {
        if (view instanceof Button)
        {
            selectedParticipation.setStatusCode(Participation.VALIDATED);
            selectedParticipation.setValidationRequested(false);
            db.updateParticipation(selectedParticipation);
            String mess = getResources().getString(R.string.sms_validation_request) + event.getName()
                    + getResources().getString(R.string.sms_approve);
            sendSms(mess);
            super.onBackPressed();
        }
    }
    public void denyRequest(View view)
    {
        if (view instanceof Button)
        {
            selectedParticipation.setStatusCode(Participation.VALIDATION_DENY);
            selectedParticipation.setValidationRequested(false);
            db.updateParticipation(selectedParticipation);
            String mess = getResources().getString(R.string.sms_validation_request) + event.getName()
                    + getResources().getString(R.string.sms_deny);
            super.onBackPressed();
        }
    }

    private void sendSms(String message)
    {
        // Ask for permission to send text message:
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,new String[] {android.Manifest.permission.SEND_SMS},REQUEST_CODE_SEND_SMS);
            // Define a reference to SmsManager (manages text messages)
        else {
            SmsManager manager = SmsManager.getDefault();

            // For each loop through the contacts list
                manager.sendTextMessage(user.getmPhoneNum(), null, message, null, null);


            Toast.makeText(this,"Message sent to "+ user.getFirstName(),Toast.LENGTH_SHORT).show();

        }
    }

}
