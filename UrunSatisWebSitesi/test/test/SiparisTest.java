package test;

import dao.BilgisayarDao;
import dao.KullaniciDao;
import dao.SatisDao;
import entity.Bilgisayar;
import entity.Kullanici;
import entity.Urunler;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;

public class SiparisTest {

    private SatisDao satisDao;
    private KullaniciDao kullaniciDao;
    private BilgisayarDao bilgisayarDao;

    @Test
    public void siparisTest() {
        Kullanici kull = getKullaniciDao().girisYap("m.cetiinnm@gmail.com", "123");
        assertTrue(kull != null);
        List<Bilgisayar> bilgisayarlar = getBilgisayarDao().listele();
        List<Urunler> urunler = new ArrayList<>();
        for (Bilgisayar bilgisayar : bilgisayarlar) {
            urunler.add(bilgisayar);
        }
        assertTrue(getSatisDao().siparisEkle(urunler, kull));
    }

    public SatisDao getSatisDao() {
        if (satisDao == null) {
            satisDao = SatisDao.getInstance();
        }
        return satisDao;
    }

    public void setSatisDao(SatisDao satisDao) {
        this.satisDao = satisDao;
    }

    public KullaniciDao getKullaniciDao() {
        if (kullaniciDao == null) {
            kullaniciDao = KullaniciDao.getInstance();
        }
        return kullaniciDao;
    }

    public void setKullaniciDao(KullaniciDao kullaniciDao) {
        this.kullaniciDao = kullaniciDao;
    }

    public BilgisayarDao getBilgisayarDao() {
        if (bilgisayarDao == null) {
            bilgisayarDao = BilgisayarDao.getInstance();
        }
        return bilgisayarDao;
    }

    public void setBilgisayarDao(BilgisayarDao bilgisayarDao) {
        this.bilgisayarDao = bilgisayarDao;
    }

}
