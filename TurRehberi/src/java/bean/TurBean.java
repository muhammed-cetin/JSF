package bean;

import dao.TurDao;
import entity.Tur;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@SessionScoped
public class TurBean implements Serializable {

    private TurDao turDao;
    private List<Tur> turList;
    private Tur tur;
    private final String yol = "C:/Users/c1to/Documents/NetBeansProjects/TurRehberi/web/images/";
    private Part dos;

    public String turSayfasi(Tur turr) {
        if (turr != null) {
            tur = turr;
        }
        return "/yetkili/tur";
    }
    
    public String turlar(){
        return "/yetkili/turlar";
    }

    public String kaydet() {
        try {
            if (dos != null) {
                InputStream veriGirisi = dos.getInputStream();
                File f = new File(yol + dos.getSubmittedFileName());
                tur.setGorsel("/images/" + f.getName());
                Files.copy(veriGirisi, f.toPath());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        if (tur.getTur_id() == 0) {
            getTurDao().ekle(tur);
        } else {
            getTurDao().guncelle(tur);
        }
        tur = new Tur();
        return "/yetkili/turlar";
    }

    public String silme(int id) {
        getTurDao().sil(id);
        return "/yetkili/turlar";
    }

    public TurDao getTurDao() {
        if (turDao == null) {
            turDao = TurDao.getInstance();
        }
        return turDao;
    }

    public void setTurDao(TurDao turDao) {
        this.turDao = turDao;
    }

    public List<Tur> getTurList() {
        turList = getTurDao().listele();
        return turList;
    }

    public void setTurList(List<Tur> turList) {
        this.turList = turList;
    }

    public Tur getTur() {
        if (tur == null) {
            tur = new Tur();
        }
        return tur;
    }

    public void setTur(Tur tur) {
        turList = getTurDao().listele();
        this.tur = tur;
    }

    public Part getDos() {
        return dos;
    }

    public void setDos(Part dos) {
        this.dos = dos;
    }

}
