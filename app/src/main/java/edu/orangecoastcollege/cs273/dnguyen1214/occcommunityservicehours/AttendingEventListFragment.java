package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttendingEventListFragment extends ListFragment {

    private DBHelper db;
    private List<Participation> allParticipationList;
    private List<Event> pastEvents;

    private ListView allEventsListView;

    private EventListAdapter eventsListAdapter;

    private User user;

    public AttendingEventListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attending_event_list, container, false);
    }

}
