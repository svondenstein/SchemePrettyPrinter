/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
package edu.lsu.CSC4101.SchemePrettyPrinter;

class StrLit extends Node {
  private String strVal;

  StrLit(String s) {
      strVal = s;
  }

  @Override
  public boolean isString() {
      return true;
  }

  public void print(int n) {
      System.out.print("\"" + strVal + "\"");
  }

//  public String getVal() {
//      return strVal;
//  }
}
