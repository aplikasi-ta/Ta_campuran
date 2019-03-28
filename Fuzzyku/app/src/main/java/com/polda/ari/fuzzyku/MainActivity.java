package com.polda.ari.fuzzyku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.*;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private String[][] rule;
    private double dLuasTanah [];
    private double dLuasBangunan[];
    private double dSsb [];
    private double test [][];
    private double aPredikat [];
    private double dHargaJual [];
    private double momen [];
    private double luas[];
    private double max1,max2, max3;
    private double a1,a2,a3,a4,a5,a6;
    private double jumlahMomen,jumlahLuas;
    private double LuasTanah, LuasBangunan, SSB, HargaJual;
    DecimalFormat DF = new DecimalFormat("#.###");
    EditText txt_luas_t,txt_luas_b,txt_sbb;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn_submit);
        txt_luas_t = (EditText) findViewById(R.id.txt_luas_tanah);
        txt_luas_b = (EditText) findViewById(R.id.txt_luas_bangunan);
        txt_sbb = (EditText) findViewById(R.id.txt_sbb);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final double luasTanah = Double.parseDouble(txt_luas_t.getText().toString().trim());
                final double luasBangunan = Double.parseDouble(txt_luas_b.getText().toString().trim());
                final double ssb = Double.parseDouble(txt_sbb.getText().toString().trim());
                Rule();
                Fuzzyfikasi(luasTanah,luasBangunan,ssb);
                ImplikasiMin();
                ImplikasiMax();
                BatasArea();
                Momen();
                Luas();
                MomenLuas();
                HargaJual();
                Double hasil = Double.parseDouble(DF.format(getHargaJual()));
                //HargaJual.setText("Rp."+String.valueOf(hasil));
                Toast.makeText(getApplication(),"Data Hasil Fuzzy "+String.valueOf(hasil),Toast.LENGTH_LONG).show();



                /*double pmMax,pmMin,Prm,MaxPro,MinPro;
                double uTrn, uNaik;
                {
                    pmMax = Double.parseDouble("");
                    pmMin = Double.parseDouble("");
                    Prm = Double.parseDouble("");
                    uTrn = turun(pmMax,pmMin,Prm);
                    uNaik = naik(pmMax,pmMin,Prm);
                }

                double PrMax, PrMin, Pers;
                double uSdk, uByk;
                {
                    PrMax = Double.parseDouble("");
                    PrMin = Double.parseDouble("");
                    Pers = Double.parseDouble("");
                    uSdk = sedikit(PrMax,PrMin,Pers);
                    uByk = banyak(PrMax, PrMin, Pers);
                }

                double p1,p2,p3,p4,max1,max2,max,Mini,Min,a1,a2;
                double AA1,AA2,AA3,m1,m2,m3,z;*/



            }
        });
    }


        public double turun(double PmMax, double PmMin, double Prm){
            double u;
            if(Prm <= PmMin){ u = 1; }
            else if((PmMin <= Prm) && (Prm <= PmMax)){ u = (PmMax - Prm) / (PmMax - PmMin); }
            else { u = 0; }
            return u;
        }

        public double naik(double PmMax, double PmMin, double Prm){
            double u;
            if(Prm <= PmMin){ u = 0; }
            else if((PmMin <= Prm) && (Prm <= PmMax)){ u = (Prm - PmMin) / (PmMax - PmMin); }
            else { u = 1; }
            return u;
        }

        public double sedikit(double PrMax, double PrMin, double Pers){
            double u;
            if(Pers <= PrMin){ u = 1; }
            else if((PrMin <= Pers) && (Pers <= PrMax)){ u = (PrMax - Pers) / (PrMax - PrMin); }
            else { u = 0; }
            return u;
        }

        public double banyak(double PrMax, double PrMin, double Pers){
            double u;
            if(Pers <= PrMin){ u = 0; }
            else if((PrMin <= Pers) && (Pers <= PrMax)){ u = (Pers - PrMin) / (PrMax - PrMin); }
            else { u = 0; }
            return u;
        }


        void Rule(){
            rule = new String[27][4];
            rule[0][0]="murah";
            rule[1][0]="murah";
            rule[2][0]="murah";
            rule[3][0]="murah";
            rule[4][0]="sedang";
            rule[5][0]="sedang";
            rule[6][0]="sedang";
            rule[7][0]="sedang";
            rule[8][0]="murah";
            rule[9][0]="murah";
            rule[10][0]="murah";
            rule[11][0]="murah";
            rule[12][0]="murah";
            rule[13][0]="sedang";
            rule[14][0]="sedang";
            rule[15][0]="sedang";
            rule[16][0]="sedang";
            rule[17][0]="sedang";
            rule[18][0]="mahal";
            rule[19][0]="mahal";
            rule[20][0]="mahal";
            rule[21][0]="mahal";
            rule[22][0]="mahal";
            rule[23][0]="mahal";
            rule[24][0]="mahal";
            rule[25][0]="mahal";
            rule[26][0]="mahal";

            rule[0][1]="murah";
            rule[1][1]="murah";
            rule[2][1]="sedang";
            rule[3][1]="sedang";
            rule[4][1]="murah";
            rule[5][1]="murah";
            rule[6][1]="sedang";
            rule[7][1]="sedang";
            rule[8][1]="murah";
            rule[9][1]="sedang";
            rule[10][1]="mahal";
            rule[11][1]="mahal";
            rule[12][1]="mahal";
            rule[13][1]="murah";
            rule[14][1]="sedang";
            rule[15][1]="mahal";
            rule[16][1]="mahal";
            rule[17][1]="mahal";
            rule[18][1]="murah";
            rule[19][1]="murah";
            rule[20][1]="murah";
            rule[21][1]="sedang";
            rule[22][1]="sedang";
            rule[23][1]="sedang";
            rule[24][1]="mahal";
            rule[25][1]="mahal";
            rule[26][1]="mahal";

            rule[0][2]="murah";
            rule[1][2]="sedang";
            rule[2][2]="murah";
            rule[3][2]="sedang";
            rule[4][2]="murah";
            rule[5][2]="sedang";
            rule[6][2]="murah";
            rule[7][2]="sedang";
            rule[8][2]="mahal";
            rule[9][2]="mahal";
            rule[10][2]="murah";
            rule[11][2]="sedang";
            rule[12][2]="mahal";
            rule[13][2]="mahal";
            rule[14][2]="mahal";
            rule[15][2]="murah";
            rule[16][2]="sedang";
            rule[17][2]="mahal";
            rule[18][2]="murah";
            rule[19][2]="sedang";
            rule[20][2]="mahal";
            rule[21][2]="murah";
            rule[22][2]="sedang";
            rule[23][2]="mahal";
            rule[24][2]="murah";
            rule[25][2]="sedang";
            rule[26][2]="mahal";

            rule[0][3]="murah";
            rule[1][3]="murah";
            rule[2][3]="murah";
            rule[3][3]="murah";
            rule[4][3]="murah";
            rule[5][3]="murah";
            rule[6][3]="sedang";
            rule[7][3]="sedang";
            rule[8][3]="sedang";
            rule[9][3]="sedang";
            rule[10][3]="sedang";
            rule[11][3]="sedang";
            rule[12][3]="sedang";
            rule[13][3]="sedang";
            rule[14][3]="mahal";
            rule[15][3]="sedang";
            rule[16][3]="mahal";
            rule[17][3]="mahal";
            rule[18][3]="sedang";
            rule[19][3]="sedang";
            rule[20][3]="sedang";
            rule[21][3]="sedang";
            rule[22][3]="mahal";
            rule[23][3]="mahal";
            rule[24][3]="sedang";
            rule[25][3]="mahal";
            rule[26][3]="mahal";
        }

    void Fuzzyfikasi(double LuasTanah, double LuasBangunan, double SSB) {
        this.dLuasTanah     = new double[27];
        this.dLuasBangunan  = new double[27];
        this.dSsb           = new double[27];
        this.test           = new double[27][4];
        this.LuasTanah      = LuasTanah;
        this.LuasBangunan   = LuasBangunan;
        this.SSB            = SSB;
        for(int i=0;i<dLuasTanah.length;i++){
            if(rule[i][0].equalsIgnoreCase("murah")){
                if(LuasTanah <= 150){
                    dLuasTanah[i]=1;
                }
                else if(LuasTanah > 150 && LuasTanah < 250){
                    dLuasTanah[i]=(250-LuasTanah)/100;
                }
                else
                    dLuasTanah[i]=0;
            }
            else if(rule[i][0].equalsIgnoreCase("sedang")){
                if(LuasTanah <= 150 || LuasTanah >= 450){
                    dLuasTanah[i]=0;
                }
                else if(LuasTanah > 150 && LuasTanah < 250){
                    dLuasTanah[i]=(LuasTanah-150)/100;
                }
                else if(LuasTanah > 350 && LuasTanah < 450){
                    dLuasTanah[i]=(450-LuasTanah)/100;
                }
                else
                    dLuasTanah[i]=1;
            }
            else{
                if(LuasTanah <= 350){
                    dLuasTanah[i]=0;
                }
                else if(LuasTanah > 350 && LuasTanah < 450){
                    dLuasTanah[i]=(LuasTanah-350)/100;
                }
                else
                    dLuasTanah[i]=1;
            }
        }

        for(int i=0;i<dLuasBangunan.length;i++){
            if(rule[i][1].equalsIgnoreCase("murah")){
                if(LuasBangunan <= 150){
                    dLuasBangunan[i]=1;
                }
                else if(LuasBangunan > 150 && LuasBangunan<250){
                    dLuasBangunan[i]=(250-LuasBangunan)/100;
                }
                else
                    dLuasBangunan[i]=0;
            }
            else if(rule[i][1].equalsIgnoreCase("sedang")){
                if(LuasBangunan <= 150 || LuasBangunan >= 450){
                    dLuasBangunan[i]=0;
                }
                else if(LuasBangunan > 150 && LuasBangunan < 250){
                    dLuasBangunan[i]=(LuasBangunan-150)/100;
                }
                else if(LuasBangunan > 350 && LuasBangunan < 450){
                    dLuasBangunan[i]=(450-LuasBangunan)/100;
                }
                else
                    dLuasBangunan[i]=1;
            }
            else{
                if(LuasBangunan <= 350){
                    dLuasBangunan[i]=0;
                }
                else if(LuasBangunan > 350 && LuasBangunan < 450){
                    dLuasBangunan[i]=(LuasBangunan-350)/100;
                }
                else
                    dLuasBangunan[i]=1;
            }
        }

        for(int i=0;i<dSsb.length;i++){
            if(rule[i][2].equalsIgnoreCase("murah")){
                if(SSB <= 15){
                    dSsb[i]=1;
                }
                else if(SSB > 15 && SSB < 30){
                    dSsb[i]=(30-SSB)/15;
                }
                else
                    dSsb[i]=0;
            }
            else if(rule[i][2].equalsIgnoreCase("sedang")){
                if(SSB <= 15 || SSB >= 50){
                    dSsb[i]=0;
                }
                else if(SSB > 15 && SSB < 30){
                    dSsb[i]=(SSB-15)/15;
                }
                else if(SSB > 40 && SSB < 50){
                    dSsb[i]=(50-SSB)/10;
                }
                else
                    dSsb[i]=1;
            }
            else{
                if(SSB <= 40){
                    dSsb[i]=0;
                }
                else if(SSB > 40 && SSB < 50){
                    dSsb[i]=(SSB-40)/10;
                }
                else
                    dSsb[i]=1;
            }
        }

        for(int i=0;i<27;i++){
            test [i][0]=dLuasTanah[i];
        }

        for(int i=0;i<27;i++){
            test [i][1]=dLuasBangunan[i];
        }

        for(int i=0;i<27;i++){
            test [i][2]=dSsb[i];
        }
    }


    void ImplikasiMin() {
        aPredikat = new double [27];
        for(int i=0;i<27;i++){
            aPredikat[i]=Math.min(dLuasTanah[i],Math.min(dLuasBangunan[i],dSsb[i]));
            test [i][3]=aPredikat[i];
        }
    }

    void ImplikasiMax() {
        this.dHargaJual = new double[3];
        max1 = 0;
        max2 = 0;
        max3 = 0;
        for(int i=0;i<27;i++){
            if(rule[i][3].equalsIgnoreCase("murah")){
                if(aPredikat[i]> max1){
                    max1=aPredikat[i];
                }

            }

            else if(rule[i][3].equalsIgnoreCase("sedang")){
                if(aPredikat[i]>max2){
                    max2=aPredikat[i];
                }

            }
            else{
                if(aPredikat[i]>max3){
                    max3=aPredikat[i];
                }

            }
        }

        dHargaJual[0]=max1;
        dHargaJual[1]=max2;
        dHargaJual[2]=max3;
    }


    void BatasArea() {
        a1=350;
        if(dHargaJual[0]>dHargaJual[1]){
            a2=560-(dHargaJual[0]*(560-550));
            a3=560-(dHargaJual[1]*(560-550));
        }
        else{
            a2=(dHargaJual[0]*(560-550))+550;
            a3=(dHargaJual[1]*(560-550))+550;
        }
        if(dHargaJual[1]>dHargaJual[2]){
            a4=860-(dHargaJual[1]*(860-850));
            a5=860-(dHargaJual[2]*(860-850));
        }
        else{
            a4=(dHargaJual[1]*(860-850))+850;
            a5=(dHargaJual[2]*(860-850))+850;
        }
        a6=1300;
    }

    void Momen() {
        momen = new double [5];
        momen[0]=(dHargaJual[0]/2*Math.pow(a2,2))-(dHargaJual[0]/2*Math.pow(a1,2));
        if(dHargaJual[0]>dHargaJual[1]){
            momen[1] = (1./(560-550))*((((560*Math.pow(a3,2))/2)-((1./3)*Math.pow(a3,3)))-(((560*Math.pow(a2,2))/2)-((1./3)*Math.pow(a2,3))));
        }
        else{
            momen[1] = (1./(560-550))*((((1./3)*Math.pow(a3,3))-((550*Math.pow(a3,2))/2))-(((1./3)*Math.pow(a2,3))-((550*Math.pow(a2,2))/2)));
        }

        momen[2]=(dHargaJual[1]/2*Math.pow(a4,2))-(dHargaJual[1]/2*Math.pow(a3,2));

        if(dHargaJual[1]>dHargaJual[2]){
            momen[3] =(1./(860-850))*((((860*Math.pow(a5,2))/2)-((1./3)*Math.pow(a5,3)))-(((860*Math.pow(a4,2))/2)-((1./3)*Math.pow(a4,3))));
        }
        else{
            momen[3] = (1./(860-850))*((((1./3)*Math.pow(a5,3))-((850*Math.pow(a5,2))/2))-(((1./3)*Math.pow(a4,3))-((850*Math.pow(a4,2))/2)));
        }
        momen[4]=(dHargaJual[2]/2*Math.pow(a6,2))-(dHargaJual[2]/2*Math.pow(a5,2));
    }

    void Luas() {
        luas = new double [5];
        luas[0]=(dHargaJual[0]*a2)-(dHargaJual[0]*a1);

        if(dHargaJual[0] > dHargaJual[1]){
            luas[1] = ((Math.pow(a3, 2)/20)-(55*a3)) - ((Math.pow(a2, 2)/20)-(55*a2));
        }

        else {
            luas[1] = ((56*a3)-(Math.pow(a3,2)/20)) - ((56*a2)-(Math.pow(a2,2)/20));
        }

        luas[2]=(dHargaJual[1]*a4)-(dHargaJual[1]*a3);

        if(dHargaJual[1] > dHargaJual[2]){
            luas[3] = ((86*a5)-((a5*a5)/20)) - ((86*a4)-((a4*a4)/20));
        }

        else {
            luas[3] = ((Math.pow(a5,2)/20)-((85*a5)))-((Math.pow(a4,2)/20)-((85*a4)));
        }

        luas[4]=(dHargaJual[2]*a6)-(dHargaJual[2]*a5);
    }

    void MomenLuas() {
        jumlahMomen=0;
        jumlahLuas=0;
        for(int i=0;i<momen.length;i++){
            jumlahMomen+=momen[i];
        }

        for(int i=0;i<luas.length;i++){
            jumlahLuas+=luas[i];
        }
    }

    void HargaJual() {
        HargaJual= jumlahMomen/jumlahLuas;
    }

    double getHargaJual(){
        return HargaJual;
    }

}
