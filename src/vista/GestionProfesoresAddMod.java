package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;

public class GestionProfesoresAddMod extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JTextField txtNumero;
	private JTextField txtNombre;
	private JTextField txtTitulacion;
	private JTextField txtDni;
	private JTextField txtRelacion_laboral;
	private JTextField txtTelefono1;
	private JPanel HeaderPanel;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private JButton btnGuardarCambios;
	private JTextField txtTelefono2;
	private JTextField txtEmail1;
	private JTextField txtEmail2;
	private JCheckBox chckbxAI_profesores;
	private JComboBox comboBoxColumna;
	private JLabel lblNombre;
	private JTextField txtApellido1;
	private JLabel lblRelacionLaboral;
	private JLabel lblTlfn1;
	private JLabel lblTlfn2;
	private JLabel lblEmail1;
	private JLabel lblEmail2;
	private JLabel lblDni;
	private JLabel lblNumero;
	private JLabel lblApellido1;
	private JLabel lblTitulacion;
	private JTextField txtApellido2;
	private JLabel lblApellido2;
	private String activo;

	public GestionProfesoresAddMod() {
		setResizable(false);
		// TODO Auto-generated constructor stub
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				rellenar();

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

		txtNumero = new JTextField();

		txtNumero.setBounds(233, 201, 247, 30);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(233, 281, 247, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido1 = new JTextField();
		txtApellido1.setBounds(233, 361, 247, 30);
		contentPane.add(txtApellido1);
		txtApellido1.setColumns(10);

		txtApellido2 = new JTextField();
		txtApellido2.setColumns(10);
		txtApellido2.setBounds(233, 440, 247, 30);
		contentPane.add(txtApellido2);

		txtTitulacion = new JTextField();
		txtTitulacion.setBounds(233, 519, 247, 30);
		contentPane.add(txtTitulacion);
		txtTitulacion.setColumns(10);

		txtDni = new JTextField();
		txtDni.setBounds(233, 597, 247, 30);
		contentPane.add(txtDni);
		txtDni.setColumns(10);

		txtRelacion_laboral = new JTextField();
		txtRelacion_laboral.setBounds(546, 201, 247, 30);
		contentPane.add(txtRelacion_laboral);
		txtRelacion_laboral.setColumns(10);

		txtTelefono1 = new JTextField();
		txtTelefono1.setColumns(10);
		txtTelefono1.setBounds(546, 281, 247, 30);
		contentPane.add(txtTelefono1);

		txtTelefono2 = new JTextField();
		txtTelefono2.setBounds(546, 361, 247, 30);
		contentPane.add(txtTelefono2);
		txtTelefono2.setColumns(10);

		txtEmail1 = new JTextField();
		txtEmail1.setColumns(10);
		txtEmail1.setBounds(546, 440, 247, 30);
		contentPane.add(txtEmail1);

		txtEmail2 = new JTextField();
		txtEmail2.setColumns(10);
		txtEmail2.setBounds(546, 519, 247, 30);
		contentPane.add(txtEmail2);

		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(165, 42, 42));
		HeaderPanel.setBounds(0, 0, 1000, 100);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Profesores");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(0, 0, 1000, 100);
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
				controlador.gestionProfesoresToPerfil();
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
				if (modeloGestionDatos.getVaciarDatos()) {
					limpiarTxt();
				}
				setVisible(false);
				controlador.gestionProfesoresAddModToGestionProfesores();

			}
		});
		btnVolver.setBounds(100, 685, 120, 40);
		contentPane.add(btnVolver);

		btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.setBounds(765, 685, 137, 40);
		contentPane.add(btnGuardarCambios);

		chckbxAI_profesores = new JCheckBox("Activo / Inactivo");
		chckbxAI_profesores.setBounds(558, 601, 128, 23);
		contentPane.add(chckbxAI_profesores);

		lblNumero = new JLabel("Numero");
		lblNumero.setBounds(243, 177, 46, 14);
		contentPane.add(lblNumero);

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(243, 257, 237, 14);
		contentPane.add(lblNombre);

		lblApellido1 = new JLabel("Apellido 1");
		lblApellido1.setBounds(243, 337, 237, 14);
		contentPane.add(lblApellido1);

		lblApellido2 = new JLabel("Apellido 2");
		lblApellido2.setBounds(243, 416, 237, 14);
		contentPane.add(lblApellido2);

		lblTitulacion = new JLabel("Titulacion");
		lblTitulacion.setBounds(243, 495, 237, 14);
		contentPane.add(lblTitulacion);

		lblDni = new JLabel("D.N.I");
		lblDni.setBounds(243, 572, 71, 14);
		contentPane.add(lblDni);

		lblRelacionLaboral = new JLabel("Relacion laboral");
		lblRelacionLaboral.setBounds(558, 177, 235, 14);
		contentPane.add(lblRelacionLaboral);

		lblTlfn1 = new JLabel("Telefono 1");
		lblTlfn1.setBounds(558, 257, 235, 14);
		contentPane.add(lblTlfn1);

		lblTlfn2 = new JLabel("Telefono 2");
		lblTlfn2.setBounds(558, 336, 229, 14);
		contentPane.add(lblTlfn2);

		lblEmail1 = new JLabel("Email 1");
		lblEmail1.setBounds(558, 416, 71, 14);
		contentPane.add(lblEmail1);

		lblEmail2 = new JLabel("Email 2");
		lblEmail2.setBounds(558, 494, 71, 14);
		contentPane.add(lblEmail2);

	}
	// Setters

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas = modeloConsultas;
	}

	public void setModeloGestionDatos(ModeloGestionDatos modeloGestionDatos) {
		this.modeloGestionDatos = modeloGestionDatos;
	}
	// Getters

	public String getNumero() {
		return txtNumero.getText();
	}

	public String getNombre() {
		return txtNombre.getText();
	}

	public String getTitulacion() {
		return txtTitulacion.getText();
	}

	public String getDni() {
		return txtDni.getText();
	}

	public String getRelacion_laboral() {
		return txtRelacion_laboral.getText();
	}

	public String getTelefono1() {
		return txtTelefono1.getText();
	}

	public String getTelefono2() {
		return txtTelefono2.getText();
	}

	public String getEmail1() {
		return txtEmail1.getText();
	}

	public String getEmail2() {
		return txtEmail2.getText();
	}

	//
	public void rellenar() {
		if (modeloGestionDatos.getRellenarDatos().length != 0) {
			txtNumero.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[0]));
			txtNombre.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[1]));
			txtApellido1.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[2]));
			txtApellido2.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[3]));
			txtTitulacion.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[4]));
			txtDni.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[5]));
			txtRelacion_laboral.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[6]));
			txtTelefono1.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[7]));
			txtTelefono2.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[8]));
			txtEmail1.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[9]));
			txtEmail2.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[10]));
			activo = (String.valueOf(modeloGestionDatos.getRellenarDatos()[11]));
			if (Integer.parseInt(activo) == 1) {
				chckbxAI_profesores.setSelected(true);
			}
		}
	}

	public int estadoCheckBox() {
		int resultado = chckbxAI_profesores.isSelected() == true ? 1 : 0;
		return resultado;
	}

	public void limpiarTxt() {
		txtNumero.setText("");
		txtNombre.setText("");
		txtApellido1.setText("");
		txtApellido2.setText("");
		txtTitulacion.setText("");
		txtDni.setText("");
		txtRelacion_laboral.setText("");
		txtTelefono1.setText("");
		txtTelefono2.setText("");
		txtEmail1.setText("");
		txtEmail2.setText("");
		chckbxAI_profesores.setSelected(false);
	}
}
