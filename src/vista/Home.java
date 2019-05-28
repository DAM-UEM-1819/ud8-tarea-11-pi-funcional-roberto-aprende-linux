package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controlador.Controlador;
import modelo.*;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.beans.VetoableChangeListener;

public class Home extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private JPanel contentPane;
	private JTable tablaRegistros;
	private JButton btnSalir;
	private JButton btnInfoExtra;
	private JPanel Header;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JScrollPane scrollPaneRegistros;
	private JLabel lblNumAlumnos;
	private JPanel infoExtra;
	private JLabel lblSimulador;
	private JLabel lblActor;
	private JCheckBox chckbxActor;
	private JLabel lblNombreSimulador;
	private JLabel lblNumeroAlumnos;
	private JButton btnGestionar;
	private JButton btnInformes;
	private JLabel lblNewLabel;
	private JLabel lblDocumentacion;
	private JLabel lblDocumentacionNumero;
	private JTextField txtBuscador;
	private JLabel lblLupa;
	private JDateChooser dateChooser;
	private String dia;
	private String mes;
	private String year;
	private boolean estaSeleccionado;

	public Home() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				controlador.solicitudDatosHome();
				btnInfoExtra.setEnabled(false);
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

		scrollPaneRegistros = new JScrollPane();
		scrollPaneRegistros.setBounds(35, 144, 700, 500);
		contentPane.add(scrollPaneRegistros);

		tablaRegistros = new JTable();
		tablaRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaRegistros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tablaRegistros.getSelectedRow() > -1) {
					estaSeleccionado = true;
					btnInfoExtra.setEnabled(true);
				} else {
					estaSeleccionado = false;
					btnInfoExtra.setEnabled(false);
				}
			}
		});
		tablaRegistros.setRowHeight(30);
		tablaRegistros.getTableHeader().setReorderingAllowed(false);
		scrollPaneRegistros.setViewportView(tablaRegistros);

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.confirmacionSalir();
			}
		});
		btnSalir.setBounds(35, 685, 170, 40);
		contentPane.add(btnSalir);

		btnInfoExtra = new JButton("Informaci\u00F3n Extra");
		btnInfoExtra.setEnabled(false);
		btnInfoExtra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.homeToInfoExtra();
				controlador.solicitudGuardarDatos();
			}
		});
		btnInfoExtra.setBounds(284, 685, 170, 40);
		contentPane.add(btnInfoExtra);

		btnGestionar = new JButton("Gestionar");
		btnGestionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.homeToGestion();
			}
		});
		btnGestionar.setBounds(782, 685, 170, 40);
		contentPane.add(btnGestionar);

		Header = new JPanel();
		Header.setBackground(new Color(164, 44, 52));
		Header.setBounds(0, 0, 1000, 100);
		contentPane.add(Header);
		Header.setLayout(null);

		fechaActual();
		lblTitulo = new JLabel(dia + "-" + mes + "-" + year);
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
		lblUemLogo.setBounds(50, 0, 150, 100);
		Header.add(lblUemLogo);

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
		lblPerfil.setBounds(850, 0, 100, 100);
		Header.add(lblPerfil);

		infoExtra = new JPanel();
		infoExtra.setBorder(null);
		infoExtra.setBounds(782, 144, 170, 500);
		contentPane.add(infoExtra);
		infoExtra.setLayout(null);

		lblNumAlumnos = new JLabel("Nº alumnos:");
		lblNumAlumnos.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumAlumnos.setBounds(0, 0, 85, 125);
		infoExtra.add(lblNumAlumnos);

		lblSimulador = new JLabel("Simulador:");
		lblSimulador.setHorizontalAlignment(SwingConstants.LEFT);
		lblSimulador.setBounds(0, 136, 85, 125);
		infoExtra.add(lblSimulador);

		lblActor = new JLabel("Actor:");
		lblActor.setHorizontalAlignment(SwingConstants.LEFT);
		lblActor.setBounds(0, 272, 85, 125);
		infoExtra.add(lblActor);

		lblNombreSimulador = new JLabel("");
		lblNombreSimulador.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreSimulador.setBounds(85, 136, 85, 125);
		infoExtra.add(lblNombreSimulador);

		lblDocumentacion = new JLabel("Documentación:");
		lblDocumentacion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDocumentacion.setBounds(0, 375, 110, 125);
		infoExtra.add(lblDocumentacion);

		chckbxActor = new JCheckBox("");
		chckbxActor.setBounds(85, 272, 85, 125);
		infoExtra.add(chckbxActor);
		chckbxActor.setHorizontalAlignment(SwingConstants.CENTER);

		lblNumeroAlumnos = new JLabel("");
		lblNumeroAlumnos.setBounds(85, 0, 85, 125);
		infoExtra.add(lblNumeroAlumnos);
		lblNumeroAlumnos.setHorizontalAlignment(SwingConstants.CENTER);

		lblDocumentacionNumero = new JLabel("");
		lblDocumentacionNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblDocumentacionNumero.setBounds(97, 375, 73, 125);
		infoExtra.add(lblDocumentacionNumero);

		btnInformes = new JButton("Informes");
		btnInformes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.homeToInformes();
			}
		});
		btnInformes.setBounds(533, 685, 170, 40);
		contentPane.add(btnInformes);

		lblNewLabel = new JLabel("Selecionar día:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(465, 111, 110, 22);
		contentPane.add(lblNewLabel);

		txtBuscador = new JTextField();
		txtBuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				controlador.solicitudBuscador(this);
			}
		});
		txtBuscador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtBuscador.setText("");
			}
		});
		txtBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscador.setText("Buscador");
		txtBuscador.setBounds(782, 111, 140, 22);
		contentPane.add(txtBuscador);
		txtBuscador.setColumns(10);

		ImageIcon lupa = new ImageIcon("./img/buscar.png");
		lblLupa = new JLabel(lupa);
		lblLupa.setBounds(932, 111, 20, 22);
		contentPane.add(lblLupa);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if ("date".equals(e.getPropertyName())) {

					String[] datosFecha = String.valueOf(e.getNewValue()).split(" ");
					dia = datosFecha[2];
					mes = numeroMes(datosFecha[1]);
					year = datosFecha[datosFecha.length - 1];
					actualizarFecha();
					controlador.solicitudDatosHome();

				}
			}
		});
		dateChooser.setBounds(585, 111, 150, 20);
		contentPane.add(dateChooser);

	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void confirmacionSalir() {
		int valorRetorno = JOptionPane.showConfirmDialog(rootPane, "¿Está seguro/a de que desea salir?");
		if (JOptionPane.YES_OPTION == valorRetorno) {
			setVisible(false);
			controlador.homeToLogin();
		}
	}

	public void vistaDefault() {
		btnGestionar.setVisible(true);
		btnInformes.setVisible(true);
		btnInfoExtra.setBounds(284, 685, 170, 40);
	}


	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas = modeloConsultas;
	}

	public void vistaUsuarioLectura() {
		btnGestionar.setVisible(false);
		btnInformes.setVisible(false);
		btnInfoExtra.setBounds(782, 685, 170, 40);
	}

	public DefaultTableModel getModel() {
		return (DefaultTableModel) tablaRegistros.getModel();

	}
	
	public String getFecha() {
		return lblTitulo.getText();
	}

	public Object[] getDatosFilaTabla() {
		Object[] datos = new Object[tablaRegistros.getColumnCount()];
		for (int i = 0; i < datos.length; i++) {
			datos[i] = String.valueOf(tablaRegistros.getValueAt(tablaRegistros.getSelectedRow(), i));
		}
		return datos;
	}

	public void actualizarInfoExtra() {
		lblNumeroAlumnos.setText(modeloConsultas.getNumeroAlumos());
		lblNombreSimulador.setText(modeloConsultas.getSimulador());
		if (modeloConsultas.tieneActor()) {
			chckbxActor.setSelected(true);
		} else {
			chckbxActor.setSelected(false);
		}
		lblDocumentacionNumero.setText(modeloConsultas.getDocumentacion());

	}

	public void fechaActual() {
		Calendar fecha = Calendar.getInstance();
		dia = String.valueOf(fecha.get(Calendar.DATE));
		if (fecha.get(Calendar.MONTH) + 1 < 10) {
			mes = "0" + String.valueOf(fecha.get(Calendar.MONTH) + 1);
		} else {
			mes = String.valueOf(fecha.get(Calendar.MONTH) + 1);
		}
		
		year = String.valueOf(fecha.get(Calendar.YEAR));
	}

	public void actualizarFecha() {
		lblTitulo.setText(dia + "-" + mes + "-" + year);
	}

	public String numeroMes(String mes) {
		String numero = "";

		switch (mes.toUpperCase()) {
		case "JAN":
			numero = "01";
			break;
		case "FEB":
			numero = "02";
			break;
		case "MAR":
			numero = "03";
			break;
		case "APR":
			numero = "04";
			break;
		case "MAY":
			numero = "05";
			break;
		case "JUN":
			numero = "06";
			break;
		case "JUL":
			numero = "07";
			break;
		case "AUG":
			numero = "08";
			break;
		case "SEP":
			numero = "09";
			break;
		case "OCT":
			numero = "10";
			break;
		case "NOV":
			numero = "11";
			break;
		case "DEC":
			numero = "12";
			break;

		}

		return numero;
	}

	public String getPalabraBuscador() {
		// TODO Auto-generated method stub
		return txtBuscador.getText();
	}
	
	public void filtro(){
		TableRowSorter trs = modeloConsultas.getFiltro();
		tablaRegistros.setRowSorter(trs);
	}
	
	
}
