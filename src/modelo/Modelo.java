package modelo;

import java.sql.*;

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

	// Atributos de control
	private int contador;

	// Select SQL
	private String selectPasswdUsuario = "SELECT PWD FROM USERS WHERE USR = ?";
	
	public Modelo() {
		baseDatos = "Hospital";
		usuarioDB = "SYSTEM";
		passwdDB = "admin";
		urlDB = "jdbc:oracle:thin:@localhost:1521:XE";
		try {
			Class.forName("oracle..jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection(urlDB, usuarioDB, passwdDB);
			System.out.println("La conexion ha sido exitosa");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("La conexion ha fallado");
			e.printStackTrace();
		}

	}

	public void loginConfirmacion(String usuario, String passwd) {
		String sql = selectPasswdUsuario;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setString(1, usuario);
			ResultSet rs = pstmt.executeQuery();
			if (rs.getString(1).equals(passwd)) {
				// LLamar al metodo de la vista para llamar al controlador y cambiar de pantalla
				login.loginExitoso();
			} else {
				contador++;
				if (contador >= 3) {
					login.salir();
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
