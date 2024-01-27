package taekwondo.igu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import taekwondo.logica.Torneo;
import taekwondo.logica.TorneoController;

import taekwondo.persistencia.ConexionMySQL;

import taekwondo.util.FiltrosParaTextField;
import taekwondo.util.Ventanas;


import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Calendar;
import java.util.Date;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JYearChooser;
import java.awt.Color;

public class NuevoTorneo extends JFrame {
	
	private Menu menu;
	private JTextField tfNombre;
	
	private JCalendar calendar;
	
	private TorneoController controller = new TorneoController();
	
	public NuevoTorneo(Menu menu) {
		
		//Saves window to go back to
		this.menu = menu;

        //Default window for this app
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 611, 416);
        setResizable(false);
        getContentPane().setLayout(null);
        setUndecorated(true);
        
        //panel all container
        JPanel pnlAllContainer = new JPanel();
        pnlAllContainer.setBackground(new Color(52, 73, 94));
        pnlAllContainer.setBounds(0, 39, 611, 377);
        getContentPane().add(pnlAllContainer);
        pnlAllContainer.setLayout(null);
        
        //back button
        JButton btnSalir = new JButton("Atras");
        btnSalir.setBackground(new Color(41, 128, 185));
        btnSalir.setForeground(new Color(255, 255, 255));
        btnSalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnAtrasActionListener();
        	}
        });
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 13));
        btnSalir.setBounds(5, 5, 89, 23);
        pnlAllContainer.add(btnSalir);
        
        //label for tittle
        JLabel lblNuevoTorneo = new JLabel("Nuevo Torneo");
        lblNuevoTorneo.setForeground(new Color(255, 255, 255));
        lblNuevoTorneo.setHorizontalAlignment(SwingConstants.CENTER);
        lblNuevoTorneo.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblNuevoTorneo.setBounds(168, 5, 274, 29);
        pnlAllContainer.add(lblNuevoTorneo);
        
        //panel container of form
        JPanel pnl_1 = new JPanel();
        pnl_1.setBackground(new Color(52, 73, 94));
        pnl_1.setBounds(5, 39, 596, 327);
        pnlAllContainer.add(pnl_1);
        pnl_1.setLayout(null);
        
        //label for text
        JLabel lblNombre = new JLabel("Ingrese un nombre para el torneo y seleccione una fecha");
        lblNombre.setForeground(new Color(255, 255, 255));
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNombre.setBounds(10, 12, 381, 16);
        pnl_1.add(lblNombre);
        
        //textfield for name
        tfNombre = new JTextField();
        tfNombre.setForeground(new Color(255, 255, 255));
        tfNombre.setBackground(new Color(44, 62, 80));
        tfNombre.setColumns(10);
        tfNombre.setBounds(20, 39, 257, 20);
        pnl_1.add(tfNombre);
        
        //calendar
        calendar = new JCalendar();
        calendar.getDayChooser().setDecorationBackgroundColor(new Color(44, 62, 80));
        calendar.getDayChooser().setSundayForeground(new Color(255, 0, 0));
        calendar.getDayChooser().setWeekdayForeground(new Color(41, 128, 185));
        calendar.getDayChooser().getDayPanel().setBackground(new Color(44, 62, 80));
        calendar.setBorder(null);
        calendar.setBounds(20, 70, 257, 119);

        //min selectable day, current day
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = currentDate.getTime();
        calendar.setMinSelectableDate(tomorrow);

        //max selectable day, current day plus 10 years
        Calendar futureDate = Calendar.getInstance();
        futureDate.add(Calendar.YEAR, 10);
        Date maxDate = futureDate.getTime();
        calendar.setMaxSelectableDate(maxDate);

        //min selectable year, current year
        //max selectable day, current year plus 10
        JYearChooser yearChooser = calendar.getYearChooser();
        yearChooser.setMinimum(currentDate.get(Calendar.YEAR));
        yearChooser.setMaximum(currentDate.get(Calendar.YEAR) + 10);

        pnl_1.add(calendar);
        
        //save button
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(41, 128, 185));
        btnGuardar.setForeground(new Color(255, 255, 255));
        btnGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnGuardarActionListener(tfNombre.getText(), calendar.getDate());
        	}
        });
        btnGuardar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnGuardar.setBounds(60, 219, 170, 35);
        pnl_1.add(btnGuardar);
        
        FiltrosParaTextField.setupTextFieldDocumentFilterTorneoNombre(tfNombre);
        
        //label for image
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("D:\\eclipse workspace 01\\escuelataekwondo\\images\\taekwondo-2.1.png"));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(290, 0, 290, 327);
        pnl_1.add(lblNewLabel);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(44, 62, 80));
        panel.setBounds(0, 0, 611, 39);
        getContentPane().add(panel);

        
	}

	//save
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

	//back
	protected void btnAtrasActionListener() {

		dispose();
        menu.setLocation(this.getX(), this.getY());
        menu.setVisible(true);
		
	}
}
