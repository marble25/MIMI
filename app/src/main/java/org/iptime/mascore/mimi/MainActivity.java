package org.iptime.mascore.mimi;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    LinearLayout titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleBar = (LinearLayout)findViewById(R.id.titleBar);

        //탭 설정
        tabLayout = (TabLayout) findViewById(R.id.tabBar);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.menu_home)); //홈 아이콘
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.menu_meeting)); //미팅 아이콘
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.menu_loca)); //술집 아이콘
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.menu_mypage)); //마이페이지 아이콘
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        //뷰페이저
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(mainPagerAdapter);

        //처음 실행 탭
        viewPager.setCurrentItem(0);
        tabLayout.setSelected(true);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tabLayout.setSelectedTabIndicatorHeight(6);
                TabAttr(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TabInit(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    //탭스트립 색상 변경
    public void TabAttr(TabLayout.Tab tab) {
        switch (tabLayout.getSelectedTabPosition()) {
            case 0:
                tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.indicator_1));
                tab.setIcon(R.drawable.menu_home_click);
                titleBar.setElevation(12);
                break;
            case 1:
                tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.indicator_2));
                tab.setIcon(R.drawable.menu_meeting_click);
                titleBar.setElevation(0);
                break;
            case 2:
                tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.indicator_3));
                tab.setIcon(R.drawable.menu_loca_click);
                titleBar.setElevation(12);
                break;
            case 3:
                tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.indicator_4));
                tab.setIcon(R.drawable.menu_mypage_click);
                titleBar.setElevation(12);
                break;
        }
    }

    public void TabInit(TabLayout.Tab tab) {
        switch (tabLayout.getSelectedTabPosition()) {
            case 0:
                tab.setIcon(R.drawable.menu_home);
                titleBar.setElevation(12);
                break;
            case 1:
                tab.setIcon(R.drawable.menu_meeting);
                titleBar.setElevation(0);
                break;
            case 2:
                tab.setIcon(R.drawable.menu_loca);
                titleBar.setElevation(12);
                break;
            case 3:
                tab.setIcon(R.drawable.menu_mypage);
                titleBar.setElevation(12);
                break;
        }
    }

    //종료확인 여부 메서드
    public void onBackPressed() {
        AlertDialog.Builder d = new AlertDialog.Builder(MainActivity.this);
        d.setMessage("정말로 종료하시겠습니까?");
        d.setPositiveButton("예", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        d.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        d.show();
    }
}
