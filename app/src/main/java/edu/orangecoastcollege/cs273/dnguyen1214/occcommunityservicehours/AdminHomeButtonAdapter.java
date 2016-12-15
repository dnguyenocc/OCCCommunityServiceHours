package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by duyng on 12/12/2016.
 */

/**
 * The Adapter for hooking up admin's home page
 *
 */
public class AdminHomeButtonAdapter extends BaseAdapter {
    private Context mContext;
    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.button1, R.drawable.button2,
            R.drawable.button3, R.drawable.button4,
            R.drawable.button5, R.drawable.button6,
            R.drawable.button7, R.drawable.button8
    };
    private Integer[] mNavIds = {
            R.id.nav_create_events, R.id.nav_feedback,
            R.id.nav_all_events, R.id.nav_upcoming_events,
            R.id.nav_past_events, R.id.nav_profile,
            R.id.nav_point, R.id.nav_exist
    };
    private Integer[] mStringIds ={
            R.string.create_event_home,R.string.feedback,
            R.string.all_event_home, R.string.upcoming_event_home,
            R.string.past_event_home, R.string.my_account,
            R.string.point, R.string.log_out
    };

    /**
     * Sets the context for the admin home button adapter
     *
     * @param c A context to set for the object
     */
    public AdminHomeButtonAdapter(Context c) {
        mContext = c;
    }

    /**
     * Returns the count of the thumbnail for the current obj.
     *
     * @return A count of the total thumbnails
     */
    public int getCount() {
        return mThumbIds.length;
    }

    /**
     * Gets the item at the position on the view.
     *
     * @param position
     * @return null
     */
    public Object getItem(int position) {
        return null;
    }


    /**
     * Gets the id of the item at the slected position on the view.
     *
     * @param position
     * @return 0
     */
    public long getItemId(int position) {
        return 0;
    }

    /**
     *
     * Creates a new ImageView for each item referenced by the Adapter
     *
     * @param position Takes in a position of location of an item.
     * @param convertView The view to convert to.
     * @param parent The parent view group that the view is nested in.
     *
     * @return  The view to return.
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout view;
        if (convertView == null) {
            view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.home_button_item, parent, false);

            ImageView imageView = (ImageView) view.findViewById(R.id.homeButtonImageView);
            TextView textView = (TextView) view.findViewById(R.id.homeButtonTextView);
            //view.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
            imageView.setImageResource(mThumbIds[position]);
            view.setTag(mNavIds[position]);
            textView.setText(mStringIds[position]);
            textView.setTextColor(Color.WHITE);

        }
        else
        {
            view = (LinearLayout) convertView;
        }


        return view;
    }


}
