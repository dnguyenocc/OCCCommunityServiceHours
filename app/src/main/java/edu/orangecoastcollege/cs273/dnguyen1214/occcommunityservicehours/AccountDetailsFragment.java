package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountDetailsFragment extends Fragment implements View.OnClickListener{

    // Request codes
    private static final int REQUEST_CODE_PHOTO = 100;
    private static final int REQUEST_CODE_CAMERA = 101;
    private AlertDialog dialog = null;

    // Declare local variables
    private Button submitButton;
    private TextView serviceHoursTextView, userNameTextView,
            securityQuestionsTextView, descriptionTextView;
    private EditText lastNameEditText, firstNameEditText,
            emailEditText, numberEditText, passwordEditText;
    private ImageView profileImageView, editLastNameImageView, editFirstNameImageView,
            editEmailImageView, editNumberImageView, editPasswordImageView;

    // Local variables for Recovery Questions
    private TextView askQuestion1TextView;
    private TextView askQuestion2TextView;
    private TextInputLayout answer1InputText;
    private TextInputLayout answer2InputText;
    private EditText answerSecurity1EditText;
    private EditText answerSecurity2EditText;
    private Button submitAnswerButton;
    private Recovery recovery;
    private SessionManager sManager;
    private ProgressBar answerRecoveryProgressBar;
    //Context context;
    private  String emailR_Q;

    private User loginUser;
    private DBHelper db;
    private Uri imageUri;

    private boolean lNameValid = false, fNameValid = false,
            emailValid = false, numberValid = false, passwordValid = false;

    private String lName, fName, email, number, password,
            ogEmail, ogNumber;

    private boolean dialogFinished = false;


    public AccountDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        loginUser = db.getLoginUser();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account_details, container, false);

        // Hookup Widgets
        hookUpWidgets(v);

        imageUri = getUriToResource(getContext(), R.drawable.default_avatar);

        // Access the DataBase helper to get the current user
        db = new DBHelper(getContext());
        loginUser = db.getLoginUser();

        // Display the User's info
        setUp (v, loginUser);

        // Set up image onClickListeners
        profileImageView.setOnClickListener(this);
        editLastNameImageView.setOnClickListener(this);
        editFirstNameImageView.setOnClickListener(this);
        editEmailImageView.setOnClickListener(this);
        editNumberImageView.setOnClickListener(this);
        editPasswordImageView.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        securityQuestionsTextView.setOnClickListener(this);


        // Set up textWatchers for the edit text fields

        lastNameEditText.addTextChangedListener(lNameChangedListener);
        firstNameEditText.addTextChangedListener(fNameChangedListener);
        emailEditText.addTextChangedListener(emailChangedListener);
        numberEditText.addTextChangedListener(numberChangedListener);
        passwordEditText.addTextChangedListener(passwordChangedListener);

        return v;
    }

    private void hookUpWidgets(View v)
    {
        serviceHoursTextView = (TextView) v.findViewById(R.id.serviceHoursTextView);
        userNameTextView = (TextView) v.findViewById(R.id.userNameTextView);
        lastNameEditText = (EditText) v.findViewById(R.id.lastNameEditText);
        firstNameEditText = (EditText) v.findViewById(R.id.firstNameEditText);
        emailEditText = (EditText) v.findViewById(R.id.emailEditText);
        numberEditText = (EditText) v.findViewById(R.id.numberEditText);
        passwordEditText = (EditText) v.findViewById(R.id.passwordEditText);
        profileImageView = (ImageView) v.findViewById(R.id.profileImageView);
        editLastNameImageView = (ImageView) v.findViewById(R.id.editLastNameImageView);
        editFirstNameImageView = (ImageView) v.findViewById(R.id.editFirstNameImageView);
        editEmailImageView = (ImageView) v.findViewById(R.id.editEmailImageView);
        editNumberImageView = (ImageView) v.findViewById(R.id.editNumberImageView);
        editPasswordImageView = (ImageView) v.findViewById(R.id.editPasswordImageView);
        submitButton = (Button) v.findViewById(R.id.submitButton);
        securityQuestionsTextView = (TextView) v.findViewById(R.id.securityQuestionsTextView);
    }

    private void setUp (View v, User user) {
        String userHours = String.valueOf(user.getmHours());
        serviceHoursTextView.setText(getResources().getString(R.string.service_hours) + " " + userHours);
        profileImageView.setImageURI(user.getmImageUri());
        userNameTextView.setText(user.getmUserName());
        lastNameEditText.setText(user.getLastName());
        firstNameEditText.setText(user.getFirstName());
        emailEditText.setText(user.getmEmail());
        numberEditText.setText(user.getmPhoneNum());
        passwordEditText.setText(user.getmPassWord());

        ogEmail = user.getmEmail();
        ogNumber = user.getmPhoneNum();
    }

    private void selectProfileImage(View v) {
        // List of all the permissions to request from user
        ArrayList<String> permList = new ArrayList<>();

        // Check to see if permissions to camera has been granted
        int cameraPermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
        if (cameraPermission != PackageManager.PERMISSION_GRANTED)
            permList.add(Manifest.permission.CAMERA);

        // Check if permissions to camera is granted
        int readExternalStoragePermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (readExternalStoragePermission != PackageManager.PERMISSION_GRANTED)
            permList.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        // Check if permissions to writing external storage is granted
        int writeExternalStoragePermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (writeExternalStoragePermission != PackageManager.PERMISSION_GRANTED)
            permList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        // If the list has items (size > 0), request permissions from the user:
        if (permList.size() > 0)
        {
            // Convert ArrayList into an Array of Strings
            String[] perms = new String[permList.size()];
            // Request permission from user
            ActivityCompat.requestPermissions(getActivity(), permList.toArray(perms), REQUEST_CODE_PHOTO);
        }

        // If we have all 3 permissions, open ImageGallery
        if (cameraPermission == PackageManager.PERMISSION_GRANTED
                && readExternalStoragePermission == PackageManager.PERMISSION_GRANTED
                && writeExternalStoragePermission == PackageManager.PERMISSION_GRANTED)
        {
            // Start an intent to launch gallery and take pics
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, REQUEST_CODE_PHOTO);
        } else {
            Toast.makeText(getContext(),
                    "OCC Community Service Hours requires camera and external storage permission",
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Get uri to any resource type within an Android Studio project. Method is public static
     * to allow other classes to use it as a helper function.
     *
     * @param context The current context
     * @param resId   The resource identifier of the drawable
     * @return  Uri to resource by given id
     * @throws Resources.NotFoundException If the given resource id does not exist.
     */
    public static Uri getUriToResource(@NonNull Context context, @AnyRes int resId) throws Resources.NotFoundException {
        /**Return a Resources instance for your application's package. */
        Resources res = context.getResources();
        /** return URI */
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + res.getResourcePackageName(resId)
                + '/' + res.getResourceTypeName(resId)
                + '/' + res.getResourceEntryName(resId));
    }

    private void edit(EditText field)
    {
        field.setClickable(true);
        field.setCursorVisible(true);
        field.setFocusable(true);
        field.setFocusableInTouchMode(true);
    }

    private TextWatcher lNameChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            lName = lastNameEditText.getText().toString();

            if (lName.isEmpty() || lName.length() < 2)
            {
                lastNameEditText.setError("at least 2 characters");
                lNameValid = false;
                submitButton.setEnabled(false);
            } else {
                lastNameEditText.setError(null);
                lNameValid = true;
                loginUser.setLastName(lName);
                submitButton.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private TextWatcher fNameChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            fName = firstNameEditText.getText().toString();

            if (fName.isEmpty() || fName.length() < 2) {
                firstNameEditText.setError("at least 2 characters");
                fNameValid = false;
                submitButton.setEnabled(false);
            } else {
                firstNameEditText.setError(null);
                fNameValid = true;
                loginUser.setFirstName(fName);
                submitButton.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private TextWatcher emailChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            email = emailEditText.getText().toString();

            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEditText.setError("enter a valid email address");
                emailValid = false;
                submitButton.setEnabled(false);
            } else if (!(ogEmail.equals(email)) && (db.checkEmail(email))) {
                Log.i("ogEmail", ogEmail);
                Log.i("Email", email);
                emailEditText.setError("Email has been used by other user");
                emailValid = false;
                submitButton.setEnabled(false);
            } else {
                emailEditText.setError(null);
                emailValid = true;
                loginUser.setmEmail(email);
                submitButton.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private TextWatcher numberChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            number = numberEditText.getText().toString();

            if (number.isEmpty() || !android.util.Patterns.PHONE.matcher(number).matches()) {
                numberEditText.setError("enter a valid phone number");
                numberValid = false;
                submitButton.setEnabled(false);
            }
            else if ((ogNumber != number)
                    && (db.checkPhoneNumber(number))) {
                numberEditText.setError("Phone number has been used by other user");
                numberValid = false;
            } else {
                numberEditText.setError(null);
                numberValid = true;
                loginUser.setmPhoneNum(number);
                submitButton.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private TextWatcher passwordChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            password = passwordEditText.getText().toString();

            if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
                passwordEditText.setError("between 4 and 10 alphanumeric characters");
                passwordValid = false;
                submitButton.setEnabled(false);
            }
            else {
                passwordEditText.setError(null);
                passwordValid = true;
                loginUser.setmPassWord(password);
                submitButton.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    /**
     * Receive the result from a previous call to
     * {@link #startActivityForResult(Intent, int)}.  This follows the
     * related Activity API as described there in
     * {@link Activity#onActivityResult(int, int, Intent)}.
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && requestCode == REQUEST_CODE_PHOTO && resultCode == Activity.RESULT_OK)
        {
            // Set the imageURI to the data
            imageUri = data.getData();
            profileImageView.setImageURI(imageUri);
            loginUser.setmImageUri(imageUri);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profileImageView:
                selectProfileImage(v);
                break;
            case R.id.editLastNameImageView:
                edit(lastNameEditText);
                break;
            case R.id.editFirstNameImageView:
                edit(firstNameEditText);
                break;
            case R.id.editEmailImageView:
                edit(emailEditText);
                break;
            case R.id.editNumberImageView:
                edit(numberEditText);
                break;
            case R.id.editPasswordImageView:
                edit(passwordEditText);
                break;
            case R.id.securityQuestionsTextView:
                try {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                    View mView = getActivity().getLayoutInflater().inflate(R.layout.fragment_answer_question_security, null);

                    //sManager = new SessionManager();
                    submitAnswerButton = (Button) mView.findViewById(R.id.submitAnswerButton);
                    submitAnswerButton.setOnClickListener(this);
                    answerRecoveryProgressBar = (ProgressBar) mView.findViewById(R.id.answerRecoveryProgressBar);
                    answer1InputText = (TextInputLayout) mView.findViewById(R.id.answer1InputText);
                    answer2InputText = (TextInputLayout) mView.findViewById(R.id.answer2InputText);

                    answerSecurity1EditText = (EditText) mView.findViewById(R.id.answerSecurity1EditText);
                    answerSecurity2EditText = (EditText) mView.findViewById(R.id.answerSecurity2EditText);

                    askQuestion1TextView = (TextView) mView.findViewById(R.id.askQuestion1TextView);
                    askQuestion2TextView = (TextView) mView.findViewById(R.id.askQuestion2TextView);

                    descriptionTextView = (TextView) mView.findViewById(R.id.descriptionTextView);

                    //Get object pass by AskEmailRecoverFragment
                    recovery = db.getRecoveryByUserEmail(emailEditText.getText().toString());

                    askQuestion1TextView.setText(recovery.getQuestion1());
                    askQuestion2TextView.setText(recovery.getQuestion2());
                    answerSecurity1EditText.setHint(recovery.getAnswer1());
                    answerSecurity2EditText.setHint(recovery.getAnswer2());

                    submitAnswerButton.setText("Update");
                    descriptionTextView.setText("Update Answers for Security Questions");

                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    submitAnswerButton.setOnClickListener(new View.OnClickListener() {
                        /**
                         * Called when a view has been clicked.
                         *
                         * @param v The view that was clicked.
                         */
                        @Override
                        public void onClick(View v) {
                            answerRecoveryProgressBar.setVisibility(ProgressBar.VISIBLE);
                            submitAnswerButton.setEnabled(false);
                            String answer1 = answerSecurity1EditText.getText().toString();
                            String answer2 = answerSecurity2EditText.getText().toString();

                            if(validate(answer1, answer2)) {
                                recovery.setAnswer1(answerSecurity1EditText.getText().toString());
                                recovery.setAnswer2(answerSecurity2EditText.getText().toString());

                                Toast.makeText(getContext(),
                                        "You have successfully updated your answers",
                                        Toast.LENGTH_SHORT).show();

                                securityQuestionsTextView.setText("Answers for Security Questions were updated, update Again?");
                                submitButton.setEnabled(true);

                                dialogFinished = true;
                                dialog.dismiss();
                            }
                            else
                            {
                                answerRecoveryProgressBar.setVisibility(ProgressBar.INVISIBLE);
                                submitAnswerButton.setEnabled(true);
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.submitButton:
                db.updateRecoveryUser(recovery);
                User userLogin = db.getUser(recovery.getUserId());
                db.addLoginUser(userLogin.getmId(), userLogin.getmRole());
                db.updateUser(loginUser);
                Toast.makeText(getContext(), "Account updated", Toast.LENGTH_SHORT).show();
                submitButton.setEnabled(false);
                transitionFragment(new HomeFragment(),"Homepage");
        }
    }

    public boolean validate(String a1, String a2)
    {
        boolean valid = true;
        if(a1.isEmpty() ||  a2.isEmpty()) {
            valid = false;
            Toast.makeText(getContext(),"Answers can not be empty", Toast.LENGTH_LONG).show();
            answerSecurity1EditText.setText("");
            answerSecurity2EditText.setText("");
        } else if (a1.length() < 3) {
            Toast.makeText(getContext(),"Answer for question 1 must be at least 3 characters", Toast.LENGTH_LONG).show();
            valid = false;
            answerSecurity1EditText.setText("");
        } else if (a2.length() < 3) {
            Toast.makeText(getContext(),"Answer for question 2 must be at least 3 characters", Toast.LENGTH_LONG).show();
            answerSecurity2EditText.setText("");
        }
        else{
            answer1InputText.setError(null);
            answer2InputText.setError(null);
           // answerSecurity1EditText.setText("");
           // answerSecurity2EditText.setText("");
        }

        return valid;
    }

    public void transitionFragment(Fragment fragmentClass, String tag)
    {
        try {
            FragmentTransaction fragment = getFragmentManager().beginTransaction();
            // Insert the fragment by replacing any existing fragment
            fragment.replace(R.id.fragmentContent, fragmentClass,tag).commit();
            //fragment.add(R.id.fragmentContent, fragmentClass).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
