package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

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

public class ModeloGestionDatos {

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
	private ModeloConsultas modeloConsultas;

	private ArrayList<Object> datosFilastabla;

	// Atributos Fichero
	private Properties propiedadesInsertado;
	private Properties propiedadesBorrado;
	private Properties propiedadesModificacion;
	private InputStream entrada;
	private OutputStream salida;
	private File fichero;

	// Atributos internos
	private Connection conexion;
	private String respuesta;
	private boolean seHaBorrado;
	private boolean seHaCreado;
	private String clave;

	// Sentencias Insertado SQL
	private String insertUsuario;
	private String insertAlumno;
	private String insertSala;
	private String insertRegistro;
	private String insertProfesor;
	private String insertActor;
	private String insertAcad;
	private String insertAsignatura;
	private String insertRealiza;
	private String insertActua;
	private String insertParticipa;
	private String insertMatricula;
	private String insertOcupa;

	// Sentencias Delete SQL
	private String deleteAlumno;
	private String deleteUsuario;
	private String deleteSala;
	private String deleteOcupa;
	private String deleteActuaRegistro;
	private String deleteParticipaRegistro;
	private String deleteRealizaRegistro;
	private String deleteOcupaRegistro;
	private String deleteRegistro;

	// Sentincias Update SQL
	private String updateAlumno;
	private String updateSala;
	private String updateRegistro;

	/**
	 * Constructor que recoge los datos de las sentencias de insertado, borrado y
	 * modificado. Asigna las sentencias a los atributos
	 */
	public ModeloGestionDatos() {

		propiedadesInsertado = new Properties();
		propiedadesBorrado = new Properties();
		propiedadesModificacion = new Properties();

		try {
			fichero = new File("./sql/insertado.ini");
			entrada = new FileInputStream(fichero);
			propiedadesInsertado.load(entrada);

			fichero = new File("./sql/borrado.ini");
			entrada = new FileInputStream(fichero);
			propiedadesBorrado.load(entrada);

			fichero = new File("./sql/modificacion.ini");
			entrada = new FileInputStream(fichero);
			propiedadesModificacion.load(entrada);

		} catch (Exception e) {
			e.printStackTrace();
		}
		datosFilastabla = new ArrayList<Object>();
		asignacionInsertado();
		asignacionBorrado();
		asignacionModificacion();

	}

	// INICIO SETTERS
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas = modeloConsultas;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
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

	// INICIO GETTERS
	public boolean getSeHaBorrado() {
		return seHaBorrado;
	}

	public boolean getSeHaCreado() {
		return seHaCreado;
	}

	public Object[] getDatosfilasTabla() {
		return datosFilastabla.toArray();

	}

	public String getRespuesta() {
		return respuesta;
	}

	/**
	 * Metodo para asignar las sentencias de insertado a los atributos
	 */
	private void asignacionInsertado() {
		insertUsuario = propiedadesInsertado.getProperty("insertUsuario");
		insertAlumno = propiedadesInsertado.getProperty("insertAlumno");
		insertSala = propiedadesInsertado.getProperty("insertSala");
		insertRegistro = propiedadesInsertado.getProperty("insertRegistro");
		insertProfesor = propiedadesInsertado.getProperty("insertProfesor");
		insertActor = propiedadesInsertado.getProperty("insertActor");
		insertAcad = propiedadesInsertado.getProperty("insertAcad");
		insertAsignatura = propiedadesInsertado.getProperty("insertAsignatura");
		insertRealiza = propiedadesInsertado.getProperty("insertRealiza");
		insertActua = propiedadesInsertado.getProperty("insertActua");
		insertParticipa = propiedadesInsertado.getProperty("insertParticipa");
		insertMatricula = propiedadesInsertado.getProperty("insertMatricula");
		insertOcupa = propiedadesInsertado.getProperty("insertOcupa");
	}

