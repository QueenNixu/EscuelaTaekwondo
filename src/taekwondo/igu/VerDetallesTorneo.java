package taekwondo.igu;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import taekwondo.logica.Taekwondoka;
import taekwondo.logica.TaekwondokaController;
import taekwondo.logica.Torneo;
import taekwondo.logica.TorneoController;

import taekwondo.persistencia.ConexionMySQL;
import taekwondo.util.XButtonOnTopBarListener;
import taekwondo.util.Ventanas;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JYearChooser;
import java.awt.Color;

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
	private JTextField tfBuscar;
	private TaekwondokaController taekwondokaController = new TaekwondokaController();
	
	private JLabel lblParticipantes;
	private int xMouse;
	private int yMouse;
	
	public VerDetallesTorneo(VerTorneos verTorneo, Torneo tor) {
		getContentPane().setBackground(new Color(52, 73, 94));
		
		//Saves window to go back to
		this.verTorneos = verTorneo;
		this.tor = tor;
		
		//load table at window opened
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				cargarTabla();
			}
		});
		
        //Default window for this app
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 611, 416);
        setResizable(false);
        getContentPane().setLayout(null);
        setUndecorated(true);
        
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
        btnSalir.setBounds(10, 51, 89, 23);
        getContentPane().add(btnSalir);
        
        //Label for title 1
        JLabel lblDetallesDe = new JLabel("Detalles del Torneo:");
        lblDetallesDe.setForeground(new Color(255, 255, 255));
        lblDetallesDe.setHorizontalAlignment(SwingConstants.CENTER);
        lblDetallesDe.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblDetallesDe.setBounds(165, 51, 274, 29);
        getContentPane().add(lblDetallesDe);
        
        //label for title 2
        JLabel lblApellidoNombre = new JLabel(tor.getNombre());
        lblApellidoNombre.setForeground(new Color(255, 255, 255));
        lblApellidoNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblApellidoNombre.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblApellidoNombre.setBounds(12, 82, 578, 29);
        getContentPane().add(lblApellidoNombre);
        
        //panel for tournement info
        JPanel pnl_1 = new JPanel();
        pnl_1.setBackground(new Color(52, 73, 94));
        pnl_1.setBounds(10, 122, 290, 225);
        getContentPane().add(pnl_1);
        pnl_1.setLayout(null);
        
        //Label for tournement name
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(new Color(255, 255, 255));
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNombre.setBounds(10, 12, 77, 16);
        pnl_1.add(lblNombre);
        
        //textfield for name
        tfNombre = new JTextField(tor.getNombre());
        tfNombre.setBorder(null);
        tfNombre.setBackground(new Color(44, 62, 80));
        tfNombre.setForeground(new Color(255, 255, 255));
        tfNombre.setColumns(10);
        tfNombre.setEnabled(false);
        tfNombre.setBounds(97, 11, 184, 20);
        pnl_1.add(tfNombre);
        
        //Label for signed athlete's
        JLabel lblInscriptos = new JLabel("Inscriptos:");
        lblInscriptos.setForeground(new Color(255, 255, 255));
        lblInscriptos.setFont(new Font("Arial", Font.PLAIN, 15));
        lblInscriptos.setBounds(10, 40, 77, 16);
        pnl_1.add(lblInscriptos);
        
        //Label for signed athlete's 2
        lblParticipantes = new JLabel(tor.getParticipantes()+"");
        lblParticipantes.setForeground(new Color(255, 255, 255));
        lblParticipantes.setFont(new Font("Arial", Font.PLAIN, 15));
        lblParticipantes.setBounds(97, 40, 77, 16);
        pnl_1.add(lblParticipantes);
        
        //label for date
        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setForeground(new Color(255, 255, 255));
        lblFecha.setFont(new Font("Arial", Font.PLAIN, 15));
        lblFecha.setBounds(10, 67, 55, 16);
        pnl_1.add(lblFecha);
        
        //calendar for date
        calendar = new JCalendar();
        calendar.getDayChooser().setWeekdayForeground(new Color(41, 128, 185));
        calendar.getDayChooser().setSundayForeground(new Color(255, 0, 0));
        calendar.getDayChooser().setDecorationBackgroundColor(new Color(44, 62, 80));
        calendar.getDayChooser().getDayPanel().setForeground(new Color(255, 255, 255));
        calendar.getDayChooser().getDayPanel().setBackground(new Color(44, 62, 80));
        calendar.setBounds(97, 67, 184, 153);

        Calendar currentDate = Calendar.getInstance();
        Date today = currentDate.getTime();
        calendar.setMinSelectableDate(today);

        Calendar futureDate = Calendar.getInstance();
        futureDate.add(Calendar.YEAR, 10);
        Date maxDate = futureDate.getTime();
        calendar.setMaxSelectableDate(maxDate);

        JYearChooser yearChooser = calendar.getYearChooser();
        yearChooser.setMinimum(currentDate.get(Calendar.YEAR));
        yearChooser.setMaximum(currentDate.get(Calendar.YEAR) + 10);

        calendar.setDate(tor.getFecha());

        calendar.setEnabled(false);
        
        pnl_1.add(calendar);
        
        //panel for signed athlete's
        JPanel pnl_2 = new JPanel();
        pnl_2.setBackground(new Color(52, 73, 94));
        pnl_2.setBounds(310, 122, 280, 225);
        getContentPane().add(pnl_2);
        pnl_2.setLayout(null);
        
        //label for search bar
        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setForeground(new Color(255, 255, 255));
		lblBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
		lblBuscar.setBounds(10, 12, 57, 16);
		pnl_2.add(lblBuscar);
		
		//Textfield for search bar
		tfBuscar = new JTextField();
		tfBuscar.setBorder(null);
		tfBuscar.setBackground(new Color(44, 62, 80));
		tfBuscar.setForeground(new Color(255, 255, 255));
		tfBuscar.setColumns(10);
		tfBuscar.setBounds(77, 11, 193, 20);
		pnl_2.add(tfBuscar);
        
		//table for signed athlete's
        table = new JTable();
		table.setBounds(1, 1, 575, 0);
		table.setBorder(null);
		table.getTableHeader().setResizingAllowed(false);
		pnl_2.add(table.getTableHeader(), BorderLayout.PAGE_START);
		pnl_2.add(table, BorderLayout.CENTER);
		table.getTableHeader().setReorderingAllowed(false);
		pnl_2.setLayout(null);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
		//scrollbar for table
        JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 40, 280, 185);
		pnl_2.add(scrollPane);
		
		//Edit button
		btnEditar = new JButton("Editar");
		btnEditar.setBackground(new Color(41, 128, 185));
		btnEditar.setForeground(new Color(255, 255, 255));
		btnEditar.setBounds(74, 361, 100, 35);
		getContentPane().add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditarActionListener();
			}
		});
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 16));
		
		//Save button
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(new Color(41, 128, 185));
		btnGuardar.setForeground(new Color(255, 255, 255));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGuardarActionListener();
			}
		});
		btnGuardar.setBounds(10, 361, 100, 35);
		getContentPane().add(btnGuardar);
		btnGuardar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnGuardar.setVisible(false);
		
		//cancel editing button
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(41, 128, 185));
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBounds(120, 361, 100, 35);
		getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelarActionListener();
			}
		});
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCancelar.setVisible(false);
		
		//button to open the window to sign athlete's
		JButton btnInscribir = new JButton("Inscribir");
		btnInscribir.setBackground(new Color(41, 128, 185));
		btnInscribir.setForeground(new Color(255, 255, 255));
		btnInscribir.setBounds(230, 361, 100, 35);
		getContentPane().add(btnInscribir);
		btnInscribir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnInscribirActionListener();
			}
		});
		btnInscribir.setFont(new Font("Arial", Font.PLAIN, 16));
		
		// Button to remove an athlete from the tournament
		JButton btnDarDeBaja = new JButton("Dar de Baja");
		btnDarDeBaja.setBackground(new Color(41, 128, 185));
		btnDarDeBaja.setForeground(new Color(255, 255, 255));
		btnDarDeBaja.setBounds(340, 361, 120, 35);
		getContentPane().add(btnDarDeBaja);
		btnDarDeBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDarDeBajaActionListener();
			}
		});
		btnDarDeBaja.setFont(new Font("Arial", Font.PLAIN, 16));
		
		// Button to open the window to select the medalists
		JButton btnMedallas = new JButton("Medallas");
		btnMedallas.setBackground(new Color(41, 128, 185));
		btnMedallas.setForeground(new Color(255, 255, 255));
		btnMedallas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMedallasActionListener();
			}
		});
		btnMedallas.setFont(new Font("Arial", Font.PLAIN, 16));
		btnMedallas.setBounds(470, 361, 120, 35);
		getContentPane().add(btnMedallas);
		
		JPanel topBar = new JPanel();
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
		
		JLabel closeButton = new JLabel("X");
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
		
		if(today.after(tor.getFecha())) {
			btnDarDeBaja.setEnabled(false);
			btnEditar.setEnabled(false);
			btnInscribir.setEnabled(false);
			btnMedallas.setEnabled(false);
        } else {
        	btnEditar.setEnabled(true);
        	btnDarDeBaja.setEnabled(true);
        	btnInscribir.setEnabled(true);
        	btnMedallas.setEnabled(true);
        }
		
		//filter for search bar
		tfBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarTabla();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarTabla();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
		
		
        
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
	//save
	protected void btnGuardarActionListener() {
		
		if(ConexionMySQL.obtenerConexion() != null) {
			
			java.sql.Date fechaSql = new java.sql.Date(calendar.getDate().getTime());
        	
            Torneo nuevoTorneo = tor;
            
            nuevoTorneo.setNombre(tfNombre.getText().trim());
            nuevoTorneo.setFecha(fechaSql);
            
        	if(torneoController.editarTorneo(nuevoTorneo)) {
        		Ventanas.mostrarExito("Se ha editado el torneo.");
        		this.tor = nuevoTorneo;
        		btnCancelarActionListener();
        		
        	}
		} else {
			Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
		}
		
	}

	//filter table
	protected void filtrarTabla() {
		
		String textoBusqueda = tfBuscar.getText().toLowerCase();
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0); // Limpiar la tabla

        for (Taekwondoka tae : listaInscriptos) {

            if (tae.getNombre().toLowerCase().contains(textoBusqueda) ||
                tae.getApellido().toLowerCase().contains(textoBusqueda)
                ) {
                Object[] objeto = { tae.getId(), tae.getApellido(), tae.getNombre()};
                modelo.addRow(objeto);
            }
        }
		
	}

	//open medalist window
	protected void btnMedallasActionListener() {
		
		if (ConexionMySQL.obtenerConexion() != null) {
			
			MedallasTorneo vmt = new MedallasTorneo(this, listaInscriptos, tor);
			vmt.setLocation(this.getX(), this.getY());
            dispose();
            vmt.setVisible(true);
            
        } else {
            Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
        }
		
		
	}

	//remove athlete from tournement
	protected void btnDarDeBajaActionListener() {
		
		if (ConexionMySQL.obtenerConexion() != null) {
			int filaSeleccionada = table.getSelectedRow();
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			if(filaSeleccionada != -1) {
				int idTae = (int) modelo.getValueAt(filaSeleccionada, 0);

				Taekwondoka tae = taekwondokaController.traerTaekwondokaById(idTae);
				Torneo tor2 = torneoController.traerTorneoById(tor.getId());
				
				if(tae != null && tor2 != null) {

					if(torneoController.eliminarTaeTor(tor2.getId(), tae.getId())) {
						Ventanas.mostrarExito("Taekwondoka dado de baja con exito.");
						cargarTabla();
						actualizarParticipantes();
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

	//opens window to sign athlete's to the tournement
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

	//load table
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

		TableColumnModel columnModel = table.getColumnModel();

		int[] anchos = { 0, 139, 139};

		for (int i = 0; i < columnModel.getColumnCount() && i < anchos.length; i++) {
			TableColumn column = columnModel.getColumn(i);
			column.setPreferredWidth(anchos[i]);
			column.setResizable(false);
		}

		TableColumn columna = table.getColumnModel().getColumn(0);
		table.getColumnModel().removeColumn(columna);
		
	}

	//cancel editing button
	protected void btnCancelarActionListener() {
		
		tfNombre.setText(tor.getNombre());
		
		calendar.setDate(tor.getFecha());
		
		calendar.setEnabled(false);
		tfNombre.setEnabled(false);
		
		btnEditar.setVisible(true);
		btnGuardar.setVisible(false);
		btnCancelar.setVisible(false);
		
	}

	//edit
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

	//back
	protected void btnAtrasActionListener() {
		
		dispose();
        verTorneos.setLocation(this.getX(), this.getY());
        verTorneos.setVisible(true);
        verTorneos.cargarTabla();
		
	}
	
	//update participants
	public void actualizarParticipantes() {
		//lblParticipantes.setText(tor.getParticipantes()+"");
		this.tor = torneoController.traerTorneoById(tor.getId());
		lblParticipantes.setText(tor.getParticipantes()+"");
	}
}
