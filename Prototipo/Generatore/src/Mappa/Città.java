package Mappa;

public abstract class Città extends ElementoMappa {
    private int numElementi;

    public Città(int ID, int alert, int numElementi) {
        super(ID, alert);
        this.numElementi = numElementi;
    }

    public int getNumElementi() {
        return numElementi;
    }

    public void setNumElementi(int numElementi) {
        this.numElementi = numElementi;
    }
}
