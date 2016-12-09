package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.animation.AnimatorSet;
import android.view.View;

/**
 * Created by hho65 on 12/8/2016.
 */

public class FlipAnimation {
    private boolean mIsBackVisible = false;
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private View mCardFrontLayout;
    private View mCardBackLayout;


//
//    public void toggleFlipAnim(View view, ImageView imageView, Context context){
//
//        findViews(view, imageView);
//        loadAnimations(context);
//        changeCameraDistance(context);
//        if (!mIsBackVisible) {
//
//            mSetRightOut.setTarget(mCardFrontLayout);
//            mSetLeftIn.setTarget(mCardBackLayout);
//            mSetRightOut.start();
//            mSetLeftIn.start();
//            mIsBackVisible = true;
//        } else {
//            mSetRightOut.setTarget(mCardBackLayout);
//            mSetLeftIn.setTarget(mCardFrontLayout);
//            mSetRightOut.start();
//            mSetLeftIn.start();
//            mIsBackVisible = false;
//        }
//    }
//
//    private void changeCameraDistance(Context context) {
//        int distance = 8000;
//        float scale = context.getResources().getDisplayMetrics().density * distance;
//        mCardFrontLayout.setCameraDistance(scale);
//        mCardBackLayout.setCameraDistance(scale);
//    }
//
//    private void loadAnimations(Context c) {
//        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(c, R.animator.out_animation);
//        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(c, R.animator.in_animation);
//    }
//
//    private void findViews(View v) {
//        mCardBackLayout = v.findViewById(R.drawable.imageView);
//        mCardFrontLayout = v.findViewById(R.id.imageView);
//    }
}
