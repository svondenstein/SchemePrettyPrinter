/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
package edu.lsu.CSC4101.SchemePrettyPrinter;

class Ident extends Node {
  private String name;

  Ident(String n) {
    name = n;
  }

  @Override
  public boolean isSymbol(){
      return true;
  }

  String getName(){
    return name;
  }

  public void print(int n) {
    System.out.print(name);
  }
//  probably not needed
//  public void print(int n, boolean parenPrintedLast) {
//      print(n);
//  }
}
