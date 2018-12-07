package com.studiobethejustice.boostcamp_3_android;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Thread;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SearchThread extends Thread {
    private static final String TAG = "SearchThread";
    final String SEARCH_URL = "https://openapi.naver.com/v1/search/movie?query=";
    final String clientId = "nkAhHANDxYvZUtA3dE06";
    final String clientSecret = "q5D_4UF3vo";

    String searchWord;


    public SearchThread(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public void run() {

        Log.d(TAG, "sendRequest: started "+ searchWord);

        try {
            String encodedWord = URLEncoder.encode(searchWord, "UTF-8");
            String apiURL = SEARCH_URL + encodedWord;

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;

            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                Log.d(TAG, "sendRequest: success");
            } else {  // 에러 발생
                Log.d(TAG, "sendRequest: error");
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
