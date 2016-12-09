package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;


public class RegisterActivity extends AppCompatActivity {
    private List<String> allQuestionList;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText userNameEditText;


    private EditText answer1EditText;
    private EditText answer2EditText;

    private TextInputLayout firstNameInput;
    private TextInputLayout lastNameInput;
    private TextInputLayout usernameInput;
    private TextInputLayout emailInput;
    private TextInputLayout passwordInput;

    private TextInputLayout answer1Input;
    private TextInputLayout answer2Input;

    private Spinner question1Spinner;
    private Spinner question2Spinner;


    private DBHelper db;
    private Uri imageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DBHelper(this);

        question1Spinner = (Spinner) findViewById(R.id.question1Spinner);
        ArrayAdapter<String> question1Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,getAllQuestions(1));
        question1Spinner.setAdapter(question1Adapter);


        question2Spinner = (Spinner) findViewById(R.id.question2Spinner);
        ArrayAdapter<String> question2Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, getAllQuestions(2));
        question2Spinner.setAdapter(question2Adapter);




        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText =(EditText) findViewById(R.id.lastNameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        userNameEditText = (EditText) findViewById(R.id.userNameEditText);


        answer1EditText = (EditText) findViewById(R.id.answer1EditText);
        answer2EditText = (EditText) findViewById(R.id.answer2EditText);

        answer1Input = (TextInputLayout) findViewById(R.id.answer1Input);
        answer2Input = (TextInputLayout) findViewById(R.id.answer2Input);


        firstNameInput = (TextInputLayout) findViewById(R.id.firstNameInput);
        lastNameInput = (TextInputLayout) findViewById(R.id.lastNameInput);
        usernameInput = (TextInputLayout) findViewById(R.id.usernameInput);
        emailInput = (TextInputLayout) findViewById(R.id.emailInput);
        passwordInput = (TextInputLayout) findViewById(R.id.passwordInput);


        imageURI = getUriToResource(this,R.drawable.default_avatar);
    }


    private String[] getAllQuestions(int typeNumber)
    {
        String [] questions;
        if(typeNumber == 1)
        {
            allQuestionList = db.getAllQuestions(typeNumber);
            questions = new String[allQuestionList.size()+1];
            questions[0] = "[Select Question 1]";
        }
        else
        {
            allQuestionList = db.getAllQuestions(typeNumber);
            questions = new String[allQuestionList.size()+1];
            questions[0] = "[Select Question 2]";
        }

        for (int i = 1;  i< questions.length; i++)
        {
            questions[i]= allQuestionList.get(i-1);
        }
        return questions;
    }




    public void backToLogin(View view)
    {
        setResult(RESULT_OK, null);
        finish();
    }
    public void newRegister(View view)
    {

        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this,R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();


        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String userName = userNameEditText.getText().toString();
        String pass = passwordEditText.getText().toString();
        String question1 = question1Spinner.getSelectedItem().toString();
        String question2 = question2Spinner.getSelectedItem().toString();
        String answer1 = answer1EditText.getText().toString();
        String answer2 = answer2EditText.getText().toString();


        if(validate(firstName,lastName,email,userName,pass,question1,question2,answer1,answer2))
        {

            User user = new User(firstName,lastName,userName,email," ",pass,0.0,2,imageURI);// set 2 for role because this is normal user
            db.addUser(user);
            Recovery recovery = new Recovery(db.getUser(userName).getmId(),question1,question2,answer1,answer2,0);
            db.addRecoveryUser(recovery);


            firstNameEditText.setText("");
            lastNameEditText.setText("");
            emailEditText.setText("");
            userNameEditText.setText("");
            passwordEditText.setText("");
            answer1EditText.setText("");
            answer2EditText.setText("");

            setResult(RESULT_OK, null);
            finish();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }
        progressDialog.dismiss();
    }



    public boolean validate(String first,String last, String email,String userName, String pass,
    String q1, String q2, String a1, String a2 ) {
        boolean valid = true;

        if (q1.equals("[Select Question 1]")) {
            Toast.makeText(this,"Please pick a question in Question 1", Toast.LENGTH_LONG).show();
            valid = false;
        }
        if (q2.equals("[Select Question 2]")) {
            Toast.makeText(this,"Please pick a question in Question 2", Toast.LENGTH_LONG).show();
            valid = false;
        }

        if (a1.isEmpty() || a1.length() < 1) {
            answer1Input.setError("at least 5 characters");
            valid = false;
            answer1EditText.setText("");
        }else {
            answer1Input.setError(null);
        }

        if (a2.isEmpty() || a2.length() < 1) {
            answer2Input.setError("at least 5 characters");
            valid = false;
            answer2EditText.setText("");
        }else {
            answer2Input.setError(null);
        }

        if (userName.isEmpty() || userName.length() < 5) {
            usernameInput.setError("at least 5 characters");
            valid = false;
            userNameEditText.setText("");
        }
        else if (db.checkUserName(userName)) {
            usernameInput.setError("Username has been used by other user");
            valid = false;
            userNameEditText.setText("");
        } else {
            usernameInput.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("enter a valid email address");
            valid = false;
            emailEditText.setText("");
        } else if (db.checkEmail(email)) {
            emailInput.setError("Email has been used by other user");
            valid = false;
            emailEditText.setText("");
        }
        else{
            emailInput.setError(null);
        }

        if (first.isEmpty() || first.length() < 2) {
            firstNameInput.setError("at least 2 characters");
            valid = false;
            firstNameEditText.setText("");
        } else {
            firstNameInput.setError(null);
        }

        if (last.isEmpty() || last.length() < 2) {
            lastNameInput.setError("at least 2 characters");
            valid = false;
            lastNameEditText.setText("");
        } else {
            lastNameInput.setError(null);
        }


        if (pass.isEmpty() || pass.length() < 4 || pass.length() > 10) {
            passwordInput.setError("between 4 and 10 alphanumeric characters");
            valid = false;
            passwordEditText.setText("");
        } else {
            passwordInput.setError(null);
        }

        return valid;
    }


    public static Uri getUriToResource(@NonNull Context context, @AnyRes int resId) throws Resources.NotFoundException{
        Resources res = context.getResources();

        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" +res.getResourcePackageName(resId)
                +'/'+ res.getResourceTypeName(resId)
                + '/' +res.getResourceEntryName(resId)
        );
    }


}
