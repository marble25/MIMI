package org.iptime.mascore.mimi.db;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Owner on 2017-08-03.
 */

public class AutoLogin {

    public void setAuto(Context context, String id, String pw) {
        SharedPreferences sp = context.getSharedPreferences("auto", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putBoolean("auto", true);
        editor.putString("id", id);
        editor.putString("pw", pw);
        editor.apply();
    }
}
