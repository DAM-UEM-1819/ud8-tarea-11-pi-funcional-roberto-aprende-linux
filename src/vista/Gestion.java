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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.ModeloConsultas;

public class Gestion extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private JPanel contentPane;
	private JButton btnVolver;
	private JButton btnGestionUsuarios;
	private JPanel Header;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnGestionarActividades;
	private JButton btnGestionarAsignatura;
	private JButton btnGestionarSalas;
	private JButton btnGestionarActores;
	private JButton btnGestionarProfesores;
	private JButton btnGestionarAlumnos;
	private JButton btnGestionRegistros;
	private JButton btnGestionarAcad;
	private JButton btnVerGrupos;

	public Gestion() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/ue.png"));
		setTitle("Hospital simulado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.gestionToHome();
			}
		});
		btnVolver.setBounds(425, 671, 150, 40);
		contentPane.add(btnVolver);

		Header = new JPanel();
		Header.setBackground(new Color(164,44,52));
		Header.setBounds(0, 0, 1000, 100);
		contentPane.add(Header);
		Header.setLayout(null);

		lblTitulo = new JLabel("Gesti\u00F3n");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 0, 1000, 100);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		Header.add(lblTitulo);

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
		Header.add(lblUemLogo);

		ImageIcon perfilIcon = new ImageIcon("./img/usuario.png");
		lblPerfil = new JLabel(perfilIcon);
		lblPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				controlador.gestionToPerfil();
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
		lblPerfil.setBounds(850, 0, 100, 100);
		Header.add(lblPerfil);

		btnGestionUsuarios = new JButton("Gestionar Usuarios");
		btnGestionUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				controlador.gestionToGestionarUsuarios();
			}
		});
		btnGestionUsuarios.setBounds(150, 165, 250, 60);
		contentPane.add(btnGestionUsuarios);

		btnGestionRegistros = new JButton("Gestionar Registros");
		btnGestionRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.gestionToGestionarRegistros();
			}
		});
		btnGestionRegistros.setBounds(600, 165, 250, 60);
		contentPane.add(btnGestionRegistros);

		btnGestionarActividades = new JButton("Gestionar Actividades");
		btnGestionarActividades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.gestionToGestionarActividades();
			}
		});
		btnGestionarActividades.setBounds(150, 265, 250, 60);
		contentPane.add(btnGestionarActividades);

		btnGestionarAsignatura = new JButton("Gestionar Asignatura");
		btnGestionarAsignatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.gestionToGestionarAsignatura();
			}
		});
		btnGestionarAsignatura.setBounds(600, 265, 250, 60);
		contentPane.add(btnGestionarAsignatura);

		btnGestionarAlumnos = new JButton("Gestionar Alumnos");
		btnGestionarAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.gestionToGestionarAlumnos();
			}
		});
		btnGestionarAlumnos.setBounds(150, 365, 250, 60);
		contentPane.add(btnGestionarAlumnos);

		btnGestionarProfesores = new JButton("Gestionar Profesores");
		btnGestionarProfesores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.gestionToGestionarProfesores();
			}
		});
		btnGestionarProfesores.setBounds(600, 365, 250, 60);
		contentPane.add(btnGestionarProfesores);

		btnGestionarActores = new JButton("Gestionar Actores");
		btnGestionarActores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.gestionToGestionarActores();
			}
		});
		btnGestionarActores.setBounds(150, 465, 250, 60);
		contentPane.add(btnGestionarActores);

		btnGestionarSalas = new JButton("Gestionar Salas");
		btnGestionarSalas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.gestionToGestionarSalas();
			}
		});
		btnGestionarSalas.setBounds(600, 465, 250, 60);
		contentPane.add(btnGestionarSalas);

		btnGestionarAcad = new JButton("Gestionar A\u00F1o Acad\u00E9mico");
		btnGestionarAcad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.gestionToGestionarAcad();
			}
		});
		btnGestionarAcad.setBounds(600, 565, 250, 60);
		contentPane.add(btnGestionarAcad);

		btnVerGrupos = new JButton("Ver grupos");
		btnVerGrupos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.gestionToVerGrupos();
			}
		});
		btnVerGrupos.setBounds(150, 565, 250, 60);
		contentPane.add(btnVerGrupos);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas = modeloConsultas;
	}

}
