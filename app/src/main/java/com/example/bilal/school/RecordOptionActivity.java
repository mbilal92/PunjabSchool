package com.example.bilal.school;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class RecordOptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_type);
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView nameTextView = (TextView) findViewById(R.id.textView_name);

        final int type = getIntent().getIntExtra("reporting_Type", -1);

        ConstraintLayout l1 = (ConstraintLayout) findViewById(R.id.constraintLayout1);
        View v = findViewById(R.id.view12);

        if (type==1) {
            l1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(RecordOptionActivity.this, ListDownReportingActivity.class);
                    i.putExtra("reporting_Type", type);
                    i.putExtra("record_type", 1);
                    startActivity(i);
                }
            });
            nameTextView.setText("School Reporting");
        } else {
            l1.setVisibility(View.GONE);
            v.setVisibility(View.GONE);
            nameTextView.setText("Community Reporting");
        }

        ConstraintLayout l2 = (ConstraintLayout) findViewById(R.id.constraintLayout2);
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecordOptionActivity.this, ListDownReportingActivity.class);
                i.putExtra("reporting_Type", type);
                i.putExtra("record_type", 2);
                startActivity(i);
            }
        });

        ConstraintLayout l3 = (ConstraintLayout) findViewById(R.id.constraintLayout3);
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecordOptionActivity.this, ListDownReportingActivity.class);
                i.putExtra("reporting_Type", type);
                i.putExtra("record_type", 3);
                startActivity(i);
            }
        });
    }

}
