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
            long startTime = System.currentTimeMillis(); // Timer Per il tempo impiegato nella generazione dei sensori
            System.out.println("Non ci sono sensori installati");
            System.out.println("Procedo a generare i sensori");
            System.out.println("\n");
            // Non ci sono sensore
            //Procedo ad inserire i sensori.


            //Attenzione Suppongo che già siano stati inseriti Istallatore e Zone all'interno del db.
            /*  Recupero le liste, in modo tale da recuperare gli id da inserire nel sensore */
            listDistretto = cs.selectDistretti();
            listEdficio = cs.selectEdifici();
            listArea = cs.selectArea();

            //Chiama il generatore di sensori
            lista = generaSensori(listDistretto,listEdficio,listArea, 40);      // Numero di sensori per area passato come parametro


            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println("Sono stati generati: " + lista.size() + " Sensori");
            System.out.println("Tempo impiegato per la generazione dei sensori: " + elapsedTime);





        }


        System.out.println("Ci sono già " + cs.numeroSensori() + " Sensori installati nel sistema. Inizio procedura di aggiornamento");
        /* Procedura di aggiornamento */

    }


    /* Genera una lista di sensori, il numero di sesnori gli viene passato come parametro come anche il tipo e il massimale */
    public static LinkedList<Sensore> generaSensori(LinkedList<Distretto> distretto, LinkedList<Edificio> edificio, LinkedList<Area> area, int numero_sensori){
        LinkedList<Sensore> list = new LinkedList<Sensore>();
        Random random = new Random();

        Database cs = new Database();
        int massimale = 0;
        int range = 0;

        for(Distretto d: distretto){
            for(Edificio e: edificio){
                for(Area a: area){

                    /* Sensori di temperatura */
                    massimale = 30;
                    range = 35;
                    for(int i = 1; i <= numero_sensori/4; i++){
                        int n = cs.numeroSensori() + 1;
                        String id = "D" + d.getID() + "E" + e.getID() + "A" + a.getID() + "ST" + n;   // Id composto
                        int valore_ambientale = random.nextInt(range);                  // Valore random
                        Date time = getDateTime();                                          // Prende la data attuale


                        String myArea = a.getID();
                        int zona = Integer.parseInt(myArea);

                        Sensore s = new Sensore(id,0, "Temperatura", valore_ambientale, massimale, 60,time);
                        cs.insertSensore(n,"Temperatura", valore_ambientale, massimale,60, time, zona,1);

                        list.add(s);

                    }

                    /* Sensori di pressione */
                    massimale = 110;
                    range = 120;
                    for(int i = 1; i <= numero_sensori/4; i++){
                        int n = cs.numeroSensori() + 1;
                        String id = "D" + d.getID() + "E" + e.getID() + "A" + a.getID() + "SP" + n;   // Id composto
                        int valore_ambientale = random.nextInt(range);                  // Valore random
                        Date time = getDateTime();                                          // Prende la data attuale

                        String myArea = a.getID();
                        int zona = Integer.parseInt(myArea);


                        Sensore s = new Sensore(id,0, "Pressione", valore_ambientale, massimale, 60,time);
                        cs.insertSensore(n,"Pressione", valore_ambientale, massimale,60, time, zona,1);
                        list.add(s);

                    }

                    /* Sensori di luminosità */
                    massimale = 50;
                    range = 55;
                    for(int i = 1; i <= numero_sensori/4; i++){
                        int n = cs.numeroSensori() + 1;
                        String id = "D" + d.getID() + "E" + e.getID() + "A" + a.getID() + "SL" + n;   // Id composto
                        int valore_ambientale = random.nextInt(range);                  // Valore random
                        Date time = getDateTime();                                          // Prende la data attuale

                        String myArea = a.getID();
                        int zona = Integer.parseInt(myArea);


                        Sensore s = new Sensore(id,0, "Luminosità", valore_ambientale, massimale, 60,time);
                        cs.insertSensore(n,"Luminosità", valore_ambientale, massimale,60, time, zona,1);
                        list.add(s);

                    }

                    /* Sensori di umidità */
                    massimale = 100;
                    range = 110;
                    for(int i = 1; i <= numero_sensori/4; i++){
                        int n = cs.numeroSensori() + 1;
                        String id = "D" + d.getID() + "E" + e.getID() + "A" + a.getID() + "SU" + n;
                        int valore_ambientale = random.nextInt(range);
                        Date time = getDateTime();

                        String myArea = a.getID();
                        int zona = Integer.parseInt(myArea);


                        Sensore s = new Sensore(id,0, "Umidità", valore_ambientale, massimale, 60,time);
                        cs.insertSensore(n,"Umidità", valore_ambientale, massimale,60, time, zona,1);
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
