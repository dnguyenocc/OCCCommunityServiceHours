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

public class HomeButtonAdapter extends BaseAdapter {
    private Context mContext;
    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.button2,
            R.drawable.button3, R.drawable.button4,
            R.drawable.button5, R.drawable.button6,
            R.drawable.button7, R.drawable.button8
    };
    private Integer[] mNavIds = {
            R.id.nav_feedback,
            R.id.nav_all_events, R.id.nav_attending_events,
            R.id.nav_attended_events, R.id.nav_profile,
            R.id.nav_point, R.id.nav_exist
    };
    private Integer[] mStringIds ={
            R.string.feedback,R.string.all_event_home,
            R.string.attending_event_home, R.string.attended_event_home,
            R.string.my_account, R.string.point,
            R.string.log_out
    };

    public HomeButtonAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
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
