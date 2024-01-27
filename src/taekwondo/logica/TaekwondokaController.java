package taekwondo.logica;

import java.util.List;

import taekwondo.persistencia.TaekwondokaDAO;
import taekwondo.util.Ventanas;

public class TaekwondokaController {
	
	private TaekwondokaDAO taekwondokaDAO = new TaekwondokaDAO();

	//save athlete
    public boolean guardarNuevoTaekwondoka(Taekwondoka nuevoTaekwondoka) {
    	//Validations
        StringBuilder errores = new StringBuilder();

        if (nuevoTaekwondoka.getNombre().trim().isEmpty() || nuevoTaekwondoka.getNombre().matches("\\s+")) {
            errores.append("Ingrese un nombre válido.\n");
        } else {
        	if(nuevoTaekwondoka.getNombre().length() >= 50) {
        		errores.append("Ingrese menos de 50 caracteres para el nombre.\n");
        	}
        }
        
        if (nuevoTaekwondoka.getApellido().trim().isEmpty() || nuevoTaekwondoka.getNombre().matches("\\s+")) {
            errores.append("Ingrese un apellido válido.\n");
        } else {
        	if(nuevoTaekwondoka.getApellido().length() >= 50) {
        		errores.append("Ingrese menos de 50 caracteres para el apellido.\n");
        	}
        }
        
        if (nuevoTaekwondoka.getEdad().isEmpty()) {
            errores.append("Ingrese una edad válida.\n");
        } else {
        	if(nuevoTaekwondoka.getEdad().length() >= 4) {
        		errores.append("Ingrese no mas de 4 caracteres.\n");
        	} else {
        		if(Integer.parseInt(nuevoTaekwondoka.getEdad()) >= 150) {
        			errores.append("Ingrese una edad menor a 150.\n");
        		}
        	}
        }
        
        if (nuevoTaekwondoka.getDireccion().isEmpty()) {
            errores.append("Ingrese una direccion válida.\n");
        } else {
        	if(nuevoTaekwondoka.getDireccion().length() >= 100) {
        		errores.append("Ingrese menos de 100 caracteres para la direccion.\n");
        	}
        }
        
        String emailPattern = "^[a-zA-Z0-9._]+@[a-zA-Z]+(\\.[a-zA-Z]{2,63}){1,2}$";

        if (!nuevoTaekwondoka.getEmail().matches(emailPattern)) {
        	errores.append("Ingrese una dirección de correo electrónico válida.\n");
        } else {
        	if(taekwondokaDAO.isMailUnique(nuevoTaekwondoka.getEmail())) {
        		if(nuevoTaekwondoka.getEmail().length() >= 100) {
            		errores.append("Ingrese menos de 100 caracteres para la dirección de correo electrónico.\n");
            	}
        	} else {
        		errores.append("Esta dirección de correo electrónico ya ha sido registrada.\n");
        	}
        }
        
        if (nuevoTaekwondoka.getCelular().isEmpty()) {
            errores.append("Ingrese un numero de celular válido.\n");
        } else {
        	if(nuevoTaekwondoka.getCelular().length() >= 15) {
        		errores.append("Ingrese menos de 15 caracteres para el celular.\n");
        	}
        }
        
        if(nuevoTaekwondoka.getCinturon() == "Desconocido") {
        	errores.append("Seleccione un color de cinturon.\n");
        }
        
        if(nuevoTaekwondoka.getPunta() == "Desconocido") {
        	errores.append("Seleccione un color de punta.\n");
        }
        
        //Were there errors?
        if (errores.length() > 0) {
            //Error window
            Ventanas.mostrarError("ERROR:\n" + errores.toString());
            return false;
        } else {
            //Calls the DAO to save the athlete
            return taekwondokaDAO.insertarTaekwondoka(nuevoTaekwondoka);
        }
    }

    //get athlete's
    public List<Taekwondoka> traerTaekwondokas() {
		return taekwondokaDAO.traerTaekwondokas();
    }
    
    //get an athlete by mail
    public Taekwondoka traerTaekwondokaByMail(String mail) {
		return taekwondokaDAO.traerTaekwondokaByMail(mail);
    }

