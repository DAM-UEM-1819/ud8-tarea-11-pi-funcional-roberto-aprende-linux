package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Label;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InformacionExtra extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JTable tablaInfoProfesores;
	private JPanel HeaderPanel;
	private JScrollPane scrollPane;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private JButton btnGuardarCambios;
	private Label lblProfesores;
	private JTable tablaInfoAlumnos;
	private Label lblAlumnos;
	private JScrollPane scrollPane_2;
	private JLabel lblInfo;

	public InformacionExtra() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				controlador.solicitudDatosInfoExtra();
				esconderPrimeraColumna();
				limpiarInfo();
			}

		});
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

		scrollPane = new JScrollPane();
		scrollPane.setBounds(98, 168, 800, 105);
		contentPane.add(scrollPane);

		tablaInfoProfesores = new JTable();
		tablaInfoProfesores.getTableHeader().setReorderingAllowed(false);
		tablaInfoProfesores.setRowHeight(40);
		scrollPane.setViewportView(tablaInfoProfesores);
		//
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(98, 335, 800, 319);
		contentPane.add(scrollPane_2);

		tablaInfoAlumnos = new JTable();
		tablaInfoAlumnos.getTableHeader().setReorderingAllowed(false);
		scrollPane_2.setViewportView(tablaInfoAlumnos);
		tablaInfoAlumnos.setRowHeight(40);

		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(164, 44, 52));
		HeaderPanel.setBounds(0, 0, 1000, 100);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Información extra");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(0, 0, 1000, 100);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		HeaderPanel.add(lblTitulo);
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setVerticalAlignment(JLabel.CENTER);

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
		lblPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				controlador.infoExtraToPerfil();
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
		lblPerfil.setBounds(850, 0, 100, 100);
		HeaderPanel.add(lblPerfil);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.infoExtraToHome();
			}
		});
		btnVolver.setBounds(100, 685, 150, 40);
		contentPane.add(btnVolver);

		btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaInfoAlumnos.clearSelection();
				controlador.solicitudActualizarNotas();
			}
		});
		btnGuardarCambios.setBounds(748, 685, 150, 40);
		contentPane.add(btnGuardarCambios);

		lblProfesores = new Label("Profesores");
		lblProfesores.setAlignment(Label.CENTER);
		lblProfesores.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblProfesores.setBounds(98, 128, 800, 34);
		contentPane.add(lblProfesores);

		lblAlumnos = new Label("Alumnos");
		lblAlumnos.setAlignment(Label.CENTER);
		lblAlumnos.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblAlumnos.setBounds(98, 301, 800, 28);
		contentPane.add(lblAlumnos);

		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(0, 108, 1000, 20);
		contentPane.add(lblInfo);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas = modeloConsultas;
	}

	public DefaultTableModel getModelProfesores() {
		return (DefaultTableModel) tablaInfoProfesores.getModel();
	}

	public DefaultTableModel getModelAlumnos() {
		return (DefaultTableModel) tablaInfoAlumnos.getModel();
	}

	public void setModeloGestionDatos(ModeloGestionDatos modeloGestionDatos) {
		this.modeloGestionDatos = modeloGestionDatos;
	}

	private void esconderPrimeraColumna() {
		tablaInfoAlumnos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		tablaInfoAlumnos.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		tablaInfoAlumnos.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(0);
		tablaInfoAlumnos.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaInfoAlumnos.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaInfoAlumnos.getColumnModel().getColumn(0).setPreferredWidth(0);
	}

	public void actualizarInfo() {
		lblInfo.setText(modeloGestionDatos.getRespuesta());
	}

	private void limpiarInfo() {
		lblInfo.setText("");
	}
}
