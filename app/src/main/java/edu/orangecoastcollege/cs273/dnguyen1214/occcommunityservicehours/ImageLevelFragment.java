package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ImageLevelFragment extends DialogFragment {

    private Button okButton;

    public ImageLevelFragment() {
        // Required empty public constructor
    }

    public static ImageLevelFragment newInstance(String title) {
        ImageLevelFragment frag = new ImageLevelFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v =  inflater.inflate(R.layout.fragment_image_level, container, false);
//        okButton = (Button) v.findViewById(R.id.finishViewLevelIButton);
//        okButton.setOnClickListener(this);

        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        okButton = (Button) view.findViewById(R.id.finishViewLevelIButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Level");
        getDialog().setTitle(title);

        // Show soft keyboard automatically and request focus to field
        //getDialog().getWindow().setSoftInputMode(
               // WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}
