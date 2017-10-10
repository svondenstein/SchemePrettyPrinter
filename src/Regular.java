import java.io.*;

class Regular extends Special {

    public Regular(){}

    void printQuote(Node t, int n, boolean p){
       /* if(!p) {
            System.out.print("(");
            p = true;
        }
        for(int i = 0; i < n; i++) {
            System.out.print(" ");
        }
        if (t.getCar().isPair()) {
            p = false;
        }
        t.getCar().print(0, p);
        p = true;
        t.getCdr().print(n, p);
        */

        print(t, n, p);
    }

    void print(Node t, int n, boolean p) {
        if (!p) {
            /*for(int i = 0; i < n; i++) {
                System.out.print(" ");
            }
            */
            System.out.print("(");
        }
        //Checks for subtrees that should printed inline
        //
        /*
        boolean foundSubtree = false;
        Node nodelet = t;
        while (!foundSubtree) {
            nodelet = nodelet.getCdr();
            if (nodelet.isNull()) {
                break;
            } else if (nodelet.getCar().isPair()) {
                foundSubtree = true;
            }

        }
        *
        //Assign correct offset based on inline printing
        int s = n;
        if (foundSubtree) {
            s = 0;
        }
        */
        if (t.getCar().isPair() || t.getCar().isNull()) {
            //t.getCar().print(s, false);
            t.getCar().print(n, false);
            if (!t.getCdr().isNull()) {
                System.out.print(" ");
            }
            /*if (s != 0) {
                System.out.println();
            }
            */
        } else {
            //t.getCar().print(s, true);
            t.getCar().print(n, true);
            if (!t.getCdr().isNull()) {
                System.out.print(" ");
            }
        }
        /*if (!t.getCdr().isNull()) {
            System.out.print(" ");
        }*/
        //Subtree solution 1
        /*if (t.getCar().isPair())
            System.out.println();*/
        //p = true;

        if (t.getCdr().isNull()) {
            t.getCdr().print(0, true);
        } else {
            //t.getCdr().print(s, true);
            t.getCdr().print(n,true);
        }
    }
}
