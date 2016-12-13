package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Alex Ho on 11/30/2016.
 */

public class SessionManager {

    /**
     * setPreferences:  set login user
     * @param context context activity
     * @param key the string key
     * @param value the string value (admin or user)
     */
    public void setPreferences(Context context, String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences("OCC", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);

        editor.commit();

    }

    /**
     * setEmailPreferences: set email fro recovery activity
     * @param context context activity
     * @param key the string key
     * @param value the string value
     */
    public void setEmailPreferences(Context context, String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences("Email", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);

        editor.commit();

    }

    /**
     * getPreferences: get the preference for user login
     * @param context the contexy of activity
     * @param key the string key
     * @return  the string value
     */
    public String getPreferences(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences("OCC", Context.MODE_PRIVATE);
        String position = prefs.getString(key, "");
        return position;
    }

    /**
     * getEmailPreferences: get email for recovery activity
     * @param context the context of activity
     * @param key the string key
     * @return the string value
     */
    public String getEmailPreferences(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences("Email", Context.MODE_PRIVATE);
        String position = prefs.getString(key, "");
        return position;
    }
}
