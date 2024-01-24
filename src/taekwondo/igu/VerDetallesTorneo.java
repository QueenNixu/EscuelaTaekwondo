package taekwondo.igu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import taekwondo.logica.Torneo;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JYearChooser;

public class VerDetallesTorneo extends JFrame {

	private VerTorneos verTorneos;
	private Torneo tor;
	private JTextField tfNombre;
	
	private JButton btnEditar;
	private JButton btnCancelar;
	private JButton btnGuardar;
	
	private JCalendar calendar;
	
	public VerDetallesTorneo(VerTorneos verTorneo, Torneo tor) {
		
		this.verTorneos = verTorneo;
		this.tor = tor;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 611, 416);
        setResizable(false);
        getContentPane().setLayout(null);
        
        JButton btnSalir = new JButton("Atras");
        btnSalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnAtrasActionListener();
        	}
        });
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 13));
        btnSalir.setBounds(5, 5, 89, 23);
        getContentPane().add(btnSalir);
        
        JLabel lblDetallesDe = new JLabel("Detalles de ");
        lblDetallesDe.setHorizontalAlignment(SwingConstants.CENTER);
        lblDetallesDe.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblDetallesDe.setBounds(160, 5, 274, 29);
        getContentPane().add(lblDetallesDe);
        
        JLabel lblApellidoNombre = new JLabel("<dynamic> <dynamic>");
        lblApellidoNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblApellidoNombre.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblApellidoNombre.setBounds(7, 36, 578, 29);
        getContentPane().add(lblApellidoNombre);
        
        JPanel panel = new JPanel();
        panel.setBounds(5, 76, 580, 290);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNombre.setBounds(10, 12, 77, 16);
        panel.add(lblNombre);
        
        tfNombre = new JTextField(tor.getNombre());
        tfNombre.setEditable(false);
        tfNombre.setColumns(10);
        tfNombre.setBounds(97, 11, 184, 20);
        panel.add(tfNombre);
        
        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setFont(new Font("Arial", Font.PLAIN, 15));
        lblFecha.setBounds(10, 67, 55, 16);
        panel.add(lblFecha);
        
        JLabel lblInscriptos = new JLabel("Inscriptos:");
        lblInscriptos.setFont(new Font("Arial", Font.PLAIN, 15));
        lblInscriptos.setBounds(10, 40, 77, 16);
        panel.add(lblInscriptos);
        
        calendar = new JCalendar();
        calendar.setBounds(97, 67, 184, 153);
        
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
        
        
        
        calendar.setDate(tor.getFecha());
        
        //Configurar para que no se pueda seleccionar ninguna fecha
        calendar.setEnabled(false);
        
        panel.add(calendar);
        
        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnGuardar.setBounds(17, 231, 123, 35);
        btnGuardar.setVisible(false);
        panel.add(btnGuardar);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnCancelarActionListener();
        	}
        });
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnCancelar.setBounds(150, 231, 123, 35);
        btnCancelar.setVisible(false);
        panel.add(btnCancelar);
        
        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnEditarActionListener();
        	}
        });
        btnEditar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnEditar.setBounds(128, 231, 123, 35);
        
        if(today.after(tor.getFecha())) {
        	btnEditar.setEnabled(false);
        } else {
        	btnEditar.setEnabled(true);
        }
        panel.add(btnEditar);
        
        JLabel lblParticipantes2 = new JLabel(tor.getParticipantes()+"");
        lblParticipantes2.setFont(new Font("Arial", Font.PLAIN, 15));
        lblParticipantes2.setBounds(97, 40, 77, 16);
        panel.add(lblParticipantes2);
        
	}

	protected void btnCancelarActionListener() {
		
		tfNombre.setText(tor.getNombre());
		
		calendar.setDate(tor.getFecha());
		
		calendar.setEnabled(false);
		
		btnEditar.setVisible(true);
		btnGuardar.setVisible(false);
		btnCancelar.setVisible(false);
		
	}

	protected void btnEditarActionListener() {

			tfNombre.setEditable(true);
			
			calendar.setEnabled(true);
			
			Calendar currentDate = Calendar.getInstance();
	        Date today = currentDate.getTime();
	        calendar.setMinSelectableDate(today);
			
			
			
			btnEditar.setVisible(false);
			btnGuardar.setVisible(true);
			btnCancelar.setVisible(true);

	}

	protected void btnAtrasActionListener() {
		
		dispose();
        verTorneos.setLocation(this.getX(), this.getY());
        verTorneos.setVisible(true);
        verTorneos.cargarTabla();
		
	}
}
