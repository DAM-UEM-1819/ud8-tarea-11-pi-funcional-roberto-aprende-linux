package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
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
		
		asignacionInsertado();
		asignacionBorrado();
		asignacionModificacion();

	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
	
	public void asignacionInsertado() {
		
		insertUsuario = propiedadesInsertado.getProperty(insertUsuario);
	}
	
	public void asignacionBorrado() {
		
	}
	
	public void asignacionModificacion() {
		
	}

	public void crearUsuarioProvisional(String user, String passwd, String rol) {
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

}
