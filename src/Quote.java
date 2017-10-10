import java.io.*;

class Quote extends Special {
    public Quote(){}

    void print(Node t, int n, boolean p) {
        t.getCar().print(n, p);
        t.getCdr().getCar().print(n, false);
    }

    void printQuote(Node t, int n, boolean p) {
        print(t, n, p);
    }
}
