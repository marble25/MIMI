package org.iptime.mascore.mimi;


import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class Signup3Fragment extends Fragment {

    TextView txtTimer, txtCheckNum, txtSendEmail;

    public Signup3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup3, container, false);

        txtTimer = (TextView) view.findViewById(R.id.textTimer);
        txtSendEmail = (TextView) view.findViewById(R.id.txtSendEmail);
        txtCheckNum = (TextView) view.findViewById(R.id.txtCheckNum);

        txtCheckNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog builder = new AlertDialog.Builder(getContext()).setMessage("인증되었습니다").show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable(){
                    public void run(){
                        builder.dismiss();
                    }
                }, 1000);
            }
        });

        return view;
    }

}
