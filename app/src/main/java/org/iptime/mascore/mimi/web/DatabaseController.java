package org.iptime.mascore.mimi.web;

import org.iptime.mascore.mimi.global.Secrets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class DatabaseController {
	public static final String defaultAddress = "http://mascore.iptime.org/mimi/";
	
	public String databaseConnect(String module, String args) { // Database

		try {
			URL url = new URL(defaultAddress + module);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            String body = "pw=" + Secrets.databasepw; //
            body += args;

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(body.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();

            StringBuffer response = new StringBuffer();
            String line = null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();
            
            System.out.println(response.toString());
            return response.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	public int databaseWork(Map<String, String> data, String module) {
		String body = "";
		
		for( String key : data.keySet()) {
			body += "&" + key + "=" +  data.get(key);
		}
		
		String result = databaseConnect(module, body);
		
		if(result.contains("Fail")) {
			System.out.println(result);
			
			if(result.contains("3")) { // ID
				return 2;
			} else if(result.contains("4")) { //
				return 3;
			} else if(result.contains("5")) {
				return 4;
			}
			return 1;
		} else if(result.contains("Success")) {
			return 0;
		}
		
		return 1;
	}	
	public int insertBoard(Map<String, String> data) {
		return databaseWork(data, "insertBoard.php");
	}
	public int changeBoard(Map<String, String> data) {
		return databaseWork(data, "changeBoard.php");
	}
	public int deleteBoard(Map<String, String> data) {
		return databaseWork(data, "deleteBoard.php");
	}
	public int insertUser(Map<String, String> data) {
		return databaseWork(data, "insertUser.php");
	}
	public int changeUser(Map<String, String> data) {
		return databaseWork(data, "changeUser.php");
	}
	public int deleteUser(Map<String, String> data) {
		return databaseWork(data, "deleteUser.php");
	}
	public int insertUserScrap(Map<String, String> data) {
		return databaseWork(data, "insertUserScrap.php");
	}
	public int deleteUserScrap(Map<String, String> data) {
		return databaseWork(data, "deleteUserScrap.php");
	}
	public int checkID(Map<String, String> data) {
		return databaseWork(data, "checkId.php");
	}
	public int checkEmail(Map<String, String> data) {
		return databaseWork(data, "checkEmail.php");
	}
	public int login(Map<String, String> data) {
		return databaseWork(data, "login.php");
	}
	public int email(Map<String, String> data) {
		return databaseWork(data, "email.php");
	}
}
