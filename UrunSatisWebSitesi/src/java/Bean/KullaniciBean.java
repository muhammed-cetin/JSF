package Bean;

import dao.KullaniciDao;
import entity.Kullanici;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class KullaniciBean implements Serializable {

    private Kullanici kullanici;
    private String parolaTekrar;
    private String mesaj, parola, mail;
    private KullaniciDao kullaniciDao;
    private boolean girisKontrol = false, adminKontrol = false;

    public KullaniciBean() {
    }

    public String kayitOl() {
        if (!kullanici.getParola().equals(parolaTekrar)) {
            mesaj = "Lütfen parolaları aynı giriniz!";
        } else if (getKullaniciDao().kontrolEmail(kullanici.getMail())) {
            mesaj = "Bu emaili kullanan başka bir kullanıcı mevcut";
        } else {
            getKullaniciDao().ekle(kullanici);
            setKullanici(new Kullanici());
            mesaj = "Tebrikler kayıt başarılı";
        }
        return "kayitol";
    }

    public String girisYap() {
        Kullanici kull = getKullaniciDao().girisYap(mail, parola);
        if (kull == null) {
            mesaj = "Hatalı email veya parola";
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("kullanici", kull);
            setGirisKontrol(true);
            setAdminKontrol(kull.isAdmin());
            return "index";
        }
        return "girisyap";
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }

    public String getParolaTekrar() {
        return parolaTekrar;
    }

    public void setParolaTekrar(String parolaTekrar) {
        this.parolaTekrar = parolaTekrar;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public KullaniciDao getKullaniciDao() {
        if (kullaniciDao == null) {
            kullaniciDao = KullaniciDao.getInstance();
        }
        return kullaniciDao;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isAdminKontrol() {
        return adminKontrol;
    }

    public void setAdminKontrol(boolean adminKontrol) {
        this.adminKontrol = adminKontrol;
    }

    public Kullanici getKullanici() {
        if (kullanici == null) {
            kullanici = new Kullanici();
        }
        return kullanici;
    }

    public void setKullaniciDao(KullaniciDao kullaniciDao) {
        this.kullaniciDao = kullaniciDao;
    }

    public boolean getGirisKontrol() {
        return girisKontrol;
    }

    public void setGirisKontrol(boolean girisKontrol) {
        this.girisKontrol = girisKontrol;
    }

}
