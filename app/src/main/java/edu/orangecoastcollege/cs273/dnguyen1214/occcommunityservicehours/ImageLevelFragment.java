package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class ImageLevelFragment extends DialogFragment {


    public ImageLevelFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_level, container, false);
    }


    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }



}
