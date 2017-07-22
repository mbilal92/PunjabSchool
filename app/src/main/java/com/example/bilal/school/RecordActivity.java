package com.example.bilal.school;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        LinearLayout l2 = (LinearLayout) findViewById(R.id.linearLayout1);
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecordActivity.this, RecordOptionActivity.class);
                i.putExtra("reporting_Type", 1); //School
                startActivity(i);
            }
        });

        LinearLayout l3 = (LinearLayout) findViewById(R.id.linearLayout2);
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecordActivity.this, RecordOptionActivity.class);
                i.putExtra("reporting_Type", 2); //Community
                startActivity(i);
            }
        });
        Button b = (Button) findViewById(R.id.button9);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecordActivity.this, MainActivity.class);
                startActivity(i);
                RecordActivity.this.finish();
            }
        });

    }
}
