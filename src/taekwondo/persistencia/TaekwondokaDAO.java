package taekwondo.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import taekwondo.logica.Taekwondoka;
import taekwondo.logica.Torneo;
import taekwondo.util.Ventanas;

public class TaekwondokaDAO {
	
	//Saves the athlete
	public boolean insertarTaekwondoka(Taekwondoka taekwondoka) {
	    String query = "INSERT INTO taekwondoka (nombre, apellido, edad, direccion, email, celular, cinturon, punta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection con = ConexionMySQL.obtenerConexion();
	         PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	        // Configurar los parámetros y ejecutar la consulta
	        pstmt.setString(1, taekwondoka.getNombre());
	        pstmt.setString(2, taekwondoka.getApellido());
	        pstmt.setString(3, taekwondoka.getEdad());
	        pstmt.setString(4, taekwondoka.getDireccion());
	        pstmt.setString(5, taekwondoka.getEmail());
	        pstmt.setString(6, taekwondoka.getCelular());
	        pstmt.setString(7, taekwondoka.getCinturon());
	        pstmt.setString(8, taekwondoka.getPunta());

	        pstmt.executeUpdate();
	        Ventanas.mostrarExito("Se ha agregado un nuevo Taekwondista.");
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
	        return false;
	    }
	}


	//get athletes
	public List<Taekwondoka> traerTaekwondokas() {
	    List<Taekwondoka> taekwondokas = new ArrayList<>();
	    String query = "SELECT * FROM taekwondoka";

	    Connection conexion = ConexionMySQL.obtenerConexion();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        pstmt = conexion.prepareStatement(query);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Taekwondoka taekwondoka = new Taekwondoka();
	            taekwondoka.setId(rs.getInt("id"));
	            taekwondoka.setNombre(rs.getString("nombre"));
	            taekwondoka.setApellido(rs.getString("apellido"));
	            taekwondoka.setEdad(rs.getInt("edad") + ""); // +"" para convertirlo en un String sencillamente
	            taekwondoka.setDireccion(rs.getString("direccion"));
	            taekwondoka.setEmail(rs.getString("email"));
	            taekwondoka.setCelular(rs.getString("celular")); // +"" para convertirlo en un String sencillamente
	            taekwondoka.setCinturon(rs.getString("cinturon"));
	            taekwondoka.setPunta(rs.getString("punta"));

	            taekwondokas.add(taekwondoka);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar la excepción de alguna manera adecuada
	    }

	    return taekwondokas;
	}


	//get athlete by mail
	public Taekwondoka traerTaekwondokaByMail(String mail) {
	    Taekwondoka tae = null;
	    String query = "SELECT * FROM taekwondoka WHERE email = ?";
	    
	    Connection conexion = ConexionMySQL.obtenerConexion();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        pstmt = conexion.prepareStatement(query);
	        pstmt.setString(1, mail); // Establecer el valor del parámetro
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            tae = new Taekwondoka();
	            tae.setId(rs.getInt("id"));
	            tae.setNombre(rs.getString("nombre"));
	            tae.setApellido(rs.getString("apellido"));
	            tae.setEdad(rs.getInt("edad") + ""); // +"" para convertirlo en un String sencillamente
	            tae.setDireccion(rs.getString("direccion"));
	            tae.setEmail(rs.getString("email"));
	            tae.setCelular(rs.getString("celular")); // +"" para convertirlo en un String sencillamente
	            tae.setCinturon(rs.getString("cinturon"));
	            tae.setPunta(rs.getString("punta"));
	        }
	        
	        return tae;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar la excepción de alguna manera adecuada
	    }
		return null;
	}


	//validates uniqueness of an email
	public boolean isMailUnique(String mail) {
		
		String query = "SELECT * FROM taekwondoka WHERE email = ?";
		
		Connection conexion = ConexionMySQL.obtenerConexion();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        pstmt = conexion.prepareStatement(query);
	        pstmt.setString(1, mail); // Establecer el valor del parámetro
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            return false;
	        }
	        
	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar la excepción de alguna manera adecuada
	    }
	    
		return false;
	}


	//edit athlete
	public boolean editarTaekwondoka(Taekwondoka taeEditado) {
        // Query para actualizar el registro en la base de datos
        String sql = "UPDATE taekwondoka SET nombre=?, apellido=?, edad=?, direccion=?, email=?, celular=?, cinturon=?, punta=? WHERE id=?";
        
        Connection con = ConexionMySQL.obtenerConexion();

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            // Establecer los parámetros en la consulta
            statement.setString(1, taeEditado.getNombre());
            statement.setString(2, taeEditado.getApellido());
            statement.setString(3, taeEditado.getEdad());
            statement.setString(4, taeEditado.getDireccion());
            statement.setString(5, taeEditado.getEmail());
            statement.setString(6, taeEditado.getCelular());
            statement.setString(7, taeEditado.getCinturon());
            statement.setString(8, taeEditado.getPunta());
            statement.setInt(9, taeEditado.getId());

            // Ejecutar la actualización
            int filasActualizadas = statement.executeUpdate();

            // Comprobar si se actualizaron filas
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar la excepción apropiadamente en tu aplicación
            return false;
        }
    }

	//validates uniqueness of an email
	public boolean isMailUnique(String mail, int id) {
		
		String query = "SELECT * FROM taekwondoka WHERE email = ? AND id !=?";
		
		Connection conexion = ConexionMySQL.obtenerConexion();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        pstmt = conexion.prepareStatement(query);
	        pstmt.setString(1, mail); // Establecer el valor del parámetro
	        pstmt.setInt(2, id); // Establecer el valor del parámetro
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	        	
	            return false;
	        }
	        
	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar la excepción de alguna manera adecuada
	    }
	    
		return false;
	}


	//delete athlete
	public static boolean eliminarTaekwondoka(int id) {
		
		// obtener torneos del taekwondoka
		// eliminarlo de esos torneos
		// eliminar relacion
		// eliminar taekwondoka
		
		List<Integer> listaTorneosTae = TorneoDAO.traerTorneosDelTaekwondokaByIdTae(id);
		
		for(int idTor : listaTorneosTae) {
			TorneoDAO.eliminarTaeTor(idTor, id);
		}
		
		String sql = "DELETE FROM taekwondoka WHERE id =?";
		
		Connection con = ConexionMySQL.obtenerConexion();
		
		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setInt(1, id);
			statement.executeUpdate();
			
			return true;
			
		}  catch (SQLException e) {
            e.printStackTrace(); // Manejar la excepción apropiadamente en tu aplicación
            return false;
        }
	}


	//get athlete by id
	public static Taekwondoka traerTaekwondokaById(int id) {
		
		String query = "SELECT * FROM taekwondoka WHERE id =?";
		
		Connection conexion = ConexionMySQL.obtenerConexion();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        pstmt = conexion.prepareStatement(query);
	        pstmt.setInt(1, id); // Establecer el valor del parámetro
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	        	
	        	Taekwondoka tae = new Taekwondoka();
	            tae.setId(rs.getInt("id"));
	            tae.setNombre(rs.getString("nombre"));
	            tae.setApellido(rs.getString("apellido"));
	            tae.setEdad(rs.getInt("edad") + ""); // +"" para convertirlo en un String sencillamente
	            tae.setDireccion(rs.getString("direccion"));
	            tae.setEmail(rs.getString("email"));
	            tae.setCelular(rs.getString("celular")); // +"" para convertirlo en un String sencillamente
	            tae.setCinturon(rs.getString("cinturon"));
	            tae.setPunta(rs.getString("punta"));
	        	
	            return tae;
	        }
	        
	        return null;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar la excepción de alguna manera adecuada
	    }
		
		return null;
	}


	//get signed athletes
	public static List<Taekwondoka> traerInscriptos(int idTor) {
		
		List<Taekwondoka> inscriptos = new ArrayList<>();
	    String query = "SELECT * FROM torneo_taekwondoka WHERE idTorneo=?";

	    Connection conexion = ConexionMySQL.obtenerConexion();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        pstmt = conexion.prepareStatement(query);
	        pstmt.setInt(1, idTor);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	        	inscriptos.add(traerTaekwondokaById(rs.getInt("idTaekwondoka")));
	        }
	        
	        return inscriptos;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar la excepción de alguna manera adecuada
	    }

	    return inscriptos;
	}


	//sign in an athlete for a tournement
	public static boolean inscribitTaeTor(int idTae, int idTor) {
		
		String query = "INSERT INTO torneo_taekwondoka (idTorneo, idTaekwondoka) VALUES (?, ?)";

	    try (Connection con = ConexionMySQL.obtenerConexion();
	         PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	        // Configurar los parámetros y ejecutar la consulta
	        pstmt.setInt(1, idTor);
	        pstmt.setInt(2, idTae);

	        pstmt.executeUpdate();
	        
	        TorneoDAO.incrementarParticipantes(idTor);
	        
	        Ventanas.mostrarExito("Se ha inscripto a "+traerTaekwondokaById(idTae).getNombre()+" al torneo "+TorneoDAO.traerTorneoById(idTor).getNombre());
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
	        return false;
	    }
		
	}



}