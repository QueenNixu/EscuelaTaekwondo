package taekwondo.igu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import taekwondo.persistencia.ConexionMySQL;
import taekwondo.util.Ventanas;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Menu extends JFrame {
	
	private Menu instancia = this;
	
	public Menu() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 416);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 595, 377);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Escuela de Taekwondo");
		lblTitulo.setBounds(159, 11, 274, 51);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 20));
		panel.add(lblTitulo);
		
		JPanel pnlBotones = new JPanel();
		pnlBotones.setBounds(10, 64, 203, 302);
		// Crea un borde para el JLabel (bordes son opcionales)
        Border borde_3 = BorderFactory.createLineBorder(Color.BLACK, 2);
		panel.add(pnlBotones);
		pnlBotones.setLayout(null);
		
		JButton btnNuevoDuenio = new JButton("Nuevo Taekwondoka");
		btnNuevoDuenio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNuevoTaekwondokaActionListener();
			}
		});
		btnNuevoDuenio.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNuevoDuenio.setBounds(10, 11, 180, 35);
		pnlBotones.add(btnNuevoDuenio);
		
		JButton btnVerTaekwondokas = new JButton("Ver Taekwondokas");
		btnVerTaekwondokas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnVerTaekwondokaActionListener();
			}
		});
		btnVerTaekwondokas.setFont(new Font("Arial", Font.PLAIN, 16));
		btnVerTaekwondokas.setBounds(10, 57, 180, 35);
		pnlBotones.add(btnVerTaekwondokas);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setFont(new Font("Arial", Font.PLAIN, 16));
		btnSalir.setBounds(10, 211, 180, 35);
		pnlBotones.add(btnSalir);
		
		JButton btnNuevoTorneo = new JButton("Nuevo Torneo");
		btnNuevoTorneo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNuevoTorneoActionListener();
			}
		});
		btnNuevoTorneo.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNuevoTorneo.setBounds(10, 103, 180, 35);
		pnlBotones.add(btnNuevoTorneo);
		
		JButton btnVerTorneos = new JButton("Ver Torneos");
		btnVerTorneos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnVerTorneosActionListener();
			}
		});
		btnVerTorneos.setFont(new Font("Arial", Font.PLAIN, 16));
		btnVerTorneos.setBounds(10, 149, 180, 35);
		pnlBotones.add(btnVerTorneos);
		// Crea un borde para el JLabel (bordes son opcionales)
        Border borde_1 = BorderFactory.createLineBorder(Color.BLACK, 2);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setBounds(223, 64, 362, 302);
		lblImagen.setIcon(new ImageIcon("D:\\eclipse workspace 01\\escuelataekwondo\\images\\taekwondo-1.1.png"));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		// Crea un borde para el JLabel (bordes son opcionales)
        Border borde_2 = BorderFactory.createLineBorder(Color.BLACK, 2);
		panel.add(lblImagen);
	}
	
	private void btnVerTorneosActionListener() {
		
		if(ConexionMySQL.obtenerConexion() != null) {
			VerTorneos vt = new VerTorneos(this);
			vt.setLocation(this.getX(), this.getY());
			dispose();
			vt.setVisible(true);
		} else {
			Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
		}
		
	}

	protected void btnNuevoTorneoActionListener() {
		
		if(ConexionMySQL.obtenerConexion() != null) {
			NuevoTorneo nt = new NuevoTorneo(this);
			nt.setLocation(this.getX(), this.getY());
			dispose();
			nt.setVisible(true);
		} else {
			Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
		}
		
	}

	private void btnNuevoTaekwondokaActionListener() {
		if(ConexionMySQL.obtenerConexion() != null) {
			NuevoTaekwondoka nt = new NuevoTaekwondoka(this);
			nt.setLocation(this.getX(), this.getY());
			dispose();
			nt.setVisible(true);
		} else {
			Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
		}
	}
	
	private void btnVerTaekwondokaActionListener() {
		if (ConexionMySQL.obtenerConexion() != null) {
            VerTaekwondokas VVT = new VerTaekwondokas(this);
            VVT.setLocation(this.getX(), this.getY());
            dispose();
            VVT.setVisible(true);
        } else {
            Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
        }
	}

}
