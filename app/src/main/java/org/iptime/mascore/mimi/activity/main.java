package org.iptime.mascore.mimi.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import org.iptime.mascore.mimi.R;
import org.iptime.mascore.mimi.etc.BoardItem;
import org.iptime.mascore.mimi.etc.BoardItemAdapter;
import org.iptime.mascore.mimi.global.Information;
import org.iptime.mascore.mimi.web.DatabaseController;

import java.util.ArrayList;
import java.util.HashMap;

public class main extends AppCompatActivity {

    public int boardFrom = 0;
    public boolean isSecondTabGenderMen = true;
    public boolean isSecondTabVisited = false;

    public BoardItemAdapter adapter = new BoardItemAdapter();
    public ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup inclusionViewGroup = (ViewGroup)findViewById(R.id.layoutMainFrame);
        View join = LayoutInflater.from(main.this).inflate(
                R.layout.tab_main_home, null);
        inclusionViewGroup.addView(join);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabMainHost);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ViewGroup inclusionViewGroup = (ViewGroup)findViewById(R.id.layoutMainFrame);
                inclusionViewGroup.removeAllViews();
                if(tab.getPosition() == 0) {
                    View join = LayoutInflater.from(main.this).inflate(
                            R.layout.tab_main_home, null);
                    inclusionViewGroup.addView(join);
                } else if(tab.getPosition() == 1) {
                    View join = LayoutInflater.from(main.this).inflate(
                            R.layout.tab_main_board, null);
                    inclusionViewGroup.addView(join);

                    if(!isSecondTabVisited) {
                        isSecondTabVisited = true;
                        initializeTab();
                    }
                    addTab();
                } else if(tab.getPosition() == 2) {
                    View join = LayoutInflater.from(main.this).inflate(
                            R.layout.tab_main_drink, null);
                    inclusionViewGroup.addView(join);
                } else if(tab.getPosition() == 3) {
                    View join = LayoutInflater.from(main.this).inflate(
                            R.layout.tab_main_personal, null);
                    inclusionViewGroup.addView(join);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void initializeTab() {
        TabLayout tabLayoutGender = (TabLayout)findViewById(R.id.tabBoardGender);
        ListView listView = (ListView) findViewById(R.id.listBoard);
        adapter = new BoardItemAdapter();

//        adapter.addItem(ContextCompat.getDrawable(main.this, R.drawable.edit), "title", "type",
//                "detail", ContextCompat.getDrawable(main.this, R.drawable.edit));

        tabLayoutGender.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ListView listView = (ListView) findViewById(R.id.listBoard);

                isSecondTabGenderMen = !isSecondTabGenderMen;
                adapter = new BoardItemAdapter();

                arrayList.clear();
                boardFrom = 0;

                addTab();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public void addTab() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseController databaseController = new DatabaseController();
                String result = databaseController.getBoard(isSecondTabGenderMen, 10, boardFrom);

                if(result.isEmpty()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(main.this, "서버 오류로 접속할 수 없습니다", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    arrayList.addAll(Information.parseBoard(main.this, result));

                    for(int i = 0;i<arrayList.size() - boardFrom;i++) {
                        HashMap<String, String> element = arrayList.get(i + boardFrom);
                        adapter.addItem(ContextCompat.getDrawable(main.this, R.drawable.edit), element.get("title"), element.get("type"),
                                element.get("detail"), ContextCompat.getDrawable(main.this, R.drawable.edit));

                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ListView listView = (ListView) findViewById(R.id.listBoard);
                            listView.setAdapter(adapter);
                        }
                    });

                    boardFrom += 10;
                }


            }
        }).start();

    }
    public void onFloatingButtonClicked(View view) {
        Intent intent = new Intent(this, write.class);
        startActivity(intent);
    }
}
