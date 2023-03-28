package com.example.w23_g1_gtpredict;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class send_sms extends AppCompatActivity {

    Button send_sms;
    Button back;
    EditText editTextPhone;
    String outputStr;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        send_sms = findViewById(R.id.btnSend);
        editTextPhone = findViewById(R.id.editTextPhone);
        back = findViewById(R.id.btnBack);

        try{
            Bundle bundle = getIntent().getExtras();
            outputStr = bundle.getString("OUTPUT","output");

        }catch(Exception e){
            e.printStackTrace();
        }

        send_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(send_sms.this, Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_GRANTED){

//                  String phone = "17782517937";
                    phone = editTextPhone.getText().toString();

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone,null,outputStr,null,null);
                    Toast.makeText(send_sms.this, "SMS Sent", Toast.LENGTH_SHORT).show();
                }else{
                    ActivityCompat.requestPermissions(send_sms.this, new String[]{Manifest.permission.SEND_SMS},100);

                }
            }
        });

       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               onBackPressed();
           }
       });
    }
}