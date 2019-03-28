package com.example.sandykurniawan.skripsi_maps;

/**
 * Created by Sandy Kurniawan on 2/22/2018.
 */

public class Lokasi {
    public double lat = 0;
    public double lng = 0;
    public int category = 0;
    public String lokname = "";

    public Lokasi(double plat, double plng, int pcategory, String sname){
        this.lat = plat;
        this.lng = plng;
        this.category = pcategory;
        this.lokname = sname;
    }
}
