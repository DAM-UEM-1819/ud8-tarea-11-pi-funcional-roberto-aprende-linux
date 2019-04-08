package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ResourceBundle.Control;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CrearUsuario extends JFrame {
	
	private Controlador controlador;
	private JPanel contentPane;
	private JPanel HeaderPanel;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private JButton btnCrearUsuario;
	private JTextField txtUsuario;
	private JLabel lblUsuario;
	private JTextField txtPasswordCrearUsuario;
	private JLabel lblPasswordCreaUsuario;
	private JComboBox comboBoxRol;
	private JLabel lblRol;

	public CrearUsuario() {
		setTitle("Hospital simulado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(165, 42, 42));
		HeaderPanel.setBounds(0, 0, 984, 101);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Crear usuario");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(250, 0, 500, 100);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		HeaderPanel.add(lblTitulo);

		lblUemLogo = new JLabel("Aqui Iria el logo");
		lblUemLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUemLogo.setBounds(0, 0, 240, 100);
		HeaderPanel.add(lblUemLogo);

		lblPerfil = new JLabel("Aqui Iria el logo");
		lblPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerfil.setBounds(760, 0, 224, 100);
		HeaderPanel.add(lblPerfil);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(100, 685, 120, 40);
		contentPane.add(btnVolver);

		btnCrearUsuario = new JButton(" Crear usuario");
		btnCrearUsuario.setBounds(762, 685, 140, 40);
		contentPane.add(btnCrearUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(402, 239, 210, 30);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setBounds(481, 174, 62, 54);
		contentPane.add(lblUsuario);

		txtPasswordCrearUsuario = new JTextField();
		txtPasswordCrearUsuario.setColumns(10);
		txtPasswordCrearUsuario.setBounds(402, 540, 210, 30);
		contentPane.add(txtPasswordCrearUsuario);

		lblPasswordCreaUsuario = new JLabel("Contrase\u00F1a");
		lblPasswordCreaUsuario.setForeground(Color.BLACK);
		lblPasswordCreaUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPasswordCreaUsuario.setBounds(467, 475, 91, 54);
		contentPane.add(lblPasswordCreaUsuario);

		comboBoxRol = new JComboBox();
		comboBoxRol.setModel(new DefaultComboBoxModel(new String[] { "Lectura", "Administrador" }));
		comboBoxRol.setBounds(402, 385, 210, 30);
		contentPane.add(comboBoxRol);

		lblRol = new JLabel("Rol");
		lblRol.setForeground(Color.BLACK);
		lblRol.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRol.setBounds(493, 317, 35, 54);
		contentPane.add(lblRol);

	}
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
}
