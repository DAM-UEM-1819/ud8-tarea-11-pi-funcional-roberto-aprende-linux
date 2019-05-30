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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import enums.ActoresComplexion;
import enums.ActoresEdad;
import enums.ActoresGenero;
import enums.ActoresIdioma;
import enums.ListadoInformes;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;

public class GestionActores extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JTable tablaActores;
	private JTextField txtNombre;
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
	private JLabel lblImportarActores;
	private JComboBox comboBoxEdad;
	private JComboBox comboBoxGenero;
	private JComboBox comboBoxIdioma;
	private JComboBox comboBoxComplexion;
	private JCheckBox chckbxActivo;
	private JLabel lblLupa;
	private JLabel lblInfo;

	public GestionActores() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				controlador.solicitudDatosActores();
				deshabilitarBotones();
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

		tablaActores = new JTable();
		tablaActores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ponerDatos();
				habilitarBotones();
			}

		});
		tablaActores.setRowHeight(30);
		tablaActores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaActores.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tablaActores);

		txtNombre = new JTextField();
		txtNombre.setBounds(98, 629, 226, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(164, 44, 52));
		HeaderPanel.setBounds(0, 0, 1000, 100);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Actores");
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
				controlador.gestionActoresToPerfil();
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
				controlador.gestionActoresToGestion();
			}
		});
		btnVolver.setBounds(100, 685, 150, 40);
		contentPane.add(btnVolver);

		btnModificarActor = new JButton("Modificar actor");
		btnModificarActor.setEnabled(false);
		btnModificarActor.setBounds(316, 685, 150, 40);
		contentPane.add(btnModificarActor);

		btnAI_actor = new JButton("Activo/Inactivo");
		btnAI_actor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//controlador.solicitudBorrarActor();
			}
		});
		btnAI_actor.setEnabled(false);
		btnAI_actor.setBounds(532, 685, 150, 40);
		contentPane.add(btnAI_actor);

		btnAddActor = new JButton(" A\u00F1adir actor");
		btnAddActor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.solicitudAddActor();
				if (modeloGestionDatos.getSeHaCreado()) {
					addActor();
				}
			}
		});
		btnAddActor.setBounds(748, 685, 150, 40);
		contentPane.add(btnAddActor);

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
					controlador.solicitudDatosActores();
				}
			}
		});
		txtBuscador.setText("Buscador");
		txtBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscador.setBounds(728, 111, 140, 22);
		contentPane.add(txtBuscador);

		lblImportarActores = new JLabel("Importar Actores");
		lblImportarActores.setIcon(
				new ImageIcon(GestionActores.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		lblImportarActores.setBounds(98, 111, 124, 20);
		contentPane.add(lblImportarActores);
		lblImportarActores.setVisible(false);

		comboBoxEdad = new JComboBox();
		comboBoxEdad.setModel(new DefaultComboBoxModel(ActoresEdad.values()));
		comboBoxEdad.setBounds(334, 629, 121, 30);
		contentPane.add(comboBoxEdad);

		comboBoxGenero = new JComboBox();
		comboBoxGenero.setModel(new DefaultComboBoxModel(ActoresGenero.values()));
		comboBoxGenero.setBounds(465, 629, 103, 30);
		contentPane.add(comboBoxGenero);

		comboBoxIdioma = new JComboBox();
		comboBoxIdioma.setModel(new DefaultComboBoxModel(ActoresIdioma.values()));
		comboBoxIdioma.setBounds(575, 629, 103, 30);
		contentPane.add(comboBoxIdioma);

		comboBoxComplexion = new JComboBox();
		comboBoxComplexion.setModel(new DefaultComboBoxModel(ActoresComplexion.values()));
		comboBoxComplexion.setBounds(688, 629, 103, 30);
		contentPane.add(comboBoxComplexion);

		chckbxActivo = new JCheckBox("Activo");
		chckbxActivo.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxActivo.setBounds(801, 633, 97, 23);
		contentPane.add(chckbxActivo);

		ImageIcon lupa = new ImageIcon("./img/buscar.png");
		lblLupa = new JLabel(lupa);
		lblLupa.setBounds(878, 111, 20, 22);
		contentPane.add(lblLupa);

		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(0, 111, 1000, 22);
		contentPane.add(lblInfo);
	}

	public String getNombre() {
		return txtNombre.getText();
	}

	public String getEdad() {
		return String.valueOf(comboBoxEdad.getSelectedItem());
	}

	public String getGenero() {
		return String.valueOf(comboBoxGenero.getSelectedItem());
	}

	public String getIdioma() {
		return String.valueOf(comboBoxIdioma.getSelectedItem());
	}

	public String getComplexion() {
		return String.valueOf(comboBoxComplexion.getSelectedItem());
	}

	public String getActivo() {
		String resultado = chckbxActivo.isSelected() == true ? "1" : "0";
		return resultado;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public void setComboBoxEdad(JComboBox comboBoxEdad) {
		this.comboBoxEdad = comboBoxEdad;
	}

	public void setComboBoxGenero(JComboBox comboBoxGenero) {
		this.comboBoxGenero = comboBoxGenero;
	}

	public void setComboBoxIdioma(JComboBox comboBoxIdioma) {
		this.comboBoxIdioma = comboBoxIdioma;
	}

	public void setComboBoxComplexion(JComboBox comboBoxComplexion) {
		this.comboBoxComplexion = comboBoxComplexion;
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
		return (DefaultTableModel) tablaActores.getModel();
	}

	public String getPalabraBuscador() {
		return txtBuscador.getText();
	}

	public void actualizarInfo() {
		lblInfo.setText(modeloGestionDatos.getRespuesta());
	}

	public void addActor() {
		DefaultTableModel model = (DefaultTableModel) tablaActores.getModel();
		model.addRow(modeloGestionDatos.getDatosfilasTabla());
		limpiarTxt();
	}

	private void limpiarTxt() {
		txtNombre.setText("");
		chckbxActivo.setSelected(false);

	}

	private void deshabilitarBotones() {
		btnModificarActor.setEnabled(false);
		btnAI_actor.setEnabled(false);

	}

	private void habilitarBotones() {
		btnModificarActor.setEnabled(true);
		btnAI_actor.setEnabled(true);

	}

	private void ponerDatos() {
		txtNombre.setText(String.valueOf(tablaActores.getValueAt(tablaActores.getSelectedRow(), 0)));
		comboBoxEdad.setSelectedItem(
				String.valueOf(tablaActores.getValueAt(tablaActores.getSelectedRow(), 1)).toUpperCase());
		if(String.valueOf(tablaActores.getValueAt(tablaActores.getSelectedRow(), 5)).equals("1")){
			chckbxActivo.setSelected(true);
		} else {
			chckbxActivo.setSelected(false);
		}

	}
}
