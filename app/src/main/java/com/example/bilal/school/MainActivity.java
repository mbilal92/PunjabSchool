package com.example.bilal.school;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.mainscreen);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final int followUps = getIntent().getIntExtra("follow_ups" , 0);
        final int agent_id = getIntent().getIntExtra("agent_id", -1);
        final String userNameValue = getIntent().getStringExtra("name");
//        if (followUps == 0 ) {
//            followUpText.setText("No Follow-ups Today");
//        } else {
//            followUpText.setText(followUps + " Follow-ups Today");
//        }

        TextView userName = (TextView) findViewById(R.id.username);
        Typeface tf = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Heavy.otf");
        userName.setTypeface(tf);

        Typeface tf2 = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Light.otf");
        TextView textView30 = (TextView) findViewById(R.id.textView30);
        textView30.setTypeface(tf2);

        Typeface tf3 = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Roman.otf");
        TextView textView36 = (TextView) findViewById(R.id.textView36);
        textView36.setTypeface(tf);
        TextView textView37 = (TextView) findViewById(R.id.textView37);
        textView37.setTypeface(tf3);
        TextView textView38 = (TextView) findViewById(R.id.textView38);
        textView38.setTypeface(tf);
        TextView textView39 = (TextView) findViewById(R.id.textView39);
        textView39.setTypeface(tf3);
        TextView textView40 = (TextView) findViewById(R.id.textView40);
        textView40.setTypeface(tf);
        TextView textView41 = (TextView) findViewById(R.id.textView41);
        textView41.setTypeface(tf3);

        TextView t1 = (TextView) findViewById(R.id.txtchildscreened);
        TextView t2 = (TextView) findViewById(R.id.txthealthsession);
        TextView t3 = (TextView) findViewById(R.id.txtschoolassessed);

        DBHelper db = new DBHelper(this);
        SQLiteDatabase db1 = db.getReadableDatabase();

        long c = DatabaseUtils.queryNumEntries(db1, "child_entry",
                null, null);

        t1.setText(String.valueOf(c));
        c = DatabaseUtils.queryNumEntries(db1, "health_education_session_reporting_entry",
                null, null);

        t2.setText(String.valueOf(c));
        db1.close();

        ConstraintLayout l2 = (ConstraintLayout) findViewById(R.id.CL9);
        l2.setClickable(true);
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ReportingActivity.class);
                i.putExtra("reporting_Type", 1); //School
                i.putExtra("agent_id", agent_id); //School
                startActivity(i);
            }
        });

        ConstraintLayout l3 = (ConstraintLayout) findViewById(R.id.CL10);
        l3.setClickable(true);
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ReportingActivity.class);
                i.putExtra("reporting_Type", 2); //Community
                i.putExtra("agent_id", agent_id);
                startActivity(i);
            }
        });

//        ConstraintLayout l4 = (ConstraintLayout) findViewById(R.id.constraintLayout5);
//        l4.setClickable(true);
//        l4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, RecordActivity.class);
//                i.putExtra("agent_id", agent_id); //School
//                startActivity(i);
//                MainActivity.this.finish();
//            }
//        });
    }
}
