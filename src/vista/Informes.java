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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import enums.ListadoInformes;
import modelo.ModeloConsultas;

public class Informes extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private JPanel contentPane;
	private JTable tablaInfoProfesores;
	private JPanel HeaderPanel;
	private JScrollPane scrollPane;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private Label lblProfesores;
	private JTable TablaInfoAlumnos;
	private JScrollPane scrollPane_2;
	private JComboBox comboBoxInformes;

	public Informes() {
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
		scrollPane.setBounds(98, 168, 800, 473);
		contentPane.add(scrollPane);

		tablaInfoProfesores = new JTable();
		tablaInfoProfesores.setModel(new DefaultTableModel(
			new Object[][] {
				{"Medicina", "431"},
				{null, null},
			},
			new String[] {
				"Titulaci�n", "Horas totales"
			}
		));
		tablaInfoProfesores.setRowHeight(40);
		scrollPane.setViewportView(tablaInfoProfesores);
		//


		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(165, 42, 42));
		HeaderPanel.setBounds(0, 0, 1000, 10);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Informes");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(278, 11, 404, 61);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		HeaderPanel.add(lblTitulo);
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setVerticalAlignment(JLabel.CENTER);

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
				controlador.homeToPerfil();
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
		lblPerfil.setBounds(818, 0, 100, 100);
		HeaderPanel.add(lblPerfil);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.informesToHome();
			}
		});
		btnVolver.setBounds(435, 685, 120, 40);
		contentPane.add(btnVolver);

		lblProfesores = new Label("Nombre del informe: ");
		lblProfesores.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblProfesores.setBounds(98, 140, 163, 22);
		contentPane.add(lblProfesores);
		
		comboBoxInformes = new JComboBox();
		comboBoxInformes.setModel(new DefaultComboBoxModel(ListadoInformes.values()));
		comboBoxInformes.setBounds(594, 129, 301, 33);
		contentPane.add(comboBoxInformes);
	}
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas= modeloConsultas;
	}
}
