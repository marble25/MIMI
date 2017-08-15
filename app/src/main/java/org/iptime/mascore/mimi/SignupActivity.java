package org.iptime.mascore.mimi;

import android.content.Intent;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.relex.circleindicator.CircleIndicator;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        SignPagerAdapter signPagerAdapter = new SignPagerAdapter(getSupportFragmentManager());

        ViewPager viewpager = (ViewPager)findViewById(R.id.viewPager);
        CircleIndicator indicator = (CircleIndicator)findViewById(R.id.indicator);

        viewpager.setAdapter(signPagerAdapter);
        indicator.setViewPager(viewpager);
    }
}
