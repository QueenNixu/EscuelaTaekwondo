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
	
	public void insertarTaekwondoka(Taekwondoka taekwondoka) {
	    String query = "INSERT INTO taekwondoka (nombre, apellido, edad, direccion, email, celular, cinturon, punta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement pstmt = ConexionMySQL.obtenerConexion().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
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
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar la excepción de alguna manera adecuada
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
	            taekwondoka.setCelular(rs.getInt("celular") + ""); // +"" para convertirlo en un String sencillamente
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



}