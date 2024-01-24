package taekwondo.igu;

import javax.swing.JFrame;

import taekwondo.logica.Taekwondoka;
import taekwondo.persistencia.ConexionMySQL;
import taekwondo.persistencia.TaekwondokaDAO;
import taekwondo.util.FiltrosParaTextField;
import taekwondo.util.PintarPanel;
import taekwondo.util.VentanaGenerica;
import taekwondo.util.Ventanas;
import taekwondo.logica.TaekwondokaController;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;


public class VerDetallesTaekwondoka extends JFrame {
	
	private VentanaGenerica ventanaOrigen;
	private Taekwondoka tae;
	private Taekwondoka nuevoTae;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfEdad;
	private JTextField tfDireccion;
	private JTextField tfEmail;
	private JTextField tfCelular;
	private JButton btnGuardar;
	private JButton btnCancelar;
	
	private JButton btnEditar;
	private JButton btnArrowUp;
	
	private JPanel pnlCinturonPunta;
	private JPanel panel_1;
	
	private TaekwondokaController controller = new TaekwondokaController();
	
	private String nuevoColorPunta;
	private String nuevoColorCinturon;
	
	public VerDetallesTaekwondoka(VentanaGenerica ventanaOrigen, Taekwondoka tae) {

		this.ventanaOrigen = ventanaOrigen;
		this.tae = tae;
		
		nuevoColorCinturon = tae.getCinturon();
		nuevoColorPunta = tae.getPunta();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 416);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 595, 377);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDetallesDe = new JLabel("Detalles de ");
		lblDetallesDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetallesDe.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblDetallesDe.setBounds(160, 5, 274, 29);
		panel.add(lblDetallesDe);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAtrasActionListener();
			}
		});
		btnAtras.setFont(new Font("Arial", Font.PLAIN, 13));
		btnAtras.setBounds(7, 5, 89, 23);
		panel.add(btnAtras);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 76, 575, 177);
		panel.add(panel_1);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGuardarActionListener();
			}
		});
		btnGuardar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnGuardar.setBounds(110, 127, 170, 35);
		btnGuardar.setVisible(false);
		panel_1.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelarActionListener();
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCancelar.setBounds(290, 127, 170, 35);
		btnCancelar.setVisible(false);
		panel_1.add(btnCancelar);
		
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					btnEditarActionListener();
			}
		});
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnEditar.setBounds(200, 127, 170, 35);
		panel_1.add(btnEditar);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNombre.setBounds(10, 10, 77, 16);
		panel_1.add(lblNombre);
		
		tfNombre = new JTextField(tae.getNombre());
		tfNombre.setEditable(false);
		tfNombre.setColumns(10);
		tfNombre.setBounds(97, 9, 170, 20);
		panel_1.add(tfNombre);
		
		tfApellido = new JTextField(tae.getApellido());
		tfApellido.setEditable(false);
		tfApellido.setColumns(10);
		tfApellido.setBounds(97, 37, 170, 20);
		panel_1.add(tfApellido);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Arial", Font.PLAIN, 15));
		lblApellido.setBounds(10, 38, 77, 16);
		panel_1.add(lblApellido);
		
		tfEdad = new JTextField(tae.getEdad());
		tfEdad.setEditable(false);
		tfEdad.setColumns(10);
		tfEdad.setBounds(97, 68, 170, 20);
		panel_1.add(tfEdad);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Arial", Font.PLAIN, 15));
		lblEdad.setBounds(10, 69, 77, 16);
		panel_1.add(lblEdad);
		
		JLabel lblCinturonPunta = new JLabel("Cinturon/Punta:");
		lblCinturonPunta.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCinturonPunta.setBounds(30, 100, 126, 16);
		panel_1.add(lblCinturonPunta);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setFont(new Font("Arial", Font.PLAIN, 15));
		lblDireccion.setBounds(308, 11, 77, 16);
		panel_1.add(lblDireccion);
		
		tfDireccion = new JTextField(tae.getDireccion());
		tfDireccion.setEditable(false);
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(395, 10, 170, 20);
		panel_1.add(tfDireccion);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		lblEmail.setBounds(308, 42, 77, 16);
		panel_1.add(lblEmail);
		
		tfEmail = new JTextField(tae.getEmail());
		tfEmail.setEditable(false);
		tfEmail.setColumns(10);
		tfEmail.setBounds(395, 41, 170, 20);
		panel_1.add(tfEmail);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setToolTipText("asd");
		lblCelular.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCelular.setBounds(308, 73, 77, 16);
		panel_1.add(lblCelular);
		
		tfCelular = new JTextField(tae.getCelular());
		tfCelular.setEditable(false);
		tfCelular.setColumns(10);
		tfCelular.setBounds(395, 72, 170, 20);
		panel_1.add(tfCelular);
		
		pnlCinturonPunta = PintarPanel.crearColorPanel(tae.getCinturon(), tae.getPunta(), 285, 95);
		pnlCinturonPunta.setBounds(150, 100, 380, 16);
		panel_1.add(pnlCinturonPunta);
		
		
		btnArrowUp = new JButton("");
		btnArrowUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLevelUpActionListener();
			}
		});
		btnArrowUp.setToolTipText("Aumentar el rango de este Taekwondoka (una sola vez por edicion)");
		btnArrowUp.setIcon(new ImageIcon("D:\\eclipse workspace 01\\escuelataekwondo\\images\\arrow up 1.png"));
		btnArrowUp.setEnabled(false);
		btnArrowUp.setBounds(530, 100, 16, 16);
		panel_1.add(btnArrowUp);
		
	    
		JLabel lblApellidoNombre = new JLabel(tae.getApellido()+" "+tae.getNombre());
		lblApellidoNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellidoNombre.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblApellidoNombre.setBounds(7, 36, 578, 29);
		panel.add(lblApellidoNombre);
		
		FiltrosParaTextField.setupTextFieldDocumentFilter(tfNombre);
		FiltrosParaTextField.setupTextFieldDocumentFilter(tfApellido);
		FiltrosParaTextField.setupTextFieldDocumentFilter(tfEdad);
		FiltrosParaTextField.setupTextFieldDocumentFilterForNumbers(tfCelular);
		FiltrosParaTextField.setupTextFieldDocumentFilterForNumbers(tfEdad);
		FiltrosParaTextField.setupTextFieldDocumentFilterForEmail(tfEmail);
	}

	private void btnLevelUpActionListener() {

	        levelUp(tae.getCinturon(), tae.getPunta());
	        
	        // Elimina el antiguo pnlCinturonPunta del panel_1
	        panel_1.remove(pnlCinturonPunta);

	        // Crea un nuevo pnlCinturonPunta con los nuevos colores y posición
	        pnlCinturonPunta = PintarPanel.crearColorPanel(nuevoColorCinturon, nuevoColorPunta, 285, 95);
	        pnlCinturonPunta.setBounds(150, 100, 380, 16);

	        // Añade el nuevo pnlCinturonPunta al panel_1
	        panel_1.add(pnlCinturonPunta);

	        // Revalida y repinta el panel para que los cambios sean visibles
	        panel_1.revalidate();
	        panel_1.repaint();
	        
	}

	private void levelUp(String colorCinturon, String colorPunta) {
		
		switch (colorCinturon.toLowerCase()) {
	    case "blanco":
	        // Código para el caso "Rojo"
	    	switch (colorPunta.toLowerCase()) {
		    case "blanco":
		        // Código para el caso "Rojo"
		        System.out.println("Cinturon blanco");

				nuevoColorCinturon = "blanco";
				nuevoColorPunta = "amarillo";
		        break;
		    case "amarillo":
		        // Código para el caso "Azul"
		        System.out.println("Cinturon blanco punta amarilla");

				nuevoColorCinturon = "amarillo";
				nuevoColorPunta = "amarillo";
		        break;
		    default:
		        // Código para el caso por defecto si no coincide con ningún caso anterior
		        System.out.println("Hubo un problema al reconocer los colores del cinturon");
		        break;
	    	}
	        
	        break;
	    case "amarillo":
	        // Código para el caso "Azul"
	    	switch (colorPunta.toLowerCase()) {
		    case "amarillo":
		        // Código para el caso "Rojo"
		        System.out.println("Cinturon amarillo");

				nuevoColorCinturon = "amarillo";
				nuevoColorPunta = "verde";
		        break;
		    case "verde":
		        // Código para el caso "Azul"
		        System.out.println("Cinturon amarillo punta verde");

				nuevoColorCinturon = "verde";
				nuevoColorPunta = "verde";
		        break;
		    default:
		        // Código para el caso por defecto si no coincide con ningún caso anterior
		        System.out.println("Color de la punta no reconocido");
		        break;
	    	}
	    	
	        break;
	    case "verde":
	        // Código para el caso "Verde"
	    	switch (colorPunta.toLowerCase()) {
		    case "verde":
		        // Código para el caso "Rojo"
		        System.out.println("Cinturon verde");

				nuevoColorCinturon = "verde";
				nuevoColorPunta = "azul";
		        break;
		    case "azul":
		        // Código para el caso "Azul"
		        System.out.println("Cinturon verde punta azul");

				nuevoColorCinturon = "azul";
				nuevoColorPunta = "azul";
		        break;
		    default:
		        // Código para el caso por defecto si no coincide con ningún caso anterior
		        System.out.println("Color de la punta no reconocido");
		        break;
	    	}

	        break;
	    case "azul":
	        // Código para el caso "Verde"
	    	switch (colorPunta.toLowerCase()) {
		    case "azul":
		        // Código para el caso "Rojo"
		        System.out.println("Cinturon azul");

				nuevoColorCinturon = "azul";
				nuevoColorPunta = "rojo";
		        break;
		    case "rojo":
		        // Código para el caso "Azul"
		        System.out.println("Cinturon azul punta roja");
		        
				nuevoColorCinturon = "rojo";
				nuevoColorPunta = "rojo";
		        break;
		    default:
		        // Código para el caso por defecto si no coincide con ningún caso anterior
		        System.out.println("Color de la punta no reconocido");
		        break;
	    	}

	        break;
	    case "rojo":
	        // Código para el caso "Verde"
	    	switch (colorPunta.toLowerCase()) {
		    case "rojo":
		        // Código para el caso "Rojo"
		        System.out.println("Cinturon rojo");

				nuevoColorCinturon = "rojo";
				nuevoColorPunta = "negro";
		        break;
		    case "negro":
		        // Código para el caso "Azul"
		        System.out.println("Cinturon rojo punta negra");
				nuevoColorCinturon = "negro";
				nuevoColorPunta = "negro";
		        break;
		    default:
		        // Código para el caso por defecto si no coincide con ningún caso anterior
		        System.out.println("Color de la punta no reconocido");
		        break;
	    	}

	        break;
	    case "negro":
	        // Código para el caso "Verde"
	    	System.out.println("Cinturon negro.\nProximamente se agregaran los Dan.");
	        break;
	    default:
	        // Código para el caso por defecto si no coincide con ningún caso anterior
	        System.out.println("Color de la punta no reconocido");
	        break;
	}
		//codigo despues de levelup
		System.out.println("UWU");
	}

	private void btnGuardarActionListener() {
		
		if(ConexionMySQL.obtenerConexion() != null) {
			
			nuevoTae = new Taekwondoka();
        	
        	// Obtén los datos de la interfaz gráfica y crea un objeto Taekwondoka
			nuevoTae.setId(tae.getId());
        	nuevoTae.setNombre(tfNombre.getText());
			nuevoTae.setApellido(tfApellido.getText());
			nuevoTae.setEdad(tfEdad.getText());
			nuevoTae.setDireccion(tfDireccion.getText());
			nuevoTae.setEmail(tfEmail.getText());
			nuevoTae.setCelular(tfCelular.getText());
			nuevoTae.setCinturon(nuevoColorCinturon);
			nuevoTae.setPunta(nuevoColorPunta);
			
			
        	
        	if(controller.editarTaekwondoka(nuevoTae, tae.getEmail())) {
        		Ventanas.mostrarExito("Exito");
        		
        		tae = nuevoTae;
        		
        		tfNombre.setText(tae.getNombre());
        		tfApellido.setText(tae.getApellido());
        		tfEdad.setText(tae.getEdad());
        		tfDireccion.setText(tae.getDireccion());
        		tfEmail.setText(tae.getEmail());
        		tfCelular.setText(tae.getCelular());
        		
        		tfNombre.setEditable(false);
        		tfApellido.setEditable(false);
        		tfEdad.setEditable(false);
        		tfDireccion.setEditable(false);
        		tfEmail.setEditable(false);
        		tfCelular.setEditable(false);
        		
        		btnArrowUp.setEnabled(false);
        		
        		btnEditar.setVisible(true);
        		btnCancelar.setVisible(false);
        		btnGuardar.setVisible(false);
        		
        	}
		} else {
			Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
		}
		
	}
	
	private void btnEditarActionListener() {
		
		
		tfNombre.setEditable(true);
		tfApellido.setEditable(true);
		tfEdad.setEditable(true);
		tfDireccion.setEditable(true);
		tfEmail.setEditable(true);
		tfCelular.setEditable(true);
		
		btnArrowUp.setEnabled(true);
		
		btnEditar.setVisible(false);
		btnGuardar.setVisible(true);
		btnCancelar.setVisible(true);
	}
	
	private void btnCancelarActionListener() {
		
		tfNombre.setText(tae.getNombre());
		tfApellido.setText(tae.getApellido());
		tfEdad.setText(tae.getEdad());
		tfDireccion.setText(tae.getDireccion());
		tfEmail.setText(tae.getEmail());
		tfCelular.setText(tae.getCelular());
		
		System.out.println(tae.getCinturon().toString());
		System.out.println(tae.getPunta().toString());

		panel_1.remove(pnlCinturonPunta);

        // Crea un nuevo pnlCinturonPunta con los nuevos colores y posición
        pnlCinturonPunta = PintarPanel.crearColorPanel(tae.getCinturon(), tae.getPunta(), 285, 95);
        pnlCinturonPunta.setBounds(150, 100, 380, 16);

        // Añade el nuevo pnlCinturonPunta al panel_1
        panel_1.add(pnlCinturonPunta);

        // Revalida y repinta el panel para que los cambios sean visibles
        //panel_1.revalidate();
        //panel_1.repaint();
        
		tfNombre.setEditable(false);
		tfApellido.setEditable(false);
		tfEdad.setEditable(false);
		tfDireccion.setEditable(false);
		tfEmail.setEditable(false);
		tfCelular.setEditable(false);
		
		btnArrowUp.setEnabled(false);
		
		btnEditar.setText("Editar");
		
		btnEditar.setVisible(true);
		btnCancelar.setVisible(false);
		btnGuardar.setVisible(false);
		
	}

	private void btnAtrasActionListener() {
		dispose();
		if (ventanaOrigen instanceof VerTaekwondokas) {
			((VerTaekwondokas) ventanaOrigen).setLocation(this.getX(), this.getY());
			((VerTaekwondokas) ventanaOrigen).setVisible(true);
            ((VerTaekwondokas) ventanaOrigen).cargarTabla();
        } else if (ventanaOrigen instanceof InscribirTaekwondokaTorneo) {
        	((InscribirTaekwondokaTorneo) ventanaOrigen).setLocation(this.getX(), this.getY());
			((InscribirTaekwondokaTorneo) ventanaOrigen).setVisible(true);
        	((InscribirTaekwondokaTorneo) ventanaOrigen).cargarTabla();
        }
	}
}
