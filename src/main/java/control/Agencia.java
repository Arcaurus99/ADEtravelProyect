package control;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.model.Sorts;
import static com.mongodb.client.model.Updates.combine;
import org.bson.conversions.Bson;


/**
 *
 * @author Esteban
 */
public class Agencia {
    
    MongoClientURI uri = new MongoClientURI("mongodb://userLab4:passworduserLab4@93.188.167.110:27017/?authSource=lab4");
    //MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase database = mongoClient.getDatabase("lab4");
    
    String respuesta = null;
    
    public String buscarPrimerPaquete(){
        MongoCollection<Document> collection = database.getCollection("paquetes");
        try {
            respuesta = collection.find().first().toJson();
        } catch (Exception e){
            respuesta = "{\"ADVERTENCIA\":\" La colección esta vacía\"}";
        }
        return respuesta;
    }
    
    public String buscarUltimaFactura(){
        MongoCollection<Document> collection = database.getCollection("ventas");
        try {
            respuesta = collection.find().sort(Sorts.descending("numFac")).first().toJson();
        } catch (Exception e) {
            respuesta = "{\"ADVERTENCIA\":\" La colección esta vacía\"}";
        }
        return respuesta;
    }
    
    public String buscarUltimasCincoFacturas(/*int h*/){
        MongoCollection<Document> collection = database.getCollection("ventas");
        int i = 0; 
        try{
            FindIterable<Document> cursor = collection.find().sort(new BasicDBObject("numFac",-1)).limit(5);
            MongoCursor<Document> iterator = cursor.iterator();
            while(iterator.hasNext()){
                if (i == 0){
                    respuesta = "{\"Consulta\":[{\"Documento\":";
                }
                respuesta += iterator.next().toJson();
                if (i <= 3){
                    respuesta += ", \n \"Documento\":";
                }else if (i == 4){
                    respuesta += "}]}";
                }
                i++;
            } 
        }catch(Exception e){
            respuesta = "{\"ADVERTENCIA\":\" La colección esta vacía. \"}";
        }
        return respuesta;
    }
    
    public String eliminarUnDocumento(String id){
        MongoCollection<Document> collection = database.getCollection("clientes");
        try {
            collection.deleteOne(eq("_id", new ObjectId(id)));
            respuesta ="{\"Confirmation\":\" El documento indicado se ha eliminado exitosamente \"}";
        } catch(IllegalArgumentException e){
            respuesta = "{\"ADVERTENCIA\":\" El id del documento indicado no existe, intente nuevamente ingresar el id.\"}";
        }
        return respuesta; 
    }
    
    /**
    public String actualizarCliente(String id, String nuevosDatos){
        MongoCollection<Document> collection = database.getCollection("clientes");
        BasicDBObject datos = new BasicDBObject();
        try{
            try{
                try{
                    /*collection.updateOne(eq("_id", new ObjectId(id)), combine(set("address", "\"address\""), set("movil", "\"movil\"")));
                    datos = datos.parse(nuevosDatos);
                    collection.updateOne(eq("_id", new ObjectId(id)), combine(set(datos)));
                    /**BasicDBObject document = new BasicDBObject();            
                    document.put("$set", collection.find(eq("_id", new ObjectId(id))).first().toJson());
                    respuesta += "{" + document.toJson() + ", ";
                    document.replace("$set", nuevosDatos);*//**
                    respuesta += "{\"Confirmation\":\" El documento indicado se ha actualizado exitosamente \"}, " + nuevosDatos + "}";
                } catch (RuntimeException e){ //UnsupportedOperationException //ServletException
                    respuesta = "{\"Confirmation\":\" El id del documento indicado no existe, o los datos y variables "
                            + "ingresados son incorrectos, intente ingresarlos nuevamente\"}" + e.toString() + datos;
                }
            } catch (UnsupportedOperationException e){ //UnsupportedOperationException //ServletException
            respuesta = "{\"Confirmation\":\" El id del documento indicado no existe, o los datos y variables "
                    + "ingresados son incorrectos, intente ingresarlos nuevamente\"}" + e.toString() + datos;
        }
        } catch (IllegalArgumentException e){ //UnsupportedOperationException //ServletException
            respuesta = "{\"Confirmation\":\" El id del documento indicado no existe, o los datos y variables "
                    + "ingresados son incorrectos, intente ingresarlos nuevamente\"}" + e.toString() + datos;
        }
        return respuesta;
    }
    
    public String buscarCliente(String id){
        MongoCollection<Document> collection = database.getCollection("clientes");
        try{
            respuesta = collection.find(eq("_id", new ObjectId(id))).first().toJson();
        }catch(IllegalArgumentException e){
            respuesta = "{\"ADVERTENCIA\":\" El id del documento indicado no existe, intente nuevamente ingresar el id.\"}";      
        }
        return respuesta;
    }

    private Bson[] set(String nuevosDatos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Bson[] set(BasicDBObject datos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
}
