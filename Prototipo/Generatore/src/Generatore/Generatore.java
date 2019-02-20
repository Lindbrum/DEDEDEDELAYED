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
        LinkedList<Sensore> listSensore = null;


        /*Controllo se ci sono sensori*/
        if(cs.emptySensore()) {
            //Parametri
            int numero_distretti = 5;
            int numer_edifici = 20;
            int numero_aree = 10;


            System.out.println("Non ci sono sensori installati");
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


            System.out.println("\n");
            System.out.println("--- GENERAZIONE SENSORI ---");
            long startTime = System.currentTimeMillis(); // Timer Per il tempo impiegato nella generazione dei sensori
            //Chiama il generatore di sensori
            generaSensori(listArea);      // Genera i sensori per ogni area (Ne inserisce 1 per tipo)

            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println("\n");
            System.out.println("---SONO STATI GENERATI: " + cs.numeroSensori() + " SENSORI --");
            System.out.println("Tempo impiegato per la generazione dei sensori: " + elapsedTime + "ms");

            startTime = System.currentTimeMillis();
            // Generazione segnali per ogni sensore
            listSensore = cs.selectSensori();
            System.out.println("\n");
            System.out.println("--- INSERIMENTO PRIMI SEGNALI ---");
            generaSegnale(listSensore, 2);                      // Numero segnali per ogni sensore

            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;

            System.out.println("\n");
            System.out.println("--- SEGNALI GENERATI IN "+ elapsedTime +" ---");

        }


        System.out.println("\n");
        System.out.println("Ci sono: " + cs.numeroSensori() + " Sensori installati nel sistema. Inizio procedura di aggiornamento");
        /* Procedura di aggiornamento */
        while(true){

        }

    }


    /* Genera una lista di sensori, il numero di sesnori gli viene passato come parametro come anche il tipo e il massimale */
    static void generaSensori(LinkedList<Area> area){

        Database cs = new Database();
        Date time = null;
        int massimale = 0;

        for(Area a: area){

            /* Sensori di temperatura */
            massimale = 30;

            time = getDateTime();                                          // Prende la data attuale
            cs.insertSensore("Temperatura", massimale,60, time, a.getID(),1);   //Inserisce il sensore.


            /* Sensori di pressione */
            massimale = 110;

            time = getDateTime();                                          // Prende la data attuale
            cs.insertSensore("Pressione", massimale,60, time, a.getID(),1);  //Inserisce il sensore.


            /* Sensori di luminosità */
            massimale = 50;

            time = getDateTime();                                          // Prende la data attuale
            cs.insertSensore("Luminosità", massimale,60, time, a.getID(),1);

            /* Sensori di umidità */
            massimale = 100;


            time = getDateTime();
            cs.insertSensore("Umidità", massimale,60, time, a.getID(),1);


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

        return dNow;
    }

    /* genera l'admin */
    static void generateAdmin(){
        Database cs = new Database();
        cs.insertGestore(1, "admin", "admin", null, 1);
    }

    /* Genera Segnale */
    static void generaSegnale(LinkedList<Sensore> list, int numero_segnali){
        Database cs = new Database();
        Random rand = new Random();
        Date time;

        for(Sensore s: list){
            if(s.getTipo().equals("Temperatura")){
                for(int i = 1; i <= numero_segnali; i++){
                    time = getDateTime();
                    cs.insertSegnali(s.getID(), time, rand.nextInt(30),0);
                }
            }else if(s.getTipo().equals("Pressione")){
                for(int i = 1; i <= numero_segnali; i++){
                    time = getDateTime();
                    cs.insertSegnali(s.getID(), time, rand.nextInt(110),0);
                }
            }else if(s.getTipo().equals("Luminosità")) {
                for (int i = 1; i <= numero_segnali; i++) {
                    time = getDateTime();
                    cs.insertSegnali(s.getID(), time, rand.nextInt(50), 0);
                }
            } else if(s.getTipo().equals("Umidità")) {
                for(int i = 1; i <= numero_segnali; i++){
                    time = getDateTime();
                    cs.insertSegnali(s.getID(), time, rand.nextInt(100),0);
                }
            }
        }
    }
}
