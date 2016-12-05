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
    private ImageView profileImageView, editNameImageView, editEmailImageView,
            editNumberImageView, editPasswordImageView;
    private Context mContext;

    private User loginUser;
    private DBHelper db;
    private Uri imageUri;

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

        return v;
    }

    private void hookUpWidgets(View v)
    {
        submitButton = (Button) v.findViewById(R.id.submitButton);
        serviceHoursTextView = (TextView) v.findViewById(R.id.serviceHoursTextView);
        userNameTextView = (TextView) v.findViewById(R.id.userNameTextView);
        lastNameEditText = (EditText) v.findViewById(R.id.lastNameEditText);
        firstNameEditText = (EditText) v.findViewById(R.id.firstNameEditText);
        emailEditText = (EditText) v.findViewById(R.id.emailEditText);
        numberEditText = (EditText) v.findViewById(R.id.numberEditText);
        passwordEditText = (EditText) v.findViewById(R.id.passwordEditText);
        profileImageView = (ImageView) v.findViewById(R.id.profileImageView);
        editNameImageView = (ImageView) v.findViewById(R.id.editNameImageView);
        editEmailImageView = (ImageView) v.findViewById(R.id.editEmailImageView);
        editNumberImageView = (ImageView) v.findViewById(R.id.editNumberImageView);
        editPasswordImageView = (ImageView) v.findViewById(R.id.editPasswordImageView);

        // Set up image onClickListeners
        profileImageView.setOnClickListener(this);
        editNameImageView.setOnClickListener(this);
        editEmailImageView.setOnClickListener(this);
        editNumberImageView.setOnClickListener(this);
        editPasswordImageView.setOnClickListener(this);
    }

    private void setUp (View v, User user) {
        /****** NEEDS to be fixed *********
        serviceHoursTextView.setText(R.string.service_hours + " "
                + );
         */
        profileImageView.setImageURI(user.getmImageUri());
        lastNameEditText.setText(user.getLastName());
        firstNameEditText.setText(user.getFirstName());
        emailEditText.setText(user.getmEmail());
        numberEditText.setText(user.getmPhoneNum());
        passwordEditText.setText(user.getmPassWord());
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
        }
    }
}
