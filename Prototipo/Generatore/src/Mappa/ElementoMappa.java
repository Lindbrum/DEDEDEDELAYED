package Mappa;

public abstract class ElementoMappa {
    private int ID;
    private int alert;

    public ElementoMappa(int ID, int alert) {
        this.ID = ID;
        this.alert = alert;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAlert() {
        return alert;
    }

    public void setAlert(int alert) {
        this.alert = alert;
    }

    //aggiungiElementi
    //RimuoviElemento non chiaro

}
