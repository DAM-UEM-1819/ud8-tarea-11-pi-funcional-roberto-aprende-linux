package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
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

public class Perfil extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private JPanel contentPane;
	private JPanel HeaderPanel;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private JButton btnConfirmarCambios;
	private JTextField txtUsuario;
	private JLabel lblUsuario;
	private JTextField txtPasswordActual;
	private JLabel lblPasswordActual;
	private JLabel lblRolTitulo;
	private JLabel lblRol;
	private JLabel lblPasswordNueva;
	private JTextField txtPasswordNueva;
	private JLabel lblConfirmarContrasea;
	private JTextField txtPasswordConfirmar;


	/**
	 * Create the frame.
	 */
	public Perfil() {
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
		
		lblTitulo = new JLabel("Perfil");
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
		lblPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerfil.setBounds(818, 0, 100, 100);
		HeaderPanel.add(lblPerfil);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.perfilToHome();
			}
		});
		btnVolver.setBounds(100, 685, 120, 40);
		contentPane.add(btnVolver);


		btnConfirmarCambios = new JButton(" Confirmar cambios");
		btnConfirmarCambios.setBounds(762, 685, 140, 40);
		contentPane.add(btnConfirmarCambios);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(130, 246, 210, 30);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setBounds(206, 181, 62, 54);
		contentPane.add(lblUsuario);
		
		txtPasswordActual = new JTextField();
		txtPasswordActual.setColumns(10);
		txtPasswordActual.setBounds(572, 246, 210, 30);
		contentPane.add(txtPasswordActual);
		
		lblPasswordActual = new JLabel("Contrase\u00F1a actual");
		lblPasswordActual.setForeground(Color.BLACK);
		lblPasswordActual.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPasswordActual.setBounds(635, 181, 147, 54);
		contentPane.add(lblPasswordActual);
		
		lblRolTitulo = new JLabel("Rol");
		lblRolTitulo.setForeground(Color.BLACK);
		lblRolTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRolTitulo.setBounds(212, 402, 31, 54);
		contentPane.add(lblRolTitulo);
		
		lblRol = new JLabel("Administrador");
		lblRol.setForeground(Color.BLACK);
		lblRol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRol.setBounds(187, 467, 97, 30);
		contentPane.add(lblRol);
		
		lblPasswordNueva = new JLabel("Nueva contrase\u00F1a");
		lblPasswordNueva.setForeground(Color.BLACK);
		lblPasswordNueva.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPasswordNueva.setBounds(635, 317, 147, 54);
		contentPane.add(lblPasswordNueva);
		
		txtPasswordNueva = new JTextField();
		txtPasswordNueva.setColumns(10);
		txtPasswordNueva.setBounds(572, 382, 210, 30);
		contentPane.add(txtPasswordNueva);
		
		lblConfirmarContrasea = new JLabel("Confirmar contrase\u00F1a");
		lblConfirmarContrasea.setForeground(Color.BLACK);
		lblConfirmarContrasea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblConfirmarContrasea.setBounds(604, 443, 178, 54);
		contentPane.add(lblConfirmarContrasea);
		
		txtPasswordConfirmar = new JTextField();
		txtPasswordConfirmar.setColumns(10);
		txtPasswordConfirmar.setBounds(572, 508, 210, 30);
		contentPane.add(txtPasswordConfirmar);
		
	}
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas= modeloConsultas;
	}
	
}
