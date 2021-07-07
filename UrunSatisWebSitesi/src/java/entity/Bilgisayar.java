package entity;

public class Bilgisayar extends Urunler {

    private String islemci;
    private String ram;
    private String ekranKarti;
    private String hardDisk;
    private String pcTip;

    public Bilgisayar() {
    }

    public Bilgisayar(String islemci, String ram, String ekranKarti, String hardDisk, String pcTip, int urun_id, String gorsel, int stok_sayisi, String marka, String model, double fiyat, int garantiSuresi) {
        super(urun_id, gorsel, stok_sayisi, marka, model, fiyat, garantiSuresi);
        this.islemci = islemci;
        this.ram = ram;
        this.ekranKarti = ekranKarti;
        this.hardDisk = hardDisk;
        this.pcTip = pcTip;
    }

    public String getIslemci() {
        return islemci;
    }

    public void setIslemci(String islemci) {
        this.islemci = islemci;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getEkranKarti() {
        return ekranKarti;
    }

    public void setEkranKarti(String ekranKarti) {
        this.ekranKarti = ekranKarti;
    }

    public String getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(String hardDisk) {
        this.hardDisk = hardDisk;
    }

    public String getPcTip() {
        return pcTip;
    }

    public void setPcTip(String pcTip) {
        this.pcTip = pcTip;
    }

}
