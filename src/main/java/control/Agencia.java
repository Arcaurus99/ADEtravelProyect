/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.MongoClient;
//import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.model.Sorts;
import static com.mongodb.client.model.Updates.*;


/**
 *
 * @author Esteban
 */
public class Agencia {
    
    MongoClientURI uri = new MongoClientURI("mongodb://userLab4:passworduserLab4@93.188.167.110:27017/?authSource=lab4");
    //MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase database = mongoClient.getDatabase("lab4");
    
    public String buscarPrimerPaquete(){
        MongoCollection<Document> collection = database.getCollection("paquetes");
        return collection.find().first().toJson();
    }
    
    public String buscarUltimaFactura(){
        MongoCollection<Document> collection = database.getCollection("ventas");
        MongoCollection<Document> respuesta = null;
        if (collection != null){
            respuesta = collection;
        } else {
            
        }
        return respuesta.find().sort(Sorts.descending("numFac")).first().toJson();
    }
    
    public String buscarUltimasCincoFacturas(){
        MongoCollection<Document> collection;
        String listaString = null;
        try{
            collection = database.getCollection("ventas");
            if (collection != null){
                FindIterable<Document> cursor = collection.find().sort(new BasicDBObject("numFac",-1));
                MongoCursor<Document> iterator = cursor.iterator();
                //JsonArray lista = Json.createArrayBuilder().build();
                //JsonObject 
                listaString = "[";
                int i = 0;
                while(i <= 5 && iterator.hasNext()){
                    String obj;
                    obj = iterator.next().toJson();
                    //lista.add(obj);
                    listaString.concat(iterator.next().toJson());
                    if (i <=4){
                        listaString.concat(",");    
                    /*String obj = iterator.next().toJson();
                    lista.add(obj);
                    System.out.println("Documento: " + iterator.next().toJson());*/
                    }
                    i++;
                }
            }
        }catch(Exception e){
           System.out.println("Esta colección esta vacía" + e); 
        }
        listaString.concat("]");
        return listaString;
    }
    
    public String eliminarUnDocumento( String id){
        MongoCollection<Document> coleccion = database.getCollection("clientes");
        coleccion.deleteOne(eq("_id", new ObjectId(id)));
        //return "(\"Confirmation\": 1)";
        return "Fue eliminado"; 
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
    
}
