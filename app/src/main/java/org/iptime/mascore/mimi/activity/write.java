package org.iptime.mascore.mimi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.iptime.mascore.mimi.R;
import org.iptime.mascore.mimi.global.Information;
import org.iptime.mascore.mimi.global.Secrets;
import org.iptime.mascore.mimi.web.DatabaseController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class write extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        Spinner spinner = (Spinner)findViewById(R.id.spinnerWriteType);
        spinner.setPrompt("학번");

        ArrayList<String> arrayList = new ArrayList<>();
        for(int i=1;i<=8;i++) {
            arrayList.add(i + ":" + i);
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrayList);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup inclusionViewGroup = (ViewGroup)findViewById(R.id.layoutWriteEntry);
                inclusionViewGroup.removeAllViews();

                for(int i=0;i<position;i++) {
                    final View entry = LayoutInflater.from(write.this).inflate(
                            R.layout.entry, null);

                    Spinner univ = (Spinner)entry.findViewById(R.id.spinnerEntryUniv);
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll(Information.univList.values());
                    Collections.sort(arrayList);
                    arrayList.add(0, "학교");

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(write.this, R.layout.support_simple_spinner_dropdown_item, arrayList);
                    univ.setAdapter(adapter);
                    univ.setPrompt("학교");

                    univ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedItem = parent.getItemAtPosition(position).toString();
                            if(selectedItem.equals("학교")) {
                                return;
                            }
                            Integer indexes = Information.univReversedList.get(selectedItem);

                            Spinner major = (Spinner)entry.findViewById(R.id.spinnerEntryMajor);
                            ArrayList arrayList = new ArrayList();
                            for(int index : Information.conList.get(indexes)) {
                                arrayList.add(Information.majorList.get(index));
                            }
                            Collections.sort(arrayList);
                            arrayList.add(0, "학과");

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(write.this, R.layout.support_simple_spinner_dropdown_item, arrayList);
                            major.setAdapter(adapter);
                            major.setPrompt("학과");
                        }
                        public void onNothingSelected(AdapterView<?> parent)
                        {

                        }
                    });

                    inclusionViewGroup.addView(entry);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onSubmitButtonClicked(View view) {
        EditText editSubject = (EditText)findViewById(R.id.editWriteSubject);
        Spinner spinnerType = (Spinner)findViewById(R.id.spinnerWriteType);
        EditText editDetail = (EditText)findViewById(R.id.editWriteDetail);
        String groupString = "";

        if(editSubject.getText().toString().isEmpty()) {
            Toast.makeText(this, "제목을 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        ViewGroup inclusionViewGroup = (ViewGroup)findViewById(R.id.layoutWriteEntry);
        for(int i=0;i<inclusionViewGroup.getChildCount();i++) {
            View view1 = inclusionViewGroup.getChildAt(i);
            Spinner spinner0 = (Spinner)view1.findViewById(R.id.spinnerEntryNum);
            Spinner spinner1 = (Spinner)view1.findViewById(R.id.spinnerEntryUniv);
            Spinner spinner2 = (Spinner)view1.findViewById(R.id.spinnerEntryMajor);
            if(spinner0.getSelectedItem().toString().equals("학번") ||
                    spinner1.getSelectedItem().toString().equals("대학") ||
                    spinner2.getSelectedItem().toString().equals("학과")) {
                Toast.makeText(this, "필드 선택을 완료해주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            groupString += spinner0.getSelectedItem().toString().replace("학번", "") + "/" +
                    spinner1.getSelectedItem().toString() + "/" +
                    spinner2.getSelectedItem().toString() + ",";

        }

        final Map<String, String> map = new HashMap<>();
        map.put("id", Secrets.id);
        map.put("type", spinnerType.getSelectedItem().toString());
        map.put("title", editSubject.getText().toString());
        map.put("detail", editDetail.getText().toString());
        map.put("group", groupString);
        System.out.println(groupString);

        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseController databaseController = new DatabaseController();
                final int result = databaseController.insertBoard(map);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(result == 0) {
                            Toast.makeText(write.this, "글쓰기에 성공했습니다", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(write.this, main.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(write.this, "네트워크를 확인해보세요", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }).start();

    }
}
