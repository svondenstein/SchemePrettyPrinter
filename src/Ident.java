import java.io.*;
class Ident extends Node {
  private String name;

  public Ident(String n) { name = n; }

  @Override
  public boolean isSymbol(){
      return true;
  }

  public String getName(){
    return name;
  }

  public void print(int n) {
    System.out.print(name);
  }
}
