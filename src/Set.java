/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
package edu.lsu.CSC4101.SchemePrettyPrinter;

class Set extends Special {

    public Set(){}

    void print(Node t, int n, boolean p) {
        if(!p) {
            System.out.print("(");
        }
        t.getCar().print(n, false);
        if(!t.getCdr().isNull())
            System.out.print(" ");
        t.getCdr().print(n, true);
    }
}