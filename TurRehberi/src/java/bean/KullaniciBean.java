package bean;

import dao.GozlemciDao;
import dao.KullaniciDao;
import dao.TurDao;
import entity.Kayit;
import entity.Kullanici;
import entity.Tur;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class KullaniciBean implements Serializable {

    private KullaniciDao kdao;
    private List<Kullanici> kullaniciList;
    private List<Tur> kullaniciTurlari;
    private TurDao turDao;
    private Kullanici kullanici;
    private boolean girisKontrol = false, admin = false;
    private String kullaniciAdi, parola, mesaj;
    private List<Kayit> kayitlar;
    private GozlemciDao gozlemciDao;

    public KullaniciBean() {
    }

    public String kullaniciSayfasi(Kullanici kull) {
        if (kull != null) {
            kullanici = kull;
        }
        return "kullanici";
    }

    public String girisYapSayfasi() {
        return "girisyap";
    }

    public String cikisYap() {
        kullanici = null;
        girisKontrol = false;
        mesaj = null;
        admin = false;
        parola = null;
        kullaniciAdi = null;
        return "index";
    }

    public String girisYap() {
        Kullanici kull = getKdao().girisYap(kullaniciAdi, parola);
        if (kull != null) {
            mesaj = "Giris Başarılı";
            kullanici = kull;
            setGirisKontrol(true);
            setAdmin(kull.getRol().ordinal() == 0);

        } else {
            mesaj = "Kullanıcı adı veya parola yanlış";
            setGirisKontrol(false);
            return "girisyap";
        }
        return "index";
    }

    public KullaniciDao getKdao() {
        if (kdao == null) {
            kdao = KullaniciDao.getInstance();
        }
        return kdao;
    }

    public String kaydet() {
        if (kullanici.getId() == 0) {
            getKdao().ekle(kullanici);
        } else {
            getKdao().guncelle(kullanici);
        }
        kullanici = new Kullanici();
        return "index";
    }

    public String basvuruYap(int turId) {
        getTurDao().kullaniciTurEkle(kullanici.getId(), turId);
        return "turlarim";
    }

    public String basvuruIptal(int turId) {
        getTurDao().kullaniciTurSil(kullanici.getId(), turId);
        return "turlarim";
    }

    public String silme(int id) {
        getKdao().sil(id);
        return "index";
    }

    public List<Kayit> getKayitlar() {
        kayitlar = getGozlemciDao().getKayitlar();
        return kayitlar;
    }

    public void setKayitlar(List<Kayit> kayitlar) {
        this.kayitlar = kayitlar;
    }

    public Kullanici getKullanici() {
        if (kullanici == null) {
            kullanici = new Kullanici();
        }
        return kullanici;
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }

    public List<Kullanici> getKullaniciList() {
        kullaniciList = getKdao().listele();
        return kullaniciList;
    }

    public void setKullaniciList(List<Kullanici> kullaniciList) {
        this.kullaniciList = kullaniciList;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getMesaj() {
        return mesaj;
    }

    public boolean isGirisKontrol() {
        return girisKontrol;
    }

    public void setGirisKontrol(boolean girisKontrol) {
        this.girisKontrol = girisKontrol;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public List<Tur> getKullaniciTurlari() {
        kullaniciTurlari = getTurDao().listele(kullanici.getId());
        return kullaniciTurlari;
    }

    public void setKullaniciTurlari(List<Tur> kullaniciTurlari) {
        this.kullaniciTurlari = kullaniciTurlari;
    }

    public TurDao getTurDao() {
        if (turDao == null) {
            turDao = TurDao.getInstance();
        }
        return turDao;
    }

    public void setTurDao(TurDao turDao) {
        this.turDao = turDao;
    }

    public GozlemciDao getGozlemciDao() {
        if (gozlemciDao == null) {
            gozlemciDao = GozlemciDao.getGozlemciDao();
        }
        return gozlemciDao;
    }

}
