package com.example.yantai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class Web extends AppCompatActivity {
    private WebView webView;
    private TextView textView;
    private String url = "http://www.laoyantai.cn/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web2);
        webView = findViewById(R.id.webb);
        textView = findViewById(R.id.baidu);
        webView.getSettings().setJavaScriptEnabled(true);//让WebView支持JavaScript脚本
        webView.setWebViewClient(new WebViewClient());//在WebView上显示网页
        webView.loadUrl(url);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "https://www.baidu.com/";
                webView.getSettings().setJavaScriptEnabled(true);//让WebView支持JavaScript脚本
                webView.setWebViewClient(new WebViewClient());//在WebView上显示网页
                webView.loadUrl(url);
            }
        });
    }
}