package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPasswordField;

public class Perfil extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JPanel HeaderPanel;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private JButton btnConfirmarCambios;
	private JTextField txtUsuario;
	private JLabel lblUsuario;
	private JLabel lblPasswordActual;
	private JLabel lblRolTitulo;
	private JLabel lblRol;
	private JLabel lblPasswordNueva;
	private JLabel lblConfirmarContrasea;
	private JTextField txtEmail;
	private JButton btnMostrar;
	private JPasswordField pwdActual;
	private JPasswordField pwdNueva;
	private JPasswordField pwdConfirmar;
	private boolean estaMostrada;
	private JLabel lblInfo;

	/**
	 * Create the frame.
	 */
	public Perfil() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				controlador.solicitudDatosUsuarioActual();
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				limpiarInfo();
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

		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(164,44,52));
		HeaderPanel.setBounds(0, 0, 1000, 100);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Perfil");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 0, 1000, 100);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		HeaderPanel.add(lblTitulo);

		ImageIcon ueIcon = new ImageIcon("./img/ue.png");
		lblUemLogo = new JLabel(ueIcon);
		lblUemLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUemLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				controlador.loginToHome();
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
		lblUemLogo.setBounds(50, 0, 100, 100);
		HeaderPanel.add(lblUemLogo);
		
		ImageIcon perfilIcon = new ImageIcon("./img/usuario.png");
		lblPerfil = new JLabel(perfilIcon);
		lblPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerfil.setBounds(850, 0, 100, 100);
		HeaderPanel.add(lblPerfil);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.perfilToHome();
			}
		});
		btnVolver.setBounds(225, 685, 150, 40);
		contentPane.add(btnVolver);

		btnConfirmarCambios = new JButton(" Confirmar cambios");
		btnConfirmarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.solicitudActualizarUsuario();
			}
		});
		btnConfirmarCambios.setBounds(625, 685, 150, 40);
		contentPane.add(btnConfirmarCambios);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(200, 250, 200, 30);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setBounds(200, 200, 200, 40);
		contentPane.add(lblUsuario);

		lblPasswordActual = new JLabel("Contrase\u00F1a actual");
		lblPasswordActual.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswordActual.setForeground(Color.BLACK);
		lblPasswordActual.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPasswordActual.setBounds(600, 200, 200, 40);
		contentPane.add(lblPasswordActual);

		lblRolTitulo = new JLabel("Rol");
		lblRolTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblRolTitulo.setForeground(Color.BLACK);
		lblRolTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRolTitulo.setBounds(200, 500, 200, 40);
		contentPane.add(lblRolTitulo);

		lblRol = new JLabel("Administrador");
		lblRol.setHorizontalAlignment(SwingConstants.CENTER);
		lblRol.setForeground(Color.BLACK);
		lblRol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRol.setBounds(200, 550, 200, 30);
		contentPane.add(lblRol);

		lblPasswordNueva = new JLabel("Nueva contrase\u00F1a");
		lblPasswordNueva.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswordNueva.setForeground(Color.BLACK);
		lblPasswordNueva.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPasswordNueva.setBounds(600, 350, 200, 40);
		contentPane.add(lblPasswordNueva);

		lblConfirmarContrasea = new JLabel("Confirmar contrase\u00F1a");
		lblConfirmarContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmarContrasea.setForeground(Color.BLACK);
		lblConfirmarContrasea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblConfirmarContrasea.setBounds(600, 500, 200, 40);
		contentPane.add(lblConfirmarContrasea);

		JLabel lblEmail = new JLabel("Correo Electr\u00F3nico");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(200, 350, 200, 40);
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(200, 400, 200, 30);
		contentPane.add(txtEmail);

		btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPasswd();
			}
		});
		btnMostrar.setBounds(824, 404, 78, 23);
		contentPane.add(btnMostrar);

		pwdActual = new JPasswordField();
		pwdActual.setBounds(600, 250, 200, 30);
		contentPane.add(pwdActual);

		pwdNueva = new JPasswordField();
		pwdNueva.setBounds(600, 400, 200, 30);
		contentPane.add(pwdNueva);

		pwdConfirmar = new JPasswordField();
		pwdConfirmar.setBounds(600, 550, 200, 30);
		contentPane.add(pwdConfirmar);
		
		lblInfo = new JLabel("");
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(0, 102, 984, 40);
		contentPane.add(lblInfo);

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
	
	public String getUsuario() {
		return txtUsuario.getText();
	}
	
	public String getEmail() {
		return txtEmail.getText();
	}
	
	public String getPasswdActual() {
		return String.valueOf(pwdActual.getPassword());
	}
	
	public String getPasswdNueva() {
		return String.valueOf(pwdNueva.getPassword());
	}
	
	public String getPasswdComprobacion() {
		return String.valueOf(pwdConfirmar.getPassword());
	}

	public void mostrarDatosPerfil() {
		Object[] datos = modeloConsultas.getDatosUsuario();
		txtUsuario.setText(String.valueOf(datos[0]));
		lblRol.setText(String.valueOf(datos[1]));
		txtEmail.setText(String.valueOf(datos[2]).toLowerCase());

	}

	private void mostrarPasswd() {
		if (estaMostrada) {
			pwdActual.setEchoChar('●');
			pwdNueva.setEchoChar('●');
			pwdConfirmar.setEchoChar('●');
			estaMostrada = false;
		} else {
			pwdActual.setEchoChar((char) 0);
			pwdNueva.setEchoChar((char) 0);
			pwdConfirmar.setEchoChar((char) 0);
			estaMostrada = true;
		}
	}

	private void limpiarInfo() {
		txtUsuario.setText("");
		pwdActual.setText("");
		lblRol.setText("");
		txtEmail.setText("");
		pwdNueva.setText("");
		pwdConfirmar.setText("");
		lblInfo.setText("");
	}
	
	public void actualizarInfo() {
		lblInfo.setText(modeloGestionDatos.getRespuesta());
	}
}
