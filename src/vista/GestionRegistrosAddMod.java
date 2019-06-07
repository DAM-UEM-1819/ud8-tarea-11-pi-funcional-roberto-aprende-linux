package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSpinner.DateEditor;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import controlador.Controlador;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;
import oracle.sql.DATE;

public class GestionRegistrosAddMod extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JTextField txtHorasProfesor;
	private JTextField txtCodSala;
	private JTextField txtHora;
	private JTextField txtActor1;
	private JPanel HeaderPanel;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private JButton btnGuardarCambios;
	private JTextField txtActor2;
	private JTextField txtProfesor;
	private JTextField txtProfesor2;
	private JComboBox comboBoxColumna;
	private JLabel lblHorasProfesor;
	private JTextField txtActividad;
	private JLabel lblCodActor;
	private JLabel lblCodActor2;
	private JLabel lblProfesor;
	private JLabel lblCodProfesor2;
	private JLabel lblHora;
	private JLabel lblFecha;
	private JLabel lblActividad;
	private JLabel lblCodSala;
	private JTextField txtCodGrupo;
	private JLabel lblGrupo;
	private String activo;
	private JLabel lblInfo;
	private JDateChooser txtFecha;
	private JLabel lblNewLabel;

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

		txtHora = new JTextField();
		txtHora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtHora.getText().length() == 2 && e.getKeyCode()!=KeyEvent.VK_BACK_SPACE) {
					String horaAct = txtHora.getText();
					txtHora.setText(horaAct + ":");
				}
			}
		});
		txtHora.setBounds(575, 201, 250, 30);
		contentPane.add(txtHora);
		txtHora.setColumns(10);

		txtHorasProfesor = new JTextField();
		txtHorasProfesor.setBounds(150, 297, 250, 30);
		contentPane.add(txtHorasProfesor);
		txtHorasProfesor.setColumns(10);

		txtActividad = new JTextField();
		txtActividad.setBounds(575, 297, 250, 30);
		contentPane.add(txtActividad);
		txtActividad.setColumns(10);

		txtCodGrupo = new JTextField();
		txtCodGrupo.setColumns(10);
		txtCodGrupo.setBounds(150, 389, 250, 30);
		contentPane.add(txtCodGrupo);

		txtCodSala = new JTextField();
		txtCodSala.setBounds(575, 389, 247, 30);
		contentPane.add(txtCodSala);
		txtCodSala.setColumns(10);

		txtProfesor = new JTextField();
		txtProfesor.setColumns(10);
		txtProfesor.setBounds(150, 476, 247, 30);
		contentPane.add(txtProfesor);

		txtProfesor2 = new JTextField();
		txtProfesor2.setColumns(10);
		txtProfesor2.setBounds(575, 476, 247, 30);
		contentPane.add(txtProfesor2);

		txtActor1 = new JTextField();
		txtActor1.setColumns(10);
		txtActor1.setBounds(150, 559, 247, 30);
		contentPane.add(txtActor1);

		txtActor2 = new JTextField();
		txtActor2.setBounds(575, 559, 247, 30);
		contentPane.add(txtActor2);
		txtActor2.setColumns(10);

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
				controlador.solicitudAddOModRegistros();
				limpiarTxt();
			}

		});
		btnGuardarCambios.setBounds(636, 685, 137, 40);
		contentPane.add(btnGuardarCambios);

		lblFecha = new JLabel("Fecha *");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setBounds(150, 177, 250, 20);
		contentPane.add(lblFecha);

		lblHora = new JLabel("Hora *");
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setBounds(575, 177, 250, 20);
		contentPane.add(lblHora);

		lblHorasProfesor = new JLabel("Horas de profesor *");
		lblHorasProfesor.setHorizontalAlignment(SwingConstants.CENTER);
		lblHorasProfesor.setBounds(150, 273, 250, 20);
		contentPane.add(lblHorasProfesor);

		lblActividad = new JLabel("Actividad *");
		lblActividad.setHorizontalAlignment(SwingConstants.CENTER);
		lblActividad.setBounds(575, 273, 250, 20);
		contentPane.add(lblActividad);

		lblGrupo = new JLabel("Código de grupo *");
		lblGrupo.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupo.setBounds(150, 364, 250, 20);
		contentPane.add(lblGrupo);

		lblCodSala = new JLabel("Código de sala *");
		lblCodSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodSala.setBounds(575, 364, 250, 20);
		contentPane.add(lblCodSala);

		lblProfesor = new JLabel("Código de profesor *");
		lblProfesor.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfesor.setBounds(150, 452, 247, 20);
		contentPane.add(lblProfesor);

		lblCodProfesor2 = new JLabel("Código de profesor 2");
		lblCodProfesor2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodProfesor2.setBounds(575, 452, 250, 20);
		contentPane.add(lblCodProfesor2);

		lblCodActor = new JLabel("Código de actor 1");
		lblCodActor.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodActor.setBounds(150, 535, 250, 20);
		contentPane.add(lblCodActor);

		lblCodActor2 = new JLabel("Código de actor 2");
		lblCodActor2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodActor2.setBounds(575, 535, 250, 20);
		contentPane.add(lblCodActor2);

		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(0, 110, 1000, 30);
		contentPane.add(lblInfo);
		
		txtFecha = new JDateChooser();
		txtFecha.setBounds(150, 201, 247, 30);
		contentPane.add(txtFecha);
		
		lblNewLabel = new JLabel("* Campos obligatorios");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel.setBounds(40, 746, 900, 14);
		contentPane.add(lblNewLabel);

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

	public String getTxtCodSala() {
		return txtCodSala.getText();
	}

	public String getTxtFecha() {
		return String.valueOf(new SimpleDateFormat("MM-dd-yyyy").format(txtFecha.getDate()));
	}

	public String getTxtHorasProfesor() {
		return txtHorasProfesor.getText();
	}

	public String getTxtHora() {
		return txtHora.getText();
	}

	public String getTxtActor1() {
		return txtActor1.getText();
	}

	public String getTxtActor2() {
		return txtActor2.getText();
	}

	public String getTxtProfesor() {
		return txtProfesor.getText();
	}

	public String getTxtProfesor2() {
		return txtProfesor2.getText();
	}

	public String getTxtActividad() {
		return txtActividad.getText();
	}

	public void actualizarInfoConsulta() {
		lblInfo.setText(modeloConsultas.getRespuesta());
	}

	//
	public void rellenar() {
		/*if (modeloGestionDatos.getRellenarDatos().length != 0) {
			txtFecha.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[0]));
			txtHora.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[1]));
			txtHorasProfesor.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[2]));
			txtActividad.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[3]));
			txtCodGrupo.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[4]));
			txtCodSala.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[5]));
			txtProfesor.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[6]));
			txtProfesor2.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[7]));
			txtActor1.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[8]));
			txtActor2.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[9]));
			txtRelacion_laboral.setText(String.valueOf(modeloGestionDatos.getRellenarDatos()[10]));
			activo = (String.valueOf(modeloGestionDatos.getRellenarDatos()[11]));
			if (Integer.parseInt(activo) == 1) {
				chckbxAI_profesores.setSelected(true);
			}
		}*/
	}
	public int estadoCheckBox() {
		/*int resultado = chckbxAI_profesores.isSelected() == true ? 1 : 0;*/
		return 0;
	}

	public void limpiarTxt() {
		txtFecha.cleanup();
		txtHorasProfesor.setText("");
		txtActividad.setText("");
		txtCodGrupo.setText("");
		txtCodSala.setText("");
		txtHora.setText("");
		txtActor1.setText("");
		txtActor2.setText("");
		txtProfesor.setText("");
		txtProfesor2.setText("");
	}

	public void actualizarInfoDatos() {
		lblInfo.setText(modeloGestionDatos.getRespuesta());

	}

	private void limpiarInfo() {
		lblInfo.setText("");

	}

	public void addDatosModificar() {
		String [] array = modeloConsultas.getDatosFilasRegistro();
		SimpleDateFormat formato = new SimpleDateFormat("DD/MM/YYYY");
		try {
			txtFecha.setDate(formato.parse(array[0]));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtHora.setText(array[1]);
		txtHorasProfesor.setText(array[2]);
		txtActividad.setText(array[3]);
		txtCodGrupo.setText(array[4]);
	}
	
}
