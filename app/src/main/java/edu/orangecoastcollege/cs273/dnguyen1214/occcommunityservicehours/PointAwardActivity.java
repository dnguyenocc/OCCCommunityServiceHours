package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class PointAwardActivity extends AppCompatActivity {
    private View mFrontLayout;
    private View mBackLayout;
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible = false;
    private ImageView frontMedalImage;
    private ImageView backMedalImage;
    private DBHelper db;
    private int point;
    //private UserAward userAward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_award);
        //Flip Animation Implement
        findViews();
        loadAnimations();
        changeCameraDistance();
        db = new DBHelper(this);
        User user = db.getLoginUser();
        frontMedalImage = (ImageView) findViewById(R.id.frontMedalImageView);
        backMedalImage = (ImageView) findViewById(R.id.backMedalImageView);
        //userAward = new UserAward();
        Toast.makeText(this, user.getmUserName(), Toast.LENGTH_LONG).show();

        frontMedalImage.setImageURI(new UserAward().getUserAwardImageUri(user,this));
        backMedalImage.setImageURI(new UserAward().getUserAwardImageUri(user,this));


    }
    @Override
    public void onResume(){
        super.onResume();

        if (!mIsBackVisible) {
            mSetRightOut.setTarget(mFrontLayout);
            mSetLeftIn.setTarget(mBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = true;
        } else {
            mSetRightOut.setTarget(mBackLayout);
            mSetLeftIn.setTarget(mFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = false;
        }

    }
    //TODO functions for flip animation
    private void changeCameraDistance() {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mFrontLayout.setCameraDistance(scale);
        mBackLayout.setCameraDistance(scale);
    }

    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.in_animation);
    }

    private void findViews() {
        mFrontLayout = findViewById(R.id.frontLayout);
        mBackLayout = findViewById(R.id.backLayout);
    }

    public void flipMedal(View view) {
        if (!mIsBackVisible) {
            mSetRightOut.setTarget(mFrontLayout);
            mSetLeftIn.setTarget(mBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = true;
        } else {
            mSetRightOut.setTarget(mBackLayout);
            mSetLeftIn.setTarget(mFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = false;
        }
    }






}
