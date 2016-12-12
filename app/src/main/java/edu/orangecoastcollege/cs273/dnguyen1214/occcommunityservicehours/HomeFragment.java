package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Context context;
    private DBHelper db;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        context  = this.getContext();
        db = new DBHelper(context);
        final User user = db.getLoginUser();

        View view =
                inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        GridView gridView = (GridView) view.findViewById(R.id.homeButtonsGridView);
        if (user.getmRole()==User.ROLE_USER)
            gridView.setAdapter(new HomeButtonAdapter(context));
        else  if (user.getmRole()==User.ROLE_ADMIN)
            gridView.setAdapter(new AdminHomeButtonAdapter(context));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                if (user.getmRole()==User.ROLE_ADMIN)
                    ((AdminActivity)getActivity()).doNavigate((Integer)v.getTag());
                else if (user.getmRole()==User.ROLE_USER)
                    ((MainActivity)getActivity()).doNavigate((Integer)v.getTag());
            }
        });
        return view;
    }

}
