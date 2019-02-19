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
        LinkedList<Edificio> listEdificio = null;
        LinkedList<Distretto> listDistretto = null;
        LinkedList<Area> listArea = null;


        /*Controllo se ci sono sensori*/
        if(cs.emptySensore()) {
            //Parametri
            int numero_distretti = 5;
            int numer_edifici = 20;
            int numero_aree = 10;
            int numero_sensori = 5;


            System.out.println("Non ci sono sensori installati");
            System.out.println("Procedo a generare i sensori");
            System.out.println("\n");

            // generazione admin installazione
            generateAdmin();
            System.out.println("\n");
            System.out.println("--- GENERATO L'ADMIN ---");

            //Genera Distretti
            generateDistretto(numero_distretti);
            listDistretto = cs.selectDistretti();
            System.out.println("\n");
            System.out.println("--- GENERATI I DISTRETTI ---");

            //Genera Edifici
            generateEdifici(listDistretto, numer_edifici);
            listEdificio = cs.selectEdifici();
            System.out.println("\n");
            System.out.println("--- GENERATI GLI EDIFICI ---");

            // genera aree
            generateArea(listEdificio, numero_aree);
            listArea = cs.selectArea();
            System.out.println("\n");
            System.out.println("--- GENERATE LE AREE ---");


            long startTime = System.currentTimeMillis(); // Timer Per il tempo impiegato nella generazione dei sensori
            //Chiama il generatore di sensori
            generaSensori(listDistretto,listEdificio,listArea, numero_sensori);      // Numero di sensori per area passato come parametro



            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println("\n");
            System.out.println("---SONO STATI GENERATI: " + cs.numeroSensori() + " SENSORI --");
            System.out.println("Tempo impiegato per la generazione dei sensori: " + elapsedTime + "ms");





        }


        System.out.println("Ci sono già " + cs.numeroSensori() + " Sensori installati nel sistema. Inizio procedura di aggiornamento");
        /* Procedura di aggiornamento */

    }


    /* Genera una lista di sensori, il numero di sesnori gli viene passato come parametro come anche il tipo e il massimale */
    static void generaSensori(LinkedList<Distretto> distretto, LinkedList<Edificio> edificio, LinkedList<Area> area, int numero_sensori){
        Random random = new Random();

        Database cs = new Database();
        int massimale = 0;


        for(Distretto d: distretto){
            for(Edificio e: edificio){
                for(Area a: area){

                    /* Sensori di temperatura */
                    massimale = 30;
                    for(int i = 1; i <= numero_sensori/4; i++){
                        Date time = getDateTime();                                          // Prende la data attuale
                        cs.insertSensore("Temperatura", massimale,60, time, a.getID(),1);   //Inserisce il sensore.
                    }

                    /* Sensori di pressione */
                    massimale = 110;
                    for(int i = 1; i <= numero_sensori/4; i++){
                        Date time = getDateTime();                                          // Prende la data attuale
                        cs.insertSensore("Pressione", massimale,60, time, a.getID(),1);  //Inserisce il sensore.
                    }

                    /* Sensori di luminosità */
                    massimale = 50;
                    for(int i = 1; i <= numero_sensori/4; i++){
                        Date time = getDateTime();                                          // Prende la data attuale
                        cs.insertSensore("Luminosità ", massimale,60, time, a.getID(),1);
                    }

                    /* Sensori di umidità */
                    massimale = 100;

                    for(int i = 1; i <= numero_sensori/4; i++){
                        Date time = getDateTime();
                        cs.insertSensore("Umidità ", massimale,60, time, a.getID(),1);
                    }

                }
            }
        }

    }


    /* Generatore di disretti */
    static void generateDistretto(int n){
        Database cs = new Database();
        for(int i = 1; i <= n; i++){
            String nome = "Distretto" + i;
            cs.insertDistretto(nome,0);
        }
    }

    /* Generatore di Edifici per ogni distretto */
    static  void generateEdifici(LinkedList<Distretto> list, int numero_edifici){
        Database cs = new Database();
        for(Distretto l: list){
            for(int i = 1; i <= numero_edifici; i++){
                String nome = "Edificio" + i;
                cs.insertEdifici(nome, l.getID(),0);
            }
        }
    }

    /* Genera Area per ogni edificio */
    static void generateArea(LinkedList<Edificio> list, int numero_Aree){
        Database cs = new Database();
        for(Edificio l: list){
            for(int i = 1; i <= numero_Aree; i++){
                String nome = "Area" + i;
                cs.insertArea(nome, l.getID(),0);
            }
        }

    }


    /* Metodo che prende la data attuale */
    private static Date getDateTime() {
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

        return dNow;
    }

    /* genera l'admin */
    static void generateAdmin(){
        Database cs = new Database();
        cs.insertGestore(1, "admin", "admin", null, 1);
    }




}
