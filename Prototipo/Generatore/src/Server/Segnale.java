package Server;

import java.util.Date;

public class Segnale {
    private int IDSegnale;
    private int IDSensore;
    private Date ora;
    private float variabileAmbientale;
    private int alert;


    public Segnale(int IDSegnale, int IDSensore, Date ora, float variabileAmbientale, int alert) {
        this.IDSegnale = IDSegnale;
        this.IDSensore = IDSensore;
        this.ora = ora;
        this.variabileAmbientale = variabileAmbientale;
        this.alert = alert;
    }

    public int getIDSegnale() {
        return IDSegnale;
    }

    public void setIDSegnale(int IDSegnale) {
        this.IDSegnale = IDSegnale;
    }

    public int getIDSensore() {
        return IDSensore;
    }

    public void setIDSensore(int IDSensore) {
        this.IDSensore = IDSensore;
    }

    public Date getOra() {
        return ora;
    }

    public void setOra(Date ora) {
        this.ora = ora;
    }

    public float getVariabileAmbientale() {
        return variabileAmbientale;
    }

    public void setVariabileAmbientale(float variabileAmbientale) {
        this.variabileAmbientale = variabileAmbientale;
    }

    public int getAlert() {
        return alert;
    }

    public void setAlert(int alert) {
        this.alert = alert;
    }
}
