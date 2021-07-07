package entity;

import java.util.Date;

public class Kayit {

    private int id;
    private String icerik;
    private Date tarih;

    public Kayit() {
    }

    public Kayit(int id, String icerik, Date tarih) {
        this.id = id;
        this.icerik = icerik;
        this.tarih = tarih;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

}
