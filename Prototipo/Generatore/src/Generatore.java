import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Generatore {

    // Adding the multiple documents into the mongo collection.
    private static void addMultipleDocuments(MongoCollection<Document> col) {

        List<Document> docs = new ArrayList<Document>();
        Random random = new Random();
        int j = 0, n = 0, k = 0;
        int numero_sensori = 150000;
        long startTime = System.currentTimeMillis();

        for(int i = 0; i < numero_sensori; i++){
            // Sample document.
            Document emp = new Document();
            emp.put("id", i);
            emp.put("TipoSensore", "temperatura");

            j = 5;
            n = 30-j;
            k = random.nextInt(n)+j;//Valori compresi tra 5 e 30

            emp.put("Temperatura", k);

            // Adding documents to a list.
            docs.add(emp);
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("");
        System.out.println("Generati " + numero_sensori + " sensori in " + elapsedTime + "ms");
        System.out.println("Inizio caricamento sul DataBase");

        col.insertMany(docs);
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {

        // Mongodb initialization parameters.
        int port_no = 27017;
        String host_name = "127.0.0.1", db_name = "sensori", db_coll_name = "collezione_sensori";                             /* Configurare i parametri */

        // Mongodb connection string.
        String client_url = "mongodb://" + host_name + ":" + port_no + "/" + db_name;
        MongoClientURI uri = new MongoClientURI(client_url);

        // Connecting to the mongodb server using the given client uri.
        MongoClient mongo_client = new MongoClient(uri);

        // Fetching the database from the mongodb.
        MongoDatabase db = mongo_client.getDatabase(db_name);


        // Fetching the collection from the mongodb.
        MongoCollection<Document> coll = db.getCollection(db_coll_name);

        // Controllo se ci sono già dei sensori inseriti.
        if(coll.count() == 0){
            System.out.println("Non ci sono sensori nella collezione.");
            System.out.println("Inserimento dei sensori.");
            // Adding a single document in the mongo collection.
            addMultipleDocuments(coll);
        } else {
            System.out.println("Ci sono già sensori nella collezione.");
            System.out.println("Inizio aggiornamento sensori");

            while(true){

            }
        }



    }
}