package com.example.bilal.school;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchChild extends AppCompatActivity {
    List<Long> child_id = new ArrayList<>();
    String[] data ;
    ListView lv;
    int type;
    int place_id;
    int agent_id;
    String name;
    private CustomAdapter_Child mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_child);
        name = getIntent().getStringExtra("name");
        type = getIntent().getIntExtra("reporting_Type", -1);
        place_id = getIntent().getIntExtra("place_id", -1);
        agent_id = getIntent().getIntExtra("agent_id", -1);
        lv = (ListView) findViewById(R.id.asd);
        lv.setVisibility(View.GONE);
        TextView userName = (TextView) findViewById(R.id.username);
        Typeface tf = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Heavy.otf");
        userName.setTypeface(tf);

        ImageView img1 = (ImageView) findViewById(R.id.imageView7);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchChild.this.finish();
            }
        });

        ImageView search = (ImageView) findViewById(R.id.imageView8);
        final EditText dob = (EditText) findViewById(R.id.editText);
        final TextView notfouon = (TextView) findViewById(R.id.textView40);
        final TextView notfouon1 = (TextView) findViewById(R.id.textView39);
        final Button b = (Button) findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchChild.this, AddChild.class);
                i.putExtra("name", name);
                i.putExtra("reporting_Type", type);
                i.putExtra("place_id", place_id);
                i.putExtra("agent_id", agent_id);
                startActivity(i);
                SearchChild.this.finish();
            }
        });
        b.setVisibility(View.GONE);
        notfouon.setVisibility(View.GONE);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.setVisibility(View.VISIBLE);
                notfouon.setVisibility(View.VISIBLE);
                notfouon1.setVisibility(View.GONE);
                lv.setVisibility(View.VISIBLE);
                dob.clearFocus();
                notfouon.requestFocus();
                populatedata(type, place_id,dob.getText().toString());
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.stay, R.anim.slide_out_down);
    }

    void populatedata(int _type,int _place_id, String dob){
        DBHelper db = new DBHelper(this);
        SQLiteDatabase db1 = db.getReadableDatabase();

        String[] projection = {
                "id",
                "name",
                "father_name",
                "grade"
        };

        String selection = "school_community_reporting = ? and place_id = ? and dob = ?";
        String[] selectionArgs = { String.valueOf(_type), String.valueOf(_place_id), dob };

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

        mAdapter = new CustomAdapter_Child(data, this);
        if (mAdapter != null) {
            lv.setAdapter(mAdapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(SearchChild.this, ChildHealthScreeningActivity2.class);
                    i.putExtra("name", name);
                    i.putExtra("reporting_Type", type);
                    i.putExtra("child_id", child_id.get(position));
                    i.putExtra("place_id", place_id);
                    i.putExtra("place_server_id", -1);
                    i.putExtra("agent_id", agent_id);
                    startActivity(i);
                }
            });
        } else {

        }
    }
    class CustomAdapter_Child extends ArrayAdapter<String> {

        Context mContext;
        String[] data2;
        // View lookup cache
        private class ViewHolder {
            TextView txtName;
            TextView txtGrade;
            View img;
        }

        public CustomAdapter_Child(String[] data1, Context context) {
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
            CustomAdapter_Child.ViewHolder viewHolder; // view lookup cache stored in tag

            final View result;

            if (convertView == null) {

                viewHolder = new CustomAdapter_Child.ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.child_listview, parent, false);
                viewHolder.txtName = (TextView) convertView.findViewById(R.id.textView29);
                viewHolder.txtGrade = (TextView) convertView.findViewById(R.id.textView30);
                viewHolder.img = (View) convertView.findViewById(R.id.view4);
                result=convertView;
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (CustomAdapter_Child.ViewHolder) convertView.getTag();
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
