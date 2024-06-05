import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CantoConverter {

  String delimiter = "<------------------>\\s*<- Strona nr: \\d{3} ->\\s*<------------------>";
  private String text;

  public Canto toCanto(String text, String fileName) {
    Canto canto = new Canto();
    canto.setFileName(fileName);

    List<Sheet> sheets = new ArrayList<>();

    // canto without
    //<------------------>
    //<- Strona nr: 000 ->
    //<------------------>

    //number + title + liczba stron
    String trimText = text.substring(64);
    //podział na strony
    String [] textSheets = trimText.split(delimiter);

    this.text = textSheets[0].trim();

    // pierwsza strona numer pieśni i tytuł
    int cantoNumber = resolveCantoNumber(fileName);
    canto.setNumber(cantoNumber);

    String cantoTitle = resolveCantoTitle();
    canto.setCantoName(cantoTitle);
    return canto;
  }

  // Optional
  private int resolveCantoNumber(String fileName) {
    int number = 0;
    String regex = "\\d+";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);
    if (matcher.find()) {
      String textNumber = matcher.group();
      number = Integer.parseInt(textNumber);
      int endIndex = matcher.end();
      this.text = text.substring(endIndex);
    }
    if(number==0) {
      return Integer.parseInt(fileName.split("\\.")[0]);
    }
    return number;
  }

  private String resolveCantoTitle() {
    String title;
    String regex = "\\d";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);
    if (matcher.find()) {
      int startIndex = matcher.start();
      title = text.substring(0, startIndex);
      this.text = text.substring(startIndex);
    } else {
      title = text;
    }
    title = title
              .trim()
              .replaceAll("\\r?\\n", " ")
              .replaceAll("\\s+", " ");
    return title;
  }

  private int resolveSheetCounter() {
    int sheetNumber = 0;

    return sheetNumber;
  }


}
