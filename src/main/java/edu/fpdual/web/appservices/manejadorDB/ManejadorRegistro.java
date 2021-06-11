package edu.fpdual.web.appservices.manejadorDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.fpdual.web.appservices.objetos.Registro;

public class ManejadorRegistro {

	public boolean actualizarRegistro(Connection con, Registro actualizado) {
		
		if (actualizado.getId() > 0) {
			String sql = "UPDATE `Registro` SET `nombreHotel`= ?,`localizacion`= ?,`Estrellas`= ?,`id_habitacion`= ?"
					+ ",`num_personas`= ?,`fecha_entrada`= ?,`fecha_salida`= ?,`precio`=? ,`nombre_usuario`= ?,`dni`= ?"
					+ ",`telefono`= ?,`email`= ?,`id_services`=?  WHERE `id`= ?";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, actualizado.getNombreHotel());
				stmt.setString(2,actualizado.getLocalizacion());
				stmt.setInt(3, actualizado.getEstrellas());
				stmt.setInt(4, actualizado.getId_habitacion());
				stmt.setInt(5, actualizado.getNum_personas());
				stmt.setString(6,actualizado.getFecha_Entrada());
				stmt.setString(7, actualizado.getFecha_salida());
				stmt.setDouble(8, actualizado.getPrecio());
				stmt.setString(9,actualizado.getNombre_usuario());
				stmt.setString(10, actualizado.getDni());
				stmt.setString(11, actualizado.getTelefono());
				stmt.setString(12, actualizado.getEmail());
				stmt.setString(13, actualizado.getId_services());
				stmt.setInt(14, actualizado.getId());
				stmt.executeLargeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return false;
		}
		return false;
	}

	public Registro getRegistro(Connection con, int id) {
		
		String sql = "SELECT * FROM Registro where id=?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				return new Registro(result, true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public void deleteRegistro(Connection con, int id) {
		if(getRegistro(con,id)!=null) {
			String sql = "DELETE FROM Registro WHERE id=?";
			try(PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setInt(1, id);
				stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
