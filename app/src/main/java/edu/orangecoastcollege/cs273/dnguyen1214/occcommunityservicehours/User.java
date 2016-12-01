package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;


import android.net.Uri;

/**
 * Created by Huy Ho on 11/28/2016.
 */

public class User {
    private int mId;
    private String firstName;
    private String lastName;
    private String mUserName;
    private String mEmail;
    private String mPhoneNum;
    private String mPassWord;
    private Uri mImageUri;



    public User() {
    }

    public User(String firstName, String lastName, String mUserName, String mEmail, String mPhoneNum, String mPassWord, Uri mImageUri) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mUserName = mUserName;
        this.mEmail = mEmail;
        this.mPhoneNum = mPhoneNum;
        this.mPassWord = mPassWord;
        this.mImageUri = mImageUri;
    }

    public User(String firstName, String lastName, String mUserName, String mEmail, String mPhoneNum, String mPassWord) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mUserName = mUserName;
        this.mEmail = mEmail;
        this.mPhoneNum = mPhoneNum;
        this.mPassWord = mPassWord;
    }

    public User(int mId, String firstName, String lastName, String mUserName, String mEmail, String mPhoneNum, String mPassWord, Uri mImageUri) {
        this.mId = mId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mUserName = mUserName;
        this.mEmail = mEmail;
        this.mPhoneNum = mPhoneNum;
        this.mPassWord = mPassWord;
        this.mImageUri = mImageUri;
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
