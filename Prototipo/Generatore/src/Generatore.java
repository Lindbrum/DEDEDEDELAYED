import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import com.mongodb.*;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

public class Generatore {
    static int numero_sensori_temp = 3; // Numero sensori per area per temperatura
    static int numero_sensori_lum = 3;
    static int numero_sensori_umid = 3;
    static int numero_distretti = 11;
    static int numero_edifici = 55;
    static int numero_aree = 20;


    // Adding the multiple documents into the mongo collection.
    private static void addMultipleDocuments(MongoCollection<Document> col) {

        List<Document> docs = new ArrayList<Document>();
        Random random = new Random();



        for(int i = 0; i < numero_distretti; i++){

            for(int  j = 0; j < numero_edifici; j++){

                for(int k = 0; k < numero_aree; k++){

                    for(int t = 0;  t < numero_sensori_temp; t++){
                        // Sample document.
                        Document emp = new Document();
                        Document emp_id = new Document();
                        emp_id.put("id_distretto", i);
                        emp_id.put("id_edificio", j);
                        emp_id.put("id_area", k);
                        emp_id.put("id_sensore", t);
                        emp.put("id", emp_id);

                        emp.put("TipoSensore", "Temperatura");

                        int res = random.nextInt(30);//Valori compresi tra0

                        emp.put("Valore", res);

                        // Adding documents to a list.
                        docs.add(emp);

                    }

                    /*
                    for(int t = 0;  t < numero_sensori_lum; t++){
                        // Sample document.
                        Document emp = new Document();
                        Document emp_id = new Document();
                        emp_id.put("id_distretto", i);
                        emp_id.put("id_edificio", j);
                        emp_id.put("id_area", k);
                        emp_id.put("id_sensore", t);
                        emp.put("id", emp_id);

                        emp.put("TipoSensore", "Luminosità");

                        int res = random.nextInt(30);//Valori compresi tra0

                        emp.put("Valore", res);

                        // Adding documents to a list.
                        docs.add(emp);
                    }

                    for(int t = 0;  t < numero_sensori_umid; t++){
                        // Sample document.
                        Document emp = new Document();
                        Document emp_id = new Document();
                        emp_id.put("id_distretto", i);
                        emp_id.put("id_edificio", j);
                        emp_id.put("id_area", k);
                        emp_id.put("id_sensore", t);
                        emp.put("id", emp_id);

                        emp.put("TipoSensore", "Umidità");

                        int res = random.nextInt(30);//Valori compresi tra0

                        emp.put("Valore", res);

                        // Adding documents to a list.
                        docs.add(emp);

                    } */
                }

            }
        }


        System.out.println("Inizio caricamento sul DataBase");

        col.insertMany(docs);
    }

    //Update Documents
    private static void updateMultipleDocuments(MongoCollection<Document> col) {

        Random random = new Random();

        for (int i = 0; i < numero_distretti; i++) {

            for (int j = 0; j < numero_edifici; j++) {

                for (int k = 0; k < numero_aree; k++) {

                    for (int t = 0; t < numero_sensori_temp; t++) {

                        /*
                        DBObject document1 = new BasicDBObject("id", "abc");
                        DBObject document2 = new BasicDBObject("id", "xyz");
                        BasicDBList or = new BasicDBList();

                        or.add(document1);
                        or.add(document2);

                        DBObject query = new BasicDBObject("$or", or);*/

                        // Filters.and(Filters.eq("id.id_distretto", 0), Filters.eq("id.id_edificio",0), Filters.eq("id.id_area",0), Filters.eq("id.id_sensore", 0))

                        /* Trovare un metodo per velocizzare l'update - troppo lento */
                        int res = random.nextInt(30);
                        col.updateOne(Filters.and(Filters.eq("id.id_distretto", i), Filters.eq("id.id_edificio",j), Filters.eq("id.id_area",k), Filters.eq("id.id_sensore", t)) , new Document("$set", new Document("Valore", res)));

                        System.out.println("Distretto: " + i + " Edificio: " + j + " Area: " + k + " Sensori: " + t);


                    }
                }
            }

        }
    }


    @SuppressWarnings("resource")
    public static void main(String[] args) {
        try {

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
                //Timer
                long startTime = System.currentTimeMillis();

                System.out.println("Non ci sono sensori nella collezione.");
                System.out.println("Inserimento dei sensori.");
                // Adding a single document in the mongo collection.
                addMultipleDocuments(coll);

                //timer
                long stopTime = System.currentTimeMillis();
                long elapsedTime = stopTime - startTime;
                System.out.println("");
                System.out.println("Generati " + coll.count() + " sensori in " + elapsedTime + "ms");

            } else {
                System.out.println("Ci sono già sensori nella collezione.");
                System.out.println("Inizio aggiornamento sensori");


                while(true){
                    long startTime = System.currentTimeMillis();

                    updateMultipleDocuments(coll);

                    long stopTime = System.currentTimeMillis();
                    long elapsedTime = stopTime - startTime;
                    System.out.println("");
                    System.out.println("Aggiornati " + coll.count() + " sensori in " + elapsedTime + "ms");
                }
            }
        } catch (MongoException e) {
            e.printStackTrace();
        }



    }
}