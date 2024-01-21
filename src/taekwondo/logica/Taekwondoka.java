package taekwondo.logica;

public class Taekwondoka {
	
	private int id;
	private String nombre;
	private String apellido;
	private String edad;
	private String direccion;
	private String email;
	private String celular;
	private String cinturon;
	private String punta;
	
	public Taekwondoka() {
		
	}
	
	

	public Taekwondoka(int id, String nombre, String apellido, String edad, String direccion, String email, String celular,
			String cinturon, String punta) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.direccion = direccion;
		this.email = email;
		this.celular = celular;
		this.cinturon = cinturon;
		this.punta = punta;
	}



	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getCinturon() {
		return cinturon;
	}

	public void setCinturon(String cinturon) {
		this.cinturon = cinturon;
	}

	public String getPunta() {
		return punta;
	}

	public void setPunta(String punta) {
		this.punta = punta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}
	
	

}
