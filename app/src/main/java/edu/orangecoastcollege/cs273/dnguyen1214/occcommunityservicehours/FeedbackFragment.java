package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FeedbackFragment extends Fragment implements View.OnClickListener {

    private TextInputLayout nameInput;
    private TextInputLayout messageInput;
    private EditText nameEditText;
    private EditText messageEditText;
    private Button sendButton;
    DBHelper db;
    User user;
    Context context;
    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = this.getContext();
        // Inflate the layout for this fragment
      View v =  inflater.inflate(R.layout.fragment_feedback, container, false);
        db = new DBHelper(context);
        nameInput = (TextInputLayout) v.findViewById(R.id.nameInput);
        messageInput = (TextInputLayout) v.findViewById(R.id.messageInput);

        nameEditText = (EditText) v.findViewById(R.id.nameFeedbackEditText);
        messageEditText =(EditText) v.findViewById(R.id.messageFeedbackEditText);
        sendButton = (Button) v.findViewById(R.id.sendButton);
        sendButton.setOnClickListener(this);
        user = db.getLoginUser();

        return v;
    }



    public boolean validate(String name, String msg) {

        boolean valid = true;


        if(name.isEmpty())
        {
            nameInput.setError("Name cannot be empty.");
            valid = false;
        }
        else
            nameInput.setError(null);

        if(msg.isEmpty())
        {
            messageInput.setError("Message cannot be empty.");
            valid = false;
        }
        else
            messageInput.setError(null);

        return  valid;
    }


    @Override
    public void onClick(View v) {
        String name = nameEditText.getText().toString();
        String msg = messageEditText.getText().toString();
        if(validate(name, msg))
        {
            sendButton.setEnabled(false);

            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/html");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"OCCCommunityServiceHour@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "Feedback for OCC Community College Application");
            i.putExtra(Intent.EXTRA_TEXT, "Name : "+ name + "\nMessage : "+msg);
            try {
                startActivity(Intent.createChooser(i, "Send feedback..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(context, "There are an error in sending.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
