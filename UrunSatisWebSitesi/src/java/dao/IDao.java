package dao;

import java.util.List;

public interface IDao<T> {

    public boolean ekle(T obj);

    public boolean sil(int id);

    public boolean guncelle(T obj);

    public List<T> listele();

}
