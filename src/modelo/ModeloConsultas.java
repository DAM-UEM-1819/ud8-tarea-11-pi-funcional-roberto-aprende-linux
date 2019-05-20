package modelo;

import java.awt.Cursor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TreeSet;

import javax.naming.spi.DirStateFactory.Result;
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
	// private Ocupaciones ocupaciones;
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
	private ModeloGestionDatos modeloGestionDatos;

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
	private String nombreUsuario;
	private String rolUsuario;
	private TreeSet<String> listadoGrupos;
	private String grupo;
	private String numeroAlumos;
	private String simulador;
	private boolean actor;
	private boolean existe;
	private Object[] datosUsuario;

	// Sentencia Select SQL LOGIN
	private String selectPasswdUsuario;

	// Sentencias Select SQL TABLAS
	private String selectHome;
	private String selectDatosExtraHome;
	private String selectTodosUsuarios;
	private String selectTodosRegistros;
	private String selectTodosAlumnos;
	private String selectTodasActividades;
	private String selectTodasAsignaturas;
	private String selectTodosProfesores;
	private String selectTodosActores;
	private String selectTodasSalas;
	private String selectTodosAcad;

	private String selectTodosCodigoGrupo;

	// Sentencias Select SQL LISTADOS
	private String selectListadoAlumnosPorGrupo;

	// Sentencias Select SQL BUSCADOR
	private String selectBuscadorHome;
	private String selectBuscadorUsuarios;
	private String selectBuscadorRegistros;
	private String selectBuscadorActividades;
	private String selectBuscadorAsignatura;
	private String selectBuscadorAlumnos;
	private String selectBuscadorProfesores;
	private String selectBuscadorActores;
	private String selectBuscadorSalas;
	private String selectBuscadorAcad;

	// SENTENCIAS SELECT SQL DATOS EXTRA
	private String selectDatosUsuarioPerfil;

	// SENTENCIAS SELECT SQL COMPROBACION
	private String selectExisteSala;

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

		// Asignamos select de login usuario
		// Asignamos select de tablas
		selectTablas();
		// Asignamos select de listado
		// Asignamos las Select SQL BUSCADOR
		selectBuscador();

		selectComprobacionExiste();
		selectDatosExtra();
	}

	private void selectTablas() {
		// select login
		selectPasswdUsuario = propiedades.getProperty("selectPasswdUsuario");
		//
		selectHome = propiedades.getProperty("selectHome");
		selectDatosExtraHome = propiedades.getProperty("selectDatosExtraHome");
		selectTodosUsuarios = propiedades.getProperty("selectTodosUsuarios");
		selectTodosRegistros = propiedades.getProperty("selectTodosRegistros");
		selectTodosAlumnos = propiedades.getProperty("selectTodosAlumnos");
		selectTodasActividades = propiedades.getProperty("selectTodasActividades");
		selectTodasAsignaturas = propiedades.getProperty("selectTodasAsignaturas");
		selectTodosProfesores = propiedades.getProperty("selectTodosProfesores");
		selectTodosActores = propiedades.getProperty("selectTodosActores");
		selectTodasSalas = propiedades.getProperty("selectTodasSalas");
		selectTodosAcad = propiedades.getProperty("selectTodosAcad");
	}

	private void selectBuscador() {
		// select de listado de grupo
		selectTodosCodigoGrupo = propiedades.getProperty("selectTodosCodigoGrupo");
		selectListadoAlumnosPorGrupo = propiedades.getProperty("selectListadoAlumnosPorGrupo");

		//
		selectBuscadorHome = propiedades.getProperty("selectBuscadorHome");
		selectBuscadorUsuarios = propiedades.getProperty("selectBuscadorUsuarios");
		selectBuscadorRegistros = propiedades.getProperty("selectBuscadorRegistros");
		selectBuscadorActividades = propiedades.getProperty("selectBuscadorActividades");
		selectBuscadorAsignatura = propiedades.getProperty("selectBuscadorAsignatura");
		selectBuscadorAlumnos = propiedades.getProperty("selectBuscadorAlumnos");
		selectBuscadorProfesores = propiedades.getProperty("selectBuscadorProfesores");
		selectBuscadorActores = propiedades.getProperty("selectBuscadorActores");
		selectBuscadorSalas = propiedades.getProperty("selectBuscadorSalas");
		selectBuscadorAcad = propiedades.getProperty("selectBuscadorAcad");
	}

	private void selectComprobacionExiste() {
		selectExisteSala = propiedades.getProperty("selectExisteSala");
	}

	private void selectDatosExtra() {
		selectDatosUsuarioPerfil = propiedades.getProperty("selectDatosUsuarioPerfil");
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

	public void setModeloGestionDatos(ModeloGestionDatos modeloGestionDatos) {
		this.modeloGestionDatos = modeloGestionDatos;
	}

	// INICIO GETTERS

	public String getRespuesta() {
		return respuesta;
	}

	public String getGrupo() {
		return grupo;
	}

	public String getNumeroAlumos() {
		return numeroAlumos;
	}

	public String getSimulador() {
		return simulador;
	}

	public boolean tieneActor() {
		return actor;
	}

	public boolean getExiste() {
		return existe;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public Object[] getDatosUsuario() {
		return datosUsuario;
	}

	// INICIO METODOS BASE DATOS

	public void loginConfirmacion(String usuario, String passwd) {
		//BORRAR, ESTO SIRVE PARA AHORRARNOS TRABAJO, NO DEJARLO EN LA VERSION FINAL
		//FALLO DE SEGURIDAD IMPORTANTE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if (usuario.equals("") || passwd.equals("")) {
			login.loginExitoso();
		}
		conexion = modelo.getConexion();
		String sql = selectPasswdUsuario;
		usuario = usuario.toUpperCase();
		passwd = modelo.generarMD5(passwd);
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setString(1,usuario);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next() && rs.getString(1).equals(passwd)) {
				// CAmbiar solo a ADMIN
				if (rs.getString(2).equalsIgnoreCase("Admin") || rs.getString(2).equalsIgnoreCase("Administrador")) {
					login.loginExitoso();
				} else {
					login.loginExitosoLectura();
				}
				nombreUsuario = usuario;
			} else {
				respuesta = "Ususario o contrase�a incorrectos";
				login.actualizarInfo();
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
	
	public String consultarPasswdUsuario(String usuario) {
		String passwd = "";
		
		try {
			PreparedStatement pstmt = conexion.prepareStatement(selectPasswdUsuario);
			pstmt.setString(1,usuario);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				passwd = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return passwd;
	}

	public void crearUsuario(String user, String passwd, String rol) {
		String sql = selectPasswdUsuario;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setString(1, user);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				respuesta = modeloGestionDatos.crearUsuario(user, passwd, rol);
			} else {
				respuesta = "El usuario ya existe";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		crearUsuario.actualizarInfo();
	}

	public void getTablaHome(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(selectHome);
			getDatos(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// TERMINAR

	public void getDatosExtraHome(Object[] datos) {
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(selectDatosExtraHome);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		home.actualizarInfoExtra();
	}

	public void getDatosUsuarioPerfil() {
		PreparedStatement pstmt;

		try {
			pstmt = conexion.prepareStatement(selectDatosUsuarioPerfil);
			pstmt.setString(1, nombreUsuario);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData metadatos = rs.getMetaData();
			datosUsuario = new Object[metadatos.getColumnCount()];
			if (rs.next()) {

				for (int i = 0; i < metadatos.getColumnCount(); i++) {
					datosUsuario[i] = rs.getString(i + 1);
				}

				perfil.mostrarDatosPerfil();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getTablaUsuarios(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(selectTodosUsuarios);
			getDatos(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getTablaAcad(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(selectTodosAcad);
			getDatos(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getTablaActividad(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(selectTodasActividades);
			getDatos(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getTablaActores(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(selectTodosActores);
			getDatos(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getTablaAlumnos(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(selectTodosAlumnos);
			getDatos(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getTablaAsignatura(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(selectTodasAsignaturas);
			getDatos(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getTablaProfesores(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(selectTodosProfesores);
			getDatos(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getTablaRegistros(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(selectTodosRegistros);
			getDatos(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getTablaSalas(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(selectTodasSalas);
			getDatos(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		PreparedStatement pstmt;
		if (!grupo.equalsIgnoreCase("Selecciona un grupo")) {
			try {
				pstmt = conexion.prepareStatement(selectListadoAlumnosPorGrupo);
				pstmt.setString(1, grupo);
				getDatos(pstmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void getDatos(PreparedStatement pstmt) {
		tableModel.setColumnCount(0);
		tableModel.setRowCount(0);
		try {

			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData metadatos = rs.getMetaData();
			int numColumnas = metadatos.getColumnCount();
			datosFilasTabla = new Object[numColumnas];

			for (int i = 0; i < numColumnas; i++) {
				tableModel.addColumn(metadatos.getColumnName(i + 1));
			}

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

	public void comprobarSala(String sala) {
		existe = false;
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(selectExisteSala);
			pstmt.setString(1, sala);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				existe = true;
				respuesta = "Error, la sala ya existe";
				gestionSalas.actualizarInfoConsulta();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// BUSCADORES
	public void buscadorUsuarios(DefaultTableModel tableModel, String palabra) {
		this.tableModel = tableModel;
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(selectBuscadorUsuarios);
			pstmt.setString(1, "%" + palabra.toUpperCase() + "%");
			pstmt.setString(2, "%" + palabra.toUpperCase() + "%");
			getDatos(pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
