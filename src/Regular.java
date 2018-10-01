/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
package edu.lsu.CSC4101.SchemePrettyPrinter;

class Regular extends Special {

    Regular(){}

    void print(Node t, int n, boolean p) {
        if (!p) {
            System.out.print("(");
        }
        if (t.getCar().isPair() || t.getCar().isNull()) {
            t.getCar().print(n, false);
        } else {
            t.getCar().print(n, true);
        }
        if (!t.getCdr().isNull()) {
            System.out.print(" ");
            t.getCdr().print(n, true);
        } else {
            t.getCdr().print(0, true);
        }
    }
//    public void printQuote(Node t, int n, boolean p) {
//        print(t, n, p);
//    }
}
