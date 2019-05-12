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

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;

public class GestionSalas extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JTable tablaSalas;
	private JTextField txtCodigo;
	private JTextField txtTipoSala;
	private JButton btnVolver;
	private JButton btnModificarAlumno;
	private JButton btnAddAlumno;
	private JPanel Header;
	private JLabel lblSalas;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JScrollPane scrollPaneRegistros;
	private JButton btnBorrar;
	private JTextField txtNumero;
	private JTextField txtCapacidad;

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

		tablaSalas.setRowHeight(30);
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

		btnModificarAlumno = new JButton("Modificar Sala");
		btnModificarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificarAlumno.setBounds(325, 685, 120, 40);
		contentPane.add(btnModificarAlumno);

		btnAddAlumno = new JButton("A\u00F1adir Sala");
		btnAddAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnBorrar = new JButton("Borrar Sala");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBorrar.setBounds(575, 685, 120, 40);
		contentPane.add(btnBorrar);
		btnAddAlumno.setBounds(782, 685, 120, 40);
		contentPane.add(btnAddAlumno);

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
}
