class Begin extends Special {
    public Begin(){}

    void print(Node t, int n, boolean p) {
        if(!p) {
            System.out.print("(");
        }
        t.getCar().print(n, true);
        System.out.println();
        if (!t.getCdr().isNull()) {
            printSubtree(t.getCdr(), n + 4, false);
        }
    }

    private void printSubtree(Node t, int n, boolean isQuote) {
        if (isQuote) {
            System.out.print(" ");
            t.getCar().printQuote(n, false);
        } else {
            for(int i = 0; i < n; i++)
                System.out.print(" ");
            t.getCar().print(n);
            System.out.println();
        }
        if(!t.getCdr().isNull()) {
            printSubtree(t.getCdr(), n, isQuote);
        } else {
            t.getCdr().print(n - 4, true);
        }
    }

    void printQuote(Node t, int n, boolean p) {
        if(!p) {
            System.out.print(" ");
        }
        t.getCar().print(n, true);
        if (!t.getCdr().isNull()) {
            printSubtree(t.getCdr(), 0, true);
        }
    }
}
