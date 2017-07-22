package com.example.bilal.school;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChildHealthScreeningActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_health_screening1);
        TextView nameTextView = (TextView) findViewById(R.id.username);
        final String name = getIntent().getStringExtra("name");
        final int type = getIntent().getIntExtra("reporting_Type", -1);
        final int place_id = getIntent().getIntExtra("place_id", -1);
        final int agent_id = getIntent().getIntExtra("agent_id", -1);
        final long child_id = getIntent().getLongExtra("child_id", -1);
        nameTextView.setText(name);

        TextView txtchildscreened = (TextView) findViewById(R.id.txtchildscreened);
        TextView txthealthsession = (TextView) findViewById(R.id.txthealthsession);
        TextView txtschoolassessed = (TextView) findViewById(R.id.txtschoolassessed);
        ImageView img = (ImageView) findViewById(R.id.imageView7);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChildHealthScreeningActivity1.this.finish();
            }
        });

        TextView userName = (TextView) findViewById(R.id.username);
        Typeface tf = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Heavy.otf");
        userName.setTypeface(tf);

        Typeface tf2 = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Light.otf");
        TextView textView30 = (TextView) findViewById(R.id.textView42);
        textView30.setTypeface(tf2, Typeface.BOLD);

        DBHelper db = new DBHelper(this);
        SQLiteDatabase db1 = db.getReadableDatabase();

        long c = DatabaseUtils.queryNumEntries(db1, "child_entry",
                "place_id=?", new String[] {String.valueOf(place_id)});

        txtchildscreened.setText(String.valueOf(c));
        c = DatabaseUtils.queryNumEntries(db1, "health_education_session_reporting_entry",
                "place_id=?", new String[] {String.valueOf(place_id)});

        txthealthsession.setText(String.valueOf(c));
        String[] projection = {
                "id",
                "name",
                "father_name",
                "parent_name",
                "dob",
                "male"
        };

        String selection = "id = ? ";
        String[] selectionArgs = { String.valueOf(child_id)};

        Cursor c2 = db1.query(
                "child_entry",                     // The table to query
                null,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        if (c2.moveToNext()) {

            ((TextView) findViewById(R.id.textView2)).setText(c2.getString(c2.getColumnIndex("name")));
            ((TextView) findViewById(R.id.textView4)).setText(c2.getString(c2.getColumnIndex("father_name")));
            ((TextView) findViewById(R.id.textView6)).setText(c2.getString(c2.getColumnIndex("parent_name")));
            ((TextView) findViewById(R.id.textView8)).setText(c2.getString(c2.getColumnIndex("dob")));

            if (c2.getInt(c2.getColumnIndex("male")) == 1) {
                ((TextView) findViewById(R.id.textView10)).setText("Male");
            } else {
                ((TextView) findViewById(R.id.textView10)).setText("Female");
            }
        }


        db1.close();
        ConstraintLayout main = (ConstraintLayout) findViewById(R.id.constraintLayout4);
        ConstraintLayout submain = (ConstraintLayout) findViewById(R.id.constraintLayout5);
        if (type==1) {
            main.setBackgroundColor(Color.parseColor("#5E78CB"));
            submain.setBackgroundResource(R.drawable.school_gradiane);
        } else {
            main.setBackgroundResource(R.drawable.community_gradiane);
            submain.setBackgroundResource(R.drawable.community_gradiane);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChildHealthScreeningActivity1.this, ChildHealthScreeningActivity2.class);
                i.putExtra("name", name);
                i.putExtra("reporting_Type", type);
                i.putExtra("child_id", child_id);
                i.putExtra("place_id", place_id);
                i.putExtra("place_server_id", -1);
                i.putExtra("agent_id", agent_id);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_up, R.anim.stay);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
