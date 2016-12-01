package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class RegisterActivity extends AppCompatActivity {


    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText userNameEditText;

    private TextInputLayout firstNameInput;
    private TextInputLayout lastNameInput;
    private TextInputLayout usernameInput;
    private TextInputLayout emailInput;
    private TextInputLayout passwordInput;

    private DBHelper db;
    private Uri imageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText =(EditText) findViewById(R.id.lastNameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        userNameEditText = (EditText) findViewById(R.id.userNameEditText);

        firstNameInput = (TextInputLayout) findViewById(R.id.firstNameInput);
        lastNameInput = (TextInputLayout) findViewById(R.id.lastNameInput);
        usernameInput = (TextInputLayout) findViewById(R.id.usernameInput);
        emailInput = (TextInputLayout) findViewById(R.id.emailInput);
        passwordInput = (TextInputLayout) findViewById(R.id.passwordInput);


        //this.deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        imageURI = getUriToResource(this,R.drawable.default_avatar);
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


        String fname = firstNameEditText.getText().toString();
        String lname = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String userName = userNameEditText.getText().toString();
        String pass = passwordEditText.getText().toString();

        if(validate(fname,lname,email,userName,pass))
        {

            User user = new User(fname,lname,userName,email," ",pass,2,imageURI);// set 2 for role because this is normal user
//            petListAdapter.add(pet);
            db.addUser(user);


            firstNameEditText.setText("");
            lastNameEditText.setText("");
            emailEditText.setText("");
            userNameEditText.setText("");
            passwordEditText.setText("");

            setResult(RESULT_OK, null);
            finish();
            //startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }
        progressDialog.dismiss();
    }



    public boolean validate(String first,String last, String email,String userName, String pass) {
        boolean valid = true;

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
