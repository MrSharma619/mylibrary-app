package com.example.intents;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class About extends AppCompatActivity {
    private WebView wv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        wv1 = findViewById(R.id.wv1);
        wv1.loadUrl("https://www.github.com/MrSharma619");
        wv1.setWebViewClient(new WebViewClient());

    }

    @Override
    public void onBackPressed() {
        if(wv1.canGoBack()){
            wv1.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}