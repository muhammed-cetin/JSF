package Test;

import dao.KullaniciDao;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;

public class girisTest {

    private KullaniciDao kullaniciDao;

    @Test
    public void girisTesleri() {
        assertTrue(getKullaniciDao().girisYap("dene@gmail.com", "123") != null);
        assertTrue(getKullaniciDao().girisYap("dene@gmail.com", "147852") == null);
        
    }

    public KullaniciDao getKullaniciDao() {
        if (kullaniciDao == null) {
            kullaniciDao = KullaniciDao.getInstance();
        }
        return kullaniciDao;
    }

}
