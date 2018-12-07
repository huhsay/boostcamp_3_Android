package com.studiobethejustice.boostcamp_3_android;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SearchThread{
    private static final String TAG = "SearchThread";
    final String clientId = "nkAhHANDxYvZUtA3dE06";
    final String clientSecret = "q5D_4UF3vo";

    String searchWord;
    String searchURL;
    StringBuffer response;


    public String request(String searchURL, String searchWord) {

        this.searchURL = searchURL;
        this.searchWord = searchWord;

        try {
            String encodedWord = URLEncoder.encode(searchWord, "UTF-8");
            String apiURL = searchURL + encodedWord;

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
            response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());


        } catch (Exception e) {
            System.out.println(e);
        }

        return response.toString();
    }
}
