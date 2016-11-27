package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by bijanfazeli on 11/24/16.
 */

public class Event {
    // Declare private fields
    private int mId;
    private String mName, mDescription, mLocation, mStartTime, mEndTime, mDate, mDuration;

    public Event() {
        this(0, "", "", "", "", "", "");
    }

    public Event(int id, String name, String description,
                 String location, String startTime, String endTime, String date) {
        mId = id;
        mDate = date;
        mName = name;
        mDescription = description;
        mLocation = location;
        mStartTime = startTime;
        mEndTime = endTime;

        setDuration();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
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

    public String getStartTime() {
        return mStartTime;
    }

    public void setStartTime(String startTime) {
        mStartTime = startTime;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public void setEndTime(String endTime) {
        mEndTime = endTime;
    }

    public String getDuration() {
        return mDuration;
    }

    private void setDuration() {
        mDuration = calculateDuration();
    }

    /**
     * calculateDuration - Returns the duration of the event to the calling object.
     *
     *
     *      ex. If, startTime = 2:30 pm and endTime = 3:40 pm.
     *          Then, 1:10 will be returned.
     *
     * @return duration
     */
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

    // eventPassed
    public boolean eventPassed() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy hh:mm aa");
        String dateTime = dateFormat.format(c.getTime());
        //System.out.println(dateTime);


        return true;

    }

    // eventUpcoming
}
