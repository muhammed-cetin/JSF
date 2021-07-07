package dao;

import entity.Telefon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelefonDao extends Dao implements IDao<Telefon> {

    private static TelefonDao instance = null;

    private TelefonDao() {
    }

    public static TelefonDao getInstance() {
        if (instance == null) {
            instance = new TelefonDao();
        }
        return instance;
    }

    @Override
    public boolean ekle(Telefon tel) {
        String q = "insert into telefon(" + tel.urunString() + "kamera,ekranBoyutu,pilOmru,kapasite,isletimSistemi) values (default,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);

            pst.setString(1, tel.getGorsel());
            pst.setInt(2, tel.getStok_sayisi());
            pst.setString(3, tel.getMarka());
            pst.setString(4, tel.getModel());
            pst.setDouble(5, tel.getFiyat());
            pst.setInt(6, tel.getGarantiSuresi());
            pst.setString(7, tel.getKamera());
            pst.setString(8, tel.getEkranBoyutu());
            pst.setString(9, tel.getPilOmru());
            pst.setString(10, tel.getKapasite());
            pst.setString(11, tel.getIsletimSistemi());
            pst.executeQuery();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean sil(int id) {
        String q = "delete from telefon where urun_id=?";
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
    public boolean guncelle(Telefon tel) {
        String q = "update telefon set " + tel.urunGuncelle() + "kamera=?,ekranBoyutu=?,pilOmru=?,kapasite=?,isletimSistemi=?   where urun_id=?";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            pst.setString(1, tel.getGorsel());
            pst.setInt(2, tel.getStok_sayisi());
            pst.setString(3, tel.getMarka());
            pst.setString(4, tel.getModel());
            pst.setDouble(5, tel.getFiyat());
            pst.setInt(6, tel.getGarantiSuresi());
            pst.setString(7, tel.getKamera());
            pst.setString(8, tel.getEkranBoyutu());
            pst.setString(9, tel.getPilOmru());
            pst.setString(10, tel.getKapasite());
            pst.setString(11, tel.getIsletimSistemi());
            pst.setInt(12, tel.getUrun_id());

            pst.executeQuery();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Telefon> listele() {
        String q = "select * from telefon order by urun_id";
        List<Telefon> tlist = new ArrayList<>();
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Telefon tmp = new Telefon();

                tmp.setUrun_id(rs.getInt("urun_id"));

                tmp.setGorsel(rs.getString("gorsel"));

                tmp.setStok_sayisi(rs.getInt("stokSayisi"));
                tmp.setMarka(rs.getString("marka"));
                tmp.setModel(rs.getString("model"));
                tmp.setFiyat(rs.getDouble("fiyat"));
                tmp.setGarantiSuresi(rs.getInt("garantiSuresi"));

                tmp.setKamera(rs.getString("kamera"));
                tmp.setEkranBoyutu(rs.getString("ekranBoyutu"));
                tmp.setPilOmru(rs.getString("pilOmru"));
                tmp.setKapasite(rs.getString("kapasite"));
                tmp.setIsletimSistemi(rs.getString("isletimSistemi"));
                tlist.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tlist;
    }

}
