package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;

public class GestionActores extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JTable tablaActores;
	private JTextField txtNombre;
	private JPanel HeaderPanel;
	private JScrollPane scrollPane;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private JButton btnAI_actor;
	private JButton btnAddActor;
	private JButton btnModificarActor;
	private JComboBox comboBoxColumna;
	private JTextField textField;
	private JLabel labelImportar;
	private JComboBox comboBoxEdad;
	private JComboBox comboBoxGenero;
	private JComboBox comboBoxIdioma;
	private JComboBox comboBoxComplexion;

	/**
	 * Launch the application.
	 */

	public GestionActores() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				controlador.solicitudDatosActores();
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

		tablaActores = new JTable();
		tablaActores.setRowHeight(40);
		scrollPane.setViewportView(tablaActores);

		txtNombre = new JTextField();
		txtNombre.setBounds(98, 629, 226, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(165, 42, 42));
		HeaderPanel.setBounds(0, 0, 984, 101);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Actores");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(358, 11, 202, 61);
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
				controlador.gestionActoresToPerfil();
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
				controlador.gestionActoresToGestion();
			}
		});
		btnVolver.setBounds(100, 685, 120, 40);
		contentPane.add(btnVolver);

		btnModificarActor = new JButton("Modificar actor");
		btnModificarActor.setBounds(325, 685, 120, 40);
		contentPane.add(btnModificarActor);

		btnAI_actor = new JButton("Activo/Inactivo");
		btnAI_actor.setBounds(575, 685, 120, 40);
		contentPane.add(btnAI_actor);

		btnAddActor = new JButton(" A\u00F1adir actor");
		btnAddActor.setBounds(782, 685, 120, 40);
		contentPane.add(btnAddActor);

		comboBoxColumna = new JComboBox();
		comboBoxColumna.setBounds(761, 127, 104, 20);
		contentPane.add(comboBoxColumna);

		textField = new JTextField();
		textField.setText("Buscador");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);
		textField.setBounds(665, 127, 86, 20);
		contentPane.add(textField);

		labelImportar = new JLabel("Importar Actividades");
		labelImportar.setIcon(
				new ImageIcon(GestionActores.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		labelImportar.setBounds(98, 127, 124, 20);
		contentPane.add(labelImportar);

		comboBoxEdad = new JComboBox();
		comboBoxEdad.setModel(new DefaultComboBoxModel(new String[] { "Edad" }));
		comboBoxEdad.setBounds(334, 629, 121, 30);
		contentPane.add(comboBoxEdad);

		comboBoxGenero = new JComboBox();
		comboBoxGenero.setModel(new DefaultComboBoxModel(new String[] { "G\u00E9nero" }));
		comboBoxGenero.setBounds(465, 629, 103, 30);
		contentPane.add(comboBoxGenero);

		comboBoxIdioma = new JComboBox();
		comboBoxIdioma.setModel(new DefaultComboBoxModel(new String[] { "Idioma" }));
		comboBoxIdioma.setBounds(575, 629, 103, 30);
		contentPane.add(comboBoxIdioma);

		comboBoxComplexion = new JComboBox();
		comboBoxComplexion.setModel(new DefaultComboBoxModel(new String[] { "Complexi\u00F3n" }));
		comboBoxComplexion.setBounds(688, 629, 103, 30);
		contentPane.add(comboBoxComplexion);

		JCheckBox chckbxActivo = new JCheckBox("Activo");
		chckbxActivo.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxActivo.setBounds(801, 633, 97, 23);
		contentPane.add(chckbxActivo);
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
		return (DefaultTableModel) tablaActores.getModel();
	}
}
