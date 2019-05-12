package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;

public class CrearUsuario extends JFrame {
	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JPanel HeaderPanel;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private JButton btnCrearUsuario;
	private JTextField txtUsuario;
	private JLabel lblUsuario;
	private JLabel lblPasswordCreaUsuario;
	private JComboBox comboBoxRol;
	private JLabel lblRol;
	private JPasswordField passwordField;
	private JButton btnMostrarPwd;

	private boolean estado;
	private JLabel lblInfo;

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
				controlador.crearUsuarioToPerfil();
			}
			@SuppressWarnings("deprecation")
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.HAND_CURSOR);
			}
			@SuppressWarnings("deprecation")
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
				controlador.crearUsuarioToGestionUsuarios();
			}
		});
		btnVolver.setBounds(100, 685, 120, 40);
		contentPane.add(btnVolver);

		btnCrearUsuario = new JButton(" Crear usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.solicitudCrearUsuario();
			}
		});
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

		passwordField = new JPasswordField();
		passwordField.setBounds(402, 540, 210, 30);
		contentPane.add(passwordField);

		btnMostrarPwd = new JButton("Mostrar");
		btnMostrarPwd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!estado) {
					passwordField.setEchoChar((char) 0);
					estado = true;
				} else {
					passwordField.setEchoChar('●');
					estado = false;
				}
			}
		});
		btnMostrarPwd.setBounds(622, 544, 69, 23);
		contentPane.add(btnMostrarPwd);
		
		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(407, 124, 205, 39);
		contentPane.add(lblInfo);

	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}


	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas= modeloConsultas;
	}
	
	public void setModeloGestionDatos(ModeloGestionDatos modeloGestionDatos) {
		this.modeloGestionDatos= modeloGestionDatos;
	}

	public String getNombreUsuario() {
		return txtUsuario.getText();
	}
	
	public String getPasswd() {
		return String.valueOf(passwordField.getPassword());
	}
	
	public String getRol() {
		return String.valueOf(comboBoxRol.getSelectedItem());
	}
	
	public void actualizarInfo() {
		lblInfo.setText(modeloConsultas.getRespuesta());
	}
	

}
