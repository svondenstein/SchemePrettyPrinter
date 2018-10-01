/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
package edu.lsu.CSC4101.SchemePrettyPrinter;

class IdentToken extends Token {
  private String name;

  public IdentToken(String s) {
    super(TokenType.IDENT);
    name = s;
  }

  String getName() {
    return name;
  }
}
