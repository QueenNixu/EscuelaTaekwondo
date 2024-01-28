package taekwondo.igu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import taekwondo.persistencia.ConexionMySQL;
import taekwondo.util.Ventanas;
import taekwondo.util.XButtonOnTopBarListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Menu extends JFrame {
	
	private int xMouse = 0;
	private int yMouse = 0;
	
	public Menu() {
		
		//Default window for this app
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 416);
		setResizable(false);
		getContentPane().setLayout(null);
		setUndecorated(true);
		
		//Panel container of all components
		JPanel pnlAllContainer = new JPanel();
		pnlAllContainer.setBackground(new Color(52, 73, 94));
		pnlAllContainer.setBounds(0, 39, 611, 377);
		getContentPane().add(pnlAllContainer);
		pnlAllContainer.setLayout(null);
		
		//Label for title
		JLabel lblTitulo = new JLabel("Escuela de Taekwondo");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setBounds(167, 11, 274, 51);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 20));
		pnlAllContainer.add(lblTitulo);
		
		//Panel for all buttons
		JPanel pnlBotones = new JPanel();
		pnlBotones.setBackground(new Color(52, 73, 94));
		pnlBotones.setBounds(18, 64, 203, 302);
		pnlAllContainer.add(pnlBotones);
		pnlBotones.setLayout(null);
		
		//Label for image
		JLabel lblImagen = new JLabel("");
		lblImagen.setBounds(239, 64, 362, 302);
		lblImagen.setIcon(new ImageIcon("D:\\eclipse workspace 01\\escuelataekwondo\\images\\taekwondo-1.1.png"));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		pnlAllContainer.add(lblImagen);
		
		//Buttons
		
		//New Taekwondo athlete
		JButton btnNuevoTaekwondoka = new JButton("Nuevo Taekwondoka");
		btnNuevoTaekwondoka.setForeground(new Color(255, 255, 255));
		btnNuevoTaekwondoka.setBackground(new Color(41, 128, 185));
		btnNuevoTaekwondoka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNuevoTaekwondokaActionListener();
			}
		});
		btnNuevoTaekwondoka.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNuevoTaekwondoka.setBounds(10, 11, 180, 35);
		pnlBotones.add(btnNuevoTaekwondoka);
		
		//List Taekwondo athletes
		JButton btnVerTaekwondokas = new JButton("Ver Taekwondokas");
		btnVerTaekwondokas.setBackground(new Color(41, 128, 185));
		btnVerTaekwondokas.setForeground(new Color(255, 255, 255));
		btnVerTaekwondokas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnVerTaekwondokaActionListener();
			}
		});
		btnVerTaekwondokas.setFont(new Font("Arial", Font.PLAIN, 16));
		btnVerTaekwondokas.setBounds(10, 57, 180, 35);
		pnlBotones.add(btnVerTaekwondokas);
		
		//New Tournement
		JButton btnNuevoTorneo = new JButton("Nuevo Torneo");
		btnNuevoTorneo.setForeground(new Color(255, 255, 255));
		btnNuevoTorneo.setBackground(new Color(41, 128, 185));
		btnNuevoTorneo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNuevoTorneoActionListener();
			}
		});
		btnNuevoTorneo.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNuevoTorneo.setBounds(10, 103, 180, 35);
		pnlBotones.add(btnNuevoTorneo);
		
		//List Tournements
		JButton btnVerTorneos = new JButton("Ver Torneos");
		btnVerTorneos.setBackground(new Color(41, 128, 185));
		btnVerTorneos.setForeground(new Color(255, 255, 255));
		btnVerTorneos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnVerTorneosActionListener();
			}
		});
		btnVerTorneos.setFont(new Font("Arial", Font.PLAIN, 16));
		btnVerTorneos.setBounds(10, 149, 180, 35);
		pnlBotones.add(btnVerTorneos);
        
        //Exit
        JButton btnSalir = new JButton("Salir");
        btnSalir.setForeground(new Color(255, 255, 255));
        btnSalir.setBackground(new Color(41, 128, 185));
  		btnSalir.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				System.exit(0);
  			}
  		});
  		btnSalir.setFont(new Font("Arial", Font.PLAIN, 16));
  		btnSalir.setBounds(21, 211, 161, 35);
  		pnlBotones.add(btnSalir);
  		
  		JPanel topBar = new JPanel();
  		topBar.addMouseMotionListener(new MouseMotionAdapter() {
  			@Override
  			public void mouseDragged(MouseEvent e) {
  				mouseMovement(e);
  				}
  		});
  		topBar.addMouseListener(new MouseAdapter() {
  			@Override
  			public void mousePressed(MouseEvent e) {
  				updateCoordenates(e);
  			}
  		});
  		topBar.setBackground(new Color(44, 62, 80));
  		topBar.setBounds(0, 0, 611, 39);
  		getContentPane().add(topBar);
  		topBar.setLayout(null);
  		
  		JLabel closeButton = new JLabel("X");
  		closeButton.setForeground(new Color(0, 0, 0));
  		closeButton.addMouseListener(new MouseAdapter() {
  			@Override
  			public void mouseClicked(MouseEvent e) {
  				XButtonOnTopBarListener.cerrarApp();
  			}
  			@Override
  			public void mouseEntered(MouseEvent e) {
  				XButtonOnTopBarListener.mouseOnButton(closeButton);
  			}
  			@Override
  			public void mouseExited(MouseEvent e) {
  				XButtonOnTopBarListener.mouseNotOnButton(closeButton);
  			}
  			@Override
  			public void mousePressed(MouseEvent e) {
  				XButtonOnTopBarListener.buttonPressed(closeButton);
  			}
  		});
  		closeButton.setHorizontalAlignment(SwingConstants.CENTER);
  		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
  		closeButton.setBounds(565, 0, 46, 40);
  		topBar.add(closeButton);
	}
	
	protected void updateCoordenates(MouseEvent e) {
		xMouse = e.getX();
		yMouse = e.getY();
		//System.out.println("x: "+xMouse+", y: "+yMouse);
	}

	protected void mouseMovement(MouseEvent e) {
		this.setLocation(e.getXOnScreen() - xMouse, e.getYOnScreen() - yMouse);
		//System.out.println("x: "+e.getXOnScreen()+", y: "+e.getYOnScreen());
	}

	//New Taekwondo athlete
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

	//List Taekwondo athletes
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
	
	//New Tournement
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
	
	//List Tournements
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

}
