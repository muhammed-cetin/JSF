package Test;

import dao.KullaniciDao;
import entity.Kullanici;
import entity.Rol;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;

public class KullaniciTestleri {

    private KullaniciDao kullaniciDao;

    @Test
    public void kullaniciEkleme() {
        Kullanici kullanici = new Kullanici();
        kullanici.setAd("TEst");
        kullanici.setSoyad("Soyad");
        kullanici.setEmail("TestEmail");
        kullanici.setKullaniciAdi("TestAd");
        kullanici.setTelefon("TestTel");
        kullanici.setParola("TestParola");
        int kullaniciId = getKullaniciDao().kullaniciEkle(kullanici);
        assertTrue(kullaniciId != 0); //Kullanıcı ekleme testi
        kullanici.setId(kullaniciId);
        assertTrue(getKullaniciDao().girisYap("TestAd", "TestParola") != null);// giriş testi
        assertTrue(getKullaniciDao().girisYap("TestAd", "sdadss") == null);
        assertTrue(kullaniciGuncelleme(kullanici));
        assertTrue(kullaniciSilme(kullaniciId));

    }

    public boolean kullaniciGuncelleme(Kullanici kullanici) {
        kullanici.setAd("TEst Guncelleme");
        kullanici.setSoyad("Soyad Guncelleme");
        kullanici.setEmail("TestEmail Guncelleme");
        kullanici.setKullaniciAdi("TestAd");
        kullanici.setTelefon("TestTel");
        kullanici.setParola("TestParola");
        kullanici.setRol(Rol.KULLANICI);
        return getKullaniciDao().guncelle(kullanici); //Kullanıcı guncelleme testi
    }

    public boolean kullaniciSilme(int kullaniciId) {
        return getKullaniciDao().sil(kullaniciId);
    }

    public KullaniciDao getKullaniciDao() {
        if (kullaniciDao == null) {
            kullaniciDao = KullaniciDao.getInstance();
        }
        return kullaniciDao;
    }

}
