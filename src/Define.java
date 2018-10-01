/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
package edu.lsu.CSC4101.SchemePrettyPrinter;

class Define extends Special {

    public Define(){}

    void print(Node t, int n, boolean p) {
        if(!p) {
            System.out.print("(");
        }
        t.getCar().print(n, true);
        System.out.print(" ");
        t.getCdr().getCar().print(n, false);
        if (t.getCdr().getCar().isPair()) {
            System.out.println();
            printSubtree(t.getCdr().getCdr(), n + 4);
        } else {
            System.out.print(" ");
            t.getCdr().getCdr().print(n, true);
        }
    }

    private void printSubtree(Node t, int n) {
        for(int i = 0; i < n; i++)
            System.out.print(" ");
        t.getCar().print(n);
        System.out.println();
        if(!t.getCdr().isNull()) {
            printSubtree(t.getCdr(), n);
        } else {
            t.getCdr().print(n - 4, true);
        }
    }
}
