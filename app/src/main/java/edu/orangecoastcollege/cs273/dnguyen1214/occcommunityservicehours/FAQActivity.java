package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class FAQActivity extends AppCompatActivity {


    private TextView answerTextView;
    private EditText questionEditText;
    private ListView faqAnswersListView;
    private TextInputLayout questionInputLayout;
    //Preference to the SensorManager (manager all the sensors on device)
    private SensorManager senserManager;
    private List<FAQAnswer> ansswerlist;
    private  FAQAnswerAdapter answerListAdapter;
    private DBHelper db;
    private FAQAnswer answer;
    private String key;
    //r
    private Sensor accelerotometor;
    //Preferences to the shake detector
    private ShakeDetector shakeDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        db = new DBHelper(this);


        // TASK 1: SET THE REFERENCES TO THE LAYOUT ELEMENTS
        answerTextView = (TextView) findViewById(R.id.answerTextView);
        questionEditText = (EditText) findViewById(R.id.questionEditText);
        //questionEditText.addTextChangedListener(questionEditTextTextWatcher);
        questionInputLayout = (TextInputLayout) findViewById(R.id.questionInputLayout);
        faqAnswersListView = (ListView) findViewById(R.id.faqAnswersListView);
        // TASK 2: CREATE A NEW MAGIC ANSWER OBJECT
        db.addFAQAnswer(new FAQAnswer("ptk","What is Phi Theta Kappa?\nPhi Theta Kappa Honor Society, also ΦΘΚ or sometimes PTK, is the international honor society of two-year colleges and academic programs, particularly community colleges and junior colleges."));
        db.addFAQAnswer(new FAQAnswer("occ","What is Orange Coast College? \nOrange Coast College is a community college in Orange County, California, United States. It was founded in 1947, with its first classes opening in the fall of 1948."));
        db.addFAQAnswer(new FAQAnswer("hourpoint","What is Hour Point? \nYour hour point will be maintain in database, and you can use this hour point to apply for many majors"));
        db.addFAQAnswer(new FAQAnswer("occcommunityservicehours","What is Orange Coast College Community Service Hours Application?\nThis application simply browse through our populated list of events and find the the event that’s right for you. Once you click on attending, you’ll be able to send a request to the admin of the group to validate the amount of hours you’ve worked and have it stored on your profile to showcase your commitment towards helping your community.\n"));
        db.addFAQAnswer(new FAQAnswer("participation","What is Participation?\nwho attended event for volunteer or help out their club"));
        db.addFAQAnswer(new FAQAnswer("create","What is Create Event?\nonly admin can access this function which create an event, and the member of club could register for volunteer"));
        db.addFAQAnswer(new FAQAnswer("feedback","What is Feedback?\nthis is where we want to hear from you about your experience when you use our application, or any error that cause the application's failure to function "));
        db.addFAQAnswer(new FAQAnswer("all","What is All Event?\nThis is where all events will be show in one list view, you can click on event in the list to view its detail "));
        db.addFAQAnswer(new FAQAnswer("upcoming","What is Upcoming Event?\nonly Admin can access this function which will show all upcoming events in one list view, you can click on event in the list to view its detail "));
        db.addFAQAnswer(new FAQAnswer("past","What is Past Event?\nonly Admin can access this function which will show all past events in one list view, you can click on event in the list to view its detail "));
        //db.addFAQAnswer(new FAQAnswer(null,"Empty \nthere is empty string. Please enter the key word"));

        ansswerlist = db.getAllAnswer();

        answerListAdapter = new FAQAnswerAdapter(this,R.layout.faq_answer_item,ansswerlist);
        faqAnswersListView.setAdapter(answerListAdapter);


            // TASK 3: REGISTER THE SENSOR MANAGER AND SETUP THE SHAKE DETECTION
            senserManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            accelerotometor = senserManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            shakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
                @Override
                public void onShake() {
                        //Geta random answe, put it in the answerTextView
                        //TODO get answer detail
                    key = questionEditText.getText().toString().toLowerCase();
                    viewShakeAnswer();

                }

            });



    }



    private void viewShakeAnswer()
    {
            if(validate(key)){
                answer = db.getAnswer(key);
                Intent detailsIntent = new Intent(this, FAQAnswerDetailActivity.class);
                detailsIntent.putExtra("selectedAnswer", answer);
                startActivity(detailsIntent);
            }

    }


    public void viewFAQAnswerDetail(View v)
    {
        if(v instanceof FrameLayout) {
            FrameLayout selectedLayout = (FrameLayout) v;
            FAQAnswer selecttedFAQAnswer = (FAQAnswer) selectedLayout.getTag();

            Intent detailsIntent = new Intent(this, FAQAnswerDetailActivity.class);
            detailsIntent.putExtra("selectedAnswer",selecttedFAQAnswer);
            startActivity(detailsIntent);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        //Start the accelerometer
        senserManager.registerListener(shakeDetector, accelerotometor, SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    protected void onPause() {
        super.onPause();
        senserManager.unregisterListener(shakeDetector);

    }

    private boolean validate(String keyword) {

        boolean valid = true;


        if(keyword.isEmpty())
        {
            questionInputLayout.setError("Username cannot be empty.");
            valid = false;
        }
        else
            questionInputLayout.setError(null);

        if(!db.checkFAQAnswer(keyword))
        {
            questionInputLayout.setError("There is no key like this");
            valid = false;
        }
        else
            questionInputLayout.setError(null);


        return  valid;
    }

}
