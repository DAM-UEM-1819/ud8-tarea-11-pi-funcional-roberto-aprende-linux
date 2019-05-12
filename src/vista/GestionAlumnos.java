package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controlador.Controlador;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;

public class GestionAlumnos extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JTable tablaAlumnos;
	private JTextField txtExpediente;
	private JTextField txtNombre;
	private JButton btnVolver;
	private JButton btnModificarAlumno;
	private JButton btnAddAlumno;
	private JPanel Header;
	private JLabel lblAlumnos;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JScrollPane scrollPaneRegistros;
	private JButton btnBorrarAlumno;
	private JCheckBox chckbxActivoInactivo;
	private JLabel labelImportar;
	private JTextField txtBuscador;
	private JComboBox comboBoxColumna;
	private JLabel lblInfo;

	public GestionAlumnos() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				controlador.solicitudDatosAlumnos();
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
		
		

		scrollPaneRegistros = new JScrollPane();
		scrollPaneRegistros.setBounds(100, 145, 800, 450);
		contentPane.add(scrollPaneRegistros);

		tablaAlumnos = new JTable();
		tablaAlumnos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		tablaAlumnos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtExpediente.setText(String.valueOf( tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 0)));
				txtNombre.setText(String.valueOf(tablaAlumnos.getValueAt(tablaAlumnos.getSelectedRow(), 1)));
			}
		});
		tablaAlumnos.setRowHeight(30);

		scrollPaneRegistros.setViewportView(tablaAlumnos);

		txtExpediente = new JTextField();
		txtExpediente.setBounds(125, 600, 200, 30);
		contentPane.add(txtExpediente);
		txtExpediente.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(407, 600, 200, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.gestionAlumnosToGestion();
			}
		});
		btnVolver.setBounds(100, 685, 120, 40);
		contentPane.add(btnVolver);

		btnModificarAlumno = new JButton("Modificar Alumno");
		btnModificarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificarAlumno.setBounds(325, 685, 120, 40);
		contentPane.add(btnModificarAlumno);

		btnAddAlumno = new JButton("A\u00F1adir Alumno");
		btnAddAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.solicitudcrearAlumno();
				addAlumno();
			}
		});
		btnBorrarAlumno = new JButton("Borrar Alumno");
		btnBorrarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.solicitudborrarAlumno();
				// tablaAlumnos.getSelectedRow();
			}
		});

		btnBorrarAlumno.setBounds(575, 685, 120, 40);
		contentPane.add(btnBorrarAlumno);
		btnAddAlumno.setBounds(782, 685, 120, 40);
		contentPane.add(btnAddAlumno);

		Header = new JPanel();
		Header.setBackground(new Color(165, 42, 42));
		Header.setBounds(0, 0, 984, 100);
		contentPane.add(Header);
		Header.setLayout(null);

		lblAlumnos = new JLabel("Alumnos");
		lblAlumnos.setForeground(new Color(255, 255, 255));
		lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlumnos.setBounds(250, 0, 500, 100);
		lblAlumnos.setFont(new Font("Tahoma", Font.PLAIN, 50));
		Header.add(lblAlumnos);

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
                controlador.gestionAlumnosToPerfil();
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

		chckbxActivoInactivo = new JCheckBox("Activo/Inactivo");
		chckbxActivoInactivo.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxActivoInactivo.setBounds(679, 602, 200, 23);
		contentPane.add(chckbxActivoInactivo);

		labelImportar = new JLabel("Importar Actividades");
		labelImportar.setIcon(
				new ImageIcon(GestionAlumnos.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		labelImportar.setBounds(100, 111, 124, 20);
		contentPane.add(labelImportar);

		txtBuscador = new JTextField();
		txtBuscador.setText("Buscador");
		txtBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscador.setColumns(10);
		txtBuscador.setBounds(667, 111, 86, 20);
		contentPane.add(txtBuscador);

		comboBoxColumna = new JComboBox();
		comboBoxColumna
				.setModel(new DefaultComboBoxModel(new String[] { "Columna", "Expediente", "Nombre y apellido" }));
		comboBoxColumna.setBounds(763, 111, 104, 20);
		contentPane.add(comboBoxColumna);
		
		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(234, 111, 429, 23);
		contentPane.add(lblInfo);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void modificarAlumnoAlerta() {
		JOptionPane.showConfirmDialog(rootPane, "�Desea modificar el usuario seleccionado?");
	}

	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas = modeloConsultas;
	}

	public void setModeloGestionDatos(ModeloGestionDatos modeloGestionDatos) {
		this.modeloGestionDatos = modeloGestionDatos;
	}

	public DefaultTableModel getModel() {
		return (DefaultTableModel) tablaAlumnos.getModel();
	}

	// getters

	public String getExp() {
		return txtExpediente.getText();
	}

	public String getNombre() {
		return txtNombre.getText();
	}
	
	public void addAlumno() {
		DefaultTableModel model = (DefaultTableModel) tablaAlumnos.getModel();
		model.addRow(modeloGestionDatos.getDatosfilasTabla());
	}
	
	public void actualizarInfo() {
		lblInfo.setText(modeloGestionDatos.getRespuesta());
	}
}
