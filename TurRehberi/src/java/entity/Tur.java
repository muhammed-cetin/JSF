package entity;

public class Tur {

    private int tur_id;
    private String gorsel;
    private String baslik;
    private String aciklama;
    private int ucret;
    private int kontejan;
    private String rota;
    private String hizmetler;

    public Tur() {
    }

    public Tur(int tur_id, String gorsel, String baslik, String aciklama, int ucret, int kontejan, String rota, String hizmetler) {
        this.tur_id = tur_id;
        this.gorsel = gorsel;
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.ucret = ucret;
        this.kontejan = kontejan;
        this.rota = rota;
        this.hizmetler = hizmetler;
    }

    public int getTur_id() {
        return tur_id;
    }

    public void setTur_id(int tur_id) {
        this.tur_id = tur_id;
    }

    public String getGorsel() {
        return gorsel;
    }

    public void setGorsel(String gorsel) {
        this.gorsel = gorsel;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public int getUcret() {
        return ucret;
    }

    public void setUcret(int ucret) {
        this.ucret = ucret;
    }

    public int getKontejan() {
        return kontejan;
    }

    public void setKontejan(int kontejan) {
        this.kontejan = kontejan;
    }

    public String getRota() {
        return rota;
    }

    public void setRota(String rota) {
        this.rota = rota;
    }

    public String getHizmetler() {
        return hizmetler;
    }

    public void setHizmetler(String hizmetler) {
        this.hizmetler = hizmetler;
    }

}
