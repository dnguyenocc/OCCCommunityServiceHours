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


    /**
     * getUserAwardImageUri: get the image uri which indicate the award for the user
     * @param user an user object
     * @param context a context activity
     * @return an uri image
     */
    public Uri getUserAwardImageUri(User user, Context context)
    {
        Uri imageURI = getUriToResource(context,R.drawable.prez_volunteer_award_logo);
        int award = calculationAward(user.getmHours());
        if(award == 1)
        {
            imageURI = getUriToResource(context,R.drawable.bronze);
        }
        else if(award == 2)
        {
            imageURI = getUriToResource(context,R.drawable.silver);
        }
        else if(award == 3)
        {
            imageURI = getUriToResource(context,R.drawable.gold);
        }
        else if(award == 4)
        {
            imageURI = getUriToResource(context,R.drawable.best);
        }

        return  imageURI;

    }


    /**
     * calculationAward: calculate the user's award by total hour community service of the user
     * @param hours a double value of user's hours
     * @return an integer value [0,1,2,3,4]
     */
    //This award is based on President Volunteer Service Award program (age 15-25)
    // 0 = nothing; 1 = bronze; 2 = silver; 3 = gold; 4 = lifetime
    private int calculationAward(double hours) {
        int award = 0;
        if (hours >= 100.0 && hours <= 174.0)
            award = 1;
        else if(hours >= 175.0 && hours <= 249.0)
            award = 2;
        else if (hours >= 250 && hours <= 4000)
            award = 3;
        else if (hours >=4000)
            award = 4;

        return award;
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
