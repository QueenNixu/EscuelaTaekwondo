package taekwondo.igu;

import javax.swing.JFrame;

import taekwondo.logica.Taekwondoka;
import taekwondo.persistencia.ConexionMySQL;
import taekwondo.persistencia.TaekwondokaDAO;
import taekwondo.util.PintarPanel;
import taekwondo.util.Ventanas;
import taekwondo.logica.TaekwondokaController;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;


public class VerDetallesTaekwondoka extends JFrame {
	
	private VerTaekwondokas verTaekwondokas;
	private Taekwondoka tae;
	private Taekwondoka nuevoTae;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfEdad;
	private JTextField tfDireccion;
	private JTextField tfEmail;
	private JTextField tfCelular;
	
	private JButton btnEditar;
	private JButton btnArrowUp;
	
	
	private String colorNuevoCinturon;
	private String colorNuevaPunta;
	
	private TaekwondokaController controller = new TaekwondokaController();
	
	public VerDetallesTaekwondoka(VerTaekwondokas verTaekwondokas, Taekwondoka tae) {
		
		System.out.println("ID: "+tae.getId());
		System.out.println(tae.getNombre());
		System.out.println(tae.getApellido());
		System.out.println(tae.getEdad());
		System.out.println(tae.getDireccion());
		System.out.println(tae.getEmail());
		System.out.println(tae.getCelular());
		System.out.println(tae.getCinturon());
		System.out.println(tae.getPunta());
		
		this.verTaekwondokas = verTaekwondokas;
		this.tae = tae;
		
		
		
		colorNuevoCinturon = tae.getCinturon();
		colorNuevaPunta = tae.getPunta();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 416);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 595, 377);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDetalles = new JLabel("Detalles de ");
		lblDetalles.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalles.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblDetalles.setBounds(160, 5, 274, 29);
		panel.add(lblDetalles);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAtrasActionListener();
			}
		});
		btnAtras.setFont(new Font("Arial", Font.PLAIN, 13));
		btnAtras.setBounds(7, 5, 89, 23);
		panel.add(btnAtras);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 76, 575, 177);
		panel.add(panel_1);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGuardarActionListener();
			}
		});
		btnGuardar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnGuardar.setBounds(110, 127, 170, 35);
		panel_1.add(btnGuardar);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnEditar.getText().equals("Editar")) {
					btnEditarActionListener();
				} else {
					btnCancelarActionListener();
				}
			}
		});
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnEditar.setBounds(300, 127, 170, 35);
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
		
		JLabel lblCinturonpunta = new JLabel("Cinturon/Punta:");
		lblCinturonpunta.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCinturonpunta.setBounds(30, 100, 126, 16);
		panel_1.add(lblCinturonpunta);
		
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
		
		JPanel pnlCinturonPunta = PintarPanel.crearColorPanel(tae.getCinturon(), tae.getPunta(), 285, 95);
		pnlCinturonPunta.setBounds(150, 100, 380, 16);
		panel_1.add(pnlCinturonPunta);
		
		btnArrowUp = new JButton("");
		btnArrowUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLevelUpActionListener();
			}
		});
		btnArrowUp.setToolTipText("Aumentar el rango de este Taekwondoka");
		btnArrowUp.setIcon(new ImageIcon("D:\\eclipse workspace 01\\escuelataekwondo\\images\\arrow up 1.png"));
		btnArrowUp.setEnabled(false);
		btnArrowUp.setBounds(530, 100, 16, 16);
		panel_1.add(btnArrowUp);
		
	    
		JLabel lblApellidoNombre = new JLabel(tae.getApellido()+" "+tae.getNombre());
		lblApellidoNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellidoNombre.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblApellidoNombre.setBounds(7, 36, 578, 29);
		panel.add(lblApellidoNombre);
	}

	private void btnLevelUpActionListener() {
		
		System.out.println("Level Up!");
		
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
			nuevoTae.setCinturon(colorNuevoCinturon);
			nuevoTae.setPunta(colorNuevaPunta);
        	
        	if(controller.editarTaekwondoka(nuevoTae)) {
        		Ventanas.mostrarExito("Exito");
        		
        		btnCancelarActionListener();
        		
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
		
		btnEditar.setText("Cancelar");
		
	}
	
	private void btnCancelarActionListener() {
		
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
		
		btnEditar.setText("Editar");
		
	}

	private void btnAtrasActionListener() {
		dispose();
		verTaekwondokas.setLocation(this.getX(), this.getY());
		verTaekwondokas.setVisible(true);
		verTaekwondokas.cargarTabla();
	}
}
