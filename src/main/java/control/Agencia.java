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
//ARNULFITO GAY
/**
 *
 * @author Esteban
 */
public class Agencia {
    
    public String buscarPaquete(){
        
        //----CONEXIÓN SEVIDOR----
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017"); 
        //----CONEXIÓN A BD----
        MongoDatabase database = mongoClient.getDatabase("ADEtravel");
        //----GENERAR COLECCIÓN----
        MongoCollection<Document> collection = database.getCollection("paquetes");
        
        //AGREGAR EXCEPCIONES DE ELEMENTO VACÍO
        
        return collection.find().first().toJson();
    }
    
}
