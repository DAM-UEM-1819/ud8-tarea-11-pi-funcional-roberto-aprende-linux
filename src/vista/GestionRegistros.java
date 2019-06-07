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
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;

public class GestionRegistros extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JTable tablaRegistros;
	private JPanel HeaderPanel;
	private JScrollPane scrollPane;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private JButton btnBorrarRegistro;
	private JButton btnAddRegistro;
	private JTextField txtBuscador;
	private JLabel lblImportarActividades;
	private JButton btnModificar;
	private JLabel lblInfo;
	private JLabel lblLupa;

	/**
	 * Create the frame.
	 */
	public GestionRegistros() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				controlador.solicitudDatosRegistros();
				esconderPrimeraColumna();
				desHabilitarBotones();

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
		scrollPane.setBounds(100, 145, 800, 500);
		contentPane.add(scrollPane);

		tablaRegistros = new JTable();
		tablaRegistros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				habilitarBotones();

			}
		});
		tablaRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaRegistros.setRowHeight(30);
		tablaRegistros.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tablaRegistros);

		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(164, 44, 52));
		HeaderPanel.setBounds(0, 0, 1000, 100);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Registros");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(0, 0, 1000, 100);
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
				controlador.gestionRegistrosToPerfil();
				;
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
				controlador.gestionRegistrosToGestion();
			}
		});
		btnVolver.setBounds(100, 690, 150, 40);
		contentPane.add(btnVolver);

		btnBorrarRegistro = new JButton("Borrar Registro");
		btnBorrarRegistro.setEnabled(false);
		btnBorrarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitarBotones();
				if (confirmacionBorrar() == 0) {
					controlador.solicitudBorrar(this);

					if (modeloGestionDatos.getSeHaBorrado()) {
						borrado();
					}
				}
			}
		});
		btnBorrarRegistro.setBounds(532, 690, 150, 40);
		contentPane.add(btnBorrarRegistro);

		btnAddRegistro = new JButton("A\u00F1adir Registro");
		btnAddRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.gestionRegistrosToGestionRegistrosAddMod();
			}
		});

		btnAddRegistro.setBounds(750, 690, 150, 40);
		contentPane.add(btnAddRegistro);

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
					controlador.solicitudDatosRegistros();
				}

				esconderPrimeraColumna();
			}
		});
		txtBuscador.setText("Buscador");
		txtBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscador.setBounds(728, 111, 140, 22);
		contentPane.add(txtBuscador);
		txtBuscador.setColumns(10);

		lblImportarActividades = new JLabel("Importar Registros");
		lblImportarActividades.setIcon(
				new ImageIcon(GestionActividad.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		lblImportarActividades.setBounds(98, 111, 124, 20);
		contentPane.add(lblImportarActividades);
		lblImportarActividades.setVisible(false);

		btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.guardarCodRegistro();
				habilitarBotones();
				setVisible(false);
				controlador.guardarDatosRegistro();
				controlador.gestionRegistrosToGestionRegistrosAddMod();
			}
		});
		btnModificar.setBounds(316, 690, 150, 40);
		contentPane.add(btnModificar);

		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(234, 111, 429, 23);
		contentPane.add(lblInfo);

		ImageIcon lupa = new ImageIcon("./img/buscar.png");
		lblLupa = new JLabel(lupa);
		lblLupa.setBounds(878, 111, 20, 22);
		contentPane.add(lblLupa);
	}

	// Setter
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas = modeloConsultas;
	}

	public void setModeloGestionDatos(ModeloGestionDatos modeloGestionDatos) {
		this.modeloGestionDatos = modeloGestionDatos;
	}

	// Getter
	public DefaultTableModel getModel() {
		return (DefaultTableModel) tablaRegistros.getModel();
	}

	public String getPrimaryKey() {
		return String.valueOf(tablaRegistros.getValueAt(tablaRegistros.getSelectedRow(), 0));
	}

//	public void utilidadBotones() {
//		if(!txtCod_registro.getText().isEmpty()) {
//			btnBorrarRegistro.setEnabled(true);
//		}else {
//			btnBorrarRegistro.setEnabled(false);
//		}
//	}

	public void habilitarBotones() {
		btnModificar.setEnabled(true);
		btnBorrarRegistro.setEnabled(true);
	}

	public void desHabilitarBotones() {
		btnModificar.setEnabled(false);
		btnBorrarRegistro.setEnabled(false);
	}

	public void borrado() {
		DefaultTableModel model = (DefaultTableModel) tablaRegistros.getModel();
		model.removeRow(tablaRegistros.getSelectedRow());

	}

	public String getPalabraBuscador() {
		return txtBuscador.getText();
	}

	public String getCodRegistro() {
		return String.valueOf(tablaRegistros.getValueAt(tablaRegistros.getSelectedRow(), 0));
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

	private void esconderPrimeraColumna() {
		tablaRegistros.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		tablaRegistros.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		tablaRegistros.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(0);
		tablaRegistros.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaRegistros.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaRegistros.getColumnModel().getColumn(0).setPreferredWidth(0);
	}
	
	public String[] datosFila() {
		String[] array = new String[5];
		array[0] = String.valueOf(tablaRegistros.getValueAt(tablaRegistros.getSelectedRow(), 1));
		array[1] = String.valueOf(tablaRegistros.getValueAt(tablaRegistros.getSelectedRow(), 2));
		array[2] = String.valueOf(tablaRegistros.getValueAt(tablaRegistros.getSelectedRow(), 3));
		array[3] = String.valueOf(tablaRegistros.getValueAt(tablaRegistros.getSelectedRow(), 4));
		array[4] = String.valueOf(tablaRegistros.getValueAt(tablaRegistros.getSelectedRow(), 5));
		return array;
		
	}
}
