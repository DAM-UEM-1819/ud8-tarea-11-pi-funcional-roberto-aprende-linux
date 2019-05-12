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

	// Sentencias Insertado SQL
	private String insertUsuario;
	private String insertAlumno;

	// Sentencias Delete SQL
	private String deleteAlumno;

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

	private void asignacionInsertado() {
		insertUsuario = propiedadesInsertado.getProperty("insertUsuario");
		insertAlumno = propiedadesInsertado.getProperty("insertAlumno");
	}

	private void asignacionBorrado() {
		deleteAlumno = propiedadesBorrado.getProperty("deleteAlumno");
	}

	private void asignacionModificacion() {

	}

	public String crearUsuario(String user, String passwd, String rol) {
		String sql = insertUsuario;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt = conexion.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.setString(2, passwd);
			pstmt.setString(3, rol);
			ResultSet rs = pstmt.executeQuery();
			respuesta = "Usuario creado";
		} catch (Exception e) {
			respuesta = "Error, algun campo vacio";
			crearUsuario.actualizarInfo();
			e.printStackTrace();
		}

		return respuesta;

	}

	public void crearAlumno(String exp, String nombre) {
		String sql = insertAlumno;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setString(1, exp);
			pstmt.setString(2, nombre);
			ResultSet rs = pstmt.executeQuery();
			datosFilastabla.add(exp);
			datosFilastabla.add(nombre);
			datosFilastabla.add(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void borrarAlumno(String exp, String nombre) {
		String sql = deleteAlumno;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setString(1, exp);
			pstmt.setString(2, nombre);
			ResultSet rs = pstmt.executeQuery();
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Object[] getDatosfilasTabla() {
		return datosFilastabla.toArray();
	}

}
