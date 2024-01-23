package taekwondo.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import taekwondo.logica.Taekwondoka;
import taekwondo.logica.Torneo;
import taekwondo.util.Ventanas;

public class TorneoDAO {

	public static boolean guardarNuevoTorneo(Torneo torneo) {
		
		String query = "INSERT INTO torneo (nombre, fecha) VALUES (?, ?)";
		
		try (Connection con = ConexionMySQL.obtenerConexion();
		         PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
		        // Configurar los parámetros y ejecutar la consulta
		        pstmt.setString(1, torneo.getNombre());
		        pstmt.setDate(2, torneo.getFecha());

		        pstmt.executeUpdate();
		        
		        return true;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
		        return false;
		    }
		
	}

	public List<Torneo> traerTorneos() {
		
		List<Torneo> torneos = new ArrayList<>();
	    String query = "SELECT * FROM torneo";

	    Connection conexion = ConexionMySQL.obtenerConexion();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        pstmt = conexion.prepareStatement(query);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Torneo torneo = new Torneo();
	            torneo.setNombre(rs.getString("nombre"));
	            torneo.setFecha(rs.getDate("fecha"));
	            torneo.setParticipantes(rs.getInt("participantes")); // +"" para convertirlo en un String sencillamente
	            torneo.setIdGanadorOro(rs.getInt("idGanadorOro"));
	            torneo.setIdGanadorPlata(rs.getInt("idGanadorPlata"));
	            torneo.setIdGanadorBronce3(rs.getInt("idGanadorBronce3"));
	            torneo.setIdGanadorBronce4(rs.getInt("idGanadorBronce4"));

	            torneos.add(torneo);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar la excepción de alguna manera adecuada
	    }

	    return torneos;
	}
	
	

}
