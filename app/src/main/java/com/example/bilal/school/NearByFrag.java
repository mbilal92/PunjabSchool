package com.example.bilal.school;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Bilal on 5/28/2017.
 */

public class NearByFrag extends Fragment {
    ListView mListView;
    private CustomAdapter mAdapter;
    private  String[] mStrings ;
    private int[] mintId ;
    int type;
    int agent_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the view from fragmenttab1.xml
        View view = inflater.inflate(R.layout.fragview, container, false);
        mListView = (ListView) view.findViewById(R.id.listview1);
        type = this.getArguments().getInt("type");
        agent_id = this.getArguments().getInt("agent_id");
        if (type == 1) {
            mStrings = new String[]{"DPS", "City School"};
            mintId = new int[]{1, 2};
        } else {
            mStrings = new String[] {"Model Town"};
            mintId = new int[] {1};
        }

        mAdapter = new CustomAdapter(mStrings, this.getContext());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String) mAdapter.getItem(position);
                Intent i = new Intent(NearByFrag.this.getContext(), ReportingOptionsActivity.class);
                i.putExtra("name", value);
                i.putExtra("reporting_Type", type);
                i.putExtra("place_id", mintId[position]);
                i.putExtra("agent_id", agent_id);
                startActivity(i);
            }
        });

        return view;
    }
}
