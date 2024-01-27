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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import taekwondo.logica.Taekwondoka;
import taekwondo.logica.TaekwondokaController;
import taekwondo.logica.Torneo;
import taekwondo.persistencia.ConexionMySQL;
import taekwondo.util.PintarPanel;
import taekwondo.util.VentanaGenerica;
import taekwondo.util.Ventanas;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InscribirTaekwondokaTorneo extends JFrame implements VentanaGenerica {

	private TaekwondokaController taekwondokaController = null;
	private JTable tablaTaekwondokas;
	private VerDetallesTorneo vvdt;
	private JTextField tfBuscar;
	private List<Taekwondoka> listaTaekwondokas;
	private Torneo tor;

	public InscribirTaekwondokaTorneo(VerDetallesTorneo vvdt, Torneo tor) {

		this.vvdt = vvdt;
		this.tor = tor;

		taekwondokaController = new TaekwondokaController();

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
		setUndecorated(true);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(52, 73, 94));
		panel.setBounds(0, 39, 611, 377);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblListaDeTaekwondokas = new JLabel("<html><div style='text-align: center;'>Lista de Taekwondokas<br>no inscriptos<div><html>");
		lblListaDeTaekwondokas.setBackground(new Color(52, 73, 94));
		lblListaDeTaekwondokas.setForeground(new Color(255, 255, 255));
		lblListaDeTaekwondokas.setVerticalAlignment(SwingConstants.TOP);
		lblListaDeTaekwondokas.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeTaekwondokas.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblListaDeTaekwondokas.setBounds(122, 5, 367, 60);
		panel.add(lblListaDeTaekwondokas);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(15, 111, 580, 206);
		panel.add(panel_1);

		tablaTaekwondokas = new JTable();
		tablaTaekwondokas.setBounds(1, 1, 575, 0);
		tablaTaekwondokas.setBorder(null);
		tablaTaekwondokas.getTableHeader().setResizingAllowed(false);
		panel_1.add(tablaTaekwondokas.getTableHeader(), BorderLayout.PAGE_START);
		panel_1.add(tablaTaekwondokas, BorderLayout.CENTER);
		tablaTaekwondokas.getTableHeader().setReorderingAllowed(false);
		panel_1.setLayout(null);
		tablaTaekwondokas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// Agrega el JScrollPane envolviendo la tabla
		JScrollPane scrollPane = new JScrollPane(tablaTaekwondokas);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 580, 206);
		panel_1.add(scrollPane);

		JPanel pnlBotones = new JPanel();
		pnlBotones.setBackground(new Color(52, 73, 94));
		pnlBotones.setBounds(149, 326, 313, 40);
		panel.add(pnlBotones);
		pnlBotones.setLayout(null);

		JButton btnVerDetalles = new JButton("Ver Detalles");
		btnVerDetalles.setForeground(new Color(255, 255, 255));
		btnVerDetalles.setBackground(new Color(41, 128, 185));
		btnVerDetalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnVerDetallesActionListener();
			}
		});
		btnVerDetalles.setFont(new Font("Arial", Font.PLAIN, 16));
		btnVerDetalles.setBounds(163, 5, 140, 30);
		pnlBotones.add(btnVerDetalles);
		
		JButton btnInscribir = new JButton("Inscribir");
		btnInscribir.setForeground(new Color(255, 255, 255));
		btnInscribir.setBackground(new Color(41, 128, 185));
		btnInscribir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnInscribirActionListener();
			}
		});
		btnInscribir.setFont(new Font("Arial", Font.PLAIN, 16));
		btnInscribir.setBounds(10, 5, 140, 30);
		pnlBotones.add(btnInscribir);

		JButton btnAtras = new JButton("Atras");
		btnAtras.setForeground(new Color(255, 255, 255));
		btnAtras.setBackground(new Color(41, 128, 185));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAtrasActionListener();
			}
		});
		btnAtras.setFont(new Font("Arial", Font.PLAIN, 13));
		btnAtras.setBounds(5, 5, 89, 23);
		panel.add(btnAtras);
		
		JPanel pnlBuscador = new JPanel();
		pnlBuscador.setBackground(new Color(52, 73, 94));
		pnlBuscador.setBounds(15, 76, 580, 24);
		panel.add(pnlBuscador);
		pnlBuscador.setLayout(null);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setForeground(new Color(255, 255, 255));
		lblBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
		lblBuscar.setBounds(10, 5, 57, 14);
		pnlBuscador.add(lblBuscar);
		
		tfBuscar = new JTextField();
		tfBuscar.setForeground(new Color(255, 255, 255));
		tfBuscar.setBackground(new Color(44, 62, 80));
		tfBuscar.setBounds(77, 3, 493, 18);
		pnlBuscador.add(tfBuscar);
		tfBuscar.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(44, 62, 80));
		panel_2.setBounds(0, 0, 611, 40);
		getContentPane().add(panel_2);
		
		// Agregar un DocumentListener al JTextField para manejar cambios en el texto
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

	protected void btnInscribirActionListener() {
		
		if (ConexionMySQL.obtenerConexion() != null) {
			
			int filaSeleccionada = tablaTaekwondokas.getSelectedRow();
			DefaultTableModel modelo = (DefaultTableModel) tablaTaekwondokas.getModel();
			if(filaSeleccionada != -1) {
				int taeId = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString());
				System.out.println("id del tae: "+taeId);
				
				// buscar todos los datos del Taekwondoka (mail e id son unicos)
				Taekwondoka tae = taekwondokaController.traerTaekwondokaById(taeId);
				if(tae != null) {
					if(taekwondokaController.inscribirTaeTor(taeId, tor.getId())){
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

	protected void filtrarTabla() {
		
		String textoBusqueda = tfBuscar.getText().toLowerCase();
        DefaultTableModel modelo = (DefaultTableModel) tablaTaekwondokas.getModel();
        modelo.setRowCount(0); // Limpiar la tabla

        for (Taekwondoka tae : listaTaekwondokas) {
            // Filtrar por nombre, apellido o email (puedes ajustar según tus necesidades)
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
			
			List<Taekwondoka> listaInscriptos = taekwondokaController.traerInscriptos(tor.getId());
			List<Integer> listaInscriptosId = listaInscriptos.stream().map(Taekwondoka::getId).collect(Collectors.toList());
			
			if (listaTaekwondokas != null) {
				for (Taekwondoka tae : listaTaekwondokas) {
					if(!listaInscriptosId.contains(tae.getId())) {
						Object[] objeto = { tae.getId(), tae.getApellido(), tae.getNombre(), tae.getEmail(),
								tae.getCinturon() + "," + tae.getPunta() };
						tablaModelo.addRow(objeto);
					}
				}
			}

			tablaTaekwondokas.setModel(tablaModelo);
		}

		

		// Obtén el modelo de columna de la tabla
		TableColumnModel columnModel = tablaTaekwondokas.getColumnModel();

		// Ajusta el ancho predeterminado de las columnas
		int[] anchos = { 0, 150, 150, 180, 98 }; // Puedes ajustar los valores según tus necesidades

		for (int i = 0; i < columnModel.getColumnCount() && i < anchos.length; i++) {
			TableColumn column = columnModel.getColumn(i);
			column.setPreferredWidth(anchos[i]);
			column.setResizable(false);
		}

		tablaTaekwondokas.setDefaultRenderer(Object.class, new ColorCellRenderer());
		TableColumn columna = tablaTaekwondokas.getColumnModel().getColumn(0);
		tablaTaekwondokas.getColumnModel().removeColumn(columna);
		

	}

	private class ColorCellRenderer extends DefaultTableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
					column);

			// Solo aplica la personalización a la última columna
			if (column == table.getColumnCount() - 1 && value instanceof String) {
				String[] colores = ((String) value).split(",");

				if (colores.length == 2) {

					// Obtén el modelo de la tabla
					DefaultTableModel model = (DefaultTableModel) table.getModel();

					// Obtén los datos específicos de la fila actual
					Vector<Object> rowDataVector = (Vector<Object>) model.getDataVector().elementAt(row);

					// Convierte el vector de datos a un array de objetos
					Object[] rowData = rowDataVector.toArray();

					// Suponiendo que los colores están en la última columna de tu modelo
					String[] colors = rowData[model.getColumnCount() - 1].toString().split(",");

					

					return PintarPanel.crearColorPanel(colors[0], colors[1], 80, 18);
				}
			}

			// Si no es la última columna o no hay dos colores, devuelve la representación
			// por defecto
			return cellComponent;
		}

	}

	private void btnAtrasActionListener() {
		dispose();
		vvdt.cargarTabla();
		vvdt.actualizarParticipantes();
		vvdt.setLocation(this.getX(), this.getY());
		vvdt.setVisible(true);
	}
	
	private void btnVerDetallesActionListener() {
		
		System.out.println("UWU");
		if (ConexionMySQL.obtenerConexion() != null) {
			
			int filaSeleccionada = tablaTaekwondokas.getSelectedRow();
			DefaultTableModel modelo = (DefaultTableModel) tablaTaekwondokas.getModel();
			if(filaSeleccionada != -1) {
				int taeId = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString());
				System.out.println("id del tae: "+taeId);
				
				// buscar todos los datos del Taekwondoka (mail e id son unicos)
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
