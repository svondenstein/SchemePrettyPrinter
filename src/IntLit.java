/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
class IntLit extends Node {
  private int intVal;

  public IntLit(int i) { intVal = i; }

  @Override

  public boolean isNumber(){
    return true;
  }

  public void print(int n) {
    System.out.print(intVal);
  }
}
