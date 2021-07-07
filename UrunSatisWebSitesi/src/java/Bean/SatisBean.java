package Bean;

import dao.SatisDao;
import entity.Satis;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SatisBean implements Serializable {

    private SatisDao satisDao;
    private List<Satis> satisList;

    public SatisBean() {
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

    public List<Satis> getSatisList() {
        satisList = getSatisDao().listele();
        return satisList;
    }

    public void setSatisList(List<Satis> satisList) {
        this.satisList = satisList;
    }

}
