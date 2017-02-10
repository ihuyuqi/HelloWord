package com.rubi.helloword;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends AppCompatActivity {
    private LineChartView lineChartView;
    private Button btntest;
    String[] date = {"10-22","11-22","12-22","1-22","6-22","5-23","5-22","6-22","5-23","5-22"};//X轴的标注
    int[] dataY = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120};
    int[] score= {50,42,90,33,10,74,22,18,79,20};//图表的数据点
    private List<PointValue> arrPoint = new ArrayList<>();
    private List<AxisValue> arrAxis = new ArrayList<>();
    private List<AxisValue> arrAxisY = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getAxiXLables();
        getAxisPoints();
        initLineChart();
    }

    private void initLineChart() {
        List<Line> lines = new ArrayList<>();
        Line line = new Line(arrPoint).setColor(Color.RED);//折线的颜色
        line.setShape(ValueShape.CIRCLE);//线上每个数据点的形状 圆形
        line.setCubic(false);//曲线是否平滑
        line.setStrokeWidth(2);//线条的粗细 默认是3
        line.setFilled(false);//是否填充曲线面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
        line.setHasLines(true);//是否用直线显示 false则只有点
        line.setHasPoints(true);//是否显示圆点 false没有原点只有点显示
        lines.add(line);
        LineChartData lineData = new LineChartData();
        lineData.setLines(lines);
        /**
         * 坐标轴
         */
        Axis axisx = new Axis();//x轴
        axisx.setHasTiltedLabels(false);//x轴下面坐标轴字体是斜的还是直的
        axisx.setTextColor(Color.BLACK);
//        axisx.setName();表格名称
        axisx.setTextSize(11);
        axisx.setMaxLabelChars(7);//最多几个x轴坐标,缩放后数据 大于7小于数据远
        axisx.setValues(arrAxis);//填充x轴的坐标名称
        lineData.setAxisXBottom(axisx);//x轴在底部
        axisx.setHasLines(true);//x轴分割线
        Axis axisY = new Axis();  //Y轴

        axisY.setName("");//y轴标注
        axisY.setTextSize(11);//设置字体大小
        axisY.setLineColor(Color.BLACK);
        axisY.setHasLines(true);
//        axisY.setMaxLabelChars(13);
//        axisY.setValues(arrAxisY);
        lineData.setAxisYLeft(axisY);  //Y轴设置在左边
        //设置行为属性，支持缩放、滑动以及平移
        lineChartView.setInteractive(true);
        lineChartView.setZoomType(ZoomType.HORIZONTAL);
        lineChartView.setMaxZoom((float) 3);
        lineChartView.setLineChartData(lineData);
        lineChartView.setVisibility(View.VISIBLE);
    }

    /**
     * 图标每个点显示
     */

    private void getAxisPoints() {
        for (int i = 0; i < score.length; i++) {
            arrPoint.add(new PointValue(i, score[i]));
        }
    }

    /**
     * 设置x轴显示
     */
    private void getAxiXLables() {
        for (int i = 0; i < date.length; i++) {
            arrAxis.add(new AxisValue(i).setLabel(date[i]));
        }
        for (int i = 0; i < dataY.length; i++) {
            arrAxisY.add(new AxisValue(i).setLabel(dataY[i]+""));
        }
        
    }

    

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView() {
        lineChartView = (LineChartView) findViewById(R.id.lineChart);
        btntest = (Button) findViewById(R.id.btn_test);
        btntest.setOnClickListener(view -> Toast.makeText(this, "123折线图", Toast.LENGTH_SHORT).show());
        checkSelfPermission();
    }

    private void checkSelfPermission() {
    }


}
