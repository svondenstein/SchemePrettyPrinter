/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
package edu.lsu.CSC4101.SchemePrettyPrinter;

class IntToken extends Token {
  private int intVal;

  IntToken(int i) {
    super(TokenType.INT);
    intVal = i;
  }
    
  int getIntVal() {
      return intVal;
  }
}
