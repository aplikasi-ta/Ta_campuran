package com.trans.lampung.driver;

/**
 * Created by Riqi on 21/12/2016.
 */
public class Profile {
    private String nama ="";
    private String nsim ="";
    private String alamat ="";
    private String jekel ="";
    private String pnumber ="";
    private String email ="";
    private String latitude ="";
    private String longitude ="";
    private String status ="";
    private String jurusan = "";
    private String tarif = "";
    public Profile()
    {
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat= alamat;
    }

    public String getJekel() {
        return jekel;
    }
    public void setJekel(String jekel) {
        this.jekel = jekel;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status= status;
    }

    public String getNsim() {
        return nsim;
    }
    public void setNsim(String nsim) {
        this.nsim= nsim;
    }

    public String getJurusan() {
        return jurusan;
    }
    public void setJurusan(String jurusan) {
        this.jurusan= jurusan;
    }

    public String getTarif() {
        return tarif;
    }
    public void setTarif(String tarif) {
        this.tarif= tarif;
    }

    public String getPnumber() {
        return pnumber;
    }
    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}