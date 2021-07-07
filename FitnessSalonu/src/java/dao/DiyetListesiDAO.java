package dao;

import entity.DiyetListesi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiyetListesiDAO extends SuperDAO implements IDao<DiyetListesi> {

    private static DiyetListesiDAO diyetListesiDao = null;

    private DiyetListesiDAO() {
    }

    public static DiyetListesiDAO getDiyetListesiDaoo() {
        if (diyetListesiDao == null) {
            diyetListesiDao = new DiyetListesiDAO();
        }
        return diyetListesiDao;
    }

    PreparedStatement pst = null;
    ResultSet rs = null;
    EgitimDAO egitimdao;
    UyeDAO uyedao;

    @Override
    public boolean insert(DiyetListesi diyetListesi) {
        try {
            pst = this.getConnection().prepareStatement("insert into diyet_listesi (gramaj,saat_araligi,yemek_ismi,egitim_id) values(?,?,?,?)");
            pst.setString(1, diyetListesi.getGramaj());
            pst.setString(2, diyetListesi.getSaat_araligi());
            pst.setString(3, diyetListesi.getYemek_isim());
            pst.setInt(4, diyetListesi.getEgitim().getEgitim_id());
            return pst.executeUpdate() != 0;
        } catch (SQLException ex) {
            System.out.println(" DiyetListesiDAO HATA(Create): " + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(DiyetListesi diyetListesi) {
        try {
            pst = this.getConnection().prepareStatement("delete from diyet_listesi where diy_listesi_id=?");
            pst.setInt(1, diyetListesi.getDiyet_id());
            return pst.executeUpdate() != 0;
        } catch (SQLException ex) {
            System.out.println(" DiyetListesiDAO HATA(Delete): " + ex.getMessage());
        }
        return false;
    }

    @Override
    public List<DiyetListesi> findAll(String deger, int page, int pageSize) {
        List<DiyetListesi> diyetListesi = new ArrayList();
        int start = (page - 1) * pageSize;
        try {
            pst = this.getConnection().prepareStatement("select * from diyet_listesi where yemek_ismi like ? order by diy_listesi_id asc limit ? offset ?");
            pst.setString(1, "%" + deger + "%"); // ara çubuğuna girilen herhangi bir değeri içeren bütün bilgileri getirmek için "%" + deger + "%" bu şekilde kullandık.
            pst.setInt(2, pageSize);
            pst.setInt(3, start);
            rs = pst.executeQuery();
            while (rs.next()) {
                DiyetListesi temp = new DiyetListesi();
                temp.setDiyet_id(rs.getInt("diy_listesi_id"));
                temp.setYemek_isim(rs.getString("yemek_ismi"));
                temp.setGramaj(rs.getString("gramaj"));
                temp.setSaat_araligi(rs.getString("saat_araligi"));
                temp.setEgitim(this.getEgitimdao().find(rs.getInt("egitim_id")));
                diyetListesi.add(temp);
            }
            return diyetListesi;
        } catch (SQLException ex) {
            System.out.println(" DiyetListesiDAO HATA(FindAll): " + ex.getMessage());
            return null;
        }
    }

    @Override
    public DiyetListesi find(int id) {
        DiyetListesi diyetListesi = null;
        try {
            pst = this.getConnection().prepareStatement("select * from diyet_listesi where diy_listesi_id=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            rs.next();
            diyetListesi = new DiyetListesi();
            diyetListesi.setDiyet_id(rs.getInt("diy_listesi_id"));
            diyetListesi.setYemek_isim(rs.getString("yemek_ismi"));
            diyetListesi.setGramaj(rs.getString("gramaj"));
            diyetListesi.setSaat_araligi(rs.getString("saat_araliği"));
            diyetListesi.setEgitim(this.getEgitimdao().find(rs.getInt("egitim_id")));
        } catch (SQLException ex) {
            System.out.println(" DiyetListesiDAO HATA(Find): " + ex.getMessage());
        }
        return diyetListesi;
    }

    @Override
    public boolean update(DiyetListesi diyetListesi) {
        try {
            pst = this.getConnection().prepareStatement("update diyet_listesi set gramaj=?,saat_araligi=?,yemek_ismi=?,egitim_id=? where diy_listesi_id=?");
            pst.setString(1, diyetListesi.getGramaj());
            pst.setString(2, diyetListesi.getSaat_araligi());
            pst.setString(3, diyetListesi.getYemek_isim());
            pst.setInt(4, diyetListesi.getEgitim().getEgitim_id());
            pst.setInt(5, diyetListesi.getDiyet_id());
            return pst.executeUpdate() != 0;

        } catch (SQLException ex) {
            System.out.println("DiyetListesiDAO HATA(Update): " + ex.getMessage());
        }
        return false;
    }

    public EgitimDAO getEgitimdao() {
        if (this.egitimdao == null) {
            this.egitimdao = dao.EgitimDAO.getEgitimDao();
        }
        return egitimdao;
    }

    public void setEgitimdao(EgitimDAO egitimdao) {
        this.egitimdao = egitimdao;
    }

    @Override
    public int count() {
        int count = 0;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select count(diy_listesi_id) as diyet_count from diyet_listesi");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("diyet_count");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
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

}
