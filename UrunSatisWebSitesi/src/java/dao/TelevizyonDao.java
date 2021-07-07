package dao;

import entity.Televizyon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelevizyonDao extends Dao implements IDao<Televizyon> {

    private static TelevizyonDao instance = null;

    private TelevizyonDao() {
    }

    public static TelevizyonDao getInstance() {
        if (instance == null) {
            instance = new TelevizyonDao();
        }
        return instance;
    }

    @Override
    public boolean ekle(Televizyon tev) {
        String q = "insert into televizyon(" + tev.urunString() + "ekranBoyutu,cozunurlukTuru,baglantiTuru) values (default,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);

            pst.setString(1, tev.getGorsel());
            pst.setInt(2, tev.getStok_sayisi());
            pst.setString(3, tev.getMarka());
            pst.setString(4, tev.getModel());
            pst.setDouble(5, tev.getFiyat());
            pst.setInt(6, tev.getGarantiSuresi());
            pst.setString(7, tev.getEkranBoyutu());
            pst.setString(8, tev.getCozunurlukTuru());
            pst.setString(9, tev.getBaglantiTuru());
            pst.executeQuery();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean sil(int id) {
        String q = "delete from televizyon where urun_id=?";
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
    public boolean guncelle(Televizyon tev) {
        String q = "update televizyon set " + tev.urunGuncelle() + "ekranBoyutu=?,cozunurlukTuru=?,baglantiTuru=? where urun_id=?";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            pst.setString(1, tev.getGorsel());
            pst.setInt(2, tev.getStok_sayisi());
            pst.setString(3, tev.getMarka());
            pst.setString(4, tev.getModel());
            pst.setDouble(5, tev.getFiyat());
            pst.setInt(6, tev.getGarantiSuresi());
            pst.setString(7, tev.getEkranBoyutu());
            pst.setString(8, tev.getCozunurlukTuru());
            pst.setString(9, tev.getBaglantiTuru());
            pst.setInt(10, tev.getUrun_id());

            pst.executeQuery();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Televizyon> listele() {
        String q = "select * from televizyon order by urun_id";
        List<Televizyon> tvlist = new ArrayList<>();
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Televizyon tmp = new Televizyon();

                tmp.setUrun_id(rs.getInt("urun_id"));

                tmp.setGorsel(rs.getString("gorsel"));
                tmp.setStok_sayisi(rs.getInt("stokSayisi"));
                tmp.setMarka(rs.getString("marka"));
                tmp.setModel(rs.getString("model"));
                tmp.setFiyat(rs.getDouble("fiyat"));
                tmp.setGarantiSuresi(rs.getInt("garantiSuresi"));

                tmp.setEkranBoyutu(rs.getString("ekranBoyutu"));
                tmp.setCozunurlukTuru(rs.getString("cozunurlukTuru"));
                tmp.setBaglantiTuru(rs.getString("baglantiTuru"));
                tvlist.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tvlist;
    }

}
