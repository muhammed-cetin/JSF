package test;

import dao.KullaniciDao;
import entity.Kullanici;
import java.util.List;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;

public class KullaniciTest {

    private KullaniciDao kullaniciDao;

    @Test
    public void KullaniciTestleri() {
        Kullanici kullanici = new Kullanici();
        kullanici.setAd("KullaniciAdTest");
        kullanici.setSoyad("KullaniciSoyadTest");
        kullanici.setMail("kullaniciTestmail@gmail.com");
        kullanici.setParola("123456");
        kullanici.setTelefon("05155151515");
        kullanici.setAdres("Kullanici Adres Test");
        kullanici.setAdmin(true);
        int kullaniciId = getKullaniciDao().kullaniciEkle(kullanici);
        assertTrue(kullaniciId != 0); //Kullanıcı ekleme testi
        kullanici.setKulllanici_id(kullaniciId);
        assertTrue(getKullaniciDao().girisYap("kullaniciTestmail@gmail.com", "123456") != null);// giriş testi
        assertTrue(getKullaniciDao().girisYap("kullaniciTestmail@gmail.com", "11111") == null);

        List<Kullanici> kullanicilar = kullaniciDao.listele();
        Kullanici guncellenecek_kullanici = kullanicilar.get(kullanicilar.size() - 1);
        guncellenecek_kullanici.setAd("Guncellendi");
        assertTrue(kullaniciDao.guncelle(guncellenecek_kullanici)); // güncelleme
        assertTrue(kullaniciDao.sil(guncellenecek_kullanici.getKulllanici_id())); // silme
    }

    public KullaniciDao getKullaniciDao() {
        if (kullaniciDao == null) {
            kullaniciDao = KullaniciDao.getInstance();
        }
        return kullaniciDao;
    }

}
