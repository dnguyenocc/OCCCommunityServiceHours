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
 * Created by duyng on 12/2/2016.
 */

public class EventListAdapter extends ArrayAdapter<Event> {

    private Context mContext;
    private List<Event> mEventsList = new ArrayList<>();
    private int mResourceId;



    /**
     * Creates a new <code>EventListAdapter</code> given a mContext, resource id and list of events.
     *
     * @param c The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param events The list of events to display
     */
    public EventListAdapter(Context c, int rId, List<Event> events) {
        super(c, rId, events);
        mContext = c;
        mResourceId = rId;
        mEventsList = events;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the Event selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        final Event selectedEvent = mEventsList.get(pos);

        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        LinearLayout eventListLinearLayout =
                (LinearLayout) view.findViewById(R.id.eventListLinearLayout);

        TextView eventListNameTextView =
                (TextView) view.findViewById(R.id.eventListNameTextView);
        TextView eventListTimeTextView =
                (TextView) view.findViewById(R.id.eventListTimeTextView);
        TextView eventListLocationTextView = (TextView) view.findViewById(R.id.eventListLocationTextView);

        ImageView eventListImageView = (ImageView) view.findViewById(R.id.eventListImageView);
        eventListLinearLayout.setTag(selectedEvent);

        eventListNameTextView.setText(selectedEvent.getName());
        eventListTimeTextView.setText(selectedEvent.getStartDate() + " - " + selectedEvent.getEndDate());
        eventListLocationTextView.setText(selectedEvent.getLocation());
        eventListImageView.setImageURI(selectedEvent.getImageUri());

        return view;
    }
}
