package com.siadous.thomas.mynews.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.siadous.thomas.mynews.R;

public class DetailActivity extends AppCompatActivity {

    private WebView webView;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent intent = getIntent();
        if (null != intent) {
            data = intent.getStringExtra("key_url");
        }



        webView = findViewById(R.id.web_view_details);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(data);

    }
}
