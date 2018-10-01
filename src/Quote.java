/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
package edu.lsu.CSC4101.SchemePrettyPrinter;

class Quote extends Special {

    Quote(){}

    void print(Node t, int n, boolean p) {
        t.getCar().print(n, p); //print '
        t.getCdr().getCar().print(n, false); //print rest of tree
    }

//    @Override
    void printQuote(Node t, int n, boolean p) {
         print(t, n, p);
    }
}
