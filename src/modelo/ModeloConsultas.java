package modelo;

import java.awt.Cursor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TreeSet;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controlador.Controlador;
import vista.CrearUsuario;
import vista.Gestion;
import vista.GestionAcad;
import vista.GestionActividad;
import vista.GestionActores;
import vista.GestionAlumnos;
import vista.GestionAsignatura;
import vista.GestionProfesores;
import vista.GestionProfesoresAddMod;
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
	private GestionProfesoresAddMod gestionProfesoresAddMod;
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
	private String actor;
	private String documentacion;
	private boolean existe;
	private Object[] datosUsuario;
	private TableRowSorter filtro;

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

	// SENTENCIAS SELECT SQL PARA DATOS INTERNOS
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
	private String selectExisteProfesor;

	// SENTENCIAS SELECT SQL INFORMES
	private String informeNumeroHorasTotalesPorActividad;
	private String informeNumeroHorasTotalesActividadPorTitulacion;
	private String informeNumeroHorasTotalesActividadPorTitulacionYCurso;
	private String informeNumeroHorasTotalesActividadPorTitulacionYAsignatura;
	private String informeNumeroHorasTotalesActividadPorProfesor;
	private String informeNumeroHorasTotalesActividadPorSala;
	private String informeNumeroHorasTotalesActividadPorActividad;
	private String informeNumeroHorasTotalesActividadPorSemestre;
	private String informeNumeroHorasTotalesActividadPorMes;
	private String informeNumeroHorasActorTotalesCursoAcademico;
	private String informeNumeroHorasActorTotalesTitulacionYMes;
	private String informeNumeroHorasActorTotalesTitulacionCursoAcademico;
	private String informeListadoAlumnosAsignaturaYGrupoActivos;
	private String informeListadoAlumnosNotasPorNombreActividad;
	private String informeListadoProfesoresPorTitulacionActivos;
	private String infoExtraProfesores;
	private String infoExtraAlumnos;
	

	// SENTENCIAS SELECT SQL INFORMACION EXTRA

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

		// ASIGNACIÓN DE LAS CONSULTAS
		selectTablas();
		selectBuscador();
		selectComprobacionExiste();
		selectDatosExtra();
		selectInformes();
	}

	private void selectTablas() {
		// select login
		selectPasswdUsuario = propiedades.getProperty("selectPasswdUsuario");

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

	private void selectInformes() {
		// NÚMERO DE HORAS DE ACTIVIDAD
		informeNumeroHorasTotalesPorActividad = propiedades.getProperty("informeNumeroHorasTotalesPorActividad");
		informeNumeroHorasTotalesActividadPorTitulacion = propiedades
				.getProperty("informeNumeroHorasTotalesActividadPorTitulacion");
		informeNumeroHorasTotalesActividadPorTitulacionYCurso = propiedades
				.getProperty("informeNumeroHorasTotalesActividadPorTitulacionYCurso");
		informeNumeroHorasTotalesActividadPorTitulacionYAsignatura = propiedades
				.getProperty("informeNumeroHorasTotalesActividadPorTitulacionYAsignatura");
		informeNumeroHorasTotalesActividadPorProfesor = propiedades
				.getProperty("informeNumeroHorasTotalesActividadPorProfesor");
		informeNumeroHorasTotalesActividadPorSala = propiedades
				.getProperty("informeNumeroHorasTotalesActividadPorSala");
		informeNumeroHorasTotalesActividadPorActividad = propiedades
				.getProperty("informeNumeroHorasTotalesActividadPorActividad");
		informeNumeroHorasTotalesActividadPorSemestre = propiedades
				.getProperty("informeNumeroHorasTotalesActividadPorSemestre");
		informeNumeroHorasTotalesActividadPorMes = propiedades.getProperty("informeNumeroHorasTotalesActividadPorMes");

		// NÚMERO DE HORAS DE ACTOR
		informeNumeroHorasActorTotalesCursoAcademico = propiedades
				.getProperty("informeNumeroHorasActorTotalesCursoAcademico");
		informeNumeroHorasActorTotalesTitulacionYMes = propiedades
				.getProperty("informeNumeroHorasActorTotalesTitulacionYMes");
		informeNumeroHorasActorTotalesTitulacionCursoAcademico = propiedades
				.getProperty("informeNumeroHorasActorTotalesTitulacionCursoAcademico");

		// LISTADO DE ALUMNOS
		informeListadoAlumnosAsignaturaYGrupoActivos = propiedades
				.getProperty("informeListadoAlumnosAsignaturaYGrupoActivos");
		informeListadoAlumnosNotasPorNombreActividad = propiedades
				.getProperty("informeListadoAlumnosNotasPorNombreActividad");

		// LISTADO DE PROFESORES
		informeListadoProfesoresPorTitulacionActivos = propiedades
				.getProperty("informeListadoProfesoresPorTitulacionActivos");
	}

	private void selectComprobacionExiste() {
		selectExisteSala = propiedades.getProperty("selectExisteSala");
		selectExisteProfesor = propiedades.getProperty("selectExisteProfesor");
	}

	private void selectDatosExtra() {
		selectDatosUsuarioPerfil = propiedades.getProperty("selectDatosUsuarioPerfil");
		infoExtraProfesores = propiedades.getProperty("infoExtraProfesores");
		infoExtraAlumnos = propiedades.getProperty("infoExtraAlumnos");
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

	public void setGestionProfesoresAddMod(GestionProfesoresAddMod gestionProfesoresAddMod) {
		this.gestionProfesoresAddMod = gestionProfesoresAddMod;
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
		boolean valor = false;
		if (actor.equals("1")) {
			valor = true;
		}
		return valor;
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

	public String getDocumentacion() {
		return documentacion;
	}
	
	public TableRowSorter getFiltro() {
		return filtro;
	}

	// INICIO METODOS BASE DATOS

	/**
	 * Metodo que sirve para proceder a la confirmación del login, compara los datos
	 * dados con los datos de la BBDD. Para comparar la contraseña genera un MD5 de
	 * la misma y compara el hash con el que haya en la BBDD para el usuario
	 * correspondiente
	 *
	 * @param usuario El usuario escrito en el textfield del login
	 * @param passwd  La contraseña del usuario
	 */
	public void loginConfirmacion(String usuario, String passwd) {
		// BORRAR, ESTO SIRVE PARA AHORRARNOS TRABAJO, NO DEJARLO EN LA VERSION FINAL
		// FALLO DE SEGURIDAD IMPORTANTE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if (usuario.equals("") || passwd.equals("")) {
			login.loginExitoso();
		}
		conexion = modelo.getConexion();
		String sql = selectPasswdUsuario;
		usuario = usuario.toUpperCase();
		usuario = usuario.trim();
		passwd = modelo.generarMD5(passwd);
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
				nombreUsuario = usuario;
			} else {
				respuesta = "Ususario o contraseña incorrectos";
				login.actualizarInfo();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Este método devuelve el hash de la contraseña de un usuario en específico
	 *
	 * @param usuario El usuario a consultar
	 * @param passwd  La contraseña del usuario
	 * @return El hash de la contraseña ubicada en la BBDD
	 */
	public String consultarPasswdUsuario(String usuario, String passwd) {

		try {
			PreparedStatement pstmt = conexion.prepareStatement(selectPasswdUsuario);
			pstmt.setString(1, usuario);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				passwd = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return passwd;
	}

	/**
	 * Este método consulta si el usuario que se quiere añadir ya existe o no en la
	 * base de datos. En caso de que no exista llamará al metodo correspondiente
	 * para crear el usuario
	 *
	 * @param user   El nombre del usuario
	 * @param passwd La contraseña del usuario
	 * @param rol    El rol del usuario
	 */
	public void crearUsuario(String user, String rol, String email) {
		String sql = selectPasswdUsuario;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setString(1, user);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				respuesta = modeloGestionDatos.crearUsuario(user, rol, email);
			} else {
				respuesta = "El usuario ya existe";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		crearUsuario.actualizarInfo();
	}

	/**
	 * Metodo que sirve para preparar la consulta que se va a realizar para la
	 * ventana home
	 *
	 * @param tableModel La tabla de la vista Home
	 */
	public void getTablaHome(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(selectHome);
			pstmt.setString(1, home.getFecha());
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
			// Aqui irian los setString
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				numeroAlumos = rs.getString(1);
				simulador = rs.getString(2);
				actor = rs.getString(3);
				documentacion = rs.getString(4);
			}
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

	/**
	 * Metodo que sirve para preparar la consulta que se va a realizar para la
	 * ventana de gestion de usuarios
	 *
	 * @param tableModel La tabla de la vista de usuarios
	 */
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

	/**
	 * Metodo que sirve para preparar la consulta que se va a realizar para la
	 * ventana de año academico
	 *
	 * @param tableModel La tabla de la vista de acad
	 */
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

	/**
	 * Metodo que sirve para preparar la consulta que se va a realizar para la
	 * ventana de gestion de actividades
	 *
	 * @param tableModel La tabla de la vista de actividad
	 */
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

	/**
	 * Metodo que sirve para preparar la consulta que se va a realizar para la
	 * ventana de gestion de actores
	 *
	 * @param tableModel La tabla de la vista de gestion de actores
	 */
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

	/**
	 * Metodo que sirve para preparar la consulta que se va a realizar para la
	 * ventana de gestion de alumnos
	 *
	 * @param tableModel La tabla de la vista de gestion de alumnos
	 */
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

	/**
	 * Metodo que sirve para preparar la consulta que se va a realizar para la
	 * ventana de gestion de asignaturas
	 *
	 * @param tableModel La tabla de la vista de gestion de asignaturas
	 */
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

	/**
	 * Metodo que sirve para preparar la consulta que se va a realizar para la
	 * ventana de gestion de profesores
	 *
	 * @param tableModel La tabla de la vista de fgestion de profesores
	 */
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

	/**
	 * Metodo que sirve para preparar la consulta que se va a realizar para la
	 * ventana de gestion de registros
	 *
	 * @param tableModel La tabla de la vista de gestion de registros
	 */
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

	/**
	 * Metodo que sirve para preparar la consulta que se va a realizar para la
	 * ventana de gestion de salas
	 *
	 * @param tableModel La tabla de la vista de gestion de salas
	 */
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

	/**
	 * Metodo que sirve para sacar los grupos de alumnos
	 */
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

	/**
	 * Metodo que sirve para
	 *
	 * @param pstmt La consulta preparada y lista para ejecutarse
	 */
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

	/**
	 * Metodo que sirve para saber si uan sala ya existe o no
	 *
	 * @param sala La sala a comprobar
	 */
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

	public void comprobarInsertODelete(String profe) {
		PreparedStatement pstmt;
		try {
			pstmt = conexion.prepareStatement(selectExisteProfesor);
			pstmt.setString(1, profe);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				controlador.solicitudProfeMod();
				respuesta = "Profesor modificado";
				gestionProfesoresAddMod.actualizarInfoConsulta();

			} else {
				controlador.solicitudProfeAdd();
				respuesta = "Profesor creado";
				gestionProfesoresAddMod.actualizarInfoConsulta();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// BUSCADORES

	/**
	 * Metodo general que sirve para realizar todas las consultas de búsqueda
	 *
	 * @param tableModel La tabla donde se va a mostrar el resultado
	 * @param palabra    La palabra a buscar
	 * @param opcion     La opcion que se desea, depende del tipo de la clase donde
	 *                   te encuetres
	 */
	public void buscador(DefaultTableModel tableModel, String palabra, String opcion) {
		this.tableModel = tableModel;
		PreparedStatement pstmt = null;
		palabra = palabra.toUpperCase();

		try {
			switch (opcion) {
			case "A":
				pstmt = conexion.prepareStatement(selectBuscadorAlumnos);
				pstmt.setString(1, palabra + "%");
				pstmt.setString(2, palabra + "%");
				pstmt.setString(3, palabra + "%");
				break;
			case "B":
				pstmt = conexion.prepareStatement(selectBuscadorUsuarios);
				pstmt.setString(1, palabra + "%");
				pstmt.setString(2, palabra + "%");
				break;
			case "C":
				pstmt = conexion.prepareStatement(selectBuscadorActividades);
				pstmt.setString(1, palabra + "%");
				pstmt.setString(2, palabra + "%");
				pstmt.setString(3, palabra + "%");
				pstmt.setString(4, palabra + "%");
				pstmt.setString(5, palabra + "%");
				pstmt.setString(6, palabra + "%");
				pstmt.setString(7, palabra + "%");
				break;
			case "D":
				pstmt = conexion.prepareStatement(selectBuscadorAsignatura);
				pstmt.setString(1, palabra + "%");
				pstmt.setString(2, palabra + "%");
				pstmt.setString(3, palabra + "%");
				pstmt.setString(4, palabra + "%");
				break;
			case "E":
				pstmt = conexion.prepareStatement(selectBuscadorSalas);
				pstmt.setString(1, palabra + "%");
				pstmt.setString(2, palabra + "%");
				pstmt.setString(3, palabra + "%");
				pstmt.setString(4, palabra + "%");
				break;
			case "F":
				pstmt = conexion.prepareStatement(selectBuscadorProfesores);
				pstmt.setString(1, palabra + "%");
				pstmt.setString(2, palabra + "%");
				pstmt.setString(3, palabra + "%");
				pstmt.setString(4, palabra + "%");
				pstmt.setString(5, palabra + "%");
				pstmt.setString(6, palabra + "%");
				pstmt.setString(7, palabra + "%");
				pstmt.setString(8, palabra + "%");
				pstmt.setString(9, palabra + "%");
				pstmt.setString(10, palabra + "%");
				pstmt.setString(11, palabra + "%");
				pstmt.setString(12, palabra + "%");
				break;
			case "G":
				pstmt = conexion.prepareStatement(selectBuscadorAcad);
				pstmt.setString(1, palabra + "%");
				pstmt.setString(2, palabra + "%");
				pstmt.setString(3, palabra + "%");
				break;
			case "H":
				pstmt = conexion.prepareStatement(selectBuscadorActores);
				pstmt.setString(1, palabra + "%");
				pstmt.setString(2, palabra + "%");
				pstmt.setString(3, palabra + "%");
				pstmt.setString(4, palabra + "%");
				pstmt.setString(5, palabra + "%");
				pstmt.setString(6, palabra + "%");
				pstmt.setString(7, palabra + "%");
				break;
			case "I":
				pstmt = conexion.prepareStatement(selectBuscadorRegistros);
				pstmt.setString(1, palabra + "%");
				pstmt.setString(2, palabra + "%");
				pstmt.setString(3, palabra + "%");
				pstmt.setString(4, palabra + "%");
				pstmt.setString(5, palabra + "%");
				break;
			case "J":
				pstmt = conexion.prepareStatement(selectListadoAlumnosPorGrupo); // Cambiar
				pstmt.setString(1, palabra + "%");
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		getDatos(pstmt);

	}
	
	public void buscadorHome(DefaultTableModel tableModel, String palabraBuscador) {
		this.tableModel = tableModel;
		TableRowSorter trs = new TableRowSorter(tableModel);
		trs.setRowFilter(RowFilter.regexFilter("(?i)" + palabraBuscador, 0));
		this.filtro = trs;
		home.filtro();
	}

	/**
	 * Metodo que sirve para crear los informes de la ventana informes
	 *
	 * @param informe    El informe a mostrar
	 * @param tableModel La tabla de la vista
	 */
	public void crearInforme(DefaultTableModel tableModel, String informe) {
		this.tableModel = tableModel;
		PreparedStatement pstmt = null;
		informe = informe.toUpperCase();

		try {
			switch (informe) {
			case "HORAS_TOTALES_ACTIVIDAD":
				pstmt = conexion.prepareStatement(informeNumeroHorasTotalesPorActividad);
				break;
			case "HORAS_ACTIVIDAD_TITULACION":
				pstmt = conexion.prepareStatement(informeNumeroHorasTotalesActividadPorTitulacion);
				break;
			case "HORAS_ACTIVIDAD_TITULACION_Y_CURSO":
				pstmt = conexion.prepareStatement(informeNumeroHorasTotalesActividadPorTitulacionYCurso);
				break;
			case "HORAS_ACTIVIDAD_TITULACION_Y_ASIGNATURA":
				pstmt = conexion.prepareStatement(informeNumeroHorasTotalesActividadPorTitulacionYAsignatura);
				break;
			case "HORAS_ACTIVIDAD_PROFESOR":
				pstmt = conexion.prepareStatement(informeNumeroHorasTotalesActividadPorProfesor);
				break;
			case "HORAS_ACTIVIDAD_SALA":
				pstmt = conexion.prepareStatement(informeNumeroHorasTotalesActividadPorSala);
				break;
			case "HORAS_ACTIVIDAD_TIPO_ACTIVIDAD":
				pstmt = conexion.prepareStatement(informeNumeroHorasTotalesActividadPorActividad);
				break;
			case "HORAS_ACTIVIDAD_MESES":
				pstmt = conexion.prepareStatement(informeNumeroHorasTotalesActividadPorMes);
				break;
			case "HORAS_ACTIVIDAD_SEMESTRES":
				pstmt = conexion.prepareStatement(informeNumeroHorasTotalesActividadPorSemestre);
				break;
			case "HORAS_ACTOR_TOTALES_EN_ACAD":
				pstmt = conexion.prepareStatement(informeNumeroHorasActorTotalesCursoAcademico);
				break;
			case "HORAS_ACTOR_TITULACION_Y_MES":
				pstmt = conexion.prepareStatement(informeNumeroHorasActorTotalesTitulacionYMes);
				break;
			case "HORAS_ACTOR_TITULACION_Y_ACAD":
				pstmt = conexion.prepareStatement(informeNumeroHorasActorTotalesTitulacionCursoAcademico);
				break;
			case "LISTADO_ALUMNOS_SEGUN_ASIGNATURA_Y_GRUPO_ACTIVOS":
				pstmt = conexion.prepareStatement(informeListadoAlumnosAsignaturaYGrupoActivos);
				break;
			case "LISTADO_ALUMNOS_SEGUN_NOMBRE_ACTIVIDAD":
				pstmt = conexion.prepareStatement(informeListadoAlumnosNotasPorNombreActividad);
				break;
			case "LISTADO_PROFESORES_SEGUN_TITULACION":
				pstmt = conexion.prepareStatement(informeListadoProfesoresPorTitulacionActivos);
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		getDatos(pstmt);

	}

	/**
	 * Metodo que sirve para guardar los datos de la fila selecionada ne la ventana
	 * home
	 * 
	 * @param datosFilaTabla El array de datos que contiene toda la informacion de
	 *                       la fila seleccionada
	 */
	public void guardarDatosFilaHome(Object[] datosFilaTabla) {
		this.datosFilasTabla = datosFilaTabla;
	}

	/**
	 * Metodo para mostrar el listado de alumnos y profesores del registro
	 * seleccionado en la ventana de home
	 * 
	 * @param modelProfesores La tabla de profesores
	 * @param modelAlumnos    La tabla de alumnos
	 */
	public void datosInfoExtra(DefaultTableModel modelProfesores, DefaultTableModel modelAlumnos) {
		this.tableModel = tableModel;
		PreparedStatement pstmt = null;
		try {
			pstmt = conexion.prepareStatement(infoExtraProfesores);
			getDatos(pstmt);

			pstmt = conexion.prepareStatement(infoExtraAlumnos);
			getDatos(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


}
