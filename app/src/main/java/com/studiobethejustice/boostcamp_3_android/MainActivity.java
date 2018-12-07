package com.studiobethejustice.boostcamp_3_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


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
                SearchThread searchThread = new SearchThread(searchWord);
                searchThread.start();

            }
        });
    }
}
