package com.example.w23_g1_gtpredict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class QuickPredActivity extends AppCompatActivity {
    Spinner selectOutput;
    EditText editTextNumTemp;
    Button btnCalcQuick;
    final String TAG = "QuickCalcDemo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_pred);
        Log.d(TAG,"Starting Quick Calc demo...");
        selectOutput = findViewById(R.id.selectOutput);
        editTextNumTemp = findViewById(R.id.editTextNumTemp);
        btnCalcQuick = findViewById(R.id.btnCalcQuick);

        selectOutput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    Toast.makeText(QuickPredActivity.this, "Selected Power Output", Toast.LENGTH_SHORT).show();
                } else  {
                    Toast.makeText(QuickPredActivity.this, "Selected Efficiency", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    btnCalcQuick.setOnClickListener((View view) ->{
      if (editTextNumTemp.getText().toString().isEmpty()){

          Toast.makeText(this, "Please enter an integer", Toast.LENGTH_SHORT).show();


      }else{

          try {

int numTemp = Integer.parseInt( editTextNumTemp.getText().toString());

int index = selectOutput.getSelectedItemPosition();
double output = 0;
double corP,corE;
//Sampel corrections added for demo purpose as below with if logics ,full correction tables wil be added to SQL lite in futre
if (numTemp >= 1 & numTemp  < 5)
{corP = 0.99;corE =1.01;}
 if (numTemp >= 5 & numTemp  < 10) {

              }
              {
    corP = 0.98;corE =1.02 ;
}
if (numTemp >= 10){
                  corP = 0.97;corE =1.03 ;
              }
switch (index){

    case 0:

        output = corP*100;
        break;

        case 1:
    output = corE*80;
    break;

}
              Intent myResults = new Intent(QuickPredActivity.this,QuickPredResults.class);

Bundle bundle = new Bundle();
bundle.putInt("TEMP",numTemp);
bundle.putString("TYPE",selectOutput.getSelectedItem().toString());
bundle.putDouble("OUTPUT",output);

myResults.putExtras(bundle);
startActivity(myResults);
          }catch (Exception ex){

              ex.printStackTrace();
              Toast.makeText(this, "Must valid whole number", Toast.LENGTH_SHORT).show();
          }



      }

    });

    }
}