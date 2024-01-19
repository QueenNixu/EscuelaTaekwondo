package taekwondo.igu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Menu extends JFrame {
	
	Menu instancia = this;
	
	public Menu() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 416);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 595, 377);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Escuela de Taekwondo");
		lblNewLabel.setBounds(159, 11, 274, 51);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 20));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 64, 203, 302);
		// Crea un borde para el JLabel (bordes son opcionales)
        Border borde_3 = BorderFactory.createLineBorder(Color.BLACK, 2);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNuevoDuenio = new JButton("Nuevo Taekwondoka");
		btnNuevoDuenio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNuevoTaekwondokaActionListener();
			}
		});
		btnNuevoDuenio.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNuevoDuenio.setBounds(10, 11, 180, 35);
		panel_1.add(btnNuevoDuenio);
		
		JButton btnVerTaekwondokas = new JButton("Ver Taekwondokas");
		btnVerTaekwondokas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnVerTaekwondokaActionListener();
			}
		});
		btnVerTaekwondokas.setFont(new Font("Arial", Font.PLAIN, 16));
		btnVerTaekwondokas.setBounds(10, 60, 180, 35);
		panel_1.add(btnVerTaekwondokas);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setFont(new Font("Arial", Font.PLAIN, 16));
		btnSalir.setBounds(10, 154, 180, 35);
		panel_1.add(btnSalir);
		// Crea un borde para el JLabel (bordes son opcionales)
        Border borde_1 = BorderFactory.createLineBorder(Color.BLACK, 2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(223, 64, 362, 302);
		lblNewLabel_1.setIcon(new ImageIcon("D:\\eclipse workspace 01\\escuelataekwondo\\images\\taekwondo-1.1.png"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		// Crea un borde para el JLabel (bordes son opcionales)
        Border borde_2 = BorderFactory.createLineBorder(Color.BLACK, 2);
		panel.add(lblNewLabel_1);
	}
	
	private void btnNuevoTaekwondokaActionListener() {
		NuevoTaekwondoka nt = new NuevoTaekwondoka(this);
		nt.setLocation(this.getX(), this.getY());
		dispose();
		nt.setVisible(true);
	}
	
	private void btnVerTaekwondokaActionListener() {
		VerTaekwondokas VVT = new VerTaekwondokas(this);
		VVT.setLocation(this.getX(), this.getY());
		dispose();
		VVT.setVisible(true);
	}
}
