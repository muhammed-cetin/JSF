package Bean;

import dao.BilgisayarDao;
import entity.Bilgisayar;
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
public class BilgisayarBean implements Serializable {

    private BilgisayarDao pcDao;
    private List<Bilgisayar> pcList;
    private Bilgisayar pc;
    private final String yol = "C:/Users/c1to/Documents/NetBeansProjects/UrunSatisWebSitesi/web/images/";
    private Part dos;

    public BilgisayarBean() {
    }

    public BilgisayarDao getPcDao() {
        if (pcDao == null) {
            pcDao = BilgisayarDao.getInstance();
        }
        return pcDao;
    }

    public String pcPage(Bilgisayar pc) {
        this.pc = pc;
        return "pc";
    }

    public String kaydet() {
        try {
            if(dos != null){
            InputStream veriGirisi = dos.getInputStream();
            File f = new File(yol + dos.getSubmittedFileName());
            pc.setGorsel("/images/" + f.getName());
            Files.copy(veriGirisi, f.toPath());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        if (pc.getUrun_id() == 0) {
            getPcDao().ekle(pc);
        } else {
            getPcDao().guncelle(pc);
        }
        pc = new Bilgisayar();
        return "bilgisayar";
    }

    public String silme(int id) {
        getPcDao().sil(id);
        return "bilgisayar";
    }

    public List<Bilgisayar> getPcList() {
        pcList = getPcDao().listele();
        return pcList;
    }

    public void setPcList(List<Bilgisayar> pcList) {
        this.pcList = pcList;
    }

    public Bilgisayar getPc() {
        if (pc == null) {
            pc = new Bilgisayar();
        }
        return pc;
    }

    public void setPc(Bilgisayar pc) {
        this.pc = pc;
    }

    public Part getDos() {
        return dos;
    }

    public void setDos(Part dos) {
        this.dos = dos;
    }

}
