import java.util.Arrays;

public class Sheet {
  public static final int ROW_COUNT = 8;
  public static final int COLUMN_COUNT = 20;
  private String delimiter = " -------";
  private int number;
  private String text;
  private int rows;

  public Sheet(int number, String text) {
    this.number = number;
    this.text = generateFormattedSheet(text);
    this.delimiter = generateDelimiter(number);
  }
  private String generateFormattedSheet(String text){
    Character [][] sheetTable = new Character[Sheet.ROW_COUNT][Sheet.COLUMN_COUNT];
    int charCounter = 0;
    for(int i = 0; i < Sheet.ROW_COUNT; i++) {
      for (int j = 0; j < Sheet.COLUMN_COUNT; j++) {
        if(charCounter < text.length()) {
          sheetTable[i][j] = text.charAt(charCounter);
          charCounter++;
        } else {
          sheetTable[i][j] = ' ';
        }
      }
    }
    StringBuilder sheet = new StringBuilder();
    for(int i = 0; i < Sheet.ROW_COUNT; i++) {
      for (int j = 0; j < Sheet.COLUMN_COUNT; j++) {
        sheet.append(sheetTable[i][j]);
      }
      sheet.append("\n");
    }
    return sheet.toString();
  }

  private String generateDelimiter(int number) {
    StringBuilder sb = new StringBuilder();
    return sb.append(Delimiter.DOTTED)
      .append("\n")
      .append(Delimiter.START)
      .append(convert(number))
      .append(Delimiter.END)
      .append("\n")
      .append(Delimiter.DOTTED)
      .toString();
  }

  private String convert(int number) {
    String stringNumber = String.valueOf(number);
    if (stringNumber.length()==1)
      stringNumber = "00" + stringNumber;
    if(stringNumber.length()==2)
      stringNumber = "0" + stringNumber;
    return stringNumber;
  }


  @Override
  public String toString() {
    return delimiter + "\n" + text;
  }
}
