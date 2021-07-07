package Test;

import dao.OlcumDAO;
import entity.Olcum;
import java.util.List;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;

public class OlcumTestleri {

    private final OlcumDAO olcumDao = OlcumDAO.getOlcumDao();

    @Test
    public void olcumTest() {
        Olcum olcum = new Olcum();
        olcum.setBoy(183);
        olcum.setKilo(85);
        olcum.setVucut_kitle_indeksi(olcum.getKilo() / Math.pow(olcum.getBoy(), 2) * 10000);
        assertTrue(olcumDao.insert(olcum));
        List<Olcum> olcumler = olcumDao.findAll(1, 100);
        assertTrue(!olcumler.isEmpty());
        olcum.setKilo(95);
        olcum.setOlcum_id(olcumler.get(olcumler.size() - 1).getOlcum_id());
        assertTrue(olcumDao.update(olcum));
        assertTrue(olcumDao.delete(olcumler.get(olcumler.size() - 1)));
    }
}
