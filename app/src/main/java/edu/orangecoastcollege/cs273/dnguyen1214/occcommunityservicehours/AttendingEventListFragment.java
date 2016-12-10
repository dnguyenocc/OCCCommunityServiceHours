package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttendingEventListFragment extends ListFragment {

    private DBHelper db;
    private List<Participation> allParticipationList;
    private List<Event> presentEvents;

    private ListView presentEventsListView;

    private EventListAdapter eventsListAdapter;

    private User user;

    public AttendingEventListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_attending_event_list, container, false);

        db = new DBHelper(getContext());


        presentEventsListView = (ListView) v.findViewById(R.id.presentEventsListView);


        user = db.getLoginUser();
        allParticipationList = db.getAllParticipationsByUserId(user.getmId());


        presentEvents = new ArrayList<Event>();
        for (Participation participation : allParticipationList) {
            if (!(participation.getEvent().eventPassed())) presentEvents.add(participation.getEvent());
        }

        if (presentEvents.isEmpty())
            Toast.makeText(getContext(), "You are not attending any events at the moment.", Toast.LENGTH_LONG).show();
        eventsListAdapter = new EventListAdapter(getContext(), R.layout.event_list_item, presentEvents);
        presentEventsListView.setAdapter(eventsListAdapter);

        return v;
    }

}
