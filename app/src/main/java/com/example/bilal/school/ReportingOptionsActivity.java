package com.example.bilal.school;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ReportingOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting_options);
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView nameTextView = (TextView) findViewById(R.id.username);
        final String name = getIntent().getStringExtra("name");
        final int type = getIntent().getIntExtra("reporting_Type", -1);
        final int place_id = getIntent().getIntExtra("place_id", -1);
        final int agent_id = getIntent().getIntExtra("agent_id", -1);
        nameTextView.setText(name);

        TextView txtchildscreened = (TextView) findViewById(R.id.txtchildscreened);
        TextView txthealthsession = (TextView) findViewById(R.id.txthealthsession);
        TextView txtschoolassessed = (TextView) findViewById(R.id.txtschoolassessed);
        ImageView img = (ImageView) findViewById(R.id.imageView7);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportingOptionsActivity.this.finish();
            }
        });

        TextView userName = (TextView) findViewById(R.id.username);
        Typeface tf = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Heavy.otf");
        userName.setTypeface(tf);

        Typeface tf3 = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Roman.otf");

        Typeface tf2 = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Light.otf");
        TextView textView30 = (TextView) findViewById(R.id.textView30);
        textView30.setTypeface(tf2);
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

        DBHelper db = new DBHelper(this);
        SQLiteDatabase db1 = db.getReadableDatabase();
        String selection = "place_id = ?";
        String[] selectionArgs = {String.valueOf(place_id)};

        long c = DatabaseUtils.queryNumEntries(db1, "child_entry",
                "place_id=?", new String[] {String.valueOf(place_id)});

        txtchildscreened.setText(String.valueOf(c));
        c = DatabaseUtils.queryNumEntries(db1, "health_education_session_reporting_entry",
                "place_id=?", new String[] {String.valueOf(place_id)});

        txthealthsession.setText(String.valueOf(c));
        db1.close();
        ConstraintLayout main = (ConstraintLayout) findViewById(R.id.constraintLayout4);
        ConstraintLayout submain = (ConstraintLayout) findViewById(R.id.constraintLayout5);
        ConstraintLayout l1 = (ConstraintLayout) findViewById(R.id.CL9);
        if (type==1) {
            main.setBackgroundResource(R.drawable.school_gradiane);
            submain.setBackgroundResource(R.drawable.school_gradiane);
            l1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ReportingOptionsActivity.this, EnvironmentAssesmentActivity.class);
                    i.putExtra("name", name);
                    i.putExtra("place_id", place_id);
                    i.putExtra("agent_id", agent_id);
                    startActivity(i);
                }
            });
        } else {
            l1.setVisibility(View.GONE);
            main.setBackgroundResource(R.drawable.community_gradiane);
            submain.setBackgroundResource(R.drawable.community_gradiane);
        }

        ConstraintLayout l2 = (ConstraintLayout) findViewById(R.id.CL10);
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReportingOptionsActivity.this, ChildHealthScreeningActivity.class);
                i.putExtra("name", name);
                i.putExtra("reporting_Type", type);
                i.putExtra("place_id", place_id);
                i.putExtra("agent_id", agent_id);
                startActivity(i);
            }
        });

        ConstraintLayout l3 = (ConstraintLayout) findViewById(R.id.CL11);
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReportingOptionsActivity.this, HealthEducationSessionActivity.class);
                i.putExtra("name", name);
                i.putExtra("reporting_Type", type);
                i.putExtra("place_id", place_id);
                i.putExtra("agent_id", agent_id);
                startActivity(i);
            }
        });
    }
}
