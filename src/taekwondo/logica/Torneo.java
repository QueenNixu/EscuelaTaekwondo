package taekwondo.logica;

import java.sql.Date;

public class Torneo {
	
	private int id;
	private String nombre;
	private Date fecha;
	private int participantes;
	private int idGanadorOro;
	private int idGanadorPlata;
	private int idGanadorBronce3;
	private int idGanadorBronce4;
	
	public Torneo() {
		
	}
	
	public Torneo(int id, String nombre, Date fecha, int participantes, int idGanadorOro,
			int idGanadorPlata, int idGanadorBronce3, int idGanadorBronce4) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.participantes = participantes;
		this.idGanadorOro = idGanadorOro;
		this.idGanadorPlata = idGanadorPlata;
		this.idGanadorBronce3 = idGanadorBronce3;
		this.idGanadorBronce4 = idGanadorBronce4;
	}
	
	public Torneo(String nombre, java.sql.Date fecha2) {
		super();
		this.nombre = nombre;
		this.fecha = fecha2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public java.sql.Date getFecha() {
		return (java.sql.Date) fecha;
	}

	public void setFecha(java.sql.Date fecha) {
		this.fecha = fecha;
	}

	public int getParticipantes() {
		return participantes;
	}

	public void setParticipantes(int participantes) {
		this.participantes = participantes;
	}

	public int getIdGanadorOro() {
		return idGanadorOro;
	}

	public void setIdGanadorOro(int idGanadorOro) {
		this.idGanadorOro = idGanadorOro;
	}

	public int getIdGanadorPlata() {
		return idGanadorPlata;
	}

	public void setIdGanadorPlata(int idGanadorPlata) {
		this.idGanadorPlata = idGanadorPlata;
	}

	public int getIdGanadorBronce3() {
		return idGanadorBronce3;
	}

	public void setIdGanadorBronce3(int idGanadorBronce3) {
		this.idGanadorBronce3 = idGanadorBronce3;
	}

	public int getIdGanadorBronce4() {
		return idGanadorBronce4;
	}

	public void setIdGanadorBronce4(int idGanadorBronce4) {
		this.idGanadorBronce4 = idGanadorBronce4;
	}
	
	
	
	

}
