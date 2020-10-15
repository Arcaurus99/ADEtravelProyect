/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.regex;
import control.Agencia;
import javax.json.JsonValue;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.bson.Document;

/**
 * REST Web Service
 *
 * @author Esteban
 */
@Path("ws_agencia")
public class Ws_agencia {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Ws_agencia
     */
    public Ws_agencia() {
    }
    
    /**
     * Retrieves representation of an instance of vista.Ws_agencia
     * @return an instance of java.lang.String
     */
    
    Agencia agenADEtravel = new Agencia();
    
    @GET
    @Path("buscarPrimerPaquete")
    @Produces({"application/json"})
    public String buscarPrimerPaquete(){
        return agenADEtravel.buscarPrimerPaquete();
    }
        
    @GET
    @Path("buscarUltimaFactura")
    @Produces({"application/json"})
    public String buscarUltimaFactura(){
        return agenADEtravel.buscarUltimaFactura();
    }
        
    @GET
    @Path("buscarUltimasFacturas")
    @Produces({"application/json"})
    public String buscarUltimasCincoFacturas(){
        return agenADEtravel.buscarUltimasCincoFacturas();
    }
    
    @GET
    @Path("actualizarClientes/id/{id}/address/{address}/movil/{movil}")
    @Produces({"application/json"})
    public String actuailizarCliente(@PathParam("id") String id,
            @PathParam("address") String address,@PathParam("movil") String movil){
        String comprobacion = "[" + agenADEtravel.buscarPrimerCliente() + ", ";
        agenADEtravel.actualizarCliente(id, address, movil);
        comprobacion = comprobacion.concat(agenADEtravel.buscarPrimerCliente() + "]");
        return comprobacion;
    }
    
}
