package dao;

import entity.Uye;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.DBConnection;

public class EgitmenObserver implements Observer {

    private DBConnection db;
    private Connection connection;

    public DBConnection getDb() {
        if (this.db == null) {
            this.db = new DBConnection();
        }
        return db;
    }

    public Connection getConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            this.connection = this.getDb().connect();
        }
        return connection;
    }

    @Override
    public void uyeKayitHaberVer(Uye uye) {
        try (PreparedStatement pst = this.getConnection().prepareStatement("insert into uyeler (uye_ad,uye_soyad,uye_cinsiyet,uye_tel,uye_yas,uye_mail,admin,sifre) values (?,?,?,?,?,?,0,?)")) {

            pst.setString(1, uye.getUye_ad());
            pst.setString(2, uye.getUye_soyad());
            pst.setString(3, uye.getUye_cinsiyet());
            pst.setString(4, uye.getUye_tel());
            pst.setInt(5, uye.getUye_yas());
            pst.setString(6, uye.getUye_mail());
            pst.setString(7, uye.getSifre());
            pst.executeUpdate();
            pst.close();

        } catch (SQLException ex) {
            System.out.println("UyeDAO HATA(Kayitol) : " + ex.getMessage());
        }
    }

}
