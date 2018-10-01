/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
package edu.lsu.CSC4101.SchemePrettyPrinter;

class Token implements TokenType {
  private int tt;

  Token(int t) {
      tt = t;
  }

  int getType() {
      return tt;
  }

  int getIntVal() {
      return 0;
  }

  String getStrVal() {
      return "";
  }

  String getName() {
      return "";
  }
}
