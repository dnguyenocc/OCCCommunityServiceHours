package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class UpcommingEventsListActivityFragment extends Fragment {

    public UpcommingEventsListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upcomming_events_list, container, false);
    }
}
