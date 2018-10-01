/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
package edu.lsu.CSC4101.SchemePrettyPrinter;

class StrToken extends Token {
  private String strVal;

  StrToken(String s) {
    super(TokenType.STRING);
    strVal = s;
  }

  String getStrVal() {
      return strVal;
  }
}
