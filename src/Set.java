import java.io.*;

class Set extends Special {
    public Set(){}

    void print(Node t, int n, boolean p) {
        if(!p) {
            for(int i = 0; i < n; i++) {
                System.out.print(" ");
            }
            System.out.print("(");
            //p = true;
        }
        t.getCar().print(n, false);
        if(!t.getCdr().isNull())
            System.out.print(" ");
        t.getCdr().print(n, true);
    }

    //@Override
    void printQuote(Node t, int n, boolean p) {
        print(t, n, p);
    }
}
