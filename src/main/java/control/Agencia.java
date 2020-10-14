/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
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
import com.mongodb.client.model.Sorts;
import static com.mongodb.client.model.Updates.*;


/**
 *
 * @author Esteban
 */
public class Agencia {
    
    MongoClient mongoClient = MongoClients.create("mongodb://userLab4:passworduserLab4@93.188.167.110:27017/?authSource=lab4");
    //MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    MongoDatabase database = mongoClient.getDatabase("lab4");

    
    public String buscarPrimerPaquete(){
        MongoCollection<Document> collection = database.getCollection("paquetes");
        return collection.find().first().toJson();
    }
    
    public String buscarUltimaFactura(){
        MongoCollection<Document> collection = database.getCollection("ventas");
        return collection.find().sort(Sorts.descending("codigo")).first().toJson();
    }
    
    public String buscarUltimasCincoFacturas(){
        MongoCollection<Document> collection = database.getCollection("ventas");
        FindIterable<Document> cursor = collection.find().sort(new BasicDBObject("numFac",-1));
        MongoCursor<Document> iterator = cursor.iterator();
        List<BasicDBObject> lista = new ArrayList<>();
        int i = 0;
        while(i <= 5) {
            lista.add(new BasicDBObject("", iterator.next().toJson()));
            i++;
        }
        return lista.iterator().next().toJson();
    }
    
    public String actualizarCliente(String id, String address, String movil){
        MongoCollection<Document> collection = database.getCollection("clientes");
        collection.updateOne(eq("_id", new ObjectId(id)), combine(set("address", address), set("movil", movil)));
        return "(\"Confirmation\": 1)";
    }
    
    public String buscarPrimerCliente(){
        MongoCollection<Document> collection = database.getCollection("clientes");
        return collection.find().first().toJson();
    }
    
}
