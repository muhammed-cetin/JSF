package dao;

import entity.Tur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TurDao extends Dao implements IDao<Tur> {

    private GozlemciDao gozlemciDao;
    private KullaniciDao kullaniciDao;

    private static TurDao instance = null;

    private TurDao() {
    }

    public static TurDao getInstance() {
        if (instance == null) {
            instance = new TurDao();
        }
        return instance;
    }

    public int turEkle(Tur tur) {
        String q = "insert into tur(tur_id,gorsel,baslik,aciklama,ucret,kontejan,rota,hizmetler) values (default,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, tur.getGorsel());
            pst.setString(2, tur.getBaslik());
            pst.setString(3, tur.getAciklama());
            pst.setInt(4, tur.getUcret());
            pst.setInt(5, tur.getKontejan());
            pst.setString(6, tur.getRota());
            pst.setString(7, tur.getHizmetler());
            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                return 0;
            }
            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return (int) generatedKeys.getLong(1);
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public boolean sil(int id) {
        String q = "delete from tur where tur_id=?";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            pst.setInt(1, id);
            return pst.executeUpdate() != 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean guncelle(Tur tur) {
        String q = "update tur set gorsel=?,baslik=?,aciklama=?,ucret=?,kontejan=?,rota=?,hizmetler=? where tur_id=?";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            pst.setString(1, tur.getGorsel());
            pst.setString(2, tur.getBaslik());
            pst.setString(3, tur.getAciklama());
            pst.setInt(4, tur.getUcret());
            pst.setInt(5, tur.getKontejan());
            pst.setString(6, tur.getRota());
            pst.setString(7, tur.getHizmetler());
            pst.setInt(8, tur.getTur_id());
            return pst.executeUpdate() != 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Tur> listele() {
        String q = "select * from tur order by tur_id";
        List<Tur> tlist = new ArrayList<>();
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Tur tmp = new Tur();
                tmp.setTur_id(rs.getInt("tur_id"));
                tmp.setGorsel(rs.getString("gorsel"));
                tmp.setBaslik(rs.getString("baslik"));
                tmp.setAciklama(rs.getString("aciklama"));
                tmp.setUcret(rs.getInt("ucret"));
                tmp.setKontejan(rs.getInt("kontejan"));
                tmp.setRota(rs.getString("rota"));
                tmp.setHizmetler(rs.getString("hizmetler"));
                tlist.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tlist;
    }

    public List<Tur> listele(int kullaniciId) {
        String q = "select * from tur where tur_id in (select tur_id from kullanici_tur where kullanici_id = ?)";
        List<Tur> tlist = new ArrayList<>();
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            pst.setInt(1, kullaniciId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Tur tmp = new Tur();
                tmp.setTur_id(rs.getInt("tur_id"));
                tmp.setGorsel(rs.getString("gorsel"));
                tmp.setBaslik(rs.getString("baslik"));
                tmp.setAciklama(rs.getString("aciklama"));
                tmp.setUcret(rs.getInt("ucret"));
                tmp.setKontejan(rs.getInt("kontejan"));
                tmp.setRota(rs.getString("rota"));
                tmp.setHizmetler(rs.getString("hizmetler"));
                tlist.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tlist;
    }

    @Override
    public Tur idIleGetir(int id) {
        String q = "select * from tur where tur_id=?";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Tur tmp = new Tur();
                tmp.setTur_id(rs.getInt("tur_id"));
                tmp.setGorsel(rs.getString("gorsel"));
                tmp.setBaslik(rs.getString("baslik"));
                tmp.setAciklama(rs.getString("aciklama"));
                tmp.setUcret(rs.getInt("ucret"));
                tmp.setKontejan(rs.getInt("kontejan"));
                tmp.setRota(rs.getString("rota"));
                tmp.setHizmetler(rs.getString("hizmetler"));
                return tmp;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Observer
    public boolean kullaniciTurEkle(int kullaniciId, int turId) {
        String iliski = "insert into kullanici_tur(id,kullanici_id,tur_id) values(default,?,?)";
        try {
            PreparedStatement pst2 = this.getConn().prepareStatement(iliski);
            pst2.setInt(1, kullaniciId);
            pst2.setInt(2, turId);
            if (pst2.executeUpdate() != 0) {
                return getGozlemciDao().bilgilendir(getKullaniciDao().idIleGetir(kullaniciId), idIleGetir(turId));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean ekle(Tur tur) {
        String q = "insert into tur(tur_id,gorsel,baslik,aciklama,ucret,kontejan,rota,hizmetler) values (default,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);

            pst.setString(1, tur.getGorsel());
            pst.setString(2, tur.getBaslik());
            pst.setString(3, tur.getAciklama());
            pst.setInt(4, tur.getUcret());
            pst.setInt(5, tur.getKontejan());
            pst.setString(6, tur.getRota());
            pst.setString(7, tur.getHizmetler());

            return pst.executeUpdate() != 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean kullaniciTurSil(int id, int turId) {
        String q = "delete from kullanici_tur where kullanici_id=? and tur_id=?";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            pst.setInt(1, id);
            pst.setInt(2, turId);
            return pst.executeUpdate() != 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public GozlemciDao getGozlemciDao() {
        if (gozlemciDao == null) {
            gozlemciDao = GozlemciDao.getGozlemciDao();
        }
        return gozlemciDao;
    }

    public KullaniciDao getKullaniciDao() {
        if (kullaniciDao == null) {
            kullaniciDao = KullaniciDao.getInstance();
        }
        return kullaniciDao;
    }

}
