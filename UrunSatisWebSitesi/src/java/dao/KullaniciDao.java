package dao;

import entity.Kullanici;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KullaniciDao extends Dao implements IDao<Kullanici> {

    private static KullaniciDao instance = null;

    private KullaniciDao() {
    }

    public static KullaniciDao getInstance() {
        if (instance == null) {
            instance = new KullaniciDao();
        }
        return instance;
    }

    public boolean kontrolEmail(String email) {
        String q = "select * from kullanici where mail=?";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Kullanici girisYap(String email, String parola) {
        String q = "select * from kullanici where mail=? and parola = ?";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);

            pst.setString(1, email);
            pst.setString(2, parola);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Kullanici tmp = new Kullanici();

                tmp.setKulllanici_id(rs.getInt("kullanici_id"));
                tmp.setAd(rs.getString("ad"));
                tmp.setSoyad(rs.getString("soyad"));
                tmp.setMail(rs.getString("mail"));
                tmp.setParola(rs.getString("parola"));
                tmp.setTelefon(rs.getString("telefon"));
                tmp.setAdres(rs.getString("adres"));
                tmp.setAdmin(rs.getBoolean("admin"));
                return tmp;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean ekle(Kullanici kullanici) {
        String q = "insert into kullanici(kullanici_id,ad,soyad,mail,parola,telefon,adres,admin) values (default,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);

            pst.setString(1, kullanici.getAd());
            pst.setString(2, kullanici.getSoyad());
            pst.setString(3, kullanici.getMail());
            pst.setString(4, kullanici.getParola());
            pst.setString(5, kullanici.getTelefon());
            pst.setString(6, kullanici.getAdres());
            pst.setBoolean(7, kullanici.isAdmin());
            pst.executeQuery();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean sil(int id) {
        String q = "delete from kullanici where kullanici_id=?";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            pst.setInt(1, id);
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Kullanici> getGozlemciler() {
        String q = "select * from kullanici where admin=true";
        List<Kullanici> klist = new ArrayList<>();
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Kullanici tmp = new Kullanici();

                tmp.setKulllanici_id(rs.getInt("kullanici_id"));
                tmp.setAd(rs.getString("ad"));
                tmp.setSoyad(rs.getString("soyad"));
                tmp.setMail(rs.getString("mail"));
                tmp.setParola(rs.getString("parola"));
                tmp.setTelefon(rs.getString("telefon"));
                tmp.setAdres(rs.getString("adres"));
                klist.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return klist;
    }

    @Override
    public boolean guncelle(Kullanici kullanici) {
        String q = "update kullanici set ad=?,soyad=?,mail=?,parola=?,telefon=?,adres=?  where kullanici_id=?";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            pst.setString(1, kullanici.getAd());
            pst.setString(2, kullanici.getSoyad());
            pst.setString(3, kullanici.getMail());
            pst.setString(4, kullanici.getParola());
            pst.setString(5, kullanici.getTelefon());
            pst.setString(6, kullanici.getAdres());
            pst.setInt(7, kullanici.getKulllanici_id());
            return pst.executeUpdate() != 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Kullanici> listele() {
        String q = "select * from kullanici order by kullanici_id";
        List<Kullanici> klist = new ArrayList<>();
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Kullanici tmp = new Kullanici();

                tmp.setKulllanici_id(rs.getInt("kullanici_id"));
                tmp.setAd(rs.getString("ad"));
                tmp.setSoyad(rs.getString("soyad"));
                tmp.setMail(rs.getString("mail"));
                tmp.setParola(rs.getString("parola"));
                tmp.setTelefon(rs.getString("telefon"));
                tmp.setAdres(rs.getString("adres"));
                klist.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return klist;
    }

    public int kullaniciEkle(Kullanici kullanici) {
        String q = "insert into kullanici(kullanici_id,ad,soyad,mail,parola,telefon,adres,admin) values (default,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, kullanici.getAd());
            pst.setString(2, kullanici.getSoyad());
            pst.setString(3, kullanici.getMail());
            pst.setString(4, kullanici.getParola());
            pst.setString(5, kullanici.getTelefon());
            pst.setString(6, kullanici.getAdres());
            pst.setBoolean(7, kullanici.isAdmin());
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

}
