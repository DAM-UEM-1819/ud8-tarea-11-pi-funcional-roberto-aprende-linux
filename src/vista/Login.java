package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

import java.awt.TextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private Controlador controlador;
	private JPanel contentPane;
	private JPanel HeaderPanel;
	private JLabel lblUemLogo;
	private JLabel lblTitulo;
	private TextField txtUser;
	private TextField textField;
	private JLabel lblUsuarioTemp;
	private JLabel lblPasswordTemp;
	private JPanel FooterPanel;
	private JLabel lblNewLabel;
	private JButton btnLogin;
	


	
	
	
	public Login() {
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
		
		textField = new TextField();
		textField.setBounds(451, 463, 200, 30);
		contentPane.add(textField);
		
		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(165, 42, 42));
		HeaderPanel.setBounds(0, 0, 984, 101);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);
		
		lblUemLogo = new JLabel("Aqui Iria el logo");
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
		
		lblUsuarioTemp = new JLabel("Usuario");
		lblUsuarioTemp.setBounds(380, 263, 46, 14);
		contentPane.add(lblUsuarioTemp);
		
		lblPasswordTemp = new JLabel("Contrase\u00F1a");
		lblPasswordTemp.setBounds(380, 462, 65, 14);
		contentPane.add(lblPasswordTemp);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Más adelante habra que cambiarlo de sitio porque aqui se llamará al 
				 * controlador que llamara al modelo pasandole los datos para verificar credenciales
				 */
				controlador.loginToHome();
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
	}
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
}
