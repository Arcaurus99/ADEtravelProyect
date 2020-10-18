package vista;

import control.Agencia;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author Esteban
 */
@Path("ws_agencia")
public class Ws_agencia {

    @Context
    private UriInfo context;

    public Ws_agencia() {
    }
    
    Agencia agenADEtravel = new Agencia();
    
    @GET
    @Path("buscarPrimerPaquete")
    @Produces({"application/json"})
    public String buscarPrimerPaquete() {
        return agenADEtravel.buscarPrimerPaquete();
    }

    @GET
    @Path("buscarUltimaFactura")
    @Produces({"application/json"})
    public String buscarUltimaFactura() {
        return agenADEtravel.buscarUltimaFactura();
    }

    @GET
    @Path("buscarUltimasFacturas")
    @Produces({"application/json"})
    public String buscarUltimasCincoFacturas(/*@PathParam("cantidad") int cantidad*/){
        return agenADEtravel.buscarUltimasCincoFacturas();
    }

    @GET
    @Path("eliminarUnDocumento/id/{id}")
    @Produces({"applicaction/json"})
    public String eliminarUnDocumento(@PathParam("id") String id) {
        return agenADEtravel.eliminarUnDocumento(id);
    }
    /**
    @GET
    @Path("actualizarClientes/id/{id}/nuevosDatos/{nuevosDatos}")
    @Produces({"application/json"})
    public String actuailizarCliente(@PathParam("id") String id, @PathParam("nuevosDatos") String nuevosDatos){
        String comprobacion = "";
        //String comprobacion = "{" + agenADEtravel.buscarCliente(id) + ", ";
        comprobacion += agenADEtravel.actualizarCliente(id, nuevosDatos);
        //comprobacion += (agenADEtravel.buscarCliente(id)) + "}";
        return comprobacion;
    }

    @GET
    @Path("buscarCliente/id/{id}")
    @Produces({"application/json"})
    public String buscarCliente(@PathParam("id") String id,
            @PathParam("address") String address, @PathParam("movil") String movil) {
        return agenADEtravel.buscarCliente(id);
    }
    */
}


