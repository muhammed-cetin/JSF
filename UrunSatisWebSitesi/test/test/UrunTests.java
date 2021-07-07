package test;

import dao.BilgisayarDao;
import entity.Bilgisayar;
import java.util.List;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;

public class UrunTests {
    
    private BilgisayarDao bilgisayarDao;
    
    @Test
    public void bilgisayarUrunCrudTest() {
        Bilgisayar bilgisayar = new Bilgisayar();
        bilgisayar.setMarka("Monster");
        bilgisayar.setModel("Abra-A5");
        bilgisayar.setFiyat(8800);
        bilgisayar.setGarantiSuresi(2);
        bilgisayar.setStok_sayisi(20);
        bilgisayar.setGorsel("/image/monster.jpg");
        bilgisayar.setEkranKarti("GTX 1060 Ti");
        bilgisayar.setHardDisk("512 GB SSD");
        bilgisayar.setIslemci("Intel i7");
        bilgisayar.setRam("16 Gb");
        bilgisayar.setPcTip("Laptop");
        assertTrue(getBilgisayarDao().ekle(bilgisayar));
        List<Bilgisayar> list = getBilgisayarDao().listele();
        assertTrue(!list.isEmpty());
        bilgisayar = list.get(list.size()-1);
        bilgisayar.setEkranKarti("Guncelleme");
        assertTrue(getBilgisayarDao().guncelle(bilgisayar));
        assertTrue(getBilgisayarDao().sil(bilgisayar.getUrun_id()));
        
    }
    
    public BilgisayarDao getBilgisayarDao() {
        if (bilgisayarDao == null) {
            bilgisayarDao = BilgisayarDao.getInstance();
        }
        return bilgisayarDao;
    }
    
}
