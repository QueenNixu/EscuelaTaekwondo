package taekwondo.util;

import java.awt.Color;

import javax.swing.JLabel;

import taekwondo.igu.Menu;

public class XButtonOnTopBarListener {
	
	public static void cerrarApp(/*MouseEvent e*/) {
		System.exit(0);
	}

	public static void mouseOnButton(JLabel lblNewLabel) {
		
		lblNewLabel.setBackground(Color.RED);
		lblNewLabel.setOpaque(true);
		
	}

	public static void mouseNotOnButton(JLabel lblNewLabel) {
		
		lblNewLabel.setBackground(null);
		lblNewLabel.setOpaque(false);
		
	}

	public static void buttonPressed(JLabel lblNewLabel) {
		
		// Color original
	    Color originalColor = lblNewLabel.getBackground();
	    
	    // Ajustar el color original a un tono más oscuro
	    int red = (int) (originalColor.getRed() * 0.8);
	    int green = (int) (originalColor.getGreen() * 0.8);
	    int blue = (int) (originalColor.getBlue() * 0.8);
	    Color darkerColor = new Color(red, green, blue);
	    
	    // Establecer el color oscurecido como fondo
	    lblNewLabel.setBackground(darkerColor);
		
	}



}
