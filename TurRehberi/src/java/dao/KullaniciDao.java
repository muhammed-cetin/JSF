package dao;

import entity.Kullanici;
import entity.Rol;
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

    @Override
    public boolean ekle(Kullanici kullanici) {
        String q = "insert into kullanici(id,ad,soyad,email,kullanici_adi,telefon,parola,rol) values (default,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, kullanici.getAd());
            pst.setString(2, kullanici.getSoyad());
            pst.setString(3, kullanici.getEmail());
            pst.setString(4, kullanici.getKullaniciAdi());
            pst.setString(5, kullanici.getTelefon());
            pst.setString(6, kullanici.getParola());
            pst.setInt(7, Rol.KULLANICI.ordinal());
            return pst.executeUpdate() != 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public int kullaniciEkle(Kullanici kullanici) {
        String q = "insert into kullanici(id,ad,soyad,email,kullanici_adi,telefon,parola,rol) values (default,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, kullanici.getAd());
            pst.setString(2, kullanici.getSoyad());
            pst.setString(3, kullanici.getEmail());
            pst.setString(4, kullanici.getKullaniciAdi());
            pst.setString(5, kullanici.getTelefon());
            pst.setString(6, kullanici.getParola());
            pst.setInt(7, Rol.KULLANICI.ordinal());
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
        String q = "delete from kullanici where id=?";
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
    public boolean guncelle(Kullanici kullanici) {
        String q = "update kullanici set ad=?,soyad=?,email=?,kullanici_adi=?,telefon=?,parola=?,rol=? where id=?";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            pst.setString(1, kullanici.getAd());
            pst.setString(2, kullanici.getSoyad());
            pst.setString(3, kullanici.getEmail());
            pst.setString(4, kullanici.getKullaniciAdi());
            pst.setString(5, kullanici.getTelefon());
            pst.setString(6, kullanici.getParola());
            pst.setInt(7, kullanici.getRol().ordinal());
            pst.setInt(8, kullanici.getId());
            return pst.executeUpdate() != 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Kullanici> listele() {
        String q = "select * from kullanici order by id";
        List<Kullanici> mlist = new ArrayList<>();
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Kullanici tmp = new Kullanici();
                tmp.setId(rs.getInt("id"));
                tmp.setAd(rs.getString("ad"));
                tmp.setSoyad(rs.getString("soyad"));
                tmp.setEmail(rs.getString("email"));
                tmp.setKullaniciAdi(rs.getString("kullanici_adi"));
                tmp.setTelefon(rs.getString("telefon"));
                tmp.setParola(rs.getString("parola"));
                tmp.setRol(Rol.values()[rs.getInt("rol")]);

                mlist.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mlist;
    }

    @Override
    public Kullanici idIleGetir(int id) {
        String q = "select * from kullanici where id=?";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Kullanici tmp = new Kullanici();
                tmp.setId(rs.getInt("id"));
                tmp.setAd(rs.getString("ad"));
                tmp.setSoyad(rs.getString("soyad"));
                tmp.setEmail(rs.getString("email"));
                tmp.setKullaniciAdi(rs.getString("kullanici_adi"));
                tmp.setTelefon(rs.getString("telefon"));
                tmp.setParola(rs.getString("parola"));
                tmp.setRol(Rol.values()[rs.getInt("rol")]);
                return tmp;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int idSiniGetir(String kullaniciAdi) {
        String q = "select * from kullanici where kullanici_adi =?";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            pst.setString(1, kullaniciAdi);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public Kullanici girisYap(String email, String parola) {
        String q = "select * from kullanici where email=? and parola =?";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);

            pst.setString(1, email);
            pst.setString(2, parola);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Kullanici tmp = new Kullanici();
                tmp.setId(rs.getInt("id"));
                tmp.setAd(rs.getString("ad"));
                tmp.setSoyad(rs.getString("soyad"));
                tmp.setEmail(rs.getString("email"));
                tmp.setKullaniciAdi(rs.getString("kullanici_adi"));
                tmp.setTelefon(rs.getString("telefon"));
                tmp.setParola(rs.getString("parola"));
                tmp.setRol(Rol.values()[rs.getInt("rol")]);
                return tmp;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
