package taekwondo.logica;

import java.sql.Date;

public class Torneo {
	
	private String nombre;
	private Date fechaInicio;
	private Date fechaFin;
	private int participantes;
	private int idGanadorOro;
	private int idGanadorPlata;
	private int idGanadorBronce3;
	private int idGanadorBronce4;
	
	public Torneo() {
		
	}
	
	public Torneo(String nombre, Date fechaInicio, Date fechaFin, int participantes, int idGanadorOro,
			int idGanadorPlata, int idGanadorBronce3, int idGanadorBronce4) {
		super();
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.participantes = participantes;
		this.idGanadorOro = idGanadorOro;
		this.idGanadorPlata = idGanadorPlata;
		this.idGanadorBronce3 = idGanadorBronce3;
		this.idGanadorBronce4 = idGanadorBronce4;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
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
