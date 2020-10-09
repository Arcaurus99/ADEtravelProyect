/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.Agencia;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

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
    @Path("consultaPaquetes")
    @Produces({"application/json"})
    public String consultaPaquetes(){
        
        return agenADEtravel.buscarPaquete();
        
    }
    
    @GET
    @Path("actualizarClientes/id, address, movil/{id},{address},{movil}")
    @Produces({"application/json"})
    public String actuailizarCliente(@PathParam("id, address, movil") String id, String address, String movil){
        
        return agenADEtravel.actualizarCliente(id, address, movil);
        
    }
    
}
