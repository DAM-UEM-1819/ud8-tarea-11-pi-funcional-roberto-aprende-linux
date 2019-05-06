package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.Properties;

import javax.swing.table.DefaultTableModel;

import controlador.*;
import vista.*;

public class Modelo implements IModelo {

	// Atributos para relacionar
	private Login login;
	private Home home;
	private Gestion gestion;
	private Informes informes;
//	private Ocupaciones ocupaciones;
	private InformacionExtra infoExtra;
	private CrearUsuario crearUsuario;
	private GestionUsuarios gestionUsuarios;
	private GestionAcad gestionAcad;
	private GestionActividad gestionActividad;
	private GestionActores gestionActores;
	private GestionAlumnos gestionAlumnos;
	private GestionRegistros gestionRegistros;
	private GestionAsignatura gestionAsignatura;
	private GestionProfesores gestionProfesores;
	private GestionSalas gestionSalas;
	private VerGrupos verGrupos;
	private Perfil perfil;
	private Controlador controlador;

	// Atributos Base de datos
	private String baseDatos;
	private String usuarioDB;
	private String passwdDB;
	private String urlDB;
	private Connection conexion;

	// Atributos Fichero
	private Properties propiedades;
	private InputStream entrada;
	private OutputStream salida;
	private File fichero;

	// Atributos internos
	private int contador;
	private String respuesta;
	private DefaultTableModel tableModel;
	private String[] nombreColumnasTabla;
	private Object[] datosFilasTabla;

	// Sentencias Select SQL
	private String selectPasswdUsuario = "SELECT PWD FROM HOSPITAL.USERS WHERE USR = ?"; // Inicio sesion
	private String selectTodosUsuarios = "SELECT USR, ROL FROM HOSPITAL.USERS"; // Tabla usuarios
	private String selectTodosRegistros = "SELECT cod_registro , fecha , horas_profesor , actividad_nombre FROM HOSPITAL.Registro"; //Select registro
	private String selectTodosAlumnos = "SELECT * FROM HOSPITAL.alumno"; //Select registro
	
	// Sentencias Insertado SQL
	private String insertUsuario = "INSERT INTO HOSPITAL.users (usr, pwd, rol) VALUES (?,?,?)";

	// Sentencias Borrado SQL

	public Modelo() {
		propiedades = new Properties();
		fichero = new File("./configuracion.ini");

		try {
			entrada = new FileInputStream(fichero);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			propiedades.load(entrada);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		baseDatos = propiedades.getProperty("baseDatos");
		usuarioDB = propiedades.getProperty("usuario");
		passwdDB = propiedades.getProperty("passwd");
		urlDB = propiedades.getProperty("url");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection(urlDB, usuarioDB, passwdDB);
			System.out.println("La conexion ha sido exitosa");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("La conexion ha fallado");
			e.printStackTrace();
		}

	}

	/*
	 * ********************************************* INICIO SETTERS
	 * *********************************************
	 */

	public void setLogin(Login login) {
		this.login = login;
	}

	public void setHome(Home home) {
		this.home = home;
	}

	public void setGestion(Gestion gestion) {
		this.gestion = gestion;
	}

	public void setInformes(Informes informes) {
		this.informes = informes;
	}

	public void setInfoExtra(InformacionExtra infoExtra) {
		this.infoExtra = infoExtra;
	}

	public void setCrearUsuario(CrearUsuario crearUsuario) {
		this.crearUsuario = crearUsuario;
	}

	public void setGestionUsuarios(GestionUsuarios gestionUsuarios) {
		this.gestionUsuarios = gestionUsuarios;
	}

	public void setGestionAcad(GestionAcad gestionAcad) {
		this.gestionAcad = gestionAcad;
	}

	public void setGestionActividad(GestionActividad gestionActividad) {
		this.gestionActividad = gestionActividad;
	}

	public void setGestionActores(GestionActores gestionActores) {
		this.gestionActores = gestionActores;
	}

	public void setGestionAlumnos(GestionAlumnos gestionAlumnos) {
		this.gestionAlumnos = gestionAlumnos;
	}

	public void setGestionRegistros(GestionRegistros gestionRegistros) {
		this.gestionRegistros = gestionRegistros;
	}

	public void setGestionAsignatura(GestionAsignatura gestionAsignatura) {
		this.gestionAsignatura = gestionAsignatura;
	}

	public void setGestionProfesores(GestionProfesores gestionProfesores) {
		this.gestionProfesores = gestionProfesores;
	}

	public void setGestionSalas(GestionSalas gestionSalas) {
		this.gestionSalas = gestionSalas;
	}

	public void setVerGrupos(VerGrupos verGrupos) {
		this.verGrupos = verGrupos;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	/*
	 * ********************************************* INICIO GETTERS
	 * *********************************************
	 */

	public String getRespuesta() {
		return respuesta;
	}

	/*
	 * ********************************************* INICIO METODOS BASE DATOS
	 * *********************************************
	 */

	public void loginConfirmacion(String usuario, String passwd) {
		String sql = selectPasswdUsuario;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setString(1, usuario);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next() && rs.getString(1).equals(passwd)) {
				login.loginExitoso();
			} else {
				contador++;
				if (contador >= 3) {
					login.salir();
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void crearUsuario(String user, String passwd, String rol) {
		String sql = selectPasswdUsuario;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setString(1, user);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				sql = insertUsuario;
				try {
					pstmt = conexion.prepareStatement(sql);
					pstmt.setString(1, user);
					pstmt.setString(2, passwd);
					pstmt.setString(3, rol);
					rs = pstmt.executeQuery();
					respuesta = "Usuario creado";
				} catch (Exception e) {
					respuesta = "Error, algun campo vacio";
					crearUsuario.actualizarInfo();
					e.printStackTrace();
				}
			} else {
				respuesta = "El usuario ya existe";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		crearUsuario.actualizarInfo();
	}

	public void getTablaUsuarios(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosUsuarios;
		getDatos(sql);
	}
	
	public void getTablaAcad(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosUsuarios;
		getDatos(sql);
	}
	
	public void getTablaActividad(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosUsuarios;
		getDatos(sql);
	}
	
	public void getTablaActores(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosUsuarios;
		getDatos(sql);
	}
	
	public void getTablaAlumnos(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosAlumnos;
		getDatos(sql);
	}
	
	public void getTablaAsignatura(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosUsuarios;
		getDatos(sql);
	}
	
	public void getTablaProfesores(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosUsuarios;
		getDatos(sql);
	}
	
	public void getTablaRegistros(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosRegistros;
		getDatos(sql);
	}
	public void getTablaSalas(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosUsuarios;
		getDatos(sql);
	}

	private void getDatos(String sql) {
		tableModel.setColumnCount(0);
		tableModel.setRowCount(0);
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData metadatos = rs.getMetaData();

			int numColumnas = metadatos.getColumnCount();
			datosFilasTabla = new Object[numColumnas];

			for (int i = 0; i < numColumnas; i++) {
				tableModel.addColumn(metadatos.getColumnName(i + 1));
			}
			
			// metadatos.getColumnName(1); 

			while (rs.next()) {
				for (int i = 0; i < numColumnas; i++) {
					datosFilasTabla[i] = rs.getObject(i+ 1);
				}
				tableModel.addRow(datosFilasTabla);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
