package org.iptime.mascore.mimi.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.iptime.mascore.mimi.R;
import org.iptime.mascore.mimi.db.AutoLogin;
import org.iptime.mascore.mimi.global.Information;
import org.iptime.mascore.mimi.global.Secrets;
import org.iptime.mascore.mimi.web.DatabaseController;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    public String id, pw;
    public boolean isAutoLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(!Information.isJsonSet()) {
            Information.parseJSON(getApplicationContext());
        }


        SharedPreferences sp = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        isAutoLogin = sp.getBoolean("auto", false);
        if(isAutoLogin) {
            id = sp.getString("id", "");
            pw = sp.getString("pw", "");
            login(0);
        }
    }
    public void login(final int status) {
        final Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map.put("password", pw);

        Secrets.id = id;

        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseController dbController = new DatabaseController();
                final int result = dbController.login(map);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(result == 0) {
                            Toast.makeText(login.this, "로그인 성공", Toast.LENGTH_SHORT).show();

                            if(isAutoLogin && status == 1) {
                                AutoLogin autoLogin = new AutoLogin();
                                autoLogin.setAuto(login.this, id, pw);
                            }
                            Intent intent = new Intent(login.this, main.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } else if(result == 1) {
                            Toast.makeText(login.this, "네트워크 연결을 확인해보세요", Toast.LENGTH_SHORT).show();
                        } else if(result == 2) {
                            Toast.makeText(login.this, "네트워크 연결을 확인해보세요", Toast.LENGTH_SHORT).show();
                        } else if(result == 3) {
                            Toast.makeText(login.this, "아이디나 비밀번호를 확인해보세요", Toast.LENGTH_SHORT).show();
                        } else if(result == 4) {
                            Toast.makeText(login.this, "이메일 인증을 완료해 주세요", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }).start();
    }
    public void onLoginClicked(View view) {
        EditText inputId = (EditText)findViewById(R.id.editLoginUsername);
        EditText inputPw = (EditText)findViewById(R.id.editLoginPassword);
        CheckBox loginAuto = (CheckBox)findViewById(R.id.checkLoginAuto);

        isAutoLogin = loginAuto.isChecked();
        id = inputId.getText().toString();
        pw = inputPw.getText().toString();

        login(1);
    }
    public void onJoinClicked(View view) {
        Intent intent = new Intent(this, join.class);
        startActivity(intent);
    }
}
