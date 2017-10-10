import java.io.*;
class StrLit extends Node {
  private String strVal;

  public StrLit(String s) { strVal = s; }

  @Override
  public boolean isString(){
      return true;
  }

  public void print(int n) {

    System.out.print("\"" + strVal + "\"");
  }
}
