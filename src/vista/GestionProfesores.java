package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class GestionProfesores extends JFrame {
	
	private Controlador controlador;
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

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public GestionProfesores() {
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
		tablaProfesores.setModel(new DefaultTableModel(
				new Object[][] { { "1", "Raul", "20", "Hombre", "Bilingue", "Normal", "1" },
						{ null, null, null, null, null, null, null }, },
				new String[] { "Numero", "Nombre", "Titulación", "DNI","Activo","Relacion","Telefono 1","Telefono 2","Mail 1","Mail 2" }));
		tablaProfesores.setRowHeight(40);
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

		lblUemLogo = new JLabel("Aqui Iria el logo");
		lblUemLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUemLogo.setBounds(0, 0, 240, 100);
		HeaderPanel.add(lblUemLogo);

		lblPerfil = new JLabel("Aqui Iria el logo");
		lblPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerfil.setBounds(760, 0, 224, 100);
		HeaderPanel.add(lblPerfil);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.gestionProfesoresToGestion();
			}
		});
		btnVolver.setBounds(100, 685, 120, 40);
		contentPane.add(btnVolver);

		btnModificarProfesor = new JButton("Modificar profesor");
		btnModificarProfesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showConfirmDialog(rootPane, "¿Desea modificar el profesor seleccionado?" );
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
		
		chckbxAI_profesores = new JCheckBox(""
				+ "A/I");
		chckbxAI_profesores.setBounds(439, 633, 41, 23);
		contentPane.add(chckbxAI_profesores);
		
		txtBuscador = new JTextField();
		txtBuscador.setText("Buscador");
		txtBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscador.setBounds(665, 127, 86, 20);
		contentPane.add(txtBuscador);
		txtBuscador.setColumns(10);
		
		comboBoxColumna = new JComboBox();
		comboBoxColumna.setModel(new DefaultComboBoxModel(new String[] {"Columna", "Numero", "Nombre", "Titulación", "DNI","Activo","Relacion","Telefono 1","Telefono 2","Mail 1","Mail 2" }));
		comboBoxColumna.setBounds(761, 127, 104, 20);
		contentPane.add(comboBoxColumna);
		
		lblImportarActividades = new JLabel("Importar Profesores");
		lblImportarActividades.setIcon(new ImageIcon(GestionActividad.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		lblImportarActividades.setBounds(98, 127, 124, 20);
		contentPane.add(lblImportarActividades);
	}
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
}
