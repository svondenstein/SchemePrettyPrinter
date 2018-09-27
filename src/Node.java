/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
class Node {
  void print(int n) {}

  void print(int n, boolean p) {
    print(n);
  }

  void printQuote(int n, boolean p){}

  // BooleanLit
  public boolean isBoolean()   {
      return false;
  }

  // IntLit
  public boolean isNumber() {
      return false;
  }

  // StringLit
  public boolean isString() {
      return false;
  }

  // Ident
  public boolean isSymbol() {
      return false;
  }

  // nil
  public boolean isNull() {
      return false;
  }

  // Cons
  public boolean isPair() {
      return false;
  }

  public Node getCar() {
      return null;
  }
  
  public Node getCdr() {
      return null;
  }
  
  public void setCar(Node a) {
  }
  
  public void setCdr(Node d) {
  }
}
