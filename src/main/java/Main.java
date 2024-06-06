import java.io.File;
import java.util.*;

public class Main {

  public static List<Canto> cantos = new ArrayList<>();
  public static final Scanner input = new Scanner(System.in);
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
          System.out.println(canto.getFormattedText());
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

    System.out.println("Wybierz: ");
    System.out.println("D - aby dodać nową pieśń");
    System.out.println("E - aby zakończyć");

    String userChoice = input.nextLine();

    if (Objects.equals(userChoice, "D")) {
      CantoFormatter cantoFormatter = new CantoFormatter();
      System.out.println("Podaj NAZWĘ pieśni: ");
      String name = input.nextLine();
      System.out.println("Podaj TEKST pieśni: ");
      StringBuilder text = new StringBuilder();
      while (input.hasNextLine()) {
        String line = input.nextLine();
        if(line.equals("e"))
          break;
        text.append(line);
      }
      System.out.println();
      Canto canto = cantoFormatter.createCanto(name, text.toString(), 56);
      canto.generateTXT();
      System.out.println("Pomyślnie utworzono pieśń " + canto.getSheets() + " " + name + " " + text);
    }



  }


}
