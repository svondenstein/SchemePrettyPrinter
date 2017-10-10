import java.io.*;
class BooleanLit extends Node {
  private boolean booleanVal;

  public BooleanLit(boolean b) {
    booleanVal = b;
  }

  @Override
  public boolean isBoolean(){
      return true;
  }

  public void print(int n) {
    if (booleanVal) {
      System.out.println("#t");
    } else {
      System.out.println("#f");
    }
  }
}
