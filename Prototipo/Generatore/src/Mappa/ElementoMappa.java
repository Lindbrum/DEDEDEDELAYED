package Mappa;

public abstract class ElementoMappa {
    private String ID;
    private int alert;

    public ElementoMappa(String ID, int alert) {
        this.ID = ID;
        this.alert = alert;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
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
