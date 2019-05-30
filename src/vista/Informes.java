package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import enums.ListadoInformes;
import modelo.Modelo;
import modelo.ModeloConsultas;

public class Informes extends JFrame {

	private Controlador controlador;
	private ModeloConsultas modeloConsultas;
	private Modelo modelo;
	private JPanel contentPane;
	private JTable tablaInformes;
	private JPanel HeaderPanel;
	private JScrollPane scrollPane;
	private JLabel lblTitulo;
	private JLabel lblUemLogo;
	private JLabel lblPerfil;
	private JButton btnVolver;
	private JLabel lblDescargar;
	private JTable TablaInfoAlumnos;
	private JScrollPane scrollPane_2;
	private JComboBox comboBoxInformes;
	private JLabel lblDescargarInforme;
	private String ruta;
	private JLabel lblInfo;

	public Informes() {
		setResizable(false);
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
		scrollPane.setBounds(98, 168, 800, 473);
		contentPane.add(scrollPane);

		tablaInformes = new JTable();
		tablaInformes.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		tablaInformes.setRowHeight(40);
		tablaInformes.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tablaInformes);
		//

		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(164, 44, 52));
		HeaderPanel.setBounds(0, 0, 1000, 100);
		contentPane.add(HeaderPanel);
		HeaderPanel.setLayout(null);

		lblTitulo = new JLabel("Informes");
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
		HeaderPanel.add(lblPerfil);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controlador.informesToHome();
			}
		});
		btnVolver.setBounds(425, 685, 150, 40);
		contentPane.add(btnVolver);

		ImageIcon descargar = new ImageIcon("./img/descargar.png");
		lblDescargar = new JLabel(descargar);
		lblDescargar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rutaArchivoExcel();
				controlador.exportar();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.HAND_CURSOR);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		lblDescargar.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblDescargar.setBounds(98, 129, 20, 22);

		contentPane.add(lblDescargar);

		comboBoxInformes = new JComboBox();
		comboBoxInformes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.solicitudInforme();
			}
		});
		comboBoxInformes.setModel(new DefaultComboBoxModel(ListadoInformes.values()));
		comboBoxInformes.setBounds(504, 123, 394, 33);
		contentPane.add(comboBoxInformes);

		lblDescargarInforme = new JLabel("Descargar");
		lblDescargarInforme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescargarInforme.setBounds(128, 124, 100, 33);
		contentPane.add(lblDescargarInforme);
		
		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(240, 129, 252, 27);
		contentPane.add(lblInfo);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas = modeloConsultas;
	}
	
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public String getInforme() {
		return String.valueOf(comboBoxInformes.getSelectedItem());
	}

	public DefaultTableModel getModel() {
		return (DefaultTableModel) tablaInformes.getModel();
	}
	
	public String getRuta() {
		return ruta;
	}

	private void rutaArchivoExcel() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.xlsx", "XLSX");

		// Le indicamos el filtro
		fileChooser.setFileFilter(filtro);
		int result = fileChooser.showSaveDialog(contentPane);
		File fileName = fileChooser.getSelectedFile();
		if ((fileName != null) && (!fileName.getName().equals(""))) {
			ruta = fileName.getAbsolutePath();
			if (!ruta.substring(ruta.length() - 4).equalsIgnoreCase("xlsx"))
				ruta = ruta + ".xlsx";
		}
	}
	
	public void actualizarInfo() {
		lblInfo.setText(modelo.getRespuesta());
	}
}
