import java.util.ArrayList;
import java.util.List;

public class Canto {

  private Delimiter delimiterBuilder;
  private String fileName;
  private int sheetCounter;
  private int number;
  private String cantoName;

  private Kind kind;

  private List<Sheet> sheets;


  public Canto() {

  }

  public Canto(String fileName, String cantoName, int number, int sheetCounter) {
    this.fileName = fileName;
    this.cantoName = cantoName;
    this.number = number;
    this.sheetCounter = sheetCounter;
    this.sheets = new ArrayList<>(sheetCounter);
  }

  public void setDelimiterBuilder(Delimiter delimiterBuilder) {
    this.delimiterBuilder = delimiterBuilder;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public void setSheetCounter(int sheetCounter) {
    this.sheetCounter = sheetCounter;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public void setCantoName(String cantoName) {
    this.cantoName = cantoName;
  }

  public void setSheets(List<Sheet> sheets) {
    this.sheets = sheets;
  }

  public Delimiter getDelimiterBuilder() {
    return delimiterBuilder;
  }

  public String getFileName() {
    return fileName;
  }

  public int getSheetCounter() {
    return sheetCounter;
  }

  public int getNumber() {
    return number;
  }

  public String getCantoName() {
    return cantoName;
  }

  public List<Sheet> getSheets() {
    return sheets;
  }

  public String getNumberAndTitle() {
    return String.format("%-9d %s", number, cantoName);
  }

}
