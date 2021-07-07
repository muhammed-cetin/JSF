package dao;

import entity.Kayit;
import entity.Kullanici;
import entity.Tur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GozlemciDao extends Dao {

    /*
        GozlemciDao sınıfı ile gozlemlenmesini istediğimiz işlemlerden
        sonra ki burada bu başvuru ile için yapıldı. Başvurudan sonra gelen başvuru
        gozlemci konumundaki admine bildirilir.
    
     */
    private static GozlemciDao gozlemciDao;

    private GozlemciDao() {
    }

    public static GozlemciDao getGozlemciDao() {
        if (gozlemciDao == null) {
            gozlemciDao = new GozlemciDao();
        }
        return gozlemciDao;
    }

    public boolean bilgilendir(Kullanici kullanici, Tur tur) {
        String q = "insert into tur_kayit(icerik) values (?)";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            pst.setString(1, getBilgilendirmeIcerigi(kullanici, tur));
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    String getBilgilendirmeIcerigi(Kullanici kullanici, Tur tur) {
        return kullanici.getAd() + " " + kullanici.getSoyad() + " kullanıcısı " + tur.getBaslik() + " adlı tura başvuru yaptı.";
    }

    public List<Kayit> getKayitlar() {
        String q = "select * from tur_kayit order by id";
        List<Kayit> klist = new ArrayList<>();
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Kayit tmp = new Kayit(rs.getInt("id"), rs.getString("icerik"), rs.getDate("basvuru_tarihi"));
                klist.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return klist;
    }

}
