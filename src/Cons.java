/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
package edu.lsu.CSC4101.SchemePrettyPrinter;

class Cons extends Node {
    private Node car;
    private Node cdr;
    private Special form;
  
    // parseList() `parses' special forms, constructs an appropriate
    // object of a subclass of Special, and stores a pointer to that
    // object in variable form.  It would be possible to fully parse
    // special forms at this point.  Since this causes complications
    // when using (incorrect) programs as data, it is easiest to let
    // parseList only look at the car for selecting the appropriate
    // object from the Special hierarchy and to leave the rest of
    // parsing up to the interpreter.
    void parseList() {
        if(car.isSymbol()) {
            if (((Ident) car).getName().compareTo("\'") == 0 || (((Ident) car).getName().compareTo("quote") == 0)){
                form = new Quote();
            } else if (((Ident) car).getName().compareTo("set!") == 0) {
                form = new Set();
            } else if (((Ident) car).getName().compareTo("lambda") == 0) {
                form = new Lambda();
            } else if (((Ident) car).getName().compareTo("let") == 0) {
                form = new Let();
            } else if (((Ident) car).getName().compareTo("begin") == 0) {
                form = new Begin();
            } else if (((Ident) car).getName().compareTo("if") == 0) {
                form = new If();
            } else if (((Ident) car).getName().compareTo("cond") == 0) {
                form = new Cond();
            } else if (((Ident) car).getName().compareTo("define") == 0) {
                form = new Define();
            } else {
                form = new Regular();
            }
        } else {
            form = new Regular();
        }

    }

    @Override
    public boolean isPair(){
        return true;
    }

    @Override
    public Node getCar(){
       return car;
    }

    @Override
    public Node getCdr(){
        return cdr;
    }

    public Cons(Node a, Node d) {
	    car = a;
	    cdr = d;
	    parseList();
    }

    void print(int n) {
	    form.print(this, n, false);
    }

    void print(int n, boolean p) {
	    form.print(this, n, p);
    }
}
