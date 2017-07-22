package com.example.bilal.school;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChildHealthScreeningActivity extends AppCompatActivity {

    List<Long> child_id = new ArrayList<>();
    String[] data ;
    ListView lv;
    int type;
    int place_id;
    String name;
    int agent_id;
    private CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_health_screening);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        name = getIntent().getStringExtra("name");
        agent_id = getIntent().getIntExtra("agent_id", -1);
        type = getIntent().getIntExtra("reporting_Type", -1);
        place_id = getIntent().getIntExtra("place_id", -1);
        TextView nameTextView = (TextView) findViewById(R.id.textView_name);
        nameTextView.setText(name);

        ImageView img = (ImageView) findViewById(R.id.imageView7);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChildHealthScreeningActivity.this.finish();
            }
        });

        ConstraintLayout main = (ConstraintLayout) findViewById(R.id.constraintLayout4);
        if (type==1) {
            main.setBackgroundResource(R.drawable.school_gradiane);
        } else {
            main.setBackgroundResource(R.drawable.community_gradiane);
        }

//        SimpleAdapter adapter = new SimpleAdapter(this, data,
//                android.R.layout.simple_list_item_2,
//                new String[] {"name", "grade"},
//                new int[] {android.R.id.text1,
//                        android.R.id.text2});

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChildHealthScreeningActivity.this, SearchChild.class);
                i.putExtra("name", name);
                i.putExtra("reporting_Type", type);
                i.putExtra("child_id", -1);
                i.putExtra("place_id", place_id);
                i.putExtra("place_server_id", -1);
                i.putExtra("agent_id", agent_id);
                startActivityForResult(i, 0);
                overridePendingTransition(R.anim.slide_in_up, R.anim.stay);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        populatedata(type, place_id);
        lv = (ListView) findViewById(R.id.listview_student);
        mAdapter = new CustomAdapter(data, this);
        lv.setAdapter(mAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ChildHealthScreeningActivity.this, ChildHealthScreeningActivity1.class);
                i.putExtra("name", name);
                i.putExtra("reporting_Type", type);            i.putExtra("child_id", child_id.get(position));
                i.putExtra("place_id", place_id);
                i.putExtra("place_server_id", -1);
                i.putExtra("agent_id", agent_id);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        populatedata(type, place_id);
        ((BaseAdapter) lv.getAdapter()).notifyDataSetChanged();
    }

    void populatedata(int type,int place_id){
        DBHelper db = new DBHelper(this);
        SQLiteDatabase db1 = db.getReadableDatabase();

        String[] projection = {
                "id",
                "name",
                "father_name",
                "grade"
        };

        String selection = "school_community_reporting = ? and place_id = ?";
        String[] selectionArgs = { String.valueOf(type), String.valueOf(place_id) };

        Cursor c = db1.query(
                "child_entry",                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                "id DESC"                                 // The sort order
        );

//        Cursor c = db.get_children(type,0);
        ArrayList<String> tmp = new ArrayList<>();
        while (c.moveToNext()) {

            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("name", c.getString(c.getColumnIndex("name")) + " "  + c.getString(c.getColumnIndex("father_name")));
            datum.put("grade", c.getString(c.getColumnIndex("grade")));
            child_id.add(c.getLong(c.getColumnIndex("id")));
            tmp.add(c.getString(c.getColumnIndex("name")) + " "  + c.getString(c.getColumnIndex("father_name")) + "," + c.getString(c.getColumnIndex("grade")));
        }

        data = tmp.toArray(new String[tmp.size()]);
        db1.close();
    }

    class CustomAdapter extends ArrayAdapter<String> {

        Context mContext;
        String[] data2;
        // View lookup cache
        private class ViewHolder {
            TextView txtName;
            TextView txtGrade;
            View img;
        }

        public CustomAdapter(String[] data1, Context context) {
        super(context, R.layout.child_listview, data1);
            this.mContext = context;
            data2 = data1;
        }

        private int lastPosition = -1;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            String name = data2[position].split(",")[0];
            String grade = "Grade " + data2[position].split(",")[1];
            // Check if an existing view is being reused, otherwise inflate the view
            ChildHealthScreeningActivity.CustomAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

            final View result;

            if (convertView == null) {

                viewHolder = new ChildHealthScreeningActivity.CustomAdapter.ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.child_listview, parent, false);
                viewHolder.txtName = (TextView) convertView.findViewById(R.id.textView29);
                viewHolder.txtGrade = (TextView) convertView.findViewById(R.id.textView30);
                viewHolder.img = (View) convertView.findViewById(R.id.view4);
                result=convertView;
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ChildHealthScreeningActivity.CustomAdapter.ViewHolder) convertView.getTag();
                result=convertView;
            }

//            Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//            result.startAnimation(animation);
//            lastPosition = position;

            viewHolder.txtName.setText(name);
            viewHolder.txtGrade.setText(grade);

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

}
