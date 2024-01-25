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
	            torneo.setId(rs.getInt("id"));
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

	public static Torneo traerTorneoById(int idTor) {
		
		String query = "SELECT * FROM torneo WHERE id =?";
		
		Connection conexion = ConexionMySQL.obtenerConexion();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        pstmt = conexion.prepareStatement(query);
	        pstmt.setInt(1, idTor); // Establecer el valor del parámetro
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

	public static boolean eliminarTorneo(int id) {
		
		String sql = "DELETE FROM torneo_taekwondoka WHERE idTorneo = ?";
		
		Connection con = ConexionMySQL.obtenerConexion();
		
		try (PreparedStatement statement = con.prepareStatement(sql)) {
            // Establecer los parámetros en la consulta
			statement.setInt(1, id);

            // Ejecutar la actualización
			statement.executeUpdate();
			
			sql = "DELETE FROM torneo WHERE id = ?";
			
			con = ConexionMySQL.obtenerConexion();
			
			try (PreparedStatement statement1 = con.prepareStatement(sql)) {
	            // Establecer los parámetros en la consulta
				statement1.setInt(1, id);

	            // Ejecutar la actualización
				statement1.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace(); // Manejar la excepción apropiadamente en tu aplicación
	            return false;
	        }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar la excepción apropiadamente en tu aplicación
            return false;
        }
		
	}
	
	public static void incrementarParticipantes(int idTor) {
		
		String sql = "UPDATE torneo SET participantes=? WHERE id=?";
		
		Connection con = ConexionMySQL.obtenerConexion();

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            // Establecer los parámetros en la consulta
            statement.setInt(1, TorneoDAO.traerTorneoById(idTor).getParticipantes()+1);
            statement.setInt(2, idTor);

            // Ejecutar la actualización
            int filasActualizadas = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Manejar la excepción apropiadamente en tu aplicación
        }
		
	}

	public static boolean eliminarTaeTor(int idTor, int idTae) {
		
		String sql = "DELETE FROM torneo_taekwondoka WHERE idTorneo =? AND idTaekwondoka =?";
		
		Connection con = ConexionMySQL.obtenerConexion();
		
		try (PreparedStatement statement = con.prepareStatement(sql)) {
            // Establecer los parámetros en la consulta
			statement.setInt(1, idTor);
			statement.setInt(2, idTae);

            // Ejecutar la actualización
			statement.executeUpdate();
			
			decrementarParticipantes(idTor);
			
			return true;
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar la excepción apropiadamente en tu aplicación
            return false;
        }

	}
	
	public static void decrementarParticipantes(int idTor) {
		
		String sql = "UPDATE torneo SET participantes=? WHERE id=?";
		
		Connection con = ConexionMySQL.obtenerConexion();

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            // Establecer los parámetros en la consulta
            statement.setInt(1, TorneoDAO.traerTorneoById(idTor).getParticipantes()-1);
            statement.setInt(2, idTor);

            // Ejecutar la actualización
            int filasActualizadas = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Manejar la excepción apropiadamente en tu aplicación
        }
		
	}

	public static boolean editarTorneo(Torneo torneoEditado) {
		
		String sql = "UPDATE torneo SET nombre=?, fecha=? WHERE id=?";
        
        Connection con = ConexionMySQL.obtenerConexion();

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            // Establecer los parámetros en la consulta
            statement.setString(1, torneoEditado.getNombre());
            statement.setDate(2, torneoEditado.getFecha());
            statement.setInt(3, torneoEditado.getId());

            // Ejecutar la actualización
            int filasActualizadas = statement.executeUpdate();

            // Comprobar si se actualizaron filas
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar la excepción apropiadamente en tu aplicación
            return false;
        }
        
	}
	

}
