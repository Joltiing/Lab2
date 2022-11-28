import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfHelper {
    public static void saveTo(String path, String content){
        Document document = new Document();
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            document.add(new Paragraph(content));
            document.close();
            writer.close();
        } catch (DocumentException | FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
