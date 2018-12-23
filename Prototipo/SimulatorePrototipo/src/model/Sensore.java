package model;

public class Sensore {

    /** Variabili **/
    private int id;
    private int temp;

    /** Costruttore **/
    public Sensore(int id, int temp){
        this.id = id;
        this.temp = temp;
    }

    /** Set e Get **/
    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setTemp(int temp){
        this.temp = temp;
    }

    public int getTemp(){
        return this.temp;
    }
}
