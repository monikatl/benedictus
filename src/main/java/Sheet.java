import java.util.*;

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
    String [] tabText = text.split(" ");
    Stack<String> words = new Stack<>();
    List<String> currentLineWords = new ArrayList<>();
    Collections.addAll(words, tabText);
    words.forEach(System.out::println);
    StringBuilder sheet = new StringBuilder();

    boolean isWordCreating = true;

    for(int i = 0; i < Sheet.ROW_COUNT; i++) {
      while (isWordCreating) {
        int lineSum = 0;
        if(words.isEmpty())
          break;
        int currentWordLength = words.peek().length();
        lineSum += currentWordLength;
        if(lineSum > 20) {
          break;
        }
        else {
          currentLineWords.add(words.pop());
          Collections.reverse(currentLineWords);
        }
      }
      currentLineWords.forEach(sheet::append);
      sheet.append('\n');
      if (words.isEmpty()){
        break;
      }
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
