package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import enums.ActividadSimulador;
import enums.ActividadTipo;
import enums.ActoresComplexion;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;

public class GestionActividad extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JTable tablaActividad;
	private JTextField txtNombre;
	private JTextField txtTipo_sala;
	private JTextField txtHorasActividad;
	private JTextField txtDocumentacion_tecnica;
	private JTextField txtAcad;
	private JPanel HeaderPanel;
	private JScrollPane scrollPane;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private JButton btnBorrarActividad;
	private JButton btnAddActividad;
	private JButton btnModificarActividad;
	private JComboBox comboBoxTipoActividad;
	private JComboBox comboBoxSimulador;
	private JTextField txtBuscador;
	private JComboBox comboBoxColumna;
	private JLabel lblImportarActividades;
	private JLabel lblLupa;
	private JTextField txtCod_asignatura;
	private JLabel lblInfo;

	public GestionActividad() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				controlador.solicitudDatosActividad();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/ue.png"));
		setTitle("Hospital simulado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(98, 145, 800, 450);
		contentPane.add(scrollPane);

		tablaActividad = new JTable();
		tablaActividad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ponerDatos();
			}
		});
		tablaActividad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaActividad.setRowHeight(30);
		tablaActividad.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tablaActividad);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				habilitarBotones();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				habilitarBotones();
			}
		});
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setBounds(98, 629, 86, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtTipo_sala = new JTextField();
		txtTipo_sala.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				habilitarBotones();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				habilitarBotones();
			}
		});
		txtTipo_sala.setHorizontalAlignment(SwingConstants.CENTER);
		txtTipo_sala.setBounds(393, 629, 86, 30);
		contentPane.add(txtTipo_sala);
		txtTipo_sala.setColumns(10);

		txtHorasActividad = new JTextField();
		txtHorasActividad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				habilitarBotones();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				habilitarBotones();
			}
		});
		txtHorasActividad.setHorizontalAlignment(SwingConstants.CENTER);
		txtHorasActividad.setBounds(689, 629, 71, 30);
		contentPane.add(txtHorasActividad);
		txtHorasActividad.setColumns(10);

		txtDocumentacion_tecnica = new JTextField();
		txtDocumentacion_tecnica.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				habilitarBotones();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				habilitarBotones();
			}
		});
		txtDocumentacion_tecnica.setHorizontalAlignment(SwingConstants.CENTER);
		txtDocumentacion_tecnica.setBounds(593, 629, 86, 30);
		contentPane.add(txtDocumentacion_tecnica);
		txtDocumentacion_tecnica.setColumns(10);

		txtAcad = new JTextField();
		txtAcad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				habilitarBotones();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				habilitarBotones();
			}
		});
		txtAcad.setHorizontalAlignment(SwingConstants.CENTER);
		txtAcad.setColumns(10);
		txtAcad.setBounds(787, 629, 98, 30);
		contentPane.add(txtAcad);

		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(164, 44, 52));
		HeaderPanel.setBounds(0, 0, 1000, 100);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Actividad");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(358, 11, 266, 61);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		HeaderPanel.add(lblTitulo);
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setVerticalAlignment(JLabel.CENTER);

		ImageIcon ueIcon = new ImageIcon("./img/ue.png");
		lblUemLogo = new JLabel(ueIcon);
		lblUemLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUemLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				controlador.loginToHome();
			}

			@SuppressWarnings("deprecation")
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.HAND_CURSOR);
			}

			@SuppressWarnings("deprecation")
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		lblUemLogo.setBounds(50, 0, 100, 100);
		HeaderPanel.add(lblUemLogo);

		ImageIcon perfilIcon = new ImageIcon("./img/usuario.png");
		lblPerfil = new JLabel(perfilIcon);
		lblPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				controlador.gestionActividadToPerfil();
			}

			@SuppressWarnings("deprecation")
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.HAND_CURSOR);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		lblPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerfil.setBounds(850, 0, 100, 100);
		HeaderPanel.add(lblPerfil);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.gestionActividadToGestion();
				limpiarTxt();
				deselecionarFilayBotones();
			}
		});
		btnVolver.setBounds(100, 685, 150, 40);
		contentPane.add(btnVolver);

		btnModificarActividad = new JButton("Modificar actividad");
		btnModificarActividad.setEnabled(false);
		btnModificarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showConfirmDialog(rootPane, "�Desea modificar el profesor seleccionado?");
				controlador.solicitudModificarActividad();

				confirmarBotones();
				limpiarTxt();

			}
		});
		btnModificarActividad.setBounds(316, 685, 150, 40);
		contentPane.add(btnModificarActividad);

		btnBorrarActividad = new JButton("Borrar actividad");
		btnBorrarActividad.setEnabled(false);
		btnBorrarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (confirmacionBorrar() == 0) {
					controlador.solicitudBorrar(this);
					if (modeloGestionDatos.getSeHaBorrado()) {
						borrarActividad();
					} else {
						JOptionPane.showMessageDialog(rootPane, "Esta actividad esta siendo utilizada por un registro",
								"Error", 0);
					}

				}
				limpiarTxt();
			}
		});
		btnBorrarActividad.setBounds(532, 685, 150, 40);
		contentPane.add(btnBorrarActividad);

		btnAddActividad = new JButton(" A\u00F1adir actividad");
		btnAddActividad.setEnabled(false);
		btnAddActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitarBotones();
				controlador.solicutudAddActividad();
				if (modeloGestionDatos.getSeHaCreado()) {
					addActividad();
				} else {
					JOptionPane.showMessageDialog(rootPane, "No se ha creado esa asignatura o ese año academico",
							"Error", 0);
				}
				limpiarTxt();
			}
		});
		btnAddActividad.setBounds(748, 685, 150, 40);
		contentPane.add(btnAddActividad);

		comboBoxTipoActividad = new JComboBox();

		comboBoxTipoActividad.setModel(new DefaultComboBoxModel(ActividadTipo.values()));
		comboBoxTipoActividad.setBounds(297, 629, 86, 30);
		contentPane.add(comboBoxTipoActividad);

		comboBoxSimulador = new JComboBox();
		comboBoxSimulador.setModel(new DefaultComboBoxModel(ActividadSimulador.values()));
		comboBoxSimulador.setBounds(498, 629, 71, 30);
		contentPane.add(comboBoxSimulador);

		txtBuscador = new JTextField();
		txtBuscador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtBuscador.setText("");
			}
		});
		txtBuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtBuscador.getText().equals("")) {
					controlador.solicitudBuscador(this);
				} else {
					controlador.solicitudDatosActividad();
				}
			}
		});
		txtBuscador.setText("Buscador");
		txtBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscador.setBounds(728, 112, 140, 22);
		contentPane.add(txtBuscador);
		txtBuscador.setColumns(10);

		lblImportarActividades = new JLabel("Importar Actividades");
		lblImportarActividades.setIcon(
				new ImageIcon(GestionActividad.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		lblImportarActividades.setBounds(98, 111, 124, 20);
		contentPane.add(lblImportarActividades);

		ImageIcon lupa = new ImageIcon("./img/buscar.png");
		lblLupa = new JLabel(lupa);
		lblLupa.setBounds(878, 111, 20, 22);
		contentPane.add(lblLupa);

		txtCod_asignatura = new JTextField();
		txtCod_asignatura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				habilitarBotones();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				habilitarBotones();
			}
		});
		txtCod_asignatura.setHorizontalAlignment(SwingConstants.CENTER);
		txtCod_asignatura.setColumns(10);
		txtCod_asignatura.setBounds(200, 629, 87, 30);
		contentPane.add(txtCod_asignatura);
		lblImportarActividades.setVisible(false);

		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(234, 111, 429, 23);
		contentPane.add(lblInfo);

	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas = modeloConsultas;
	}

	public void setModeloGestionDatos(ModeloGestionDatos modeloGestionDatos) {
		this.modeloGestionDatos = modeloGestionDatos;
	}

	public String getNombre() {
		return txtNombre.getText();
	}

	public String getCod_asignatura() {
		return txtCod_asignatura.getText();
	}

	public String getTipo_sala() {
		return txtTipo_sala.getText();
	}

	public String getHorasActividad() {
		return txtHorasActividad.getText();
	}

	public String getDocumentacion_tecnica() {
		return txtDocumentacion_tecnica.getText();
	}

	public String getAcad() {
		return txtAcad.getText();
	}

	public String getTipoActividad() {
		return String.valueOf(comboBoxTipoActividad.getSelectedItem());
	}

	public String getSimulador() {
		return String.valueOf(comboBoxSimulador.getSelectedItem());
	}

	public DefaultTableModel getModel() {
		return (DefaultTableModel) tablaActividad.getModel();
	}

	public String getPrimaryKey() {
		return String.valueOf(tablaActividad.getValueAt(tablaActividad.getSelectedRow(), 0));
	}

	public String getPalabraBuscador() {
		return txtBuscador.getText();
	}

	public Object datosTipo() {
		String tipo = String.valueOf(tablaActividad.getValueAt(tablaActividad.getSelectedRow(), 2)).toUpperCase().trim();
		Object selecion = null;
		switch (tipo) {
		case "ESCENARIO COMPLEJO":
			selecion = ActividadTipo.Escenario_complejo;
			break;
		case "TALLER DE HABILIDADES":
			selecion = ActividadTipo.Taller_habilidades;
			break;
		case "DIARREA/PANCREATITIS":
			selecion = ActividadTipo.Diarrea_Pancreatitis;
			break;
		case "HDA/CIRROSIS":
			selecion = ActividadTipo.HDA_Cirrosis;
			break;

		default:
			break;
		}
		return selecion;
	}

	public Object datosSimulador() {
		String simulador = String.valueOf(tablaActividad.getValueAt(tablaActividad.getSelectedRow(), 4)).toUpperCase();
		Object selecion =ActividadSimulador.Vacio; 
		
		switch (simulador) {
		case "ISTAN":
			selecion = ActividadSimulador.ISTAN;
			break;
		case "OTRO":
			selecion = ActividadSimulador.OTRO;
			break;
		case "SV":
			selecion = ActividadSimulador.SV;
			break;
		case "IOT":
			selecion = ActividadSimulador.IOT;
			break;

		default:
			break;
		}
		return selecion;
	}

	private void ponerDatos() {
		txtNombre.setText(String.valueOf(tablaActividad.getValueAt(tablaActividad.getSelectedRow(), 0)));
		txtCod_asignatura.setText(String.valueOf(tablaActividad.getValueAt(tablaActividad.getSelectedRow(), 1)));
		comboBoxTipoActividad.setSelectedItem(datosTipo());
		txtTipo_sala.setText(String.valueOf(tablaActividad.getValueAt(tablaActividad.getSelectedRow(), 3)));
		comboBoxSimulador.setSelectedItem(datosSimulador());
		txtDocumentacion_tecnica.setText(String.valueOf(tablaActividad.getValueAt(tablaActividad.getSelectedRow(), 5)));
		txtHorasActividad.setText(String.valueOf(tablaActividad.getValueAt(tablaActividad.getSelectedRow(), 6)));
		txtAcad.setText(String.valueOf(tablaActividad.getValueAt(tablaActividad.getSelectedRow(), 7)));
		habilitarBotones();
	}

	public void addActividad() {
		DefaultTableModel model = (DefaultTableModel) tablaActividad.getModel();
		model.addRow(modeloGestionDatos.getDatosfilasTabla());
		limpiarTxt();
	}

	public void borrarActividad() {
		DefaultTableModel model = (DefaultTableModel) tablaActividad.getModel();
		if (modeloGestionDatos.getSeHaBorrado()) {
			model.removeRow(tablaActividad.getSelectedRow());
			lblInfo.setText("Activiad borrada");
			limpiarTxt();
		}

	}

	public boolean confirmarBotones() {
		boolean modAlert = true;
		if (!getNombre().equals(String.valueOf(tablaActividad.getValueAt(tablaActividad.getSelectedRow(), 0)))
				|| !getCod_asignatura()
						.equals(String.valueOf(tablaActividad.getValueAt(tablaActividad.getSelectedRow(), 1)))
				|| !getAcad().equals(String.valueOf(tablaActividad.getValueAt(tablaActividad.getSelectedRow(), 7)))) {
			lblInfo.setText("");
			JOptionPane.showMessageDialog(rootPane,
					"No puedes modificar estos campos\n Nombre\n Asignatura codigo\n Año académico", "Error", 0);
			tablaActividad.isRowSelected(tablaActividad.getSelectedRowCount() - 1);

		} else {
			modAlert = false;
		}
		return modAlert;

	}

	public int confirmacionBorrar() {
		int confirmacion = 1;
		int valorRetorno = JOptionPane.showConfirmDialog(rootPane,
				"¿Está seguro/a de que desea borrar el registro seleccionado?");
		if (JOptionPane.YES_OPTION == valorRetorno) {
			confirmacion = 0;
		}

		return confirmacion;
	}

	public void habilitarBotones() {
		// btnAlta
		if (!txtNombre.getText().equals("") && !txtCod_asignatura.getText().equals("")
				&& !txtDocumentacion_tecnica.getText().equals("") && !txtHorasActividad.getText().equals("")
				&& !txtTipo_sala.getText().equals("")) {
			btnAddActividad.setEnabled(true);
		} else {
			btnAddActividad.setEnabled(false);
		}

		// btnmodificar
		if (tablaActividad.getSelectedRowCount() == 1 && !txtNombre.getText().equals("")
				&& !txtCod_asignatura.getText().equals("") && !txtDocumentacion_tecnica.getText().equals("")
				&& !txtHorasActividad.getText().equals("") && !txtTipo_sala.getText().equals("")) {
			btnModificarActividad.setEnabled(true);
		} else {
			btnModificarActividad.setEnabled(false);
		}

		// btnBorrar
		if (tablaActividad.getSelectedRowCount() == 1 && !txtNombre.getText().equals("")
				&& !txtCod_asignatura.getText().equals("") && !txtDocumentacion_tecnica.getText().equals("")
				&& !txtHorasActividad.getText().equals("") && !txtTipo_sala.getText().equals("")) {
			btnBorrarActividad.setEnabled(true);
		} else {
			btnBorrarActividad.setEnabled(false);
		}

	}
	
	public void deselecionarFilayBotones() {
		tablaActividad.isRowSelected(tablaActividad.getSelectedRowCount() - 1);
		btnBorrarActividad.setEnabled(false);
		btnModificarActividad.setEnabled(false);
		lblInfo.setText("");
	}

	public void limpiarTxt() {
		txtDocumentacion_tecnica.setText("");
		txtTipo_sala.setText("");
		txtAcad.setText("");
		txtNombre.setText("");
		txtCod_asignatura.setText("");
		txtHorasActividad.setText("");
	}

	public void actualizarInfoDatos() {
		lblInfo.setText(modeloGestionDatos.getRespuesta());
	}
}
