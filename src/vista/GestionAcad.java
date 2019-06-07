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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import com.toedter.calendar.JDateChooser;

import controlador.Controlador;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.beans.PropertyChangeEvent;

public class GestionAcad extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JTable tablaAcad;
	private JPanel HeaderPanel;
	private JScrollPane scrollPane;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private JButton btnAI_actor;
	private JButton btnAddAcad;
	private JButton btnModificarAcad;
	private JTextField txtBuscador;
	private JLabel lblImportaionesActividades;
	private JLabel lblLupa;
	private JDateChooser dateChooserSEM1;
	private JDateChooser dateChooserSEM2;
	private String dia;
	private String mes;
	private String year;
	private JLabel lblInfo;
	private ArrayList<String> test;

	public GestionAcad() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				controlador.solicitudDatosAcad();
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

		dateChooserSEM1 = new JDateChooser();
		dateChooserSEM1.setDateFormatString("dd-MM-yyyy");
		dateChooserSEM1.setBounds(376, 617, 164, 31);
		contentPane.add(dateChooserSEM1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(98, 145, 800, 446);
		contentPane.add(scrollPane);

		tablaAcad = new JTable();
		tablaAcad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				actualizarDatos();

			}
		});
		tablaAcad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaAcad.setRowHeight(30);
		tablaAcad.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tablaAcad);

		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(164, 44, 52));
		HeaderPanel.setBounds(0, 0, 1000, 100);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("A\u00F1o academico");
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
				controlador.gestionAcadToPerfil();
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
		HeaderPanel.add(lblPerfil);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				controlador.gestionAcadToGestion();
			}
		});
		btnVolver.setBounds(100, 685, 150, 40);
		contentPane.add(btnVolver);

		btnModificarAcad = new JButton("Modificar a\u00F1o");
		btnModificarAcad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.modificarAcad();

			}
		});
		btnModificarAcad.setBounds(316, 685, 150, 40);
		contentPane.add(btnModificarAcad);

		btnAI_actor = new JButton("Activo/Inactivo");
		btnAI_actor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(dateChooserSEM1.getDate());
				String[] fecha = dateChooserSEM1.getDate().toString().split(" ");
				for (int i = 0; i < fecha.length; i++) {
					System.out.println(fecha[i] + " " + i);

				}
			}

		});
		btnAI_actor.setBounds(532, 685, 150, 40);
		contentPane.add(btnAI_actor);

		btnAddAcad = new JButton(" A\u00F1adir a\u00F1o");
		btnAddAcad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.crearAcad();
				addAcad();
			}
		});
		btnAddAcad.setBounds(748, 685, 150, 40);
		contentPane.add(btnAddAcad);

		lblImportaionesActividades = new JLabel("Importar Actividades");
		lblImportaionesActividades.setEnabled(false);
		lblImportaionesActividades
				.setIcon(new ImageIcon(GestionAcad.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		lblImportaionesActividades.setBounds(96, 111, 124, 20);
		contentPane.add(lblImportaionesActividades);
		lblImportaionesActividades.setVisible(false);

		txtBuscador = new JTextField();
		txtBuscador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtBuscador.setText("");
			}
		});
		txtBuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtBuscador.getText().equals("")) {
					controlador.solicitudBuscador(this);
				} else {
					controlador.solicitudDatosAcad();
				}
			}
		});
		txtBuscador.setText("Buscador");
		txtBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscador.setBounds(728, 112, 140, 22);
		contentPane.add(txtBuscador);

		ImageIcon lupa = new ImageIcon("./img/buscar.png");
		lblLupa = new JLabel(lupa);
		lblLupa.setBounds(878, 112, 20, 22);
		contentPane.add(lblLupa);

		dateChooserSEM2 = new JDateChooser();
		dateChooserSEM2.setDateFormatString("dd-MM-yyyy");
		dateChooserSEM2.setBounds(646, 617, 164, 31);
		contentPane.add(dateChooserSEM2);

		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(234, 111, 429, 23);
		contentPane.add(lblInfo);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas = modeloConsultas;
	}

	public void setModeloGestionDatos(ModeloGestionDatos modeloGestionDatos) {
		this.modeloGestionDatos = modeloGestionDatos;
	}

	public DefaultTableModel getModel() {
		return (DefaultTableModel) tablaAcad.getModel();
	}

	public String getPalabraBuscador() {
		return txtBuscador.getText();
	}

	private void actualizarDatos() {
		int diaSem1 = Integer
				.parseInt(String.valueOf(tablaAcad.getValueAt(tablaAcad.getSelectedRow(), 1)).substring(8, 10));
		int mesSem1 = Integer
				.parseInt(String.valueOf(tablaAcad.getValueAt(tablaAcad.getSelectedRow(), 1)).substring(5, 7));
		int anioSem1 = Integer
				.parseInt(String.valueOf(tablaAcad.getValueAt(tablaAcad.getSelectedRow(), 1)).substring(0, 4));
		Calendar caSem1 = new GregorianCalendar(anioSem1, mesSem1 - 1, diaSem1);
		java.util.Date sem1 = caSem1.getTime();
		int diaSem2 = Integer
				.parseInt(String.valueOf(tablaAcad.getValueAt(tablaAcad.getSelectedRow(), 2)).substring(8, 10));
		int mesSem2 = Integer
				.parseInt(String.valueOf(tablaAcad.getValueAt(tablaAcad.getSelectedRow(), 2)).substring(5, 7));
		int anioSem2 = Integer
				.parseInt(String.valueOf(tablaAcad.getValueAt(tablaAcad.getSelectedRow(), 2)).substring(0, 4));
		Calendar caSem2 = new GregorianCalendar(anioSem2, mesSem2 - 1, diaSem2);
		java.util.Date sem2 = caSem2.getTime();
		dateChooserSEM1.setDate(sem1);
		dateChooserSEM2.setDate(sem2);
	}

	public String getSEM1() {
		String mesSem1 = String.valueOf(dateChooserSEM1.getDate().getMonth() + 1);
		String diaSem1 = String.valueOf(dateChooserSEM1.getDate().getDate());
		String anioSem1 = String.valueOf(dateChooserSEM1.getDate().getYear() + 1900);
		return diaSem1 + "/" + mesSem1 + "/" + anioSem1;
	}

	public String getSEM2() {
		String mesSem2 = String.valueOf(dateChooserSEM2.getDate().getMonth() + 1);
		String diaSem2 = String.valueOf(dateChooserSEM2.getDate().getDate());
		String anioSem2 = String.valueOf(dateChooserSEM2.getDate().getYear() + 1900);
		return diaSem2 + "/" + mesSem2 + "/" + anioSem2;
	}

	public String getAcad() {
		int anio = Integer.parseInt(getSEM1().substring(getSEM1().length() - 2, getSEM1().length()));
		String acad = anio + "/" + (anio + 1);
		return acad;

	}

	public void addAcad() {
		DefaultTableModel model = (DefaultTableModel) tablaAcad.getModel();
		Object sem21[] = modeloGestionDatos.getDatosfilasTabla();
		 String[] fecha1 = dateChooserSEM1.getDate().toString().split(" ");
		 // Año test.add(fecha[5]);
		 // Mes test.add(fecha[1]);
		 // Dia test.add(fecha[2]);
		 // cer test.add(fecha[3]);
		 String[] sem1 = sem21[1].toString().split("/");
		 fecha1[5] = sem1[2];
		 fecha1[2] = sem1[0];
		 fecha1[1] = sem1[0];
		 
		model.addRow(sem21);
		limpiarDateChooser();
	}

	public void actualizarInfo() {
		lblInfo.setText(modeloGestionDatos.getRespuesta());
	}

	public void limpiarDateChooser() {
		dateChooserSEM1.setDate(null);
		dateChooserSEM2.setDate(null);
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

}
