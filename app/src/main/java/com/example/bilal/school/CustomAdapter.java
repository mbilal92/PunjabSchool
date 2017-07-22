package com.example.bilal.school;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Bilal on 5/28/2017.
 */
class CustomAdapter extends ArrayAdapter<String> {

    private String[] dataSet;
    Context mContext;

    // View lookup cache
    private class ViewHolder {
        TextView txtName;
        View img;
    }

    public CustomAdapter(String[] data, Context context) {
        super(context, R.layout.school_listview, data);
        this.dataSet = data;
        this.mContext=context;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String dataModel = dataSet[position];
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.school_listview, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.textView29);
            viewHolder.img = (View) convertView.findViewById(R.id.view4);
            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

//            Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//            result.startAnimation(animation);
//            lastPosition = position;

        viewHolder.txtName.setText(dataModel);
        if (position % 3 == 0) {
            viewHolder.img.setBackgroundColor(Color.parseColor("#7BD189"));
        } else if (position % 3 == 2) {
            viewHolder.img.setBackgroundColor(Color.parseColor("#75D5E2"));
        } else if (position % 3 == 2) {
            viewHolder.img.setBackgroundColor(Color.parseColor("#758FE2"));
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
