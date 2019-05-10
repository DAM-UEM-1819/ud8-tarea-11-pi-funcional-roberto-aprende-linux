package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.*;

public class VerGrupos extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private JPanel contentPane;
	private JPanel HeaderPanel;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private JComboBox comboBoxColumna;
	private JTable tablaGrupos;
	private JScrollPane scrollPane;

	
	public VerGrupos() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				borrarGrupos();
				comboBoxColumna.addItem("Selecciona un grupo");
				controlador.solicitudListadoGrupos();
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

		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(165, 42, 42));
		HeaderPanel.setBounds(0, 0, 984, 101);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Grupos");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(10, 0, 974, 100);
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
				controlador.verGruposToGestion();
			}
		});
		btnVolver.setBounds(419, 685, 120, 40);
		contentPane.add(btnVolver);

		comboBoxColumna = new JComboBox();
		comboBoxColumna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.solicitudListadoAlumnosPorGrupo();
			}
		});
		comboBoxColumna.setMaximumRowCount(100);
		comboBoxColumna.setBounds(100, 127, 765, 40);
		contentPane.add(comboBoxColumna);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 176, 765, 479);
		contentPane.add(scrollPane);

		tablaGrupos = new JTable();
		tablaGrupos.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre y apellido" }));
		scrollPane.setViewportView(tablaGrupos);

	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas = modeloConsultas;
	}
	
	public void borrarGrupos() {
		comboBoxColumna.removeAllItems();
	}
	
	public String getGrupoComboBox(){
		return String.valueOf(comboBoxColumna.getSelectedItem());
	}
	
	@SuppressWarnings("unchecked")
	public void addGrupo() {
		comboBoxColumna.addItem(modeloConsultas.getGrupo());
	}
	
	public DefaultTableModel getModel() {
		return (DefaultTableModel) tablaGrupos.getModel();
	}
}
