public class Delimiter {
  private final String DOTTED = "<------------------>";
  private final String START = "<- Strona nr: ";
  private final String END = " ->";
  private int counter = 0;

  public String nextDelimiter() {
    StringBuilder sB = new StringBuilder();

    sB.append(DOTTED)
      .append("\n")
      .append(START)
      .append(generateStringNumber())
      .append(END)
      .append("\n")
      .append(DOTTED);

    return sB.toString();
  }

  private String generateStringNumber() {
    String stringNumber = resolveString();
    counter++;
    return stringNumber;
  }

  private String resolveString() {
    String textCounter = Integer.toString(counter);
    if(textCounter.length()==1)
      return "00" + textCounter;
    if(textCounter.length()==2)
      return "0" + textCounter;
    else
      return textCounter;

  }

}
