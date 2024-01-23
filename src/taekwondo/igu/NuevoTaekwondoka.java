package taekwondo.igu;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import taekwondo.logica.Taekwondoka;
import taekwondo.logica.TaekwondokaController;
import taekwondo.persistencia.ConexionMySQL;
import taekwondo.util.FiltrosParaTextField;
import taekwondo.util.Ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class NuevoTaekwondoka extends JFrame {
	
    private JTextField tfNombre;
    private JTextField tfApellido;
    private JTextField tfCelular;
    private JTextField tfDireccion;
    private JTextField tfEmail;
    private JTextField tfEdad;
    private JComboBox<Color> cbCinturon;
    private JComboBox<Color> cbPunta;
    
    private Menu menu;
    
    private TaekwondokaController controller = new TaekwondokaController();

    public NuevoTaekwondoka(Menu menu) {
        this.menu = menu;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 611, 416);
        setResizable(false);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 595, 377);
        getContentPane().add(panel);

        JLabel lblTitulo = new JLabel("Nuevo Taekwondoka");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblTitulo.setBounds(160, 5, 274, 29);
        panel.add(lblTitulo);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBounds(10, 45, 575, 321);
        panel.add(panel_1);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnGuardarActionListener();
        	}
        });
        btnGuardar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnGuardar.setBounds(109, 275, 170, 35);
        panel_1.add(btnGuardar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnLimpiarActionListener();
            }
        });
        btnLimpiar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnLimpiar.setBounds(299, 275, 170, 35);
        panel_1.add(btnLimpiar);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNombre.setBounds(10, 10, 126, 16);
        panel_1.add(lblNombre);

        tfNombre = new JTextField();
        tfNombre.setBounds(146, 9, 419, 20);
        panel_1.add(tfNombre);
        tfNombre.setColumns(10);

        tfApellido = new JTextField();
        tfApellido.setColumns(10);
        tfApellido.setBounds(146, 37, 419, 20);
        panel_1.add(tfApellido);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setFont(new Font("Arial", Font.PLAIN, 15));
        lblApellido.setBounds(10, 38, 126, 16);
        panel_1.add(lblApellido);

        tfCelular = new JTextField();
        tfCelular.setColumns(10);
        tfCelular.setBounds(146, 161, 419, 20);
        panel_1.add(tfCelular);

        JLabel lblCelular = new JLabel("Celular:");
        lblCelular.setToolTipText("asd");
        lblCelular.setFont(new Font("Arial", Font.PLAIN, 15));
        lblCelular.setBounds(10, 162, 126, 16);
        panel_1.add(lblCelular);

        tfDireccion = new JTextField();
        tfDireccion.setColumns(10);
        tfDireccion.setBounds(146, 99, 419, 20);
        panel_1.add(tfDireccion);

        JLabel lblDireccion = new JLabel("Direccion:");
        lblDireccion.setFont(new Font("Arial", Font.PLAIN, 15));
        lblDireccion.setBounds(10, 100, 126, 16);
        panel_1.add(lblDireccion);

        tfEmail = new JTextField();
        tfEmail.setColumns(10);
        tfEmail.setBounds(146, 130, 419, 20);
        panel_1.add(tfEmail);

        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 15));
        lblEmail.setBounds(10, 131, 126, 16);
        panel_1.add(lblEmail);

        tfEdad = new JTextField();
        tfEdad.setColumns(10);
        tfEdad.setBounds(146, 68, 419, 20);
        panel_1.add(tfEdad);

        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setFont(new Font("Arial", Font.PLAIN, 15));
        lblEdad.setBounds(10, 69, 126, 16);
        panel_1.add(lblEdad);

        JLabel lblCinturon = new JLabel("Cinturon:");
        lblCinturon.setFont(new Font("Arial", Font.PLAIN, 15));
        lblCinturon.setBounds(10, 193, 134, 16);
        panel_1.add(lblCinturon);

        JLabel lblPunta = new JLabel("Punta:");
        lblPunta.setFont(new Font("Arial", Font.PLAIN, 15));
        lblPunta.setBounds(10, 224, 134, 16);
        panel_1.add(lblPunta);

        cbCinturon = new JComboBox<>();
        cbCinturon.setBounds(146, 192, 419, 22);
        populateComboBox(cbCinturon);
        cbCinturon.setRenderer(new ColorComboBoxRenderer(cbCinturon));
        panel_1.add(cbCinturon);

        cbPunta = new JComboBox<>();
        cbPunta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cbPunta.setBackground((Color) cbPunta.getSelectedItem());
                // Cambia el color del texto basándose en la luminancia del color seleccionado
                if ((Color) cbPunta.getSelectedItem() != null) {
                    if (isBrightColor((Color) cbPunta.getSelectedItem())) {
                        cbPunta.setForeground(Color.BLACK);
                    } else {
                        cbPunta.setForeground(Color.WHITE);
                    }
                }
            }
        });
        cbPunta.setBounds(146, 223, 419, 22);
        cbPunta.setRenderer(new ColorComboBoxRenderer(cbPunta));
        panel_1.add(cbPunta);

        // Agrega un ActionListener al primer JComboBox
        cbCinturon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = (Color) cbCinturon.getSelectedItem();
                updateSecondComboBoxOptions(selectedColor, cbPunta);
                cbCinturon.setBackground(selectedColor);

                if ((Color) cbPunta.getSelectedItem() != null) {
                    if (isBrightColor((Color) cbPunta.getSelectedItem())) {
                        cbPunta.setForeground(Color.BLACK);
                    } else {
                        cbPunta.setForeground(Color.WHITE);
                    }
                }
            }
        });
        
     // Establecer el DocumentFilter para los JTextFields que solo permita letras
        FiltrosParaTextField.setupTextFieldDocumentFilter(tfNombre);
        FiltrosParaTextField.setupTextFieldDocumentFilter(tfApellido);
        FiltrosParaTextField.setupTextFieldDocumentFilterForNumbers(tfCelular);
        FiltrosParaTextField.setupTextFieldDocumentFilterForNumbers(tfEdad);
        FiltrosParaTextField.setupTextFieldDocumentFilterForEmail(tfEmail);
        
        JButton btnSalir = new JButton("Atras");
        btnSalir.setBounds(5, 5, 89, 23);
        panel.add(btnSalir);
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAtrasActionListener();
            }
        });
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 13));
    }
    
    private void btnGuardarActionListener() {

        // Llama al controlador para guardar el nuevo Taekwondoka
        if(ConexionMySQL.obtenerConexion() != null) {
        	
        	// Obtén los datos de la interfaz gráfica y crea un objeto Taekwondoka
        	String nombre = tfNombre.getText();
        	String apellido = tfApellido.getText();
        	String edad = tfEdad.getText();
        	String direccion = tfDireccion.getText();
        	String email = tfEmail.getText();
        	String celular = tfCelular.getText();
        	String cinturon = obtenerNombreColor( (Color) cbCinturon.getSelectedItem());
        	String punta = obtenerNombreColor( (Color) cbPunta.getSelectedItem());
        	
            Taekwondoka nuevoTaekwondoka = new Taekwondoka(-1, nombre, apellido, edad, direccion, email, celular,
    		cinturon, punta);
        	
        	if(controller.guardarNuevoTaekwondoka(nuevoTaekwondoka)) {
        		btnLimpiarActionListener();
        	}
		} else {
			Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
		}
    }

    private void btnLimpiarActionListener() {
        tfNombre.setText("");
        tfApellido.setText("");
        tfCelular.setText("");
        tfDireccion.setText("");
        tfEmail.setText("");
        tfEdad.setText("");
        cbCinturon.setSelectedIndex(0);
    }

    private void btnAtrasActionListener() {
        dispose();
        menu.setLocation(this.getX(), this.getY());
        menu.setVisible(true);
    }

    // Método para llenar los colores en el JComboBox
    private void populateComboBox(JComboBox<Color> comboBox) {
        comboBox.addItem(null);
        comboBox.addItem(Color.WHITE);
        comboBox.addItem(Color.YELLOW);
        comboBox.addItem(Color.GREEN);
        comboBox.addItem(Color.BLUE);
        comboBox.addItem(Color.RED);
        comboBox.addItem(Color.BLACK);
    }

    // Método para actualizar dinámicamente las opciones del segundo JComboBox
    private void updateSecondComboBoxOptions(Object selectedColor, JComboBox<Color> comboBox_1) {
        comboBox_1.removeAllItems();  // Elimina todas las opciones actuales

        // Mapa que contiene las opciones para cada color del primer JComboBox
        Map<Color, Color[]> colorMap = new HashMap<>();
        colorMap.put(Color.WHITE, new Color[]{Color.WHITE, Color.YELLOW});
        colorMap.put(Color.YELLOW, new Color[]{Color.YELLOW, Color.GREEN});
        colorMap.put(Color.GREEN, new Color[]{Color.GREEN, Color.BLUE});
        colorMap.put(Color.BLUE, new Color[]{Color.BLUE, Color.RED});
        colorMap.put(Color.RED, new Color[]{Color.RED, Color.BLACK});
        colorMap.put(Color.BLACK, new Color[]{Color.BLACK});

        // Obtén las opciones según el color seleccionado en el primer JComboBox
        Color[] options = colorMap.get(selectedColor);

        // Agrega las opciones al segundo JComboBox
        if (options != null) {
            for (Color color : options) {
                comboBox_1.addItem(color);
            }
        }

        // Si hay opciones, selecciona la primera; de lo contrario, selecciona null
        comboBox_1.setSelectedItem(options != null && options.length > 0 ? options[0] : null);
    }

    private class ColorComboBoxRenderer extends DefaultListCellRenderer {
        private JComboBox<Color> cbCinturon;

        public ColorComboBoxRenderer(JComboBox<Color> comboBox) {
            this.cbCinturon = comboBox;
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof Color) {
                Color color = (Color) value;
                label.setBackground(color);
                label.setText(getColorName(color));
                label.setOpaque(true);

                if (isBrightColor(color)) {
                    label.setForeground(Color.BLACK);
                } else {
                    label.setForeground(Color.WHITE);
                }
            } else {
                label.setText("");
            }

            return label;
        }

        private String getColorName(Color color) {
            if (color != null) {
                if (color.equals(Color.RED)) {
                    return "Rojo";
                } else if (color.equals(Color.BLUE)) {
                    return "Azul";
                } else if (color.equals(Color.GREEN)) {
                    return "Verde";
                } else if (color.equals(Color.YELLOW)) {
                    return "Amarillo";
                } else if (color.equals(Color.ORANGE)) {
                    return "Naranja";
                } else if (color.equals(Color.WHITE)) {
                    return "Blanco";
                } else if (color.equals(Color.BLACK)) {
                    return "Negro";
                }
            }

            return "";
        }

        private boolean isBrightColor(Color color) {
            // Cambiado el umbral de brillo
            int brightnessThreshold = 200;
            double luminance = 0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue();
            return luminance > brightnessThreshold;
        }
    }

    private boolean isBrightColor(Color color) {
        // Cambiado el umbral de brillo
        int brightnessThreshold = 200;
        double luminance = 0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue();
        return luminance > brightnessThreshold;
    }

    private String obtenerNombreColor(Color color) {
    	if(color != null) {
    		if (color.equals(Color.WHITE)) {
                return "Blanco";
            } else if (color.equals(Color.YELLOW)) {
                return "Amarillo";
            } else if (color.equals(Color.GREEN)) {
                return "Verde";
            } else if (color.equals(Color.BLUE)) {
                return "Azul";
            } else if (color.equals(Color.RED)) {
                return "Rojo";
            } else if (color.equals(Color.BLACK)) {
                return "Negro";
            } else {
                // Puedes manejar otros colores según sea necesario.
                return "Desconocido";
            }
    	} else return "Desconocido";
    }
    
    public class JTextFieldLimit extends PlainDocument {
        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }
    

}
