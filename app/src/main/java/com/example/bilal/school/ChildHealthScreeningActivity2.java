package com.example.bilal.school;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ChildHealthScreeningActivity2 extends AppCompatActivity {

    Integer[] answers = new Integer[19];
    private DatePickerDialog fromDatePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_health_screening2);
        Intent i = getIntent();
        TextView userName = (TextView) findViewById(R.id.textView_name);
        Typeface tf = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Heavy.otf");
        userName.setTypeface(tf);

        Typeface tf2 = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Light.otf");
        TextView textView30 = (TextView) findViewById(R.id.textView30);
        textView30.setTypeface(tf2);

        final int block = getIntent().getIntExtra("block", -2);
        ImageView img1 = (ImageView) findViewById(R.id.imageView7);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChildHealthScreeningActivity2.this.finish();
                overridePendingTransition(R.anim.stay, R.anim.slide_out_down);
            }
        });
        if (block == 1) {
            final int id = getIntent().getIntExtra("id", -2);
            TextView nameTextView = (TextView) findViewById(R.id.textView_name);
            nameTextView.setText("child Health");
            fillEntries(id);
        } else {
            final String name = i.getStringExtra("name");
            final int type = i.getIntExtra("reporting_Type", -1);
            final long child_id = i.getLongExtra("child_id", -2);
            final int place_id = i.getIntExtra("place_id", -2);
//            final int place_server_id = i.getIntExtra("place_server_id", -2);
            TextView nameTextView = (TextView) findViewById(R.id.textView_name);
            nameTextView.setText(name);
//            EditText txt_name = (EditText) findViewById(R.id.txt_name);
//            EditText txt_parent_name = (EditText) findViewById(R.id.txt_parent_name);
//            EditText txt_sibling = (EditText) findViewById(R.id.txt_sibling);
//            EditText txt_height = (EditText) findViewById(R.id.txt_height);
//            EditText txt_weight = (EditText) findViewById(R.id.txt_weight);
//            EditText father_name = (EditText) findViewById(R.id.txt_father_name);
//            final EditText txt_dob = (EditText) findViewById(R.id.date_of_birth);
//            txt_dob.setInputType(InputType.TYPE_NULL);
//            ImageView img = (ImageView) findViewById(R.id.imageView_dob);
//            img.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Calendar newCalendar = Calendar.getInstance();
//                    fromDatePickerDialog_dob = new DatePickerDialog(ChildHealthScreeningActivity2.this, new DatePickerDialog.OnDateSetListener() {
//                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                            Calendar newDate = Calendar.getInstance();
//                            newDate.set(year, monthOfYear, dayOfMonth);
//                            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
//                            txt_dob.setText(dateFormatter.format(newDate.getTime()));
//                        }
//
//                    },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
//                    fromDatePickerDialog_dob.show();
//                }
//            });
//
            final EditText date = (EditText) findViewById(R.id.date);
            date.setInputType(InputType.TYPE_NULL);

            ImageView img2 = (ImageView) findViewById(R.id.imageView4);
            img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar newCalendar = Calendar.getInstance();
                    fromDatePickerDialog = new DatePickerDialog(ChildHealthScreeningActivity2.this, new DatePickerDialog.OnDateSetListener() {

                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            Calendar newDate = Calendar.getInstance();
                            newDate.set(year, monthOfYear, dayOfMonth);
                            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                            date.setText(dateFormatter.format(newDate.getTime()));
                        }

                    },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                    fromDatePickerDialog.show();
                }
            });

