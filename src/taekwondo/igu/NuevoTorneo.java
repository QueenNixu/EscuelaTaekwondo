package taekwondo.igu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import taekwondo.logica.Taekwondoka;
import taekwondo.logica.Torneo;
import taekwondo.logica.TorneoController;
import taekwondo.persistencia.ConexionMySQL;
import taekwondo.util.FiltrosParaTextField;
import taekwondo.util.Ventanas;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.IntStream;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JYearChooser;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class NuevoTorneo extends JFrame {
	
	private Menu menu;
	private JTextField tfNombre;
	
	private JCalendar calendar;
	
	private TorneoController controller = new TorneoController();
	
	public NuevoTorneo(Menu menu) {
		
		this.menu = menu;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 611, 416);
        setResizable(false);
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 595, 377);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNuevoTorneo = new JLabel("Nuevo Torneo");
        lblNuevoTorneo.setHorizontalAlignment(SwingConstants.CENTER);
        lblNuevoTorneo.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblNuevoTorneo.setBounds(160, 5, 274, 29);
        panel.add(lblNuevoTorneo);
        
        JButton btnSalir = new JButton("Atras");
        btnSalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnAtrasActionListener();
        	}
        });
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 13));
        btnSalir.setBounds(5, 5, 89, 23);
        panel.add(btnSalir);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(5, 39, 580, 327);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNombre = new JLabel("Ingrese un nombre para el torneo y seleccione una fecha");
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNombre.setBounds(10, 12, 381, 16);
        panel_1.add(lblNombre);
        
        tfNombre = new JTextField();
        tfNombre.setColumns(10);
        tfNombre.setBounds(20, 39, 257, 20);
        panel_1.add(tfNombre);
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnGuardarActionListener(tfNombre.getText(), calendar.getDate());
        	}
        });
        btnGuardar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnGuardar.setBounds(60, 219, 170, 35);
        panel_1.add(btnGuardar);
        
        FiltrosParaTextField.setupTextFieldDocumentFilterTorneoNombre(tfNombre);

        calendar = new JCalendar();
        calendar.setBorder(null);
        calendar.setBounds(20, 70, 257, 119);

        // Configurar la fecha mínima como la fecha actual
        Calendar currentDate = Calendar.getInstance();
        Date today = currentDate.getTime();
        calendar.setMinSelectableDate(today);

        // Configurar la fecha máxima según tus necesidades (por ejemplo, 1 año en el futuro)
        Calendar futureDate = Calendar.getInstance();
        futureDate.add(Calendar.YEAR, 10); // Puedes ajustar este valor según tus necesidades
        Date maxDate = futureDate.getTime();
        calendar.setMaxSelectableDate(maxDate);

        // Deshabilitar la navegación a años anteriores
        JYearChooser yearChooser = calendar.getYearChooser();
     // Establecer el rango de años permitidos (desde el año actual hasta 10 años en el futuro)
        yearChooser.setMinimum(currentDate.get(Calendar.YEAR));
        yearChooser.setMaximum(currentDate.get(Calendar.YEAR) + 10);

        panel_1.add(calendar);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("D:\\eclipse workspace 01\\escuelataekwondo\\images\\taekwondo-2.1.png"));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(290, 0, 290, 327);
        panel_1.add(lblNewLabel);

        
	}

	protected void btnGuardarActionListener(String nombre, Date fecha) {
		
		if(ConexionMySQL.obtenerConexion() != null) {
			
			java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
			
        	
            Torneo nuevoTorneo = new Torneo(nombre.trim(), fechaSql);
        	
        	if(controller.guardarNuevoTorneo(nuevoTorneo)) {
        		Ventanas.mostrarExito("Se ha agregado un nuevo torneo.");
        		tfNombre.setText("");
        	}
		} else {
			Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
		}
		
	}

	protected void btnAtrasActionListener() {

		dispose();
        menu.setLocation(this.getX(), this.getY());
        menu.setVisible(true);
		
	}
}