    //Edit athlete
	public boolean editarTaekwondoka(Taekwondoka taeEditado, String mail) {
		StringBuilder errores = new StringBuilder();

        if (taeEditado.getNombre().trim().isEmpty() || taeEditado.getNombre().matches("\\s+")) {
            errores.append("Ingrese un nombre válido.\n");
        } else {
        	if(taeEditado.getNombre().length() >= 50) {
        		errores.append("Ingrese menos de 50 caracteres para el nombre.\n");
        	}
        }
        
        if (taeEditado.getApellido().trim().isEmpty() || taeEditado.getNombre().matches("\\s+")) {
            errores.append("Ingrese un apellido válido.\n");
        } else {
        	if(taeEditado.getApellido().length() >= 50) {
        		errores.append("Ingrese menos de 50 caracteres para el apellido.\n");
        	}
        }
        
        if (taeEditado.getEdad().isEmpty()) {
            errores.append("Ingrese una edad válida.\n");
        } else {
        	if(taeEditado.getEdad().length() >= 4) {
        		errores.append("Ingrese no mas de 4 caracteres.\n");
        	} else {
        		if(Integer.parseInt(taeEditado.getEdad()) >= 150) {
        			errores.append("Ingrese una edad menor a 150.\n");
        		}
        	}
        }
        
        if (taeEditado.getDireccion().isEmpty()) {
            errores.append("Ingrese una direccion válida.\n");
        } else {
        	if(taeEditado.getDireccion().length() >= 100) {
        		errores.append("Ingrese menos de 100 caracteres para la direccion.\n");
        	}
        }
        
        String emailPattern = "^[a-zA-Z0-9._]+@[a-zA-Z]+(\\.[a-zA-Z]{2,63}){1,2}$";

        if (!taeEditado.getEmail().matches(emailPattern)) {
        	errores.append("Ingrese una dirección de correo electrónico válida.\n");
        } else {
        	if(taekwondokaDAO.isMailUnique(taeEditado.getEmail(), taeEditado.getId())) {
        		if(taeEditado.getEmail().length() >= 100) {
            		errores.append("Ingrese menos de 100 caracteres para la dirección de correo electrónico.\n");
            	}
        	} else {
        		errores.append("Esta dirección de correo electrónico ya ha sido registrada.\n");
        	}
        }
        
        if (taeEditado.getCelular().isEmpty()) {
            errores.append("Ingrese un numero de celular válido.\n");
        } else {
        	if(taeEditado.getCelular().length() >= 15) {
        		errores.append("Ingrese menos de 15 caracteres para el celular.\n");
        	}
        }
        
        if(taeEditado.getCinturon() == "Desconocido") {
        	errores.append("Seleccione un color de cinturon.\n");
        }
        
        if(taeEditado.getPunta() == "Desconocido") {
        	errores.append("Seleccione un color de punta.\n");
        }


        // Agregar más validaciones según sea necesario

        // Verificar si hubo errores
        if (errores.length() > 0) {
            // Mostrar mensaje de error en una ventana en lugar de imprimirlo en consola
            Ventanas.mostrarError("ERROR:\n" + errores.toString());
            return false;
        } else {
            // Si no hay errores, proceder con la persistencia
            return taekwondokaDAO.editarTaekwondoka(taeEditado);
        }
	}

	//delete athlete
	public boolean eliminarTaekwondoka(int id) {
		
		return TaekwondokaDAO.eliminarTaekwondoka(id);
		
	}

	//get an athlete by id
	public Taekwondoka traerTaekwondokaById(int id) {
		
		return TaekwondokaDAO.traerTaekwondokaById(id);
	}

	//get signed athlete's by tournement id
	public List<Taekwondoka> traerInscriptos(int idTor) {
		
		return TaekwondokaDAO.traerInscriptos(idTor);
	}

	//sign an athlete for a tournement
	public boolean inscribirTaeTor(int idTae, int idTor) {
		
		return TaekwondokaDAO.inscribitTaeTor(idTae, idTor);
		
	}
}