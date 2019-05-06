package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ComboBoxEditor;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.*;

public class GestionAsignatura extends JFrame {

	private Controlador controlador;
	private Modelo modelo;
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

	public GestionAsignatura() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				controlador.solicitudDatosAsignatura();
			}
		});
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

		tablaAsignaturas = new JTable();
		tablaAsignaturas.setRowHeight(40);
		scrollPane.setViewportView(tablaAsignaturas);

		txtCodigoAsignatura = new JTextField();
		txtCodigoAsignatura.setBounds(108, 629, 190, 30);
		contentPane.add(txtCodigoAsignatura);
		txtCodigoAsignatura.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(308, 630, 190, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtTitulacion = new JTextField();
		txtTitulacion.setBounds(508, 630, 190, 30);
		contentPane.add(txtTitulacion);
		txtTitulacion.setColumns(10);

		txtCurso = new JTextField();
		txtCurso.setBounds(708, 630, 182, 30);
		contentPane.add(txtCurso);
		txtCurso.setColumns(10);

		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(165, 42, 42));
		HeaderPanel.setBounds(0, 0, 984, 101);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Asignaturas");
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
				controlador.gestionAsignaturaToGestion();
			}
		});
		btnVolver.setBounds(100, 685, 120, 40);
		contentPane.add(btnVolver);

		btnModificarAsignatura = new JButton("Modificar asignatura");
		btnModificarAsignatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showConfirmDialog(rootPane, "¿Desea modificar el profesor seleccionado?");
			}
		});
		btnModificarAsignatura.setBounds(325, 685, 135, 40);
		contentPane.add(btnModificarAsignatura);

		btnBorrarAsignatura = new JButton("Borrar asignatura");
		btnBorrarAsignatura.setBounds(575, 685, 120, 40);
		contentPane.add(btnBorrarAsignatura);

		btnAddAsignatura = new JButton(" A\u00F1adir asignatura");
		btnAddAsignatura.setBounds(774, 685, 128, 40);
		contentPane.add(btnAddAsignatura);

		txtBuscador = new JTextField();
		txtBuscador.setText("Buscador");
		txtBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscador.setBounds(665, 127, 86, 20);
		contentPane.add(txtBuscador);
		txtBuscador.setColumns(10);

		comboBoxColumna = new JComboBox();
		comboBoxColumna.setModel(new DefaultComboBoxModel(
				new String[] { "Columna", "Codigo Registro", "Fecha", "Hora", "Hora Profesor", "Actividad nombre" }));
		comboBoxColumna.setBounds(761, 127, 104, 20);
		contentPane.add(comboBoxColumna);

		lblImportarActividades = new JLabel("Importar Asignaturas");
		lblImportarActividades.setIcon(
				new ImageIcon(GestionActividad.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		lblImportarActividades.setBounds(98, 127, 124, 20);
		contentPane.add(lblImportarActividades);

	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public DefaultTableModel getModel() {
		return (DefaultTableModel) tablaAsignaturas.getModel();
	}
}
