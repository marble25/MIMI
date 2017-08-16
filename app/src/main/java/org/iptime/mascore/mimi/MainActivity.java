package org.iptime.mascore.mimi;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //탭 설정
        tabLayout = (TabLayout) findViewById(R.id.tabBar);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icon_sign_warning)); //홈 아이콘
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icon_sign_warning)); //미팅 아이콘
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icon_sign_warning)); //술집 아이콘
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.icon_sign_warning)); //마이페이지 아이콘
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //뷰페이저
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(mainPagerAdapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
