package com.studiobethejustice.boostcamp_3_android;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    final String SEARCH_URL = "https://openapi.naver.com/v1/search/movie?query=";

    EditText mTextSearch;
    Button mButtonSearch;
    RecyclerView mRecyclerResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextSearch = findViewById(R.id.edit_text_search);
        mButtonSearch = findViewById(R.id.btn_search);
        mRecyclerResult = findViewById(R.id.recycler_view);

        //Volley setting
        if (AppHelper.requestQueue == null) AppHelper.requestQueue = Volley.newRequestQueue(this);

        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchWord = mTextSearch.getText().toString().trim();
                SearchTask searchTask = new SearchTask(SEARCH_URL, searchWord);
                searchTask.execute();

            }
        });
    }

    private class SearchTask extends AsyncTask<Void, Void, String> {

        String searchURL;
        String searchWord;

        public SearchTask(String searchURL, String searchWord) {
            this.searchURL = searchURL;
            this.searchWord = searchWord;
        }

        @Override
        protected String doInBackground(Void... voids) {

            String result;
            SearchThread searchThread = new SearchThread();
            result = searchThread.request(searchURL, searchWord);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            resultProcess(s);
        }
    }

    private void resultProcess(String s) {
        Gson gson = new Gson();

        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
