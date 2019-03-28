package com.example.ari.masojek;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ViewFlipper;

public class Menu_Plg extends AppCompatActivity {
    WebView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__plg);
        view = (WebView) this.findViewById(R.id.slidermas);
        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient(new MyBrowser());

        if (savedInstanceState == null)
        {
            view.loadUrl("https://sumit.id/app_server/slider.php");
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState ){
        super.onSaveInstanceState(outState);
        view.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        view.restoreState(savedInstanceState);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public  boolean shouldOverrideUrlLoading(WebView view, String url ){
            view.loadUrl(url);
            return true;
        }
    }

}