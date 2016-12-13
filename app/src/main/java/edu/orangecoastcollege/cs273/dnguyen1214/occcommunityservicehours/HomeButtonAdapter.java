package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

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
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);

            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);

        } else {
            imageView = (ImageView) convertView;
        }

        //imageView.setImageResource(mThumbIds[position]);
        imageView.setBackgroundResource(mThumbIds[position]);
        imageView.setTag(mNavIds[position]);

        return imageView;
    }


}
