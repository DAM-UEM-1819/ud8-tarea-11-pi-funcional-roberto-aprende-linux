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
	private String deleteUsuario;

	// Sentincias Update SQL
	private String updateAlumno;

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
		deleteUsuario = propiedadesBorrado.getProperty("deleteUsuario");
	}

	private void asignacionModificacion() {
		updateAlumno = propiedadesModificacion.getProperty("updateAlumno");

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
		datosFilastabla.removeAll(datosFilastabla);
		if (!exp.isEmpty() || !nombre.isEmpty()) {
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
		} else {
			respuesta = "Error, expediente o nombre vacio";
		}
		gestionAlumnos.actualizarInfo();
	}

	public void borrarDato(String clave, String opcion) {
		String sql = "";
		switch (opcion) {
		case "A":
			sql = deleteAlumno;
			break;
		case "B":
			sql = deleteUsuario;
			break;
		case "C":
			sql = deleteActividad;
			break;
		case "D":
			sql = deleteAsignatura;
			break;
		case "E":
			sql = deleteSalas;
			break;
		case "F":
			sql = deleteRegistros;
			break;
		}

		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setString(1, clave);
			ResultSet rs = pstmt.executeQuery();
			datosFilastabla.removeAll(datosFilastabla);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void ModificarAlumno(String exp, String nombre, int activo) {
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

	public Object[] getDatosfilasTabla() {
		return datosFilastabla.toArray();

	}

	public String getRespuesta() {
		return respuesta;
	}

}
