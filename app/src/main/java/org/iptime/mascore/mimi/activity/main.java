package org.iptime.mascore.mimi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.iptime.mascore.mimi.R;

public class main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onFloatingButtonClicked(View view) {
        Intent intent = new Intent(this, write.class);
        startActivity(intent);
    }
}
