package Database;


/* Import Mongo */
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import model.Sensore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    /* Connect to the DB */
    public static MongoDatabase dbConnect() {

        MongoClientURI uri = new MongoClientURI("mongodb://127.0.0.1:27017");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase("unnamedb");

        return db;
    }

    /* Check if a collection exists */
    public static boolean collectionExists(MongoDatabase db) {
        boolean collectionExists = db.listCollectionNames()
                .into(new ArrayList<String>()).contains("sensori");

        return collectionExists;
    }

    /* Insert sensor in the DB */
    public static void saveData(MongoDatabase database, ArrayList<Sensore> listaSensore) throws IOException {

        MongoCollection<Document> collection = database.getCollection("sensori");
        List<Document> documents = getSignalList(listaSensore);

        // Write the documents into the DB
        collection.insertMany(documents);

    }

    /* Update the list */
    public static void update(MongoDatabase db, ArrayList<Sensore> newList) throws IOException{
        ArrayList<Sensore> oldSensori = DBManager.getData(DBManager.dbConnect());


        MongoCollection myCollection = db.getCollection("sensori");

        /*
        for (int i = 0; i < oldSensori.size(); i++){

        }*/

        myCollection.drop();
        DBManager.saveData(db, oldSensori);
    }

    /* Get Data */
    public static ArrayList<Sensore> getData(MongoDatabase db){
        MongoCollection<Document> collection = db.getCollection("sensori");

        List<Document> sensori = (List<Document>) collection.find().into(new ArrayList<Document>());

        ArrayList<Sensore> listaSensori = new ArrayList<Sensore>();

        for (Document sensore : sensori){
            Sensore temp = new Sensore(sensore.getInteger("id"), sensore.getInteger("temp"));
            listaSensori.add((temp));
        }
        return listaSensori;
    }

    /* Get the list of signals */
    public static List<Document> getSignalList(ArrayList<Sensore> listaSensori){
        List<Document> documents = new ArrayList<Document>();

        for (int i = 0; i < listaSensori.size(); i++){
            Document Temp = new Document("id", listaSensori.get(i).getId());
            Temp.append("temp", listaSensori.get(i).getTemp());

            documents.add(Temp);
        }

        return documents;
    }


}
