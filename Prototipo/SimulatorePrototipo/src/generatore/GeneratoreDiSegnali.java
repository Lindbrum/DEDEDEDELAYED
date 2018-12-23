package generatore;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import model.Sensore;
import Database.DBManager;

public class GeneratoreDiSegnali {
    private static Socket socket;

    public static void main(String[] args) throws IOException {

        File tmp = new File("temp.tmp");

        if (!tmp.exists()){
            System.out.println("Non ci sono sensori, Inizio generazione sensori!");
            List<Integer> sensorCount = createSensor(DBManager.dbConnect(), 5);

            saveDataToList(sensorCount);

        } else System.out.println("I sensori sono stati creati, inizio generazione");


        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {

                List<Integer> paramList = null;
                try {
                    paramList = getDataFromList();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {

                    // Start generating signals
                    generateSignals(paramList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 36000);


    }


    public static List<Integer> getDataFromList() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream("temp.tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Integer> sensorParams = (List<Integer>) ois.readObject();
        ois.close();

        return sensorParams;
    }

    public static List<Integer> createSensor(MongoDatabase db, int n) throws IOException {

        MongoCollection<Document> collection = db.getCollection("sensori");
        collection.drop();

        ArrayList<Sensore> sensorList = new ArrayList<Sensore>();

        List<Integer> sensorCount = new ArrayList<Integer>();

        // Variabili

        // Measure execution time
        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= n; i++) {
            int randomSensori = ThreadLocalRandom.current().nextInt(100, 200);
            sensorCount.add(randomSensori);

            for (int sensori = 1; sensori <= randomSensori; sensori++) {
                n++;

                Sensore obj = new Sensore(n, 1);


                sensorList.add(obj);
            }
        }
            // Record the execution time and display it on screen
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("");
        System.out.println("Generated " + sensorList.size() + " sensori in " + elapsedTime + "ms");


        return sensorCount;
    }

    public static void sendDataToServer(ArrayList<Sensore> sensoreList) throws IOException{
        // Hostname and port configuration
        String host = "localhost";
        int port = 25001;

        InetAddress address = InetAddress.getByName(host);
        socket = new Socket(address, port);

        // Open the stream
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        long startTime = System.currentTimeMillis();
        System.out.println("");
        System.out.println("Sending sensor trough TCP...");

        // Measure execution time
        startTime = System.currentTimeMillis();

        // Write the entire robot list thought sockets
        oos.writeObject(sensoreList);
        oos.close();

        // Record the execution time and display it on screen
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("TCP transfer time: " + elapsedTime + "ms");
    }


    // Save the parameters to a tmp file
    public static void saveDataToList(List<Integer> sensorParams) throws IOException {

        FileOutputStream fos = new FileOutputStream("temp.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(sensorParams);
        oos.close();
    }













    public static void generateSignals(List<Integer> paramList) throws IOException  {

        int n = 0;
        int count = 0;
        ArrayList<Sensore> sensorList = new ArrayList<Sensore>();
        for (int i = 0; i < paramList.size(); i++){
            n++;


            for (int j = 0; j < paramList.get(i); j++){
                count++;
                Sensore Obj = new Sensore(count, 1);

                sensorList.add(Obj);
            }
        }

        System.out.println("");
        System.out.println("Generated " + sensorList.size() * 7 + " signals for " + sensorList.size() + " robots");

        sendDataToServer(sensorList);

    }

}


