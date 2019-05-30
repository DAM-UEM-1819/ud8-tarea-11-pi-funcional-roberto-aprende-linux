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

public class GestionRegistrosAddMod extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JTextField txtFecha;
	private JTextField txtHorasProfesor;
	private JTextField txtCodActor;
	private JTextField txtHora;
	private JTextField txtRelacion_laboral;
	private JTextField txtTelefono1;
	private JPanel HeaderPanel;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private JButton btnGuardarCambios;
	private JTextField txtTelefono2;
	private JTextField txtProfesor;
	private JTextField txtCodSala;
	private JCheckBox chckbxAI_profesores;
	private JComboBox comboBoxColumna;
	private JLabel lblHorasProfesor;
	private JTextField txtActividad;
	private JLabel lblRelacionLaboral;
	private JLabel lblTlfn1;
	private JLabel lblTlfn2;
	private JLabel lblProfesor;
	private JLabel lblCodSala;
	private JLabel lblHora;
	private JLabel lblFecha;
	private JLabel lblActividad;
	private JLabel lblTitulacion;
	private JTextField txtCodGrupo;
	private JLabel lblGrupo;
	private String activo;
	private JLabel lblInfo;

	public GestionRegistrosAddMod() {
		setResizable(false);
		// TODO Auto-generated constructor stub
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				rellenar();
				limpiarInfo();

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

		txtFecha = new JTextField();

		txtFecha.setBounds(150, 201, 247, 30);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);

		txtHora = new JTextField();
		txtHora.setBounds(575, 201, 250, 30);
		contentPane.add(txtHora);
		txtHora.setColumns(10);

		txtHorasProfesor = new JTextField();
		txtHorasProfesor.setBounds(150, 281, 250, 30);
		contentPane.add(txtHorasProfesor);
		txtHorasProfesor.setColumns(10);

		txtActividad = new JTextField();
		txtActividad.setBounds(575, 281, 250, 30);
		contentPane.add(txtActividad);
		txtActividad.setColumns(10);

		txtCodGrupo = new JTextField();
		txtCodGrupo.setColumns(10);
		txtCodGrupo.setBounds(150, 361, 250, 30);
		contentPane.add(txtCodGrupo);

		txtCodActor = new JTextField();
		txtCodActor.setBounds(575, 361, 247, 30);
		contentPane.add(txtCodActor);
		txtCodActor.setColumns(10);

		txtProfesor = new JTextField();
		txtProfesor.setColumns(10);
		txtProfesor.setBounds(150, 440, 247, 30);
		contentPane.add(txtProfesor);

		txtCodSala = new JTextField();
		txtCodSala.setColumns(10);
		txtCodSala.setBounds(575, 440, 247, 30);
		contentPane.add(txtCodSala);

		txtTelefono1 = new JTextField();
		txtTelefono1.setColumns(10);
		txtTelefono1.setBounds(150, 519, 247, 30);
		contentPane.add(txtTelefono1);

		txtTelefono2 = new JTextField();
		txtTelefono2.setBounds(575, 519, 247, 30);
		contentPane.add(txtTelefono2);
		txtTelefono2.setColumns(10);

		txtRelacion_laboral = new JTextField();
		txtRelacion_laboral.setBounds(150, 597, 247, 30);
		contentPane.add(txtRelacion_laboral);
		txtRelacion_laboral.setColumns(10);

		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(164, 44, 52));
		HeaderPanel.setBounds(0, 0, 1000, 100);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Registros");
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
		lblPerfil.setBounds(850, 0, 100, 100);
		HeaderPanel.add(lblPerfil);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (modeloGestionDatos.getVaciarDatos()) {
					limpiarTxt();
				}
				setVisible(false);
				controlador.gestionRegistrosAddModToGestionRegistros();

			}
		});
		btnVolver.setBounds(214, 685, 120, 40);
		contentPane.add(btnVolver);

		btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarInfo();
				controlador.solicitudAddOMod();
				limpiarTxt();
			}

		});
		btnGuardarCambios.setBounds(636, 685, 137, 40);
		contentPane.add(btnGuardarCambios);

		lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setBounds(150, 177, 250, 20);
		contentPane.add(lblFecha);

		lblHora = new JLabel("Hora");
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setBounds(575, 177, 250, 20);
		contentPane.add(lblHora);

		lblHorasProfesor = new JLabel("Horas de profesor");
		lblHorasProfesor.setHorizontalAlignment(SwingConstants.CENTER);
		lblHorasProfesor.setBounds(150, 257, 250, 20);
		contentPane.add(lblHorasProfesor);

		lblActividad = new JLabel("Actividad");
		lblActividad.setHorizontalAlignment(SwingConstants.CENTER);
		lblActividad.setBounds(575, 257, 250, 20);
		contentPane.add(lblActividad);

		lblGrupo = new JLabel("Código de grupo");
		lblGrupo.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupo.setBounds(150, 336, 250, 20);
		contentPane.add(lblGrupo);

		lblTitulacion = new JLabel("Código de actor");
		lblTitulacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulacion.setBounds(575, 336, 250, 20);
		contentPane.add(lblTitulacion);

		lblProfesor = new JLabel("Código de profesor");
		lblProfesor.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfesor.setBounds(150, 416, 250, 20);
		contentPane.add(lblProfesor);

		lblCodSala = new JLabel("Código de sala");
		lblCodSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodSala.setBounds(575, 416, 250, 20);
		contentPane.add(lblCodSala);

		lblTlfn1 = new JLabel("Telefono 1");
		lblTlfn1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTlfn1.setBounds(150, 495, 250, 20);
		contentPane.add(lblTlfn1);

		lblTlfn2 = new JLabel("Telefono 2");
		lblTlfn2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTlfn2.setBounds(575, 495, 250, 20);
		contentPane.add(lblTlfn2);

		lblRelacionLaboral = new JLabel("Relacion laboral");
		lblRelacionLaboral.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelacionLaboral.setBounds(150, 572, 250, 20);
		contentPane.add(lblRelacionLaboral);

		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(0, 110, 1000, 30);
		contentPane.add(lblInfo);

		chckbxAI_profesores = new JCheckBox("Activo / Inactivo");
		chckbxAI_profesores.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxAI_profesores.setBounds(575, 601, 250, 23);
		contentPane.add(chckbxAI_profesores);

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
		return txtFecha.getText();
	}

	public String getNombre() {
		return txtHorasProfesor.getText();
	}

	public String getApellido1() {
		return txtActividad.getText();
	}

	public String getApellido2() {
		return txtCodGrupo.getText();
	}

	public String getTitulacion() {
		return txtCodActor.getText();
	}

	public String getDni() {
		return txtHora.getText();
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
		return txtProfesor.getText();
	}

	public String getEmail2() {
		return txtCodSala.getText();
	}

	public String getAI_profesores() {
		activo = String.valueOf(estadoCheckBox());
		return activo;
	}

	public void actualizarInfoConsulta() {
		lblInfo.setText(modeloConsultas.getRespuesta());
	}

	//
	public void rellenar() {
		if (modeloGestionDatos.getRellenarDatos().length != 0) {
			txtFecha.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[0]));
			txtHora.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[1]));
			txtHorasProfesor.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[2]));
			txtActividad.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[3]));
			txtCodGrupo.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[4]));
			txtCodActor.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[5]));
			txtProfesor.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[6]));
			txtCodSala.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[7]));
			txtTelefono1.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[8]));
			txtTelefono2.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[9]));
			txtRelacion_laboral.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[10]));
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
		txtFecha.setText("");
		txtHorasProfesor.setText("");
		txtActividad.setText("");
		txtCodGrupo.setText("");
		txtCodActor.setText("");
		txtHora.setText("");
		txtRelacion_laboral.setText("");
		txtTelefono1.setText("");
		txtTelefono2.setText("");
		txtProfesor.setText("");
		txtCodSala.setText("");
		chckbxAI_profesores.setSelected(false);
	}

	public void actualizarInfoDatos() {
		lblInfo.setText(modeloGestionDatos.getRespuesta());

	}
	
	private void limpiarInfo() {
		lblInfo.setText("");
		
	}
}
