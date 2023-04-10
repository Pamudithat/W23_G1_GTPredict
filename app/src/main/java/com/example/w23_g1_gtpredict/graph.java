package com.example.w23_g1_gtpredict;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

public class graph extends AppCompatActivity {

    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        back = findViewById(R.id.btnBack1);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        //----
        GTDatabase db = Room.databaseBuilder(getApplicationContext(),
               GTDatabase.class, "GT_DB3").allowMainThreadQueries().build();
        GTDataDao gtDataDao = db.gtDataDao();
        List<GTData> gtdatas = gtDataDao.getallgtdatasDESC();
        //for (GTData item : gtdatas) {

        GTData item = gtdatas.get(gtdatas.size()-1);
        GTData item1 = gtdatas.get(gtdatas.size()-2 );
        GTData item2 = gtdatas.get(gtdatas.size()-3);
        GTData item3 = gtdatas.get(gtdatas.size()-4);
        GTData item4 = gtdatas.get(gtdatas.size()-5);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {

                new DataPoint(item4.Temp, item4.corP),
                new DataPoint(item3.Temp, item3.corP),
                new DataPoint(item2.Temp, item2.corP),
                new DataPoint(item1.Temp, item1.corP),
                //new DataPoint(20, 30),
               //new DataPoint(30, 25),
              // new DataPoint(40, 68),
              //  new DataPoint(50, 89),
                new DataPoint(item.Temp, item.corP)
        });
        graph.addSeries(series);
        series.setThickness(6);
        series.setDrawDataPoints(true);
        series.setAnimated(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


}