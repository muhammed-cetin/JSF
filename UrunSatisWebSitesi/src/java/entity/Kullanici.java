package entity;

public class Kullanici {

    private int kulllanici_id;
    private String ad;
    private String soyad;
    private String mail;
    private String parola;
    private String telefon;
    private String adres;
    private boolean admin;

    public Kullanici() {
    }

    public Kullanici(int kulllanici_id, String ad, String soyad, String mail, String parola, String telefon, String adres, boolean admin) {
        this.kulllanici_id = kulllanici_id;
        this.ad = ad;
        this.soyad = soyad;
        this.mail = mail;
        this.parola = parola;
        this.telefon = telefon;
        this.adres = adres;
        this.admin = admin;
    }

    public int getKulllanici_id() {
        return kulllanici_id;
    }

    public void setKulllanici_id(int kulllanici_id) {
        this.kulllanici_id = kulllanici_id;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
