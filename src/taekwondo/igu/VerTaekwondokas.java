package taekwondo.igu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;

import java.util.List;
import java.util.Vector;

import taekwondo.logica.Taekwondoka;
import taekwondo.logica.TaekwondokaController;
import taekwondo.persistencia.ConexionMySQL;

import taekwondo.util.PintarPanel;
import taekwondo.util.XButtonOnTopBarListener;
import taekwondo.util.VentanaGenerica;
import taekwondo.util.Ventanas;

public class VerTaekwondokas extends JFrame implements VentanaGenerica {

	private TaekwondokaController taekwondokaController = null;
	private JTable tablaTaekwondokas;
	private Menu menu;
	private JTextField tfBuscar;
	private List<Taekwondoka> listaTaekwondokas;
	private int xMouse;
	private int yMouse;

	public VerTaekwondokas(Menu menu) {

		//Saves window menu to go back
		this.menu = menu;

		taekwondokaController = new TaekwondokaController();
		
		//on window opened load table
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
		
		//Panel all container
		JPanel panel = new JPanel();
		panel.setBackground(new Color(52, 73, 94));
		panel.setBounds(0, 39, 611, 377);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		//Back button
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBackground(new Color(41, 128, 185));
		btnAtras.setForeground(new Color(255, 255, 255));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAtrasActionListener();
			}
		});
		btnAtras.setFont(new Font("Arial", Font.PLAIN, 13));
		btnAtras.setBounds(5, 5, 89, 23);
		panel.add(btnAtras);

		//label for title
		JLabel lblListaDeTaekwondokas = new JLabel("Lista de Taekwondokas");
		lblListaDeTaekwondokas.setForeground(new Color(255, 255, 255));
		lblListaDeTaekwondokas.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeTaekwondokas.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblListaDeTaekwondokas.setBounds(168, 5, 274, 29);
		panel.add(lblListaDeTaekwondokas);
		
		//panel for search bar
		JPanel pnlBuscador = new JPanel();
		pnlBuscador.setBackground(new Color(52, 73, 94));
		pnlBuscador.setBounds(15, 39, 580, 24);
		panel.add(pnlBuscador);
		pnlBuscador.setLayout(null);
		
		//label for searh bar
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setForeground(new Color(255, 255, 255));
		lblBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
		lblBuscar.setBounds(10, 5, 57, 14);
		pnlBuscador.add(lblBuscar);
		
		//textfield for search bar
		tfBuscar = new JTextField();
		tfBuscar.setBorder(null);
		tfBuscar.setBackground(new Color(44, 62, 80));
		tfBuscar.setForeground(new Color(255, 255, 255));
		tfBuscar.setBounds(77, 3, 493, 18);
		pnlBuscador.add(tfBuscar);
		tfBuscar.setColumns(10);

		//panel for table
		JPanel pnlTable = new JPanel();
		pnlTable.setBounds(15, 74, 580, 243);
		panel.add(pnlTable);

		//table for athletes
		tablaTaekwondokas = new JTable();
		tablaTaekwondokas.setBounds(1, 1, 575, 0);
		tablaTaekwondokas.setBorder(null);
		tablaTaekwondokas.getTableHeader().setResizingAllowed(false);
		pnlTable.add(tablaTaekwondokas.getTableHeader(), BorderLayout.PAGE_START);
		pnlTable.add(tablaTaekwondokas, BorderLayout.CENTER);
		tablaTaekwondokas.getTableHeader().setReorderingAllowed(false);
		pnlTable.setLayout(null);
		tablaTaekwondokas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//scrollbar for table
		JScrollPane scrollPane = new JScrollPane(tablaTaekwondokas);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 580, 243);
		pnlTable.add(scrollPane);
		
		//panel for buttons
		JPanel pnlBotones = new JPanel();
		pnlBotones.setBackground(new Color(52, 73, 94));
		pnlBotones.setBounds(149, 326, 313, 40);
		panel.add(pnlBotones);
		pnlBotones.setLayout(null);

		//button to see details of the selected athlete
		JButton btnVerDetalles = new JButton("Ver Detalles");
		btnVerDetalles.setBackground(new Color(41, 128, 185));
		btnVerDetalles.setForeground(new Color(255, 255, 255));
		btnVerDetalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnVerDetallesActionListener();
			}
		});
		btnVerDetalles.setFont(new Font("Arial", Font.PLAIN, 16));
		btnVerDetalles.setBounds(10, 5, 140, 30);
		pnlBotones.add(btnVerDetalles);

		//Button to delete selected athlete
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(41, 128, 185));
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEliminarActinListener();
			}
		});
		btnEliminar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnEliminar.setBounds(160, 5, 140, 30);
		pnlBotones.add(btnEliminar);
		
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
		
		//table filter
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
	//delete athlete
	protected void btnEliminarActinListener() {
		
		if (ConexionMySQL.obtenerConexion() != null) {
			int filaSeleccionada = tablaTaekwondokas.getSelectedRow();
			DefaultTableModel modelo = (DefaultTableModel) tablaTaekwondokas.getModel();
			if(filaSeleccionada != -1) {
				int idTae = (int) modelo.getValueAt(filaSeleccionada, 0);
				System.out.println(idTae);
				
				Taekwondoka tae = taekwondokaController.traerTaekwondokaById(idTae);
				if(tae != null) {
					if(taekwondokaController.eliminarTaekwondoka(tae.getId())) {
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

	//filter for search bar
	protected void filtrarTabla() {
		
		String textoBusqueda = tfBuscar.getText().toLowerCase();
        DefaultTableModel modelo = (DefaultTableModel) tablaTaekwondokas.getModel();
        modelo.setRowCount(0);

        for (Taekwondoka tae : listaTaekwondokas) {
            if (tae.getNombre().toLowerCase().contains(textoBusqueda) ||
                tae.getApellido().toLowerCase().contains(textoBusqueda) ||
                tae.getEmail().toLowerCase().contains(textoBusqueda) ||
                tae.getCinturon().toLowerCase().contains(textoBusqueda) ||
                tae.getPunta().toLowerCase().contains(textoBusqueda)
                ) {
                Object[] objeto = { tae.getId(), tae.getApellido(), tae.getNombre(), tae.getEmail(), tae.getCinturon() + "," + tae.getPunta() };
                modelo.addRow(objeto);
            }
        }
		
	}

	//load table
	@Override
	public void cargarTabla() {
		DefaultTableModel tablaModelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		String titulos[] = { "id", "Apellido", "Nombre", "E-mail", "Cinturon Punta" };
		tablaModelo.setColumnIdentifiers(titulos);
		
		if(ConexionMySQL.obtenerConexion() != null) {
			
			listaTaekwondokas = taekwondokaController.traerTaekwondokas();
			
			if (listaTaekwondokas != null) {
				for (Taekwondoka tae : listaTaekwondokas) {
					Object[] objeto = { tae.getId(), tae.getApellido(), tae.getNombre(), tae.getEmail(),
							tae.getCinturon() + "," + tae.getPunta() };
					tablaModelo.addRow(objeto);
				}
			}

			tablaTaekwondokas.setModel(tablaModelo);
		}

		

		TableColumnModel columnModel = tablaTaekwondokas.getColumnModel();

		int[] anchos = { 0, 150, 150, 180, 98 };
		for (int i = 0; i < columnModel.getColumnCount() && i < anchos.length; i++) {
			TableColumn column = columnModel.getColumn(i);
			column.setPreferredWidth(anchos[i]);
			column.setResizable(false);
		}

		tablaTaekwondokas.setDefaultRenderer(Object.class, new ColorCellRenderer());
		TableColumn columna = tablaTaekwondokas.getColumnModel().getColumn(0);
		tablaTaekwondokas.getColumnModel().removeColumn(columna);
		

	}

	//cell renderer to see the actual colors of the belt and tip
	private class ColorCellRenderer extends DefaultTableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
					column);

			if (column == table.getColumnCount() - 1 && value instanceof String) {
				String[] colores = ((String) value).split(",");

				if (colores.length == 2) {

					DefaultTableModel model = (DefaultTableModel) table.getModel();

					Vector<Object> rowDataVector = (Vector<Object>) model.getDataVector().elementAt(row);
					
					Object[] rowData = rowDataVector.toArray();
					
					String[] colors = rowData[model.getColumnCount() - 1].toString().split(",");

					

					return PintarPanel.crearColorPanel(colors[0], colors[1], 80, 18);
				}
			}
			
			return cellComponent;
		}

	}

	//back button
	private void btnAtrasActionListener() {
		dispose();
		menu.setLocation(this.getX(), this.getY());
		menu.setVisible(true);
	}
	
	//see details button
	private void btnVerDetallesActionListener() {
		if (ConexionMySQL.obtenerConexion() != null) {
			
			int filaSeleccionada = tablaTaekwondokas.getSelectedRow();
			DefaultTableModel modelo = (DefaultTableModel) tablaTaekwondokas.getModel();
			if(filaSeleccionada != -1) {
				int taeId = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString());
				System.out.println("id del tae: "+taeId);
				
				Taekwondoka tae = taekwondokaController.traerTaekwondokaById(taeId);
				if(tae != null) {
					VerDetallesTaekwondoka VVDT = new VerDetallesTaekwondoka(this, tae);
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
}
