package Generatore;

import Mappa.Edificio;
import Server.Database;
import Mappa.Sensore;
import Mappa.Distretto;
import Mappa.Area;

import java.util.LinkedList;
import java.util.Random;

import java.util.Date;
import java.text.SimpleDateFormat;


public class Generatore {

    public static void main(String[] args) {
        Database cs = new Database();
        LinkedList<Sensore> lista = new LinkedList<Sensore>();
        LinkedList<Edificio> listEdficio = null;
        LinkedList<Distretto> listDistretto = null;
        LinkedList<Area> listArea = null;


        /*Controllo se ci sono sensori*/
        if(cs.emptySensore()) {
            System.out.println("Non ci sono sensori installati");
            System.out.println("Procedo a generare i sensori");
            // Non ci sono sensore
            //Procedo ad inserire i sensori.


            //Attenzione Suppongo che già siano stati inseriti Istallatore e Zone all'interno del db.
            /*  Recupero le liste, in modo tale da recuperare gli id da inserire nel sensore */
            listDistretto = cs.selectDistretti();
            listEdficio = cs.selectEdifici();
            listArea = cs.selectArea();

            //Chiama il generatore di sensori
            lista = generaSensori(listDistretto,listEdficio,listArea, 10);


            //Test
            for(Sensore t: lista){
                System.out.println("id:" + t.getID());
                System.out.println("Data" + t.getUltimoInvio());
            }





        }

        //System.out.println("Ci sono sensori presenti!");
        //System.out.println("Aggiornamento Valori");

    }


    /* Genera una lista di sensori, il numero di sesnori gli viene passato come parametro come anche il tipo e il massimale */
    public static LinkedList<Sensore> generaSensori(LinkedList<Distretto> distretto, LinkedList<Edificio> edificio, LinkedList<Area> area, int numero_sensori){
        LinkedList<Sensore> list = new LinkedList<Sensore>();
        Random random = new Random();

        Database cs = new Database();


        for(Distretto d: distretto){
            for(Edificio e: edificio){
                for(Area a: area){

                    /* Sensori di temperatura */
                    for(int i = 1; i <= numero_sensori/3; i++){
                        String id = "D" + d.getID() + "E" + e.getID() + "A" + a.getID() + "S" + i;   // Id composto
                        int valore_ambientale = random.nextInt(30);                  // Valore random
                        Date time = getDateTime();                                          // Prende la data attuale

                        Sensore s = new Sensore(id,0, "Temperatura", valore_ambientale, 30, 60,time);

                        list.add(s);

                    }

                    /* Sensori di pressione */
                    for(int i = 1; i <= numero_sensori/3; i++){
                        String id = "D" + d.getID() + "E" + e.getID() + "A" + a.getID() + "S" + i;   // Id composto
                        int valore_ambientale = random.nextInt(150);                  // Valore random
                        Date time = getDateTime();                                          // Prende la data attuale

                        Sensore s = new Sensore(id,0, "Pressione", valore_ambientale, 150, 60,time);
                        list.add(s);

                    }

                    /* Sensori di luminosità */
                    for(int i = 1; i <= numero_sensori/3; i++){
                        String id = "D" + d.getID() + "E" + e.getID() + "A" + a.getID() + "S" + i;   // Id composto
                        int valore_ambientale = random.nextInt(50);                  // Valore random
                        Date time = getDateTime();                                          // Prende la data attuale

                        Sensore s = new Sensore(id,0, "Luminosità", valore_ambientale, 50, 60,time);
                        list.add(s);

                    }

                }
            }
        }


        return list;
    }


    /* Metodo che prende la data attuale */
    private static Date getDateTime() {
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

        return dNow;
    }





}
