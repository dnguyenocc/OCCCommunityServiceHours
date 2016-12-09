package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;

/**
 * Created by hho65 on 12/8/2016.
 */

public class FlipAnimation {


    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;




    public void toggleFlipAnim( View frontImageView, View backImageView, Context context)
    {


        loadAnimations(context);
        changeCameraDistance(context,frontImageView,backImageView);

            mSetRightOut.setTarget(frontImageView);
            mSetLeftIn.setTarget(backImageView);
            mSetRightOut.start();
            mSetLeftIn.start();

            mSetRightOut.setTarget(backImageView);
            mSetLeftIn.setTarget(frontImageView);
            mSetRightOut.start();
            mSetLeftIn.start();

    }

    private void changeCameraDistance(Context context, View frontImageView, View backImageView) {
        int distance = 8000;
        float scale = context.getResources().getDisplayMetrics().density * distance;
        frontImageView.setCameraDistance(scale);
        backImageView.setCameraDistance(scale);
    }

    private void loadAnimations(Context c) {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(c, R.animator.out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(c, R.animator.in_animation);
    }


}
