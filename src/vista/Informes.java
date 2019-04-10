package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;

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
import modelo.*;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Informes extends JFrame {

	private Controlador controlador;
	private Modelo modelo;
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
	private JTable TablaInfoAlumnos;
	private JScrollPane scrollPane_2;
	private JComboBox comboBoxInformes;

	public Informes() {
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
				"Titulación", "Horas totales"
			}
		));
		tablaInfoProfesores.setRowHeight(40);
		scrollPane.setViewportView(tablaInfoProfesores);
		//


		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(165, 42, 42));
		HeaderPanel.setBounds(0, 0, 984, 101);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Informes");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(278, 11, 404, 61);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		HeaderPanel.add(lblTitulo);
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setVerticalAlignment(JLabel.CENTER);

		lblUemLogo = new JLabel("Aqui Iria el logo");
		lblUemLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUemLogo.setBounds(0, 0, 240, 100);
		HeaderPanel.add(lblUemLogo);

		lblPerfil = new JLabel("Aqui Iria el logo");
		lblPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerfil.setBounds(760, 0, 224, 100);
		HeaderPanel.add(lblPerfil);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.informesToHome();
			}
		});
		btnVolver.setBounds(100, 685, 120, 40);
		contentPane.add(btnVolver);

		btnGuardarCambios = new JButton("Mostrar");
		btnGuardarCambios.setBounds(782, 685, 120, 40);
		contentPane.add(btnGuardarCambios);

		lblProfesores = new Label("Nombre del informe");
		lblProfesores.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblProfesores.setBounds(397, 128, 163, 22);
		contentPane.add(lblProfesores);
		
		comboBoxInformes = new JComboBox();
		comboBoxInformes.setModel(new DefaultComboBoxModel(new String[] {"Informe"}));
		comboBoxInformes.setBounds(352, 689, 301, 33);
		contentPane.add(comboBoxInformes);
	}
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	public void seModelo(Modelo modelo) {
		this.modelo= modelo;
	}
}
