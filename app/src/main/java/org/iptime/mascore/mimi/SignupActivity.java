package org.iptime.mascore.mimi;

import android.content.Intent;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.relex.circleindicator.CircleIndicator;

public class SignupActivity extends AppCompatActivity {

    Button mBtnPre, mBtnNext;
    CustomViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        SignPagerAdapter signPagerAdapter = new SignPagerAdapter(getSupportFragmentManager());

        viewPager = (CustomViewPager)findViewById(R.id.viewPager);
        CircleIndicator indicator = (CircleIndicator)findViewById(R.id.indicator);

        viewPager.setAdapter(signPagerAdapter);
        indicator.setViewPager(viewPager);

        mBtnPre = (Button)findViewById(R.id.btnPrevious);
        mBtnNext = (Button)findViewById(R.id.btnNext);

        mBtnPre.setOnClickListener(btnPreClick);
        mBtnNext.setOnClickListener(btnNextClick);
    }

    private View.OnClickListener btnNextClick =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (viewPager.getCurrentItem()) {
                case 0:
                    viewPager.setCurrentItem(1);
                    break;
                case 1:
                    viewPager.setCurrentItem(2);
                    break;
                case 2:
                    viewPager.setCurrentItem(3);
                    break;
            }
        }
    };

    private View.OnClickListener btnPreClick =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (viewPager.getCurrentItem()) {
                case 1:
                    viewPager.setCurrentItem(0);
                    break;
                case 2:
                    viewPager.setCurrentItem(1);
                    break;
                case 3:
                    viewPager.setCurrentItem(2);
                    break;
            }
        }
    };
}
