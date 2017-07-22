package com.example.bilal.school;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.data;

public class ListDownReportingActivity extends AppCompatActivity {

    List<Integer> id = new ArrayList<>();
    List<Map<String, String>> data = new ArrayList<Map<String, String>>();
    ListView lv;
    int type;
    int record_type;
    int place_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_down_reporting);
        TextView nameTextView = (TextView) findViewById(R.id.textView_heading);
        TextView nameTextViewsub = (TextView) findViewById(R.id.textView27);

        type = getIntent().getIntExtra("reporting_Type", -1);
        record_type = getIntent().getIntExtra("record_type", -1);
        place_id = getIntent().getIntExtra("place_id", -1);
        lv = (ListView) findViewById(R.id.listView);

        if (type == 1) {
            nameTextView.setText("School Reporting");
        } else if (type == 2) {
            nameTextView.setText("Community Reporting");
        }

        if (record_type == 1) {
            nameTextViewsub.setText("Environment Assesment");
        } else if (record_type == 2) {
            nameTextViewsub.setText("Child Health Screening");
        } else if (record_type == 2) {
            nameTextViewsub.setText("Health Education Session");
        }

        populatedata(type, record_type);
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[]{"name", "dateadded"},
                new int[]{android.R.id.text1,
                        android.R.id.text2});
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                if (record_type == 1) {
                    i = new Intent(ListDownReportingActivity.this, EnvironmentAssesmentActivity.class);
                } else if (record_type == 2) {
                    i = new Intent(ListDownReportingActivity.this, ChildHealthScreeningActivity2.class);
                } else {
                    i = new Intent(ListDownReportingActivity.this, HealthEducationSessionActivity.class);
                }
                i.putExtra("block", 1);
                i.putExtra("id", ListDownReportingActivity.this.id.get(position));
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    void populatedata(int type, int record_type){
        data.clear();
        DBHelper db = new DBHelper(this);
        SQLiteDatabase db1 = db.getReadableDatabase();

        String table_name ;
        String name;
        if (record_type == 1) {
            table_name = "environment_reporting_entry";
            name = "Environment Assessment";
        } else if (record_type == 2) {
            table_name = "child_health_reporting_entry";
            name = "Child Health Screening";
        } else {
            table_name = "health_education_session_reporting_entry";
            name = "Health Education Session";
        }

        String[] projection = {
                "id",
                "dateadded"
        };

        String selection = "school_community_reporting = ?";// and place_id = ? ";
        String[] selectionArgs = { String.valueOf(type)};//, String.valueOf(place_id) };

        Cursor c = db1.query(
                table_name,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                "id DESC"                                 // The sort order
        );

//        Cursor c = db.get_children(type,0);
        while (c.moveToNext()) {

            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("name", name);
            datum.put("dateadded", c.getString(c.getColumnIndex("dateadded")));
            id.add(c.getInt(c.getColumnIndex("id")));
            data.add(datum);
        }
        db1.close();
    }

}
