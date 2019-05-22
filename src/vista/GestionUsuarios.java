package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class GestionUsuarios extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JTable tablaUsuarios;
	private JPanel Header;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JScrollPane scrollPaneRegistros;
	private JLabel lblImportarActividades;
	private JPanel titulo;
	private JPanel logo;
	private JPanel perfil;
	private JPanel center;
	private JPanel izquierda;
	private JPanel derecha;

	public GestionUsuarios() {
		Dimension dimensiones = new Dimension(1000, 150);

		ImageIcon ueIcon = new ImageIcon("./img/ue.png");

		ImageIcon perfilIcon = new ImageIcon("./img/usuario.png");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				controlador.solicitudDatosUsuarios();
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
		contentPane.setLayout(new BorderLayout(0, 0));

		Header = new JPanel();
		Header.setMaximumSize(new Dimension(32767, 100));
		Header.setBackground(SystemColor.activeCaption);
		Header.setPreferredSize(new Dimension(1000, 100));
		contentPane.add(Header, BorderLayout.NORTH);
		Header.setLayout(new GridLayout(0, 3, 0, 0));

		logo = new JPanel();
		logo.setMaximumSize(new Dimension(150, 32767));
		logo.setPreferredSize(new Dimension(150, 10));
		logo.setBackground(SystemColor.controlHighlight);
		Header.add(logo);
		logo.setLayout(new BorderLayout(0, 0));
		lblUemLogo = new JLabel();
		lblUemLogo.setMaximumSize(new Dimension(100, 0));
		lblUemLogo.setIcon(ueIcon);
		lblUemLogo.setPreferredSize(new Dimension(100, 100));
		logo.add(lblUemLogo, BorderLayout.NORTH);
		lblUemLogo.setHorizontalAlignment(SwingConstants.CENTER);

		titulo = new JPanel();
		titulo.setMinimumSize(new Dimension(700, 10));
		titulo.setPreferredSize(new Dimension(600, 100));
		titulo.setBackground(SystemColor.controlHighlight);
		Header.add(titulo);
		titulo.setLayout(new BorderLayout(0, 0));

		lblTitulo = new JLabel("Usuarios");
		titulo.add(lblTitulo, BorderLayout.NORTH);
		lblTitulo.setPreferredSize(new Dimension(700, 100));
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 50));

		perfil = new JPanel();
		perfil.setMaximumSize(new Dimension(150, 32767));
		perfil.setPreferredSize(new Dimension(150, 10));
		Header.add(perfil);
		perfil.setBackground(SystemColor.controlHighlight);
		perfil.setLayout(new BorderLayout(0, 0));
		lblPerfil = new JLabel();
		lblPerfil.setPreferredSize(new Dimension(100, 100));
		lblPerfil.setIcon(perfilIcon);
		perfil.add(lblPerfil, BorderLayout.CENTER);
		lblPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				controlador.gestionUsuariosToPerfil();
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

		center = new JPanel();
		center.setPreferredSize(new Dimension(800, 600));
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout(0, 0));

		scrollPaneRegistros = new JScrollPane();
		scrollPaneRegistros.setPreferredSize(new Dimension(800, 600));
		center.add(scrollPaneRegistros);

		tablaUsuarios = new JTable();
		tablaUsuarios.setPreferredSize(new Dimension(800, 600));
		tablaUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnBorrarUsr.setEnabled(true);
			}
		});
		tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaUsuarios.setRowHeight(30);
		tablaUsuarios.getTableHeader().setReorderingAllowed(false);
		scrollPaneRegistros.setViewportView(tablaUsuarios);

		izquierda = new JPanel();
		izquierda.setPreferredSize(new Dimension(100, 10));
		contentPane.add(izquierda, BorderLayout.WEST);

		derecha = new JPanel();
		derecha.setPreferredSize(new Dimension(100, 10));
		contentPane.add(derecha, BorderLayout.EAST);

//		lblImportarActividades = new JLabel("Importar Usuarios");
//		lblImportarActividades.setIcon(
//				new ImageIcon(GestionActividad.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
//		lblImportarActividades.setBounds(100, 114, 124, 20);
//		contentPane.add(lblImportarActividades);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void borrarUsuarioAlerta() {
		JOptionPane.showConfirmDialog(rootPane, "�Desea borrar el usuario seleccionado?");
	}

	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas = modeloConsultas;
	}

	public void setModeloGestionDatos(ModeloGestionDatos modeloGestionDatos) {
		this.modeloGestionDatos = modeloGestionDatos;
	}

	public DefaultTableModel getModel() {
		return (DefaultTableModel) tablaUsuarios.getModel();

	}

	public String getPrimaryKey() {
		return String.valueOf(tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 0));
	}

	public String getPalabraBuscador() {
		return txtBuscador.getText();
	}
}
