package com.example.bilal.school;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.IdRes;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import static android.R.attr.password;

public class EnvironmentAssesmentActivity extends AppCompatActivity {

    Context _c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment_assesment);
        _c = this;
        TextView userName = (TextView) findViewById(R.id.textView_name);
        Typeface tf = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Heavy.otf");
        userName.setTypeface(tf);

        Typeface tf2 = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Light.otf");
        TextView textView30 = (TextView) findViewById(R.id.textView30);
        textView30.setTypeface(tf2);

    }

    Integer[] answers = new Integer[23];

    @Override
    protected void onResume() {
        super.onResume();
        final int block = getIntent().getIntExtra("block", -2);
        ImageView img1 = (ImageView) findViewById(R.id.imageView7);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnvironmentAssesmentActivity.this.finish();
            }
        });

        if (block == 1) {
            final int id = getIntent().getIntExtra("id", -2);
            TextView nameTextView = (TextView) findViewById(R.id.textView_name);
            nameTextView.setText("Environment Assessment");
            fillEntries(id);
            ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.main);
            for (int i = 0; i < layout.getChildCount(); i++) {
                View child = layout.getChildAt(i);
                child.setEnabled(false);
            }
        } else {
            final int place_id = getIntent().getIntExtra("place_id", -2);
            final int agent_id = getIntent().getIntExtra("agent_id", -2);
            final String name = getIntent().getStringExtra("name");
            TextView nameTextView = (TextView) findViewById(R.id.textView_name);
            nameTextView.setText(name);


            toggleThings((TextView) findViewById(R.id.textViewr1), (ToggleButton) findViewById(R.id.r1tb1), (ToggleButton) findViewById(R.id.r1tb2), 0);
            toggleThings((TextView) findViewById(R.id.textViewr2), (ToggleButton) findViewById(R.id.r2tb1), (ToggleButton) findViewById(R.id.r2tb2), 1);
            toggleThings((TextView) findViewById(R.id.textViewr3), (ToggleButton) findViewById(R.id.r3tb1), (ToggleButton) findViewById(R.id.r3tb2), 2);
            toggleThings((TextView) findViewById(R.id.textView4), (ToggleButton) findViewById(R.id.r4tb1), (ToggleButton) findViewById(R.id.r4tb2), 3);
            toggleThings((TextView) findViewById(R.id.textView5), (ToggleButton) findViewById(R.id.r5tb1), (ToggleButton) findViewById(R.id.r5tb2), 4);
            toggleThings((TextView) findViewById(R.id.textView6), (ToggleButton) findViewById(R.id.r6tb1), (ToggleButton) findViewById(R.id.r6tb2), 5);
            toggleThings((TextView) findViewById(R.id.textView7), (ToggleButton) findViewById(R.id.r7tb1), (ToggleButton) findViewById(R.id.r7tb2), 6);
            toggleThings((TextView) findViewById(R.id.textView8), (ToggleButton) findViewById(R.id.r8tb1), (ToggleButton) findViewById(R.id.r8tb2), 7);
            toggleThings((TextView) findViewById(R.id.textView9), (ToggleButton) findViewById(R.id.r9tb1), (ToggleButton) findViewById(R.id.r9tb2), 8);
            toggleThings((TextView) findViewById(R.id.textView10), (ToggleButton) findViewById(R.id.r10tb1), (ToggleButton) findViewById(R.id.r10tb2), 9);
            toggleThings((TextView) findViewById(R.id.textView11), (ToggleButton) findViewById(R.id.r11tb1), (ToggleButton) findViewById(R.id.r11tb2), 10);
            toggleThings((TextView) findViewById(R.id.textView12), (ToggleButton) findViewById(R.id.r12tb1), (ToggleButton) findViewById(R.id.r12tb2), 11);
            toggleThings((TextView) findViewById(R.id.textView13), (ToggleButton) findViewById(R.id.r13tb1), (ToggleButton) findViewById(R.id.r13tb2), 12);
            toggleThings((TextView) findViewById(R.id.textView14), (ToggleButton) findViewById(R.id.r14tb1), (ToggleButton) findViewById(R.id.r14tb2), 13);
            toggleThings((TextView) findViewById(R.id.textView15), (ToggleButton) findViewById(R.id.r15tb1), (ToggleButton) findViewById(R.id.r15tb2), 14);
            toggleThings((TextView) findViewById(R.id.textView16), (ToggleButton) findViewById(R.id.r16tb1), (ToggleButton) findViewById(R.id.r16tb2), 15);
            toggleThings((TextView) findViewById(R.id.textView17), (ToggleButton) findViewById(R.id.r17tb1), (ToggleButton) findViewById(R.id.r17tb2), 16);
            toggleThings((TextView) findViewById(R.id.textView18), (ToggleButton) findViewById(R.id.r18tb1), (ToggleButton) findViewById(R.id.r18tb2), 17);
            toggleThings((TextView) findViewById(R.id.textView19), (ToggleButton) findViewById(R.id.r19tb1), (ToggleButton) findViewById(R.id.r19tb2), 18);
            toggleThings((TextView) findViewById(R.id.textView20), (ToggleButton) findViewById(R.id.r20tb1), (ToggleButton) findViewById(R.id.r20tb2), 19);
            toggleThings2((TextView) findViewById(R.id.textView21), (ToggleButton) findViewById(R.id.r21tb1), (ToggleButton) findViewById(R.id.r21tb2), (ToggleButton) findViewById(R.id.r21tb3), 20);
            toggleThings2((TextView) findViewById(R.id.textView22), (ToggleButton) findViewById(R.id.r22tb1), (ToggleButton) findViewById(R.id.r22tb2), (ToggleButton) findViewById(R.id.r22tb3), 21);

            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            llp.setMargins(0, 16, 0, 25); // llp.setMargins(left, top, right, bottom);
            (findViewById(R.id.textViewr1)).setLayoutParams(llp);

            Button save = (Button) findViewById(R.id.button_save);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String other_comments = ((EditText) findViewById(R.id.txt_other_comments)).getText().toString();
                    RequestQueue queue = Volley.newRequestQueue(_c);
                    try {
                        final ProgressDialog pd = new ProgressDialog(_c);
                        pd.setMessage("Please wait!!");
                        pd.setTitle("Saving Record");
                        pd.show();
                        JSONObject obj = new JSONObject();
                        obj.put("agent", agent_id);
                        obj.put("school", place_id);
                        String data = "";
                        for (int i : answers) {
                            data += String.valueOf(i) + ";";
                        }

                        data += other_comments;
                        obj.put("data", data);
                        obj.put("lat", 0);
                        obj.put("long", 0);

                        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                                (Request.Method.POST, "http://shns.bitmatrix.co/api/submit_environment_assesment/", obj, new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Log.i("Json", response.toString());
                                        pd.cancel();
                                        try {
                                            if (response.getBoolean("success")) {
                                                DBHelper db = new DBHelper(EnvironmentAssesmentActivity.this);
                                                long result = db.add_environment_reporting(place_id, 0, 0, answers[0], answers[1], answers[2], answers[3], answers[4], answers[5], answers[6],
                                                        answers[7], answers[8], answers[9], answers[10], answers[11], answers[12], answers[13], answers[14], answers[15], answers[16],
                                                        answers[17], other_comments);

                                                if (result == -1) {
                                                    Toast.makeText(EnvironmentAssesmentActivity.this, "Error occurred", Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(EnvironmentAssesmentActivity.this, "Record added successfully", Toast.LENGTH_LONG).show();
                                                }

                                                EnvironmentAssesmentActivity.this.finish();

                                            } else {
                                                Toast.makeText(_c, "Invalid Login and password, Please try again.", Toast.LENGTH_LONG).show();
                                            }
                                        } catch (Exception e) {

                                        }
                                    }
                                }, new Response.ErrorListener() {

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // TODO Auto-generated method stub
                                        Log.i("Error", error.toString());
                                        Toast.makeText(_c, "Connection Error", Toast.LENGTH_LONG).show();
                                        pd.cancel();
                                    }
                                });
                        queue.add(jsObjRequest);
                    } catch (Exception e) {

                    }
                }
            });
        }
    }

    void toggleThings2(final TextView txtView, final ToggleButton tb1, final ToggleButton tb2, final ToggleButton tb3, final int index) {
        tb1.setBackgroundColor(Color.parseColor("#758FE2"));
        tb2.setBackgroundColor(Color.parseColor("#F5F5F5"));
        tb3.setBackgroundColor(Color.parseColor("#F5F5F5"));

        tb1.setTextColor(Color.parseColor("#FFFFFF"));
        tb2.setTextColor(Color.parseColor("#000000"));
        tb3.setTextColor(Color.parseColor("#000000"));

        Typeface tf = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Roman.otf");
        txtView.setTypeface(tf);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llp.setMargins(0, 55, 0, 25); // llp.setMargins(left, top, right, bottom);
        txtView.setLayoutParams(llp);

        tb1.setOnClickListener(new View.OnClickListener(){
        @Override
            public void onClick (View v){
            tb1.setChecked(true);
            tb2.setChecked(false);
            tb3.setChecked(false);
            answers[index] = 0;
            tb1.setBackgroundColor(Color.parseColor("#758FE2"));
            tb2.setBackgroundColor(Color.parseColor("#F5F5F5"));
            tb3.setBackgroundColor(Color.parseColor("#F5F5F5"));
            tb1.setTextColor(Color.parseColor("#FFFFFF"));
            tb2.setTextColor(Color.parseColor("#000000"));
            tb3.setTextColor(Color.parseColor("#000000"));
        }
        });

        tb2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View v){
            tb2.setChecked(true);
            tb1.setChecked(false);
            tb3.setChecked(false);
            answers[index] = 1;
            tb2.setBackgroundColor(Color.parseColor("#758FE2"));
            tb1.setBackgroundColor(Color.parseColor("#F5F5F5"));
            tb3.setBackgroundColor(Color.parseColor("#F5F5F5"));
            tb2.setTextColor(Color.parseColor("#FFFFFF"));
            tb1.setTextColor(Color.parseColor("#000000"));
            tb3.setTextColor(Color.parseColor("#000000"));
    }
    });

        tb3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View v){
        tb3.setChecked(true);
        tb1.setChecked(false);
        tb2.setChecked(false);
        answers[index] = 2;
        tb3.setBackgroundColor(Color.parseColor("#758FE2"));
        tb1.setBackgroundColor(Color.parseColor("#F5F5F5"));
        tb2.setBackgroundColor(Color.parseColor("#F5F5F5"));
        tb3.setTextColor(Color.parseColor("#FFFFFF"));
        tb1.setTextColor(Color.parseColor("#000000"));
        tb2.setTextColor(Color.parseColor("#000000"));
    }
    });
    answers[index]=0;
}
    void toggleThings(final TextView txtView, final ToggleButton tb1, final ToggleButton tb2, final int index) {
        tb1.setBackgroundColor(Color.parseColor("#758FE2"));
        tb2.setBackgroundColor(Color.parseColor("#F5F5F5"));
        tb1.setTextColor(Color.parseColor("#FFFFFF"));
        tb2.setTextColor(Color.parseColor("#000000"));
        Typeface tf = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Roman.otf");
        txtView.setTypeface(tf);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llp.setMargins(0, 55, 0, 25); // llp.setMargins(left, top, right, bottom);
        txtView.setLayoutParams(llp);
//        txtView.setTextAppearance(R.style.TextAppearance_AppCompat_Small);
        tb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tb1.setChecked(true);
                tb2.setChecked(false);
                answers[index] = 1;
                tb1.setBackgroundColor(Color.parseColor("#758FE2"));
                tb2.setBackgroundColor(Color.parseColor("#F5F5F5"));
                tb1.setTextColor(Color.parseColor("#FFFFFF"));
                tb2.setTextColor(Color.parseColor("#000000"));
            }
        });

        tb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tb2.setChecked(true);
                tb1.setChecked(false);
                answers[index] = 0;
                tb2.setBackgroundColor(Color.parseColor("#758FE2"));
                tb1.setBackgroundColor(Color.parseColor("#F5F5F5"));
                tb2.setTextColor(Color.parseColor("#FFFFFF"));
                tb1.setTextColor(Color.parseColor("#000000"));
            }
        });
        answers[index] = 1;
    }

    public void fillEntries(int id) {
        DBHelper db = new DBHelper(this);
        SQLiteDatabase db1 = db.getReadableDatabase();

        String selection = "id = ?";
        String[] selectionArgs = { String.valueOf(id)};

        Cursor c = db1.query(
                "environment_reporting_entry",                     // The table to query
                null,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        if (c.moveToNext()) {
            setToggelThings(c,"boundry_wall",R.id.r1tb1,R.id.r1tb2);
            setToggelThings(c,"electricity",R.id.r2tb1,R.id.r2tb2);
            setToggelThings(c,"playground",R.id.r3tb1,R.id.r3tb2);
            setToggelThings(c,"clean_classroom",R.id.r4tb1,R.id.r4tb2);
            setToggelThings(c,"class_floor",R.id.r5tb1,R.id.r5tb2);
            setToggelThings(c,"janitorial_staff",R.id.r6tb1,R.id.r6tb2);
            setToggelThings(c,"ventilated_classroos",R.id.r7tb1,R.id.r7tb2);
            setToggelThings(c,"tables_chair",R.id.r8tb1,R.id.r8tb2);
            setToggelThings(c,"functional_fans",R.id.r9tb1,R.id.r9tb2);
            setToggelThings(c,"clean_water",R.id.r10tb1,R.id.r10tb2);
            setToggelThings(c,"adequate_supply",R.id.r11tb1,R.id.r11tb2);
            setToggelThings(c,"functional_toilets",R.id.r12tb1,R.id.r12tb2);
            setToggelThings(c,"sufficient_number_of_toilets",R.id.r13tb1,R.id.r13tb2);
            setToggelThings(c,"running_water",R.id.r14tb1,R.id.r14tb2);
            setToggelThings(c,"drainage_system",R.id.r15tb1,R.id.r15tb2);
            setToggelThings(c,"drainage_system_functional",R.id.r16tb1,R.id.r16tb2);
            setToggelThings(c,"waste_management_plan",R.id.r17tb1,R.id.r17tb2);
            ToggleButton tb1 = ((ToggleButton) findViewById(R.id.r18tb1));
            ToggleButton tb2 = ((ToggleButton) findViewById(R.id.r18tb2));
            ToggleButton tb3 = ((ToggleButton) findViewById(R.id.r22tb3));

            if (c.getInt(c.getColumnIndex("waste_manag_frequency")) == 0) {
                tb1.setChecked(true);
                tb2.setChecked(false);
                tb3.setChecked(false);
            } else if (c.getInt(c.getColumnIndex("waste_manag_frequency")) == 1) {
                tb2.setChecked(true);
                tb1.setChecked(false);
                tb3.setChecked(false);
            } else if (c.getInt(c.getColumnIndex("waste_manag_frequency")) == 2) {
                tb3.setChecked(true);
                tb2.setChecked(false);
                tb1.setChecked(false);
            }

            tb1.setEnabled(false);
            tb2.setEnabled(false);
            tb3.setEnabled(false);

            setToggelThings(c,"waste_manag_frequency",R.id.r17tb1,R.id.r17tb2);
            EditText other_comments = (EditText) findViewById(R.id.txt_other_comments);
            other_comments.setText(c.getString(c.getColumnIndex("other_comments")));
            other_comments.setEnabled(false);
            Button b = (Button) findViewById(R.id.button_save);
            b.setVisibility(View.GONE);
        }

        db1.close();
        c.close();
    }

    public void setToggelThings(Cursor c, String col_name, int id1, int id2 ) {
        ToggleButton tb1 = ((ToggleButton) findViewById(id1));
        ToggleButton tb2 = ((ToggleButton) findViewById(id2));
        if (c.getInt(c.getColumnIndex(col_name)) == 1) {
            tb1.setChecked(true);
            tb2.setChecked(false);
        } else {
            tb2.setChecked(true);
            tb1.setChecked(false);
        }
        tb1.setEnabled(false);
        tb2.setEnabled(false);
    }


}


