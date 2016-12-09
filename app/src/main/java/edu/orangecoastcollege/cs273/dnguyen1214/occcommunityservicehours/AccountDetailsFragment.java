package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
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

    // Declare local variables
    private Button submitButton;
    private TextView serviceHoursTextView, userNameTextView;
    private EditText lastNameEditText, firstNameEditText,
            emailEditText, numberEditText, passwordEditText;
    private ImageView profileImageView, editLastNameImageView, editFirstNameImageView,
            editEmailImageView, editNumberImageView, editPasswordImageView;
    private Context mContext;

    private User loginUser;
    private DBHelper db;
    private Uri imageUri;

    private boolean lNameValid = false, fNameValid = false,
            emailValid = false, numberValid = false, passwordValid = false;

    private String lName, fName, email, number, password,
            ogEmail, ogNumber;


    public AccountDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account_details, container, false);

        // Hookup Widgets
        hookUpWidgets(v);

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

    }

    private void setUp (View v, User user) {
        /****** NEEDS to be fixed *********
        serviceHoursTextView.setText(R.string.service_hours + " "
                + );
         */
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
            /*}  WAIT FOR ALEX TO IMPLEMENT checkNumber
            else if ((ogNumber != number)
                    && (db.checkNumber(number))) {
                numberEditText.setError("Phone number has been used by other user");
                numberValid = false;
                */
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
            case R.id.submitButton:
                db.updateUser(loginUser);
                Toast.makeText(getContext(), "Account updated", Toast.LENGTH_SHORT).show();
                submitButton.setEnabled(false);
        }
    }



}
