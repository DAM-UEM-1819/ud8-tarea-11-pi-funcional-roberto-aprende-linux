package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
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
import enums.Roles;

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
	private JLabel lblCorreo;
	private JComboBox comboBoxRol;
	private JLabel lblRol;

	private boolean estado;
	private JLabel lblInfo;
	private JTextField txtCorreo;
	private JTextField txtCorreoComprobacion;

	public CrearUsuario() {
		setResizable(false);
		setAutoRequestFocus(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/ue.png"));
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
		HeaderPanel.setBounds(0, 0, 1000, 100);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Crear usuario");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 0, 1000, 100);
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
				setVisible(false);
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
				setVisible(false);
				controlador.crearUsuarioToGestionUsuarios();
			}
		});
		btnVolver.setBounds(100, 685, 120, 40);
		contentPane.add(btnVolver);

		btnCrearUsuario = new JButton(" Crear usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comprobaciones()) {
					controlador.solicitudCrearUsuario();
				} else {

				}
			}
		});
		btnCrearUsuario.setBounds(762, 685, 140, 40);
		contentPane.add(btnCrearUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(200, 317, 200, 30);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setBounds(200, 262, 200, 54);
		contentPane.add(lblUsuario);

		lblCorreo = new JLabel("Email");
		lblCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorreo.setForeground(Color.BLACK);
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCorreo.setBounds(600, 262, 200, 54);
		contentPane.add(lblCorreo);

		comboBoxRol = new JComboBox();
		comboBoxRol.setModel(new DefaultComboBoxModel(Roles.values()));
		comboBoxRol.setBounds(200, 467, 200, 30);
		contentPane.add(comboBoxRol);

		lblRol = new JLabel("Rol");
		lblRol.setHorizontalAlignment(SwingConstants.CENTER);
		lblRol.setForeground(Color.BLACK);
		lblRol.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRol.setBounds(200, 412, 200, 54);
		contentPane.add(lblRol);

		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(0, 124, 984, 39);
		contentPane.add(lblInfo);

		JLabel lblConfirmarEmail = new JLabel("Confirmar email");
		lblConfirmarEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmarEmail.setForeground(Color.BLACK);
		lblConfirmarEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblConfirmarEmail.setBounds(600, 412, 200, 54);
		contentPane.add(lblConfirmarEmail);

		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(600, 317, 200, 30);
		contentPane.add(txtCorreo);

		txtCorreoComprobacion = new JTextField();
		txtCorreoComprobacion.setColumns(10);
		txtCorreoComprobacion.setBounds(600, 467, 200, 30);
		contentPane.add(txtCorreoComprobacion);

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

	public String getNombreUsuario() {
		return txtUsuario.getText();
	}

	public String getRol() {
		return String.valueOf(comboBoxRol.getSelectedItem());
	}

	public void actualizarInfo() {
		lblInfo.setText(modeloConsultas.getRespuesta());
	}

	public void actualizarInfoVista(String error) {
		lblInfo.setText(error);
	}

	public String getEmail() {
		return txtCorreo.getText();
	}

	public boolean comprobaciones() {
		boolean todoCorrecto = false;
		String user = txtUsuario.getText();
		String correo = txtCorreo.getText();
		String correoComprobacion = txtCorreoComprobacion.getText();
		if (!user.equals("") && !correo.equals("") && !correoComprobacion.equals("")) {
			if (correo.contains("@") && correo.contains(".") && !correo.contains(" ")) {
				if (correo.equals(correoComprobacion)) {
					todoCorrecto = true;
				} else {
					actualizarInfoVista("Error, los correos no coinciden");
				}

			} else {
				actualizarInfoVista("Error, el email tiene que tener un @, un . y no puede contener espacios");
			}

		} else {
			actualizarInfoVista("Error, rellene todos los campos");
		}

		return todoCorrecto;
	}
}
