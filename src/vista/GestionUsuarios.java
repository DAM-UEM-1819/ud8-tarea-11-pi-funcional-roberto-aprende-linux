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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GestionUsuarios extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;
	private JPanel contentPane;
	private JTable tablaUsuarios;
	private JButton btnVolver;
	private JButton btnBorrarUsr;
	private JButton btnAddUsuario;
	private JPanel Header;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JScrollPane scrollPaneRegistros;
	private JLabel lblImportarUsuario;
	private JTextField txtBuscador;
	private JLabel lblNewLabel;

	public GestionUsuarios() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				controlador.solicitudDatosUsuarios();
				btnBorrarUsr.setEnabled(false);
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
		scrollPaneRegistros.setBounds(100, 145, 800, 500);
		contentPane.add(scrollPaneRegistros);

		tablaUsuarios = new JTable();
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

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.gestionUsuariosToGestion();
			}
		});
		btnVolver.setBounds(100, 685, 120, 40);
		contentPane.add(btnVolver);

		btnBorrarUsr = new JButton("Borrar Usuario");
		btnBorrarUsr.setEnabled(false);
		btnBorrarUsr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.solicitudBorrar(this);
				btnBorrarUsr.setEnabled(false);
				if (modeloGestionDatos.getSeHaBorrado()) {
					borrado();
				}
			}
		});
		btnBorrarUsr.setBounds(440, 685, 120, 40);
		contentPane.add(btnBorrarUsr);

		btnAddUsuario = new JButton("A\u00F1adir Usuario");
		btnAddUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.gestionUsuariosToCrearUsuario();
			}
		});
		btnAddUsuario.setBounds(782, 685, 120, 40);
		contentPane.add(btnAddUsuario);

		Header = new JPanel();
		Header.setBackground(new Color(164,44,52));
		Header.setBounds(0, 0, 1000, 100);
		contentPane.add(Header);
		Header.setLayout(null);

		lblTitulo = new JLabel("Usuarios");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 0, 1010, 100);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		Header.add(lblTitulo);

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
		lblPerfil.setBounds(818, 0, 100, 100);
		Header.add(lblPerfil);

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
					controlador.solicitudDatosUsuarios();
				}
			}
		});
		txtBuscador.setText("Buscador");
		txtBuscador.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscador.setBounds(726, 112, 140, 22);
		contentPane.add(txtBuscador);

		lblImportarUsuario = new JLabel("Importar Usuarios");
		lblImportarUsuario.setIcon(
				new ImageIcon(GestionActividad.class.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		lblImportarUsuario.setBounds(100, 114, 124, 20);
		contentPane.add(lblImportarUsuario);
		
		ImageIcon lupa = new ImageIcon("./img/buscar.png");
		lblNewLabel = new JLabel(lupa);
		lblNewLabel.setBounds(880, 111, 20, 22);
		contentPane.add(lblNewLabel);
		lblImportarUsuario.setVisible(false);
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

	private void borrado() {
		DefaultTableModel model = (DefaultTableModel) tablaUsuarios.getModel();
		model.removeRow(tablaUsuarios.getSelectedRow());
	}

}
