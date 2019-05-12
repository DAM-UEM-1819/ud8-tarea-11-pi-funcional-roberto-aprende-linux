package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.*;

import java.awt.TextField;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class Login extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private JPanel contentPane;
	private JPanel HeaderPanel;
	private JLabel lblUemLogo;
	private JLabel lblTitulo;
	private TextField txtUser;
	private JLabel lblUsuarioTemp;
	private JLabel lblPasswordTemp;
	private JPanel FooterPanel;
	private JLabel lblNewLabel;
	private JButton btnLogin;
	private JPasswordField passwdPasswordUsuario;
	private JLabel lblInfo;

	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/ue.png"));
		setTitle("Hospital simulado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUser = new TextField();
		txtUser.setBounds(451, 263, 200, 30);
		contentPane.add(txtUser);

		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(165, 42, 42));
		HeaderPanel.setBounds(0, 0, 984, 101);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);
		
		ImageIcon ueIcon = new ImageIcon("./img/ue.png");
		lblUemLogo = new JLabel(ueIcon);
		lblUemLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUemLogo.setBounds(0, 0, 240, 100);
		HeaderPanel.add(lblUemLogo);

		lblTitulo = new JLabel("Login");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(358, 11, 266, 61);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		HeaderPanel.add(lblTitulo);
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setVerticalAlignment(JLabel.CENTER);

		ImageIcon usuarioIcon = new ImageIcon("./img/usuario.png");
		lblUsuarioTemp = new JLabel(usuarioIcon);
		lblUsuarioTemp.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarioTemp.setBounds(345, 225, 100, 100);
		contentPane.add(lblUsuarioTemp);
		
		ImageIcon passwdIcon = new ImageIcon("./img/candado.png");
		lblPasswordTemp = new JLabel(passwdIcon);
		lblPasswordTemp.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswordTemp.setBounds(345, 422, 100, 100);
		contentPane.add(lblPasswordTemp);

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					controlador.loginSolicitud();
			}
		});
		btnLogin.setBounds(380, 572, 271, 58);
		contentPane.add(btnLogin);

		FooterPanel = new JPanel();
		FooterPanel.setBackground(new Color(165, 42, 42));
		FooterPanel.setBounds(0, 709, 984, 53);
		contentPane.add(FooterPanel);
		FooterPanel.setLayout(null);

		lblNewLabel = new JLabel("Universidad Europea de Madrid");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(400, 11, 201, 31);
		FooterPanel.add(lblNewLabel);

		passwdPasswordUsuario = new JPasswordField();
		passwdPasswordUsuario.setBounds(451, 463, 200, 30);
		contentPane.add(passwdPasswordUsuario);
		
		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(380, 132, 271, 30);
		contentPane.add(lblInfo);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public String getTxtUser() {
		return txtUser.getText();
	}

	public String getTextPasswd() {
		return String.valueOf(passwdPasswordUsuario.getPassword());
	}

	public void loginExitoso() {
		setVisible(false);
		controlador.loginToHome();
	}
	
	public void loginExitosoLectura() {
		setVisible(false);
		controlador.loginToHomeLectura();
	}

	public void salir() {
		System.exit(0);
	}
	
	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas= modeloConsultas;
	}
	
	public void actualizarInfo() {
		lblInfo.setText(modeloConsultas.getRespuesta());
	}
}
