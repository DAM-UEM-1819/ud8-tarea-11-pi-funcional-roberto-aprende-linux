package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.*;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class GestionAlumnos extends JFrame {
	
	private Controlador controlador;
	private Modelo modelo;
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
	private JButton btnActivoInactivo;
	private JCheckBox chckbxActivoInactivo;
	private JLabel labelImportar;
	private JTextField txtBuscador;
	private JComboBox comboBoxColumna;


	public GestionAlumnos() {
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
		tablaAlumnos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Expediente", "Nombre y apellidos", "Activo"
			}
		));
		tablaAlumnos.setRowHeight(30);
		scrollPaneRegistros.setViewportView(tablaAlumnos);

		txtExpediente = new JTextField();
		txtExpediente.setBounds(125, 600, 200, 30);
		contentPane.add(txtExpediente);
		txtExpediente.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(407, 600, 200, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.gestionAlumnosToGestion();
			}
		});
		btnVolver.setBounds(100, 685, 120, 40);
		contentPane.add(btnVolver);

		btnModificarAlumno = new JButton("Modificar Alumno");
		btnModificarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificarAlumno.setBounds(325, 685, 120, 40);
		contentPane.add(btnModificarAlumno);

		btnAddAlumno = new JButton("A\u00F1adir Alumno");
		btnAddAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnActivoInactivo = new JButton("Activo/Inactivo");
		btnActivoInactivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnActivoInactivo.setBounds(575, 685, 120, 40);
		contentPane.add(btnActivoInactivo);
		btnAddAlumno.setBounds(782, 685, 120, 40);
		contentPane.add(btnAddAlumno);
		
		Header = new JPanel();
		Header.setBackground(new Color(165, 42, 42));
		Header.setBounds(0, 0, 984, 100);
		contentPane.add(Header);
		Header.setLayout(null);
		
		lblAlumnos = new JLabel("Alumnos");
		lblAlumnos.setForeground(new Color(255, 255, 255));
		lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlumnos.setBounds(250, 0, 500, 100);
		lblAlumnos.setFont(new Font("Tahoma", Font.PLAIN, 50));
		Header.add(lblAlumnos);
		
		lblUemLogo = new JLabel("Aqui Iria el logo");
		lblUemLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUemLogo.setBounds(0, 0, 240, 100);
		Header.add(lblUemLogo);
		
		lblPerfil = new JLabel("Aqui Iria el logo");
		lblPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerfil.setBounds(760, 0, 224, 100);
		Header.add(lblPerfil);
		
		chckbxActivoInactivo = new JCheckBox("Activo/Inactivo");
		chckbxActivoInactivo.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxActivoInactivo.setBounds(679, 602, 200, 23);
		contentPane.add(chckbxActivoInactivo);
		
		labelImportar = new JLabel("Importar Actividades");
		labelImportar.setIcon(new ImageIcon(GestionAlumnos.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		labelImportar.setBounds(100, 111, 124, 20);
		contentPane.add(labelImportar);
		
		txtBuscador = new JTextField();
		txtBuscador.setText("Buscador");
		txtBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscador.setColumns(10);
		txtBuscador.setBounds(667, 111, 86, 20);
		contentPane.add(txtBuscador);
		
		comboBoxColumna = new JComboBox();
		comboBoxColumna.setModel(new DefaultComboBoxModel(new String[] {"Columna", "Expediente", "Nombre y apellido"}));
		comboBoxColumna.setBounds(763, 111, 104, 20);
		contentPane.add(comboBoxColumna);
	}
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	public void modificarAlumnoAlerta() {
		JOptionPane.showConfirmDialog(rootPane, "�Desea modificar el usuario seleccionado?");
	}
	
	public void setModelo(Modelo modelo) {
		this.modelo= modelo;
	}
}
