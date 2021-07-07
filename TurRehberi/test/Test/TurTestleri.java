package Test;

import dao.KullaniciDao;
import dao.TurDao;
import entity.Tur;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;

public class TurTestleri {

    private TurDao turDao;
    private KullaniciDao kullaniciDao;

    @Test
    public void turTestleri() {
        Tur tur = new Tur();
        tur.setGorsel("gorsel.jpg");
        tur.setBaslik("TestBaslik");
        tur.setAciklama("TestAcıklama");
        tur.setUcret(150);
        tur.setKontejan(20);
        tur.setRota("Ankara-İstanbul-Eskişehir");
        tur.setHizmetler("TestHizmetler");
        tur.setTur_id(getTurDao().turEkle(tur));
        assertTrue(tur.getTur_id() != 0);
        tur.setBaslik("Guncelleme Test");
        tur.setAciklama("Guncelleme Aciklama");
        assertTrue(getTurDao().guncelle(tur));
        assertTrue(getTurDao().sil(tur.getTur_id()));

    }

    public TurDao getTurDao() {
        if (turDao == null) {
            turDao = TurDao.getInstance();
        }
        return turDao;
    }

    public KullaniciDao getKullaniciDao() {
        if (kullaniciDao == null) {
            kullaniciDao = KullaniciDao.getInstance();
        }
        return kullaniciDao;
    }

}
