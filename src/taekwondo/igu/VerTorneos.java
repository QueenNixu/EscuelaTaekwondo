package taekwondo.igu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import java.awt.Component;

import java.text.SimpleDateFormat;

import java.util.List;

import java.awt.event.ActionEvent;

import taekwondo.logica.TaekwondokaController;
import taekwondo.logica.Torneo;
import taekwondo.logica.TorneoController;

import taekwondo.persistencia.ConexionMySQL;

import taekwondo.util.Ventanas;
import java.awt.Color;

public class VerTorneos extends JFrame {
	
	private Menu menu;
	private JTextField tfBuscar;
	private JTable table;
	private TorneoController controller = new TorneoController();
	private List<Torneo> listaTorneos;
	private TaekwondokaController taekwondokaController = new TaekwondokaController();
	private TorneoController torneoController = new TorneoController();

	
	public VerTorneos(Menu menu) {
		getContentPane().setBackground(new Color(52, 73, 94));

		//Saves window to go back to
		this.menu = menu;
		
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
        btnSalir.setBounds(5, 54, 89, 23);
        getContentPane().add(btnSalir);
        
        //Label titulo
        JLabel lblTitulo = new JLabel("Lista de Torneos");
        lblTitulo.setForeground(new Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblTitulo.setBounds(168, 54, 274, 29);
        getContentPane().add(lblTitulo);
        
        //Panel for search bar
        JPanel pnlBuscador = new JPanel();
        pnlBuscador.setBackground(new Color(52, 73, 94));
        pnlBuscador.setLayout(null);
        pnlBuscador.setBounds(18, 87, 580, 24);
        getContentPane().add(pnlBuscador);
        
        //Label for search bar
        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setForeground(new Color(255, 255, 255));
        lblBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
        lblBuscar.setBounds(10, 5, 57, 14);
        pnlBuscador.add(lblBuscar);
        
        //Textfield for search bar
        tfBuscar = new JTextField();
        tfBuscar.setBackground(new Color(44, 62, 80));
        tfBuscar.setForeground(new Color(255, 255, 255));
        tfBuscar.setColumns(10);
        tfBuscar.setBounds(77, 3, 493, 18);
        pnlBuscador.add(tfBuscar);
        
        //Panel table container
        JPanel pnlTableContainer = new JPanel();
        pnlTableContainer.setLayout(null);
        pnlTableContainer.setBounds(18, 122, 580, 243);
        getContentPane().add(pnlTableContainer);
        
        //table
		table = new JTable();
		table.setBounds(1, 1, 575, 0);
		table.setBorder(null);
		table.getTableHeader().setResizingAllowed(false);
		pnlTableContainer.add(table.getTableHeader(), BorderLayout.PAGE_START);
		pnlTableContainer.add(table, BorderLayout.CENTER);
		table.getTableHeader().setReorderingAllowed(false);
		pnlTableContainer.setLayout(null);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//scrollbar for table
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 580, 243);
		pnlTableContainer.add(scrollPane);
		
		//Panel for buttons
        JPanel pnlBotones = new JPanel();
        pnlBotones.setBackground(new Color(52, 73, 94));
        pnlBotones.setLayout(null);
        pnlBotones.setBounds(138, 370, 313, 40);
        getContentPane().add(pnlBotones);
        
        //see details buttons
        JButton btnVerDetalles = new JButton("Ver Detalles");
        btnVerDetalles.setBackground(new Color(41, 128, 185));
        btnVerDetalles.setForeground(new Color(255, 255, 255));
        btnVerDetalles.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnVerDeatellesActionListener();
        	}
        });
        btnVerDetalles.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVerDetalles.setBounds(10, 5, 140, 30);
        pnlBotones.add(btnVerDetalles);
        
        //Delete button
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(41, 128, 185));
        btnEliminar.setForeground(new Color(255, 255, 255));
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnEliminarActionListener();
        	}
        });
        btnEliminar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnEliminar.setBounds(160, 5, 140, 30);
        pnlBotones.add(btnEliminar);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(44, 62, 80));
        panel_1.setBounds(0, 0, 611, 40);
        getContentPane().add(panel_1);
        
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
	
	//delete
	protected void btnEliminarActionListener() {
		
		if (ConexionMySQL.obtenerConexion() != null) {
			int filaSeleccionada = table.getSelectedRow();
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			if(filaSeleccionada != -1) {
				int idTor = (int) modelo.getValueAt(filaSeleccionada, 0);

				Torneo tor = torneoController.traerTorneoById(idTor);
				if(tor != null) {
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

	//see details
	protected void btnVerDeatellesActionListener() {
		
		if (ConexionMySQL.obtenerConexion() != null) {
			
			int filaSeleccionada = table.getSelectedRow();
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			if(filaSeleccionada != -1) {
				int torId = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString());
				System.out.println("id del tor: "+torId);
				
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

	//filter table
	protected void filtrarTabla() {
		
		String textoBusqueda = tfBuscar.getText().toLowerCase();
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
        for (Torneo tor : listaTorneos) {
        	
        	String ganadorOro = (tor.getIdGanadorOro() != 0) ? taekwondokaController.traerTaekwondokaById(tor.getIdGanadorOro()).getApellido() + " " + taekwondokaController.traerTaekwondokaById(tor.getIdGanadorOro()).getNombre() : "-";
        	String ganadorPlata = (tor.getIdGanadorPlata() != 0) ? taekwondokaController.traerTaekwondokaById(tor.getIdGanadorPlata()).getApellido() + " " + taekwondokaController.traerTaekwondokaById(tor.getIdGanadorPlata()).getNombre() : "-";
        	String ganadorBronce3 = (tor.getIdGanadorBronce3() != 0) ? taekwondokaController.traerTaekwondokaById(tor.getIdGanadorBronce3()).getApellido() + " " + taekwondokaController.traerTaekwondokaById(tor.getIdGanadorBronce3()).getNombre() : "-";
        	String ganadorBronce4 = (tor.getIdGanadorBronce4() != 0) ? taekwondokaController.traerTaekwondokaById(tor.getIdGanadorBronce4()).getApellido() + " " + taekwondokaController.traerTaekwondokaById(tor.getIdGanadorBronce4()).getNombre() : "-";
        	
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
	
	//load table
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

		TableColumnModel columnModel = table.getColumnModel();

		int[] anchos = { 0, 60, 54, 64, 100, 100, 100, 100};

		for (int i = 0; i < columnModel.getColumnCount() && i < anchos.length; i++) {
			TableColumn column = columnModel.getColumn(i);
			column.setPreferredWidth(anchos[i]);
			column.setResizable(false);
		}
		
		table.getColumnModel().getColumn(3).setCellRenderer(new DerechaTableCellRenderer());
		TableColumn columna = table.getColumnModel().getColumn(0);
        table.getColumnModel().removeColumn(columna);

	}
	
	//cell renderer to set the allignement of the signed athlete's to the right
	private class DerechaTableCellRenderer extends DefaultTableCellRenderer {
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	        setHorizontalAlignment(RIGHT);
	        
	        return component;
	    }
	}


	//back
	protected void btnAtrasActionListener() {

		dispose();
        menu.setLocation(this.getX(), this.getY());
        menu.setVisible(true);
		
	}
}
