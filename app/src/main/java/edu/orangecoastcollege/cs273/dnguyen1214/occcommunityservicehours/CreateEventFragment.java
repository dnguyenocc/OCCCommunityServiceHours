package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;


import android.*;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;

import static edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours.R.id.firstNameEditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventFragment extends Fragment implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private static final int REQUEST_CODE_PHOTO = 100;
    private Uri imageUri;
    private ImageView eventImageView;
    private EditText eventNameEditText, eventLocationEditText, eventDetailsEditText;
    private TextView startDateTextView, endDateTextView;
    private Button createEventButton;

    private Calendar c;

    private DatePickerDialog datePickerDialogStart, datePickerDialogEnd;

    private int day, month, year, hour, minute,
            dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;

    private boolean isPm = false, inStartDate = true,
            nameValid = false, locationValid = false;

    private String name, location;

    private int past = 0, current = 0;

    public CreateEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_event, container, false);

        imageUri = getUriToResource(getContext(), R.drawable.default_image);

        c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        eventNameEditText = (EditText) v.findViewById(R.id.eventNameEditText);
        eventLocationEditText = (EditText) v.findViewById(R.id.eventLocationEditText);
        eventDetailsEditText = (EditText) v.findViewById(R.id.eventDetailsEditText);
        startDateTextView = (TextView) v.findViewById(R.id.startDateTextView);
        endDateTextView = (TextView) v.findViewById(R.id.endDateTextView);
        createEventButton = (Button) v.findViewById(R.id.createEventButton);

        eventImageView = (ImageView) v.findViewById(R.id.eventImageView);

        // Set up TextChanged listeners
        eventNameEditText.addTextChangedListener(nameChangedListener);
        eventLocationEditText.addTextChangedListener(locationChangedListener);

        // Set up onClickListeners
        eventImageView.setOnClickListener(this);
        startDateTextView.setOnClickListener(this);
        endDateTextView.setOnClickListener(this);

        return v;
    }

    TextWatcher nameChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            name = eventNameEditText.getText().toString();

            if ((name.isEmpty() || name.length() < 2) && !locationValid)
            {
                eventNameEditText.setError("Need at least 2 characters");
                createEventButton.setEnabled(false);
                nameValid = false;
            } else {
                createEventButton.setEnabled(true);
                nameValid = true;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher locationChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            location = eventLocationEditText.getText().toString();

            if ((name.isEmpty() || name.length() < 15) && !nameValid)
            {
                eventLocationEditText.setError("Need at least 15 characters");
                createEventButton.setEnabled(false);
                locationValid = false;
            } else {
                createEventButton.setEnabled(true);
                locationValid = true;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void selectEventImage(View v) {
        // List of all the permissions to request from user
        ArrayList<String> permList = new ArrayList<>();

        // Check to see if permissions to camera has been granted
        int cameraPermission = ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA);
        if (cameraPermission != PackageManager.PERMISSION_GRANTED)
            permList.add(android.Manifest.permission.CAMERA);

        // Check if permissions to camera is granted
        int readExternalStoragePermission = ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (readExternalStoragePermission != PackageManager.PERMISSION_GRANTED)
            permList.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);

        // Check if permissions to writing external storage is granted
        int writeExternalStoragePermission = ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (writeExternalStoragePermission != PackageManager.PERMISSION_GRANTED)
            permList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

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
            eventImageView.setImageURI(imageUri);
        }
    }



    /**
     * @param view       the picker associated with the dialog
     * @param year       the selected year
     * @param month      the selected month (0-11 for compatibility with
     *                   {@link Calendar#MONTH})
     * @param dayOfMonth th selected day of the month (1-31, depending on
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        yearFinal = year;
        monthFinal = month + 1;
        dayFinal = dayOfMonth;
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), this, hour, minute, false);
        timePickerDialog.show();
    }

    /**
     * Called when the user is done setting a new time and the dialog has
     * closed.
     *
     * @param view      the view associated with this listener
     * @param hourOfDay the hour that was set
     * @param minute    the minute that was set
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        hourFinal = hourOfDay;
        minuteFinal = minute;
        isPm = (hourOfDay >= 12);

        if (inStartDate) {
            startDateTextView.setText(
                    monthFinal + "-" + dayFinal + "-" + yearFinal + " " +
                            String.format(
                                    Locale.US,
                                    "%02d:%02d %s",
                                    (hourFinal == 12 || hourFinal == 0) ? 12 : hourFinal % 12,
                                    minuteFinal,
                                    isPm ? "pm" : "am"));
            inStartDate = false;
        } else {
            endDateTextView.setText(
                    monthFinal + "-" + dayFinal + "-" + yearFinal + " " +
                            String.format(
                                    Locale.US,
                                    "%02d:%02d %s",
                                    (hourFinal == 12 || hourFinal == 0) ? 12 : hourFinal % 12,
                                    minuteFinal,
                                    isPm ? "pm" : "am"));
        }
    }



    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.eventImageView:
                selectEventImage(v);
                break;
            case R.id.startDateTextView:
                inStartDate = true;
                DatePickerDialog datePickerDialogStart =
                        new DatePickerDialog(getContext(),
                                CreateEventFragment.this, year, month, day);
                datePickerDialogStart.show();
                break;
            case R.id.endDateTextView:
                DatePickerDialog datePickerDialogEnd = new DatePickerDialog(getContext(),
                        CreateEventFragment.this, year, month, day);
                datePickerDialogEnd.show();

            case R.id.createEventButton:
                if (!(startDateTextView.getText().toString().isEmpty()) || !(endDateTextView.getText().toString().isEmpty()))
                {
                    DBHelper db = new DBHelper(getContext());
                    User user = db.getLoginUser();
                    Event event = new Event(name, user.getmId(),
                            startDateTextView.getText().toString(),
                            endDateTextView.getText().toString(),
                            eventDetailsEditText.getText().toString(),
                            eventLocationEditText.getText().toString(),
                            imageUri);
                    if (!(event.validDates()))
                        Toast.makeText(getActivity(),
                                "THE START TIME CAN NOT BE AFTER THE END TIME\n" +
                                        "Please change the fields and submit again.",
                                Toast.LENGTH_SHORT).show();
                    else {
                        db.addEvent(event);
                        Toast.makeText(getContext(), "Your event has been added",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(), HostEventActivity.class));
                    }
                }
                break;
        }
    }

}
