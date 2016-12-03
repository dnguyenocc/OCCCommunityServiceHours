package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;


import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Huy Ho on 11/28/2016.
 */

public class User implements Parcelable{
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

    public User(String firstName, String lastName, String mUserName, String mEmail, String mPhoneNum, String mPassWord, double mHours, int mRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mUserName = mUserName;
        this.mEmail = mEmail;
        this.mPhoneNum = mPhoneNum;
        this.mPassWord = mPassWord;
        this.mHours = mHours;
        this.mRole = mRole;
    }

    public User(String firstName, String lastName, String mUserName, String mEmail, String mPhoneNum, String mPassWord, double mHours, int mRole, Uri mImageUri) {
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

    public User(int mId, String firstName, String lastName, String mUserName, String mEmail, String mPhoneNum, String mPassWord, double mHours, int mRole, Uri mImageUri) {
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


    public double getmHours() {
        return mHours;
    }

    public void setmHours(double mHours) {
        this.mHours = mHours;
    }

    public void setmRole(int mRole) {
        this.mRole = mRole;
    }

    public int getmRole() {
        return mRole;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getmUserName() {
        return mUserName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmPhoneNum() {
        return mPhoneNum;
    }

    public String getmPassWord() {
        return mPassWord;
    }

    public Uri getmImageUri() {
        return mImageUri;
    }

    public int getmId() {
        return mId;
    }


    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setmPhoneNum(String mPhoneNum) {
        this.mPhoneNum = mPhoneNum;
    }

    public void setmPassWord(String mPassWord) {
        this.mPassWord = mPassWord;
    }

    public void setmImageUri(Uri mImageUri) {
        this.mImageUri = mImageUri;
    }
}
