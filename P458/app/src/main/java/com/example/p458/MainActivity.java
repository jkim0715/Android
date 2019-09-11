package com.example.p458;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       webView = findViewById(R.id.webView);
       webView.addJavascriptInterface(new JS(),"js");
        textView =findViewById(R.id.textView);

       webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        //이거 안해주면 javascript가 동작안함.
        webSettings.setJavaScriptEnabled(true);

        // 최초 시작
        webView.loadUrl("http://m.naver.com");

    }

    final class JS{
        JS(){}
        @android.webkit.JavascriptInterface
        public void webclick(String str){
            textView.setText(str);
        }

    }

    public void clickBt(View view){
        if(view.getId() == R.id.button){

        }else if(view.getId() == R.id.button2){
            webView.loadUrl("http://70.12.60.106/Server/index.html");
        }else if(view.getId() == R.id.button3){
            webView.loadUrl("javascript:s('cmd')");
        }
    }
}
