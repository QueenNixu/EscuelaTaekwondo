package taekwondo.igu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import taekwondo.logica.Taekwondoka;
import taekwondo.logica.TaekwondokaController;
import taekwondo.logica.Torneo;
import taekwondo.logica.TorneoController;
import taekwondo.persistencia.ConexionMySQL;
import taekwondo.util.Ventanas;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

public class VerTorneos extends JFrame {
	
	private Menu menu;
	private JTextField tfBuscar;
	private JTable table;
	private TorneoController controller = new TorneoController();
	private List<Torneo> listaTorneos;
	private TaekwondokaController taekwondokaController = new TaekwondokaController();
	private TorneoController torneoController = new TorneoController();

	
	public VerTorneos(Menu menu) {
		
		this.menu = menu;
		
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
        
		JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBounds(7, 73, 580, 243);
        getContentPane().add(panel_1);
		
		table = new JTable();
		table.setBounds(1, 1, 575, 0);
		table.setBorder(null);
		table.getTableHeader().setResizingAllowed(false);
		panel_1.add(table.getTableHeader(), BorderLayout.PAGE_START);
		panel_1.add(table, BorderLayout.CENTER);
		table.getTableHeader().setReorderingAllowed(false);
		panel_1.setLayout(null);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// Agrega el JScrollPane envolviendo la tabla
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 580, 243);
		panel_1.add(scrollPane);
        
