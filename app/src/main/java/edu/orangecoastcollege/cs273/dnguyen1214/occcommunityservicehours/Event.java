package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.net.Uri;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bijanfazeli on 11/24/16.
 */

public class Event {

    // Declare private fields
    private int mId;
    private String mName;
    private Date mStartDate, mEndDate;
    private String mDescription, mLocation;
    private Uri mImageUri;

    private SimpleDateFormat dateFormat;

    public Event(String name,
                 String startDate, String endDate,
                 String description, String location,
                 Uri imageUri) {
        this(-1, name, startDate, endDate, description, location, imageUri);
    }

    public Event(int id,
                 String name,
                 String startDate, String endDate,
                 String description, String location, Uri imageUri) {

        dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm aa", Locale.US);
        mId = id;
        mName = name;

        try {
            mStartDate = dateFormat.parse(startDate);
            mEndDate = dateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mDescription = description;
        mLocation = location;
        mImageUri = imageUri;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public Uri getImageUri() {
        return mImageUri;
    }

    public void setImageUri(Uri imageUri) {
        mImageUri = imageUri;
    }

    public String getStartDate() {
        return mStartDate.toString();
    }

    public void setStartDate(String startDate) {
        dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm aa", Locale.US);

        try {
            mStartDate = dateFormat.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getEndDate() {
        return mEndDate.toString();
    }

    public void setEndDate(String endDate) {
        dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm aa", Locale.US);

        try {
            mEndDate = dateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public boolean eventPassed()
    {
        Date now = new Date(Calendar.getInstance().getTimeInMillis());
        return now.after(mEndDate);
    }

    /*
     * calculateDuration - Returns the duration of the event to the calling object.
     *
     *
     *      ex. If, startTime = 2:30 pm and endTime = 3:40 pm.
     *          Then, 1:10 will be returned.
     *
     * @return duration

    private String calculateDuration() {
        // Declare a local variable, 'duration' that will store the amount of time the event takes.
        String duration;

        // Declare and initialize 'startPeriod and endPeriod' to their respective periods.
        String startPeriod = mStartTime.substring(mStartTime.length() - 2);
        String endPeriod = mEndTime.substring(mEndTime.length() - 2);

        // Store the minutes for startTime and endTime into separate variables
        int startMin = Integer.parseInt(mStartTime.substring(mStartTime.indexOf(':'), mStartTime.lastIndexOf(' ')));
        int endMin = Integer.parseInt(mEndTime.substring(mEndTime.indexOf(':'), mEndTime.lastIndexOf(' ')));

        // Find out what periods the start and end time fall under and execute appropriate condition.
        // The body of the conditions will find the hour diff between the end time and start time and store it to duration
        if (startPeriod.equals("pm") && endPeriod.equals("am"))
            if (mEndTime.substring(0, 2).equals("12"))
                duration = String.format(Locale.US, "%02d",
                        Integer.parseInt(mEndTime.substring(0, mEndTime.indexOf(':'))) -
                        Integer.parseInt(mStartTime.substring(0, mStartTime.indexOf(':'))));
            else
                duration = String.format(Locale.US, "%02d",
                        Integer.parseInt(mEndTime.substring(0, mEndTime.indexOf(':'))) -
                        (12 + Integer.parseInt(mStartTime.substring(0, mStartTime.indexOf(':')))));
        else
            duration =  String.format(Locale.US, "%02d",
                    Integer.parseInt(mEndTime.substring(0, mEndTime.indexOf(':'))) -
                    Integer.parseInt(mStartTime.substring(0, mStartTime.indexOf(':'))));

        // Add a colon and the remaining minutes to duration
        duration += ":" + String.format(Locale.US, "%02d",
                ((endMin >= startMin) ? (endMin - startMin) : (60 - (startMin - endMin))));

        return duration;
    }
    */


     /* eventPassed - Returns a boolean value depending on whether the event has passed.
     *
     *
     *      ex. If, the event is set for '09-10-2018 09:30 am'
     *              and the current date and time is '09-10-2018 08:00 am'
     *          Then, the value false will be returned.
     *
     * @return boolean value

    private boolean eventPassed() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm aa", Locale.US);
        String dateTime = dateFormat.format(c.getTime());

        //System.out.println(dateTime);f
        String [] values = dateTime.split(" ");
        String [] date = values[0].split("-");
        String [] time = values[1].split(":");
        String period = values[2];

        String [] startTime = mStartTime.split(" ");
        String eventPeriod = startTime[1];

        String [] eventTime = startTime[0].split(":");

        if (mYear >= Integer.parseInt(date[2]))
        {
            if (mMonth >= Integer.parseInt(date[0]))
            {
                if (mDay >= Integer.parseInt(date[1]))
                {
                    if (eventPeriod.equals(period)) {
                        return (Integer.parseInt(eventTime[0]) < Integer.parseInt(time[0])) ||
                                !(Integer.parseInt(eventTime[0]) >= Integer.parseInt(time[0]));
                    }
                    return (eventPeriod.equals("am") && period.equals("pm"));
                }
                return true;
            }
            return true;
        }
        return true;
    }
    */
}
