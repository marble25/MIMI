package org.iptime.mascore.mimi.global;

import android.content.Context;

import org.iptime.mascore.mimi.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Owner on 2017-08-03.
 */

public class Information {
    public static String id;

    public static String jsonUniv;
    public static String jsonMajor;
    public static String jsonCon;
    public static String jsonMail;

    public static HashMap<Integer, String> univList = new HashMap<Integer, String>();
    public static HashMap<String, Integer> univReversedList = new HashMap<String, Integer>();
    public static HashMap<Integer, String> majorList = new HashMap<Integer, String>();
    public static HashMap<String, Integer> majorReversedList = new HashMap<String, Integer>();
    public static HashMap<Integer, ArrayList<Integer>> conList = new HashMap<>();
    public static HashMap<Integer, String> mailList = new HashMap<>();

    public static boolean isJsonSet() {
        if(univList.isEmpty()) {
            return false;
        }
        return true;
    }

    public static String loadJSONFromAsset(Context context, String filename) {
        String json = null;
        try {
            InputStream is = null;
            if(filename.equals("university")) {
                is = context.getResources().openRawResource(R.raw.university);
            } else if(filename.equals("major")) {
                is = context.getResources().openRawResource(R.raw.universitymajor);
            } else if(filename.equals("con")) {
                is = context.getResources().openRawResource(R.raw.universitymajorconnection);
            } else if(filename.equals("mail")) {
                is = context.getResources().openRawResource(R.raw.universitymail);
            }

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
    public static void parseJSON(Context context) {
        jsonUniv = loadJSONFromAsset(context, "university");
        jsonMajor = loadJSONFromAsset(context, "major");
        jsonCon = loadJSONFromAsset(context, "con");
        jsonMail = loadJSONFromAsset(context, "mail");

        try {
            JSONArray array = new JSONArray(jsonUniv);

            for(int i=0;i<array.length();i++) {
                JSONObject el = array.getJSONObject(i);
                Integer idx = el.getInt("idx");
                String name = el.getString("university");

                univList.put(idx, name);
                univReversedList.put(name, idx);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONArray array = new JSONArray(jsonMajor);

            for(int i=0;i<array.length();i++) {
                JSONObject el = array.getJSONObject(i);
                Integer idx = el.getInt("idx");
                String name = el.getString("major");

                majorList.put(idx, name);
                majorReversedList.put(name, idx);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONArray array = new JSONArray(jsonCon);

            for(int i=0;i<array.length();i++) {
                JSONObject el = array.getJSONObject(i);
                Integer univ = el.getInt("university");
                Integer major = el.getInt("major");

                ArrayList<Integer> arrayList = conList.get(univ);

                if(arrayList == null) {
                    arrayList = new ArrayList<>();
                }

                arrayList.add(major);
                conList.put(univ, arrayList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONArray array = new JSONArray(jsonMail);

            for(int i=0;i<array.length();i++) {
                JSONObject el = array.getJSONObject(i);
                Integer univId = el.getInt("univId");
                String mail = el.getString("mail");

                mailList.put(univId, mail);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<HashMap<String, String>> parseBoard(Context context, String data) {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(data);
            for(int i=0;i<array.length();i++) {
                JSONObject el = array.getJSONObject(i);
                String idx = el.getString("idx");
                String id = el.getString("id");
                String type = el.getString("type");
                String title = el.getString("title");
                String detail = el.getString("detail");
                String gender = el.getString("gender");

                HashMap<String, String> map = new HashMap<>();
                map.put("idx", idx);
                map.put("id", id);
                map.put("type", type);
                map.put("title", title);
                map.put("detail", detail);
                map.put("gender", gender);

                arrayList.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
