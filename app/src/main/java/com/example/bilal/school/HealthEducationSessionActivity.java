package com.example.bilal.school;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.sql.Date;
import java.util.Locale;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class HealthEducationSessionActivity extends AppCompatActivity {

    Context _c;
    private DatePickerDialog fromDatePickerDialog;
    private TimePickerDialog std,etd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_education_session);
        _c = this;
        TextView userName = (TextView) findViewById(R.id.textView_name);
        Typeface tf = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Heavy.otf");
        userName.setTypeface(tf);

        Typeface tf2 = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Light.otf");
        TextView textView30 = (TextView) findViewById(R.id.textView30);
        textView30.setTypeface(tf2);

        TextView textView1 = (TextView) findViewById(R.id.textView1);
        textView1.setTypeface(tf2);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setTypeface(tf2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setTypeface(tf2);
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        textView4.setTypeface(tf2);
        TextView textView5 = (TextView) findViewById(R.id.textView5);
        textView5.setTypeface(tf2);
        TextView textView6 = (TextView) findViewById(R.id.textView6);
        textView6.setTypeface(tf2);
        TextView textView7 = (TextView) findViewById(R.id.textView7);
        textView7.setTypeface(tf2);
        TextView textView8 = (TextView) findViewById(R.id.textView8);
        textView8.setTypeface(tf2);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();
        final int block = getIntent().getIntExtra("block", -2);
        ImageView img1 = (ImageView) findViewById(R.id.imageView7);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HealthEducationSessionActivity.this.finish();
            }
        });
        if (block == 1) {
            final int id = getIntent().getIntExtra("id", -2);
            TextView nameTextView = (TextView) findViewById(R.id.textView_name);
            nameTextView.setText("Health Education");

            fillEntries(id);
        } else {

            final int type = getIntent().getIntExtra("reporting_Type", -1);
            final int place_id = getIntent().getIntExtra("place_id", -1);
            final int agent_id = getIntent().getIntExtra("agent_id", -1);
            final String name = getIntent().getStringExtra("name");
            TextView nameTextView = (TextView) findViewById(R.id.textView_name);
            nameTextView.setText(name);

            final EditText numberofParticipant = (EditText) findViewById(R.id.numberofParticipant);
            final EditText number_male = (EditText) findViewById(R.id.number_male);
            final EditText number_female = (EditText) findViewById(R.id.number_female);
            final EditText topic_discussed = (EditText) findViewById(R.id.topic);
            final EditText advisor = (EditText) findViewById(R.id.st);
            final EditText st = (EditText) findViewById(R.id.et);
            final EditText et = (EditText) findViewById(R.id.advisor);

            ConstraintLayout main = (ConstraintLayout) findViewById(R.id.constraintLayout4);
            if (type==1) {
                main.setBackgroundResource(R.drawable.school_gradiane);
            } else {
                main.setBackgroundResource(R.drawable.community_gradiane);
            }
//        final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker3);
            final EditText date = (EditText) findViewById(R.id.date);
            date.setInputType(InputType.TYPE_NULL);

//            ImageView img = (ImageView) findViewById(R.id.imageView_st);
//            img.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Calendar newCalendar = Calendar.getInstance();
//                    std = new TimePickerDialog(_c, new TimePickerDialog.OnTimeSetListener() {
//                        @Override
//                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//
//                        }
//                    },newCalendar.get(Calendar.HOUR_OF_DAY),newCalendar.get(Calendar.MINUTE), false);
//                    fromDatePickerDialog.show();
//                }
//            });

            ImageView img = (ImageView) findViewById(R.id.imageView4);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar newCalendar = Calendar.getInstance();
                    fromDatePickerDialog = new DatePickerDialog(_c, new DatePickerDialog.OnDateSetListener() {

                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            Calendar newDate = Calendar.getInstance();
                            newDate.set(year, monthOfYear, dayOfMonth);
                            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                            date.setText(dateFormatter.format(newDate.getTime()));
                        }

                    }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                    fromDatePickerDialog.show();
                }
            });

            Button b = (Button) findViewById(R.id.button3);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                int day  = datePicker.getDayOfMonth();
//                int month= datePicker.getMonth();
//                int year = datePicker.getYear();

