package com.Bluetooth.Activity;

import android.app.Activity;
import android.os.Bundle;
import com.Bluetooth.base.BluetoothTools;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Color;
import android.os.Bundle;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by TR on 2015/9/5.
 */
public class MainActivity extends Activity
{

    private LineChart mLineChart;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        Log.i("dd","affair");
//        BluetoothTools.initBluetooth();
//        BluetoothTools.findDevicesByName("ddd");

        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 2));
        values.add(new PointValue(1, 4));
        values.add(new PointValue(2, 3));
        values.add(new PointValue(3, 4));
        values.add(new PointValue(3.4f, 4.3f));
        values.add(new PointValue(4.f, 3.2f));
//In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);
        LineChartView chart = (LineChartView) findViewById(R.id.chart);
        chart.setLineChartData(data);





//        mLineChart = (LineChart) findViewById(R.id.spread_line_chart);
//        final LineData mLineData = getLineData(36, 100);
//        showChart(mLineChart, mLineData, Color.rgb(114, 188, 223));
//        Timer mTimer = new Timer();
//
//
//        mTimer = new Timer();
//        mTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//                Message message = new Message();
//                message.what = 1;
//                handler.sendMessage(message);
//
//
//            }
//        }, 1*1000, 1*1000);


    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                LineData mLineData = getLineData(36, 100);
                mLineChart.setData(mLineData);
                mLineChart.animateX(0);
//                refreshMLineChart();
            }
            super.handleMessage(msg);
        };
    };

    private void refreshMLineChart(){
        mLineChart.invalidate();
    }

    // 设置显示的样式
    private void showChart(LineChart lineChart, LineData lineData, int color) {
        lineChart.setDrawBorders(false);  //是否在折线图上添加边框

        // no description text
        lineChart.setDescription("");// 数据描述
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
        lineChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable / disable grid background
        lineChart.setDrawGridBackground(false); // 是否显示表格颜色
        lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度

        // enable touch gestures
        lineChart.setTouchEnabled(true); // 设置是否可以触摸

        // enable scaling and dragging
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(true);// 是否可以缩放

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);//

        lineChart.setBackgroundColor(color);// 设置背景

        // add data
        lineChart.setData(lineData); // 设置数据

        // get the legend (only possible after setting data)
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的

        // modify the legend ...
        // mLegend.setPosition(LegendPosition.LEFT_OF_CHART);
        mLegend.setForm(LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(6f);// 字体
        mLegend.setTextColor(Color.WHITE);// 颜色
//      mLegend.setTypeface(mTf);// 字体

        lineChart.animateX(0); // 立即执行的动画,x轴
    }


    ArrayList<Entry> yValues = new ArrayList<Entry>();
    ArrayList<String> xValues = new ArrayList<String>();
    /**
     * 生成一个数据
     * @param count 表示图表中有多少个坐标点
     * @param range 用来生成range以内的随机数
     * @return
     */
    private LineData getLineData(int count, float range) {

        for (int i = 0; i < count; i++) {
            // x轴显示的数据，这里默认使用数字下标显示

        }

        // y轴的数据;
       if (yValues.size()>=count){
           yValues.remove(0);
           xValues.remove(0);
       }
        int start = 0;
        if (xValues.size()>0){
//            try {
//
//            }catch ()
            start = Integer.parseInt(xValues.get(xValues.size()-1));
        }
        for (int i =start+1; yValues.size() < count; i++) {
            float value = (float) (Math.random() * range) + 3;
            yValues.add(new Entry(value, i));
            xValues.add("" + i);
            Log.d("yValues i:",""+i);
        }


        // create a dataset and give it a type
        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(yValues, "测试折线图" /*显示在比例图上*/);
        // mLineDataSet.setFillAlpha(110);
        // mLineDataSet.setFillColor(Color.RED);

        //用y轴的集合来设置参数
        lineDataSet.setLineWidth(1.75f); // 线宽
        lineDataSet.setCircleSize(3f);// 显示的圆形大小
        lineDataSet.setColor(Color.WHITE);// 显示颜色
        lineDataSet.setCircleColor(Color.WHITE);// 圆形的颜色
        lineDataSet.setHighLightColor(Color.WHITE); // 高亮的线的颜色

        ArrayList<LineDataSet> lineDataSets = new ArrayList<LineDataSet>();
        lineDataSets.add(lineDataSet); // add the datasets

        // create a data object with the datasets
        LineData lineData = new LineData(xValues, lineDataSets);

        return lineData;
    }

}
