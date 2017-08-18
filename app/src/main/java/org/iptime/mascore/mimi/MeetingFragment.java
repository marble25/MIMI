package org.iptime.mascore.mimi;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeetingFragment extends Fragment {

    ListView listView;
    Button btnMale, btnFemale;
    FloatingActionButton fab;

    public MeetingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meeting, container, false);

        listView = (ListView)view.findViewById(R.id.listView);
        btnMale = (Button)view.findViewById(R.id.btnMale);
        btnFemale = (Button)view.findViewById(R.id.btnFemale);
        btnMale.setOnClickListener(BtnMaleList);
        btnFemale.setOnClickListener(BtnFemaleList);

        fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WritingActivity.class));
            }
        });

        //어답터
        MeetingListAdapter listAdapter = new MeetingListAdapter(getContext());
        listView.setAdapter(listAdapter);

        return view;
    }

    private View.OnClickListener BtnMaleList = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btnMale.setTextColor(getResources().getColor(R.color.mint));
            btnFemale.setTextColor(getResources().getColor(R.color.warm_grey_two));
            MeetingListAdapter listAdapter = new MeetingListAdapter(getContext());
            listView.setAdapter(listAdapter);
        }
    };

    private View.OnClickListener BtnFemaleList = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btnFemale.setTextColor(getResources().getColor(R.color.colorAccent));
            btnMale.setTextColor(getResources().getColor(R.color.warm_grey_two));
            MeetingListAdapter listAdapter = new MeetingListAdapter(getContext());
            listView.setAdapter(listAdapter);
        }
    };
}
