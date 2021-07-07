package dao;

import entity.Urunler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UrunlerDao extends Dao implements IDao<Urunler> {

    private static UrunlerDao instance = null;

    private UrunlerDao() {
    }

    public static UrunlerDao getInstance() {
        if (instance == null) {
            instance = new UrunlerDao();
        }
        return instance;
    }

    @Override
    public boolean ekle(Urunler urun) {
        String q = "insert into urunler(urun_id,gorsel,stokSayisi,marka,model,fiyat,garantiSuresi) values (default,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);

            pst.setString(1, urun.getGorsel());
            pst.setInt(2, urun.getStok_sayisi());
            pst.setString(3, urun.getMarka());
            pst.setString(4, urun.getModel());
            pst.setDouble(5, urun.getFiyat());
            pst.setInt(6, urun.getGarantiSuresi());
            pst.executeQuery();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean sil(int id) {
        String q = "delete from urunler where urun_id=?";
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
    public boolean guncelle(Urunler urun) {
        String q = "update urunler set gorsel=?,stokSayisi=?,marka=?,model=?,fiyat=?,garantiSuresi=?  where urun_id=?";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            pst.setString(1, urun.getGorsel());
            pst.setInt(2, urun.getStok_sayisi());
            pst.setString(3, urun.getMarka());
            pst.setString(4, urun.getModel());
            pst.setDouble(5, urun.getFiyat());
            pst.setInt(6, urun.getGarantiSuresi());
            pst.setInt(7, urun.getUrun_id());
            pst.executeQuery();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Urunler> listele() {
        String q = "select * from urunler order by urun_id";
        List<Urunler> ulist = new ArrayList<>();
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Urunler tmp = new Urunler();

                tmp.setUrun_id(rs.getInt("urun_id"));
                tmp.setGorsel(rs.getString("gorsel"));
                tmp.setStok_sayisi(rs.getInt("stokSayisi"));
                tmp.setMarka(rs.getString("marka"));
                tmp.setModel(rs.getString("model"));
                tmp.setFiyat(rs.getDouble("fiyat"));
                tmp.setGarantiSuresi(rs.getInt("garantiSuresi"));
                ulist.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ulist;
    }

}
