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
			
			Torneo tor = traerTorneoById(idTor);
			if(idTae == tor.getIdGanadorOro()) {
				tor.setIdGanadorOro(0);
				editarTorneo(tor);
			} else {
				if(idTae == tor.getIdGanadorPlata()) {
					tor.setIdGanadorPlata(0);
					editarTorneo(tor);
				} else {
					if(idTae == tor.getIdGanadorBronce3()) {
						tor.setIdGanadorBronce3(0);
						editarTorneo(tor);
					} else {
						if(idTae == tor.getIdGanadorBronce4()) {
							tor.setIdGanadorBronce4(0);
							editarTorneo(tor);
						}
					}
				}
			}
			
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
	    StringBuilder sqlBuilder = new StringBuilder("UPDATE torneo SET nombre=?, fecha=?");
	    
	    // Lista para almacenar los valores que se establecerán en el PreparedStatement
	    List<Object> parametros = new ArrayList<>();
	    parametros.add(torneoEditado.getNombre());
	    parametros.add(torneoEditado.getFecha());

	    // Verificar y agregar los IDs si son diferentes de 0, de lo contrario, agregar NULL
	    if (torneoEditado.getIdGanadorOro() != 0) {
	        sqlBuilder.append(", idGanadorOro=?");
	        parametros.add(torneoEditado.getIdGanadorOro());
	    } else {
	        sqlBuilder.append(", idGanadorOro=NULL");
	    }
	    if (torneoEditado.getIdGanadorPlata() != 0) {
	        sqlBuilder.append(", idGanadorPlata=?");
	        parametros.add(torneoEditado.getIdGanadorPlata());
	    } else {
	        sqlBuilder.append(", idGanadorPlata=NULL");
	    }
	    if (torneoEditado.getIdGanadorBronce3() != 0) {
	        sqlBuilder.append(", idGanadorBronce3=?");
	        parametros.add(torneoEditado.getIdGanadorBronce3());
	    } else {
	        sqlBuilder.append(", idGanadorBronce3=NULL");
	    }
	    if (torneoEditado.getIdGanadorBronce4() != 0) {
	        sqlBuilder.append(", idGanadorBronce4=?");
	        parametros.add(torneoEditado.getIdGanadorBronce4());
	    } else {
	        sqlBuilder.append(", idGanadorBronce4=NULL");
	    }
	    
	    // Agregar la condición WHERE
	    sqlBuilder.append(" WHERE id=?");

	    Connection con = ConexionMySQL.obtenerConexion();

	    try (PreparedStatement statement = con.prepareStatement(sqlBuilder.toString())) {
	        // Establecer los parámetros en el PreparedStatement
	        for (int i = 0; i < parametros.size(); i++) {
	            statement.setObject(i + 1, parametros.get(i));
	        }
	        statement.setInt(parametros.size() + 1, torneoEditado.getId());

	        // Ejecutar la actualización
	        statement.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace(); // Manejar la excepción apropiadamente en tu aplicación
	        return false;
	    }
	}

	public static List<Integer> traerTorneosDelTaekwondokaByIdTae(int id) {
		
		String query = "SELECT idTorneo FROM torneo_taekwondoka WHERE idTaekwondoka =?";
		
		Connection conexion = ConexionMySQL.obtenerConexion();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        pstmt = conexion.prepareStatement(query);
	        pstmt.setInt(1, id); // Establecer el valor del parámetro
	        rs = pstmt.executeQuery();
	        
	        List<Integer> idTorneos = new ArrayList<>();

	        while (rs.next()) {
	        	
	        	idTorneos.add(rs.getInt("idTorneo"));
	        	
	        }
	        
	        return idTorneos;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar la excepción de alguna manera adecuada
	    }
		
		return null;
	}




	

}
