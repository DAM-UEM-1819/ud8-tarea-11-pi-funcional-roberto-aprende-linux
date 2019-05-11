package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Properties;
import java.util.TreeSet;

import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import vista.CrearUsuario;
import vista.Gestion;
import vista.GestionAcad;
import vista.GestionActividad;
import vista.GestionActores;
import vista.GestionAlumnos;
import vista.GestionAsignatura;
import vista.GestionProfesores;
import vista.GestionRegistros;
import vista.GestionSalas;
import vista.GestionUsuarios;
import vista.Home;
import vista.InformacionExtra;
import vista.Informes;
import vista.Login;
import vista.Perfil;
import vista.VerGrupos;

public class ModeloConsultas {

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
	private Modelo modelo;

	// Atributos Fichero
	private Properties propiedades;
	private InputStream entrada;
	private OutputStream salida;
	private File fichero;

	// Atributos internos
	private Connection conexion;
	private int contador;
	private String respuesta;
	private DefaultTableModel tableModel;
	private String[] nombreColumnasTabla;
	private Object[] datosFilasTabla;
	private String rolUsuario;
	private TreeSet<String> listadoGrupos;
	private String grupo;

	// Sentencia Select SQL LOGIN
	private String selectPasswdUsuario;

	// Sentencias Select SQL TABLAS
	private String selectHome ;
	private String selectTodosUsuarios ;
	private String selectTodosRegistros  ;
	private String selectTodosAlumnos ;
	private String selectTodasActividades ;
	private String selectTodasAsignaturas ;
	private String selectTodosProfesores ;
	private String selectTodosActores;
	private String selectTodasSalas;
	private String selectTodosAcad;
	

	private String selectTodosCodigoGrupo;

	// Sentencias Select SQL LISTADOS
	private String selectListadoAlumnosPorGrupo;

	// Sentencias Select SQL BUSCADOR
	private String selectBuscadorHome;
	private String selectBuscadorUsuarios;
	private String selectBuscadorRegistros ;
	private String selectBuscadorActividades;
	private String selectBuscadorAsignatura = "SELECT codigo , nombre , titulacion , curso FROM HOSPITAL.asignatura WHERE codigo=? or nombre=? or titulacion=? or curso=?";
	private String selectBuscadorAlumnos = "SELECT * FROM HOSPITAL.alumno WHERE exp=? or nombre=? or activo=?";
	private String selectBuscadorProfesores = "SELECT * from HOSPITAL.profesor WHERE numero=? or nombre=? or apellido1=? or apellido2=? or titulacion=? or dni=? or activo=? or relacion_laboral=? or tlf1=? or tlf2=? or mail1=? or mail2=?";
	private String selectBuscadorActores = "SELECT cod_actor, nombre, edad, genero, idioma, complexion, activo FROM HOSPITAL.actor WHERE cod_actor=? or nombre=? or edad=? or genero=? or idioma=? or complexion=? or activo=?";
	private String selectBuscadorSalas = "SELECT cod_sala , tipo_sala , numero , capacidad FROM HOSPITAL.sala WHERE cod_sala=? or tipo_sala=? or numero=? or capacidad=?";
	private String selectBuscadorAcad = "SELECT * FROM HOSPITAL.acad WHERE acad=? or sem1=? or sem2=?";

	public ModeloConsultas() {
		propiedades = new Properties();
		fichero = new File("./sql/consultas.ini");

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
		
		//Asignamos select de login usuario
		selectPasswdUsuario = propiedades.getProperty("selectPasswdUsuario");
		
		//Asignamos select de tablas
		selectHome = propiedades.getProperty("selectHome");
		selectTodosUsuarios = propiedades.getProperty("selectTodosUsuarios");
		selectTodosRegistros = propiedades.getProperty("selectTodosRegistros");
		selectTodosAlumnos = propiedades.getProperty("selectTodosAlumnos");
		selectTodasActividades = propiedades.getProperty("selectTodasActividades");
		selectTodasAsignaturas = propiedades.getProperty("selectTodasAsignaturas");
		selectTodosProfesores = propiedades.getProperty("selectTodosProfesores");
		selectTodosActores = propiedades.getProperty("selectTodosProfesores");
		selectTodasSalas = propiedades.getProperty("SelecTodasSalas");
		selectTodosAcad =  propiedades.getProperty("selecTodosAcad");
		//Asignamos select de listado
		selectTodosCodigoGrupo = propiedades.getProperty("selectTodosCodigoGrupo");
		selectListadoAlumnosPorGrupo = propiedades.getProperty("selectListadoAlumnosPorGrupo");
		//Asignamos las Select SQL BUSCADOR
		selectBuscadorHome = propiedades.getProperty("selectBuscadorHome");
		selectBuscadorUsuarios = propiedades.getProperty("selectBuscadorUsuarios");
		selectBuscadorRegistros = propiedades.getProperty("selectBuscadorRegistros");
		selectBuscadorActividades = propiedades.getProperty("selectBuscadorActividades");
		
		
		
	}

