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

public class GestionAsignatura extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JTable tablaAsignaturas;
	private JTextField txtCodigoAsignatura;
	private JTextField txtNombre;
	private JTextField txtTitulacion;
	private JTextField txtCurso;
	private JPanel HeaderPanel;
	private JScrollPane scrollPane;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private JButton btnBorrarAsignatura;
	private JButton btnAddAsignatura;
	private JButton btnModificarAsignatura;
	private JTextField txtBuscador;
	private JComboBox comboBoxColumna;
	private JLabel lblImportarActividades;
	private JLabel lblLupa;
	private JLabel lblInfo;

	public GestionAsignatura() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				controlador.solicitudDatosAsignatura();
				btnAddAsignatura.setEnabled(false);
				btnBorrarAsignatura.setEnabled(false);
				btnModificarAsignatura.setEnabled(false);
				

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
		scrollPane.setBounds(100, 145, 800, 450);
		contentPane.add(scrollPane);

		tablaAsignaturas = new JTable();
		tablaAsignaturas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtCodigoAsignatura
						.setText(String.valueOf(tablaAsignaturas.getValueAt(tablaAsignaturas.getSelectedRow(), 0)));
				;
				txtNombre.setText(String.valueOf(tablaAsignaturas.getValueAt(tablaAsignaturas.getSelectedRow(), 1)));
				;
				txtTitulacion
						.setText(String.valueOf(tablaAsignaturas.getValueAt(tablaAsignaturas.getSelectedRow(), 2)));
				;
				txtCurso.setText(String.valueOf(tablaAsignaturas.getValueAt(tablaAsignaturas.getSelectedRow(), 3)));
				;
				habilitarBotones();
			}
		});
		tablaAsignaturas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaAsignaturas.setRowHeight(30);
		tablaAsignaturas.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tablaAsignaturas);

		txtCodigoAsignatura = new JTextField();
		txtCodigoAsignatura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				habilitarBotones();
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				habilitarBotones();

			}
		});
		txtCodigoAsignatura.setBounds(100, 630, 150, 30);
		contentPane.add(txtCodigoAsignatura);
		txtCodigoAsignatura.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				habilitarBotones();
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				habilitarBotones();

			}
		});
		txtNombre.setBounds(325, 630, 150, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtTitulacion = new JTextField();
		txtTitulacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				habilitarBotones();
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				habilitarBotones();

			}

		});
		txtTitulacion.setBounds(545, 630, 150, 30);
		contentPane.add(txtTitulacion);
		txtTitulacion.setColumns(10);

		txtCurso = new JTextField();
		txtCurso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				habilitarBotones();
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				habilitarBotones();

			}
		});
		txtCurso.setBounds(750, 630, 150, 30);
		contentPane.add(txtCurso);
		txtCurso.setColumns(10);

		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(164, 44, 52));
		HeaderPanel.setBounds(0, 0, 1000, 100);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Asignaturas");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(0, 0, 1000, 100);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		HeaderPanel.add(lblTitulo);
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setVerticalAlignment(JLabel.CENTER);

		ImageIcon ueIcon = new ImageIcon("./img/ue.png");
		lblUemLogo = new JLabel(ueIcon);
		lblUemLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUemLogo.setBounds(50, 0, 100, 100);
		HeaderPanel.add(lblUemLogo);

		ImageIcon perfilIcon = new ImageIcon("./img/usuario.png");
		lblPerfil = new JLabel(perfilIcon);
		lblPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				controlador.gestionAsignaturaToPerfil();
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
				habilitarBotones();
				setVisible(false);
				limpiarTxt();
				controlador.gestionAsignaturaToGestion();

			}
		});
		btnVolver.setBounds(100, 685, 150, 40);
		contentPane.add(btnVolver);

		btnModificarAsignatura = new JButton("Modificar");
		btnModificarAsignatura.setEnabled(false);
		btnModificarAsignatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.modAsignatura();
				if (modeloGestionDatos.getSeHaCreado()) {
					modAsignatura();
				}

			}
		});
		btnModificarAsignatura.setBounds(325, 685, 150, 40);
		contentPane.add(btnModificarAsignatura);

		btnBorrarAsignatura = new JButton("Borrar asignatura");
		btnBorrarAsignatura.setEnabled(false);
		btnBorrarAsignatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.solicitudBorrar(this);
				if (modeloGestionDatos.getSeHaBorrado()) {
					borrado();
				} else {
					JOptionPane.showMessageDialog(null, "Esta asignatura esta siendo utilizada por alguna actividad");
				}
				habilitarBotones();
			}
		});
		btnBorrarAsignatura.setBounds(545, 685, 150, 40);
		contentPane.add(btnBorrarAsignatura);

		btnAddAsignatura = new JButton(" A\u00F1adir asignatura");
		btnAddAsignatura.setEnabled(false);
		btnAddAsignatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.crearAsignatura();
				if (modeloGestionDatos.getSeHaCreado()) {
					addAsignatura();
				}
				habilitarBotones();
			}
		});
		btnAddAsignatura.setBounds(750, 685, 150, 40);
		contentPane.add(btnAddAsignatura);

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
					controlador.solicitudDatosAsignatura();
				}
			}
		});
		txtBuscador.setText("Buscador");
		txtBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscador.setBounds(728, 111, 140, 22);
		contentPane.add(txtBuscador);
		txtBuscador.setColumns(10);

		lblImportarActividades = new JLabel("Importar Asignaturas");
		lblImportarActividades.setIcon(
				new ImageIcon(GestionActividad.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		lblImportarActividades.setBounds(98, 111, 124, 20);
		contentPane.add(lblImportarActividades);
		lblImportarActividades.setVisible(false);

		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(234, 111, 429, 23);
		contentPane.add(lblInfo);

		ImageIcon lupa = new ImageIcon("./img/buscar.png");
		lblLupa = new JLabel(lupa);
		lblLupa.setBounds(878, 111, 20, 22);
		contentPane.add(lblLupa);

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

	public DefaultTableModel getModel() {
		return (DefaultTableModel) tablaAsignaturas.getModel();
	}

	public String getPrimaryKey() {
		return String.valueOf(tablaAsignaturas.getValueAt(tablaAsignaturas.getSelectedRow(), 0));
	}

	public String getPalabraBuscador() {
		return txtBuscador.getText();
	}

	public String getCodigoAsignatura() {
		return txtCodigoAsignatura.getText();
	}

	public String getNombre() {
		return txtNombre.getText();
	}

	public String getTitulacion() {
		return txtTitulacion.getText();
	}

	public String getCurso() {
		return txtCurso.getText();
	}

	public void addAsignatura() {
		DefaultTableModel model = (DefaultTableModel) tablaAsignaturas.getModel();
		model.addRow(modeloGestionDatos.getDatosfilasTabla());
		limpiarTxt();
	}

	public void borrado() {
		DefaultTableModel model = (DefaultTableModel) tablaAsignaturas.getModel();
		model.removeRow(tablaAsignaturas.getSelectedRow());
		limpiarTxt();
	}

	public void modAsignatura() {
		DefaultTableModel model = (DefaultTableModel) tablaAsignaturas.getModel();
		if (getCodigoAsignatura().equals(String.valueOf(model.getValueAt(tablaAsignaturas.getSelectedRow(), 0)))) {
			lblInfo.setText("Sala modificada");
			model.setValueAt(getCodigoAsignatura(), tablaAsignaturas.getSelectedRow(), 0);
			model.setValueAt(getNombre(), tablaAsignaturas.getSelectedRow(), 1);
			model.setValueAt(getTitulacion(), tablaAsignaturas.getSelectedRow(), 2);
			model.setValueAt(getCurso(), tablaAsignaturas.getSelectedRow(), 3);
			limpiarTxt();
		} else {
			lblInfo.setText("Error , no puedes modificar el codigo de la asignatura");
		}

	}

	public void habilitarBotones() {
		// btnAlta
		if (!txtCodigoAsignatura.getText().equals("") && !txtCurso.getText().equals("")
				&& !txtNombre.getText().equals("") && !txtTitulacion.getText().equals("")) {
			btnAddAsignatura.setEnabled(true);
		} else {
			btnAddAsignatura.setEnabled(false);
		}

		// btnmodificar
		if (tablaAsignaturas.getSelectedRowCount() == 1 && !txtCodigoAsignatura.getText().equals("")
				&& !txtCurso.getText().equals("") && !txtNombre.getText().equals("")
				&& !txtTitulacion.getText().equals("")) {
			btnModificarAsignatura.setEnabled(true);
		} else {
			btnModificarAsignatura.setEnabled(false);
		}

		// btnBorrar
		if (tablaAsignaturas.getSelectedRowCount() == 1 && !txtCodigoAsignatura.getText().equals("")) {
			btnBorrarAsignatura.setEnabled(true);
		} else {
			btnBorrarAsignatura.setEnabled(false);
		}

	}

	private void limpiarTxt() {
		txtCodigoAsignatura.setText("");
		txtNombre.setText("");
		txtCurso.setText("");
		txtTitulacion.setText("");

	}

	public void actualizarInfoDatos() {
		lblInfo.setText(modeloGestionDatos.getRespuesta());
	}

}
