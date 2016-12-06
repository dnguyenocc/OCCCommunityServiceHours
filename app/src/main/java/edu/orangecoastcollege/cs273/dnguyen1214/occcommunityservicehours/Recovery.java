package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

/**
 * Created by Alex Ho on 12/6/2016.
 */

public class Recovery {
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
