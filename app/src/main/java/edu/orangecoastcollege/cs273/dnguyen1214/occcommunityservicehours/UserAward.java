package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;

/**
 * Created by Huy Ho on 12/9/2016.
 */

public class UserAward {

    private double progressPointHours;
    private String levelName;
    private Uri imageUri;
    private int point;
    private double hours;
    private double neededHours;

    public UserAward() {
    }

    public double getprogressPointHours() {
        return progressPointHours;
    }

    public String getLevelName() {
        return levelName;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    /**
     * getUserAwardImageUri: get the image uri which indicate the award for the user
     * @param user an user object
     * @param context a context activity
     * @return an uri image
     */
    public void setUserAwardImageUri(User user, Context context)
    {

        hours = user.getmHours();

        if(hours < 100.0) {
            point = 0;
            levelName = "No Level";
            progressPointHours = 100.0;
            imageUri = getUriToResource(context,R.drawable.prez_volunteer_award_logo);
        }
        else if (hours >= 100.0 && hours <= 174.0){
            levelName = "Bronze Level";
            progressPointHours = 174.0;
            point = 1;
            imageUri = getUriToResource(context,R.drawable.bronze);
        }
        else if(hours >= 175.0 && hours <= 249.0){
            point = 2;
            levelName = "Silver Level";
            progressPointHours = 249.0;
            imageUri = getUriToResource(context,R.drawable.silver);
        }

        else if (hours >= 250 && hours <= 4000){
            point = 3;
            levelName ="Gold Level";
            progressPointHours = 4000;
            imageUri = getUriToResource(context,R.drawable.gold);
        }

        else if (hours >=4000) {
            point = 4;
            levelName = "LifeTime Level";
            progressPointHours = 0.0;
            imageUri = getUriToResource(context,R.drawable.best);
        }

        neededHours = progressPointHours - hours;

    }

public int getPercent()
{
    return (int)(hours*100.0/progressPointHours);
}



    private static final Uri getUriToResource(@NonNull Context context, @AnyRes int resId) throws Resources.NotFoundException{
        Resources res = context.getResources();

        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" +res.getResourcePackageName(resId)
                +'/'+ res.getResourceTypeName(resId)
                + '/' +res.getResourceEntryName(resId)
        );
    }
}
