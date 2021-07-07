package entity;

public class Satis {

    private int satis_id;
    private String musteriAd;
    private String musteriSoyad;
    private String urunMarka;
    private String urunModel;
    private String tarih;
    private int adet;

    public Satis() {
    }

    public Satis(int satis_id, String musteriAd, String musteriSoyad, String urunMarka, String urunModel, String tarih, int adet) {
        this.satis_id = satis_id;
        this.musteriAd = musteriAd;
        this.musteriSoyad = musteriSoyad;
        this.urunMarka = urunMarka;
        this.urunModel = urunModel;
        this.tarih = tarih;
        this.adet = adet;
    }

    public int getSatis_id() {
        return satis_id;
    }

    public void setSatis_id(int satis_id) {
        this.satis_id = satis_id;
    }

    public String getMusteriAd() {
        return musteriAd;
    }

    public void setMusteriAd(String musteriAd) {
        this.musteriAd = musteriAd;
    }

    public String getMusteriSoyad() {
        return musteriSoyad;
    }

    public void setMusteriSoyad(String musteriSoyad) {
        this.musteriSoyad = musteriSoyad;
    }

    public String getUrunMarka() {
        return urunMarka;
    }

    public void setUrunMarka(String urunMarka) {
        this.urunMarka = urunMarka;
    }

    public String getUrunModel() {
        return urunModel;
    }

    public void setUrunModel(String urunModel) {
        this.urunModel = urunModel;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

    
}
