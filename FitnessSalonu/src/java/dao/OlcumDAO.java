package dao;

import entity.Olcum;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OlcumDAO extends SuperDAO implements IDao<Olcum> {

    private static OlcumDAO olcumDao = null;

    private OlcumDAO() {
    }

    public static OlcumDAO getOlcumDao() {
        if (olcumDao == null) {
            olcumDao = new OlcumDAO();
        }
        return olcumDao;
    }

    PreparedStatement pst = null;
    ResultSet rs = null;

    @Override
    public boolean insert(Olcum olcum) {
        try {
            pst = this.getConnection().prepareStatement("insert into olcum (kilo,boy,vucut_kitle_indeksi) values(?,?,?) ");

            pst.setDouble(1, olcum.getKilo());
            pst.setDouble(2, olcum.getBoy());
            pst.setDouble(3, olcum.getVucut_kitle_indeksi());
            return pst.executeUpdate() != 0;
        } catch (SQLException ex) {
            System.out.println("OlcumDAO HATA(Create) : " + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Olcum olcum) {
        try {
            pst = this.getConnection().prepareStatement("delete from olcum where olcum_id=?");
            pst.setInt(1, olcum.getOlcum_id());
            return pst.executeUpdate() != 0;
        } catch (SQLException ex) {
            System.out.println(" OlcumDAO HATA(Delete): " + ex.getMessage());
        }
        return false;
    }

    public List<Olcum> findAll(int page, int pageSize) {
        List<Olcum> olcumList = new ArrayList();

        int start = (page - 1) * pageSize;
        try {
            pst = this.getConnection().prepareStatement("select * from olcum order by olcum_id asc limit ? offset ?");
            pst.setInt(1, pageSize);
            pst.setInt(2, start);
            rs = pst.executeQuery();
            while (rs.next()) {
                Olcum temp = new Olcum();

                temp.setOlcum_id(rs.getInt("olcum_id"));
                temp.setKilo(rs.getInt("kilo"));
                temp.setBoy(rs.getInt("boy"));
                temp.setVucut_kitle_indeksi(rs.getFloat("vucut_kitle_indeksi"));

                olcumList.add(temp);
            }

            return olcumList;
        } catch (SQLException ex) {
            System.out.println("OlcumDAO HATA(FindAll):" + ex.getMessage());
            return null;
        }
    }

    @Override
    public Olcum find(int id) {
        Olcum olcum = null;
        try {
            pst = this.getConnection().prepareStatement("select * from olcum where olcum_id = ?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                olcum = new Olcum();
                olcum.setOlcum_id(rs.getInt("olcum_id"));
                olcum.setKilo(rs.getInt("kilo"));
                olcum.setBoy(rs.getInt("boy"));
                olcum.setVucut_kitle_indeksi(rs.getFloat("vucut_kitle_indeksi"));
            }
        } catch (SQLException ex) {
            System.out.println("OlcumDAO HATA(Find):" + ex.getMessage());
        }
        return olcum;
    }

    @Override
    public boolean update(Olcum olcum) {
        try {
            pst = this.getConnection().prepareStatement("update olcum set kilo=? , boy=? , vucut_kitle_indeksi=?  where olcum_id=?");

            pst.setDouble(1, olcum.getKilo());
            pst.setDouble(2, olcum.getBoy());
            pst.setDouble(3, olcum.getVucut_kitle_indeksi());
            pst.setInt(4, olcum.getOlcum_id());
            return pst.executeUpdate() != 0;
        } catch (SQLException ex) {
            System.out.println("OlcumDAO HATA(Update):" + ex.getMessage());
        }
        return false;
    }

    @Override
    public int count() { // istenilen tablonun veri sayısını döndüren metod
        int count = 0;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select count(olcum_id) as olcum_count from olcum");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("olcum_count");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    @Override
    public List<Olcum> findAll(String deger, int page, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
