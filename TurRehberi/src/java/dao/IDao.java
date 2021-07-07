package dao;

import java.util.List;

public interface IDao<T> {

    /*
        Sistemimiz var olan varlıklar için oluşturduğumuz arayüz(IDao interface'i)
        ile alt sistemler için veritabanı işlemlerini yönetebileceğimiz basit bir katman sağlamış olduk.
        Bu sayede Kullanici ve tur varlıkları arayüzü uyguladıklarında daha sonrasında Bean sınıflarımızdan
        kolayca veritabanı işlemlerini gerçekleştirdik.        
     */
    public boolean ekle(T obj);

    public boolean sil(int id);

    public boolean guncelle(T obj);

    public List<T> listele();

    public T idIleGetir(int id);
}
