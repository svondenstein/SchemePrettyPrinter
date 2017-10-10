class Cond extends Special {

    public Cond(){}

    void print(Node t, int n, boolean p) {
        if(!p) {
            System.out.print("(");
        }
        t.getCar().print(n, true);
        System.out.println();
        if (!t.getCdr().isNull()) {
            printSubtree(t.getCdr(), n + 4);
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
