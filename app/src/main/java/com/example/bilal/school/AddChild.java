package com.example.bilal.school;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddChild extends AppCompatActivity {
    private DatePickerDialog fromDatePickerDialog_dob;
    int type;
    int place_id;
    int male;
    Context _c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);
        _c = this;

        type = getIntent().getIntExtra("reporting_Type", -1);
        place_id = getIntent().getIntExtra("place_id", -1);
        ImageView img1 = (ImageView) findViewById(R.id.imageView7);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddChild.this.finish();
            }
        });
        final int agent_id = getIntent().getIntExtra("agent_id", -1);
       final EditText txt_name = (EditText) findViewById(R.id.txt_name);
        final EditText txt_parent_name = (EditText) findViewById(R.id.txt_parent_name);
        final EditText father_name = (EditText) findViewById(R.id.txt_father_name);
        final EditText txt_dob = (EditText) findViewById(R.id.date_of_birth);
        txt_dob.setInputType(InputType.TYPE_NULL);
        final  EditText txt_grade = (EditText) findViewById(R.id.txt_grade);
        ImageView img = (ImageView) findViewById(R.id.imageView_dob);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                fromDatePickerDialog_dob = new DatePickerDialog(AddChild.this, new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                        txt_dob.setText(dateFormatter.format(newDate.getTime()));
                    }

                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                fromDatePickerDialog_dob.show();
            }
        });

        Spinner staticSpinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.blood,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
        Button b = (Button) findViewById(R.id.button6);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String formatedDate_dob = txt_dob.getText().toString();
                final int grade = Integer.valueOf(((EditText) findViewById(R.id.txt_grade)).getText().toString());
//                RequestQueue queue = Volley.newRequestQueue(_c);
//                try {
//                    final ProgressDialog pd = new ProgressDialog(_c);
//                    pd.setMessage("Please wait!!");
//                    pd.setTitle("Saving Record");
//                    pd.show();
//                    JSONObject obj = new JSONObject();
//                    obj.put("agent", agent_id);
//                    if (type == 1) {
//                        obj.put("owner_type", "School");
//                    } else if (type == 2) {
//                        obj.put("owner_type", "Tehsil");
//                    } else {
//                        obj.put("owner_type", "UC");
//                    }
//
//                    obj.put("owner_id", place_id);
//                    String data = "";
//                    data += txt_grade.getText().toString() + ";";
//                    data += txt_name.getText().toString() + ";";
//                    data += father_name.getText().toString() + ";";
//                    data += txt_parent_name.getText().toString() + ";";
//                    data += txt_dob.getText().toString() + ";";
//                    data += male;
//                    obj.put("data", data);
//                    JsonObjectRequest jsObjRequest = new JsonObjectRequest
//                            (Request.Method.POST, "http://shns.bitmatrix.co/api/create_student/", obj, new Response.Listener<JSONObject>() {
//
//                                @Override
//                                public void onResponse(JSONObject response) {
//                                    Log.i("Json", response.toString());
//                                    pd.cancel();
//                                    try {
//                                        if (response.getBoolean("success")) {
//
//
                                            DBHelper db = new DBHelper(AddChild.this);
//                String numberOfSiblings = ((EditText) findViewById(R.id.txt_sibling)).getText().toString();
//                int numofsiblins = numberOfSiblings == "" ? -1 : Integer.valueOf(numberOfSiblings);
                long child_id = db.add_child(type, place_id, 0, 0, grade, ((EditText) findViewById(R.id.txt_name)).getText().toString(),
                        ((EditText) findViewById(R.id.txt_parent_name)).getText().toString(), ((EditText) findViewById(R.id.txt_father_name)).getText().toString(),
                        formatedDate_dob, male);
//                        ((EditText) findViewById(R.id.txt_height)).getText().toString(), ((EditText) findViewById(R.id.txt_weight)).getText().toString());
                if (child_id == -1) {
                    Toast.makeText(AddChild.this, "Error occurred", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AddChild.this, "Record added successfully", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(AddChild.this, ChildHealthScreeningActivity2.class);
                    i.putExtra("reporting_Type", type);
                    i.putExtra("child_id", child_id);
                    i.putExtra("place_id", place_id);
                    i.putExtra("place_server_id", -1);

                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_up, R.anim.stay);
                    AddChild.this.finish();
                    }
//                                        } else {
//                                            Toast.makeText(_c, "Invalid Login and password, Please try again.", Toast.LENGTH_LONG).show();
//                                        }
//                                    } catch (Exception e) {
//
//                                    }
//                                }
//                            }, new Response.ErrorListener() {
//
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//                                    // TODO Auto-generated method stub
//                                    Log.i("Error", error.toString());
//                                    Toast.makeText(_c, "Connection Error", Toast.LENGTH_LONG).show();
//                                    pd.cancel();
//                                }
//                            });
//                    queue.add(jsObjRequest);
//                } catch (Exception e) {
//
//                }

            }
        });

        toggleThings((ToggleButton) findViewById(R.id.r2tb1), (ToggleButton) findViewById(R.id.r2tb2), 12);
    }

    void toggleThings(final ToggleButton tb1, final  ToggleButton tb2, final int index) {
        tb1.setBackgroundColor(Color.parseColor("#758FE2"));
        tb2.setBackgroundColor(Color.parseColor("#F5F5F5"));
        tb1.setTextColor(Color.parseColor("#FFFFFF"));
        tb2.setTextColor(Color.parseColor("#000000"));

        tb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tb1.setChecked(true);
                tb2.setChecked(false);
                tb1.setBackgroundColor(Color.parseColor("#758FE2"));
                tb2.setBackgroundColor(Color.parseColor("#F5F5F5"));
                tb1.setTextColor(Color.parseColor("#FFFFFF"));
                tb2.setTextColor(Color.parseColor("#000000"));
                male = 0;
            }
        });

        tb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tb2.setChecked(true);
                tb1.setChecked(false);
                tb2.setBackgroundColor(Color.parseColor("#758FE2"));
                tb1.setBackgroundColor(Color.parseColor("#F5F5F5"));
                tb2.setTextColor(Color.parseColor("#FFFFFF"));
                tb1.setTextColor(Color.parseColor("#000000"));
                male = 1;
            }
        });
        male = 1;
    }


}
