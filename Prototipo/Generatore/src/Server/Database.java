package Server;

import Mappa.Area;
import Mappa.Distretto;
import Mappa.Edificio;
import Mappa.Sensore;


import java.sql.*;
import java.util.LinkedList;



public class Database {
    int numero_dis = 5; // NumeroDistretti


    /* Controlla se ci sono sensori*/
    public boolean emptySensore(){

        Connection dbConnection = ConnectionDB.Connect();

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
            String query = "select * from zona WHERE tipologia = 'area' OR tipologia = 'Area';" ;
            ResultSet rs = stmt.executeQuery(query) ;

            while(rs.next()){

                id = rs.getInt("idZona");
                alert = rs.getInt("alert");

                Area a = new Area(Integer.toString(id), alert, numero_dis);      //NumeroSensoriPerOgni Area 10
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
        Connection dbConnection = ConnectionDB.Connect();
        int id = 0;
        int alert = 0;

        try {
            Statement stmt = dbConnection.createStatement() ;
            String query = "select * from zona where tipologia = 'Edificio' or tipologia = 'edificio'";
            ResultSet rs = stmt.executeQuery(query) ;

            while(rs.next()){
                id = rs.getInt("idZona");
                alert = rs.getInt("alert");

                Edificio e = new Edificio(Integer.toString(id), alert, numero_dis);
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
        Connection dbConnection = ConnectionDB.Connect();
        int id = 0;
        int alert = 0;

        try {
            Statement stmt = dbConnection.createStatement() ;
            String query = "select * from zona WHERE tipologia = 'Distretto' or tipologia = 'distretto'" ;
            ResultSet rs = stmt.executeQuery(query) ;

            while(rs.next()){
                id = rs.getInt("idZona");
                alert = rs.getInt("alert");

                Distretto d = new Distretto(Integer.toString(id), alert, numero_dis);
                list.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    /* Inserimento sensore DB */
    public void insertSensore(int idSensore, String tipo, int variabileAmbientale, int massimale, int frequenzaInvio, java.util.Date ultimoinvio, int zona, int installatore){
        Connection dbConnection = ConnectionDB.Connect();


        String insertTableSQL = "INSERT INTO sensore" + "(idSensore, tipo, variabileAmbientale, massimale, frequenzaInvio, ultimoInvio, zona, installatore) VALUES" + "(?,?,?,?,?,?,?,?)";
        try{

            PreparedStatement pre = dbConnection.prepareStatement(insertTableSQL);
            pre.setInt(1, idSensore);
            pre.setString(2, tipo);
            pre.setInt(3, variabileAmbientale);
            pre.setInt(4, massimale);
            pre.setInt(5, frequenzaInvio);
            pre.setDate(6, convertJavaDateToSqlDate(ultimoinvio));
            pre.setInt(7, zona);
            pre.setInt(8, installatore);

            pre.executeUpdate();
            pre.close();

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
