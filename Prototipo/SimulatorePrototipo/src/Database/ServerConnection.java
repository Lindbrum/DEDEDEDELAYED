package Database;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import model.Sensore;

public class ServerConnection {

    private static Socket socket;

    public static void getOOStream() throws IOException{
        // Lista sensori
        ArrayList<Sensore> listaSensori = new ArrayList<Sensore>();

        try {
            // Da capire
            ServerSocket serverSocket = new ServerSocket(25001);
            System.out.println("Server Started to the port: 25001");
            ObjectInputStream ois = null;


            while(true){
                //Reading the message from the client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                ois = new ObjectInputStream(is);


                // Read the object from client
                listaSensori = (ArrayList<Sensore>) ois.readObject();

                // Measure execution time
                long startTime = System.currentTimeMillis();

                File doc = new File("temp.tmp");


                boolean dbEmpty = DBManager.collectionExists(DBManager.dbConnect());


                if(!dbEmpty){

                    DBManager.saveData(DBManager.dbConnect(), listaSensori);
                    long stopTime = System.currentTimeMillis();
                    long elapsedTime = stopTime - startTime;
                    System.out.println("Saved " + listaSensori.size() + " sensor in " + elapsedTime + "ms");

                } else{
                    // Update existing robots signals
                    System.out.println("");
                    System.out.println("Got the list from the client...updating sensor entries in the db...");
                    DBManager.update(DBManager.dbConnect(), listaSensori);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                /* Close Socket */
                socket.close();
            } catch (Exception e) {
            }
        }

    }




    /** Main **/
    public static void main(String[] args) throws IOException {
        getOOStream();
    }
}


