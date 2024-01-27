package taekwondo.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PintarPanel {
	
	//creates a 2 color panel for belt and tip
	public static JPanel crearColorPanel(String cinturon, String punta, int anchoPrimero, int anchoSegundo) {
        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new BorderLayout());

        // Mapea los nombres de colores a valores de Color
        Color mappedColor1 = mapColor(cinturon.trim());
        Color mappedColor2 = mapColor(punta.trim());

        // Crea un rectángulo para el primer color
        JPanel firstColorPanel = new JPanel();
        firstColorPanel.setBackground(mappedColor1);
        firstColorPanel.setPreferredSize(new Dimension(anchoPrimero, anchoSegundo));

        // Crea un rectángulo para el segundo color
        JPanel secondColorPanel = new JPanel();
        secondColorPanel.setBackground(mappedColor2);
        secondColorPanel.setPreferredSize(new Dimension(anchoPrimero, anchoSegundo));

        // Agrega los rectángulos al panel principal
        colorPanel.add(firstColorPanel, BorderLayout.WEST);
        colorPanel.add(secondColorPanel, BorderLayout.EAST);

        return colorPanel;
    }

	//color mapping
    private static Color mapColor(String colorName) {
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
