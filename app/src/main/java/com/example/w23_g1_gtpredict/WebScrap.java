package com.example.w23_g1_gtpredict;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import org.jsoup.Jsoup;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WebScrap extends AppCompatActivity {


    TextView wind;
    TextView precip;
    TextView humidity;
    String thedescription;
    String precipita;
    String alldata;
    String humid;
    String win;

    TextView dis;
    List<String> info = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_scrap);

        dis=findViewById(R.id.disc);
        wind=findViewById(R.id.wind);
        precip=findViewById(R.id.precip);
        humidity=findViewById(R.id.humidity);

        Bundle bundle = getIntent().getExtras();

        String city = bundle.getString("city", "Default");
        String province = bundle.getString("city", "Default");
        Log.d("TAG",city);

        discription_webscrap dw=new discription_webscrap(city,province);
        dw.execute();



    }


    public class discription_webscrap extends AsyncTask<String,String,List<String>> {

        String city;
        String province;

        public discription_webscrap(String city, String province) {
            this.city = city;
            this.province = province;
        }

        @Override
        protected void onPostExecute(List<String> strings) {
           super.onPostExecute(strings);
            dis.setText(strings.get(0));
            precip.setText(strings.get(1));
            humidity.setText(strings.get(2));
            wind.setText(strings.get(3));

        }

        @Override
        protected List<String> doInBackground(String... strings) {
            List<String> info = new ArrayList<>();
           org.jsoup.nodes.Document document=null;

            try {
                //   String x="royal oak";
                // String y="bc";

                document=Jsoup.connect("https://www.google.com/search?q=weather+"+city+"+"+province).get();
                Log.d("TAG","File got");
                org.jsoup.select.Elements elements=document.getElementsByClass("wob_t q8U8x");
                Log.d("TAG","class got");
                thedescription=elements.text();
                thedescription=thedescription+" °C";
                Log.d("TAG",thedescription);
                org.jsoup.select.Elements elemen=document.getElementsByClass("wtsRwe");
                alldata=elemen.text();
                Log.d("TAG",alldata);
                precipita=alldata.substring(0,18);
                Log.d("TAG",precipita);
                humid=alldata.substring(18,32);
                Log.d("TAG",humid);
                win=alldata.substring(32,49);
                Log.d("TAG",win);
                String gx=dis.getText().toString();
                Log.d("TAG",gx);

                //
                info.add(thedescription);
                info.add(precipita);
                info.add(humid);
                info.add(win);


            }
            catch(Exception ex){
                Log.d("TAG","ERROR");
                startActivity(new Intent(WebScrap.this,takedata.class));
                Toast.makeText(WebScrap.this, "Please Enter the Right Place", Toast.LENGTH_SHORT).show();
                ex.printStackTrace();

            }

        return info;


        }
    }


}