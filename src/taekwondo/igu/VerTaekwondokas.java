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

import taekwondo.logica.Taekwondoka;
import taekwondo.logica.TaekwondokaController;
import taekwondo.persistencia.ConexionMySQL;
import taekwondo.util.PintarPanel;
import taekwondo.util.Ventanas;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerTaekwondokas extends JFrame {

	private TaekwondokaController taekwondokaController = null;
	private JTable tablaTaekwondokas;
	private Menu menu;
	private JTextField tfBuscar;
	private List<Taekwondoka> listaTaekwondokas;

	public VerTaekwondokas(Menu menu) {

		this.menu = menu;

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

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 595, 377);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblListaDeTaekwondokas = new JLabel("Lista de Taekwondokas");
		lblListaDeTaekwondokas.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeTaekwondokas.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblListaDeTaekwondokas.setBounds(160, 5, 274, 29);
		panel.add(lblListaDeTaekwondokas);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(7, 74, 580, 243);
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
		scrollPane.setBounds(0, 0, 580, 243);
		panel_1.add(scrollPane);

		JPanel pnlBotones = new JPanel();
		pnlBotones.setBounds(65, 326, 464, 40);
		panel.add(pnlBotones);
		pnlBotones.setLayout(null);

		JButton btnVerDetalles = new JButton("Ver Detalles");
		btnVerDetalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnVerDetallesActionListener();
			}
		});
		btnVerDetalles.setFont(new Font("Arial", Font.PLAIN, 16));
		btnVerDetalles.setBounds(10, 5, 140, 30);
		pnlBotones.add(btnVerDetalles);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnEliminar.setBounds(310, 5, 140, 30);
		pnlBotones.add(btnEliminar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnEditar.setBounds(160, 5, 140, 30);
		pnlBotones.add(btnEditar);

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAtrasActionListener();
			}
		});
		btnAtras.setFont(new Font("Arial", Font.PLAIN, 13));
		btnAtras.setBounds(7, 5, 89, 23);
		panel.add(btnAtras);
		
		JPanel pnlBuscador = new JPanel();
		pnlBuscador.setBounds(7, 39, 580, 24);
		panel.add(pnlBuscador);
		pnlBuscador.setLayout(null);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
		lblBuscar.setBounds(10, 5, 57, 14);
		pnlBuscador.add(lblBuscar);
		
		tfBuscar = new JTextField();
		tfBuscar.setBounds(77, 3, 493, 18);
		pnlBuscador.add(tfBuscar);
		tfBuscar.setColumns(10);
		
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
            	
            	System.out.println("Cinturon: "+tae.getCinturon().toLowerCase());
            	System.out.println("Punta: "+tae.getPunta().toLowerCase());
                Object[] objeto = { tae.getApellido(), tae.getNombre(), tae.getEmail(), tae.getCinturon() + "," + tae.getPunta() };
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

		String titulos[] = { "Apellido", "Nombre", "E-mail", "Cinturon Punta" };
		tablaModelo.setColumnIdentifiers(titulos);
		
		if(ConexionMySQL.obtenerConexion() != null) {
			
			listaTaekwondokas = taekwondokaController.traerTaekwondokas();
			
			if (listaTaekwondokas != null) {
				for (Taekwondoka tae : listaTaekwondokas) {
					Object[] objeto = { tae.getApellido(), tae.getNombre(), tae.getEmail(),
							tae.getCinturon() + "," + tae.getPunta() };
					tablaModelo.addRow(objeto);
				}
			}

			tablaTaekwondokas.setModel(tablaModelo);
		}

		

		// Obtén el modelo de columna de la tabla
		TableColumnModel columnModel = tablaTaekwondokas.getColumnModel();

		// Ajusta el ancho predeterminado de las columnas
		int[] anchos = { 150, 150, 180, 98 }; // Puedes ajustar los valores según tus necesidades

		for (int i = 0; i < columnModel.getColumnCount() && i < anchos.length; i++) {
			TableColumn column = columnModel.getColumn(i);
			column.setPreferredWidth(anchos[i]);
			column.setResizable(false);
		}

		tablaTaekwondokas.setDefaultRenderer(Object.class, new ColorCellRenderer());

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
		menu.setLocation(this.getX(), this.getY());
		menu.setVisible(true);
	}
	
	private void btnVerDetallesActionListener() {
		if (ConexionMySQL.obtenerConexion() != null) {
			int filaSeleccionada = tablaTaekwondokas.getSelectedRow();
			DefaultTableModel modelo = (DefaultTableModel) tablaTaekwondokas.getModel();
			if(filaSeleccionada != -1) {
				String TaekwondokaMail = modelo.getValueAt(filaSeleccionada, 2).toString();
				//System.out.println(TaekwondokaMail);
				
				// buscar todos los datos del Taekwondoka (mail e id son unicos)
				Taekwondoka tae = taekwondokaController.traerTaekwondokaByMail(TaekwondokaMail);
				if(tae != null) {
					VerDetallesTaekwondoka VVDT = new VerDetallesTaekwondoka(this, tae);
		            VVDT.setLocation(this.getX(), this.getY());
		            dispose();
		            VVDT.setVisible(true);
				} else {
					Ventanas.mostrarError("Hubo un problema y no se encontro el Taekwondoka.");
				}
			}
        } else {
            Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
        }
	}
}
