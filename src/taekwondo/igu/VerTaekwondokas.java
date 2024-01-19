package taekwondo.igu;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

import taekwondo.logica.Taekwondoka;
import taekwondo.logica.TaekwondokaController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerTaekwondokas extends JFrame {

    private TaekwondokaController taekwondokaController = null;
    private JTable tablaTaekwondokas;
    private Menu menu;

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
        panel_1.setBounds(7, 84, 580, 282);
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
        scrollPane.setBounds(0, 0, 580, 282);
        panel_1.add(scrollPane);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(7, 34, 578, 45);
        panel.add(panel_2);
        panel_2.setLayout(null);
        
        JButton btnVerDetalles = new JButton("Ver Detalles de Taekwondoka");
        btnVerDetalles.setFont(new Font("Arial", Font.PLAIN, 13));
        btnVerDetalles.setBounds(5, 11, 210, 23);
        panel_2.add(btnVerDetalles);
        
        JButton btnNewButton_1 = new JButton("Eliminar Taekwondoka");
        btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 13));
        btnNewButton_1.setBounds(225, 11, 170, 23);
        panel_2.add(btnNewButton_1);
        
        JButton btnNewButton_1_1 = new JButton("Editar Taekwondoka");
        btnNewButton_1_1.setFont(new Font("Arial", Font.PLAIN, 13));
        btnNewButton_1_1.setBounds(405, 11, 163, 23);
        panel_2.add(btnNewButton_1_1);
        
        JButton btnNewButton_1_1_1 = new JButton("Atras");
        btnNewButton_1_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnAtrasActionListener();
        	}
        });
        btnNewButton_1_1_1.setFont(new Font("Arial", Font.PLAIN, 13));
        btnNewButton_1_1_1.setBounds(7, 5, 89, 23);
        panel.add(btnNewButton_1_1_1);

    }

    private void cargarTabla() {
        DefaultTableModel tablaModelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String titulos[] = {"Apellido", "Nombre", "E-mail", ""};
        tablaModelo.setColumnIdentifiers(titulos);

        List<Taekwondoka> listaTaekwondokas = taekwondokaController.traerTaekwondokas();

        if (listaTaekwondokas != null) {
            for (Taekwondoka tae : listaTaekwondokas) {
                Object[] objeto = {tae.getApellido(), tae.getNombre(), tae.getEmail(), tae.getCinturon()+","+tae.getPunta()};
                tablaModelo.addRow(objeto);
            }
        }

        tablaTaekwondokas.setModel(tablaModelo);

        // Obtén el modelo de columna de la tabla
        TableColumnModel columnModel = tablaTaekwondokas.getColumnModel();

        // Ajusta el ancho predeterminado de las columnas
        int[] anchos = {150, 150, 180, 98}; // Puedes ajustar los valores según tus necesidades

        for (int i = 0; i < columnModel.getColumnCount() && i < anchos.length; i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(anchos[i]);
            column.setResizable(false);
        }
        
        tablaTaekwondokas.setDefaultRenderer(Object.class, new ColorCellRenderer());

    }
    
    private class ColorCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {

            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Solo aplica la personalización a la última columna
            if (column == table.getColumnCount() - 1 && value instanceof String) {
                String[] colores = ((String) value).split(",");

                if (colores.length == 2) {
                    int anchoPrimero = 80; // Ancho del primer color
                    int anchoSegundo = 18; // Ancho del segundo color

                    // Obtén el modelo de la tabla
                    DefaultTableModel model = (DefaultTableModel) table.getModel();

                    // Obtén los datos específicos de la fila actual
                    Vector<Object> rowDataVector = (Vector<Object>) model.getDataVector().elementAt(row);

                    // Convierte el vector de datos a un array de objetos
                    Object[] rowData = rowDataVector.toArray();

                    // Suponiendo que los colores están en la última columna de tu modelo
                    String[] colors = rowData[model.getColumnCount() - 1].toString().split(",");

                    // Mapea los nombres de colores a valores de Color
                    Color mappedColor1 = mapColor(colors[0].trim());
                    Color mappedColor2 = mapColor(colors[1].trim());

                    // Crea un panel para personalizar la representación visual
                    JPanel colorPanel = new JPanel();
                    colorPanel.setLayout(new BorderLayout());

                    // Crea un rectángulo para el primer color
                    JPanel firstColorPanel = new JPanel();
                    firstColorPanel.setBackground(mappedColor1);
                    // System.out.println("mappedColor1: " + mappedColor1);
                    firstColorPanel.setPreferredSize(new Dimension(anchoPrimero, cellComponent.getHeight()));

                    // Crea un rectángulo para el segundo color
                    JPanel secondColorPanel = new JPanel();
                    secondColorPanel.setBackground(mappedColor2);
                    // System.out.println("mappedColor2: " + mappedColor2);
                    secondColorPanel.setPreferredSize(new Dimension(anchoSegundo, cellComponent.getHeight()));

                    // Agrega los rectángulos al panel principal
                    colorPanel.add(firstColorPanel, BorderLayout.WEST);
                    colorPanel.add(secondColorPanel, BorderLayout.EAST);

                    return colorPanel;
                }
            }

            // Si no es la última columna o no hay dos colores, devuelve la representación por defecto
            return cellComponent;
        }
        
        private Color mapColor(String colorName) {
            switch (colorName.toLowerCase()) {
                case "blanco":
                    return Color.WHITE;
                case "amarillo":
                    return Color.YELLOW;
                case "verde":
                    return Color.GREEN;
                case "azul":
                    return Color.BLUE;
                case "rojo":
                    return Color.RED;
                // Agrega más casos según necesites
                default:
                    return Color.BLACK; // Color por defecto en caso de no coincidir
            }
        }
    }
    
    private void btnAtrasActionListener() {
        dispose();
        menu.setLocation(this.getX(), this.getY());
        menu.setVisible(true);
    }
}
