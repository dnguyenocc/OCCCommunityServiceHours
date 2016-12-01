package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

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

public class Participation {
    final static int REGISTERED = 1;
    final static int VALIDATED = 2;
    private int mId;
    private int mStatusCode;
    private boolean mValidationRequested;
    private float mServiceHours;
    private String mResponsibilities;
    private User mUser;
    private Event mEvent;

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

    public Participation(int id, int status, User user, Event event)
    {
        mId = id;
        mStatusCode = status;
        mValidationRequested = false;
        mServiceHours = 0;
        mUser = user;
        mEvent = event;
    }

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

    public int getId() {return mId;}

    public int getStatusCode() {return mStatusCode;}

    public boolean getValidationRequested(){return mValidationRequested;}

    public float getServiceHours() {return mServiceHours;}

    public String getResponsibilities() {return mResponsibilities;}

    public Event getEvent() {return mEvent;}

    public User getUser() {return mUser;}

    public void setStatusCode(int statusCode) {mStatusCode = statusCode;}

    public void setValidationRequested(boolean validationRequested)
    {
        mValidationRequested = validationRequested;
    }

    public void setServiceHours(float serviceHours){mServiceHours = serviceHours;}

    public void setResponsibilities(String responsibilities){mResponsibilities = responsibilities;}

    public void setEvent(Event event){mEvent = event;}

    public void setUser(User user){mUser = user;}

    @Override
    public String toString() {
        return super.toString();
    }
}
