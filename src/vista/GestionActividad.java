package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class GestionActividad extends JFrame {
	
	private Controlador controlador;
	private Modelo modelo;
	private JPanel contentPane;
	private JTable tablaActividad;
	private JTextField txtNombre;
	private JTextField txtDocumentacion_tecnica;
	private JTextField txtHorasActividad;
	private JTextField txtCod_asignatura;
	private JTextField txtSimulador;
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



	 
	public GestionActividad() {
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

		tablaActividad = new JTable();
		tablaActividad.setModel(new DefaultTableModel(
			new Object[][] {
				{"Seg paciente 1", "Taller de habilidades", "2", "2", "01411", "ISTAN", null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Nombre", "Tipo de actividad", "Tipo de sala", "Simulador", "Documentación", "Horas de actividad", "Año académico"
			}
		));
		tablaActividad.setRowHeight(30);
		scrollPane.setViewportView(tablaActividad);

		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setText("Nombre");
		txtNombre.setBounds(108, 629, 114, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtDocumentacion_tecnica = new JTextField();
		txtDocumentacion_tecnica.setHorizontalAlignment(SwingConstants.CENTER);
		txtDocumentacion_tecnica.setText("Tipo de Sala");
		txtDocumentacion_tecnica.setBounds(367, 629, 86, 30);
		contentPane.add(txtDocumentacion_tecnica);
		txtDocumentacion_tecnica.setColumns(10);

		txtHorasActividad = new JTextField();
		txtHorasActividad.setText("Horas de actividad");
		txtHorasActividad.setHorizontalAlignment(SwingConstants.CENTER);
		txtHorasActividad.setBounds(692, 629, 114, 30);
		contentPane.add(txtHorasActividad);
		txtHorasActividad.setColumns(10);

		txtCod_asignatura = new JTextField();
		txtCod_asignatura.setText("Documentaci\u00F3n");
		txtCod_asignatura.setHorizontalAlignment(SwingConstants.CENTER);
		txtCod_asignatura.setBounds(583, 629, 86, 30);
		contentPane.add(txtCod_asignatura);
		txtCod_asignatura.setColumns(10);

		txtSimulador = new JTextField();
		txtSimulador.setHorizontalAlignment(SwingConstants.CENTER);
		txtSimulador.setText("A\u00F1o");
		txtSimulador.setColumns(10);
		txtSimulador.setBounds(820, 629, 58, 30);
		contentPane.add(txtSimulador);
		


		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(165, 42, 42));
		HeaderPanel.setBounds(0, 0, 984, 101);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Actividad");
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
				controlador.gestionActividadToGestion();
			}
		});
		btnVolver.setBounds(100, 685, 120, 40);
		contentPane.add(btnVolver);

		btnModificarActividad = new JButton("Modificar actividad");
		btnModificarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showConfirmDialog(rootPane, "¿Desea modificar el profesor seleccionado?" );
			}
		});
		btnModificarActividad.setBounds(325, 685, 128, 40);
		contentPane.add(btnModificarActividad);

		btnBorrarActividad = new JButton("Borrar actividad");
		btnBorrarActividad.setBounds(575, 685, 120, 40);
		contentPane.add(btnBorrarActividad);

		btnAddActividad = new JButton(" A\u00F1adir actividad");
		btnAddActividad.setBounds(774, 685, 128, 40);
		contentPane.add(btnAddActividad);
		
		comboBoxTipoActividad = new JComboBox();
		comboBoxTipoActividad.setModel(new DefaultComboBoxModel(new String[] {"Tipo de actividad"}));
		comboBoxTipoActividad.setBounds(246, 629, 98, 30);
		contentPane.add(comboBoxTipoActividad);
		
		comboBoxSimulador = new JComboBox();
		comboBoxSimulador.setModel(new DefaultComboBoxModel(new String[] {"Simulador"}));
		comboBoxSimulador.setBounds(487, 629, 71, 30);
		contentPane.add(comboBoxSimulador);
		
		txtBuscador = new JTextField();
		txtBuscador.setText("Buscador");
		txtBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscador.setBounds(665, 127, 86, 20);
		contentPane.add(txtBuscador);
		txtBuscador.setColumns(10);
		
		comboBoxColumna = new JComboBox();
		comboBoxColumna.setModel(new DefaultComboBoxModel(new String[] {"Columna", "Nombre", "Tipo de actividad", "Tipo de sala", "Simulador", "Documentaci\u00F3n", "Horas de actividad", "A\u00F1o acad\u00E9mico"}));
		comboBoxColumna.setBounds(761, 127, 104, 20);
		contentPane.add(comboBoxColumna);
		
		lblImportarActividades = new JLabel("Importar Actividades");
		lblImportarActividades.setIcon(new ImageIcon(GestionActividad.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		lblImportarActividades.setBounds(98, 127, 124, 20);
		contentPane.add(lblImportarActividades);
		
	
	}
	
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	public void setModelo(Modelo modelo) {
		this.modelo= modelo;
	}
}
