package com.example.w23_g1_gtpredict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class takedata extends AppCompatActivity {

    EditText NameCity;
    EditText NameProvince;
    Button data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takedata);

        NameCity=findViewById(R.id.NameCity);
        NameProvince=findViewById(R.id.NameProvince);
        data=findViewById(R.id.data);

        data.setOnClickListener((View view)->{

            if (NameCity.getText().toString().isEmpty()){

                Toast.makeText(this, "Please enter the City", Toast.LENGTH_SHORT).show();

            }else if(NameProvince.getText().toString().isEmpty()){

                Toast.makeText(this, "Please enter the Province", Toast.LENGTH_SHORT).show();

            }else {


                Intent intent = new Intent(takedata.this, WebScrap.class);
                Bundle bundle = new Bundle();
                bundle.putString("city", NameCity.getText().toString());
                bundle.putString("province", NameProvince.getText().toString());
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }
}