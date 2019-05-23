package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;

public class GestionProfesores extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JTable tablaProfesores;
	private JTextField txtNumero;
	private JTextField txtNombre;
	private JTextField txtTitulacion;
	private JTextField txtDni;
	private JTextField txtRelacion_laboral;
	private JTextField txtTelefono1;
	private JPanel HeaderPanel;
	private JScrollPane scrollPane;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private JButton btnAI_profesor;
	private JButton btnAddProfesor;
	private JButton btnModificarProfesor;
	private JTextField txtTelefono2;
	private JTextField txtEmail1;
	private JTextField txtEmail2;
	private JCheckBox chckbxAI_profesores;
	private JLabel lblImportarActividades;
	private JComboBox comboBoxColumna;
	private JTextField txtBuscador;

	public GestionProfesores() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				controlador.solicitudDatosProfesores();
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
		scrollPane.setBounds(98, 168, 800, 450);
		contentPane.add(scrollPane);

		tablaProfesores = new JTable();
		tablaProfesores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaProfesores.setRowHeight(40);
		tablaProfesores.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tablaProfesores);

		txtNumero = new JTextField();
		txtNumero.setBounds(108, 629, 71, 30);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(189, 629, 71, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtTitulacion = new JTextField();
		txtTitulacion.setBounds(276, 629, 71, 30);
		contentPane.add(txtTitulacion);
		txtTitulacion.setColumns(10);

		txtDni = new JTextField();
		txtDni.setBounds(363, 629, 71, 30);
		contentPane.add(txtDni);
		txtDni.setColumns(10);

		txtRelacion_laboral = new JTextField();
		txtRelacion_laboral.setBounds(500, 629, 71, 30);
		contentPane.add(txtRelacion_laboral);
		txtRelacion_laboral.setColumns(10);

		txtTelefono1 = new JTextField();
		txtTelefono1.setColumns(10);
		txtTelefono1.setBounds(581, 629, 71, 30);
		contentPane.add(txtTelefono1);

		txtTelefono2 = new JTextField();
		txtTelefono2.setBounds(662, 629, 71, 30);
		contentPane.add(txtTelefono2);
		txtTelefono2.setColumns(10);

		txtEmail1 = new JTextField();
		txtEmail1.setColumns(10);
		txtEmail1.setBounds(743, 629, 71, 30);
		contentPane.add(txtEmail1);

		txtEmail2 = new JTextField();
		txtEmail2.setColumns(10);
		txtEmail2.setBounds(824, 629, 71, 30);
		contentPane.add(txtEmail2);

		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(165, 42, 42));
		HeaderPanel.setBounds(0, 0, 984, 101);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Profesores");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(358, 11, 266, 61);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		HeaderPanel.add(lblTitulo);
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setVerticalAlignment(JLabel.CENTER);

		ImageIcon ueIcon = new ImageIcon("./img/ue.png");
		lblUemLogo = new JLabel(ueIcon);
		lblUemLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUemLogo.setBounds(0, 0, 240, 100);
		HeaderPanel.add(lblUemLogo);

		ImageIcon perfilIcon = new ImageIcon("./img/usuario.png");
		lblPerfil = new JLabel(perfilIcon);
		lblPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				controlador.gestionProfesoresToPerfil();
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
		HeaderPanel.add(lblPerfil);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.gestionProfesoresToGestion();
			}
		});
		btnVolver.setBounds(100, 685, 120, 40);
		contentPane.add(btnVolver);

		btnModificarProfesor = new JButton("Modificar profesor");
		btnModificarProfesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showConfirmDialog(rootPane, "�Desea modificar el profesor seleccionado?");
			}
		});
		btnModificarProfesor.setBounds(325, 685, 120, 40);
		contentPane.add(btnModificarProfesor);

		btnAI_profesor = new JButton("Activo/Inactivo");
		btnAI_profesor.setBounds(575, 685, 120, 40);
		contentPane.add(btnAI_profesor);

		btnAddProfesor = new JButton(" A\u00F1adir profesores");
		btnAddProfesor.setBounds(774, 685, 128, 40);
		contentPane.add(btnAddProfesor);

		chckbxAI_profesores = new JCheckBox("" + "A/I");
		chckbxAI_profesores.setBounds(439, 633, 41, 23);
		contentPane.add(chckbxAI_profesores);

		txtBuscador = new JTextField();
		txtBuscador.setText("Buscador");
		txtBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscador.setBounds(812, 132, 86, 20);
		contentPane.add(txtBuscador);
		txtBuscador.setColumns(10);

		lblImportarActividades = new JLabel("Importar Profesores");
		lblImportarActividades.setIcon(
				new ImageIcon(GestionActividad.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		lblImportarActividades.setBounds(98, 127, 124, 20);
		contentPane.add(lblImportarActividades);
		lblImportarActividades.setVisible(false);
	}
	//Setters

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas = modeloConsultas;
	}

	public void setModeloGestionDatos(ModeloGestionDatos modeloGestionDatos) {
		this.modeloGestionDatos = modeloGestionDatos;
	}
	//Getters

	public String getNumero() {
		return txtNumero.getText();
	}

	public String getNombre() {
		return txtNombre.getText();
	}

	public String getTitulacion() {
		return txtTitulacion.getText();
	}

	public String getDni() {
		return txtDni.getText();
	}

	public String getRelacion_laboral() {
		return txtRelacion_laboral.getText();
	}

	public String getTelefono1() {
		return txtTelefono1.getText();
	}

	public String getTelefono2() {
		return txtTelefono2.getText();
	}

	public String getEmail1() {
		return txtEmail1.getText();
	}

	public String getEmail2() {
		return txtEmail2.getText();
	}

	public DefaultTableModel getModel() {
		return (DefaultTableModel) tablaProfesores.getModel();
	}
}
