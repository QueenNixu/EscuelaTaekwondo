package taekwondo.igu;

import javax.swing.JFrame;

import taekwondo.logica.Taekwondoka;

import taekwondo.persistencia.ConexionMySQL;

import taekwondo.util.FiltrosParaTextField;
import taekwondo.util.PintarPanel;
import taekwondo.util.XButtonOnTopBarListener;
import taekwondo.util.VentanaGenerica;
import taekwondo.util.Ventanas;

import taekwondo.logica.TaekwondokaController;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;
import java.awt.Color;


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
	private JPanel pnlDetailsButtons;
	
	private TaekwondokaController controller = new TaekwondokaController();
	
	private String nuevoColorPunta;
	private String nuevoColorCinturon;
	private JPanel topBar;
	private JLabel closeButton;
	private int yMouse;
	private int xMouse;
	
	public VerDetallesTaekwondoka(VentanaGenerica ventanaOrigen, Taekwondoka tae) {

		//Saves window to go back to
		this.ventanaOrigen = ventanaOrigen;
		this.tae = tae;
		
		nuevoColorCinturon = tae.getCinturon();
		nuevoColorPunta = tae.getPunta();
		
        //Default window for this app
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 416);
		setResizable(false);
		getContentPane().setLayout(null);
		setUndecorated(true);
		
		//pnl container of all components
		JPanel pnlAllContainer = new JPanel();
		pnlAllContainer.setBackground(new Color(52, 73, 94));
		pnlAllContainer.setBounds(0, 39, 611, 377);
		getContentPane().add(pnlAllContainer);
		pnlAllContainer.setLayout(null);
		
		//back button
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBackground(new Color(41, 128, 185));
		btnAtras.setForeground(new Color(255, 255, 255));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAtrasActionListener();
			}
		});
		btnAtras.setFont(new Font("Arial", Font.PLAIN, 13));
		btnAtras.setBounds(7, 5, 89, 23);
		pnlAllContainer.add(btnAtras);
		
		//label for title 1
		JLabel lblDetallesDe = new JLabel("Detalles de ");
		lblDetallesDe.setForeground(new Color(255, 255, 255));
		lblDetallesDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetallesDe.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblDetallesDe.setBounds(168, 5, 274, 29);
		pnlAllContainer.add(lblDetallesDe);
		
		//label for title 2
		JLabel lblApellidoNombre = new JLabel(tae.getApellido()+" "+tae.getNombre());
		lblApellidoNombre.setForeground(new Color(255, 255, 255));
		lblApellidoNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellidoNombre.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblApellidoNombre.setBounds(15, 36, 578, 29);
		pnlAllContainer.add(lblApellidoNombre);
		
		//panel container of details and buttons
		pnlDetailsButtons = new JPanel();
		pnlDetailsButtons.setBackground(new Color(52, 73, 94));
		pnlDetailsButtons.setLayout(null);
		pnlDetailsButtons.setBounds(18, 76, 575, 177);
		pnlAllContainer.add(pnlDetailsButtons);
		
		//label for name
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNombre.setBounds(10, 10, 77, 16);
		pnlDetailsButtons.add(lblNombre);
		
		//textfield for name
		tfNombre = new JTextField(tae.getNombre());
		tfNombre.setBorder(null);
		tfNombre.setBackground(new Color(44, 62, 80));
		tfNombre.setForeground(new Color(255, 255, 255));
		tfNombre.setEditable(false);
		tfNombre.setColumns(10);
		tfNombre.setBounds(97, 9, 170, 20);
		pnlDetailsButtons.add(tfNombre);
		
		//label for surname
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setForeground(new Color(255, 255, 255));
		lblApellido.setFont(new Font("Arial", Font.PLAIN, 15));
		lblApellido.setBounds(10, 38, 77, 16);
		pnlDetailsButtons.add(lblApellido);
		
		//textfield for surname
		tfApellido = new JTextField(tae.getApellido());
		tfApellido.setBorder(null);
		tfApellido.setBackground(new Color(44, 62, 80));
		tfApellido.setForeground(new Color(255, 255, 255));
		tfApellido.setEditable(false);
		tfApellido.setColumns(10);
		tfApellido.setBounds(97, 37, 170, 20);
		pnlDetailsButtons.add(tfApellido);
		
		//label for age
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setForeground(new Color(255, 255, 255));
		lblEdad.setFont(new Font("Arial", Font.PLAIN, 15));
		lblEdad.setBounds(10, 69, 77, 16);
		pnlDetailsButtons.add(lblEdad);
		
		//textfield for age
		tfEdad = new JTextField(tae.getEdad());
		tfEdad.setBorder(null);
		tfEdad.setBackground(new Color(44, 62, 80));
		tfEdad.setForeground(new Color(255, 255, 255));
		tfEdad.setEditable(false);
		tfEdad.setColumns(10);
		tfEdad.setBounds(97, 68, 170, 20);
		pnlDetailsButtons.add(tfEdad);
		
		//label for address
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setForeground(new Color(255, 255, 255));
		lblDireccion.setFont(new Font("Arial", Font.PLAIN, 15));
		lblDireccion.setBounds(308, 11, 77, 16);
		pnlDetailsButtons.add(lblDireccion);
		
		//textfield for address
		tfDireccion = new JTextField(tae.getDireccion());
		tfDireccion.setBorder(null);
		tfDireccion.setBackground(new Color(44, 62, 80));
		tfDireccion.setForeground(new Color(255, 255, 255));
		tfDireccion.setEditable(false);
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(395, 10, 170, 20);
		pnlDetailsButtons.add(tfDireccion);
		
		//label for email
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		lblEmail.setBounds(308, 42, 77, 16);
		pnlDetailsButtons.add(lblEmail);
		
		//textfield for email
		tfEmail = new JTextField(tae.getEmail());
		tfEmail.setBorder(null);
		tfEmail.setBackground(new Color(44, 62, 80));
		tfEmail.setForeground(new Color(255, 255, 255));
		tfEmail.setEditable(false);
		tfEmail.setColumns(10);
		tfEmail.setBounds(395, 41, 170, 20);
		pnlDetailsButtons.add(tfEmail);
		
		//label for cellphone
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setForeground(new Color(255, 255, 255));
		lblCelular.setToolTipText("asd");
		lblCelular.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCelular.setBounds(308, 73, 77, 16);
		pnlDetailsButtons.add(lblCelular);
		
		//textfield for cellphone
		tfCelular = new JTextField(tae.getCelular());
		tfCelular.setBorder(null);
		tfCelular.setBackground(new Color(44, 62, 80));
		tfCelular.setForeground(new Color(255, 255, 255));
		tfCelular.setEditable(false);
		tfCelular.setColumns(10);
		tfCelular.setBounds(395, 72, 170, 20);
		pnlDetailsButtons.add(tfCelular);
		
		//label for belt/tip
		JLabel lblCinturonPunta = new JLabel("Cinturon/Punta:");
		lblCinturonPunta.setForeground(new Color(255, 255, 255));
		lblCinturonPunta.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCinturonPunta.setBounds(30, 100, 126, 16);
		pnlDetailsButtons.add(lblCinturonPunta);
		
		//panel to represent belt and tip colors
		pnlCinturonPunta = PintarPanel.crearColorPanel(tae.getCinturon(), tae.getPunta(), 285, 95);
		pnlCinturonPunta.setBounds(150, 100, 380, 16);
		pnlDetailsButtons.add(pnlCinturonPunta);
		
		//button to rank up the athlete
		btnArrowUp = new JButton("");
		btnArrowUp.setBackground(new Color(41, 128, 185));
		btnArrowUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLevelUpActionListener();
			}
		});
		btnArrowUp.setToolTipText("Aumentar el rango de este Taekwondoka (una sola vez por edicion)");
		btnArrowUp.setIcon(new ImageIcon("D:\\eclipse workspace 01\\escuelataekwondo\\images\\arrow up 1.png"));
		btnArrowUp.setEnabled(false);
		btnArrowUp.setBounds(530, 100, 16, 16);
		pnlDetailsButtons.add(btnArrowUp);
		
		//edit button
		btnEditar = new JButton("Editar");
		btnEditar.setBackground(new Color(41, 128, 185));
		btnEditar.setForeground(new Color(255, 255, 255));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					btnEditarActionListener();
			}
		});
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnEditar.setBounds(200, 127, 170, 35);
		pnlDetailsButtons.add(btnEditar);
		
		//save button
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(new Color(41, 128, 185));
		btnGuardar.setForeground(new Color(255, 255, 255));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGuardarActionListener();
			}
		});
		btnGuardar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnGuardar.setBounds(110, 127, 170, 35);
		btnGuardar.setVisible(false);
		pnlDetailsButtons.add(btnGuardar);
		
		//cancel editing button
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(41, 128, 185));
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelarActionListener();
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCancelar.setBounds(290, 127, 170, 35);
		btnCancelar.setVisible(false);
		pnlDetailsButtons.add(btnCancelar);
		
		
		//filter for only number or letters
		FiltrosParaTextField.setupTextFieldDocumentFilter(tfNombre);
		FiltrosParaTextField.setupTextFieldDocumentFilter(tfApellido);
		FiltrosParaTextField.setupTextFieldDocumentFilter(tfEdad);
		FiltrosParaTextField.setupTextFieldDocumentFilterForNumbers(tfCelular);
		FiltrosParaTextField.setupTextFieldDocumentFilterForNumbers(tfEdad);
		//filter for email
		FiltrosParaTextField.setupTextFieldDocumentFilterForEmail(tfEmail);
		
		topBar = new JPanel();
		topBar.setBackground(new Color(44, 62, 80));
		topBar.setBounds(0, 0, 611, 40);
		getContentPane().add(topBar);
		topBar.setLayout(null);
		topBar.addMouseMotionListener(new MouseMotionAdapter() {
  			@Override
  			public void mouseDragged(MouseEvent e) {
  				mouseMovement(e);
  				}
  		});
  		topBar.addMouseListener(new MouseAdapter() {
  			@Override
  			public void mousePressed(MouseEvent e) {
  				updateCoordenates(e);
  			}
  		});
		
		closeButton = new JLabel("X");
		closeButton.setForeground(new Color(0, 0, 0));
		closeButton.setHorizontalAlignment(SwingConstants.CENTER);
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		closeButton.setBounds(565, 0, 46, 40);
		closeButton.addMouseListener(new MouseAdapter() {
  			@Override
  			public void mouseClicked(MouseEvent e) {
  				XButtonOnTopBarListener.cerrarApp();
  			}
  			@Override
  			public void mouseEntered(MouseEvent e) {
  				XButtonOnTopBarListener.mouseOnButton(closeButton);
  			}
  			@Override
  			public void mouseExited(MouseEvent e) {
  				XButtonOnTopBarListener.mouseNotOnButton(closeButton);
  			}
  			@Override
  			public void mousePressed(MouseEvent e) {
  				XButtonOnTopBarListener.buttonPressed(closeButton);
  			}
  		});
		topBar.add(closeButton);
	}

	protected void updateCoordenates(MouseEvent e) {
		xMouse = e.getX();
		yMouse = e.getY();
		//System.out.println("x: "+xMouse+", y: "+yMouse);
	}

	protected void mouseMovement(MouseEvent e) {
		this.setLocation(e.getXOnScreen() - xMouse, e.getYOnScreen() - yMouse);
		//System.out.println("x: "+e.getXOnScreen()+", y: "+e.getYOnScreen());
	}
	//rank up
	private void btnLevelUpActionListener() {

	        levelUp(tae.getCinturon(), tae.getPunta());
	        
	        pnlDetailsButtons.remove(pnlCinturonPunta);

	        pnlCinturonPunta = PintarPanel.crearColorPanel(nuevoColorCinturon, nuevoColorPunta, 285, 95);
	        pnlCinturonPunta.setBounds(150, 100, 380, 16);

	        pnlDetailsButtons.add(pnlCinturonPunta);

	        pnlDetailsButtons.revalidate();
	        pnlDetailsButtons.repaint();
	        
	}

	//color selection according to the cuurent rank
	private void levelUp(String colorCinturon, String colorPunta) {
		
		switch (colorCinturon.toLowerCase()) {
	    case "blanco":
	    	switch (colorPunta.toLowerCase()) {
		    case "blanco":
		        System.out.println("Cinturon blanco");

				nuevoColorCinturon = "blanco";
				nuevoColorPunta = "amarillo";
		        break;
		    case "amarillo":
		        System.out.println("Cinturon blanco punta amarilla");

				nuevoColorCinturon = "amarillo";
				nuevoColorPunta = "amarillo";
		        break;
		    default:
		        System.out.println("Hubo un problema al reconocer los colores del cinturon");
		        break;
	    	}
	        
	        break;
	    case "amarillo":
	    	switch (colorPunta.toLowerCase()) {
		    case "amarillo":
		        System.out.println("Cinturon amarillo");

				nuevoColorCinturon = "amarillo";
				nuevoColorPunta = "verde";
		        break;
		    case "verde":
		        System.out.println("Cinturon amarillo punta verde");

				nuevoColorCinturon = "verde";
				nuevoColorPunta = "verde";
		        break;
		    default:
		        System.out.println("Color de la punta no reconocido");
		        break;
	    	}
	    	
	        break;
	    case "verde":
	    	switch (colorPunta.toLowerCase()) {
		    case "verde":
		        System.out.println("Cinturon verde");

				nuevoColorCinturon = "verde";
				nuevoColorPunta = "azul";
		        break;
		    case "azul":
		        System.out.println("Cinturon verde punta azul");

				nuevoColorCinturon = "azul";
				nuevoColorPunta = "azul";
		        break;
		    default:
		        System.out.println("Color de la punta no reconocido");
		        break;
	    	}

	        break;
	    case "azul":
	    	switch (colorPunta.toLowerCase()) {
		    case "azul":
		        System.out.println("Cinturon azul");

				nuevoColorCinturon = "azul";
				nuevoColorPunta = "rojo";
		        break;
		    case "rojo":
		        System.out.println("Cinturon azul punta roja");
		        
				nuevoColorCinturon = "rojo";
				nuevoColorPunta = "rojo";
		        break;
		    default:
		        System.out.println("Color de la punta no reconocido");
		        break;
	    	}

	        break;
	    case "rojo":
	    	switch (colorPunta.toLowerCase()) {
		    case "rojo":
		        System.out.println("Cinturon rojo");

				nuevoColorCinturon = "rojo";
				nuevoColorPunta = "negro";
		        break;
		    case "negro":
		        System.out.println("Cinturon rojo punta negra");
				nuevoColorCinturon = "negro";
				nuevoColorPunta = "negro";
		        break;
		    default:
		        System.out.println("Color de la punta no reconocido");
		        break;
	    	}

	        break;
	    case "negro":
	    	System.out.println("Cinturon negro.\nProximamente se agregaran los Dan.");
	        break;
	    default:
	        System.out.println("Color de la punta no reconocido");
	        break;
	}
	}

	//save
	private void btnGuardarActionListener() {
		
		if(ConexionMySQL.obtenerConexion() != null) {
			
			nuevoTae = new Taekwondoka();

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
	
	//edit
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
	
	//cancel editing
	private void btnCancelarActionListener() {
		
		tfNombre.setText(tae.getNombre());
		tfApellido.setText(tae.getApellido());
		tfEdad.setText(tae.getEdad());
		tfDireccion.setText(tae.getDireccion());
		tfEmail.setText(tae.getEmail());
		tfCelular.setText(tae.getCelular());
		
		System.out.println(tae.getCinturon().toString());
		System.out.println(tae.getPunta().toString());

		pnlDetailsButtons.remove(pnlCinturonPunta);

        pnlCinturonPunta = PintarPanel.crearColorPanel(tae.getCinturon(), tae.getPunta(), 285, 95);
        pnlCinturonPunta.setBounds(150, 100, 380, 16);

        pnlDetailsButtons.add(pnlCinturonPunta);
        
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

	//back
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
