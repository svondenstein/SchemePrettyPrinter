/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
package edu.lsu.CSC4101.SchemePrettyPrinter;

class BooleanLit extends Node {
  private boolean booleanVal;

  BooleanLit(boolean b) {
    booleanVal = b;
  }

  @Override
  public boolean isBoolean(){
      return true;
  }

  public void print(int n) {
    if (booleanVal) {
      System.out.print("#t");
    } else {
      System.out.print("#f");
    }
  }
}
