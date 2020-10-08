/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author Esteban
 */
public class Agencia {
    

    //----CONEXICÓN REMOTA Y LOCAL----
    MongoClient mongoClient = MongoClients.create("mongodb://userLab4:passworduserLab4@93.188.167.110:27017/?authSource=lab4");
    //MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    //----CONEXIÓN A BD----
    MongoDatabase database = mongoClient.getDatabase("lab4");
    
    public String buscarPaquete(){
      
        //----GENERAR COLECCIÓN----
        MongoCollection<Document> collection = database.getCollection("paquetes");
        
        //AGREGAR EXCEPCIONES DE ELEMENTO VACÍO
        return collection.find().first().toJson();
    }
        
}
