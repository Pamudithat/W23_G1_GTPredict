package com.example.w23_g1_gtpredict;
// test
//test
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.text.DecimalFormat;

public class QuickPredResults extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    final String TAG = "QuickCalcDemo";
    final String TAG2 = "test";
    final String TAG3 = "test";

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

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
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
                }
                return true;
            }
        });



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
    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}