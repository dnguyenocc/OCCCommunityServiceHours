package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyng on 12/4/2016.
 */

public class ParticipationListAdapter extends ArrayAdapter<Participation> {
    private Context mContext;
    private List<Participation> mParticipationList = new ArrayList<>();
    private int mResourceId;



    /**
     * Creates a new <code>OfferingListAdapter</code> given a mContext, resource id and list of offerings.
     *
     * @param c The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param participations The list of offerings to display
     */
    public ParticipationListAdapter(Context c, int rId, List<Participation> participations) {
        super(c, rId, participations);
        mContext = c;
        mResourceId = rId;
        mParticipationList = participations;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the Offering selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        final Participation selectedParticipation = mParticipationList.get(pos);

        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        LinearLayout participationListLinearLayout =
                (LinearLayout) view.findViewById(R.id.requestListLinearLayout);

        TextView requestUserNameTextView =
                (TextView) view.findViewById(R.id.requestUserNameTextView);
        TextView requestEventNameTextView =
                (TextView) view.findViewById(R.id.requestEventNameTextView);
        TextView requestHoursTextView = (TextView) view.findViewById(R.id.requestHoursTextView);

        ImageView requestUserImageView = (ImageView) view.findViewById(R.id.requestUserImageView);

        participationListLinearLayout.setTag(selectedParticipation);

        User user = selectedParticipation.getUser();
        Event event = selectedParticipation.getEvent();
        requestUserNameTextView.setText(user.getLastName()+", "+user.getFirstName());
        requestEventNameTextView.setText(event.getName());
        requestHoursTextView.setText(String.valueOf(selectedParticipation.getServiceHours()));
        requestUserImageView.setImageURI(user.getmImageUri());

        return view;
    }
}
