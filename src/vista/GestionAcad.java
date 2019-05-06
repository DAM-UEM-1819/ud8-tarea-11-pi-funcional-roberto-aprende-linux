package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.*;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class GestionAcad extends JFrame {
	
	private Controlador controlador;
	private Modelo modelo;
	private JPanel contentPane;
	private JTable tablaActores;
	private JPanel HeaderPanel;
	private JScrollPane scrollPane;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private JButton btnAI_actor;
	private JButton btnAddActor;
	private JButton btnModificarActor;
	private JTextField txtBuscador;
	private JComboBox comboBoxSem2;
	private JComboBox comboBoxSem1;
	private JLabel label;
	private JComboBox comboBoxColumna;



	/**
	 * Create the frame.
	 */
	public GestionAcad() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				controlador.solicitudDatosAcad();
			}
		});
		setTitle("Hospital simulado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(98, 168, 800, 411);
		contentPane.add(scrollPane);

		tablaActores = new JTable();
		tablaActores.setRowHeight(40);
		scrollPane.setViewportView(tablaActores);

		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(165, 42, 42));
		HeaderPanel.setBounds(0, 0, 984, 101);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Año académico");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(322, 11, 333, 61);
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
			public void actionPerformed(ActionEvent arg0) {
				controlador.gestionAcadToGestion();
			}
		});
		btnVolver.setBounds(100, 685, 120, 40);
		contentPane.add(btnVolver);

		btnModificarActor = new JButton("Modificar actor");
		btnModificarActor.setBounds(325, 685, 120, 40);
		contentPane.add(btnModificarActor);

		btnAI_actor = new JButton("Activo/Inactivo");
		btnAI_actor.setBounds(575, 685, 120, 40);
		contentPane.add(btnAI_actor);

		btnAddActor = new JButton(" A\u00F1adir actor");
		btnAddActor.setBounds(782, 685, 120, 40);
		contentPane.add(btnAddActor);
		
		comboBoxSem1 = new JComboBox();
		comboBoxSem1.setModel(new DefaultComboBoxModel(new String[] {"09/01/2018"}));
		comboBoxSem1.setBounds(380, 610, 255, 40);
		contentPane.add(comboBoxSem1);
		
		comboBoxSem2 = new JComboBox();
		comboBoxSem2.setModel(new DefaultComboBoxModel(new String[] {"02/01/2019"}));
		comboBoxSem2.setBounds(657, 610, 241, 40);
		contentPane.add(comboBoxSem2);
		
		label = new JLabel("Importar Actividades");
		label.setIcon(new ImageIcon(GestionAcad.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		label.setBounds(98, 132, 124, 20);
		contentPane.add(label);
		
		txtBuscador = new JTextField();
		txtBuscador.setText("Buscador");
		txtBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscador.setColumns(10);
		txtBuscador.setBounds(665, 132, 86, 20);
		contentPane.add(txtBuscador);
		
		comboBoxColumna = new JComboBox();
		comboBoxColumna.setBounds(761, 132, 104, 20);
		contentPane.add(comboBoxColumna);
	}
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	public void setModelo(Modelo modelo) {
		this.modelo= modelo;
	}

	public DefaultTableModel getModel() {
		return (DefaultTableModel) tablaActores.getModel();
	}
}
