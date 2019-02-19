package Mappa;

import java.util.Date;

public class Sensore extends ElementoMappa {
    private String tipo;
    private float massimale;
    private int frequenzaInvio;
    private Date ultimoInvio;

    public Sensore(int ID, int alert, String tipo, float massimale, int frequenzaInvio, Date ultimoInvio) {
        super(ID, alert);
        this.tipo = tipo;
        this.massimale = massimale;
        this.frequenzaInvio = frequenzaInvio;
        this.ultimoInvio = ultimoInvio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getMassimale() {
        return massimale;
    }

    public void setMassimale(float massimale) {
        this.massimale = massimale;
    }

    public int getFrequenzaInvio() {
        return frequenzaInvio;
    }

    public void setFrequenzaInvio(int frequenzaInvio) {
        this.frequenzaInvio = frequenzaInvio;
    }

    public Date getUltimoInvio() {
        return ultimoInvio;
    }

    public void setUltimoInvio(Date ultimoInvio) {
        this.ultimoInvio = ultimoInvio;
    }

}
