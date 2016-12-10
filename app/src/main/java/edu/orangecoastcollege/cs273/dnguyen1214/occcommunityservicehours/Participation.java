package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The <code>Participation</code> class represents a single participation that user involved in an event,
 * including its id(partipation id), status (REGISTERED, VALIDATED), request_validation: (YES/NO),
 * service hours, and responsibilities
 * the <code>User</code> and the <code>Event</code> involved
 *
 *
 * @author Duy Nguyen
 * Created by duyng on 11/27/2016.
 */

public class Participation implements Parcelable {
    final static int REGISTERED = 1;
    final static int VALIDATED = 2;
    final static int VALIDATION_DENY = 3;

    private int mId;
    private int mStatusCode;
    private boolean mValidationRequested;
    private float mServiceHours;
    private String mResponsibilities;
    private User mUser;
    private Event mEvent;

    /**
     * Parameter constructor that constructs a new Participation object when called.
     * @param id id number of a particular participation
     * @param status Status code: one of these value: REGISTERED, VALIDATED, VALIDATION_DENY
     * @param validationRequested whether this participation is on a request of validation
     * @param serviceHours number of volunteered hours contributed
     * @param responsibilities briefly responsibilities, how did a user involved in a event
     * @param user a user instance
     * @param event a event instance
     */
    public Participation(int id, int status, boolean validationRequested, float serviceHours, String responsibilities,
                         User user, Event event)
    {
        mId = id;
        mStatusCode = status;
        mValidationRequested = validationRequested;
        mServiceHours = serviceHours;
        mResponsibilities = responsibilities;
        mUser = user;
        mEvent = event;
    }

    /**
     * Participation Constructor can be used when the used first register for the event
     * @param id id number of a specific participation
     * @param status Status code: one of these value: REGISTERED, VALIDATED, VALIDATION_DENY
     * @param user  a user instance
     * @param event a event instance
     */

    public Participation(int id, int status, User user, Event event)
    {
        mId = id;
        mStatusCode = status;
        mValidationRequested = false;
        mServiceHours = 0;
        mUser = user;
        mEvent = event;
    }

    /**
     *  Parameter constructor that constructs a new Participation object with offset id when called.
     * @param status Status code: one of these value: REGISTERED, VALIDATED, VALIDATION_DENY
     * @param validationRequested whether this participation is on a request of validation
     * @param serviceHours number of volunteered hours contributed
     * @param responsibilities
     * @param user
     * @param event
     */
    public Participation(int status, boolean validationRequested, float serviceHours, String responsibilities,
                         User user, Event event)
    {
        mId = -1;
        mStatusCode = status;
        mValidationRequested = validationRequested;
        mServiceHours = serviceHours;
        mResponsibilities = responsibilities;
        mUser = user;
        mEvent = event;

    }

    /**
     * Parcelable Participation
     * @param in
     */
    protected Participation(Parcel in) {
        mId = in.readInt();
        mStatusCode = in.readInt();
        mValidationRequested = in.readByte() != 0;
        mServiceHours = in.readFloat();
        mResponsibilities = in.readString();
        mUser = in.readParcelable(User.class.getClassLoader());
        mEvent = in.readParcelable(Event.class.getClassLoader());
    }

    public static final Creator<Participation> CREATOR = new Creator<Participation>() {
        @Override
        public Participation createFromParcel(Parcel in) {
            return new Participation(in);
        }

        @Override
        public Participation[] newArray(int size) {
            return new Participation[size];
        }
    };

    /**
     * getId - return Participation's id
     * @return The participation's id
     */
    public int getId() {return mId;}

    /**
     * getStatusCode - return Status Code of participation
     * @return current participation's status code
     */
    public int getStatusCode() {return mStatusCode;}

    /**
     * getValidationRequested - return boolean value to indicate this participation is in a request of validation
     * @return participation's requested
     */
    public boolean getValidationRequested(){return mValidationRequested;}

    /**
     * getServiceHours - return number hours serviced of a participation to request for validation
     * @return the partcipation's number of hours
     */
    public float getServiceHours() {return mServiceHours;}

    /**
     * getResponsiblities - return briefly duty how a participant involved in the event
     * @return participant's responsibilities
     */
    public String getResponsibilities() {return mResponsibilities;}

    /**
     * getEvent - return the event of the participation
     * @return an Event
     */
    public Event getEvent() {return mEvent;}

    /**
     * getUser - return the user of the participation
     * @return a User
     */
    public User getUser() {return mUser;}

    /**
     * setStatusCode - change participation's status
     * @param statusCode new status code
     */
    public void setStatusCode(int statusCode) {mStatusCode = statusCode;}

    /**
     * setValidationRequeseted - set participation's request
     * @param validationRequested validation requested
     */
    public void setValidationRequested(boolean validationRequested)
    {
        mValidationRequested = validationRequested;
    }

    /**
     * setServiceHours - set number of hours
     * @param serviceHours participation's hours serviced
     */
    public void setServiceHours(float serviceHours){mServiceHours = serviceHours;}

    /**
     * setResponsibilities - set participant's responsibilities
     * @param responsibilities participant's responsibilities
     */
    public void setResponsibilities(String responsibilities){mResponsibilities = responsibilities;}

    /**
     * setEvent - set participation's event
     * @param event an Event
     */
    public void setEvent(Event event){mEvent = event;}

    /**
     * setUser - set participation's user
     * @param user an User
     */
    public void setUser(User user){mUser = user;}

    /**
     *  A method for displaying a <code>Participation</code> as a String
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeInt(mStatusCode);
        dest.writeByte((byte) (mValidationRequested ? 1 : 0));
        dest.writeFloat(mServiceHours);
        dest.writeString(mResponsibilities);
        dest.writeParcelable(mUser, flags);
        dest.writeParcelable(mEvent, flags);
    }
}
