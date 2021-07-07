package Test;

import dao.DocumentDAO;
import entity.Document;
import java.util.List;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;

public class DocumentTestleri {
    
    private final DocumentDAO documentDao = DocumentDAO.getDocumentDao();
    
    @Test
    public void documentTestleri() {
        Document document = new Document();
        document.setFileName("dosyaName");
        document.setFilePath("dosyaPath");
        document.setFileType("dosyaType");
        assertTrue(documentDao.insert(document));
        List<Document> dosyalar = documentDao.findAll("", 1, 100);
        assertTrue(!dosyalar.isEmpty());
        document.setFileName("dosyaNameUpdate");
        document.setDocument_id(dosyalar.get(dosyalar.size() - 1).getDocument_id());
        assertTrue(documentDao.update(document));
        assertTrue(documentDao.delete(dosyalar.get(dosyalar.size() - 1)));
    }
}
