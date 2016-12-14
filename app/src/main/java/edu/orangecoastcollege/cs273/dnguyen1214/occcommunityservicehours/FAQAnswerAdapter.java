package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huy Ho on 12/14/2016.
 */

public class FAQAnswerAdapter extends ArrayAdapter<FAQAnswer> {
    private Context mContext;
    private List<FAQAnswer> mAnswersList = new ArrayList<>();
    private int mResourceId;



    /**
     * Creates a new <code>EventListAdapter</code> given a mContext, resource id and list of answwers.
     *
     * @param c The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param answers The list of answers to display
     */
    public FAQAnswerAdapter(Context c, int rId, List<FAQAnswer> answers) {
        super(c,rId,answers);
        mContext = c;
        mResourceId = rId;
        mAnswersList = answers;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the Event selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        final FAQAnswer selectedFAQAnswer = mAnswersList.get(pos);

        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        FrameLayout answerListLinearLayout = (FrameLayout) view.findViewById(R.id.faqLinearLayout);

        TextView answerListNameTextView = (TextView) view.findViewById(R.id.faqDescriptionTextView);


        answerListLinearLayout.setTag(selectedFAQAnswer);
        answerListNameTextView.setText(selectedFAQAnswer.getDescription());


        return view;
    }
}
