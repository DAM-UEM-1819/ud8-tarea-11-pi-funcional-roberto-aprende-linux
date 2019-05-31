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
import vista.GestionProfesoresAddMod;
import vista.GestionRegistros;
import vista.GestionRegistrosAddMod;
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
	private GestionProfesoresAddMod gestionProfesoresAddMod;
	private GestionRegistrosAddMod gestionRegistrosAddMod;
	private GestionSalas gestionSalas;
	private VerGrupos verGrupos;
	private Perfil perfil;
	private Controlador controlador;
	private Modelo modelo;
	private ModeloConsultas modeloConsultas;

	private ArrayList<Object> datosFilastabla;
	private ArrayList<String> datosProfe;

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
	private boolean seHaCambiadoEstado;
	private String clave;
	private int activo;

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
	// private String deleteAlumno;
	private String deleteUsuario;
	private String deleteSala;
	private String deleteOcupa;
	private String deleteActuaRegistro;
	private String deleteAsignatura;
	private String deleteParticipaRegistro;
	private String deleteRealizaRegistro;
	private String deleteOcupaRegistro;
	private String deleteRegistro;

	// Sentincias Update SQL
	private String updateAlumno;
	private String updateAsignatura;
	private String updateSala;
	private String updateRegistro;
	private String updateUsuario;
	private String updateProfesor;
	private String updateActor;
	// activo-inactivo
	private String activoInactivoUpdateAlumno;
	private String activoInactivoUpdateProfesor;
	private String activoInactivoUpdateActor;


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
		datosProfe = new ArrayList<String>();
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

	public void setGestionProfesoresAddMod(GestionProfesoresAddMod gestionProfesoresAddMod) {
		this.gestionProfesoresAddMod = gestionProfesoresAddMod;
	}
	
	public void setGestionRegistrosAddMod(GestionRegistrosAddMod gestionRegistrosAddMod) {
		this.gestionRegistrosAddMod = gestionRegistrosAddMod;
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

	public boolean getSeHaCambiadoEstado() {
		return seHaCambiadoEstado;
	}

	public boolean getSeHaCreado() {
		return seHaCreado;
	}

	public boolean getVaciarDatos() {
		return datosProfe.removeAll(datosProfe);
	}

	public Object[] getDatosfilasTabla() {
		return datosFilastabla.toArray();

	}

	public Object[] getRellenarDatos() {
		return datosProfe.toArray();
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
		// deleteAlumno = propiedadesBorrado.getProperty("deleteAlumno");
		deleteUsuario = propiedadesBorrado.getProperty("deleteUsuario");
		deleteSala = propiedadesBorrado.getProperty("deleteSala");
		deleteOcupa = propiedadesBorrado.getProperty("deleteOcupa");
		//
		deleteActuaRegistro = propiedadesBorrado.getProperty("deleteActuaRegistro");
		deleteParticipaRegistro = propiedadesBorrado.getProperty("deleteParticipaRegistro");
		deleteRealizaRegistro = propiedadesBorrado.getProperty("deleteRealizaRegistro");
		deleteOcupaRegistro = propiedadesBorrado.getProperty("deleteOcupaRegistro");
		deleteRegistro = propiedadesBorrado.getProperty("deleteRegistro");
		//
		deleteAsignatura = propiedadesBorrado.getProperty("deleteAsignatura");
	}

	/**
	 * Metodo para asignar las sentencias de modificacion a los atributos
	 */
	private void asignacionModificacion() {
		updateAlumno = propiedadesModificacion.getProperty("updateAlumno");
		updateAsignatura = propiedadesModificacion.getProperty("updateAsignatura");
		updateSala = propiedadesModificacion.getProperty("updateSala");
		updateRegistro = propiedadesModificacion.getProperty("updateRegistro");
		updateUsuario = propiedadesModificacion.getProperty("updateUsuario");
		updateProfesor = propiedadesModificacion.getProperty("updateProfesor");
		updateActor = propiedadesModificacion.getProperty("updateActor");
		//
		activoInactivoUpdateAlumno = propiedadesModificacion.getProperty("activoInactivoUpdateAlumno");
		activoInactivoUpdateProfesor = propiedadesModificacion.getProperty("activoInactivoUpdateProfesor");
		activoInactivoUpdateActor = propiedadesModificacion.getProperty("activoInactivoUpdateActor");

	}

	/**
	 * Metodo para crear un usuario en la BBDD
	 *
	 * @param user
	 *            El usuario a crear
	 * @param passwd
	 *            La contraseña del usuario
	 * @param rol
	 *            El rol del usuario
	 * @return Un String con el estado del metodo (si se ha creado o no)
	 */
	public String crearUsuario(String user, String rol, String correo) {
		String passwd = modelo.generadorPasswd();
		String passwdMD5 = modelo.generarMD5(passwd);
		try {
			PreparedStatement pstmt = conexion.prepareStatement(insertUsuario);
			pstmt.setString(1, user.toUpperCase());
			pstmt.setString(2, passwdMD5);
			pstmt.setString(3, rol.toUpperCase());
			pstmt.setString(4, correo.toUpperCase());
			ResultSet rs = pstmt.executeQuery();
			modelo.enviarCorreoGmail(correo, user, passwd);
			respuesta = "Usuario creado";
		}catch (SQLIntegrityConstraintViolationException e) {
			respuesta = "Error, el usuario ya existe";
			crearUsuario.actualizarInfo();
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return respuesta;

	}

	public void actualizarUsuario(String user, String correo, String passwdActual, String passwdNueva,
			String passwdComprobacion) {
		passwdActual = modelo.generarMD5(passwdActual);
		String passwdBD = modeloConsultas.consultarPasswdUsuario(user, passwdActual);
		try {

			if (!user.equals("") || !correo.equals("")) {
				if (passwdActual.equals(passwdBD)) {

					if (passwdNueva.equals(passwdComprobacion)) {

						PreparedStatement pstmt = conexion.prepareStatement(updateUsuario);
						pstmt.setString(1, user.toUpperCase());
						pstmt.setString(2, modelo.generarMD5(passwdNueva));
						pstmt.setString(3, correo.toUpperCase());
						pstmt.setString(4, user.toUpperCase());
						ResultSet rs = pstmt.executeQuery();
						respuesta = "Usuario modificado correctamente";

					} else {
						respuesta = "Error, las nuevas contrase�as no coinciden";
					}

				} else {
					respuesta = "Error, contrase�a incorrecta";
				}
			} else {
				respuesta = "Error, los campos de usuario y email est�n vacios";
			}

		} catch (Exception e) {
			respuesta = "Error, ese usuario ya existe";
			e.printStackTrace();
		}

		perfil.actualizarInfo();

	}

	/**
	 * Metodo para crear un alumno en la BBDD
	 *
	 * @param exp
	 *            El expediente del alumno
	 * @param nombre
	 *            El nombre del alumno
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
	 * @param cod
	 *            Codigo de la sala
	 * @param tipo
	 *            Tipo de sala
	 * @param numero
	 *            Numero de la sala
	 * @param capacidad
	 *            Capacidad de la sala
	 */
	public void crearSala(String tipo, String numero, String capacidad) {
		if (!tipo.isEmpty() && !numero.isEmpty() && !capacidad.isEmpty()) {
			try {
				PreparedStatement pstmt = conexion.prepareStatement(insertSala);
				pstmt.setString(1, modeloConsultas.getUltimoRegistro());
				pstmt.setString(2, tipo);
				pstmt.setString(3, numero);
				pstmt.setString(4, capacidad);
				addDatos(pstmt);
				datosFilastabla.removeAll(datosFilastabla);
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

	public void crearAsignatura(String codigoAsignatura, String nombre, String titulacion, String curso) {
		if (!codigoAsignatura.isEmpty() && !nombre.isEmpty() && !titulacion.isEmpty() && !curso.isEmpty()) {
			try {
				PreparedStatement pstmt = conexion.prepareStatement(insertAsignatura);
				pstmt.setString(1, codigoAsignatura);
				pstmt.setString(2, nombre);
				pstmt.setString(3, titulacion);
				pstmt.setString(4, curso);
				addDatos(pstmt);
				datosFilastabla.removeAll(datosFilastabla);
				datosFilastabla.add(codigoAsignatura);
				datosFilastabla.add(nombre);
				datosFilastabla.add(titulacion);
				datosFilastabla.add(curso);
				seHaCreado = true;
				respuesta = "Asignatura creada";
				gestionAsignatura.actualizarInfoDatos();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			seHaCreado = false;
			respuesta = "Error, asignatura ya creada";
			gestionAsignatura.actualizarInfoDatos();
		}

	}

	/**
	 * Metodo para ejecutar las sentencias
	 *
	 * @param pstmt
	 *            La sentencia con los interrogantes puestos
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
	 * @param clave
	 *            La clave de la fila a borrar
	 * @param opcion
	 *            El tipo de tabla al que se hace referencia
	 * @return booleano indicando si la sentencia se ha realizado con exito
	 */
	public boolean opcionesBorrarDatos(String clave, String opcion) {
		this.clave = clave;
		seHaBorrado = false;
		switch (opcion) {
		case "A":
			seHaBorrado = borrarDatos(updateAlumno);
			break;
		case "B":
			seHaBorrado = borrarDatos(deleteUsuario);
			break;
		case "C":
			// sql = deleteActividad;
			break;
		case "D":
			// sql = deleteAsignatura;
			seHaBorrado = borrarDatos(deleteAsignatura);

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

	public boolean opcionesActivoDatos(int activo, String clave, String opcion) {
		this.clave = clave;
		this.activo = activo;
		seHaCambiadoEstado = false;
		switch (opcion) {
		case "A":
			seHaCambiadoEstado = ActivoDatos(activoInactivoUpdateAlumno);
			break;
		case "B":

			break;
		case "C":
			// sql = deleteActividad;
			break;
		case "D":
			// sql = deleteAsignatura;
			break;
		case "E":

			break;
		case "F":
			// sql = deleteRegistros;

			break;
		case "G":
			// sql = activoProfesor ;
			seHaCambiadoEstado = ActivoDatos(activoInactivoUpdateProfesor);

			break;
			
		case "H":
			// sql = activoActor ;
			seHaCambiadoEstado = ActivoDatos(activoInactivoUpdateActor);

			break;
		}

		return seHaBorrado;

	}

	/**
	 * Metodo para borrar los datos
	 *
	 * @param sql
	 *            La sentencia de borrado
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
	 *
	 * Metodo que se utiliza para poner un dato activo o inactivo
	 *
	 * @param sql
	 *            sentencia del update activo/inactivo
	 * @return booleano que indica si la sentnecia se realiza con exito
	 */

	private boolean ActivoDatos(String sql) {
		seHaCambiadoEstado = false;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setInt(1, activo);
			pstmt.setString(2, clave);
			pstmt.executeUpdate();
			seHaCambiadoEstado = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seHaCambiadoEstado;
	}

	/**
	 * Metodo para modificar los datos de un alumno
	 *
	 * @param exp
	 *            El expediente del alumno
	 * @param nombre
	 *            El nombre del alumno
	 * @param activo
	 *            El estado del alumno
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
				respuesta = "Has modificado alumno";
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
	 * @param cod
	 *            Codigo de la sala
	 * @param tipo
	 *            El tipo de sala
	 * @param numero
	 *            El numero de la sala
	 * @param capacidad
	 *            La capacidad de la sala
	 */
	public void modificarSala(String tipo, String numero, String capacidad) {
		if (!tipo.isEmpty() && !numero.isEmpty() && !capacidad.isEmpty()) {
			try {
				PreparedStatement pstmt = conexion.prepareStatement(updateSala);
				pstmt.setString(1, tipo);
				pstmt.setString(2, capacidad);
				pstmt.setString(3, numero);
				pstmt.setString(4, modeloConsultas.getCodigoRegistro());
				pstmt.executeUpdate();
				seHaCreado = true;
				respuesta = "Has modificado sala";
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
		if (!cod_registro.isEmpty() && !fecha.isEmpty() && !hora.isEmpty() && !horasProfesor.isEmpty()
				&& !actividadNombre.isEmpty()) {
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
			// gestionRegistros.actualizarInfoDatos();
		}

	}

	public void modificarProfesor(String numero, String nombre, String Apellido1, String Apellido2, String titulacion,
			String dni, String AI_profesores, String relacion_laboral, String telefono1, String telefono2,
			String email1, String email2) {

		if (!numero.isEmpty() && !nombre.isEmpty() && !Apellido1.isEmpty() && !Apellido2.isEmpty()
				&& !titulacion.isEmpty() && !dni.isEmpty() && !AI_profesores.isEmpty() && !relacion_laboral.isEmpty()
				&& !telefono1.isEmpty() && !telefono2.isEmpty() && !email1.isEmpty() && !email2.isEmpty()) {

			try {
				PreparedStatement pstmt = conexion.prepareStatement(updateProfesor);
				pstmt.setString(1, nombre);
				pstmt.setString(2, Apellido1);
				pstmt.setString(3, Apellido2);
				pstmt.setString(4, titulacion);
				pstmt.setString(5, dni);
				pstmt.setString(6, AI_profesores);
				pstmt.setString(7, relacion_laboral);
				pstmt.setString(8, telefono1);
				pstmt.setString(9, telefono2);
				pstmt.setString(10, email1);
				pstmt.setString(11, email2);
				pstmt.setString(12, numero);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			respuesta = "Error, estas modificando el numero del profesor";
			gestionProfesoresAddMod.actualizarInfoDatos();
		}
	}
	
	

	public ArrayList<String> rellenarCamposProfe(String numGP, String nombreProfeGP, String ape1gp, String ape2gp,
			String titulacion, String dni, String activo, String relacion, String tlf1, String tlf2, String mail1,
			String mail2) {
		datosProfe.removeAll(datosProfe);
		String.valueOf(datosProfe.add(numGP));
		String.valueOf(datosProfe.add(dni));
		String.valueOf(datosProfe.add(nombreProfeGP));
		String.valueOf(datosProfe.add(ape1gp));
		String.valueOf(datosProfe.add(ape2gp));
		String.valueOf(datosProfe.add(titulacion));
		String.valueOf(datosProfe.add(mail1));
		String.valueOf(datosProfe.add(mail2));
		String.valueOf(datosProfe.add(tlf1));
		String.valueOf(datosProfe.add(tlf2));
		String.valueOf(datosProfe.add(relacion));
		String.valueOf(datosProfe.add(activo));
		return datosProfe;

	}

	public void crearProfesor(String numero, String nombre, String Apellido1, String Apellido2, String titulacion,
			String dni, String AI_profesores, String relacion_laboral, String telefono1, String telefono2,
			String email1, String email2) {
		if (!numero.isEmpty() && !nombre.isEmpty() && !Apellido1.isEmpty() && !Apellido2.isEmpty()
				&& !titulacion.isEmpty() && !dni.isEmpty() && !AI_profesores.isEmpty() && !relacion_laboral.isEmpty()
				&& !telefono1.isEmpty() && !telefono2.isEmpty() && !email1.isEmpty() && !email2.isEmpty()) {

			try {
				PreparedStatement pstmt = conexion.prepareStatement(insertProfesor);
				pstmt.setString(1, numero);
				pstmt.setString(2, nombre);
				pstmt.setString(3, Apellido1);
				pstmt.setString(4, Apellido2);
				pstmt.setString(5, titulacion);
				pstmt.setString(6, dni);
				pstmt.setString(7, AI_profesores);
				pstmt.setString(8, relacion_laboral);
				pstmt.setString(9, telefono1);
				pstmt.setString(10, telefono2);
				pstmt.setString(11, email1);
				pstmt.setString(12, email2);
				addDatos(pstmt);
				seHaCreado = true;
				datosProfe.removeAll(datosProfe);
				String.valueOf(datosProfe.add(numero));
				String.valueOf(datosProfe.add(nombre));
				String.valueOf(datosProfe.add(Apellido1));
				String.valueOf(datosProfe.add(Apellido2));
				String.valueOf(datosProfe.add(titulacion));
				String.valueOf(datosProfe.add(dni));
				String.valueOf(datosProfe.add(AI_profesores));
				String.valueOf(datosProfe.add(relacion_laboral));
				String.valueOf(datosProfe.add(telefono1));
				String.valueOf(datosProfe.add(telefono2));
				String.valueOf(datosProfe.add(email1));
				String.valueOf(datosProfe.add(email2));

				for (int i = 0; i < datosProfe.toArray().length; i++) {
					System.out.println(i + " " + getRellenarDatos()[i]);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			respuesta = "Error, algún campo vacio";
		}
	}



	public void modificarAsignatura(String codigoAsignatura, String nombre, String titulacion, String curso) {
		if (!codigoAsignatura.isEmpty() && !nombre.isEmpty() && !titulacion.isEmpty() && !curso.isEmpty()) {
			try {
				PreparedStatement pstmt = conexion.prepareStatement(updateAsignatura);
				pstmt.setString(1, nombre);
				pstmt.setString(2, titulacion);
				pstmt.setString(3, curso);
				pstmt.setString(4, codigoAsignatura);
				pstmt.executeUpdate();
				datosFilastabla.removeAll(datosFilastabla);
				datosFilastabla.add(nombre);
				datosFilastabla.add(titulacion);
				datosFilastabla.add(curso);
				seHaCreado = true;
				respuesta = "Asignatura modificada";
				gestionAsignatura.actualizarInfoDatos();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			seHaCreado = false;
			respuesta = "Error, no puedes modificar el codifgo de la asignatura ya creada";
			gestionAsignatura.actualizarInfoDatos();
		}

	}

	public void crearActor(String nombre, String edad, String genero, String idioma, String complexion,
			String activo2) {
		if (!nombre.isEmpty()) {
			try {
				PreparedStatement pstmt = conexion.prepareStatement(insertActor);
				pstmt.setString(1, modeloConsultas.getUltimoRegistro());
				pstmt.setString(2, nombre);
				pstmt.setString(3, edad);
				pstmt.setString(4, genero);
				pstmt.setString(5, idioma);
				pstmt.setString(6, complexion);
				pstmt.setString(7, activo2);

				addDatos(pstmt);

				datosFilastabla.removeAll(datosFilastabla);
				datosFilastabla.add(modeloConsultas.getUltimoRegistro());
				datosFilastabla.add(nombre);
				datosFilastabla.add(edad);
				datosFilastabla.add(genero);
				datosFilastabla.add(idioma);
				datosFilastabla.add(complexion);
				datosFilastabla.add(activo2);
				
				respuesta = "Actor creado";
				seHaCreado = true;
			} catch (Exception e) {
				seHaCreado = false;
				e.printStackTrace();
			}
		} else {
			seHaCreado = false;
			respuesta = "Error, nombre vacio";
		}
		gestionActores.actualizarInfo();
	}

	public void modificarActor(String nombre, String edad, String genero, String idioma, String complexion,
			String activo, String codActor) {
		if (!nombre.isEmpty()) {
			try {
				PreparedStatement pstmt = conexion.prepareStatement(updateActor);
				pstmt.setString(1, nombre);
				pstmt.setString(2, edad);
				pstmt.setString(3, genero);
				pstmt.setString(4, idioma);
				pstmt.setString(5, complexion);
				pstmt.setString(6, activo);
				pstmt.setString(7, codActor);
				pstmt.executeUpdate();
				respuesta = "Actor modificado";
				seHaCreado = true;
			} catch (Exception e) {
				seHaCreado = false;
				e.printStackTrace();
			}
		} else {
			seHaCreado = false;
			respuesta = "Error, nombre vacio";
		}
		gestionAlumnos.actualizarInfo();
		
		
	}

}
