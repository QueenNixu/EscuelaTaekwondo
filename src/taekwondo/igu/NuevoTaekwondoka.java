package taekwondo.igu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class NuevoTaekwondoka extends JFrame {
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private Menu menu;

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

        JLabel lblNuevoDueo = new JLabel("Nuevo Taekwondoka");
        lblNuevoDueo.setHorizontalAlignment(SwingConstants.CENTER);
        lblNuevoDueo.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblNuevoDueo.setBounds(184, 11, 229, 51);
        panel.add(lblNuevoDueo);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBounds(10, 63, 575, 303);
        panel.add(panel_1);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnGuardar.setBounds(10, 257, 170, 35);
        panel_1.add(btnGuardar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnLimpiar.setBounds(200, 257, 170, 35);
        panel_1.add(btnLimpiar);

        JButton btnSalir = new JButton("Atras");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAtrasActionListener();
            }
        });
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 16));
        btnSalir.setBounds(395, 257, 170, 35);
        panel_1.add(btnSalir);

        JLabel lblNewLabel = new JLabel("Nombre:");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel.setBounds(10, 10, 126, 16);
        panel_1.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(146, 9, 419, 20);
        panel_1.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(146, 37, 419, 20);
        panel_1.add(textField_1);

        JLabel lblNewLabel_6 = new JLabel("Apellido:");
        lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel_6.setBounds(10, 38, 126, 16);
        panel_1.add(lblNewLabel_6);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(146, 68, 419, 20);
        panel_1.add(textField_2);

        JLabel lblNewLabel_6_1 = new JLabel("Celular:");
        lblNewLabel_6_1.setToolTipText("asd");
        lblNewLabel_6_1.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel_6_1.setBounds(10, 69, 126, 16);
        panel_1.add(lblNewLabel_6_1);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(146, 99, 419, 20);
        panel_1.add(textField_3);

        JLabel lblNewLabel_6_2 = new JLabel("Direccion:");
        lblNewLabel_6_2.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel_6_2.setBounds(10, 100, 126, 16);
        panel_1.add(lblNewLabel_6_2);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(146, 130, 419, 20);
        panel_1.add(textField_4);

        JLabel lblNewLabel_6_3 = new JLabel("E-mail:");
        lblNewLabel_6_3.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel_6_3.setBounds(10, 131, 126, 16);
        panel_1.add(lblNewLabel_6_3);

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(146, 161, 419, 20);
        panel_1.add(textField_5);

        JLabel lblNewLabel_6_4 = new JLabel("Raza:");
        lblNewLabel_6_4.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel_6_4.setBounds(10, 162, 126, 16);
        panel_1.add(lblNewLabel_6_4);

        JLabel lblNewLabel_6_5 = new JLabel("Cinturon:");
        lblNewLabel_6_5.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel_6_5.setBounds(10, 193, 134, 16);
        panel_1.add(lblNewLabel_6_5);

        JLabel lblNewLabel_6_6 = new JLabel("Punta:");
        lblNewLabel_6_6.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel_6_6.setBounds(10, 224, 134, 16);
        panel_1.add(lblNewLabel_6_6);

        JComboBox<Color> comboBox = new JComboBox<>();
        comboBox.setBounds(146, 192, 419, 22);
        populateComboBox(comboBox);
        comboBox.setRenderer(new ColorComboBoxRenderer(comboBox));
        panel_1.add(comboBox);

        JComboBox<Color> comboBox_1 = new JComboBox<>();
        comboBox_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		comboBox_1.setBackground((Color)comboBox_1.getSelectedItem());
        		// Cambia el color del texto basándose en la luminancia del color seleccionado
        		if((Color)comboBox_1.getSelectedItem() != null) {
        			if (isBrightColor((Color)comboBox_1.getSelectedItem())) {
        				comboBox_1.setForeground(Color.BLACK);
                    } else {
                    	comboBox_1.setForeground(Color.WHITE);
                    }
        		}
                
        	}
        });
        comboBox_1.setBounds(146, 223, 419, 22);
        comboBox_1.setRenderer(new ColorComboBoxRenderer(comboBox_1));
        panel_1.add(comboBox_1);

        // Agrega un ActionListener al primer JComboBox
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = (Color) comboBox.getSelectedItem();
                updateSecondComboBoxOptions(selectedColor, comboBox_1);
                comboBox.setBackground(selectedColor);

                if((Color)comboBox_1.getSelectedItem() != null) {
        			if (isBrightColor((Color)comboBox_1.getSelectedItem())) {
                        comboBox.setForeground(Color.BLACK);
                    } else {
                        comboBox.setForeground(Color.WHITE);
                    }
        		}
            }
        });
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

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Menu menu = new Menu();
                    NuevoTaekwondoka frame = new NuevoTaekwondoka(menu);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class ColorComboBoxRenderer extends DefaultListCellRenderer {
        private JComboBox<Color> comboBox;

        public ColorComboBoxRenderer(JComboBox<Color> comboBox) {
            this.comboBox = comboBox;
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
}
