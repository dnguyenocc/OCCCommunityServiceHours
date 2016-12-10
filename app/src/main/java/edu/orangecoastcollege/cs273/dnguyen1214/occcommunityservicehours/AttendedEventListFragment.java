package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttendedEventListFragment extends Fragment {

    private DBHelper db;
    private List<Participation> allParticipationList;
    private List<Event> pastEvents;

    private ListView pastEventsListView;

    private EventListAdapter eventsListAdapter;

    private User user;

    public AttendedEventListFragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_attended_event_list, container, false);

        db = new DBHelper(getContext());


        pastEventsListView = (ListView) v.findViewById(R.id.pastEventsListView);


        user = db.getLoginUser();
        allParticipationList = db.getAllParticipationsByUserId(user.getmId());


        pastEvents = new ArrayList<Event>();
        for (Participation participation : allParticipationList) {
            if (participation.getEvent().eventPassed()) pastEvents.add(participation.getEvent());
        }

        if (pastEvents.isEmpty())
            Toast.makeText(getContext(), "You have no past events", Toast.LENGTH_LONG).show();
        eventsListAdapter = new EventListAdapter(getContext(), R.layout.event_list_item, pastEvents);
        pastEventsListView.setAdapter(eventsListAdapter);

        return v;
    }

    /**
     * Attach to list view once the view hierarchy has been created.
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
