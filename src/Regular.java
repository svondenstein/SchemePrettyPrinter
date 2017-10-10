import java.io.*;

class Regular extends Special {
    public Regular(){}

    void printQuote(Node t, int n, boolean p){
        if(!p) {
            System.out.print("(");

        }
        if (t.getCar().isPair()) {
            p = false;
        }
        t.getCar().print(0, p);
        p = true;
        t.getCdr().print(n, p);

        print(t, n, p);
    }

    void print(Node t, int n, boolean p) {
        if (!p) {
            System.out.print("(");
        }
        if (t.getCar().isPair() || t.getCar().isNull()) {
            t.getCar().print(n, false);
            if (!t.getCdr().isNull()) {
                System.out.print(" ");
                System.out.println();
            }
        } else {
            t.getCar().print(n, true);
            if (!t.getCdr().isNull()) {
                System.out.print(" ");
            }
        }
        if (t.getCdr().isNull()) {
            t.getCdr().print(0, true);
        } else {
            t.getCdr().print(n, true);
        }
    }
}
