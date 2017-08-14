package com.hankkin.taobaodetaildemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by ASUS on 2017/7/30.
 */

public class New extends AppCompatActivity {

    WebView chartshow_web;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.layout_new);
        super.onCreate(savedInstanceState);

        dbHelper = new MyDatabaseHelper(this,"info.db",null,1);
        dbHelper.getWritableDatabase();

        chartshow_web = (WebView) findViewById(R.id.chartshow_web);
        chartshow_web.getSettings().setAllowFileAccess(true);
        chartshow_web.getSettings().setJavaScriptEnabled(true);
        chartshow_web.loadUrl("file:///android_asset/echart.html");



//        pending t = new pending();
//        t.set("好");
//        int m = t.getresult();
//        Toast.makeText(New.this,m,Toast.LENGTH_SHORT).show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
