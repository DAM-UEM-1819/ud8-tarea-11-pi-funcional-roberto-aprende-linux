package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

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
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
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

public class GestionUsuarios extends JFrame {

	private Controlador controlador;
	private Modelo modelo;
	private JPanel contentPane;
	private JTable tablaUsuarios;
	private JTextField txtUsuario;
	private JTextField txtRol;
	private JButton btnVolver;
	private JButton btnBorrarUsr;
	private JButton btnAddUsuario;
	private JPanel Header;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JScrollPane scrollPaneRegistros;
	private JLabel lblImportarActividades;
	private JTextField txtBuscador;
	private JComboBox comboBoxColumna;

	public GestionUsuarios() {
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

		tablaUsuarios = new JTable();
		tablaUsuarios.setModel(new DefaultTableModel(new Object[][] { { "Pedro", "Admin" }, { null, null }, },
				new String[] { "Usuarios", "Rol" }));
		tablaUsuarios.setRowHeight(30);
		scrollPaneRegistros.setViewportView(tablaUsuarios);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(125, 600, 350, 30);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtRol = new JTextField();
		txtRol.setBounds(525, 600, 350, 30);
		contentPane.add(txtRol);
		txtRol.setColumns(10);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.gestionUsuariosToGestion();
			}
		});
		btnVolver.setBounds(100, 685, 120, 40);
		contentPane.add(btnVolver);

		btnBorrarUsr = new JButton("Borrar Usuario");
		btnBorrarUsr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBorrarUsr.setBounds(440, 685, 120, 40);
		contentPane.add(btnBorrarUsr);

		btnAddUsuario = new JButton("A\u00F1adir Usuario");
		btnAddUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.gestionUsuarioToCrearUsuario();
			}
		});
		btnAddUsuario.setBounds(782, 685, 120, 40);
		contentPane.add(btnAddUsuario);

		Header = new JPanel();
		Header.setBackground(new Color(165, 42, 42));
		Header.setBounds(0, 0, 984, 100);
		contentPane.add(Header);
		Header.setLayout(null);

		lblTitulo = new JLabel("Usuarios");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(250, 0, 500, 100);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		Header.add(lblTitulo);

		lblUemLogo = new JLabel("Aqui Iria el logo");
		lblUemLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUemLogo.setBounds(0, 0, 240, 100);
		Header.add(lblUemLogo);

		lblPerfil = new JLabel("Aqui Iria el logo");
		lblPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerfil.setBounds(760, 0, 224, 100);
		Header.add(lblPerfil);

		txtBuscador = new JTextField();
		txtBuscador.setText("Buscador");
		txtBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscador.setBounds(663, 114, 86, 20);
		contentPane.add(txtBuscador);
		txtBuscador.setColumns(10);

		comboBoxColumna = new JComboBox();
		comboBoxColumna.setModel(new DefaultComboBoxModel(new String[] { "Columna", "Usuarios", "Rol" }));
		comboBoxColumna.setBounds(759, 114, 104, 20);
		contentPane.add(comboBoxColumna);

		lblImportarActividades = new JLabel("Importar Usuarios");
		lblImportarActividades.setIcon(
				new ImageIcon(GestionActividad.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		lblImportarActividades.setBounds(100, 114, 124, 20);
		contentPane.add(lblImportarActividades);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void borrarUsuarioAlerta() {
		JOptionPane.showConfirmDialog(rootPane, "�Desea borrar el usuario seleccionado?");
	}
	
	public void setModelo(Modelo modelo) {
		this.modelo= modelo;
	}
}
