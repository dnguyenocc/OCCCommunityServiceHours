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
    private int times;


    public Recovery() {
    }

    public Recovery(int userId, String question1, String question2, String answer1, String answer2, int times) {
        this.userId = userId;
        this.question1 = question1;
        this.question2 = question2;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.times = times;
    }

    public Recovery(int id, int userId, String question1, String question2, String answer1, String answer2, int times) {
        this.id = id;
        this.userId = userId;
        this.question1 = question1;
        this.question2 = question2;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.times = times;
    }

    protected Recovery(Parcel in) {
        id = in.readInt();
        userId = in.readInt();
        question1 = in.readString();
        question2 = in.readString();
        answer1 = in.readString();
        answer2 = in.readString();
        times = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(userId);
        dest.writeString(question1);
        dest.writeString(question2);
        dest.writeString(answer1);
        dest.writeString(answer2);
        dest.writeInt(times);
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

    public int getTimes() {
        return times;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getQuestion1() {
        return question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }
}
