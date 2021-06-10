package edu.fpdual.web.appservices.manejadorDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.fpdual.web.appservices.conexionDB.Conector;
import edu.fpdual.web.appservices.objetos.Registro;

public class ManejadorRegistro {

	public boolean actualizarRegistro(Connection con, Registro actualizado) {
		
		if (actualizado.getId() > 0) {
			try (Statement stmt = con.createStatement()) {
				String sql = generarSQLUpdate(con, actualizado);
				stmt.execute(sql); 
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return false;
		}
		return true;
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
	
	

	private String generarSQLUpdate(Connection con, Registro actualizado) {
		String sql = "UPDATE Registro SET ";
		boolean cambioNombreHotel = false;
		boolean cambioLocalizacion = false;
		boolean cambioEstrellas = false;
		boolean cambioidHabitacion = false;
		boolean cambionumPersonas = false;
		boolean cambioFechaEntrada = false;
		boolean cambioFechaSalida = false;
		boolean cambioPrecio = false;
		boolean cambioNombreUsuario = false;
		boolean cambioDni = false;
		boolean cambioTelefono = false;
		boolean cambioEmail = false;
		boolean cambioIdServices = false;

		if (actualizado.getId() > 0) {
			Registro antiguo = getRegistro(con, actualizado.getId());

			if (actualizado.getNombreHotel() != null
					&& !actualizado.getNombreHotel().equals(antiguo.getNombreHotel())) {
				sql += "nombreHotel=?, ";
				cambioNombreHotel = true;
			}
			if (actualizado.getLocalizacion() != null
					&& !actualizado.getLocalizacion().equals(antiguo.getLocalizacion())) {
				sql += "localizacion=?, ";
				cambioLocalizacion = true;
			}
			if (actualizado.getEstrellas() > 0 && actualizado.getEstrellas() != antiguo.getEstrellas()) {
				sql += "Estrellas=?, ";
				cambioEstrellas = true;
			}
			if (actualizado.getId_habitacion() > 0 && actualizado.getId_habitacion() != antiguo.getId_habitacion()) {
				sql += "id_habitacion=?, ";
				cambioidHabitacion = true;
			}
			if (actualizado.getNum_personas() > 0 && actualizado.getNum_personas() != antiguo.getNum_personas()) {
				sql += "num_personas=?, ";
				cambionumPersonas = true;
			}
			if (actualizado.getFecha_Entrada() != null
					&& !actualizado.getFecha_Entrada().equals(antiguo.getFecha_Entrada())) {
				sql += "fecha_entrada=?, ";
				cambioFechaEntrada = true;
			}
			if (actualizado.getFecha_salida() != null
					&& !actualizado.getFecha_salida().equals(antiguo.getFecha_salida())) {
				sql += "fecha_salida=?, ";
				cambioFechaSalida = true;
			}
			if (actualizado.getPrecio() >= 0 && actualizado.getPrecio() != antiguo.getPrecio()) {
				sql += "precio=?, ";
				cambioPrecio = true;
			}
			if (actualizado.getNombre_usuario() != null
					&& !actualizado.getNombre_usuario().equals(antiguo.getNombre_usuario())) {
				sql += "nombre_usuario=?, ";
				cambioNombreUsuario = true;
			}
			if (actualizado.getDni() != null && !actualizado.getDni().equals(antiguo.getDni())) {
				sql += "dni=?, ";
				cambioDni = true;
			}
			if (actualizado.getTelefono() != null && !actualizado.getTelefono().equals(antiguo.getTelefono())) {
				sql += "telefono=?, ";
				cambioTelefono = true;
			}
			if (actualizado.getEmail() != null && !actualizado.getEmail().equals(antiguo.getEmail())) {
				sql += "email=?, ";
				cambioEmail = true;
			}
			if (actualizado.getId_services() != null
					&& !actualizado.getId_services().equals(antiguo.getId_services())) {
				sql += "id_services=?, ";
				cambioIdServices = true;
			}
			sql = sql.substring(0, sql.length() - 2);
			sql += " WHERE id=?";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {

				int contador = 1;
				if (cambioNombreHotel) {
					stmt.setString(contador, actualizado.getNombreHotel());
					contador++;
				}
				if (cambioLocalizacion) {
					stmt.setString(contador, actualizado.getLocalizacion());
					contador++;
				}
				if (cambioEstrellas) {
					stmt.setInt(contador, actualizado.getEstrellas());
					contador++;
				}
				if (cambioidHabitacion) {
					stmt.setInt(contador, actualizado.getId_habitacion());
					contador++;
				}
				if (cambionumPersonas) {
					stmt.setInt(contador, actualizado.getNum_personas());
					contador++;
				}
				if (cambioFechaEntrada) {
					stmt.setString(contador, actualizado.getFecha_Entrada());
					contador++;
				}
				if (cambioFechaSalida) {
					stmt.setString(contador, actualizado.getFecha_salida());
					contador++;
				}
				if (cambioPrecio) {
					stmt.setDouble(contador, actualizado.getPrecio());
					contador++;
				}
				if (cambioNombreUsuario) {
					stmt.setString(contador, actualizado.getNombre_usuario());
					contador++;
				}
				if (cambioDni) {
					stmt.setString(contador, actualizado.getDni());
					contador++;
				}
				if (cambioTelefono) {
					stmt.setString(contador, actualizado.getTelefono());
					contador++;
				}
				if (cambioEmail) {
					stmt.setString(contador, actualizado.getEmail());
					contador++;
				}
				if (cambioIdServices) {
					stmt.setString(contador, actualizado.getId_services());
					contador++;
				}
				stmt.setInt(contador, actualizado.getId());
				String[] consulta = stmt.toString().split(": ");
				return consulta[1];
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
