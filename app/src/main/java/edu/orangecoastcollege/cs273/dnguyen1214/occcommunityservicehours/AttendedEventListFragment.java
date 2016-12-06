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
    private List<Event> allUserEvents;

    private ListView allEventsListView;

    private EventListAdapter eventsListAdapter;

    private User user;

    public AttendedEventListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_attended_event_list, container, false);
        db = new DBHelper(getContext());


        allEventsListView = (ListView) v.findViewById(R.id.allEventsListView);


        user = db.getLoginUser();
        allParticipationList = db.getAllParticipationsByUserId(user.getmId());


        allUserEvents = new ArrayList<Event>();
        for (Participation participation : allParticipationList) {
            if (participation.getEvent().eventPassed()) allUserEvents.add(participation.getEvent());
        }

        if (allUserEvents == null)
            Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
        eventsListAdapter = new EventListAdapter(getContext(), R.layout.event_list_item, allUserEvents);
        allEventsListView.setAdapter(eventsListAdapter);



        return v;
    }


    /**
     * This method will be called when an item in the list is selected.
     * Subclasses should override. Subclasses can call
     * getListView().getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param l        The ListView where the click happened
     * @param v        The view that was clicked within the ListView
     * @param position The position of the view in the list
     * @param id       The row id of the item that was clicked
     **/
    /* MUST FIX LATER, THIS IS WRONG
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Bundle bundle1 = new Bundle();
        Event selectedEvent = (Event) v.getTag();
        bundle1.putParcelable("SelectedEvent", selectedEvent);
        Intent intent = new Intent(getActivity(), EventDetailsActivity.class);
        intent.putExtras(bundle1);
        getActivity().startActivity(intent);
    }*/
}
