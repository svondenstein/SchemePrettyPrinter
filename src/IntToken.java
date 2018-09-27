/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
class IntToken extends Token {
  private int intVal;

  public IntToken(int i) {
    super(TokenType.INT);
    intVal = i;
  }
    
  int getIntVal() {
    return intVal;
  }
}
