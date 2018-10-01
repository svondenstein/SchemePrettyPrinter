/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
package edu.lsu.CSC4101.SchemePrettyPrinter;

class Nil extends Node {

  public Nil() {}

  @Override
  public boolean isNull(){
    return true;
  }

  public void print(int n) {
    print(n, false);
  }

  public void print(int n, boolean p) {
    for (int i = 0; i < n; i++)
      System.out.print(" ");
    if (p) {
      System.out.print(")");
    } else {
      System.out.print("()");
    }
  }
}
