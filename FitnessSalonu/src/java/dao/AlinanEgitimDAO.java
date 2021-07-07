package dao;

import entity.AlinanEgitim;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlinanEgitimDAO extends SuperDAO implements IDao<AlinanEgitim> {

    private static AlinanEgitimDAO alinanEgitimDao = null;

    private AlinanEgitimDAO() {
    }

    public static AlinanEgitimDAO getAlinanEgitimDao() {
        if (alinanEgitimDao == null) {
            alinanEgitimDao = new AlinanEgitimDAO();
        }
        return alinanEgitimDao;
    }

    PreparedStatement pst = null;
    ResultSet rs = null;

    EgitimDAO egitimdao;
    UyeDAO uyedao;

    @Override
    public boolean insert(AlinanEgitim alinanEgitim) {
        try {
            pst = this.getConnection().prepareStatement("insert into alinan_egitim (egitim_id,uye_id) values(?,?) ");

            pst.setInt(1, alinanEgitim.getEgitim().getEgitim_id());
            pst.setInt(2, alinanEgitim.getUye().getUye_id());
            return pst.executeUpdate() != 0;

        } catch (SQLException ex) {
            System.out.println("AlinanEgitimDAO HATA(Create) : " + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(AlinanEgitim alinanEgitim) {

        try {
            pst = this.getConnection().prepareStatement("delete from alinan_egitim where alinan_id=?");
            pst.setInt(1, alinanEgitim.getAlinan_id());
            return pst.executeUpdate() != 0;
        } catch (SQLException ex) {
            System.out.println(" AlinanEgitimDAO HATA(Delete): " + ex.getMessage());
        }
        return false;
    }

    public List<AlinanEgitim> findAll() {
        List<AlinanEgitim> alinanEgitimlist = new ArrayList();

        try {
            pst = this.getConnection().prepareStatement("select * from alinan_egitim");

            rs = pst.executeQuery();
            while (rs.next()) {
                AlinanEgitim temp = new AlinanEgitim();
                temp.setAlinan_id(rs.getInt("alinan_id"));
                temp.setEgitim(this.getEgitimdao().find(rs.getInt("egitim_id")));
                temp.setUye(this.getUyedao().find(rs.getInt("uye_id")));

                alinanEgitimlist.add(temp);
            }

            return alinanEgitimlist;
        } catch (SQLException ex) {
            System.out.println("AlinanEgitimDAO HATA(FindAll):" + ex.getMessage());
            return null;
        }
    }

    @Override
    public AlinanEgitim find(int id) {
        AlinanEgitim alinanEgitim = null;
        try {
            pst = this.getConnection().prepareStatement("select * from alinan_egitim where alinan_id = ?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            rs.next();

            alinanEgitim = new AlinanEgitim();
            alinanEgitim.setAlinan_id(rs.getInt("alinan_id"));
            alinanEgitim.setEgitim(this.getEgitimdao().find(rs.getInt("egitim_id")));
            alinanEgitim.setUye(this.getUyedao().find(rs.getInt("uye_id")));
        } catch (SQLException ex) {

            System.out.println("AlinanEgitimDAO HATA(Find):" + ex.getMessage());
        }

        return alinanEgitim;
    }

    @Override
    public boolean update(AlinanEgitim alinanEgitim) {
        try {
            pst = this.getConnection().prepareStatement("update alinan_egitim set egitim_id=? , uye_id=? where alinan_id=?");

            pst.setInt(1, alinanEgitim.getEgitim().getEgitim_id());
            pst.setInt(2, alinanEgitim.getUye().getUye_id());

            pst.setInt(3, alinanEgitim.getAlinan_id());
            return pst.executeUpdate() != 0;
        } catch (SQLException ex) {
            System.out.println("AlinanEgitimDAO HATA(Update):" + ex.getMessage());
        }
        return false;
    }

    @Override
    public int count() {
        int count = 0;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select count(alinan_id) as alinan_count from alinan_egitim");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("alinan_count");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public EgitimDAO getEgitimdao() {
        if (this.egitimdao == null) {
            this.egitimdao = EgitimDAO.getEgitimDao();
        }
        return egitimdao;
    }

    public void setEgitimdao(EgitimDAO egitimdao) {
        this.egitimdao = egitimdao;
    }

    public UyeDAO getUyedao() {
        if (this.uyedao == null) {
            this.uyedao = UyeDAO.getUyeDao();

        }
        return uyedao;
    }

    public void setUyedao(UyeDAO uyedao) {
        this.uyedao = uyedao;
    }

    @Override
    public List<AlinanEgitim> findAll(String deger, int page, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
