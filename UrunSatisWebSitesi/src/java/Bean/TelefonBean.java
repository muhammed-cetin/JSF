package Bean;

import dao.TelefonDao;
import entity.Telefon;
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
public class TelefonBean implements Serializable {

    private final String yol = "C:/Users/c1to/Documents/NetBeansProjects/UrunSatisWebSitesi/web/images/";
    private Part dos;

    public TelefonBean() {
    }

    private TelefonDao telDao;
    private List<Telefon> telList;
    private Telefon tel;

    public TelefonDao getTelDao() {
        if (telDao == null) {
            telDao = TelefonDao.getInstance();
        }
        return telDao;
    }

    public String telPage(Telefon tel) {
        this.tel = tel;
        return "tel";
    }

    public String kaydet() {
        try {
            if (dos != null) {
                InputStream veriGirisi = dos.getInputStream();
                File f = new File(yol + dos.getSubmittedFileName());
                tel.setGorsel("/images/" + f.getName());
                Files.copy(veriGirisi, f.toPath());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        if (tel.getUrun_id() == 0) {
            getTelDao().ekle(tel);
        } else {
            getTelDao().guncelle(tel);
        }
        tel = new Telefon();
        return "telefon";
    }

    public String silme(int id) {
        getTelDao().sil(id);
        return "telefon";
    }

    public List<Telefon> getTelList() {
        telList = getTelDao().listele();
        return telList;
    }

    public void setTelList(List<Telefon> telList) {
        this.telList = telList;
    }

    public Telefon getTel() {
        if (tel == null) {
            tel = new Telefon();
        }
        return tel;
    }

    public void setTel(Telefon tel) {
        this.tel = tel;
    }

    public Part getDos() {
        return dos;
    }

    public void setDos(Part dos) {
        this.dos = dos;
    }

}