	/**
	 * Metodo para asignar las sentencias de borrado a los atributos
	 */
	private void asignacionBorrado() {
		deleteAlumno = propiedadesBorrado.getProperty("deleteAlumno");
		deleteUsuario = propiedadesBorrado.getProperty("deleteUsuario");
		deleteSala = propiedadesBorrado.getProperty("deleteSala");
		deleteOcupa = propiedadesBorrado.getProperty("deleteOcupa");
		//
		deleteActuaRegistro = propiedadesBorrado.getProperty("deleteActuaRegistro");
		deleteParticipaRegistro = propiedadesBorrado.getProperty("deleteParticipaRegistro");
		deleteRealizaRegistro = propiedadesBorrado.getProperty("deleteRealizaRegistro");
		deleteOcupaRegistro = propiedadesBorrado.getProperty("deleteOcupaRegistro");
		deleteRegistro = propiedadesBorrado.getProperty("deleteRegistro");
	}

	/**
	 * Metodo para asignar las sentencias de modificacion a los atributos
	 */
	private void asignacionModificacion() {
		updateAlumno = propiedadesModificacion.getProperty("updateAlumno");
		updateSala = propiedadesModificacion.getProperty("updateSala");
		updateRegistro = propiedadesModificacion.getProperty("updateRegistro");

	}

	/**
	 * Metodo para crear un usuario en la BBDD
	 *
	 * @param user   El usuario a crear
	 * @param passwd La contraseña del usuario
	 * @param rol    El rol del usuario
	 * @return Un String con el estado del metodo (si se ha creado o no)
	 */
	public String crearUsuario(String user, String rol, String correo) {
		String sql = insertUsuario;
		String passwd = modelo.generadorPasswd();
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt = conexion.prepareStatement(sql);
			pstmt.setString(1, user.toUpperCase());
			pstmt.setString(2, passwd);
			pstmt.setString(3, rol.toUpperCase());
			pstmt.setString(4, correo.toUpperCase());
			ResultSet rs = pstmt.executeQuery();
			modelo.enviarCorreoGmail(correo, user, passwd);
			respuesta = "Usuario creado";
		} catch (Exception e) {
			respuesta = "Error, algun campo vacio";
			crearUsuario.actualizarInfo();
			e.printStackTrace();
		}

