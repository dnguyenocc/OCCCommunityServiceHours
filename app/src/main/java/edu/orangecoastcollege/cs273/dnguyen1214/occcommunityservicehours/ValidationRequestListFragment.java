package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ValidationRequestListFragment extends Fragment {
    private DBHelper db;
    private List<Participation> allrequestParticipation;

    private ListView participationsListView;
    private TextView numberRequestTextView;

    private ParticipationListAdapter participationListAdapter;

    Context context;
    public ValidationRequestListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();

        allrequestParticipation =db.getRequestParticipations();
        numberRequestTextView.setText(String.valueOf(allrequestParticipation.size()));
        participationListAdapter = new ParticipationListAdapter(context, R.layout.request_list_item,allrequestParticipation);
        participationsListView.setAdapter(participationListAdapter);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_validation_request_list, container, false);
        context  = this.getContext();
        db = new DBHelper(context);
        allrequestParticipation =db.getRequestParticipations();
        numberRequestTextView = (TextView) view.findViewById(R.id.numberRequestTextView);

        numberRequestTextView.setText(String.valueOf(allrequestParticipation.size()));

        participationsListView = (ListView) view.findViewById(R.id.allValidationRequestListView);


        return view;
    }

}
