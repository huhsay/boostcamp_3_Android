package com.studiobethejustice.boostcamp_3_android.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.studiobethejustice.boostcamp_3_android.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;
    private WebSettings mWebSettings;
    private ActionBar mActionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_viewctivity);

        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

        //toolbar
        mActionbar = getSupportActionBar();
        mActionbar.setDisplayHomeAsUpEnabled(true);

        //webview
        mWebView = findViewById(R.id.web_view);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true); //웹뷰 가로 비율

        mWebView.loadUrl(link);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
