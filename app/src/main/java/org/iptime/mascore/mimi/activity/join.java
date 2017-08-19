package org.iptime.mascore.mimi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.iptime.mascore.mimi.R;
import org.iptime.mascore.mimi.global.Information;
import org.iptime.mascore.mimi.web.DatabaseController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * join 액티비티
 *
 * 회원가입 1, 2, 3, 4 창 모두 이 액티비티에서 처리
 */
public class join extends AppCompatActivity {

    // 회원가입 창이 넘어갈 때마다 전역 변수에 저장

    public String id = "";
    public String pw = "";
    public String name = "";
    public int gender = -1;
    public int num = 0;
    public String univ = "";
    public String major = "";
    public String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // activity_join_1을 빈 공간에 채워넣음음
        ViewGroup inclusionViewGroup = (ViewGroup)findViewById(R.id.layoutJoin);

        View join1 = LayoutInflater.from(this).inflate(
                R.layout.activity_join_1, null);
        inclusionViewGroup.addView(join1);

    }

    public void onNext1ButtonClicked(View view) {
        EditText inputId = (EditText)findViewById(R.id.editJoinUsername);
        EditText inputPw = (EditText)findViewById(R.id.editJoinPassword);
        EditText inputPwCheck = (EditText)findViewById(R.id.editJoinPasswordCheck);

        if(inputId.getText().toString().isEmpty()) {
            Toast.makeText(join.this, "ID를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        if(inputPw.getText().toString().isEmpty()) {
            Toast.makeText(join.this, "PW를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        if(inputPwCheck.getText().toString().isEmpty()) {
            Toast.makeText(join.this, "PW 확인을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!inputPw.getText().toString().equals(inputPwCheck.getText().toString())) {
            Toast.makeText(join.this, "PW와 PW 확인이 같은지 확인해보세요", Toast.LENGTH_SHORT).show();
            return;
        }

        id = inputId.getText().toString();
        pw = inputPw.getText().toString();

        final Map<String, String> map = new HashMap<String, String>();
        map.put("id", inputId.getText().toString());

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseController dbController = new DatabaseController();
                final int result = dbController.checkID(map);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(result == 0) {
                            Toast.makeText(join.this, "아이디 체크 성공", Toast.LENGTH_SHORT).show();

                            ViewGroup inclusionViewGroup = (ViewGroup)findViewById(R.id.layoutJoin);

                            View join2 = LayoutInflater.from(join.this).inflate(
                                    R.layout.activity_join_2, null);
                            inclusionViewGroup.removeAllViews();
                            inclusionViewGroup.addView(join2);

                        } else if(result == 1) {
                            Toast.makeText(join.this, "네트워크 연결을 확인해보세요", Toast.LENGTH_SHORT).show();
                        } else if(result == 2) {
                            Toast.makeText(join.this, "동일한 아이디가 있습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        t.start();
    }
    public void onNext2ButtonClicked(View view) {
        EditText inputName = (EditText)findViewById(R.id.editJoinName);
        RadioButton radioMale = (RadioButton)findViewById(R.id.radioJoinMale);
        RadioButton radioFemale = (RadioButton)findViewById(R.id.radioJoinFemale);
        Spinner spinner = (Spinner)findViewById(R.id.spinnerJoinNum);

        if(inputName.getText().toString().isEmpty()) {
            Toast.makeText(join.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!radioMale.isChecked() && !radioFemale.isChecked()) {
            Toast.makeText(join.this, "성별을 선택해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        if(spinner.getSelectedItem().toString().equals("학번")) {
            Toast.makeText(join.this, "학번을 선택해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        name = inputName.getText().toString();
        gender = (radioMale.isChecked() ? 0 : 1);
        num = Integer.parseInt(spinner.getSelectedItem().toString().replace("학번", ""));

        ViewGroup inclusionViewGroup = (ViewGroup)findViewById(R.id.layoutJoin);

        View join3 = LayoutInflater.from(join.this).inflate(
                R.layout.activity_join_3, null);
        inclusionViewGroup.removeAllViews();
        inclusionViewGroup.addView(join3);

        Spinner univ = (Spinner)findViewById(R.id.spinnerJoinUniv);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Information.univList.values());
        Collections.sort(arrayList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrayList);
        univ.setAdapter(adapter);

        univ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                Integer indexes = Information.univReversedList.get(selectedItem);

                Spinner major = (Spinner)findViewById(R.id.spinnerJoinMajor);
                ArrayList arrayList = new ArrayList();
                for(int index : Information.conList.get(indexes)) {
                    arrayList.add(Information.majorList.get(index));
                }
                Collections.sort(arrayList);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(join.this, R.layout.support_simple_spinner_dropdown_item, arrayList);
                major.setAdapter(adapter);

                TextView textView = (TextView)findViewById(R.id.textJoinMail);
                textView.setText("@" + Information.mailList.get(indexes));
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }
    public void onNext3ButtonClicked(View view) {
        Spinner spinnerUniv = (Spinner)findViewById(R.id.spinnerJoinUniv);
        Spinner spinnerMajor = (Spinner)findViewById(R.id.spinnerJoinMajor);
        EditText editEmail = (EditText)findViewById(R.id.editJoinEmail);
        TextView textEmail = (TextView)findViewById(R.id.textJoinMail);

        if(editEmail.getText().toString().isEmpty()) {
            Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        email = editEmail.getText().toString() + textEmail.getText().toString();
        System.out.println("Email : " + email);
        univ = spinnerUniv.getSelectedItem().toString();
        major = spinnerMajor.getSelectedItem().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseController databaseController = new DatabaseController();

                Map<String, String> mapEmail = new HashMap<>();
                mapEmail.put("email", email);

                final int result = databaseController.checkEmail(mapEmail);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(result == 0) {
                            Toast.makeText(join.this, "성공", Toast.LENGTH_SHORT).show();
                        } else if(result == 1) {
                            Toast.makeText(join.this, "네트워크를 확인해보세요", Toast.LENGTH_SHORT).show();
                        } else if(result == 2) {
                            Toast.makeText(join.this, "같은 이메일이 있습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                if(result != 0) {
                    return;
                }

                Map<String, String> map = new HashMap<>();
                map.put("id", id);
                map.put("password", pw);
                map.put("gender", String.valueOf(gender));
                map.put("name", name);
                map.put("university", univ);
                map.put("major", major);
                map.put("num", String.valueOf(num));
                map.put("email", email);

                final int finalResult = databaseController.insertUser(map);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(finalResult == 0) {
                            Toast.makeText(join.this, "회원가입에 성공했습니다. 이메일로 인증을 완료해주세요", Toast.LENGTH_SHORT).show();
                        } else if(finalResult == 1) {
                            Toast.makeText(join.this, "네트워크를 확인해보세요", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                if(finalResult != 0) {
                    return;
                }

                databaseController.email(mapEmail);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ViewGroup inclusionViewGroup = (ViewGroup)findViewById(R.id.layoutJoin);

                        View join4 = LayoutInflater.from(join.this).inflate(
                                R.layout.activity_join_4, null);
                        inclusionViewGroup.removeAllViews();
                        inclusionViewGroup.addView(join4);
                    }
                });
            }
        }).start();
    }
    public void onNext4ButtonClicked(View view) {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
    public void onPrev2ButtonClicked(View view) {
        ViewGroup inclusionViewGroup = (ViewGroup)findViewById(R.id.layoutJoin);

        View join1 = LayoutInflater.from(this).inflate(
                R.layout.activity_join_1, null);
        inclusionViewGroup.addView(join1);
    }
    public void onPrev3ButtonClicked(View view) {
        ViewGroup inclusionViewGroup = (ViewGroup)findViewById(R.id.layoutJoin);

        View join2 = LayoutInflater.from(this).inflate(
                R.layout.activity_join_2, null);
        inclusionViewGroup.addView(join2);
    }
}
