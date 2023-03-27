package com.example.w23_g1_gtpredict;
// test
//test
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class QuickPredResults extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ImageView endImage;
    ObjectAnimator animator;
    Animation animation;
    final String TAG = "QuickCalcDemo";
    final String TAG2 = "test";
    final String TAG3 = "test";
    String outputStr;
    String phone;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_pred_results);

        endImage=findViewById(R.id.endImage);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);


        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animator = ObjectAnimator.ofArgb(this, "color", Color.YELLOW, Color.RED);
            animator.setDuration(8000);
            animator.setInterpolator(new LinearInterpolator());
        }

        endImage.startAnimation(animation);
        animator.start();


        int numTemp = 0;
        try{

            Bundle bundle = getIntent().getExtras();
            double output = bundle.getDouble("OUTPUT",99999);
            numTemp = getIntent().getExtras().getInt("TEMP",99999);
            String OutputType = bundle.getString("TYPE","error");
            DecimalFormat df = new DecimalFormat("#.0");
            DecimalFormat df1 = new DecimalFormat("#.0");
            outputStr = "Output KPI Type: " + OutputType+ "\n" +"Current Temperature: " + df1.format(numTemp)+" degree C" +"\n" + OutputType+": "
                    + df.format(output) +" Units" ;

            TextView textViewQuickResults = findViewById(R.id.textViewQuickResults);
            textViewQuickResults.setText(outputStr);

            textViewQuickResults.setGravity(Gravity.CENTER);
        }
        catch(Exception ex){

            ex.printStackTrace();
            Log.d(TAG,"wrong"+ numTemp);

        }

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.menu_open,R.string.menu_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_chart:
                        startActivity(new Intent(QuickPredResults.this,graph.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_upload:
                        startActivity(new Intent(QuickPredResults.this,upload_file.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_download:
                        download_csv();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_email:
                        send_email();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_sms:
                        Intent myResults = new Intent(QuickPredResults.this,send_sms.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("OUTPUT",outputStr);
                        myResults.putExtras(bundle);
                        startActivity(myResults);

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });



    }
    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    public void setColor(int color){

        //intensity.setBackgroundColor(color);
        endImage.setColorFilter(color, PorterDuff.Mode.OVERLAY);
    }

    public void send_email(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,"");
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_TEXT,outputStr);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose Email Client"));
    }

    public void download_csv(){
//        if(ContextCompat.checkSelfPermission(QuickPredResults.this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
//
            ActivityCompat.requestPermissions(QuickPredResults.this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
//
//        }else{
           try{
               File file = new File("/sdcard/Download/");
               file.mkdirs();

               String csv = "/sdcard/Download/report.csv";
               CSVWriter csvWriter = new CSVWriter(new FileWriter(csv, true));
               String row[] = new String[]{"test","123"};
               csvWriter.writeNext(row);
               csvWriter.close();
               Toast.makeText(this, "File Successfully Downloaded", Toast.LENGTH_SHORT).show();

           } catch (Exception e){
               e.printStackTrace();
           }
        }
//    }

}