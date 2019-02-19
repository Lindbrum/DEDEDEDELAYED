package Server;

import Mappa.Area;
import Mappa.Distretto;
import Mappa.Edificio;
import Mappa.Sensore;


import java.sql.*;
import java.util.LinkedList;



public class Database {
    int numero_dis = 10; // NumeroDistretti


    /* Controlla se ci sono sensori*/
    public boolean emptySensore(){

        Connection dbConnection = ConnectionDB.Connect();

        try {

            Statement stmt = dbConnection.createStatement() ;
            String query = "select count(*) AS totale from Sensore;" ;
            ResultSet rs = stmt.executeQuery(query) ;

            rs.next();

            if(rs.getInt("totale") == 0){
                dbConnection.close();
                return true;
            }


            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

    /* Restituisce il numero di sensori */
    public int numeroSensori(){

        Connection dbConnection = ConnectionDB.Connect();

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
        Connection dbConnection = ConnectionDB.Connect();
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
            dbConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    /* Ritorna la lista in base alla tipolgoia Edificio*/
    public LinkedList<Edificio> selectEdifici(){
        LinkedList<Edificio> list = new LinkedList<Edificio>();
        Connection dbConnection = ConnectionDB.Connect();
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
            dbConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /* Ritorna la lista in base alla tipolgoia Distretto*/
    public LinkedList<Distretto> selectDistretti(){
        LinkedList<Distretto> list = new LinkedList<Distretto>();
        Connection dbConnection = ConnectionDB.Connect();
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

            dbConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    /* Inserimento Edificio*/
    public void insertEdifici(String nome, int idDistretto, int alert){
        Connection dbConnection = ConnectionDB.Connect();

        String insertTableSQL = "INSERT INTO edificio" + "(nome, idDistretto, alert) VALUES" + "(?,?,?)";
        try{
            PreparedStatement pre = dbConnection.prepareStatement(insertTableSQL);
            pre.setString(1, nome);
            pre.setInt(2, idDistretto);
            pre.setInt(3,alert);
            pre.executeUpdate();
            pre.close();
            dbConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Inserimento Distretto */
    public void insertDistretto(String nome, int alert){
        Connection dbConnection = ConnectionDB.Connect();

        String insertTableSQL = "INSERT INTO distretto" + "(nome, alert) VALUES" + "(?,?)";
        try{
            PreparedStatement pre = dbConnection.prepareStatement(insertTableSQL);
            pre.setString(1, nome);
            pre.setInt(2, alert);
            pre.executeUpdate();

            dbConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* inserimento Area */
    public void insertArea(String nome, int Edificio, int alert){
        Connection dbConnection = ConnectionDB.Connect();

        String insertTableSQL = "INSERT INTO area" + "(nome, alert, idEdificio) VALUES" + "(?,?,?)";
        try{
            PreparedStatement pre = dbConnection.prepareStatement(insertTableSQL);
            pre.setString(1, nome);
            pre.setInt(2,alert);
            pre.setInt(3, Edificio);
            pre.executeUpdate();
            pre.close();

            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*Inserimento gestore */
    public void insertGestore(int id,String username, String pass, String tipo, int admin){
        Connection dbConnection = ConnectionDB.Connect();
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
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /* Inserimento sensore DB e primo segnale*/
    public void insertSensore(String tipo, int massimale, int frequenzaInvio, java.util.Date ultimoinvio,int area, int gestore){
        Connection dbConnection = ConnectionDB.Connect();


        String insertTableSQL = "INSERT INTO sensore" + "(tipo, massimale, frequenzaInvio, ultimoInvio, area, gestore) VALUES" + "(?,?,?,?,?,?)";
        try{

            PreparedStatement pre = dbConnection.prepareStatement(insertTableSQL);
            pre.setString(1, tipo);
            pre.setInt(2, massimale);
            pre.setInt(3, frequenzaInvio);
            pre.setDate(4, convertJavaDateToSqlDate(ultimoinvio));
            pre.setInt(5, area);
            pre.setInt(6, gestore);

            pre.executeUpdate();
            pre.close();
            dbConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    /* Ritorna lista Sensori */
    public LinkedList<Sensore> listaSensori(){
        LinkedList<Sensore> list = new LinkedList<Sensore>();
        Connection dbConnection = ConnectionDB.Connect();

        try {
            Statement stmt = dbConnection.createStatement() ;
            String query = "select * from sensore";
            ResultSet rs = stmt.executeQuery(query) ;

            while(rs.next()){
                //Sensore s = new Sensore(id,0, "Luminosità", valore_ambientale, massimale, 60,time);
                //list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

    /*Metodo per convertire il tipo Date di Java al tipo Date mysql*/
    public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

}
