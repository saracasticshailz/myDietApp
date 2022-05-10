package com.d4h.hp.diet4happlication.AllActivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.d4h.hp.diet4happlication.R;

public class TermsAndConditionActivity extends AppCompatActivity {

    WebView mywebview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_privacy_policy);

        mywebview = (WebView)findViewById(R.id.webview_privacypolicy);

        mywebview.loadUrl("http://uat.diet4health.in/public/diet4health.in/d4h_api/terms.html");
    }
}
