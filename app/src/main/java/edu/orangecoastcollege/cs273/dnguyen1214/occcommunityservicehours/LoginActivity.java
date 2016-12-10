package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
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
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
    private ProgressBar loginProgressBar;
    private Button signInButton;
    private LinearLayout loginMainLayout;
    //FLip Animation
    private View mFrontLayout;
    private View mBackLayout;
    private String userN;
    private String pass;
    SessionManager sManager;
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Flip Animation Implement
        findViews();
        loadAnimations();
        changeCameraDistance();




        // Set up the login form.
        signInButton = (Button) findViewById(R.id.signInButton);
        userName = (EditText) findViewById(R.id.userNameLoginEditText);
        passWord = (EditText) findViewById(R.id.passwordLoginEditText);
        imageURI = getUriToResource(this,R.drawable.default_avatar);
        loginProgressBar = (ProgressBar) findViewById(R.id.loginProgressBar);
        usernameInputLayout =(TextInputLayout) findViewById(R.id.usernameInputLayout);
        passwordInputLayout = (TextInputLayout) findViewById(R.id.passwordInputLayout);
        loginMainLayout = (LinearLayout) findViewById(R.id.loginMainLayout);


        sManager = new SessionManager();

//           this.deleteDatabase(DBHelper.DATABASE_NAME);
          db = new DBHelper(this);
//           db.importUsersFromCSV("User.csv");
//           db.importEventsFromCSV("Events.csv");
//           db.importParticipationsFromCSV("participations.csv");

    }


    @Override
    public void onResume(){
        super.onResume();

        if (!mIsBackVisible) {
            mSetRightOut.setTarget(mFrontLayout);
            mSetLeftIn.setTarget(mBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = true;
        } else {
            mSetRightOut.setTarget(mBackLayout);
            mSetLeftIn.setTarget(mFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = false;
        }

    }

    public void forgotPassword(View view)
    {
        startActivity(new Intent(LoginActivity.this,RecoveryActivity.class));
    }

    public void signUp(View view)
    {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }

    public void signIn(View view)
    {
        hideSoftKeyboard();
        signInButton.setEnabled(false);
        loginProgressBar.setVisibility(ProgressBar.VISIBLE);
        loginMainLayout.setVisibility(LinearLayout.GONE);
        hideSoftKeyboard();
        userN = userName.getText().toString();
        pass = passWord.getText().toString();
         String statusLogin = "";
        //check if username and password in database
        if (validate(userN, pass)) {
            User userLogin = db.getUser(userN);
            db.addLoginUser(userLogin.getmId(), userLogin.getmRole());

            if(userLogin.getmRole() == 1) {
                statusLogin = "admin";
                startActivity(new Intent(LoginActivity.this,AdminActivity.class));
            }
            else if(userLogin.getmRole() == 2) {
                statusLogin = "user";
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            sManager.setPreferences(LoginActivity.this,"status",statusLogin);
            String status =  sManager.getPreferences(LoginActivity.this,"status");
            Log.d("status",status);
        }
        else
        {
            loginProgressBar.setVisibility(ProgressBar.INVISIBLE);
            signInButton.setEnabled(true);
            loginMainLayout.setVisibility(LinearLayout.VISIBLE);
        }


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




    public static final Uri getUriToResource(@NonNull Context context, @AnyRes int resId) throws Resources.NotFoundException{
        Resources res = context.getResources();

        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" +res.getResourcePackageName(resId)
                +'/'+ res.getResourceTypeName(resId)
                + '/' +res.getResourceEntryName(resId)
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }


    //TODO functions for flip animation
    private void changeCameraDistance() {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mFrontLayout.setCameraDistance(scale);
        mBackLayout.setCameraDistance(scale);
    }

    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.in_animation);
    }

    private void findViews() {
        mFrontLayout = findViewById(R.id.frontLayout);
        mBackLayout = findViewById(R.id.backLayout);
    }

    public void flipImage(View view) {
        if (!mIsBackVisible) {
            mSetRightOut.setTarget(mFrontLayout);
            mSetLeftIn.setTarget(mBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = true;
        } else {
            mSetRightOut.setTarget(mBackLayout);
            mSetLeftIn.setTarget(mFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = false;
        }
    }





    /**
     * Hides the soft keyboard
     */
    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

//    /**
//     * Shows the soft keyboard
//     */
//    public void showSoftKeyboard(View view) {
//        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//        view.requestFocus();
//        inputMethodManager.showSoftInput(view, 0);
//    }
}