package entity;

public class Televizyon extends Urunler {

    private String ekranBoyutu;
    private String cozunurlukTuru;
    private String baglantiTuru;

    public Televizyon() {
    }

    public Televizyon(String ekranBoyutu, String cozunurlukTuru, String baglantiTuru, int urun_id, String gorsel, int stok_sayisi, String marka, String model, double fiyat, int garantiSuresi) {
        super(urun_id, gorsel, stok_sayisi, marka, model, fiyat, garantiSuresi);
        this.ekranBoyutu = ekranBoyutu;
        this.cozunurlukTuru = cozunurlukTuru;
        this.baglantiTuru = baglantiTuru;
    }

    public String getEkranBoyutu() {
        return ekranBoyutu;
    }

    public void setEkranBoyutu(String ekranBoyutu) {
        this.ekranBoyutu = ekranBoyutu;
    }

    public String getCozunurlukTuru() {
        return cozunurlukTuru;
    }

    public void setCozunurlukTuru(String cozunurlukTuru) {
        this.cozunurlukTuru = cozunurlukTuru;
    }

    public String getBaglantiTuru() {
        return baglantiTuru;
    }

    public void setBaglantiTuru(String baglantiTuru) {
        this.baglantiTuru = baglantiTuru;
    }

}
