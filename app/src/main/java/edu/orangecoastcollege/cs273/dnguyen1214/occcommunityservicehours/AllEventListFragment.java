package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;


import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * AllEventListFragment
 *
 * A simple {@link Fragment} subclass.
 */
public class AllEventListFragment extends Fragment {
    // Declaration of private fields
    private DBHelper db;
    private List<Event> allEventsList;
    private ArrayList<Event> filteredEventsList;

    private EditText eventNameEditText;
    private Spinner eventTimeSpinner;
    private ListView eventsListView;

    private EventListAdapter eventsListAdapter;
    Context context;

    private SensorManager sensorManager;

    // Reference to the accelerometer;
    private Sensor accelerometer;
    private ShakeDetector shakeDetector;
    private Button resetButton;

    public AllEventListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(shakeDetector);
    }

    /**
     * Configures the AllEventListFragment when it's View is created.
     *
     * @param inflater The layout inflater
     * @param container The view group container in which the fragment resides
     * @param savedInstanceState Any saved state to restore in this fragment
     * @return The view v
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        context  = this.getContext();
        View view =
                inflater.inflate(R.layout.fragment_all_event_list, container, false);
        // Inflate the layout for this fragment

        db = new DBHelper(context);
        allEventsList =db.getAllEvents();

      /*
        Uri imageURI = LoginActivity.getUriToResource(context,R.drawable.occpirate);
        allEventsList = new ArrayList<>();
        allEventsList.add(new Event(1,"Book drive","12-14-16 07:45 am","12-14-16 10:45 am",
                "Need volunteers to facilitate with reviewing and handling of books.","OCC Library",imageURI));
        allEventsList.add(new Event(2,"Beach Clean up","12-14-16 07:45 am","12-14-16 10:45 am",
                "Need volunteers to clean beach.","Newport beach",imageURI));
        allEventsList.add(new Event(3,"Blood Donation","12-01-16 07:45 am","12-01-16 10:45 am",
                "Need volunteers to donate blood.","OCC Watson Hall",imageURI));
        */
        filteredEventsList = new ArrayList<>();

        for (Event event : allEventsList)
        {
            if (!event.eventPassed())
                filteredEventsList.add(event);
        }
        eventNameEditText = (EditText) view.findViewById(R.id.eventNameEditText);
        eventNameEditText.addTextChangedListener(eventNameTextWatcher);

        eventTimeSpinner = (Spinner)view.findViewById(R.id.eventTimeSpinner);
        ArrayAdapter<String> eventTimeSpinnerAdapter =
                new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,
                        new String[]{"All","Upcoming","Past"});
        eventTimeSpinner.setAdapter(eventTimeSpinnerAdapter);
        eventTimeSpinner.setOnItemSelectedListener(eventTimeSpinnerListener);

        eventsListView = (ListView) view.findViewById(R.id.allEventsListView);
        eventsListAdapter = new EventListAdapter(context, R.layout.event_list_item,allEventsList);
        eventsListView.setAdapter(eventsListAdapter);
        resetButton = (Button) view.findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                eventsListAdapter.clear();
                for (Event event: allEventsList)
                    eventsListAdapter.add(event);
                eventTimeSpinner.setSelection(0);
                eventNameEditText.setText("");
            }
        });


        // TASK 3: REGISTER THE SENSOR MANAGER AND SETUP THE SHAKE DETECTION
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
 
        shakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                if (filteredEventsList.size()>0) {
                    Random rng = new Random();
                    int randomPosition = rng.nextInt(filteredEventsList.size());
                    Event selectedEvent = filteredEventsList.get(randomPosition);
                    Intent detailsIntent = new Intent(getContext(), EventDetailsActivity.class);
                    detailsIntent.putExtra("SelectedEvent", selectedEvent);
                    startActivity(detailsIntent);
                }
                else
                {
                    Toast.makeText(getContext(),"There is no new event recently!",Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    /**
     * Resumes the fragment if the user already has the app running and is logged in.
     * Avoids creating the view again.
     *
     */
    @Override
    public void onResume() {
        super.onResume();
        allEventsList =db.getAllEvents();
        eventsListAdapter.notifyDataSetChanged();
        sensorManager.registerListener(shakeDetector,accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    /**
     * A TextWatcher to check for an input from the user
     * and execute the appropriate action depending on what the
     * user is entering in the input field.
     *
     */
    public TextWatcher eventNameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String input = s.toString().toLowerCase();
            if (input.equals(""))
            {
                // Repopulate the list adapter with all offerings
                eventsListAdapter.clear();
                for (Event event:allEventsList)
                    eventsListAdapter.add(event);
            }
            else
            {
                eventsListAdapter.clear();
                for (Event event:allEventsList)
                {
                    if (event.getName().toLowerCase().contains(input))
                        eventsListAdapter.add(event);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    /**
     * Setup for an item listener
     * Executes the approp action depending on what the user clicked on the adapter.
     *
     */
    public AdapterView.OnItemSelectedListener eventTimeSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedEventTime = String.valueOf(parent.getItemAtPosition(position));
            if (selectedEventTime.equals("All"))
            {
                eventsListAdapter.clear();
                for (Event event: allEventsList)
                    eventsListAdapter.add(event);
            }
            else if (selectedEventTime.equals("Upcomming"))
            {
                eventsListAdapter.clear();
                for (Event event : allEventsList)
                {
                    if (!event.eventPassed())
                        eventsListAdapter.add(event);
                }
            }
            else if (selectedEventTime.equals("Past"))
            {
                eventsListAdapter.clear();
                for (Event event : allEventsList)
                {
                    if (event.eventPassed())
                        eventsListAdapter.add(event);
                }
            }
        }

        /**
         * A method that sets the current selection to 0 if nothing was selected.
         *
         * @param parent
         */
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            parent.setSelection(0);
        }
    };





}
