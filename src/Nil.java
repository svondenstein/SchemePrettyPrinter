import java.io.*;
class Nil extends Node {
  public Nil() { }

  @Override
  public boolean isNull(){
      return true;
  }

  public void print(int n)		{ print(n, false); }
  public void print(int n, boolean p) {
    for (int i = 0; i < n; i++)
      System.out.print(" ");
    
    if (p) {
      System.out.print(")");
    } else {
      System.out.println("()");
    }
  }
}