        JButton btnSalir = new JButton("Atras");
        btnSalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnAtrasActionListener();
        	}
        });
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 13));
        btnSalir.setBounds(5, 5, 89, 23);
        getContentPane().add(btnSalir);
        
        JLabel lblTitulo = new JLabel("Lista de Torneos");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblTitulo.setBounds(160, 5, 274, 29);
        getContentPane().add(lblTitulo);
        
        JPanel pnlBuscador = new JPanel();
        pnlBuscador.setLayout(null);
        pnlBuscador.setBounds(5, 38, 580, 24);
        getContentPane().add(pnlBuscador);
        
        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
        lblBuscar.setBounds(10, 5, 57, 14);
        pnlBuscador.add(lblBuscar);
        
        tfBuscar = new JTextField();
        tfBuscar.setColumns(10);
        tfBuscar.setBounds(77, 3, 493, 18);
        pnlBuscador.add(tfBuscar);
        
        JPanel pnlBotones = new JPanel();
        pnlBotones.setLayout(null);
        pnlBotones.setBounds(143, 327, 313, 40);
        getContentPane().add(pnlBotones);
        
        JButton btnVerDetalles = new JButton("Ver Detalles");
        btnVerDetalles.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnVerDeatellesActionListener();
        	}
        });
        btnVerDetalles.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVerDetalles.setBounds(10, 5, 140, 30);
        pnlBotones.add(btnVerDetalles);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnEliminarActionListener();
        	}
        });
        btnEliminar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnEliminar.setBounds(160, 5, 140, 30);
        pnlBotones.add(btnEliminar);
        
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
                // Este método es menos relevante para campos de texto simples
            }
        });
        
	}
	
	protected void btnEliminarActionListener() {
		
		if (ConexionMySQL.obtenerConexion() != null) {
			int filaSeleccionada = table.getSelectedRow();
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			if(filaSeleccionada != -1) {
				int idTor = (int) modelo.getValueAt(filaSeleccionada, 0);
				
				// buscar todos los datos del Taekwondoka (mail e id son unicos)
				Torneo tor = torneoController.traerTorneoById(idTor);
				if(tor != null) {
					//elimianr taekwondoka
					if(torneoController.eliminarTorneo(tor.getId())) {
						Ventanas.mostrarExito("Taekwondoka eliminado con exito.");
						cargarTabla();
					}
				} else {
					Ventanas.mostrarError("Hubo un problema y no se encontro el Taekwondoka.");
				}
			} else {
				Ventanas.mostrarError("Seleccione una fila.");
			}
        } else {
            Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
        }
		
	}

	protected void btnVerDeatellesActionListener() {
		
		if (ConexionMySQL.obtenerConexion() != null) {
			
			int filaSeleccionada = table.getSelectedRow();
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			if(filaSeleccionada != -1) {
				int torId = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString());
				System.out.println("id del tor: "+torId);
				
				// buscar todos los datos del Taekwondoka (mail e id son unicos)
				Torneo tor = torneoController.traerTorneoById(torId);
				if(tor != null) {
					VerDetallesTorneo VVDT = new VerDetallesTorneo(this, tor);
		            VVDT.setLocation(this.getX(), this.getY());
		            dispose();
		            VVDT.setVisible(true);
				} else {
					Ventanas.mostrarError("Hubo un problema y no se encontro el Taekwondoka.");
				}
			} else {
				Ventanas.mostrarError("Seleccione una fila.");
			}
        } else {
            Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
        }
		
	}

	protected void filtrarTabla() {
		
		String textoBusqueda = tfBuscar.getText().toLowerCase();
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0); // Limpiar la tabla

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
        for (Torneo tor : listaTorneos) {
        	
        	String ganadorOro = (tor.getIdGanadorOro() != 0) ? taekwondokaController.traerTaekwondokaById(tor.getIdGanadorOro()).getApellido() + " " + taekwondokaController.traerTaekwondokaById(tor.getIdGanadorOro()).getNombre() : "-";
        	String ganadorPlata = (tor.getIdGanadorPlata() != 0) ? taekwondokaController.traerTaekwondokaById(tor.getIdGanadorPlata()).getApellido() + " " + taekwondokaController.traerTaekwondokaById(tor.getIdGanadorPlata()).getNombre() : "-";
        	String ganadorBronce3 = (tor.getIdGanadorBronce3() != 0) ? taekwondokaController.traerTaekwondokaById(tor.getIdGanadorBronce3()).getApellido() + " " + taekwondokaController.traerTaekwondokaById(tor.getIdGanadorBronce3()).getNombre() : "-";
        	String ganadorBronce4 = (tor.getIdGanadorBronce4() != 0) ? taekwondokaController.traerTaekwondokaById(tor.getIdGanadorBronce4()).getApellido() + " " + taekwondokaController.traerTaekwondokaById(tor.getIdGanadorBronce4()).getNombre() : "-";
            // Filtrar por nombre, apellido o email (puedes ajustar según tus necesidades)
            if (tor.getNombre().toLowerCase().contains(textoBusqueda) ||
            	ganadorOro.toLowerCase().contains(textoBusqueda) ||
            	ganadorPlata.toLowerCase().contains(textoBusqueda) ||
           		ganadorBronce3.toLowerCase().contains(textoBusqueda) ||
           		ganadorBronce4.toLowerCase().contains(textoBusqueda) ||
            	formato.format(tor.getFecha()).contains(textoBusqueda)
                ) {
            	
                Object[] objeto = {
                		tor.getId(),
                		tor.getNombre(),
						formato.format(tor.getFecha()),
						tor.getParticipantes(),
						ganadorOro,
						ganadorPlata,
						ganadorBronce3,
						ganadorBronce4
						};
                modelo.addRow(objeto);
            }
        }
		
	}
	
	public void cargarTabla() {
		DefaultTableModel tablaModelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		String titulos[] = { "id", "Nombre", "Fecha", "Inscritos", "Oro", "Plata","Bronce", "Bronce"};
		tablaModelo.setColumnIdentifiers(titulos);
		
		if(ConexionMySQL.obtenerConexion() != null) {
			
			listaTorneos = controller.traerTorneos();
			
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
			
			if (listaTorneos != null) {
				for (Torneo tor : listaTorneos) {
					
					 String ganadorOro = (tor.getIdGanadorOro() != 0) ? taekwondokaController.traerTaekwondokaById(tor.getIdGanadorOro()).getApellido() + " " + taekwondokaController.traerTaekwondokaById(tor.getIdGanadorOro()).getNombre() : "-";
					 String ganadorPlata = (tor.getIdGanadorPlata() != 0) ? taekwondokaController.traerTaekwondokaById(tor.getIdGanadorPlata()).getApellido() + " " + taekwondokaController.traerTaekwondokaById(tor.getIdGanadorPlata()).getNombre() : "-";
					 String ganadorBronce3 = (tor.getIdGanadorBronce3() != 0) ? taekwondokaController.traerTaekwondokaById(tor.getIdGanadorBronce3()).getApellido() + " " + taekwondokaController.traerTaekwondokaById(tor.getIdGanadorBronce3()).getNombre() : "-";
					 String ganadorBronce4 = (tor.getIdGanadorBronce4() != 0) ? taekwondokaController.traerTaekwondokaById(tor.getIdGanadorBronce4()).getApellido() + " " + taekwondokaController.traerTaekwondokaById(tor.getIdGanadorBronce4()).getNombre() : "-";
					    
					Object[] objeto = {
							tor.getId(),
							tor.getNombre(),
							formato.format(tor.getFecha()),
							tor.getParticipantes(),
							ganadorOro,
							ganadorPlata,
							ganadorBronce3,
							ganadorBronce4
							};
					tablaModelo.addRow(objeto);
				}
			}

			table.setModel(tablaModelo);
		}

		

		// Obtén el modelo de columna de la tabla
		TableColumnModel columnModel = table.getColumnModel();

		// Ajusta el ancho predeterminado de las columnas
		int[] anchos = { 0, 60, 54, 64, 100, 100, 100, 100}; // Puedes ajustar los valores según tus necesidades

		for (int i = 0; i < columnModel.getColumnCount() && i < anchos.length; i++) {
			TableColumn column = columnModel.getColumn(i);
			column.setPreferredWidth(anchos[i]);
			column.setResizable(false);
		}
		
		table.getColumnModel().getColumn(3).setCellRenderer(new DerechaTableCellRenderer());
		TableColumn columna = table.getColumnModel().getColumn(0);
        table.getColumnModel().removeColumn(columna);

	}
	
	private class DerechaTableCellRenderer extends DefaultTableCellRenderer {
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	        // Verifica si la columna es la columna de "Inscritos"
	        if (column == 3) { // Ajusta el índice de la columna según tu modelo
	            setHorizontalAlignment(RIGHT); // Alinea el contenido a la derecha
	        }

	        return component;
	    }
	}


	protected void btnAtrasActionListener() {

		dispose();
        menu.setLocation(this.getX(), this.getY());
        menu.setVisible(true);
		
	}
}
