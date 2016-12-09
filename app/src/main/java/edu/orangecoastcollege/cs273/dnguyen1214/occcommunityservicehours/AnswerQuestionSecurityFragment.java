package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AnswerQuestionSecurityFragment extends Fragment implements View.OnClickListener{

    private TextView askQuestion1TextView;
    private TextView askQuestion2TextView;
    private TextInputLayout answer1InputText;
    private TextInputLayout answer2InputText;
    private EditText answerSecurity1EditText;
    private EditText answerSecurity2EditText;
    private Button submitAnswerButton;
    private DBHelper db;
    private Recovery recovery;
    Context context;
    public AnswerQuestionSecurityFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_answer_question_security, container, false);
        context = this.getContext();
        db = new DBHelper(context);

        submitAnswerButton = (Button) v.findViewById(R.id.submitAnswerButton);
        submitAnswerButton.setOnClickListener(this);


        answer1InputText = (TextInputLayout) v.findViewById(R.id.answer1InputText);
        answer2InputText = (TextInputLayout) v.findViewById(R.id.answer2InputText);

        answerSecurity1EditText = (EditText) v.findViewById(R.id.answerSecurity1EditText);
        answerSecurity2EditText = (EditText) v.findViewById(R.id.answerSecurity2EditText);

        askQuestion1TextView = (TextView) v.findViewById(R.id.askQuestion1TextView);
        askQuestion2TextView = (TextView) v.findViewById(R.id.askQuestion2TextView);
        //Get object pass by AskEmailRecoverFragment
        recovery = (Recovery) getArguments().getSerializable(
                "Recovery");

        askQuestion1TextView.setText(recovery.getQuestion1());
        askQuestion2TextView.setText(recovery.getQuestion2());


        return v;
    }


    @Override
    public void onClick(View v) {


        String answer1 = answerSecurity1EditText.getText().toString();
        String answer2 = answerSecurity2EditText.getText().toString();

        if(validate(answer1, answer2)) {

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.recoveryContent, new AnswerQuestionSecurityFragment())
                    .commit();
        }

    }


    public boolean validate(String a1, String a2)
    {
        boolean valid = true;
        if(a1.isEmpty() ||  a2.isEmpty()) {
            valid = false;
            Toast.makeText(context,"Answers can not be empty", Toast.LENGTH_LONG).show();
            answerSecurity1EditText.setText("");
            answerSecurity2EditText.setText("");
        } else if (!a1.equals(recovery.getAnswer1()) || !a2.equals(recovery.getAnswer2()) ) {
            Toast.makeText(context,"Answers are not correct", Toast.LENGTH_LONG).show();
            valid = false;
            answerSecurity1EditText.setText("");
            answerSecurity2EditText.setText("");
        }
        else{

            answer1InputText.setError(null);
            answer2InputText.setError(null);
            answerSecurity1EditText.setText("");
            answerSecurity2EditText.setText("");
        }

        return valid;
    }
}
