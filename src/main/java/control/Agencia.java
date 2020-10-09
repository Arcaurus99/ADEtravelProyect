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
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;


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
    
    public String actualizarCliente(String id, String address, String movil){
        
        MongoCollection<Document> collection = database.getCollection("cliente");
        collection.updateOne(eq("_id", new ObjectId(id)), combine(set("address", address), set("movil", movil)));
        return "(\"Confirmation\": 1)";
        
    }
    
        
}
