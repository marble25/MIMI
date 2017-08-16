package org.iptime.mascore.mimi;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Signup2Fragment extends Fragment {

    CheckBox chkMale, chkFemale;

    public Signup2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup2, container, false);

        chkMale = (CheckBox) view.findViewById(R.id.radioButtonMale);
        chkFemale = (CheckBox) view.findViewById(R.id.radioButtonFemale);

        chkMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true) {
                    chkMale.setTextColor(Color.WHITE);
                } else {
                    chkMale.setTextColor(Color.DKGRAY);
                }
            }
        });

        chkFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true) {
                    chkFemale.setTextColor(Color.WHITE);
                } else {
                    chkFemale.setTextColor(Color.DKGRAY);
                }
            }
        });

        return view;
    }

}
