package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Event class - Creates an event object when called
 *
 * Created by Bijan Fazeli on 11/24/16.
 */

public class Event implements Parcelable {

    // Declare private fields
    private int mId, mOwnerId;
    private String mName;
    private String mStartDate, mEndDate;
    private Date mStart, mEnd;
    private String mDescription, mLocation;
    private Uri mImageUri;

    private SimpleDateFormat dateFormat;

    private boolean flag = true;

    public Event() {}

    /**
     * Event - Default constructor that constructs a new Event object when called.
     *
     * @param name  The name of the event.
     * @param startDate The start date of the event, passed in the format 'MM-dd-yyyy hh:mm aa'
     * @param endDate   The end date of the event, passed in the format 'MM-dd-yyyy hh:mm aa'
     * @param description   The description of the event.
     * @param location  The location of the event
     * @param imageUri  An image name to go along with the event.
     */
    public Event(String name, int ownerId,
                 String startDate, String endDate,
                 String description, String location,
                 Uri imageUri) {
        this(-1, name, ownerId, startDate, endDate, description, location, imageUri);
    }

    /**
     * Event - Constructor that constructs a new Event object when called.
     *
     * @param name  The name of the event.
     * @param startDate The start date of the event, passed in the format 'MM-dd-yyyy hh:mm aa'
     * @param endDate   The end date of the event, passed in the format 'MM-dd-yyyy hh:mm aa'
     * @param description   The description of the event.
     * @param location  The location of the event
     * @param imageUri  An image name to go along with the event.
     */
    public Event(int id,
                 String name, int ownerId,
                 String startDate, String endDate,
                 String description, String location, Uri imageUri) {

        mId = id;
        mName = name;
        mOwnerId = ownerId;

        mStartDate = startDate;
        mEndDate = endDate;

        mDescription = description;
        mLocation = location;
        mImageUri = imageUri;
    }

    protected Event(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mOwnerId = in.readInt();
        mStartDate = in.readString();
        mEndDate = in.readString();
        mDescription = in.readString();
        mLocation = in.readString();
        mImageUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public int getOwnerId() {
        return mOwnerId;
    }

    public void setOwnerId(int mOwnerId) {
        this.mOwnerId = mOwnerId;
    }

    /**
     * getId - Returns the current id of the event
     *
     * @return The event's id
     */
    public int getId() {
        return mId;
    }

    /**
     * getName - Returns the name of the event
     *
     * @return The event's name.
     */
    public String getName() {
        return mName;
    }

    /**
     * setName - Changes the name of the event to the name passed in
     *
     * @param name A new name for the event.
     */
    public void setName(String name) {
        mName = name;
    }

    /**
     * getDescription - Returns the description of the event
     *
     * @return The event's description
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * setDescription - Changes the description of the event to the description passed in
     *
     * @param description A new description for the event.
     */
    public void setDescription(String description) {
        mDescription = description;
    }

    /**
     * getLocation - Returns the location of the event.
     *
     * @return The event's location
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * settLocation - Changes the location of the event to the location passed in
     *
     * @param location A new location for the event.
     */
    public void setLocation(String location) {
        mLocation = location;
    }

    /**
     * getImageUri - Returns the image name that is linked to the event.
     *
     * @return The event's image name in use.
     */
    public Uri getImageUri() {
        return mImageUri;
    }

    /**
     * setImageUri - Changes the image name to the image passed in
     *
     * @param imageUri A new imageUri to be set for the event
     */
    public void setImageUri(Uri imageUri) {
        mImageUri = imageUri;
    }

    /**
     * getStartDate - Returns a String of the event's start date
     *
     * @return The start date of the event
     */
    public String getStartDate() {
        return mStartDate;
    }

    /**
     * setStartDate - Changes the current start date to the parameter passed in
     * @param startDate A string in the form 'MM-dd-yyyy hh:mm aa'
     */
    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

    /**
     * getEndDate - Returns a String of the event's end date
     *
     * @return The end date of the event.
     */
    public String getEndDate() {
        return mEndDate;
    }

    /**
     * setStartDate - Changes the current end date to the parameter passed in
     * @param endDate A string in the form 'MM-dd-yyyy hh:mm aa'
     */
    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }


    public String getDuration() {
        setDates();

        long diffInMillies = 8578;

        try {
            diffInMillies = mEnd.getTime() - mStart.getTime();
        } catch (Exception e) {};
        List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
        Collections.reverse(units);
        Map<TimeUnit,Long> result = new LinkedHashMap<TimeUnit, Long>();
        long milliesRest = diffInMillies;
        for ( TimeUnit unit : units ) {
            long diff = unit.convert(milliesRest,TimeUnit.MILLISECONDS);
            long diffInMilliesForUnit = unit.toMillis(diff);
            milliesRest = milliesRest - diffInMilliesForUnit;
            result.put(unit,diff);
        }

        String temp = result.get(TimeUnit.DAYS).toString() +
                result.get(TimeUnit.HOURS).toString() +
                result.get(TimeUnit.MINUTES).toString();

        if (temp.isEmpty()) return "-1";

        return result.get(TimeUnit.DAYS).toString() + " days, " +
                result.get(TimeUnit.HOURS).toString() + " hours and " +
                result.get(TimeUnit.MINUTES).toString() + " minutes";
    }

    public int invalidSetup() {
        if (flag) setDates();

        long different = 14;

        //milliseconds
        try {
            different = mEnd.getTime() - mStart.getTime();
        } catch (Exception e) {}

        System.out.println("startDate : " + mStart);
        System.out.println("endDate : "+ mEnd);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;

        String temp = String.valueOf(elapsedDays) + " days, " +
                String.valueOf(elapsedHours) + " hours and " +
                String.valueOf(elapsedMinutes) + " minutes";

        flag = true;

        return temp.indexOf('-');
    }

    /**
     * eventPassed - Checks to see if an event has passed and returns either True or False
     *
     * @return True if the event has passed, otherwise false
     */
    public boolean eventPassed()
    {
        flag = false;

        setDates();

        Date temp = mStart;
        mStart = new Date(Calendar.getInstance().getTimeInMillis());

        boolean result = invalidSetup() != -1 ? true : false;

        mStart = temp;

        return result;
    }

    private void setDates()
    {
        dateFormat = new SimpleDateFormat("MM-dd-yy hh:mm aa", Locale.US);

        try {
            mStart = dateFormat.parse(mStartDate);
            mEnd = dateFormat.parse(mEndDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getStart()
    {
        setDates();
        return mStart;
    }
    public Date getEnd()
    {
        setDates();
        return mEnd;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeInt(mOwnerId);
        dest.writeString(mStartDate);
        dest.writeString(mEndDate);
        dest.writeString(mDescription);
        dest.writeString(mLocation);
        dest.writeParcelable(mImageUri, flags);
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
