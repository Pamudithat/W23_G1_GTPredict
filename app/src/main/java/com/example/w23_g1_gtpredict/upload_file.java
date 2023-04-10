package com.example.w23_g1_gtpredict;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.room.Room;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class upload_file extends AppCompatActivity {

    private Button select;
    Button back;
    private TextView csvTxt;

    ArrayList<String> temp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);
        csvTxt=findViewById(R.id.csvtext);
        select=findViewById(R.id.button);
        back = findViewById(R.id.btnBack2);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //imp step
                if(SDK_INT >= Build.VERSION_CODES.R)
                {
                    if(Environment.isExternalStorageManager()){
                        //choosing csv file
                        Intent intent=new Intent();
                        intent.setType("*/*");
                        intent.putExtra(Intent.EXTRA_AUTO_LAUNCH_SINGLE_CHOICE,true);
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"Select CSV File "),101);
                    }
                    else{
                        //getting permission from user
                        Intent intent=new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                        Uri uri=Uri.fromParts("package",getPackageName(),null);
                        startActivity(intent);
//                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    }
                }
//                else{
//                    // for below android 11
//
//                    Intent intent=new Intent();
//                    intent.setType("*/*");
//                    intent.putExtra(Intent.EXTRA_AUTO_LAUNCH_SINGLE_CHOICE,true);
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
//
//                    ActivityCompat.requestPermissions(upload_file.this,new String[] {WRITE_EXTERNAL_STORAGE},102);
//
//
//                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    Uri fileuri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101 && data!=null){
            fileuri=data.getData();
            csvTxt.setText(readCSVData(getFilePathFromUri(fileuri)));
        }
    }


    // this method is used for getting file path from uri
    public String getFilePathFromUri(Uri uri){
        String[] filenme1;
        String fn;
        String filepath=uri.getPath();
        String filePath1[]=filepath.split(":");
        filenme1 =filepath.split("/");
        fn=filenme1[filenme1.length-1];
        return Environment.getExternalStorageDirectory().getPath()+"/"+filePath1[1];
    }

    //reading file data

    public String readCSVData(String path){
        String filedata = "";
        File file=new File(path);
        try {

            Scanner scanner=new Scanner(file);
            while (scanner.hasNextLine()){

                String line=scanner.nextLine();
                temp.add(line);
                filedata = filedata+line+"\n";
              //  String [] splited=line.split(",");
              //  String row="";
               // for (String s:splited){

                  //  row=row+s+"  ";

              //  }

                //filedata=filedata+row+"\n";

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(upload_file.this,"Error",Toast.LENGTH_SHORT).show();
        }
        new bgthread().start();

        return filedata;

    }

    class bgthread extends Thread
    {
        public void run ()
        {
            super.run();
            GTDatabase db = Room.databaseBuilder(getApplicationContext(),
                    GTDatabase.class, "GT_DB3").build();
            GTDataDao gtDataDao = db.gtDataDao();
            for (String s:temp) {
                int numTemp = Integer.parseInt( s.toString());
                double corE =0;
                double corP =0;
                if (numTemp >= 1 & numTemp  < 5)
                {corP = 0.99;corE =1.01;}
                if (numTemp >= 5 & numTemp  < 10)
                {
                    corP = 0.98;corE =1.02 ;
                }
                if (numTemp >= 10 & numTemp  < 20)
                {
                    corP = 0.97;corE =1.03 ;
                }

                if (numTemp >= 30 & numTemp  < 40)
                {
                    corP = 0.96;corE =1.04 ;
                }
                if (numTemp >= 40){
                    corP = 0.95;corE =1.05 ;
                }
                //*****
                double output1 = corP *100;
                double output2 = corE *80;
                gtDataDao.insertrecord(new GTData(numTemp, output1, output2));
            }

        }
    }

}
