package com.hankkin.taobaodetaildemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.hankkin.taobaodetaildemo.db.TagsManager;

import java.util.ArrayList;

/**
 * Created by ASUS on 2017/7/30.
 */

public class New extends AppCompatActivity {

//    // 返回的数据
//    private String info;
//    // 返回主线程更新数据
//    private static Handler handler = new Handler();
//
//    private TextView infv;

    //WebView chartshow_web;
    //private MyDatabaseHelper dbHelper;
    private PieChart mPieChart;

    private TextView mPromptText;

    private TagGroup mDefaultTagGroup;

    private TagsManager mTagsManager;

    private TagGroup mTagGroup;

    //String [] tags;
    //ArrayList<String> tags = new ArrayList<>();

    private TagGroup.OnTagClickListener mTagClickListener = new TagGroup.OnTagClickListener() {
        @Override
        public void onTagClick(String tag) {
            Toast.makeText(New.this, "11条相关评论", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.layout_new);
        super.onCreate(savedInstanceState);

//        Button btn = (Button) findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 检测网络，无法检测wifi
//                if (!checkNetwork()) {
//                    Toast toast = Toast.makeText(New.this,"网络未连接", Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.CENTER, 0, 0);
//                    toast.show();
//                } else{
//                    Toast.makeText(New.this,"lianjie",Toast.LENGTH_SHORT).show();
//                }
//                // 创建子线程，分别进行Get和Post传输
//                new Thread(new MyThread()).start();
//            }
//        });

//        dbHelper = new MyDatabaseHelper(this,"info.db",null,1);
//        dbHelper.getWritableDatabase();
//
//        chartshow_web = (WebView) findViewById(R.id.chartshow_web);
//        chartshow_web.getSettings().setAllowFileAccess(true);
//        chartshow_web.getSettings().setJavaScriptEnabled(true);
//        chartshow_web.loadUrl("file:///android_asset/echart.html");


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    Thread.sleep(2000);
//                } catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//            }
//        }).start();



        //mPieChart.setVisibility(View.VISIBLE);
        mPieChart = (PieChart) findViewById(R.id.spread_pie_chart);
        PieData mPieData = getPieData();
        showChart(mPieChart, mPieData);

        String[] tags = {"衣服质量","买到划算","客服推荐","拍大一","不掉毛","颜色","会关顾"};

        mDefaultTagGroup = (TagGroup) findViewById(R.id.tag_group);
        if (tags != null && tags.length > 0) {
            mDefaultTagGroup.setTags(tags);
        }

        mDefaultTagGroup.setOnTagClickListener(mTagClickListener);
    }

//    // 子线程接收数据，主线程修改数据
//    public class MyThread implements Runnable {
//        @Override
//        public void run() {
//            info = WebService.executeHttpGet();
//            //System.out.println("info:"+info);
//            String[] a = info.split(";");
//            for (int i = 0; i < a.length ; i ++){
//                //tags.add(a[i]);
//                System.out.println(a[i]);
//            }
//            // info = WebServicePost.executeHttpPost(username.getText().toString(), password.getText().toString());
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    infv.setText(info);
////                    dialog.dismiss();
//                }
//            });
//        }
//    }
//
//    // 检测网络
//    private boolean checkNetwork() {
//        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connManager.getActiveNetworkInfo() != null) {
//            return connManager.getActiveNetworkInfo().isAvailable();
//        }
//        return false;
//    }

    private void showChart(PieChart pieChart, PieData pieData) {
        pieChart.setHoleColorTransparent(true);

        pieChart.setHoleRadius(60f);  //半径
        pieChart.setTransparentCircleRadius(64f); // 半透明圈
        //pieChart.setHoleRadius(0)  //实心圆
        pieChart.setDrawCenterText(false);  //饼状图中间可以添加文字
        pieChart.setDrawHoleEnabled(true);
        pieChart.setRotationAngle(90); // 初始旋转角度
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true); // 可以手动旋转

        // display percentage values
        pieChart.setUsePercentValues(true);  //显示成百分比
        //pieChart.setCenterText("新词比例");  //饼状图中间的文字
        pieChart.setDescriptionTextSize(1);
        pieData.setValueTextSize(10);
        pieChart.setCenterTextSize(15);
//        pieChart.setCenterTextSizePixels(50);
        //设置数据
        pieChart.setData(pieData);

        // undo all highlights
//      pieChart.highlightValues(null);
//      pieChart.invalidate();

        Legend mLegend = pieChart.getLegend();  //设置比例图
        //mLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);  //最右边显示
//      mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形
        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(5f);

        pieChart.animateXY(1000, 1000);  //设置动画
        // mChart.spin(2000, 0, 360);
    }


    private PieData getPieData() {

        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容

        xValues.add("不忘初心");xValues.add("坚定不移");
        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据

        // 饼图数据
        /**
         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38
         * 所以 14代表的百分比就是14%
         */
        float quarterly1 = 90;
        float quarterly2 = 10;

        yValues.add(new Entry(quarterly1, 0));
        yValues.add(new Entry(quarterly2, 1));

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, ""/*显示在比例图上*/);
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
//        colors.add(Color.rgb(205, 205, 205));
//        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(57, 135, 200));

        pieDataSet.setColors(colors);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(xValues, pieDataSet);

        return pieData;
    }
}
