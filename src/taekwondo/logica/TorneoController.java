package taekwondo.logica;

import java.util.Date;
import java.util.List;

import taekwondo.persistencia.TorneoDAO;
import taekwondo.util.Ventanas;

public class TorneoController {
	
	private TorneoDAO torneoDAO = new TorneoDAO();

	public boolean guardarNuevoTorneo(Torneo torneo) {
		if(torneo.getNombre().isEmpty()) {
			Ventanas.mostrarError("Ingrese un nombre.");
			return false;
		}
		return TorneoDAO.guardarNuevoTorneo(torneo);
	}

	public List<Torneo> traerTorneos() {
		return torneoDAO.traerTorneos();
	}

}
