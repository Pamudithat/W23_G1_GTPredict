package com.example.w23_g1_gtpredict;
// test
//test
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import java.text.DecimalFormat;

public class QuickPredResults extends AppCompatActivity {
    final String TAG = "QuickCalcDemo";
    final String TAG2 = "test";
    final String TAG3 = "test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_pred_results);
        int numTemp = 0;
        try{

            Bundle bundle = getIntent().getExtras();
            double output = bundle.getDouble("OUTPUT",99999);
            numTemp = getIntent().getExtras().getInt("TEMP",99999);
            String OutputType = bundle.getString("TYPE","error");
            DecimalFormat df = new DecimalFormat("#.0");
            DecimalFormat df1 = new DecimalFormat("#.0");
                    String outputStr = "Output KPI Type: " + OutputType+ "\n" +"Current Temperature: " + df1.format(numTemp)+" degree C" +"\n" + OutputType+": "
                            + df.format(output) +" Units" ;

            TextView textViewQuickResults = findViewById(R.id.textViewQuickResults);
            textViewQuickResults.setText(outputStr);

            textViewQuickResults.setGravity(Gravity.CENTER);
        }
        catch(Exception ex){

            ex.printStackTrace();
            Log.d(TAG,"wrong"+ numTemp);

        }
    }
}