package dao;

import entity.Bilgisayar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BilgisayarDao extends Dao implements IDao<Bilgisayar> {

    /*
        Veritabanı bağlantı sınıflarının bir nesne oluşturması yeterlidir,
        daha fazla oluşturup kaynakların fazla kullanımını engellemek amacıyla
        constructor (yapılandırıcı) metodu private yapılarak diğer sınıflardan 
        new BilgisayarDao() ifadesiyle yeni bir nesne oluşturulması engellenmiştir.
        
        Sınıftan bağımsız oluşturduğumuz static bir referans ile bir kere oluşturulan
        nesnenin referansı getInstance() metodu ile oluşturmak istenen yerlerde çağrıldı.
        Bu sayede her seferinde yeni bir nesne oluşturmak yerine oluşturulan ilk nesnenin
        referansları verildi.
    
        Singleton (Oluşturucu) tasarım kalıbı.
     */
    private static BilgisayarDao instance = null;

    private BilgisayarDao() {
    }

    public static BilgisayarDao getInstance() {
        if (instance == null) {
            instance = new BilgisayarDao();
        }
        return instance;
    }

    @Override
    public boolean ekle(Bilgisayar blg) {
        String q = "insert into bilgisayar(" + blg.urunString() + "islemci,ram,ekranKarti,hardDisk,pcTip) values (default,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);

            pst.setString(1, blg.getGorsel());
            pst.setInt(2, blg.getStok_sayisi());
            pst.setString(3, blg.getMarka());
            pst.setString(4, blg.getModel());
            pst.setDouble(5, blg.getFiyat());
            pst.setInt(6, blg.getGarantiSuresi());
            pst.setString(7, blg.getIslemci());
            pst.setString(8, blg.getRam());
            pst.setString(9, blg.getEkranKarti());
            pst.setString(10, blg.getHardDisk());
            pst.setString(11, blg.getPcTip());
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean sil(int id) {
        String q = "delete from bilgisayar where urun_id=?";
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
    public boolean guncelle(Bilgisayar blg) {
        String q = "update bilgisayar set " + blg.urunGuncelle() + "islemci=?,ram=?,ekranKarti=?,hardDisk=?,pcTip=? where urun_id=?";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);

            pst.setString(1, blg.getGorsel());
            pst.setInt(2, blg.getStok_sayisi());
            pst.setString(3, blg.getMarka());
            pst.setString(4, blg.getModel());
            pst.setDouble(5, blg.getFiyat());
            pst.setInt(6, blg.getGarantiSuresi());
            pst.setString(7, blg.getIslemci());
            pst.setString(8, blg.getRam());
            pst.setString(9, blg.getEkranKarti());
            pst.setString(10, blg.getHardDisk());
            pst.setString(11, blg.getPcTip());
            pst.setInt(12, blg.getUrun_id());

            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Bilgisayar> listele() {
        String q = "select * from bilgisayar order by urun_id";
        List<Bilgisayar> tlist = new ArrayList<>();
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Bilgisayar tmp = new Bilgisayar();

                tmp.setUrun_id(rs.getInt("urun_id"));

                tmp.setGorsel(rs.getString("gorsel"));
                tmp.setStok_sayisi(rs.getInt("stokSayisi"));
                tmp.setMarka(rs.getString("marka"));
                tmp.setModel(rs.getString("model"));
                tmp.setFiyat(rs.getDouble("fiyat"));
                tmp.setGarantiSuresi(rs.getInt("garantiSuresi"));

                tmp.setIslemci(rs.getString("islemci"));
                tmp.setRam(rs.getString("ram"));
                tmp.setEkranKarti(rs.getString("ekranKarti"));
                tmp.setHardDisk(rs.getString("hardDisk"));
                tmp.setPcTip(rs.getString("pcTip"));
                tlist.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tlist;
    }

}