	// INICIO SETTERS

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
	
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	// INICIO GETTERS

	public String getRespuesta() {
		return respuesta;
	}

	public String getGrupo() {
		return grupo;
	}

	// INICIO METODOS BASE DATOS

	public void loginConfirmacion(String usuario, String passwd) {
		conexion = modelo.getConexion();
		String sql = selectPasswdUsuario;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setString(1, usuario);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next() && rs.getString(1).equals(passwd)) {
				// CAmbiar solo a ADMIN
				if (rs.getString(2).equalsIgnoreCase("Admin") || rs.getString(2).equalsIgnoreCase("Administrador")) {
					login.loginExitoso();
				} else {
					login.loginExitosoLectura();
				}

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

	/*
	 * 
	 * public void crearUsuario(String user, String passwd, String rol) { String sql
	 * = selectPasswdUsuario; try { PreparedStatement pstmt =
	 * conexion.prepareStatement(sql); pstmt.setString(1, user); ResultSet rs =
	 * pstmt.executeQuery(); if (!rs.next()) { sql = insertUsuario; try { pstmt =
	 * conexion.prepareStatement(sql); pstmt.setString(1, user); pstmt.setString(2,
	 * passwd); pstmt.setString(3, rol); rs = pstmt.executeQuery(); respuesta =
	 * "Usuario creado"; } catch (Exception e) { respuesta =
	 * "Error, algun campo vacio"; crearUsuario.actualizarInfo();
	 * e.printStackTrace(); } } else { respuesta = "El usuario ya existe"; }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * crearUsuario.actualizarInfo(); }
	 */

	public void getTablaHome(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectHome;
		getDatos(sql);
	}

	public void getTablaUsuarios(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosUsuarios;
		getDatos(sql);
	}

	public void getTablaAcad(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosAcad;
		getDatos(sql);
	}

	public void getTablaActividad(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodasActividades;
		getDatos(sql);
	}

	public void getTablaActores(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosActores;
		getDatos(sql);
	}

	public void getTablaAlumnos(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosAlumnos;
		getDatos(sql);
	}

	public void getTablaAsignatura(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodasAsignaturas;
		getDatos(sql);
	}

	public void getTablaProfesores(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosProfesores;
		getDatos(sql);
	}

	public void getTablaRegistros(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosRegistros;
		getDatos(sql);
	}

	public void getTablaSalas(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodasSalas;
		getDatos(sql);
	}

	public void listadoGrupos() {
		String sql = selectTodosCodigoGrupo;
		listadoGrupos = new TreeSet<String>();
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				listadoGrupos.add(rs.getString(1));
			}

			for (String elemento : listadoGrupos) {
				grupo = elemento;
				verGrupos.addGrupo();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getListadoAlumnosPorGrupo(DefaultTableModel tableModel, String grupo) {
		this.tableModel = tableModel;
		String sql = selectListadoAlumnosPorGrupo;

		if (!grupo.equalsIgnoreCase("Selecciona un grupo")) {
			tableModel.setColumnCount(0);
			tableModel.setRowCount(0);
			try {
				PreparedStatement pstmt = conexion.prepareStatement(sql);
				pstmt.setString(1, grupo);
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
						datosFilasTabla[i] = rs.getObject(i + 1);
					}
					tableModel.addRow(datosFilasTabla);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
					datosFilasTabla[i] = rs.getObject(i + 1);
				}
				tableModel.addRow(datosFilasTabla);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
