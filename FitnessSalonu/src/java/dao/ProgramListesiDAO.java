package dao;

import entity.ProgramListesi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramListesiDAO extends SuperDAO implements IDao<ProgramListesi> {

    private static ProgramListesiDAO programListesiDao = null;

    private ProgramListesiDAO() {
    }

    public static ProgramListesiDAO getProgramListesiDao() {
        if (programListesiDao == null) {
            programListesiDao = new ProgramListesiDAO();
        }
        return programListesiDao;
    }

    PreparedStatement pst = null;
    ResultSet rs = null;
    EgitimDAO egitimdao;
    UyeDAO uyedao;

    @Override
    public boolean insert(ProgramListesi programListesi) {
        try {
            pst = this.getConnection().prepareStatement("insert into program_listesi (hareket_adi,tekrar_sayisi,hareket_bolgesi,egitim_id) values(?,?,?,?)");
            pst.setString(1, programListesi.getHareket_adi());
            pst.setString(2, programListesi.getTekrar_sayisi());
            pst.setString(3, programListesi.getHareket_bolgesi());
            pst.setInt(4, programListesi.getEgitim().getEgitim_id());
            return pst.executeUpdate() != 0;

        } catch (SQLException ex) {
            System.out.println(" ProgramListesiDAO HATA(Create): " + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(ProgramListesi programListesi) {
        try {
            pst = this.getConnection().prepareStatement("delete from program_listesi where por_liste_id=?");
            pst.setInt(1, programListesi.getProgram_id());
            return pst.executeUpdate() != 0;
        } catch (SQLException ex) {
            System.out.println(" ProgramListesiDAO HATA(Delete): " + ex.getMessage());
        }
        return false;
    }

    @Override
    public List<ProgramListesi> findAll(String deger, int page, int pageSize) {
        List<ProgramListesi> programListesi = new ArrayList();
        int start = (page - 1) * pageSize;

        try {
            pst = this.getConnection().prepareStatement("select * from program_listesi where  hareket_adi like ? order by por_liste_id asc limit ? offset ?");
            pst.setString(1, "%" + deger + "%"); // ara çubuğuna girilen herhangi bir değeri içeren bütün bilgileri getirmek için "%" + deger + "%" bu şekilde kullandık.
            pst.setInt(2, pageSize);
            pst.setInt(3, start);
            rs = pst.executeQuery();
            while (rs.next()) {
                ProgramListesi temp = new ProgramListesi();
                temp.setProgram_id(rs.getInt("por_liste_id"));
                temp.setHareket_adi(rs.getString("hareket_adi"));
                temp.setTekrar_sayisi(rs.getString("tekrar_sayisi"));
                temp.setHareket_bolgesi(rs.getString("hareket_bolgesi"));
                temp.setEgitim(this.getEgitimdao().find(rs.getInt("egitim_id")));
                programListesi.add(temp);
            }

            return programListesi;
        } catch (SQLException ex) {
            System.out.println(" ProgramListesiDAO HATA(FindAll): " + ex.getMessage());
            return null;
        }
    }

    @Override
    public ProgramListesi find(int id) {
        ProgramListesi programListesi = null;
        try {
            pst = this.getConnection().prepareStatement("select * from program_listesi where por_liste_id=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            rs.next();
            programListesi = new ProgramListesi();
            programListesi.setProgram_id(rs.getInt("por_liste_id"));
            programListesi.setHareket_adi(rs.getString("hareket_adi"));
            programListesi.setTekrar_sayisi(rs.getString("tekrar_sayisi"));
            programListesi.setHareket_bolgesi(rs.getString("hareket_bolgesi"));
            programListesi.setEgitim(this.getEgitimdao().find(rs.getInt("egitim_id")));
        } catch (SQLException ex) {
            System.out.println(" ProgramListesiDAO HATA(Find): " + ex.getMessage());
        }
        return programListesi;
    }

    @Override
    public boolean update(ProgramListesi programListesi) {
        try {
            pst = this.getConnection().prepareStatement("update program_listesi set hareket_adi=?,tekrar_sayisi=?,hareket_bolgesi=?,egitim_id=? where por_liste_id=?");
            pst.setString(1, programListesi.getHareket_adi());
            pst.setString(2, programListesi.getTekrar_sayisi());
            pst.setString(3, programListesi.getHareket_bolgesi());
            pst.setInt(4, programListesi.getEgitim().getEgitim_id());
            pst.setInt(5, programListesi.getProgram_id());
            return pst.executeUpdate() != 0;
        } catch (SQLException ex) {
            System.out.println("ProgramListesiDAO HATA(Update): " + ex.getMessage());
        }
        return false;
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

    @Override
    public int count() {
        int count = 0;
        try {
            pst = this.getConnection().prepareStatement("select count(por_liste_id) as por_count from program_listesi");
            rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("por_count");
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
