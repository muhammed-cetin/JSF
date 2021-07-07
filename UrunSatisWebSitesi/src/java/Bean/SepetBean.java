package Bean;

import dao.SatisDao;
import entity.Kullanici;
import entity.Urunler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class SepetBean implements Serializable {

    private List<Urunler> sepet;
    private double toplamTutar = 0;
    private SatisDao satisDao;

    public SepetBean() {
    }

    public String sepeteEkle(Urunler urun) {
        toplamTutar += urun.getFiyat();
        getSepet().add(urun);
        return "sepet";
    }

    public String siparisVer() {
        Kullanici kull = (Kullanici) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("kullanici");
        getSatisDao().siparisEkle(sepet, kull);
        return "index";
    }

    public String sepettenCikar(Urunler urun) {
        toplamTutar -= urun.getFiyat();
        getSepet().remove(urun);
        return "sepet";
    }

    public List<Urunler> getSepet() {
        if (sepet == null) {
            sepet = new ArrayList<>();
        }
        return sepet;
    }

    public void setSepet(List<Urunler> sepet) {
        this.sepet = sepet;
    }

    public double getToplamTutar() {
        return toplamTutar;
    }

    public void setToplamTutar(double toplamTutar) {
        this.toplamTutar = toplamTutar;
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

}
