package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;


import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Huy Ho on 11/28/2016.
 */

public class User implements Parcelable{
    public static final int ROLE_ADMIN = 1;
    public static final int ROLE_USER = 2;
    private int mId;
    private String firstName;
    private String lastName;
    private String mUserName;
    private String mEmail;
    private String mPhoneNum;
    private String mPassWord;
    private double mHours;
    private int mRole; // 1 is admin, 2 is normal user
    private Uri mImageUri;




    public User() {
    }

    /**
     * User - Default constructor that constructs a new User object when called.
     *
     * @param firstName  The first name of the user.
     * @param lastName The last name of the user.
     * @param mUserName   The username of the user
     * @param mEmail   The email of the user.
     * @param mPhoneNum  The phone number of the user.
     * @param mPassWord  The password of the user.
     * @param mHours  Total hours community service of the user.
     * @param mRole  The role of the user in application; 1 is admin and 2 is user
     * @param mImageUri  An image uri for user.
     */
    public User(String firstName, String lastName, String mUserName, String mEmail,
                String mPhoneNum, String mPassWord, double mHours, int mRole, Uri mImageUri) {

        this(-1, firstName, lastName, mUserName, mEmail, mPhoneNum, mPassWord, mHours, mRole, mImageUri);
    }
    /**
     * User - Overload constructor that constructs a new User object when called.
     * @param mId  The id number of the user.
     * @param firstName  The first name of the user.
     * @param lastName The last name of the user.
     * @param mUserName   The username of the user
     * @param mEmail   The email of the user.
     * @param mPhoneNum  The phone number of the user.
     * @param mPassWord  The password of the user.
     * @param mHours  Total hours community service of the user.
     * @param mRole  The role of the user in application; 1 is admin and 2 is user
     * @param mImageUri  An image uri for user.
     */
    public User(int mId, String firstName, String lastName, String mUserName, String mEmail,
                String mPhoneNum, String mPassWord, double mHours, int mRole, Uri mImageUri) {

        this.mId = mId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mUserName = mUserName;
        this.mEmail = mEmail;
        this.mPhoneNum = mPhoneNum;
        this.mPassWord = mPassWord;
        this.mHours = mHours;
        this.mRole = mRole;
        this.mImageUri = mImageUri;
    }

    protected User(Parcel in) {
        mId = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        mUserName = in.readString();
        mEmail = in.readString();
        mPhoneNum = in.readString();
        mPassWord = in.readString();
        mHours = in.readDouble();
        mRole = in.readInt();
        mImageUri = in.readParcelable(Uri.class.getClassLoader());
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(mUserName);
        dest.writeString(mEmail);
        dest.writeString(mPhoneNum);
        dest.writeString(mPassWord);
        dest.writeDouble(mHours);
        dest.writeInt(mRole);
        dest.writeParcelable(mImageUri, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


    /**
     * getmHours: return the total hours  of the user
     *
     * @return a double value
     */
    public double getmHours() {
        return mHours;
    }

    /**
     * setmHours: set total hours volunteer of the user
     * @param mHours a double value
     */
    public void setmHours(double mHours) {
        this.mHours = mHours;
    }
    /**
     * setmRole: set role of the user (1 is admin; 2 is user)
     *
     * @param mRole an integer value
     */
    public void setmRole(int mRole) {
        this.mRole = mRole;
    }

    /***
     * getmRole: get the role of the user  for authorization (1 is admin; 2 is user)
     * @return an integer value of user's role
     */
    public int getmRole() {
        return mRole;
    }

    /**
     * setFirstName: set the first name of the user
     *
     * @param firstName a string value of user'first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * setLastName: set last name of the user
     * @param lastName a string value of user's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * getFirstName: get the first name of the user
     * @return a string value of user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * getLastName:  get last name of the user
     * @return a string value of user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * get the username of the user
     * @return a string value of user's username
     */
    public String getmUserName() {
        return mUserName;
    }

    /**
     * getmEmail: return the email of the user
     * @return a string value of user's email
     */
    public String getmEmail() {
        return mEmail;
    }

    /**
     * getmPhoneNum: return the phone number of the user
     * @return a string value of the user's phonenumber
     */
    public String getmPhoneNum() {
        return mPhoneNum;
    }

    /**
     * getmPassWord: return the password of the user
     * @return a string value of user's password
     */
    public String getmPassWord() {
        return mPassWord;
    }

    /**
     * getmImageUri: return the image uri of the user
     * @return a uri of user's image
     */
    public Uri getmImageUri() {
        return mImageUri;
    }

    /**
     * getmId: return the id of the user
     * @return a integer of user's id
     */
    public int getmId() {
        return mId;
    }

    /**
     * setmUserName: set the username of the user
     * @param mUserName a string of user's username
     */
    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    /**
     * setmEmail: set the email of the user
     * @param mEmail a string of user's email
     */
    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    /**
     * setmPhoneNum: set the phone number of the user
     * @param mPhoneNum a string value of user's phone
     */
    public void setmPhoneNum(String mPhoneNum) {
        this.mPhoneNum = mPhoneNum;
    }

    /**
     * setmPassWord: set the password of the user
     * @param mPassWord a sting value of user's password
     */
    public void setmPassWord(String mPassWord) {
        this.mPassWord = mPassWord;
    }

    /**
     * setmImageUri: set the image uri of the user
     * @param mImageUri a uri of user's image
     */
    public void setmImageUri(Uri mImageUri) {
        this.mImageUri = mImageUri;
    }
}
