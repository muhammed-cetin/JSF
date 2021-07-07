package entity;

import dao.Dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GozlemcileriBilgilendir extends Dao implements Gozlemci {

    @Override
    public void bilgilendir(Satis sat) {
        String q = "insert into satis(satis_id,musteriAd,musteriSoyad,urunMarka,urunModel,tarih,adet) values (default,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = this.getConn().prepareStatement(q);

            pst.setString(1, sat.getMusteriAd());
            pst.setString(2, sat.getMusteriSoyad());
            pst.setString(3, sat.getUrunMarka());
            pst.setString(4, sat.getUrunModel());
            pst.setString(5, sat.getTarih());
            pst.setInt(6, sat.getAdet());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
