package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.telephony.PhoneNumberUtils;
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
 * Created by duyng on 12/11/2016.
 */

public class ParticipantListAdapter extends ArrayAdapter<Participation> {
    private Context mContext;
    private List<Participation> mParticipationList = new ArrayList<>();
    private int mResourceId;
/**
 * Creates a new <code>ParticipationListAdapter</code> given a mContext, resource id and list of offerings.
 *
 * @param c The mContext for which the adapter is being used (typically an activity)
 * @param rId The resource id (typically the layout file name)
 * @param participations The list of participations to display
 */
public ParticipantListAdapter(Context c, int rId, List<Participation> participations) {
        super(c, rId, participations);
        mContext = c;
        mResourceId = rId;
        mParticipationList = participations;
        }

/**
 * Gets the view associated with the layout.
 * @param pos The position of the Participation selected in the list.
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


        TextView participantNameTextView =
        (TextView) view.findViewById(R.id.participantNameTextView);
        TextView participantRoleTextView =
        (TextView) view.findViewById(R.id.participantRoleTextView);
        TextView participantPhoneTextView = (TextView) view.findViewById(R.id.participantPhoneTextView);

        ImageView participantImageView = (ImageView) view.findViewById(R.id.participantImageView);
            User user = selectedParticipation.getUser();
                participantNameTextView.setText(user.getLastName()+", "+user.getFirstName());
                participantRoleTextView.setText((user.getmRole()==1?"host":"member"));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                participantPhoneTextView.setText(PhoneNumberUtils.formatNumber(user.getmPhoneNum(),"US"));
            }
            else
                participantPhoneTextView.setText(user.getmPhoneNum());
            participantImageView.setImageURI(user.getmImageUri());

        return view;
        }
}
