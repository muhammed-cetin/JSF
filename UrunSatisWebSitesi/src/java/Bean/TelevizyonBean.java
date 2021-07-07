package Bean;

import dao.TelevizyonDao;
import entity.Televizyon;
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
public class TelevizyonBean implements Serializable {

    private TelevizyonDao tvDao;
    private List<Televizyon> tvList;
    private Televizyon tv;

    private final String yol = "C:/Users/c1to/Documents/NetBeansProjects/UrunSatisWebSitesi/web/images/";
    private Part dos;

    public TelevizyonBean() {
    }

    public TelevizyonDao getTvDao() {
        if (tvDao == null) {
            tvDao = TelevizyonDao.getInstance();
        }
        return tvDao;
    }

    public String tvPage(Televizyon tv) {
        this.tv = tv;
        return "tv";
    }

    public String kaydet() {
        try {
            if (dos != null) {
                InputStream veriGirisi = dos.getInputStream();
                File f = new File(yol + dos.getSubmittedFileName());
                tv.setGorsel("/images/" + f.getName());
                Files.copy(veriGirisi, f.toPath());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        if (tv.getUrun_id() == 0) {
            getTvDao().ekle(tv);
        } else {
            getTvDao().guncelle(tv);
        }
        tv = new Televizyon();
        return "televizyon";
    }

    public String silme(int id) {
        getTvDao().sil(id);
        return "televizyon";
    }

    public List<Televizyon> getTvList() {
        tvList = getTvDao().listele();
        return tvList;
    }

    public void setTvList(List<Televizyon> tvList) {
        this.tvList = tvList;
    }

    public Televizyon getTv() {
        if (tv == null) {
            tv = new Televizyon();
        }
        return tv;
    }

    public void setTv(Televizyon tv) {
        this.tv = tv;
    }

    public Part getDos() {
        return dos;
    }

    public void setDos(Part dos) {
        this.dos = dos;
    }

}
