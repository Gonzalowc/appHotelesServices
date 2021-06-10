package edu.fpdual.web.appservices.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.fpdual.web.appservices.conexionDB.Conector;
import edu.fpdual.web.appservices.manejadorDB.ManejadorRegistro;
import edu.fpdual.web.appservices.objetos.Registro;

@Path("/actions")
public class NotificationController {
	
	@GET
	@Path("/ping")
	public Response ping() {
		return Response.ok().entity("Service online").build();
	}
	
	@GET
	@Path("/registro/modificar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateRegistro(Registro registro) {
		try(Connection con = new Conector().getMySQLConnection()){
			new ManejadorRegistro().actualizarRegistro(con, registro);
			return Response.ok().entity("Update Successfull").build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).entity("Update not Successfull").build();
		}
	}
	
	@GET
	@Path("/registro/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRegistro(@PathParam("id") int id) {
		try(Connection con = new Conector().getMySQLConnection()){
			new ManejadorRegistro().deleteRegistro(con, id);
			return Response.ok().entity("Delete Sucessfull").build();
		}catch(SQLException e){
			e.printStackTrace();
			return Response.status(500).entity("Delete not Sucessfull").build();
		}
	}

}

