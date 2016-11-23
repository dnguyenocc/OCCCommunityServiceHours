package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.net.Uri;

/**
 * Created by hho65 on 11/22/2016.
 */

public class Users {
    private int mId;
    private String mFirstName;
    private String mLastName;
    private String mUserName;
    private String mEmail;
    private String mPhoneNum;
    private String mPassWord;
    private Uri mImageUri;


    public Users() {
    }

    public Users(int mId, String mFirstName, String mLastName, String mUserName, String mEmail, String mPhoneNum, String mPassWord, Uri mImageUri) {
        this.mId = mId;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mUserName = mUserName;
        this.mEmail = mEmail;
        this.mPhoneNum = mPhoneNum;
        this.mPassWord = mPassWord;
        this.mImageUri = mImageUri;
    }


    public String getmFirstName() {
        return mFirstName;
    }

    public String getmLastName() {
        return mLastName;
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

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
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
