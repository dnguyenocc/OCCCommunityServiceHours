package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;


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
import android.widget.EditText;
import android.widget.Toast;



/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText userName;
    private EditText passWord;
    private DBHelper db;
    private Uri imageURI;
    private TextInputLayout usernameInputLayout;
    private TextInputLayout passwordInputLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        userName = (EditText) findViewById(R.id.userNameLoginEditText);
        passWord = (EditText) findViewById(R.id.passwordLoginEditText);
        imageURI = getUriToResource(this,R.drawable.default_avatar);

        usernameInputLayout =(TextInputLayout) findViewById(R.id.usernameInputLayout);
        passwordInputLayout = (TextInputLayout) findViewById(R.id.passwordInputLayout);

        this.deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
        db.addUser(new User("alex","ho","admin"," "," ","12345",imageURI));




    }


    public void signIn(View view)
    {
        String user = userName.getText().toString();
        String pass = passWord.getText().toString();

        //check if username and password in database
        if (validate(user, pass))
            startActivity(new Intent(LoginActivity.this, MainActivity.class));


    }

    public boolean validate(String userN, String passW) {

        boolean valid = true;

        if(userN.isEmpty())
        {
            usernameInputLayout.setError("Username cannot be empty.");
            valid = false;
        }
        else
            usernameInputLayout.setError(null);

        if(passW.isEmpty())
        {
            passwordInputLayout.setError("Password cannot be empty.");
            valid = false;
        }
        else
            passwordInputLayout.setError(null);

        if (!db.checkLogin(userN,passW)) {
            userName.setText("");
            passWord.setText("");
            Toast.makeText(this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
            valid = false;

        } else {
            userName.setText("");
            passWord.setText("");
        }

        return  valid;
    }


    public void signUp(View view)
    {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
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