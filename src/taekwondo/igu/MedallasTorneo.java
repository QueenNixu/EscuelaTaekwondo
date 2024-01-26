package taekwondo.igu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import taekwondo.logica.Taekwondoka;
import taekwondo.logica.TaekwondokaController;
import taekwondo.logica.Torneo;
import taekwondo.logica.TorneoController;
import taekwondo.persistencia.ConexionMySQL;
import taekwondo.util.Ventanas;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MedallasTorneo extends JFrame {
	private JTable table;
	private JTextField tfBuscar;
	private VerDetallesTorneo verDetallesTorneo;
	private List<Taekwondoka> listaInscriptos;
	private Torneo tor;
	private TaekwondokaController taekwondokaController = new TaekwondokaController();
	private JTextField tfPrimerLugar;
	private JTextField tfSegundoLugar;
	private JTextField tfTercerLugar;
	private JTextField tfCuartoLugar;
	private TorneoController torneoController = new TorneoController();
	
	private int idMedallistaOro;
	private int idMedallistaPlata;
	private int idMedallistaBronce3;
	private int idMedallistaBronce4;

	
	public MedallasTorneo(VerDetallesTorneo verDetallesTorneo, List<Taekwondoka> listaInscriptos, Torneo tor) {
		
		this.listaInscriptos = listaInscriptos;
		this.tor = tor;
		this.verDetallesTorneo = verDetallesTorneo;
		idMedallistaOro = tor.getIdGanadorOro();
		idMedallistaPlata = tor.getIdGanadorPlata();
		idMedallistaBronce3 = tor.getIdGanadorBronce3();
		idMedallistaBronce4 = tor.getIdGanadorBronce4();
		
		System.out.println("ID del medallista de oro: " + idMedallistaOro);
		System.out.println("ID del medallista de plata: " + idMedallistaPlata);
		System.out.println("ID del medallista de bronce 3: " + idMedallistaBronce3);
		System.out.println("ID del medallista de bronce 4: " + idMedallistaBronce4);

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 611, 416);
        setResizable(false);
        getContentPane().setLayout(null);
        
        addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				cargarTabla();
			}
		});
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 595, 377);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Medallistas del Torneo:");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblTitulo.setBounds(163, 5, 274, 29);
        panel.add(lblTitulo);
        
        JLabel lblNombreTorneo = new JLabel("nombre del torneo");
        lblNombreTorneo.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombreTorneo.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblNombreTorneo.setBounds(10, 36, 578, 29);
        panel.add(lblNombreTorneo);
        
        JButton btnSalir = new JButton("Atras");
        btnSalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnAtrasActionListener();
        	}
        });
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 13));
        btnSalir.setBounds(5, 5, 89, 23);
        panel.add(btnSalir);
        
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBounds(10, 189, 575, 131);
        panel.add(panel_1);
        
        table = new JTable();
		table.setBounds(1, 1, 575, 0);
		table.setBorder(null);
		table.setAutoResizeMode(0);
		table.getTableHeader().setResizingAllowed(false);
		panel_1.add(table.getTableHeader(), BorderLayout.PAGE_START);
		panel_1.add(table, BorderLayout.CENTER);
		table.getTableHeader().setReorderingAllowed(false);
		panel_1.setLayout(null);
        
        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
        lblBuscar.setBounds(10, 12, 57, 16);
        panel_1.add(lblBuscar);
        
        tfBuscar = new JTextField();
        tfBuscar.setColumns(10);
        tfBuscar.setBounds(77, 11, 488, 20);
        panel_1.add(tfBuscar);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 36, 575, 95);
        panel_1.add(scrollPane);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(10, 65, 575, 121);
        panel.add(panel_2);
        panel_2.setLayout(null);
        
        JLabel lblPrimerLugar = new JLabel("Primer Lugar:");
        lblPrimerLugar.setFont(new Font("Arial", Font.PLAIN, 15));
        lblPrimerLugar.setBounds(10, 12, 110, 16);
        panel_2.add(lblPrimerLugar);
        
        tfPrimerLugar = new JTextField();
        tfPrimerLugar.setEditable(false);
        tfPrimerLugar.setBackground(new Color(255, 215, 0));
        tfPrimerLugar.setColumns(10);
        tfPrimerLugar.setBounds(120, 11, 229, 20);
        panel_2.add(tfPrimerLugar);
        
        JLabel lblSegundoLugar = new JLabel("Segundo Lugar:");
        lblSegundoLugar.setFont(new Font("Arial", Font.PLAIN, 15));
        lblSegundoLugar.setBounds(10, 40, 110, 16);
        panel_2.add(lblSegundoLugar);
        
        tfSegundoLugar = new JTextField();
        tfSegundoLugar.setEditable(false);
        tfSegundoLugar.setBackground(new Color(192, 192, 192));
        tfSegundoLugar.setColumns(10);
        tfSegundoLugar.setBounds(120, 39, 229, 20);
        panel_2.add(tfSegundoLugar);
        
        JLabel lblTercerLugar = new JLabel("Tercer Lugar:");
        lblTercerLugar.setFont(new Font("Arial", Font.PLAIN, 15));
        lblTercerLugar.setBounds(10, 68, 110, 16);
        panel_2.add(lblTercerLugar);
        
        tfTercerLugar = new JTextField();
        tfTercerLugar.setEditable(false);
        tfTercerLugar.setBackground(new Color(205, 127, 50));
        tfTercerLugar.setColumns(10);
        tfTercerLugar.setBounds(120, 67, 229, 20);
        panel_2.add(tfTercerLugar);
        
        JLabel lblCuartoLugar = new JLabel("Cuarto Lugar:");
        lblCuartoLugar.setFont(new Font("Arial", Font.PLAIN, 15));
        lblCuartoLugar.setBounds(10, 96, 110, 16);
        panel_2.add(lblCuartoLugar);
        
        tfCuartoLugar = new JTextField();
        tfCuartoLugar.setEditable(false);
        tfCuartoLugar.setBackground(new Color(205, 127, 50));
        tfCuartoLugar.setColumns(10);
        tfCuartoLugar.setBounds(120, 95, 229, 20);
        panel_2.add(tfCuartoLugar);
        
        JButton btnOtorgarMedallaOro = new JButton("Otorgar Medalla");
        btnOtorgarMedallaOro.setFont(new Font("Arial", Font.PLAIN, 11));
        btnOtorgarMedallaOro.setBounds(349, 11, 110, 19);
        panel_2.add(btnOtorgarMedallaOro);
        
        JButton btnOtorgarMedallaPlata = new JButton("Otorgar Medalla");
        btnOtorgarMedallaPlata.setFont(new Font("Arial", Font.PLAIN, 11));
        btnOtorgarMedallaPlata.setBounds(349, 39, 110, 19);
        panel_2.add(btnOtorgarMedallaPlata);
        
        JButton btnOtorgarMedallaBronce3 = new JButton("Otorgar Medalla");
        btnOtorgarMedallaBronce3.setFont(new Font("Arial", Font.PLAIN, 11));
        btnOtorgarMedallaBronce3.setBounds(349, 67, 110, 19);
        panel_2.add(btnOtorgarMedallaBronce3);
        
        JButton btnOtorgarMedallaBronce4 = new JButton("Otorgar Medalla");
        btnOtorgarMedallaBronce4.setFont(new Font("Arial", Font.PLAIN, 11));
        btnOtorgarMedallaBronce4.setBounds(349, 95, 110, 19);
        panel_2.add(btnOtorgarMedallaBronce4);
        
        ActionListener botonesOtorgar = new ActionListener() {

			@Override
            public void actionPerformed(ActionEvent e) {
            	
            	if (ConexionMySQL.obtenerConexion() != null) {
            		int filaSeleccionada = table.getSelectedRow();
        			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        			if(filaSeleccionada != -1) {
        				int idTae = (int) modelo.getValueAt(filaSeleccionada, 0);
        				
        				// buscar todos los datos del Taekwondoka (mail e id son unicos)
        				Taekwondoka tae = taekwondokaController.traerTaekwondokaById(idTae);
        				
        				if(tae != null) {
        					JButton botonPresionado = (JButton) e.getSource();

        					DefaultTableModel model = (DefaultTableModel) table.getModel();
        					
        	                // Dependiendo del botón presionado, cambia el textField correspondiente
        	                if (botonPresionado == btnOtorgarMedallaOro) {
        	                	if(!tfPrimerLugar.getText().equals("-")) {
        	                		Taekwondoka taeRetirado = taekwondokaController.traerTaekwondokaById(idMedallistaOro);
        	                		Object[] objeto = { taeRetirado.getId(), taeRetirado.getApellido(), taeRetirado.getNombre(), taeRetirado.getEmail()};
        	                        modelo.addRow(objeto);
        	                	}
        	                	tfPrimerLugar.setText(tae.getApellido()+" "+tae.getNombre());
        	                	idMedallistaOro = tae.getId();
        	                	
        	                	//remover row con mail = tae.getEmail
        	                } else if (botonPresionado == btnOtorgarMedallaPlata) {
        	                	if(!tfSegundoLugar.getText().equals("-")) {
        	                		Taekwondoka taeRetirado = taekwondokaController.traerTaekwondokaById(idMedallistaPlata);
        	                		Object[] objeto = { taeRetirado.getId(), taeRetirado.getApellido(), taeRetirado.getNombre(), taeRetirado.getEmail()};
        	                        modelo.addRow(objeto);
        	                	}
        	                	tfSegundoLugar.setText(tae.getApellido()+" "+tae.getNombre());
        	                	idMedallistaPlata = tae.getId();
        	                } else if (botonPresionado == btnOtorgarMedallaBronce3) {
        	                	if(!tfTercerLugar.getText().equals("-")) {
        	                		Taekwondoka taeRetirado = taekwondokaController.traerTaekwondokaById(idMedallistaBronce3);
        	                		Object[] objeto = { taeRetirado.getId(), taeRetirado.getApellido(), taeRetirado.getNombre(), taeRetirado.getEmail()};
        	                        modelo.addRow(objeto);
        	                	}
        	                	tfTercerLugar.setText(tae.getApellido()+" "+tae.getNombre());
        	                	idMedallistaBronce3 = tae.getId();
        	                } else if (botonPresionado == btnOtorgarMedallaBronce4) {
        	                	if(!tfCuartoLugar.getText().equals("-")) {
        	                		Taekwondoka taeRetirado = taekwondokaController.traerTaekwondokaById(idMedallistaBronce4);
        	                		Object[] objeto = { taeRetirado.getId(), taeRetirado.getApellido(), taeRetirado.getNombre(), taeRetirado.getEmail()};
        	                        modelo.addRow(objeto);
        	                	}
        	                	tfCuartoLugar.setText(tae.getApellido()+" "+tae.getNombre());
        	                	idMedallistaBronce4 = tae.getId();
        	                }

    	                	// Iterar sobre las filas de la tabla
    	                	for (int i = 0; i < model.getRowCount(); i++) {
    	                	    // Obtener el correo electrónico en la columna correspondiente (por ejemplo, columna 2)
    	                	    int id = (int) model.getValueAt(i, 0);

    	                	    // Verificar si el correo electrónico coincide con el de tae
    	                	    if (id == tae.getId()) {
    	                	        // Remover la fila
    	                	        model.removeRow(i);
    	                	        break; // Terminar la iteración una vez que se haya eliminado la fila
    	                	    }
    	                	}
    	                	
        				} else {
        					Ventanas.mostrarError("Hubo un problema y no se encontro el Taekwondoka.");
        				}
        			} else {
        				Ventanas.mostrarError("Seleccione una fila.");
        			}
            	} else {
                    Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
                }
            }
        };
        
        btnOtorgarMedallaOro.addActionListener(botonesOtorgar);
        btnOtorgarMedallaPlata.addActionListener(botonesOtorgar);
        btnOtorgarMedallaBronce3.addActionListener(botonesOtorgar);
        btnOtorgarMedallaBronce4.addActionListener(botonesOtorgar);
        
        JButton btnRetirarMedallaOro = new JButton("Retirar Medalla");
        btnRetirarMedallaOro.setFont(new Font("Arial", Font.PLAIN, 11));
        btnRetirarMedallaOro.setBounds(459, 11, 110, 19);
        panel_2.add(btnRetirarMedallaOro);
        
        JButton btnRetirarMedallaPlata = new JButton("Retirar Medalla");
        btnRetirarMedallaPlata.setFont(new Font("Arial", Font.PLAIN, 11));
        btnRetirarMedallaPlata.setBounds(459, 39, 110, 19);
        panel_2.add(btnRetirarMedallaPlata);
        
        JButton btnRetirarMedallaBronce3 = new JButton("Retirar Medalla");
        btnRetirarMedallaBronce3.setFont(new Font("Arial", Font.PLAIN, 11));
        btnRetirarMedallaBronce3.setBounds(459, 67, 110, 19);
        panel_2.add(btnRetirarMedallaBronce3);
        
        JButton btnRetirarMedallaBronce4 = new JButton("Retirar Medalla");
        btnRetirarMedallaBronce4.setFont(new Font("Arial", Font.PLAIN, 11));
        btnRetirarMedallaBronce4.setBounds(459, 95, 110, 19);
        panel_2.add(btnRetirarMedallaBronce4);
        
        ActionListener botonesRetirar = new ActionListener() {

			@Override
            public void actionPerformed(ActionEvent e) {
            	
            	if (ConexionMySQL.obtenerConexion() != null) {
            		
            		JButton botonPresionado = (JButton) e.getSource();

					DefaultTableModel modelo = (DefaultTableModel) table.getModel();
					
	                // Dependiendo del botón presionado, cambia el textField correspondiente
	                if (botonPresionado == btnRetirarMedallaOro) {
	                	
	                	System.out.println("btnOro presionado");
	                	
	                	if(!tfPrimerLugar.getText().equals("-")) {
	                		Taekwondoka taeRetirado = taekwondokaController.traerTaekwondokaById(idMedallistaOro);
	                		if(taeRetirado != null) {
	                			Object[] objeto = { taeRetirado.getId(), taeRetirado.getApellido(), taeRetirado.getNombre(), taeRetirado.getEmail()};
		                        modelo.addRow(objeto);
		                        tfPrimerLugar.setText("-");
	    	                	idMedallistaOro = 0;
	                		} else {
	        					Ventanas.mostrarError("Hubo un problema y no se encontro el Taekwondoka.Hubo un problema y no se encontro el Taekwondoka.");
	        				}
	                	}
	                	
	                	//remover row con mail = tae.getEmail
	                } else if (botonPresionado == btnRetirarMedallaPlata) {
	                	
	                	System.out.println("btnPlata presionado");
	                	
	                	if(!tfSegundoLugar.getText().equals("-")) {
	                		Taekwondoka taeRetirado = taekwondokaController.traerTaekwondokaById(idMedallistaPlata);
	                		if(taeRetirado != null) {
	                			Object[] objeto = { taeRetirado.getId(), taeRetirado.getApellido(), taeRetirado.getNombre(), taeRetirado.getEmail()};
		                        modelo.addRow(objeto);
		                        tfSegundoLugar.setText("-");
			                	idMedallistaPlata = 0;
	                		} else {
	        					Ventanas.mostrarError("Hubo un problema y no se encontro el Taekwondoka.Hubo un problema y no se encontro el Taekwondoka.");
	        				}
	                	}
	                } else if (botonPresionado == btnRetirarMedallaBronce3) {
	                	
	                	System.out.println("btnBronce3 presionado");
	                	
	                	if(!tfTercerLugar.getText().equals("-")) {
	                		Taekwondoka taeRetirado = taekwondokaController.traerTaekwondokaById(idMedallistaBronce3);
	                		if(taeRetirado != null) {
	                			
	                			Object[] objeto = { taeRetirado.getId(), taeRetirado.getApellido(), taeRetirado.getNombre(), taeRetirado.getEmail()};
		                        modelo.addRow(objeto);
		                        tfTercerLugar.setText("-");
			                	idMedallistaBronce3 = 0;
			                } else {
	        					Ventanas.mostrarError("Hubo un problema y no se encontro el Taekwondoka.Hubo un problema y no se encontro el Taekwondoka.");
	        				}
	                	}
	                } else if (botonPresionado == btnRetirarMedallaBronce4) {
	                	
	                	System.out.println("btnBronce4 presionado");
	                	
	                	if(!tfCuartoLugar.getText().equals("-")) {
	                		Taekwondoka taeRetirado = taekwondokaController.traerTaekwondokaById(idMedallistaBronce4);
	                		if(taeRetirado != null) {
	                			Object[] objeto = { taeRetirado.getId(), taeRetirado.getApellido(), taeRetirado.getNombre(), taeRetirado.getEmail()};
		                        modelo.addRow(objeto);
		                        tfCuartoLugar.setText("-");
			                	idMedallistaBronce4 = 0;
	                		} else {
	        					Ventanas.mostrarError("Hubo un problema y no se encontro el Taekwondoka.Hubo un problema y no se encontro el Taekwondoka.");
	        				}
	                	}
	                }
            	} else {
                    Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
                }
            }
        };
        
        btnRetirarMedallaOro.addActionListener(botonesRetirar);
        btnRetirarMedallaPlata.addActionListener(botonesRetirar);
        btnRetirarMedallaBronce3.addActionListener(botonesRetirar);
        btnRetirarMedallaBronce4.addActionListener(botonesRetirar);
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnGuardarActionListener();
        	}
        });
        btnGuardar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnGuardar.setBounds(149, 331, 120, 35);
        panel.add(btnGuardar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnCancelarActionListener();
        	}
        });
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnCancelar.setBounds(279, 331, 120, 35);
        panel.add(btnCancelar);
        
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
        
        cargarMedallistas();
	}
	
	protected void btnGuardarActionListener() {
		
		if(ConexionMySQL.obtenerConexion() != null) {
			
            Torneo nuevoTorneo = tor;
            
            nuevoTorneo.setIdGanadorOro(idMedallistaOro);
            nuevoTorneo.setIdGanadorPlata(idMedallistaPlata);
            nuevoTorneo.setIdGanadorBronce3(idMedallistaBronce3);
            nuevoTorneo.setIdGanadorBronce4(idMedallistaBronce4);
            
        	if(torneoController.editarTorneo(nuevoTorneo)) {
        		Ventanas.mostrarExito("Se ha editado el torneo.");
        		this.tor = nuevoTorneo;
        		btnCancelarActionListener();
        		
        	}
		} else {
			Ventanas.mostrarError("Ocurrió un error inesperado. Por favor, contacte al soporte técnico.");
		}
		
	}

	protected void btnAtrasActionListener() {
		
		dispose();
		verDetallesTorneo.setLocation(this.getX(), this.getY());
		verDetallesTorneo.setVisible(true);
		verDetallesTorneo.cargarTabla();
		
	}

	protected void btnCancelarActionListener() {
		
		idMedallistaOro = tor.getIdGanadorOro();
		idMedallistaPlata = tor.getIdGanadorPlata();
		idMedallistaBronce3 = tor.getIdGanadorBronce3();
		idMedallistaBronce4 = tor.getIdGanadorBronce4();
		
		cargarMedallistas();
		
	}

	private void cargarMedallistas() {
		
		if(ConexionMySQL.obtenerConexion() != null) {
			Taekwondoka medallistaOro = taekwondokaController.traerTaekwondokaById(tor.getIdGanadorOro());
			Taekwondoka medallistaPlata = taekwondokaController.traerTaekwondokaById(tor.getIdGanadorPlata());
			Taekwondoka medallistaBronce3 = taekwondokaController.traerTaekwondokaById(tor.getIdGanadorBronce3());
			Taekwondoka medallistaBronce4 = taekwondokaController.traerTaekwondokaById(tor.getIdGanadorBronce4());
			
			tfPrimerLugar.setText( (medallistaOro != null)? (medallistaOro.getApellido()+" "+medallistaOro.getNombre() ) : ("-"));
			tfSegundoLugar.setText( (medallistaPlata != null)? (medallistaPlata.getApellido()+" "+medallistaPlata.getNombre() ) : ("-"));
			tfTercerLugar.setText( (medallistaBronce3 != null)? (medallistaBronce3.getApellido()+" "+medallistaBronce3.getNombre() ) : ("-"));
			tfCuartoLugar.setText( (medallistaBronce4 != null)? (medallistaBronce4.getApellido()+" "+medallistaBronce4.getNombre() ) : ("-"));
			
			cargarTabla();
		}
		
	}

	protected void filtrarTabla() {
		
		String textoBusqueda = tfBuscar.getText().toLowerCase();
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0); // Limpiar la tabla

        for (Taekwondoka tae : listaInscriptos) {
            // Filtrar por nombre, apellido o email (puedes ajustar según tus necesidades)
            if (tae.getNombre().toLowerCase().contains(textoBusqueda) ||
                tae.getApellido().toLowerCase().contains(textoBusqueda) ||
                tae.getEmail().toLowerCase().contains(textoBusqueda)
                ) {
            	if(tae.getId() != idMedallistaOro &&
            		tae.getId() != idMedallistaPlata &&
            		tae.getId() != idMedallistaBronce3 &&
            		tae.getId() != idMedallistaBronce4) {
            		Object[] objeto = { tae.getId(), tae.getApellido(), tae.getNombre(), tae.getEmail()};
                    modelo.addRow(objeto);
            	}
            }
        }
		
	}

	protected void cargarTabla() {
		
		DefaultTableModel tablaModelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		String titulos[] = { "id", "Apellido", "Nombre", "E-mail"};
		tablaModelo.setColumnIdentifiers(titulos);
		
		if(ConexionMySQL.obtenerConexion() != null) {
			
			
			listaInscriptos = taekwondokaController.traerInscriptos(tor.getId());
			
			if (listaInscriptos != null) {
				
				
				for (Taekwondoka ins : listaInscriptos) {
					
					if(ins.getId() != idMedallistaOro &&
					   ins.getId() != idMedallistaPlata &&
					   ins.getId() != idMedallistaBronce3 &&
					   ins.getId() != idMedallistaBronce4) {
						Object[] objeto = {
								ins.getId(),
								ins.getApellido(),
								ins.getNombre(),
								ins.getEmail()
								};
						tablaModelo.addRow(objeto);
		            	}
				}
			}

			table.setModel(tablaModelo);
		}
		
		// Obtén el modelo de columna de la tabla
		TableColumnModel columnModel = table.getColumnModel();

		// Ajusta el ancho predeterminado de las columnas
		int[] anchos = { 0, 190, 190, 193}; // Puedes ajustar los valores según tus necesidades

		for (int i = 0; i < columnModel.getColumnCount() && i < anchos.length; i++) {
			TableColumn column = columnModel.getColumn(i);
			column.setPreferredWidth(anchos[i]);
			column.setResizable(false);
		}

		TableColumn columna = table.getColumnModel().getColumn(0);
		table.getColumnModel().removeColumn(columna);
		
	}
}
