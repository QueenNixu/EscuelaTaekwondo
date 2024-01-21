package taekwondo.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import taekwondo.logica.Taekwondoka;
import taekwondo.util.Ventanas;

public class TaekwondokaDAO {
	
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


	public boolean editarTaekwondoka(Taekwondoka taeEditado) {
        // Query para actualizar el registro en la base de datos
        String sql = "UPDATE taekwondoka SET nombre=?, apellido=?, edad=?, direccion=?, email=?, celular=? WHERE id=?";
        
        Connection con = ConexionMySQL.obtenerConexion();

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            // Establecer los parámetros en la consulta
            statement.setString(1, taeEditado.getNombre());
            statement.setString(2, taeEditado.getApellido());
            statement.setString(3, taeEditado.getEdad());
            statement.setString(4, taeEditado.getDireccion());
            statement.setString(5, taeEditado.getEmail());
            statement.setString(6, taeEditado.getCelular());
            statement.setInt(7, taeEditado.getId()); // Asumiendo que hay un campo id en tu tabla

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