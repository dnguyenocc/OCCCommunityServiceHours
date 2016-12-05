package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountDetailsFragment extends Fragment implements View.OnClickListener{

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
        db = new DBHelper(mContext);
        loginUser = db.getLoginUser();

        // Display the User's info
        displayInfo(v, loginUser);

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
    }

    private void displayInfo(View v, User user) {
        /****** NEEDS to be fixed *********
        serviceHoursTextView.setText(R.string.service_hours + " "
                + );
         */
        lastNameEditText.setText(user.getLastName());
        firstNameEditText.setText(user.getFirstName());
        emailEditText.setText(user.getmEmail());
        numberEditText.setText(user.getmPhoneNum());
        passwordEditText.setText(user.getmPassWord());

    }

    @Override
    public void onClick(View view) {

    }
}
