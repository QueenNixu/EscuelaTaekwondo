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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import taekwondo.logica.Taekwondoka;
import taekwondo.logica.Torneo;
import taekwondo.logica.TorneoController;
import taekwondo.persistencia.ConexionMySQL;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

public class VerTorneos extends JFrame {
	
	private Menu menu;
	private JTextField textField;
	private JTable table;
	private TorneoController controller = new TorneoController();
	private List<Torneo> listaTorneos;

	
	public VerTorneos(Menu menu) {
		
		this.menu = menu;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				cargarTabla();
			}
		});
		
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
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(77, 3, 493, 18);
        pnlBuscador.add(textField);
        
	}
	
	public void cargarTabla() {
		DefaultTableModel tablaModelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		String titulos[] = { "Nombre", "Fecha", "Inscritos", "Oro", "Plata","Bronce", "Bronce"};
		tablaModelo.setColumnIdentifiers(titulos);
		
		if(ConexionMySQL.obtenerConexion() != null) {
			
			listaTorneos = controller.traerTorneos();
			
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
			
			if (listaTorneos != null) {
				for (Torneo tor : listaTorneos) {
					Object[] objeto = { tor.getNombre(),
							formato.format(tor.getFecha()),
							tor.getParticipantes(),
							"Reategui Agustin",
							"Reategui Agustin",
							"Reategui Agustin",
							"Reategui Agustin"};
					tablaModelo.addRow(objeto);
				}
			}

			table.setModel(tablaModelo);
		}

		

		// Obtén el modelo de columna de la tabla
		TableColumnModel columnModel = table.getColumnModel();

		// Ajusta el ancho predeterminado de las columnas
		int[] anchos = { 60, 55, 65, 100, 100, 100, 100}; // Puedes ajustar los valores según tus necesidades

		for (int i = 0; i < columnModel.getColumnCount() && i < anchos.length; i++) {
			TableColumn column = columnModel.getColumn(i);
			column.setPreferredWidth(anchos[i]);
			column.setResizable(false);
		}
		
		table.getColumnModel().getColumn(2).setCellRenderer(new DerechaTableCellRenderer());

	}
	
	private class DerechaTableCellRenderer extends DefaultTableCellRenderer {
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	        // Verifica si la columna es la columna de "Inscritos"
	        if (column == 2) { // Ajusta el índice de la columna según tu modelo
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