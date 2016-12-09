package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AskEmailRecoveryFragment extends Fragment implements View.OnClickListener {

    private TextInputLayout answerEmailSecurityInputText;
    private EditText emailSecurityEditText;
    private Button submitButton;
    private DBHelper db;
    private Recovery recovery;
    private String email;
    private SessionManager sManager;
    Context context;
    public AskEmailRecoveryFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ask_email_recovery, container, false);
        context = this.getContext();
        db = new DBHelper(context);
        db.addRecoveryUser(new Recovery(1,1,"sadsadsad","dsfdsfds","a","b",0));
        db.addRecoveryUser(new Recovery(2,2,"sadsadsad","dsfdsfds","a","b",0));
        db.addRecoveryUser(new Recovery(3,3,"sadsadsad","dsfdsfds","a","b",0));
        answerEmailSecurityInputText = (TextInputLayout) v.findViewById(R.id.answerEmailSecurityInputText);
        emailSecurityEditText = (EditText) v.findViewById(R.id.emailSecurityEditText);
        submitButton = (Button) v.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        sManager = new SessionManager();


        return v;
    }


    @Override
    public void onClick(View v) {

        email = emailSecurityEditText.getText().toString();

        if(validate(email)) {
            sManager.setEmailPreferences(context,"status",email);

            AnswerQuestionSecurityFragment fragment2 = new AnswerQuestionSecurityFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.recoveryContent, fragment2);
            fragmentTransaction.commit();


            }

    }





    public boolean validate(String email)
    {
        boolean valid = true;
        if(email.isEmpty()) {
            answerEmailSecurityInputText.setError("Can not be empty");
            valid = false;
            emailSecurityEditText.setText("");
        } else if (!db.checkEmail(email)) {
            answerEmailSecurityInputText.setError("Email is not correct");
            valid = false;
            emailSecurityEditText.setText("");
        }
        else{
            answerEmailSecurityInputText.setError(null);
            emailSecurityEditText.setText("");
        }
        return valid;
    }
}
