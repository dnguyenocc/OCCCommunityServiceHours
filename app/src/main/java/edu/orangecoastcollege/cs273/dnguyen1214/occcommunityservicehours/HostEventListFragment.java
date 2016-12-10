package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HostEventListFragment extends Fragment implements View.OnClickListener {

    private DBHelper db;
    private List<Event> hostEvents;

    private ListView hostEventsListView;
    private Button addEventButton;

    private EventListAdapter eventsListAdapter;

    private User user;

    public HostEventListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_host_event_list, container, false);

        db = new DBHelper(getContext());
        user = db.getLoginUser();

        hostEventsListView = (ListView) v.findViewById(R.id.hostEventListView);
        addEventButton = (Button) v.findViewById(R.id.addEventButton);
        addEventButton.setOnClickListener(this);

        hostEvents = db.getAllEventsByOwnerId(user.getmId());

        eventsListAdapter = new EventListAdapter(getContext(), R.layout.admin_event_list_item, hostEvents);
        hostEventsListView.setAdapter(eventsListAdapter);

        return v;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.profileImageView) {
            try {
                Fragment fragment = CreateEventFragment.class.newInstance();
                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.host_fragment, fragment).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (v.getId() == R.id.addEventButton) {
            try {
                Fragment fragment = CreateEventFragment.class.newInstance();
                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.host_fragment, fragment).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
