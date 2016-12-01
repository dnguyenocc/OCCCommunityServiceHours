package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Alex Ho on 11/30/2016.
 */

public class SessionManager {

    //method to save status
    public void setPreferences(Context context, String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences("OCC", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();

    }


    public String getPreferences(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences("OCC", Context.MODE_PRIVATE);
        String position = prefs.getString(key, "");
        return position;
    }
}
