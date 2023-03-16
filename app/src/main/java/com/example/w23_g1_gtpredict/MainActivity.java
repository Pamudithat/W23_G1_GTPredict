package com.example.w23_g1_gtpredict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btnEnter;
    Animation animation;
    ImageView companyNameImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        companyNameImg=findViewById(R.id.companyNameImg);

        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        companyNameImg.startAnimation(animation);

        btnEnter = findViewById(R.id.gtPredictEnterbtn);




        btnEnter.setOnClickListener((View view) ->{
            startActivity(new Intent(MainActivity.this,QuickPredActivity.class));
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
           // Toast.makeText(MainActivity.this, "Selected Power Output", Toast.LENGTH_SHORT).show();

        });


    }


}

