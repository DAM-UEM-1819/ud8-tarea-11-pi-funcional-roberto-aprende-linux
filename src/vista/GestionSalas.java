package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabableView;

import controlador.Controlador;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;
import javax.swing.ListSelectionModel;

public class GestionSalas extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JTable tablaSalas;
	private JTextField txtCodigo;
	private JTextField txtTipoSala;
	private JButton btnVolver;
	private JButton btnModificarSala;
	private JButton btnAddSala;
	private JPanel Header;
	private JLabel lblSalas;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JScrollPane scrollPaneRegistros;
	private JButton btnBorrar;
	private JTextField txtNumero;
	private JTextField txtCapacidad;
	private JLabel lblImportarSalas;
	private JTextField txtBuscador;
	private JComboBox comboBoxColumna;
	private JLabel lblInfo;

	public GestionSalas() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				controlador.solicitudDatosSalas();
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
		scrollPaneRegistros.setBounds(100, 145, 800, 450);
		contentPane.add(scrollPaneRegistros);

		tablaSalas = new JTable();
		tablaSalas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaSalas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtCodigo.setText(String.valueOf(tablaSalas.getValueAt(tablaSalas.getSelectedRow(), 0)));
				txtTipoSala.setText(String.valueOf(tablaSalas.getValueAt(tablaSalas.getSelectedRow(), 1)));
				txtCapacidad.setText(String.valueOf(tablaSalas.getValueAt(tablaSalas.getSelectedRow(), 2)));
				txtNumero.setText(String.valueOf(tablaSalas.getValueAt(tablaSalas.getSelectedRow(), 3)));
				
				
			}
		});

		tablaSalas.setRowHeight(30);
		tablaSalas.getTableHeader().setReorderingAllowed(false);
		scrollPaneRegistros.setViewportView(tablaSalas);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(110, 600, 150, 30);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtTipoSala = new JTextField();
		txtTipoSala.setBounds(322, 600, 150, 30);
		contentPane.add(txtTipoSala);
		txtTipoSala.setColumns(10);

		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(735, 600, 150, 30);
		contentPane.add(txtNumero);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.gestionSalasToGestion();
			}
		});

		txtCapacidad = new JTextField();
		txtCapacidad.setColumns(10);
		txtCapacidad.setBounds(533, 600, 150, 30);
		contentPane.add(txtCapacidad);
		btnVolver.setBounds(100, 685, 120, 40);
		contentPane.add(btnVolver);

		btnModificarSala = new JButton("Modificar Sala");
		btnModificarSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblInfo.setText("");
				controlador.solicutudModificarSala();
				if (modeloGestionDatos.getSeHaCreado()) {
					modSala();
				}
				
			}
		});
		btnModificarSala.setBounds(325, 685, 120, 40);
		contentPane.add(btnModificarSala);

		btnAddSala = new JButton("A\u00F1adir Sala");
		btnAddSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblInfo.setText("");
				controlador.solicitudCrearSala();
				if (!modeloConsultas.getExiste() && modeloGestionDatos.getSeHaCreado()) {
					addSala();
				}
				
			}
		});

		btnBorrar = new JButton("Borrar Sala");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblInfo.setText("");
				controlador.solicitudBorrar(this);
				if (modeloGestionDatos.getSeHaBorrado()) {
					borrado();
				}
			}
		});
		btnBorrar.setBounds(575, 685, 120, 40);
		contentPane.add(btnBorrar);
		btnAddSala.setBounds(782, 685, 120, 40);
		contentPane.add(btnAddSala);

		Header = new JPanel();
		Header.setBackground(new Color(165, 42, 42));
		Header.setBounds(0, 0, 984, 100);
		contentPane.add(Header);
		Header.setLayout(null);

		lblSalas = new JLabel("Salas");
		lblSalas.setForeground(new Color(255, 255, 255));
		lblSalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalas.setBounds(250, 0, 500, 100);
		lblSalas.setFont(new Font("Tahoma", Font.PLAIN, 50));
		Header.add(lblSalas);

		ImageIcon ueIcon = new ImageIcon("./img/ue.png");
		lblUemLogo = new JLabel(ueIcon);
		lblUemLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblUemLogo.setBounds(0, 0, 240, 100);
		Header.add(lblUemLogo);

		ImageIcon perfilIcon = new ImageIcon("./img/usuario.png");
		lblPerfil = new JLabel(perfilIcon);
		lblPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				controlador.gestionSalasToPerfil();
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
		Header.add(lblPerfil);
		

		lblImportarSalas = new JLabel("Importar Salas");
		lblImportarSalas.setIcon(
				new ImageIcon(GestionAlumnos.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		lblImportarSalas.setBounds(100, 111, 124, 20);
		contentPane.add(lblImportarSalas);
		lblImportarSalas.setVisible(false);
		
		
		txtBuscador = new JTextField();
		txtBuscador.setText("Buscador");
		txtBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscador.setColumns(10);
		txtBuscador.setBounds(814, 114, 86, 20);
		contentPane.add(txtBuscador);

	
		

		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(234, 111, 429, 23);
		contentPane.add(lblInfo);

	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void modificarSalaAlerta() {
		JOptionPane.showConfirmDialog(rootPane, "�Desea modificar la sala seleccionada?");
	}

	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas = modeloConsultas;
	}

	public void setModeloGestionDatos(ModeloGestionDatos modeloGestionDatos) {
		this.modeloGestionDatos = modeloGestionDatos;
	}

	public DefaultTableModel getModel() {
		return (DefaultTableModel) tablaSalas.getModel();
	}

	public String getPrimaryKey() {
		return String.valueOf(tablaSalas.getValueAt(tablaSalas.getSelectedRow(), 0));
	}
	
	public String getCodigo() {
		return txtCodigo.getText();
	}
	
	public String getTipoSala() {
		return txtTipoSala.getText();
	}
	
	public String getNumero() {
		return txtNumero.getText();
	}
	
	public String getCapacidad() {
		return txtCapacidad.getText();
	}
	
	public void addSala() {
		DefaultTableModel model = (DefaultTableModel) tablaSalas.getModel();
		model.addRow(modeloGestionDatos.getDatosfilasTabla());
		limpiarTxt();
	}

	private void limpiarTxt() {
		txtCodigo.setText("");
		txtTipoSala.setText("");
		txtNumero.setText("");
		txtCapacidad.setText("");
	}
	
	public void borrado() {
		DefaultTableModel model = (DefaultTableModel) tablaSalas.getModel();
		model.removeRow(tablaSalas.getSelectedRow());
		limpiarTxt();

	}
	
	public void modSala() {
		DefaultTableModel model = (DefaultTableModel) tablaSalas.getModel();
		// model.setValueAt(getExp(),tablaAlumnos.getSelectedRow(), 0);

		if (getCodigo().equals(String.valueOf(model.getValueAt(tablaSalas.getSelectedRow(), 0)))) {
				lblInfo.setText("Sala modificada");
				model.setValueAt(getCodigo(), tablaSalas.getSelectedRow(), 0);
				model.setValueAt(getTipoSala(), tablaSalas.getSelectedRow(), 1);
				model.setValueAt(getCapacidad(), tablaSalas.getSelectedRow(), 2);
				model.setValueAt(getNumero(), tablaSalas.getSelectedRow(), 3);
				limpiarTxt();
			
		} else {
			lblInfo.setText("Error , no puedes modificar el codigo de la sala");
		}
		
		

	}
	
	public void actualizarInfoConsulta() {
			lblInfo.setText(modeloConsultas.getRespuesta());
	}
	
	public void actualizarInfoDatos() {
			lblInfo.setText(modeloGestionDatos.getRespuesta());
		
	}
}
