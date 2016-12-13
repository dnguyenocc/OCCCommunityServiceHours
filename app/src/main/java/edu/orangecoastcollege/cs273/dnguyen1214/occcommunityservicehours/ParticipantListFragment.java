package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;


import android.app.AlertDialog;
import android.app.Dialog;
//import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ParticipantListFragment extends DialogFragment {
    private DBHelper db;
    private Context mContext;

    public static ParticipantListFragment newInstance(int eventId) {
        ParticipantListFragment participantListFragment = new ParticipantListFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("eventId", eventId);
        participantListFragment.setArguments(args);

        return participantListFragment;
    }

    public ParticipantListFragment() {
        // Required empty public constructor


    }
    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        mContext = getContext();
        db = new DBHelper(mContext);
        int eventId = getArguments().getInt("eventId");
        ArrayList<Participation> allParticipants = db.getAllParticipationsByEventId(eventId);
        ParticipantListAdapter participantListAdapter = new ParticipantListAdapter(mContext,R.layout.participant_list_item,allParticipants);

        final AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.partcipant_list);
        if (allParticipants.isEmpty())
            builder.setMessage(R.string.participant_empty);
        /*else
            builder.setPositiveButton(R.string.send_sms, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    ((EventDetailsActivity)getActivity()).doPositiveClick();

                }
            });
            */
        builder.setAdapter(participantListAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {

                    }
                }
        );


        return builder.create(); // return the AlertDialog
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }
    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_participant_list, container, false);
    }
*/
}
