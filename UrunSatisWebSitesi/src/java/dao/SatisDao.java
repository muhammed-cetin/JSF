package dao;

import entity.GozlemcileriBilgilendir;
import entity.Kullanici;
import entity.Satis;
import entity.Urunler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SatisDao extends Dao implements IDao<Satis> {

    private static SatisDao instance = null;
    private KullaniciDao kdao = null;

    private SatisDao() {
    }

    public static SatisDao getInstance() {
        if (instance == null) {
            instance = new SatisDao();
        }
        return instance;
    }

    public boolean siparisEkle(List<Urunler> urunler, Kullanici kullanici) {
        for (Urunler urun : urunler) {
            Satis tmp = new Satis();
            tmp.setMusteriAd(kullanici.getAd());
            tmp.setMusteriSoyad(kullanici.getSoyad());
            tmp.setUrunMarka(urun.getMarka());
            tmp.setUrunModel(urun.getModel());
            tmp.setTarih(LocalDate.now().toString());
            tmp.setAdet(1);
            gozlemcileriBilgilendir(tmp);
        }
        return true;
    }

    @Override
    public boolean ekle(Satis sat) {
        String q = "insert into satis(satis_id,musteriAd,musteriSoyad,urunMarka,urunModel,tarih,adet) values (default,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);

            pst.setString(1, sat.getMusteriAd());
            pst.setString(2, sat.getMusteriSoyad());
            pst.setString(3, sat.getUrunMarka());
            pst.setString(4, sat.getUrunModel());
            pst.setString(5, sat.getTarih());
            pst.setInt(6, sat.getAdet());
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean sil(int id) {
        String q = "delete from satis where satis_id=?";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            pst.setInt(1, id);
            pst.executeQuery();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean guncelle(Satis sat) {
        String q = "update satis set musteriAd=?,musteriSoyad=?,urunMarka=?,urunModel=?,tarih=?,adet=?  where satis_id=?";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            pst.setString(1, sat.getMusteriAd());
            pst.setString(2, sat.getMusteriSoyad());
            pst.setString(3, sat.getUrunMarka());
            pst.setString(4, sat.getUrunModel());
            pst.setString(5, sat.getTarih());
            pst.setInt(6, sat.getAdet());
            pst.setInt(7, sat.getSatis_id());
            pst.executeQuery();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Satis> listele() {
        String q = "select * from satis order by satis_id";
        List<Satis> slist = new ArrayList<>();
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Satis tmp = new Satis();

                tmp.setSatis_id(rs.getInt("satis_id"));
                tmp.setMusteriAd(rs.getString("musteriAd"));
                tmp.setMusteriSoyad(rs.getString("musteriSoyad"));
                tmp.setUrunMarka(rs.getString("urunMarka"));
                tmp.setUrunModel(rs.getString("urunModel"));
                tmp.setTarih(rs.getString("tarih"));
                tmp.setAdet(rs.getInt("adet"));
                slist.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return slist;
    }

    public void gozlemcileriBilgilendir(Satis sat) {
        List<Kullanici> gozlemciList = getKdao().getGozlemciler();
        for (Kullanici kull : gozlemciList) {
            GozlemcileriBilgilendir goz = new GozlemcileriBilgilendir();
            goz.bilgilendir(sat);
        }
    }

    public KullaniciDao getKdao() {
        if (kdao == null) {
            kdao = KullaniciDao.getInstance();
        }
        return kdao;
    }

}
