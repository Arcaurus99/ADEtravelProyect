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
import org.bson.Document;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.model.Sorts;
import static com.mongodb.client.model.Updates.*;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.lang.Object;


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
    
    public JsonValue buscarUltimasCincoFacturas(int h){
        MongoCollection<Document> collection = database.getCollection("ventas");
        FindIterable<Document> cursor = collection.find().sort(new BasicDBObject("numFac",-1));
        MongoCursor<Document> iterator = cursor.iterator();
        JsonArray lista = Json.createArrayBuilder().build();
        int i = 0;
        while(i <= 5 && iterator.hasNext()) {
            String obj;
            obj = iterator.next().toJson();
            lista.add(obj);
            System.out.println("Documento: " + iterator.next().toJson());
            i++;
        }
        return lista;
    }
    
    public String actualizarCliente(String id, String address, String movil){
        MongoCollection<Document> collection = database.getCollection("clientes");
        collection.updateOne(eq("_id", new ObjectId(id)), combine(set("address", "\"address\""), set("movil", "\"movil\"")));
        return "(\"Confirmation\": 1)";
    }
    
    public String buscarPrimerCliente(){
        MongoCollection<Document> collection = database.getCollection("clientes");
        return collection.find().first().toJson();
    }
    
    public static void main (String args[]){
        Agencia agenADEtravel = new Agencia();
        agenADEtravel.buscarUltimasCincoFacturas();
    }

    public JsonValue buscarUltimasCincoFacturas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
