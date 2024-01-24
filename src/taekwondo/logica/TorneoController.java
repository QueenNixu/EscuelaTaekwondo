package taekwondo.logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import taekwondo.persistencia.ConexionMySQL;
import taekwondo.persistencia.TorneoDAO;
import taekwondo.util.Ventanas;

public class TorneoController {
	
	private TorneoDAO torneoDAO = new TorneoDAO();

	public boolean guardarNuevoTorneo(Torneo torneo) {
		if(torneo.getNombre().isEmpty()) {
			Ventanas.mostrarError("Ingrese un nombre.");
			return false;
		}
		return TorneoDAO.guardarNuevoTorneo(torneo);
	}

	public List<Torneo> traerTorneos() {
		return torneoDAO.traerTorneos();
	}

	public Torneo traerTorneoById(int id) {
		
		String query = "SELECT * FROM torneo WHERE id =?";
		
		Connection conexion = ConexionMySQL.obtenerConexion();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        pstmt = conexion.prepareStatement(query);
	        pstmt.setInt(1, id); // Establecer el valor del parámetro
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	        	
	        	Torneo tor = new Torneo();
	        	tor.setId(rs.getInt("id"));
	        	tor.setNombre(rs.getString("nombre"));
	        	tor.setFecha(rs.getDate("fecha"));
	        	tor.setParticipantes(rs.getInt("participantes"));
	        	tor.setIdGanadorOro(rs.getInt("idGanadorOro"));
	        	tor.setIdGanadorPlata(rs.getInt("idGanadorPlata"));
	        	tor.setIdGanadorBronce3(rs.getInt("idGanadorBronce3"));
	        	tor.setIdGanadorBronce4(rs.getInt("idGanadorBronce4"));
	        	
	            return tor;
	        }
	        
	        return null;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar la excepción de alguna manera adecuada
	    }
	    
		return null;
	}

	public boolean eliminarTorneo(int id) {
		return TorneoDAO.eliminarTorneo(id);
	}

	public boolean eliminarTaeTor(int idTor, int idTae) {
		
		return TorneoDAO.eliminarTaeTor(idTor, idTae);
	}

}
