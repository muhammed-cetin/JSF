package entity;

public class Telefon extends Urunler {

    private String kamera;
    private String ekranBoyutu;
    private String pilOmru;
    private String kapasite;
    private String isletimSistemi;

    public Telefon() {
    }

    public Telefon(String kamera, String ekranBoyutu, String pilOmru, String kapasite, String isletimSistemi, int urun_id, String gorsel, int stok_sayisi, String marka, String model, double fiyat, int garantiSuresi) {
        super(urun_id, gorsel, stok_sayisi, marka, model, fiyat, garantiSuresi);
        this.kamera = kamera;
        this.ekranBoyutu = ekranBoyutu;
        this.pilOmru = pilOmru;
        this.kapasite = kapasite;
        this.isletimSistemi = isletimSistemi;
    }

    public String getKamera() {
        return kamera;
    }

    public void setKamera(String kamera) {
        this.kamera = kamera;
    }

    public String getEkranBoyutu() {
        return ekranBoyutu;
    }

    public void setEkranBoyutu(String ekranBoyutu) {
        this.ekranBoyutu = ekranBoyutu;
    }

    public String getPilOmru() {
        return pilOmru;
    }

    public void setPilOmru(String pilOmru) {
        this.pilOmru = pilOmru;
    }

    public String getKapasite() {
        return kapasite;
    }

    public void setKapasite(String kapasite) {
        this.kapasite = kapasite;
    }

    public String getIsletimSistemi() {
        return isletimSistemi;
    }

    public void setIsletimSistemi(String isletimSistemi) {
        this.isletimSistemi = isletimSistemi;
    }

}
