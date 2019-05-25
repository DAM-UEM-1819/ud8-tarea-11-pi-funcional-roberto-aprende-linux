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
import javax.swing.JCheckBox;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controlador.Controlador;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class GestionAlumnos extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JTable tablaAlumnos;
	private JTextField txtExpediente;
	private JTextField txtNombre;
	private JButton btnVolver;
	private JButton btnModificarAlumno;
	private JButton btnAddAlumno;
	private JPanel Header;
	private JLabel lblAlumnos;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JScrollPane scrollPaneRegistros;
	private JButton btnActivoAlumno;
	private JCheckBox chckbxActivoInactivo;
	private JLabel lblImportarAlumnos;
	private JTextField txtBuscador;
	private JLabel lblInfo;
	private JLabel lblLupa;

	public GestionAlumnos() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				controlador.solicitudDatosAlumnos();
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

		scrollPaneRegistros = new JScrollPane();
		scrollPaneRegistros.setBounds(100, 145, 800, 450);
		contentPane.add(scrollPaneRegistros);

		tablaAlumnos = new JTable();
		tablaAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaAlumnos.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		tablaAlumnos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				actualizarDatos();
			}

		});
		tablaAlumnos.setRowHeight(30);
		tablaAlumnos.getTableHeader().setReorderingAllowed(false);
		
		scrollPaneRegistros.setViewportView(tablaAlumnos);

		txtExpediente = new JTextField();
		txtExpediente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				habilitarBotones();
			}
			public void keyPressed(KeyEvent arg0) {
				habilitarBotones();
			}
			
		});
		txtExpediente.setBounds(129, 611, 200, 30);
		contentPane.add(txtExpediente);
		txtExpediente.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				habilitarBotones();
			}
		
		
			@Override
			public void keyPressed(KeyEvent arg0) {
				habilitarBotones();
			}
		});
		txtNombre.setBounds(410, 611, 200, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTxt();
				setVisible(false);
				controlador.gestionAlumnosToGestion();
			}
		});
		btnVolver.setBounds(100, 685, 150, 40);
		contentPane.add(btnVolver);

		btnModificarAlumno = new JButton("Modificar Alumno");
		btnModificarAlumno.setEnabled(false);
		btnModificarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.solicitudModificarAlumno();
				modAlumno();
			}
		});
		btnModificarAlumno.setBounds(316, 685, 150, 40);
		contentPane.add(btnModificarAlumno);

		btnAddAlumno = new JButton("A\u00F1adir Alumno");
		btnAddAlumno.setEnabled(false);
		btnAddAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.solicitudcrearAlumno();
				addAlumno();
			}
		});
		btnActivoAlumno = new JButton("Activo/Inactivo");
		btnActivoAlumno.setEnabled(false);
		btnActivoAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (estadoCheckBox() == 1) {
					chckbxActivoInactivo.setSelected(false);
				} else {
					chckbxActivoInactivo.setSelected(true);
				}
				controlador.solicitudBorrar(this);
				if (modeloGestionDatos.getSeHaCambiadoEstado()) {
					activoAlumno();
				}

			}
		});

		btnActivoAlumno.setBounds(532, 685, 150, 40);
		contentPane.add(btnActivoAlumno);
		btnAddAlumno.setBounds(748, 685, 150, 40);
		contentPane.add(btnAddAlumno);

		Header = new JPanel();
		Header.setBackground(new Color(164,44,52));
		Header.setBounds(0, 0, 1000, 100);
		contentPane.add(Header);
		Header.setLayout(null);

		lblAlumnos = new JLabel("Alumnos");
		lblAlumnos.setForeground(new Color(255, 255, 255));
		lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlumnos.setBounds(0, 0, 1000, 100);
		lblAlumnos.setFont(new Font("Tahoma", Font.PLAIN, 50));
		Header.add(lblAlumnos);

		ImageIcon ueIcon = new ImageIcon("./img/ue.png");
		lblUemLogo = new JLabel(ueIcon);
		lblUemLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUemLogo.setBounds(0, 0, 240, 100);
		Header.add(lblUemLogo);

		ImageIcon perfilIcon = new ImageIcon("./img/usuario.png");
		lblPerfil = new JLabel(perfilIcon);
		lblPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				controlador.gestionAlumnosToPerfil();
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
		lblPerfil.setBounds(818, 0, 100, 100);

		Header.add(lblPerfil);

		chckbxActivoInactivo = new JCheckBox("Activo/Inactivo");
		chckbxActivoInactivo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				habilitarBotones();
			}
		});
		chckbxActivoInactivo.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxActivoInactivo.setBounds(681, 615, 200, 23);
		contentPane.add(chckbxActivoInactivo);

		lblImportarAlumnos = new JLabel("Importar Alumnos");
		lblImportarAlumnos.setIcon(
				new ImageIcon(GestionAlumnos.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		lblImportarAlumnos.setBounds(100, 111, 124, 20);
		contentPane.add(lblImportarAlumnos);
		lblImportarAlumnos.setVisible(false);
		

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
					controlador.solicitudDatosAlumnos();
				}
			}
		});
		txtBuscador.setText("Buscador");
		txtBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscador.setBounds(728, 111, 140, 22);
		contentPane.add(txtBuscador);


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

	public void modificarAlumnoAlerta() {
		JOptionPane.showConfirmDialog(rootPane, "�Desea modificar el usuario seleccionado?");
	}

	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas = modeloConsultas;
	}

	public void setModeloGestionDatos(ModeloGestionDatos modeloGestionDatos) {
		this.modeloGestionDatos = modeloGestionDatos;
	}

	public DefaultTableModel getModel() {
		return (DefaultTableModel) tablaAlumnos.getModel();
	}

	// getters
	
	public String getPrimaryKey() {
		return String.valueOf(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 0));
	}
	
	public String getActividad() {
		return String.valueOf(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 2));
	}

	public String getExp() {
		return txtExpediente.getText();
	}

	public String getNombre() {
		return txtNombre.getText();
	}

	public void addAlumno() {
		DefaultTableModel model = (DefaultTableModel) tablaAlumnos.getModel();
		model.addRow(modeloGestionDatos.getDatosfilasTabla());
	}

		public void activoAlumno() {
		DefaultTableModel model = (DefaultTableModel) tablaAlumnos.getModel();
		if (getExp().equals(String.valueOf(model.getValueAt(tablaAlumnos.getSelectedRow(), 0)))) {
			lblInfo.setText("Se ha cambiado estado alumno");
			model.setValueAt(getNombre(), tablaAlumnos.getSelectedRow(), 1);
			model.setValueAt(estadoCheckBox(), tablaAlumnos.getSelectedRow(), 2);

		} else {
			lblInfo.setText("No se ha cambiado estado alumno");
		}

	}

		public void modAlumno() {
		DefaultTableModel model = (DefaultTableModel) tablaAlumnos.getModel();
		// model.setValueAt(getExp(),tablaAlumnos.getSelectedRow(), 0);

		if (getExp().equals(String.valueOf(model.getValueAt(tablaAlumnos.getSelectedRow(), 0)))) {
			model.setValueAt(getNombre(), tablaAlumnos.getSelectedRow(), 1);
			model.setValueAt(estadoCheckBox(), tablaAlumnos.getSelectedRow(), 2);

		} else {
			lblInfo.setText("No se ha podido modificar alumno");
		}

	}

	public void actualizarInfo() {
		lblInfo.setText(modeloGestionDatos.getRespuesta());
	}

	private void actualizarDatos() {
		txtExpediente.setText(String.valueOf(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 0)));
		txtNombre.setText(String.valueOf(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 1)));
		if (Integer.parseInt(String.valueOf(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 2))) == 1) {
			chckbxActivoInactivo.setSelected(true);
		} else {
			chckbxActivoInactivo.setSelected(false);
		}
		habilitarBotones();

	}

	public int estadoCheckBox() {
		int resultado = chckbxActivoInactivo.isSelected() == true ? 1 : 0;
		return resultado;
	}
	
	public String getPalabraBuscador() {
		return txtBuscador.getText();
	}
	private void limpiarTxt() {
		txtExpediente.setText("");
		txtNombre.setText("");
		chckbxActivoInactivo.setSelected(false);
	}
	
		public void habilitarBotones() {
		// btnAlta
		if (!txtExpediente.getText().equals("") && !txtNombre.getText().equals("")) {
			btnAddAlumno.setEnabled(true);
		} else {
			btnAddAlumno.setEnabled(false);
		}

		// btnmodificar
		if (tablaAlumnos.getSelectedRowCount() == 1 && !txtExpediente.getText().equals("")
				&& !txtNombre.getText().equals("")) {
			btnModificarAlumno.setEnabled(true);
		} else {
			btnModificarAlumno.setEnabled(false);
		}

		// btnBorrar
		if (tablaAlumnos.getSelectedRowCount() == 1 && !txtExpediente.getText().equals("")
				&& !txtNombre.getText().equals("")) {
			btnActivoAlumno.setEnabled(true);
		} else {
			btnActivoAlumno.setEnabled(false);
		}
	}
}
