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
import javax.swing.JFileChooser;
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
import enums.ActividadSimulador;
import enums.ActividadTipo;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;

public class GestionActividad extends JFrame {
	
	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JTable tablaActividad;
	private JTextField txtNombre;
	private JTextField txtTipo_sala;
	private JTextField txtHorasActividad;
	private JTextField txtDocumentacion_tecnica;
	private JTextField txtAcad;
	private JPanel HeaderPanel;
	private JScrollPane scrollPane;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private JButton btnBorrarActividad;
	private JButton btnAddActividad;
	private JButton btnModificarActividad;
	private JComboBox comboBoxTipoActividad;
	private JComboBox comboBoxSimulador;
	private JTextField txtBuscador;
	private JComboBox comboBoxColumna;
	private JLabel lblImportarActividades;
	private JLabel lblLupa;



	 
	public GestionActividad() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				controlador.solicitudDatosActividad();
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

		scrollPane = new JScrollPane();
		scrollPane.setBounds(98, 145, 800, 450);
		contentPane.add(scrollPane);

		tablaActividad = new JTable();
			tablaActividad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNombre.setText(String.valueOf(tablaActividad.getValueAt(tablaActividad.getSelectedRow(), 0)));
				comboBoxTipoActividad.setName(String.valueOf(tablaActividad.getValueAt(tablaActividad.getSelectedRow(), 1)));
			}
		});
		tablaActividad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaActividad.setRowHeight(30);
		tablaActividad.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tablaActividad);

		txtNombre = new JTextField();
				txtNombre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtNombre.setText("");
			}
		});
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setText("Nombre");
		txtNombre.setBounds(98, 629, 114, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtTipo_sala = new JTextField();
		txtTipo_sala.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtTipo_sala.setText("");
			}
		});
		txtTipo_sala.setHorizontalAlignment(SwingConstants.CENTER);
		txtTipo_sala.setText("Tipo de Sala");
		txtTipo_sala.setBounds(354, 629, 86, 30);
		contentPane.add(txtTipo_sala);
		txtTipo_sala.setColumns(10);

		txtHorasActividad = new JTextField();
		txtHorasActividad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtHorasActividad.setText("");
			}
		});
		txtHorasActividad.setText("Horas de actividad");
		txtHorasActividad.setHorizontalAlignment(SwingConstants.CENTER);
		txtHorasActividad.setBounds(663, 629, 114, 30);
		contentPane.add(txtHorasActividad);
		txtHorasActividad.setColumns(10);

		txtDocumentacion_tecnica = new JTextField();
		txtDocumentacion_tecnica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtDocumentacion_tecnica.setText("");
			}
		});
		txtDocumentacion_tecnica.setText("Documentaci\u00F3n");
		txtDocumentacion_tecnica.setHorizontalAlignment(SwingConstants.CENTER);
		txtDocumentacion_tecnica.setBounds(557, 629, 86, 30);
		contentPane.add(txtDocumentacion_tecnica);
		txtDocumentacion_tecnica.setColumns(10);

		txtAcad = new JTextField();
		txtAcad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtAcad.setText("");
			}
		});
		txtAcad.setHorizontalAlignment(SwingConstants.CENTER);
		txtAcad.setText("A\u00F1o");
		txtAcad.setColumns(10);
		txtAcad.setBounds(787, 629, 98, 30);
		contentPane.add(txtAcad);

		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(164, 44, 52));
		HeaderPanel.setBounds(0, 0, 1000, 100);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Actividad");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(358, 11, 266, 61);
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
				controlador.gestionActividadToPerfil();
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
				controlador.gestionActividadToGestion();
			}
		});
		btnVolver.setBounds(100, 685, 150, 40);
		contentPane.add(btnVolver);

		btnModificarActividad = new JButton("Modificar actividad");
		btnModificarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showConfirmDialog(rootPane, "�Desea modificar el profesor seleccionado?");
			}
		});
		btnModificarActividad.setBounds(316, 685, 150, 40);
		contentPane.add(btnModificarActividad);

		btnBorrarActividad = new JButton("Borrar actividad");
		btnBorrarActividad.setBounds(532, 685, 150, 40);
		contentPane.add(btnBorrarActividad);

		btnAddActividad = new JButton(" A\u00F1adir actividad");
		btnAddActividad.setBounds(748, 685, 150, 40);
		contentPane.add(btnAddActividad);

		comboBoxTipoActividad = new JComboBox();
		
		comboBoxTipoActividad.setModel(new DefaultComboBoxModel(ActividadTipo.values()));
		comboBoxTipoActividad.setBounds(234, 629, 98, 30);
		contentPane.add(comboBoxTipoActividad);

		comboBoxSimulador = new JComboBox();
		comboBoxSimulador.setModel(new DefaultComboBoxModel(ActividadSimulador.values()));
		comboBoxSimulador.setBounds(466, 629, 71, 30);
		contentPane.add(comboBoxSimulador);

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
					controlador.solicitudDatosActividad();
				}
			}
		});
		txtBuscador.setText("Buscador");
		txtBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscador.setBounds(728, 112, 140, 22);
		contentPane.add(txtBuscador);
		txtBuscador.setColumns(10);

		lblImportarActividades = new JLabel("Importar Actividades");
		lblImportarActividades.setIcon(
				new ImageIcon(GestionActividad.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		lblImportarActividades.setBounds(98, 111, 124, 20);
		contentPane.add(lblImportarActividades);

		ImageIcon lupa = new ImageIcon("./img/buscar.png");
		lblLupa = new JLabel(lupa);
		lblLupa.setBounds(878, 111, 20, 22);
		contentPane.add(lblLupa);
		lblImportarActividades.setVisible(false);

	}
	
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	
	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas= modeloConsultas;
	}
	
	public void setModeloGestionDatos(ModeloGestionDatos modeloGestionDatos) {
		this.modeloGestionDatos= modeloGestionDatos;
	}
	
	public DefaultTableModel getModel() {
		return (DefaultTableModel) tablaActividad.getModel();
	}


	public String getPrimaryKey() {
		return String.valueOf(tablaActividad.getValueAt(tablaActividad.getSelectedRow(), 0));
	}
	
	public String getPalabraBuscador() {
		return txtBuscador.getText();
	}
	
	public int confirmacionBorrar() {
		int confirmacion = 1;
		int valorRetorno = JOptionPane.showConfirmDialog(rootPane, "¿Está seguro/a de que desea borrar el registro seleccionado?");
		if (JOptionPane.YES_OPTION== valorRetorno) {
			confirmacion = 0;
		}
		
		return confirmacion;
	}
	
		public void limpiarTxt() {
		txtDocumentacion_tecnica.setText("");
		txtTipo_sala.setText("");
		txtNombre.setText("");
		txtHorasActividad.setText("");
	}
}
