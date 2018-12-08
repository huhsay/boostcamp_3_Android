package com.studiobethejustice.boostcamp_3_android.view;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.studiobethejustice.boostcamp_3_android.ItemAdapter;
import com.studiobethejustice.boostcamp_3_android.R;
import com.studiobethejustice.boostcamp_3_android.SearchThread;
import com.studiobethejustice.boostcamp_3_android.model.SearchResult;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    final String SEARCH_URL = "https://openapi.naver.com/v1/search/movie?query=";

    private Context mContext;
    private EditText mTextSearch;
    private Button mButtonSearch;
    private RecyclerView mRecyclerResult;
    private ItemAdapter mItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextSearch = findViewById(R.id.edit_text_search);
        mButtonSearch = findViewById(R.id.btn_search);
        mRecyclerResult = findViewById(R.id.recycler_view);
        mContext = getApplicationContext();

        //Search button
        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchWord = mTextSearch.getText().toString().trim();

                // 검색어 빈칸 처리
                if(searchWord.equals(null) || searchWord.equals("")){
                    Toast.makeText(mContext, "검색어를 입력해 주세요", Toast.LENGTH_LONG).show();
                    return;
                }

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
        SearchResult searchResult = gson.fromJson(s, SearchResult.class);

        // 검색결과 없을 때.
        if(searchResult.getTotal()==0){
            Toast.makeText(this, "검색결과가 없습니다.", Toast.LENGTH_LONG).show();
            return;
        }

        //set recyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerResult.setLayoutManager(layoutManager);
        mItemAdapter = new ItemAdapter(this);
        mRecyclerResult.setAdapter(mItemAdapter);

        mItemAdapter.addAllItem(searchResult.getItem());
        mItemAdapter.notifyDataSetChanged();

        Toast.makeText(this, searchResult.getItem().toString(), Toast.LENGTH_LONG).show();
    }
}
