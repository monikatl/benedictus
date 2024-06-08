import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TxtWriter {
   public static void exportToTXT(List<Canto> cantos, String fileName) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
      String formattedText = cantos.stream().filter(canto -> !canto.getCantoName().isEmpty()).map(Canto::getNumberTitleCounterAndSheets).collect(Collectors.joining("\n"));
      writer.write(formattedText);
      System.out.println("File created and content written successfully.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void exportToTXT(List<Canto> cantos) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("cantos_list.txt"))) {
      String formattedText = cantos.stream().filter(canto -> !canto.getCantoName().isEmpty()).map(Canto::getNumberAndTitleSpaces).collect(Collectors.joining("\n"));
      writer.write(formattedText);
      System.out.println("File created and content written successfully.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
