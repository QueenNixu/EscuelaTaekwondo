package taekwondo.igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import taekwondo.logica.Taekwondoka;
import taekwondo.logica.TaekwondokaController;
import taekwondo.logica.Torneo;
import taekwondo.logica.TorneoController;
import taekwondo.persistencia.ConexionMySQL;
import taekwondo.util.Ventanas;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

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
	private JTable table;
	private TorneoController torneoController = new TorneoController();
	private List<Taekwondoka> listaInscriptos;
	private JTextField textField;
	private TaekwondokaController taekwondokaController = new TaekwondokaController();
	
	private JLabel lblParticipantes;
	
	public VerDetallesTorneo(VerTorneos verTorneo, Torneo tor) {
		
		this.verTorneos = verTorneo;
		this.tor = tor;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				cargarTabla();
			}
		});
		
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
        
        JLabel lblDetallesDe = new JLabel("Detalles del Torneo:");
        lblDetallesDe.setHorizontalAlignment(SwingConstants.CENTER);
        lblDetallesDe.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblDetallesDe.setBounds(160, 5, 274, 29);
        getContentPane().add(lblDetallesDe);
        
        JLabel lblApellidoNombre = new JLabel(tor.getNombre());
        lblApellidoNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblApellidoNombre.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblApellidoNombre.setBounds(7, 36, 578, 29);
        getContentPane().add(lblApellidoNombre);
        
        JPanel panel = new JPanel();
        panel.setBounds(5, 76, 290, 225);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNombre.setBounds(10, 12, 77, 16);
        panel.add(lblNombre);
        
        tfNombre = new JTextField(tor.getNombre());
        tfNombre.setColumns(10);
        tfNombre.setEnabled(false);
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
        
        
        
        lblParticipantes = new JLabel(tor.getParticipantes()+"");
        lblParticipantes.setFont(new Font("Arial", Font.PLAIN, 15));
        lblParticipantes.setBounds(97, 40, 77, 16);
        panel.add(lblParticipantes);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(305, 76, 280, 225);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        table = new JTable();
		table.setBounds(1, 1, 575, 0);
		table.setBorder(null);
		table.getTableHeader().setResizingAllowed(false);
		panel_1.add(table.getTableHeader(), BorderLayout.PAGE_START);
		panel_1.add(table, BorderLayout.CENTER);
		table.getTableHeader().setReorderingAllowed(false);
		panel_1.setLayout(null);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
		lblBuscar.setBounds(10, 12, 57, 16);
		panel_1.add(lblBuscar);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(77, 11, 193, 20);
		panel_1.add(textField);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 40, 280, 185);
		panel_1.add(scrollPane);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(5, 315, 100, 35);
		getContentPane().add(btnGuardar);
		btnGuardar.setFont(new Font("Arial", Font.PLAIN, 16));
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(115, 315, 100, 35);
		getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelarActionListener();
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 16));
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(69, 315, 100, 35);
		getContentPane().add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditarActionListener();
			}
		});
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JButton btnInscribir = new JButton("Inscribir");
		btnInscribir.setBounds(225, 315, 100, 35);
		getContentPane().add(btnInscribir);
		btnInscribir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnInscribirActionListener();
			}
		});
		btnInscribir.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JButton btnDarDeBaja = new JButton("Dar de Baja");
		btnDarDeBaja.setBounds(335, 315, 120, 35);
		getContentPane().add(btnDarDeBaja);
		btnDarDeBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDarDeBajaActionListener();
			}
		});
		btnDarDeBaja.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JButton btnMedallas = new JButton("Medallas");
		btnMedallas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMedallasActionListener();
			}
		});
		btnMedallas.setFont(new Font("Arial", Font.PLAIN, 16));
		btnMedallas.setBounds(465, 315, 120, 35);
		getContentPane().add(btnMedallas);
		btnCancelar.setVisible(false);
		btnGuardar.setVisible(false);
		
		if(today.after(tor.getFecha())) {
			btnDarDeBaja.setEnabled(false);
        } else {
        	btnDarDeBaja.setEnabled(true);
        }
		if(today.after(tor.getFecha())) {
			btnInscribir.setEnabled(false);
        } else {
        	btnInscribir.setEnabled(true);
        }
		if(today.after(tor.getFecha())) {
        	btnEditar.setEnabled(false);
        } else {
        	btnEditar.setEnabled(true);
        }
		
		
        
	}

	protected void btnMedallasActionListener() {
		
		System.out.println("Medallas.");
		
	}

	protected void btnDarDeBajaActionListener() {
		
		if (ConexionMySQL.obtenerConexion() != null) {
			int filaSeleccionada = table.getSelectedRow();
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			if(filaSeleccionada != -1) {
				int idTae = (int) modelo.getValueAt(filaSeleccionada, 0);
				
				// buscar todos los datos del Taekwondoka (mail e id son unicos)
				Taekwondoka tae = taekwondokaController.traerTaekwondokaById(idTae);
				Torneo tor2 = torneoController.traerTorneoById(tor.getId());
				
				if(tae != null && tor2 != null) {
					//elimianr relacion torneo taekwondoka
					if(torneoController.eliminarTaeTor(tor.getId(), tae.getId())) {
						Ventanas.mostrarExito("Taekwondoka dado de baja con exito.");
						cargarTabla();
					}
				} else {
					Ventanas.mostrarError("Hubo un problema y no se encontro el Taekwondoka y/o al torneo.");
				}
			} else {
				Ventanas.mostrarError("Seleccione una fila.");
			}
        } else {
            Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
        }
		
	}

	protected void btnInscribirActionListener() {
		
		if (ConexionMySQL.obtenerConexion() != null) {
			
			InscribirTaekwondokaTorneo vitt = new InscribirTaekwondokaTorneo(this, tor);
			vitt.setLocation(this.getX(), this.getY());
            dispose();
            vitt.setVisible(true);
            
        } else {
            Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
        }
		
	}

	protected void cargarTabla() {
		
		DefaultTableModel tablaModelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		String titulos[] = { "id", "Apellido", "Nombre"};
		tablaModelo.setColumnIdentifiers(titulos);
		
		if(ConexionMySQL.obtenerConexion() != null) {
			
			listaInscriptos = taekwondokaController.traerInscriptos(tor.getId());
			
			if (listaInscriptos != null) {
				for (Taekwondoka ins : listaInscriptos) {
					    
					Object[] objeto = {
							ins.getId(),
							ins.getApellido(),
							ins.getNombre()
							};
					tablaModelo.addRow(objeto);
				}
			}

			table.setModel(tablaModelo);
		}
		
		// Obtén el modelo de columna de la tabla
		TableColumnModel columnModel = table.getColumnModel();

		// Ajusta el ancho predeterminado de las columnas
		int[] anchos = { 0, 139, 139}; // Puedes ajustar los valores según tus necesidades

		for (int i = 0; i < columnModel.getColumnCount() && i < anchos.length; i++) {
			TableColumn column = columnModel.getColumn(i);
			column.setPreferredWidth(anchos[i]);
			column.setResizable(false);
		}

		TableColumn columna = table.getColumnModel().getColumn(0);
		table.getColumnModel().removeColumn(columna);
		
	}

	protected void btnCancelarActionListener() {
		
		tfNombre.setText(tor.getNombre());
		
		calendar.setDate(tor.getFecha());
		
		calendar.setEnabled(false);
		tfNombre.setEnabled(false);
		
		btnEditar.setVisible(true);
		btnGuardar.setVisible(false);
		btnCancelar.setVisible(false);
		
	}

	protected void btnEditarActionListener() {

			tfNombre.setEnabled(true);
			
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
	
	public void actualizarParticipantes() {
		//lblParticipantes.setText(tor.getParticipantes()+"");
		this.tor = torneoController.traerTorneoById(tor.getId());
		lblParticipantes.setText(tor.getParticipantes()+"");
	}
}
