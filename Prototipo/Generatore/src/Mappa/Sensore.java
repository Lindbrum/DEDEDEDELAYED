package Mappa;

import java.util.Date;

public class Sensore extends ElementoMappa {
    private String tipo;
    private float variabileAmbientale;
    private float massimale;
    private int frequenzaInvio;
    private Date ultimoInvio;

    public Sensore(String ID, int alert, String tipo, float variabileAmbientale, float massimale, int frequenzaInvio, Date ultimoInvio) {
        super(ID, alert);
        this.tipo = tipo;
        this.variabileAmbientale = variabileAmbientale;
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

    public float getVariabileAmbientale() {
        return variabileAmbientale;
    }

    public void setVariabileAmbientale(float variabileAmbientale) {
        this.variabileAmbientale = variabileAmbientale;
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
