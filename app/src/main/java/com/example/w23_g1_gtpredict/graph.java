package com.example.w23_g1_gtpredict;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class graph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        GraphView graph = (GraphView) findViewById(R.id.graph);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(10, 50),
                new DataPoint(20, 30),
                new DataPoint(30, 25),
                new DataPoint(40, 68),
                new DataPoint(50, 89)
        });
        graph.addSeries(series);
        series.setThickness(6);
        series.setDrawDataPoints(true);
        series.setAnimated(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}