package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Huy Ho on 12/14/2016.
 */

public class FAQAnswer implements Parcelable {

    private int id;
    private String name;
    private String description;


    public FAQAnswer() {
    }

    public FAQAnswer(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public FAQAnswer(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;

    }

    protected FAQAnswer(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FAQAnswer> CREATOR = new Creator<FAQAnswer>() {
        @Override
        public FAQAnswer createFromParcel(Parcel in) {
            return new FAQAnswer(in);
        }

        @Override
        public FAQAnswer[] newArray(int size) {
            return new FAQAnswer[size];
        }
    };

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    //    public ArrayList<String> getRandomAnswer(String question) {
////        Random rng = new Random();
////        int randomPosition = rng.nextInt(mAllAnswers.length);
//
//        ArrayList<String> answerFilter = new ArrayList<>();
//        char [] questionString = new char[question.length()];
//
//        for (int i = 0; i < question.length(); i++) {
//            char c = question.charAt(i);
//            questionString[i] = c;
//        }
//
//        for (int i = 0; i < questionString.length; i++)
//        {
//            for (int j = 0; j < mAllAnswers.length; i++)
//            {
//                if(mAllAnswers[j].indexOf(questionString[i]) >= 0)
//                {
//                    answerFilter.add(mAllAnswers[j]);
//                }
//            }
//        }
//
//
//            return answerFilter;
//    }
}