//            DBHelper db = new DBHelper(this);
//            if (child_id != -1) {
//                //            c = db.get_child(child_id);
//                SQLiteDatabase db1 = db.getReadableDatabase();
//
//                String selection = "id = ?";
//                String[] selectionArgs = {String.valueOf(child_id)};
//
//                Cursor c = db1.query(
//                        "child_entry",                     // The table to query
//                        null,                               // The columns to return
//                        selection,                                // The columns for the WHERE clause
//                        selectionArgs,                            // The values for the WHERE clause
//                        null,                                     // don't group the rows
//                        null,                                     // don't filter by row groups
//                        null                                 // The sort order
//                );
//
//                if (c.moveToNext()) {
////                    txt_grade.setText(c.getString(c.getColumnIndex("grade")));
////                    txt_grade.setEnabled(false);
////                    txt_name.setText(c.getString(c.getColumnIndex("name")));
////                    txt_name.setEnabled(false);
////                    txt_parent_name.setText(c.getString(c.getColumnIndex("parent_name")));
////                    txt_parent_name.setEnabled(false);
////                    father_name.setText(c.getString(c.getColumnIndex("father_name")));
////                    father_name.setEnabled(false);
////                    txt_dob.setText(c.getString(c.getColumnIndex("dob")));
////                    txt_dob.setEnabled(false);
////                    img.setEnabled(false);
////                    txt_sibling.setText(c.getString(c.getColumnIndex("number_of_sibling")));
////                    txt_sibling.setEnabled(false);
////                    txt_height.setText(c.getString(c.getColumnIndex("height")));
////                    txt_height.setEnabled(false);
////                    txt_weight.setText(c.getString(c.getColumnIndex("weight")));
////                    txt_weight.setEnabled(false);
//                }
//                db1.close();
//                c.close();
//            }

            Button b = (Button) findViewById(R.id.button2);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    String formatedDate_dob = txt_dob.getText().toString();
                    String formatedDate = date.getText().toString();
                    DBHelper db = new DBHelper(ChildHealthScreeningActivity2.this);
                    String numberOfSiblings = ((EditText) findViewById(R.id.txt_sibling)).getText().toString();
                    int numofsiblins = numberOfSiblings.equals("") ? -1 : Integer.valueOf(numberOfSiblings);
                    long result = db.add_child_health_reporting(child_id, type, place_id, 0, 0, numofsiblins,
                            ((EditText) findViewById(R.id.txt_height)).getText().toString(), ((EditText) findViewById(R.id.txt_weight)).getText().toString(),
                            answers[0], answers[1], answers[2], answers[3], answers[4], answers[5], answers[6], answers[7], answers[8], answers[9], answers[10],
                            answers[11],answers[12],answers[13],answers[14],answers[15],answers[16],answers[17],
                            ((EditText) findViewById(R.id.txt_other_problem)).getText().toString(), answers[18], formatedDate);
                    if (result == -1) {
                        Toast.makeText(ChildHealthScreeningActivity2.this, "Error occurred", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ChildHealthScreeningActivity2.this, "Record added successfully", Toast.LENGTH_LONG).show();
                        if (child_id == -1) {
                            Intent data = new Intent();
                            data.putExtra("student_added", "Data 1 value");
                            setResult(RESULT_OK, data);
                            ChildHealthScreeningActivity2.this.finish();
                            overridePendingTransition(R.anim.stay, R.anim.slide_out_down);
                        } else {
                            ChildHealthScreeningActivity2.this.finish();
                            overridePendingTransition(R.anim.stay, R.anim.slide_out_down);
                        }
                    }
                }
            });

            Typeface tf1 = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Roman.otf");

            ((TextView) findViewById(R.id.textView1)).setTypeface(tf1);
            ((TextView) findViewById(R.id.textView2)).setTypeface(tf1);
            ((TextView) findViewById(R.id.textView3)).setTypeface(tf1);
            ((TextView) findViewById(R.id.textView5)).setTypeface(tf1);

            toggleThings((TextView) findViewById(R.id.textView4),(ToggleButton) findViewById(R.id.r1tb1), (ToggleButton) findViewById(R.id.r1tb2), (ToggleButton) findViewById(R.id.r1tb3), 0);
            toggleThings((TextView) findViewById(R.id.textView5),(ToggleButton) findViewById(R.id.r2tb1), (ToggleButton) findViewById(R.id.r2tb2), 1);
            toggleThings((TextView) findViewById(R.id.textView6),(ToggleButton) findViewById(R.id.r3tb1), (ToggleButton) findViewById(R.id.r3tb2), 2);
            toggleThings((TextView) findViewById(R.id.textView7),(ToggleButton) findViewById(R.id.r4tb1), (ToggleButton) findViewById(R.id.r4tb2), 3);
            toggleThings((TextView) findViewById(R.id.textView8),(ToggleButton) findViewById(R.id.r5tb1), (ToggleButton) findViewById(R.id.r5tb2), 4);
            toggleThings((TextView) findViewById(R.id.textView9),(ToggleButton) findViewById(R.id.r6tb1), (ToggleButton) findViewById(R.id.r6tb2), 5);
            toggleThings((TextView) findViewById(R.id.textView10),(ToggleButton) findViewById(R.id.r7tb1), (ToggleButton) findViewById(R.id.r7tb2), 6);
            toggleThings((TextView) findViewById(R.id.textView11),(ToggleButton) findViewById(R.id.r8tb1), (ToggleButton) findViewById(R.id.r8tb2), 7);
            toggleThings((TextView) findViewById(R.id.textView12),(ToggleButton) findViewById(R.id.r9tb1), (ToggleButton) findViewById(R.id.r9tb2), 8);
            toggleThings((TextView) findViewById(R.id.textView13),(ToggleButton) findViewById(R.id.r10tb1), (ToggleButton) findViewById(R.id.r10tb2), 9);
            toggleThings((TextView) findViewById(R.id.textView14),(ToggleButton) findViewById(R.id.r11tb1), (ToggleButton) findViewById(R.id.r11tb2), 10);
            toggleThings((TextView) findViewById(R.id.textView15),(ToggleButton) findViewById(R.id.r12tb1), (ToggleButton) findViewById(R.id.r12tb2), 11);
            toggleThings((TextView) findViewById(R.id.textView16),(ToggleButton) findViewById(R.id.r13tb1), (ToggleButton) findViewById(R.id.r13tb2), 12);
            toggleThings((TextView) findViewById(R.id.textView17),(ToggleButton) findViewById(R.id.r14tb1), (ToggleButton) findViewById(R.id.r14tb2), 13);
            toggleThings((TextView) findViewById(R.id.textView18),(ToggleButton) findViewById(R.id.r15tb1), (ToggleButton) findViewById(R.id.r15tb2), 14);
            toggleThings((TextView) findViewById(R.id.textView19),(ToggleButton) findViewById(R.id.r16tb1), (ToggleButton) findViewById(R.id.r16tb2), 15);
            toggleThings((TextView) findViewById(R.id.textView20),(ToggleButton) findViewById(R.id.r17tb1), (ToggleButton) findViewById(R.id.r17tb2), 16);
            toggleThings((TextView) findViewById(R.id.textView21),(ToggleButton) findViewById(R.id.r18tb1), (ToggleButton) findViewById(R.id.r18tb2), 17);
            toggleThings((TextView) findViewById(R.id.textView22),(ToggleButton) findViewById(R.id.r19tb1), (ToggleButton) findViewById(R.id.r19tb2), 18);
        }
    }

    void toggleThings(final TextView txtView, final ToggleButton tb1, final ToggleButton tb2, final ToggleButton tb3, final int index) {
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

    void fillEntries(int id){
//        EditText txt_name = (EditText) findViewById(R.id.txt_name);
//        EditText txt_parent_name = (EditText) findViewById(R.id.txt_parent_name);
//        EditText txt_sibling = (EditText) findViewById(R.id.txt_sibling);
//        EditText txt_height = (EditText) findViewById(R.id.txt_height);
//        EditText txt_weight = (EditText) findViewById(R.id.txt_weight);
//        EditText father_name = (EditText) findViewById(R.id.txt_father_name);
//        EditText txt_dob = (EditText) findViewById(R.id.date_of_birth);
//        EditText txt_grade = (EditText) findViewById(R.id.txt_grade);
        DBHelper db = new DBHelper(this);
        SQLiteDatabase db1 = db.getReadableDatabase();

        String selection = "id = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor c = db1.query(
                "child_health_reporting_entry",                     // The table to query
                null,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        if (c.moveToNext()) {
            ToggleButton tb1 = ((ToggleButton) findViewById(R.id.r1tb1));
            ToggleButton tb2 = ((ToggleButton) findViewById(R.id.r1tb2));
            ToggleButton tb3 = ((ToggleButton) findViewById(R.id.r1tb3));

            if (c.getInt(c.getColumnIndex("bmi")) == 0) {
                tb1.setChecked(true);
                tb2.setChecked(false);
                tb3.setChecked(false);
            } else if (c.getInt(c.getColumnIndex("bmi")) == 1) {
                tb2.setChecked(true);
                tb1.setChecked(false);
                tb3.setChecked(false);
            } else if (c.getInt(c.getColumnIndex("bmi")) == 2) {
                tb3.setChecked(true);
                tb2.setChecked(false);
                tb1.setChecked(false);
            }

            tb1.setEnabled(false);
            tb2.setEnabled(false);
            tb3.setEnabled(false);

            setToggelThings(c,"anemia",R.id.r2tb1,R.id.r2tb2);
            setToggelThings(c,"other_physical_deformities",R.id.r3tb1,R.id.r3tb2);
            setToggelThings(c,"mental_disability",R.id.r4tb1,R.id.r4tb2);
            setToggelThings(c,"hearing_problem",R.id.r5tb1,R.id.r5tb2);
            setToggelThings(c,"other_ear_problem",R.id.r6tb1,R.id.r6tb2);
            setToggelThings(c,"vision",R.id.r7tb1,R.id.r7tb2);
            setToggelThings(c,"other_eye_problem",R.id.r8tb1,R.id.r8tb2);
            setToggelThings(c,"acute_infection",R.id.r9tb1,R.id.r9tb2);
            setToggelThings(c,"skin_problem",R.id.r10tb1,R.id.r10tb2);
            setToggelThings(c,"dental_problem",R.id.r11tb1,R.id.r11tb2);
            setToggelThings(c,"evidence_of_truma",R.id.r12tb1,R.id.r12tb2);
            setToggelThings(c,"any_other_problem_identified",R.id.r13tb1,R.id.r13tb2);
        }

        int child_id = c.getInt(c.getColumnIndex("child_id"));
        selection = "id = ?";
        String[] selectionArgs2 = {String.valueOf(child_id)};

        c = db1.query(
                "child_entry",                     // The table to query
                null,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs2,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        if (c.moveToNext()) {
//            txt_grade.setText(c.getString(c.getColumnIndex("grade")));
//            txt_grade.setEnabled(false);
//            txt_name.setText(c.getString(c.getColumnIndex("name")));
//            txt_name.setEnabled(false);
//            txt_parent_name.setText(c.getString(c.getColumnIndex("parent_name")));
//            txt_parent_name.setEnabled(false);
//            father_name.setText(c.getString(c.getColumnIndex("father_name")));
//            father_name.setEnabled(false);
//            txt_dob.setText(c.getString(c.getColumnIndex("dob")));
//            txt_dob.setEnabled(false);
//            txt_sibling.setText(c.getString(c.getColumnIndex("number_of_sibling")));
//            txt_sibling.setEnabled(false);
//            txt_height.setText(c.getString(c.getColumnIndex("height")));
//            txt_height.setEnabled(false);
//            txt_weight.setText(c.getString(c.getColumnIndex("weight")));
//            txt_weight.setEnabled(false);
            Button b = (Button) findViewById(R.id.button2);
            b.setVisibility(View.GONE);
        }
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
