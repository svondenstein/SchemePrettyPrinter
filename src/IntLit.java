/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
package edu.lsu.CSC4101.SchemePrettyPrinter;

class IntLit extends Node {
  private int intVal;

  public IntLit(int i) {
    intVal = i;
  }

  @Override
  public boolean isNumber(){
    return true;
  }

  public void print(int n) {
    System.out.print(intVal);
  }
//  probably not needed
//  public int getVal() {
//    return intVal;
//  }
}
