package com.example.w23_g1_gtpredict;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class GTData {
   // @PrimaryKey

   //@PrimaryKey(autoGenerate = true)
   //public int id;
   @PrimaryKey
    public int Temp;

    @ColumnInfo(name = "P_CORR")
    public double corP;

    @ColumnInfo(name = "E_CORR")
    public double corE;



    public GTData(int Temp, double corP, double corE) {
        this.Temp = Temp;
        this.corP = corP;
        this.corE = corE;

    }

    public int getTemp() {
        return Temp;
    }

    public void setTemp(int temp) {
        Temp = temp;
    }

    public double getCorP() {
        return corP;
    }

    public void setCorP(double corP) {
        this.corP = corP;
    }

    public double getCorE() {
        return corE;
    }

    public void setCorE(double corE) {
        this.corE = corE;
    }
}
