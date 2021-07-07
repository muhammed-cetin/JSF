package entity;

import java.util.List;

public class Kullanici {

    private int id;
    private String ad;
    private String soyad;
    private String email;
    private String kullaniciAdi;
    private String telefon;
    private String parola;
    private List<Tur> turlarim;
    private Rol rol;

    public Kullanici() {
    }

    public Kullanici(int id, String ad, String soyad, String email, String kullaniciAdi, String telefon, String parola, List<Tur> turlarim, Rol rol) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
        this.kullaniciAdi = kullaniciAdi;
        this.telefon = telefon;
        this.parola = parola;
        this.turlarim = turlarim;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public List<Tur> getTurlarim() {
        return turlarim;
    }

    public void setTurlarim(List<Tur> turlarim) {
        this.turlarim = turlarim;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
