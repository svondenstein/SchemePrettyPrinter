/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
class Regular extends Special {

    public Regular(){}

    void print(Node t, int n, boolean p) {
        if (!p) {
            System.out.print("(");
        }
        if (t.getCar().isPair() || t.getCar().isNull()) {
            t.getCar().print(n, false);
            //????
            if (!t.getCdr().isNull()) {
                System.out.print(" ");
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
            t.getCdr().print(n,true);
        }
    }
}
