package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex Ho on 12/6/2016.
 */

public class Recovery implements Parcelable {
    private int id;
    private int userId;
    private String question1;
    private String question2;
    private String answer1;
    private String answer2;
    private String status;

    /**
     * Default default constructor without any member in case use Firebase goolge
     */
    public Recovery() {
    }

    /**
     * Default constructor that constructs a new Recovery object when called.
     * @param userId the id of the user recovery
     * @param question1 the first question
     * @param question2 the second question
     * @param answer1   the answer for first question
     * @param answer2 the answer for second question
     * @param status the status of recovery
     */
    public Recovery(int userId, String question1, String question2, String answer1, String answer2, String status) {
        this(-1, userId, question1, question2, answer1, answer2, status);
    }
    /**
     * Overload constructor that constructs a new Recovery object when called.
     * @param userId the id of the user recovery
     * @param question1 the first question
     * @param question2 the second question
     * @param answer1   the answer for first question
     * @param answer2 the answer for second question
     * @param status the status of recovery
     */
    public Recovery(int id, int userId, String question1, String question2, String answer1, String answer2, String status) {
        this.id = id;
        this.userId = userId;
        this.question1 = question1;
        this.question2 = question2;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.status = status;
    }


    protected Recovery(Parcel in) {
        id = in.readInt();
        userId = in.readInt();
        question1 = in.readString();
        question2 = in.readString();
        answer1 = in.readString();
        answer2 = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(userId);
        dest.writeString(question1);
        dest.writeString(question2);
        dest.writeString(answer1);
        dest.writeString(answer2);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Recovery> CREATOR = new Creator<Recovery>() {
        @Override
        public Recovery createFromParcel(Parcel in) {
            return new Recovery(in);
        }

        @Override
        public Recovery[] newArray(int size) {
            return new Recovery[size];
        }
    };

    /**
     * setStatus: set the status of the recovery account
     * @param status a string value of recovery's status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * getStatus:  get the status of the recovery
     * @return a string value of recovery's status
     */
    public String getStatus() {
        return status;
    }

    /**
     * setUserId: set the user id of the recovery
     * @param userId an integer value of recovery's user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * setQuestion1: set the question 1 of the recovery
     * @param question1 a string value of recovery's question 1
     */
    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    /**
     * setQuestion2:  set the question 2 of the recovery
     * @param question2 a string value of recovery's question 2
     */
    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    /**
     * setAnswer1:  set the answer 1 os the recovery
     * @param answer1 a string value of recovery's answer
     */
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    /**
     * setAnswer2:  set the answer 2 of the recovery
     * @param answer2 a string value of recovery's answer 2
     */
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    /**
     * getId:  get the id of the recovery
     * @return an integer of recovery's id
     */
    public int getId() {
        return id;
    }

    /**
     * getUserId: get the user id of the recovery
     * @return an integer of recovery's user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * getQuestion1: get the question 1 of the recovery
     * @return a string value of recovery's question 1
     */
    public String getQuestion1() {
        return question1;
    }

    /**
     * getQuestion2:  get the question 2 of the recovery
     * @return a string value of recovery's question 2
     */
    public String getQuestion2() {
        return question2;
    }

    /**
     * getAnswer1:  get the answer 1 of the recovery
     * @return a string of recovery's question 1
     */
    public String getAnswer1() {
        return answer1;
    }

    /**
     * getAnswer2: get the answer 2 of the recovery
     * @return a string of recovery's answer 2
     */
    public String getAnswer2() {
        return answer2;
    }
}
