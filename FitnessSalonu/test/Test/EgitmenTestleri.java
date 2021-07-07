package Test;

import dao.EgitmenDAO;
import entity.Egitmen;
import java.util.List;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;

public class EgitmenTestleri {
    
    private final EgitmenDAO egitmenDao = EgitmenDAO.getAlinanEgitimDao();
    
    @Test
    public void egitimTestleri() {
        
        Egitmen egitmen = new Egitmen();
        
        egitmen.setEgitmen_ad("adTest");
        egitmen.setEgitmen_soyad("soyadTest");
        egitmen.setEgitmen_cinsiyet("Erkek");
        egitmen.setEgitmen_yas(25);
        egitmen.setEgitmen_cep_telefonu("05989888888");
        egitmen.setEgitmen_email("adSoyadTest@gmail.com");
        egitmen.setUz_alan("uzmanlıkTest");
        egitmen.setTecrube("tecrubeTest");
        assertTrue(egitmenDao.insert(egitmen));
        List<Egitmen> egitmenler = egitmenDao.findAll("", 1, 100);
        assertTrue(!egitmenler.isEmpty());
        egitmen.setEgitmen_ad("adTestUpdate");
        egitmen.setEgitmen_id(egitmenler.get(egitmenler.size() - 1).getEgitmen_id());
        assertTrue(egitmenDao.update(egitmen));
        assertTrue(egitmenDao.delete(egitmenler.get(egitmenler.size() - 1)));
    }
}