//                final String formatedDate =  new StringBuilder().append(month + 1)
//                        .append("-").append(day).append("-").append(year)
//                        .append(" ").toString().trim();//sdf.format(c.getTime());
                    final String formatedDate = date.getText().toString();
                    final int male = Integer.valueOf(number_male.getText().toString() == "" ? "-1" : number_male.getText().toString());
                    final int female = Integer.valueOf(number_female.getText().toString() == "" ? "-1" : number_female.getText().toString());
                    final int numberofParticipanttxt = Integer.valueOf(numberofParticipant.getText().toString() == "" ? "-1" : numberofParticipant.getText().toString());
                    RequestQueue queue = Volley.newRequestQueue(_c);
                    try {
                        final ProgressDialog pd = new ProgressDialog(_c);
                        pd.setMessage("Please wait!!");
                        pd.setTitle("Saving Record");
                        pd.show();
                        JSONObject obj = new JSONObject();
                        obj.put("agent", agent_id);
                        if (type == 1) {
                            obj.put("owner_type", "School");
                        } else if (type == 2) {
                            obj.put("owner_type", "Tehsil");
                        } else {
                            obj.put("owner_type", "UC");
                        }

                        obj.put("owner_id", place_id);
                        obj.put("male", male);
                        obj.put("female", female);
                        obj.put("topic_discussed", topic_discussed.getText().toString());
                        obj.put("advisor", advisor.getText().toString());
                        obj.put("lat", 0);
                        obj.put("long", 0);
                        obj.put("date", formatedDate);
                        obj.put("numberofParticipant", numberofParticipanttxt);

                        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                                (Request.Method.POST, "http://shns.bitmatrix.co/api/submit_health_session/", obj, new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Log.i("Json", response.toString());
                                        pd.cancel();
                                        try {
                                            if (response.getBoolean("success")) {

                                                DBHelper db = new DBHelper(_c);
                                                if (db.add_heath_session(type, place_id, 0, 0, male,
                                                        female,numberofParticipanttxt,
                                                        topic_discussed.getText().toString(), advisor.getText().toString(), formatedDate, st.getText().toString(), et.getText().toString()) == -1) {
                                                    Toast.makeText(_c, "Error occurred", Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(_c, "Record added successfully", Toast.LENGTH_LONG).show();
                                                    HealthEducationSessionActivity.this.finish();
                                                }
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

    public void fillEntries(int id) {
        DBHelper db = new DBHelper(this);
        SQLiteDatabase db1 = db.getReadableDatabase();

        String selection = "id = ?";
        String[] selectionArgs = { String.valueOf(id)};

        Cursor c = db1.query(
                "health_education_session_reporting_entry",                     // The table to query
                null,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        if (c.moveToNext()) {

            final EditText number_male = (EditText) findViewById(R.id.number_male);
            final EditText number_female = (EditText) findViewById(R.id.number_female);
            final EditText topic_discussed = (EditText) findViewById(R.id.topic);
            final EditText advisor = (EditText) findViewById(R.id.advisor);

            number_male.setText(c.getString(c.getColumnIndex("number_of_male")));
            number_male.setEnabled(false);
            number_female.setText(c.getString(c.getColumnIndex("number_of_female")));
            number_female.setEnabled(false);
            topic_discussed.setText(c.getString(c.getColumnIndex("topic_discussed")));
            topic_discussed.setEnabled(false);
            advisor.setText(c.getString(c.getColumnIndex("advisor")));
            advisor.setEnabled(false);
            EditText date = (EditText) findViewById(R.id.date);
            date.setText(c.getString(c.getColumnIndex("date")));
            date.setEnabled(false);
            Button b = (Button) findViewById(R.id.button3);
            b.setVisibility(View.GONE);

//            txt_weight.setEnabled(false);
        }
        db1.close();
        c.close();
    }

//    public Location getLocation() {
//        try {
//            locationManager = (LocationManager) mContext
//                    .getSystemService(LOCATION_SERVICE);
//
//            // getting GPS status
//            isGPSEnabled = locationManager
//                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
//
//            // getting network status
//            isNetworkEnabled = locationManager
//                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//
//            if (!isGPSEnabled && !isNetworkEnabled) {
//                // no network provider is enabled
//            } else {
//                this.canGetLocation = true;
//                // First get location from Network Provider
//                if (isNetworkEnabled) {
//                    locationManager.requestLocationUpdates(
//                            LocationManager.NETWORK_PROVIDER,
//                            MIN_TIME_BW_UPDATES,
//                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
//                    Log.d("Network", "Network");
//                    if (locationManager != null) {
//                        location = locationManager
//                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                        if (location != null) {
//                            latitude = location.getLatitude();
//                            longitude = location.getLongitude();
//                        }
//                    }
//                }
//                // if GPS Enabled get lat/long using GPS Services
//                if (isGPSEnabled) {
//                    if (location == null) {
//                        locationManager.requestLocationUpdates(
//                                LocationManager.GPS_PROVIDER,
//                                MIN_TIME_BW_UPDATES,
//                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
//                        Log.d("GPS Enabled", "GPS Enabled");
//                        if (locationManager != null) {
//                            location = locationManager
//                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                            if (location != null) {
//                                latitude = location.getLatitude();
//                                longitude = location.getLongitude();
//                            }
//                        }
//                    }
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return location;
//    }
}
