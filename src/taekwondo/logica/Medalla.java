package taekwondo.logica;

public class Medalla {
	
	private String tipo;
	private int id_torneo;
	private int id_taekwondoka;
	
	public Medalla () {
		
	}
	
	public Medalla( String tipo, int id_torneo, int id_taekwondoka) {
		super();
		this.tipo = tipo;
		this.id_torneo = id_torneo;
		this.id_taekwondoka = id_taekwondoka;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getId_torneo() {
		return id_torneo;
	}

	public void setId_torneo(int id_torneo) {
		this.id_torneo = id_torneo;
	}

	public int getId_taekwondoka() {
		return id_taekwondoka;
	}

	public void setId_taekwondoka(int id_taekwondoka) {
		this.id_taekwondoka = id_taekwondoka;
	}
	
	

}