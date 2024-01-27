package taekwondo.igu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import taekwondo.logica.Taekwondoka;
import taekwondo.logica.TaekwondokaController;
import taekwondo.logica.Torneo;
import taekwondo.logica.TorneoController;

import taekwondo.persistencia.ConexionMySQL;

import taekwondo.util.Ventanas;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.List;

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
		//Saves window to go back to
		this.verDetallesTorneo = verDetallesTorneo;
		
		idMedallistaOro = tor.getIdGanadorOro();
		idMedallistaPlata = tor.getIdGanadorPlata();
		idMedallistaBronce3 = tor.getIdGanadorBronce3();
		idMedallistaBronce4 = tor.getIdGanadorBronce4();
		
		//load table at window opened
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 611, 416);
        setResizable(false);
        getContentPane().setLayout(null);
        setUndecorated(true);
        
        //Default window for this app
        addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				cargarTabla();
			}
		});
        
        //panel all container
        JPanel pnlAllContainer = new JPanel();
        pnlAllContainer.setBackground(new Color(52, 73, 94));
        pnlAllContainer.setBounds(0, 39, 611, 377);
        getContentPane().add(pnlAllContainer);
        pnlAllContainer.setLayout(null);
        
        //back button
        JButton btnSalir = new JButton("Atras");
        btnSalir.setBackground(new Color(41, 128, 185));
        btnSalir.setForeground(new Color(255, 255, 255));
        btnSalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnAtrasActionListener();
        	}
        });
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 13));
        btnSalir.setBounds(5, 5, 89, 23);
        pnlAllContainer.add(btnSalir);
        
        //label for title 1
        JLabel lblTitulo = new JLabel("Medallistas del Torneo:");
        lblTitulo.setForeground(new Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblTitulo.setBounds(171, 5, 274, 29);
        pnlAllContainer.add(lblTitulo);
        
        //label for title 2
        JLabel lblNombreTorneo = new JLabel("nombre del torneo");
        lblNombreTorneo.setForeground(new Color(255, 255, 255));
        lblNombreTorneo.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombreTorneo.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblNombreTorneo.setBounds(18, 36, 578, 29);
        pnlAllContainer.add(lblNombreTorneo);

        //Panel for medalist
        JPanel pnlMedalist = new JPanel();
        pnlMedalist.setBackground(new Color(52, 73, 94));
        pnlMedalist.setBounds(18, 65, 575, 121);
        pnlAllContainer.add(pnlMedalist);
        pnlMedalist.setLayout(null);
        
        //labels and text field for golr, silver, bronce 3 and bronce 4
        JLabel lblPrimerLugar = new JLabel("Primer Lugar:");
        lblPrimerLugar.setForeground(new Color(255, 255, 255));
        lblPrimerLugar.setFont(new Font("Arial", Font.PLAIN, 15));
        lblPrimerLugar.setBounds(10, 12, 110, 16);
        pnlMedalist.add(lblPrimerLugar);
        
        tfPrimerLugar = new JTextField();
        tfPrimerLugar.setEditable(false);
        tfPrimerLugar.setBackground(new Color(255, 215, 0));
        tfPrimerLugar.setColumns(10);
        tfPrimerLugar.setBounds(120, 11, 229, 20);
        pnlMedalist.add(tfPrimerLugar);
        
        JLabel lblSegundoLugar = new JLabel("Segundo Lugar:");
        lblSegundoLugar.setForeground(new Color(255, 255, 255));
        lblSegundoLugar.setFont(new Font("Arial", Font.PLAIN, 15));
        lblSegundoLugar.setBounds(10, 40, 110, 16);
        pnlMedalist.add(lblSegundoLugar);
        
        tfSegundoLugar = new JTextField();
        tfSegundoLugar.setEditable(false);
        tfSegundoLugar.setBackground(new Color(192, 192, 192));
        tfSegundoLugar.setColumns(10);
        tfSegundoLugar.setBounds(120, 39, 229, 20);
        pnlMedalist.add(tfSegundoLugar);
        
        JLabel lblTercerLugar = new JLabel("Tercer Lugar:");
        lblTercerLugar.setForeground(new Color(255, 255, 255));
        lblTercerLugar.setFont(new Font("Arial", Font.PLAIN, 15));
        lblTercerLugar.setBounds(10, 68, 110, 16);
        pnlMedalist.add(lblTercerLugar);
        
        tfTercerLugar = new JTextField();
        tfTercerLugar.setEditable(false);
        tfTercerLugar.setBackground(new Color(205, 127, 50));
        tfTercerLugar.setColumns(10);
        tfTercerLugar.setBounds(120, 67, 229, 20);
        pnlMedalist.add(tfTercerLugar);
        
        JLabel lblCuartoLugar = new JLabel("Cuarto Lugar:");
        lblCuartoLugar.setForeground(new Color(255, 255, 255));
        lblCuartoLugar.setFont(new Font("Arial", Font.PLAIN, 15));
        lblCuartoLugar.setBounds(10, 96, 110, 16);
        pnlMedalist.add(lblCuartoLugar);
        
        tfCuartoLugar = new JTextField();
        tfCuartoLugar.setEditable(false);
        tfCuartoLugar.setBackground(new Color(205, 127, 50));
        tfCuartoLugar.setColumns(10);
        tfCuartoLugar.setBounds(120, 95, 229, 20);
        pnlMedalist.add(tfCuartoLugar);
        
        //Give medal buttons
        JButton btnOtorgarMedallaOro = new JButton("Otorgar Medalla");
        btnOtorgarMedallaOro.setBackground(new Color(41, 128, 185));
        btnOtorgarMedallaOro.setForeground(new Color(255, 255, 255));
        btnOtorgarMedallaOro.setFont(new Font("Arial", Font.PLAIN, 11));
        btnOtorgarMedallaOro.setBounds(349, 11, 110, 19);
        pnlMedalist.add(btnOtorgarMedallaOro);
        
        JButton btnOtorgarMedallaPlata = new JButton("Otorgar Medalla");
        btnOtorgarMedallaPlata.setBackground(new Color(41, 128, 185));
        btnOtorgarMedallaPlata.setForeground(new Color(255, 255, 255));
        btnOtorgarMedallaPlata.setFont(new Font("Arial", Font.PLAIN, 11));
        btnOtorgarMedallaPlata.setBounds(349, 39, 110, 19);
        pnlMedalist.add(btnOtorgarMedallaPlata);
        
        JButton btnOtorgarMedallaBronce3 = new JButton("Otorgar Medalla");
        btnOtorgarMedallaBronce3.setBackground(new Color(41, 128, 185));
        btnOtorgarMedallaBronce3.setForeground(new Color(255, 255, 255));
        btnOtorgarMedallaBronce3.setFont(new Font("Arial", Font.PLAIN, 11));
        btnOtorgarMedallaBronce3.setBounds(349, 67, 110, 19);
        pnlMedalist.add(btnOtorgarMedallaBronce3);
        
        JButton btnOtorgarMedallaBronce4 = new JButton("Otorgar Medalla");
        btnOtorgarMedallaBronce4.setBackground(new Color(41, 128, 185));
        btnOtorgarMedallaBronce4.setForeground(new Color(255, 255, 255));
        btnOtorgarMedallaBronce4.setFont(new Font("Arial", Font.PLAIN, 11));
        btnOtorgarMedallaBronce4.setBounds(349, 95, 110, 19);
        pnlMedalist.add(btnOtorgarMedallaBronce4);
        
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
        
        //Remove medal buttons
        JButton btnRetirarMedallaOro = new JButton("Retirar Medalla");
        btnRetirarMedallaOro.setBackground(new Color(41, 128, 185));
        btnRetirarMedallaOro.setForeground(new Color(255, 255, 255));
        btnRetirarMedallaOro.setFont(new Font("Arial", Font.PLAIN, 11));
        btnRetirarMedallaOro.setBounds(459, 11, 110, 19);
        pnlMedalist.add(btnRetirarMedallaOro);
        
        JButton btnRetirarMedallaPlata = new JButton("Retirar Medalla");
        btnRetirarMedallaPlata.setBackground(new Color(41, 128, 185));
        btnRetirarMedallaPlata.setForeground(new Color(255, 255, 255));
        btnRetirarMedallaPlata.setFont(new Font("Arial", Font.PLAIN, 11));
        btnRetirarMedallaPlata.setBounds(459, 39, 110, 19);
        pnlMedalist.add(btnRetirarMedallaPlata);
        
        JButton btnRetirarMedallaBronce3 = new JButton("Retirar Medalla");
        btnRetirarMedallaBronce3.setBackground(new Color(41, 128, 185));
        btnRetirarMedallaBronce3.setForeground(new Color(255, 255, 255));
        btnRetirarMedallaBronce3.setFont(new Font("Arial", Font.PLAIN, 11));
        btnRetirarMedallaBronce3.setBounds(459, 67, 110, 19);
        pnlMedalist.add(btnRetirarMedallaBronce3);
        
        JButton btnRetirarMedallaBronce4 = new JButton("Retirar Medalla");
        btnRetirarMedallaBronce4.setBackground(new Color(41, 128, 185));
        btnRetirarMedallaBronce4.setForeground(new Color(255, 255, 255));
        btnRetirarMedallaBronce4.setFont(new Font("Arial", Font.PLAIN, 11));
        btnRetirarMedallaBronce4.setBounds(459, 95, 110, 19);
        pnlMedalist.add(btnRetirarMedallaBronce4);
        
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
        
        //Panel for search bar and table
        JPanel pnlSearchBarTable = new JPanel();
        pnlSearchBarTable.setBackground(new Color(52, 73, 94));
        pnlSearchBarTable.setLayout(null);
        pnlSearchBarTable.setBounds(18, 189, 575, 131);
        pnlAllContainer.add(pnlSearchBarTable);
        
        //Label for search bar
        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setForeground(new Color(255, 255, 255));
        lblBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
        lblBuscar.setBounds(10, 12, 57, 16);
        pnlSearchBarTable.add(lblBuscar);
        
        //label for search bar
        tfBuscar = new JTextField();
        tfBuscar.setForeground(new Color(255, 255, 255));
        tfBuscar.setBackground(new Color(44, 62, 80));
        tfBuscar.setColumns(10);
        tfBuscar.setBounds(77, 11, 488, 20);
        pnlSearchBarTable.add(tfBuscar);
        
        //table for signed athlete's
        table = new JTable();
		table.setBounds(1, 1, 575, 0);
		table.setBorder(null);
		table.setAutoResizeMode(0);
		table.getTableHeader().setResizingAllowed(false);
		pnlSearchBarTable.add(table.getTableHeader(), BorderLayout.PAGE_START);
		pnlSearchBarTable.add(table, BorderLayout.CENTER);
		table.getTableHeader().setReorderingAllowed(false);
		pnlSearchBarTable.setLayout(null);
        
		//scrollbar for teble
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 36, 575, 95);
        pnlSearchBarTable.add(scrollPane);
        
        //save button
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setForeground(new Color(255, 255, 255));
        btnGuardar.setBackground(new Color(41, 128, 185));
        btnGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnGuardarActionListener();
        	}
        });
        btnGuardar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnGuardar.setBounds(157, 331, 120, 35);
        pnlAllContainer.add(btnGuardar);
        
        //cancel editing button
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setForeground(new Color(255, 255, 255));
        btnCancelar.setBackground(new Color(41, 128, 185));
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnCancelarActionListener();
        	}
        });
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnCancelar.setBounds(286, 331, 120, 35);
        pnlAllContainer.add(btnCancelar);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(44, 62, 80));
        panel.setBounds(0, 0, 611, 39);
        getContentPane().add(panel);
        
        //search bar filter
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
        
        //load medalist
        cargarMedallistas();
	}
	
	//save
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

	//back
	protected void btnAtrasActionListener() {
		
		dispose();
		verDetallesTorneo.setLocation(this.getX(), this.getY());
		verDetallesTorneo.setVisible(true);
		verDetallesTorneo.cargarTabla();
		
	}

	//cancel editing
	protected void btnCancelarActionListener() {
		
		idMedallistaOro = tor.getIdGanadorOro();
		idMedallistaPlata = tor.getIdGanadorPlata();
		idMedallistaBronce3 = tor.getIdGanadorBronce3();
		idMedallistaBronce4 = tor.getIdGanadorBronce4();
		
		cargarMedallistas();
		
	}

	//load medalist
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

	//filter table
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

	//load table
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
