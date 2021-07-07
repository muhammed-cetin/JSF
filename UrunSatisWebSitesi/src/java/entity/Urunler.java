package entity;

public class Urunler {

    private int urun_id;
    private String gorsel;
    private int stok_sayisi;
    private String marka;
    private String model;
    private double fiyat;
    private int garantiSuresi;

    public Urunler() {
    }

    public Urunler(int urun_id, String gorsel, int stok_sayisi, String marka, String model, double fiyat, int garantiSuresi) {
        this.urun_id = urun_id;
        this.gorsel = gorsel;
        this.stok_sayisi = stok_sayisi;
        this.marka = marka;
        this.model = model;
        this.fiyat = fiyat;
        this.garantiSuresi = garantiSuresi;
    }

    public int getUrun_id() {
        return urun_id;
    }

    public void setUrun_id(int urun_id) {
        this.urun_id = urun_id;
    }

    public int getStok_sayisi() {
        return stok_sayisi;
    }

    public void setStok_sayisi(int stok_sayisi) {
        this.stok_sayisi = stok_sayisi;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public int getGarantiSuresi() {
        return garantiSuresi;
    }

    public void setGarantiSuresi(int garantiSuresi) {
        this.garantiSuresi = garantiSuresi;
    }

    public String getGorsel() {
        return gorsel;
    }

    public void setGorsel(String gorsel) {
        this.gorsel = gorsel;
    }

    public String urunString() {
        return "urun_id,gorsel,stokSayisi,marka,model,fiyat,garantiSuresi,";
    }

    public String urunGuncelle() {
        return "gorsel=?,stokSayisi=?,marka=?,model=?,fiyat=?,garantiSuresi=?,";
    }
}