		return respuesta;

	}

	/**
	 * Metodo para crear un alumno en la BBDD
	 *
	 * @param exp    El expediente del alumno
	 * @param nombre El nombre del alumno
	 */
	public void crearAlumno(String exp, String nombre) {
		if (!exp.isEmpty() && !nombre.isEmpty()) {
			try {
				PreparedStatement pstmt = conexion.prepareStatement(insertAlumno);
				pstmt.setString(1, exp);
				pstmt.setString(2, nombre);
				addDatos(pstmt);

				datosFilastabla.removeAll(datosFilastabla);
				datosFilastabla.add(exp);
				datosFilastabla.add(nombre);
				datosFilastabla.add(1);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			respuesta = "Error, expediente o nombre vacio";
		}
		gestionAlumnos.actualizarInfo();
	}

	/**
	 * Metodo para crear una sala en la BBDD
	 *
	 * @param cod       Codigo de la sala
	 * @param tipo      Tipo de sala
	 * @param numero    Numero de la sala
	 * @param capacidad Capacidad de la sala
	 */
	public void crearSala(String cod, String tipo, String numero, String capacidad) {
		if (!cod.isEmpty() && !tipo.isEmpty() && !numero.isEmpty() && !capacidad.isEmpty()) {
			try {
				PreparedStatement pstmt = conexion.prepareStatement(insertSala);
				pstmt.setString(1, cod);
				pstmt.setString(2, tipo);
				pstmt.setString(3, numero);
				pstmt.setString(4, capacidad);
				addDatos(pstmt);
				datosFilastabla.removeAll(datosFilastabla);
				datosFilastabla.add(cod);
				datosFilastabla.add(tipo);
				datosFilastabla.add(numero);
				datosFilastabla.add(capacidad);
				seHaCreado = true;

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			seHaCreado = false;
			respuesta = "Error, sala ya creada";
			gestionSalas.actualizarInfoDatos();
		}

	}

	/**
	 * Metodo para ejecutar las sentencias
	 *
	 * @param pstmt La sentencia con los interrogantes puestos
	 */
	private void addDatos(PreparedStatement pstmt) {
		try {
			ResultSet rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para borrar datos de una tabla en funcion de la tabla a la que se haga
	 * referencia
	 *
	 * @param clave  La clave de la fila a borrar
	 * @param opcion El tipo de tabla al que se hace referencia
	 * @return booleano indicando si la sentencia se ha realizado con exito
	 */
	public boolean opcionesBorrarDatos(String clave, String opcion) {
		this.clave = clave;
		seHaBorrado = false;
		String sql = "";
		switch (opcion) {
		case "A":
			seHaBorrado = borrarDatos(deleteAlumno);
			break;
		case "B":
			sql = deleteUsuario;
			break;
		case "C":
			// sql = deleteActividad;
			break;
		case "D":
			// sql = deleteAsignatura;
			break;
		case "E":
			seHaBorrado = borrarDatos(deleteOcupa);
			seHaBorrado = borrarDatos(deleteSala);
			break;
		case "F":
			// sql = deleteRegistros;
			seHaBorrado = borrarDatos(deleteOcupaRegistro);
			seHaBorrado = borrarDatos(deleteActuaRegistro);
			seHaBorrado = borrarDatos(deleteParticipaRegistro);
			seHaBorrado = borrarDatos(deleteRealizaRegistro);
			seHaBorrado = borrarDatos(deleteRegistro);
			break;
		}

		return seHaBorrado;

	}

	/**
	 * Metodo para borrar los datos
	 *
	 * @param sql La sentencia de borrado
	 * @return booleano indicando si la sentencia se ha realizado con exito
	 */
	private boolean borrarDatos(String sql) {
		seHaBorrado = false;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setString(1, clave);
			ResultSet rs = pstmt.executeQuery();
			datosFilastabla.removeAll(datosFilastabla);
			seHaBorrado = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seHaBorrado;
	}

	/**
	 * Metodo para modificar los datos de un alumno
	 *
	 * @param exp    El expediente del alumno
	 * @param nombre El nombre del alumno
	 * @param activo El estado del alumno
	 */
	public void modificarAlumno(String exp, String nombre, int activo) {
		String sql = updateAlumno;
		datosFilastabla.removeAll(datosFilastabla);
		if (!exp.isEmpty() || !nombre.isEmpty()) {
			try {
				PreparedStatement pstmt = conexion.prepareStatement(sql);
				pstmt.setString(1, nombre);
				pstmt.setInt(2, activo);
				pstmt.setString(3, exp);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			respuesta = "Error, expediente o nombre vacio";
		}
		gestionAlumnos.actualizarInfo();
	}

	/**
	 * Metodo para modificar los datos de una sala
	 *
	 * @param cod       Codigo de la sala
	 * @param tipo      El tipo de sala
	 * @param numero    El numero de la sala
	 * @param capacidad La capacidad de la sala
	 */
	public void modificarSala(String cod, String tipo, String numero, String capacidad) {
		if (!cod.isEmpty() && !tipo.isEmpty() && !numero.isEmpty() && !capacidad.isEmpty()) {
			try {
				PreparedStatement pstmt = conexion.prepareStatement(updateSala);
				pstmt.setString(1, tipo);
				pstmt.setString(2, capacidad);
				pstmt.setString(3, numero);
				pstmt.setString(4, cod);
				addDatos(pstmt);

				seHaCreado = true;

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			seHaCreado = false;
			respuesta = "Error, estas modificando el codigo de sala";
			gestionSalas.actualizarInfoDatos();
		}
	}

	public void modificarRegistro(String cod_registro, String fecha, String hora, String horasProfesor,
			String actividadNombre) {
		// TODO Auto-generated method stub
		if (!cod_registro.isEmpty() && !fecha.isEmpty() && !hora.isEmpty() && !horasProfesor.isEmpty() && !actividadNombre.isEmpty()) {
			try {
				PreparedStatement pstmt = conexion.prepareStatement(updateRegistro);
				pstmt.setString(1, fecha);
				pstmt.setString(2, hora);
				pstmt.setString(3, horasProfesor);
				pstmt.setString(4, actividadNombre);
				pstmt.setInt(5, Integer.parseInt(cod_registro));
				addDatos(pstmt);

				seHaCreado = true;

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			seHaCreado = false;
			respuesta = "Error, estas modificando el codigo de registro";
//			gestionRegistros.actualizarInfoDatos();
		}
		
	}

}
