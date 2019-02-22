package Server;

import Mappa.Area;
import Mappa.Distretto;
import Mappa.Edificio;
import Mappa.Sensore;
import Users.Gestore;


import java.sql.*;
import java.util.LinkedList;



public class Database {
    int numero_dis = 10; // NumeroDistretti

    Connection dbConnection = ConnectionDB.Connect();

    /* Controlla se ci sono sensori*/
    public boolean emptySensore(){


        try {
            Statement stmt = dbConnection.createStatement() ;
            String query = "select count(*) AS totale from Sensore;" ;
            ResultSet rs = stmt.executeQuery(query) ;

            rs.next();

            if(rs.getInt("totale") == 0){

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /* Restituisce il numero di sensori */
    public int numeroSensori(){


        try {

            Statement stmt = dbConnection.createStatement() ;
            String query = "select count(*) AS totale from Sensore;" ;
            ResultSet rs = stmt.executeQuery(query) ;

            rs.next();

            return rs.getInt("totale"); // ritorna il totale



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


    /* Ritorna la lista in base alla tipolgoia Area*/
    public LinkedList<Area> selectArea(){

        LinkedList<Area> list = new LinkedList<Area>();
        int id = 0;
        int alert = 0;

        try {
            Statement stmt = dbConnection.createStatement() ;
            String query = "select * from area";
            ResultSet rs = stmt.executeQuery(query) ;

            while(rs.next()){

                id = rs.getInt("idArea");
                alert = rs.getInt("alert");

                Area a = new Area(id, alert, numero_dis);      //NumeroSensoriPerOgni Area 10
                list.add(a);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    /* Ritorna la lista in base alla tipolgoia Edificio*/
    public LinkedList<Edificio> selectEdifici(){
        LinkedList<Edificio> list = new LinkedList<Edificio>();
        int id = 0;
        int alert = 0;

        try {
            Statement stmt = dbConnection.createStatement() ;
            String query = "select * from edificio";
            ResultSet rs = stmt.executeQuery(query) ;

            while(rs.next()){
                id = rs.getInt("idEdificio");
                alert = rs.getInt("alert");

                Edificio e = new Edificio(id, alert, numero_dis);
                list.add(e);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /* Ritorna la lista in base alla tipolgoia Distretto*/
    public LinkedList<Distretto> selectDistretti(){
        LinkedList<Distretto> list = new LinkedList<Distretto>();
        int id = 0;
        int alert = 0;

        try {
            Statement stmt = dbConnection.createStatement() ;
            String query = "select * from distretto" ;
            ResultSet rs = stmt.executeQuery(query) ;

            while(rs.next()){
                id = rs.getInt("idDistretto");
                alert = rs.getInt("alert");

                Distretto d = new Distretto(id, alert, numero_dis);
                list.add(d);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    /* Inserimento Edificio*/
    public void insertEdifici(String nome, int idDistretto, int alert){

        String insertTableSQL = "INSERT INTO edificio" + "(nome, idDistretto, alert) VALUES" + "(?,?,?)";
        try{
            PreparedStatement pre = dbConnection.prepareStatement(insertTableSQL);
            pre.setString(1, nome);
            pre.setInt(2, idDistretto);
            pre.setInt(3,alert);
            pre.executeUpdate();
            pre.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Inserimento Distretto */
    public void insertDistretto(String nome, int alert){


        String insertTableSQL = "INSERT INTO distretto" + "(nome, alert) VALUES" + "(?,?)";
        try{
            PreparedStatement pre = dbConnection.prepareStatement(insertTableSQL);
            pre.setString(1, nome);
            pre.setInt(2, alert);
            pre.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* inserimento Area */
    public void insertArea(String nome, int Edificio, int alert){


        String insertTableSQL = "INSERT INTO area" + "(nome, alert, idEdificio) VALUES" + "(?,?,?)";
        try{
            PreparedStatement pre = dbConnection.prepareStatement(insertTableSQL);
            pre.setString(1, nome);
            pre.setInt(2,alert);
            pre.setInt(3, Edificio);
            pre.executeUpdate();
            pre.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*Inserimento gestore */
    public void insertGestore(int id,String username, String pass, String tipo, int admin){

        String insertTableSQL = "INSERT INTO utente" + "(idUtente, username, password, tipologia, admin) VALUES" + "(?,?,?,?,?)";
        try{
            PreparedStatement pre = dbConnection.prepareStatement(insertTableSQL);
            pre.setInt(1, id);
            pre.setString(2, username);
            pre.setString(3, pass);
            pre.setString(4, tipo);
            pre.setInt(5, admin);
            pre.executeUpdate();
            pre.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /* Inserimento sensore DB e primo segnale*/
    public void insertSensore(String tipo, int massimale, int frequenzaInvio, int area, int gestore){

        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        String insertTableSQL = "INSERT INTO sensore" + "(tipo, massimale, frequenzaInvio, ultimoInvio, area, gestore) VALUES" + "(?,?,?,?,?,?)";
        try{

            PreparedStatement pre = dbConnection.prepareStatement(insertTableSQL);
            pre.setString(1, tipo);
            pre.setInt(2, massimale);
            pre.setInt(3, frequenzaInvio);
            pre.setTimestamp(4, date);
            pre.setInt(5, area);
            pre.setInt(6, gestore);

            pre.executeUpdate();
            pre.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /* InserimentoSegnali */
    public void insertSegnali(int idSensore, float variabile_ambientale, int alert){
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        String insertTableSQL = "INSERT INTO segnale" + "(sensore, timestamp, variabileAmbientale, alert) VALUES" + "(?,?,?,?)";

        try{
            PreparedStatement pre = dbConnection.prepareStatement(insertTableSQL);
            pre.setInt(1, idSensore);
            pre.setTimestamp(2, date);
            pre.setFloat(3, variabile_ambientale);
            pre.setInt(4, alert);
            pre.executeUpdate();
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* restituisce la lista dei sensori */
    public LinkedList<Sensore> selectSensori(){

        LinkedList<Sensore> list = new LinkedList<Sensore>();
        int id = 0;
        String tipo = "";
        float massimale = 0;
        int frequenza = 0;

        try {
            Statement stmt = dbConnection.createStatement() ;
            String query = "select * from sensore";
            ResultSet rs = stmt.executeQuery(query) ;

            while(rs.next()){
                id = rs.getInt("idSensore");
                tipo = rs.getString("tipo");
                massimale = rs.getFloat("massimale");
                frequenza = rs.getInt("frequenzaInvio");
                Date data = rs.getDate("ultimoInvio");

                Sensore s = new Sensore(id,0, tipo, massimale, frequenza, data);
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    /* Ritorna lista Segnali */
    public LinkedList<Segnale> selectSegnali(){
        LinkedList<Segnale> list = new LinkedList<Segnale>();
        int idSegnale = 0;
        int idSensore =0;
        Date date;
        float value = 0;
        int alert = 0;

        try {
            Statement stmt = dbConnection.createStatement() ;
            String query = "select * from segnale";
            ResultSet rs = stmt.executeQuery(query) ;

            while(rs.next()){
                idSegnale = rs.getInt("idSegnale");
                idSensore = rs.getInt("sensore");
                date = rs.getDate("timestamp");
                value = rs.getFloat("variabileAmbientale");
                alert = rs.getInt("alert");

                Segnale s = new Segnale(idSegnale,idSensore,date,value,alert);

                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /* update segnale per tipologia */
    public void updateSegnale(int idSegnale, float valore_ambientale){
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        String approveReview = "UPDATE `segnale` SET `variabileAmbientale` = ?, `timestamp` = ? WHERE `idSegnale` = ?";
        // UPDATE Sensore SET massimale = 35 WHERE idSensore = 1
        try{
            PreparedStatement pre = dbConnection.prepareStatement(approveReview);
            pre.setFloat(1, valore_ambientale);
            pre.setTimestamp(2, date);
            pre.setInt(3, idSegnale);
            pre.executeUpdate();
            pre.close();
        }   catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Cambio alert Segnale */
    public void updateAlertSegnale(int sensore, int alert){
        String approveReview = "UPDATE `segnale` SET `alert` = ? WHERE `idSensore` = ?";
        // UPDATE Sensore SET massimale = 35 WHERE idSensore = 1
        try{
            PreparedStatement pre = dbConnection.prepareStatement(approveReview);
            pre.setInt(1, alert);
            pre.setInt(2, sensore);
            pre.executeUpdate();
            pre.close();
        }   catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Cambio alert Area */
    public void updateAlertArea(int idArea, int alert){
        String approveReview = "UPDATE `area` SET `alert` = ? WHERE `idArea` = ?";
        // UPDATE Sensore SET massimale = 35 WHERE idSensore = 1
        try{
            PreparedStatement pre = dbConnection.prepareStatement(approveReview);
            pre.setInt(1, alert);
            pre.setInt(2, idArea);
            pre.executeUpdate();

        }   catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /* Chisuura connessione */
    public void closeConnection(){
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Aggiornamento alert Distretto */
    public void updateAlertDistretto(int idDistretto, int alert){
        String approveReview = "UPDATE `distretto` SET `alert` = ? WHERE `idDistretto` = ?";
        // UPDATE Sensore SET massimale = 35 WHERE idSensore = 1
        try{
            PreparedStatement pre = dbConnection.prepareStatement(approveReview);
            pre.setInt(1, alert);
            pre.setInt(2, idDistretto);
            pre.executeUpdate();
            pre.close();
        }   catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /* Aggiornamento alert Edificio */
    public void updateAlertEdificio(int idEdificio, int alert){
        String approveReview = "UPDATE `edificio` SET `alert` = ? WHERE `idEdificio` = ?";
        // UPDATE Sensore SET massimale = 35 WHERE idSensore = 1
        try{
            PreparedStatement pre = dbConnection.prepareStatement(approveReview);
            pre.setInt(1, alert);
            pre.setInt(2, idEdificio);
            pre.executeUpdate();
            pre.close();
        }   catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /*  Per il Client */
    /* login */
    public Gestore loginGestore(String username, String pass){
        String query = "select * FROM utente WHERE username = ? AND password = ?" ;
        Gestore gestore = null;

        try{

            PreparedStatement pre = dbConnection.prepareStatement(query);
            pre.setString(1, username);
            pre.setString(2, pass);
            ResultSet rs = pre.executeQuery();


            while(rs.next()){
                gestore = new Gestore(rs.getString("username"), rs.getString("password"), rs.getInt("idUtente"), rs.getString("tipologia") );
            }

        }   catch (SQLException e) {
            e.printStackTrace();
        }

        return gestore;
    }

}
