package com.example.w23_g1_gtpredict;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

public class QuickPredActivity extends AppCompatActivity {
    Spinner selectOutput;
    EditText editTextNumTemp;
    Button btnCalcQuick;
    Animation animation;
    LottieAnimationView imageView3;


    ObjectAnimator animator;
    final String TAG = "QuickCalcDemo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_pred);
        Log.d(TAG,"Starting Quick Calc demo...");
        selectOutput = findViewById(R.id.selectOutput);
        editTextNumTemp = findViewById(R.id.editTextNumTemp);
        btnCalcQuick = findViewById(R.id.btnCalcQuick);
        imageView3=findViewById(R.id.imageView3);
        final List<String> str =new ArrayList<>();
        str.add(0,"Choose Category");
        str.add("Power Output");
        str.add("Efficiency");



        ArrayAdapter arrayAdapter =new ArrayAdapter(QuickPredActivity.this,android.R.layout.simple_dropdown_item_1line,str);
        selectOutput.setAdapter(arrayAdapter);


        selectOutput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(selectOutput.getItemAtPosition(i).equals("Choose Category")){

                }
                else if (str.get(1).equals(selectOutput.getItemAtPosition(i).toString())){
                    Toast.makeText(QuickPredActivity.this, "Selected Power Output", Toast.LENGTH_SHORT).show();
                    imageView3.setAnimation(R.raw.wind);
                    imageView3.playAnimation();

                } else if(str.get(2).equals(selectOutput.getItemAtPosition(i).toString()))  {
                    Toast.makeText(QuickPredActivity.this, "Selected Efficiency", Toast.LENGTH_SHORT).show();
                    imageView3.setAnimation(R.raw.lott1);
                    imageView3.playAnimation();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnCalcQuick.setOnClickListener((View view) ->{
            if (editTextNumTemp.getText().toString().isEmpty()){

                Toast.makeText(this, "Please enter an integer", Toast.LENGTH_SHORT).show();

            }else if(selectOutput.getSelectedItem().equals("Choose Category")){

                Toast.makeText(this, "Please Choose Category", Toast.LENGTH_SHORT).show();

            }
            else{

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
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                }catch (Exception ex){

                    ex.printStackTrace();
                    Toast.makeText(this, "Must valid whole number", Toast.LENGTH_SHORT).show();
                }



            }

        });

    }


    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}