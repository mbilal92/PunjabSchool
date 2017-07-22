package com.example.bilal.school;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ReportingActivity extends AppCompatActivity {

    private SearchView mSearchView;
    private ListView mListView;
    private CustomAdapter mAdapter;
    private  String[] mStrings ;
    private int[] mintId ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = this.getIntent();
        final int type = i.getIntExtra("reporting_Type", -1);
        final int agent_id = getIntent().getIntExtra("agent_id", -1);
        ConstraintLayout l1 = (ConstraintLayout) findViewById(R.id.constraintLayout4);
        ImageView img = (ImageView) findViewById(R.id.imageView7);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportingActivity.this.finish();
            }
        });

        TextView headingText = (TextView) findViewById(R.id.username);
        Typeface tf = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Heavy.otf");
        headingText.setTypeface(tf);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Light.otf");
        TextView textView30 = (TextView) findViewById(R.id.textView30);
        textView30.setTypeface(tf2);
        Typeface tf3 = Typeface.createFromAsset(getAssets(), "AvenirLTStd-Roman.otf");

//        TextView nearyouText = (TextView) findViewById(R.id.textView_nearyou);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Nearby"));
        tabLayout.addTab(tabLayout.newTab().setText("Recent"));

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), type, agent_id));
        tabLayout.setupWithViewPager(viewPager);
        TextView nearyouText = (TextView) findViewById(R.id.textView_nearyou);
        nearyouText.setTypeface(tf3);
//
//        mSearchView = (SearchView) findViewById(R.id.searchView);
//        mListView = (ListView) findViewById(R.id.listview1);
        if (type == 1) {
            headingText.setText("School Reporting");
            nearyouText.setText("School near you");
            l1.setBackgroundResource(R.drawable.school_gradiane);
            tabLayout.setBackgroundResource(R.drawable.school_gradiane);
//            mStrings = new String[] {"DPS", "City School"};
//            mintId = new int[] {1, 2};
        } else {
            headingText.setText("Community Reporting");
            nearyouText.setText("Community centers near you");
            l1.setBackgroundResource(R.drawable.community_gradiane);
            tabLayout.setBackgroundResource(R.drawable.community_gradiane);
//            mStrings = new String[] {"Model Town"};
//            mintId = new int[] {1};
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent i = new Intent(ReportingActivity.this, SearchChool.class);
                i.putExtra("reporting_Type", type);
                i.putExtra("agent_id", agent_id);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_up, R.anim.stay);
            }
        });


//        mAdapter = new CustomAdapter(mStrings, this);
//        mListView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
//        mListView.setAdapter(mAdapter);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        mListView.setLayoutManager(mLayoutManager);
//        mListView.setItemAnimator(new DefaultItemAnimator());//        mListView.setTextFilterEnabled(true);

///        setupSearchView();
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String value = (String) mAdapter.getItem(position);
//                Intent i = new Intent(ReportingActivity.this, ReportingOptionsActivity.class);
//                i.putExtra("name", value);
//                i.putExtra("reporting_Type", type);
//                i.putExtra("place_id", mintId[position]);
//                i.putExtra("agent_id", agent_id);
//                startActivity(i);
//            }
//        });
//
    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
//                    mListView.clearTextFilter();
                    mAdapter.getFilter().filter(null);
                } else {
//                    mListView.setFilterText(newText.toString());
                    mAdapter.getFilter().filter(newText.toString());
                }
                return true;            }
        });
        mSearchView.setSubmitButtonEnabled(false);
        mSearchView.setQueryHint("Search");
    }
}
