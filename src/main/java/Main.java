import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

  public static List<Canto> cantos = new ArrayList<>();
  public static void main(String[] args) {

    String folderPath = "C:\\Users\\Monika\\Desktop\\parafia\\benedictus org\\TEXT";
    CantosReader cantosReader = new CantosReader();
    CantoConverter cantoConverter = new CantoConverter();

    File folder = new File(folderPath);

    if (folder.exists() && folder.isDirectory()) {
      File[] listOfFiles = folder.listFiles();

      for (File file : listOfFiles) {
        if (file.isFile()) {
          String filePath = file.getAbsolutePath();
          String content = cantosReader.getCanto(filePath);
          Canto canto = cantoConverter.toCanto(content, file.getName());
          cantos.add(canto);
        }
      }
    } else {
      System.out.println("Podana ścieżka nie jest katalogiem lub nie istnieje.");
    }

    // create cantos list
    cantos.sort(Comparator.comparing(Canto::getCantoName));
    // sub 600-699 - psalms
    cantos = cantos.stream().filter(canto -> canto.getNumber() > 699 || canto.getNumber() < 600).toList();
    PdfWriter.exportToPDF(cantos);
  }


}
