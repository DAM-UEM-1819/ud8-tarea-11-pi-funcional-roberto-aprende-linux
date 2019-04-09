package modelo;

import java.sql.*;

import controlador.*;
import vista.*;

public class Modelo implements IModelo{
	
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
	
	//Atributos de control
	private int contador;
	
	//Select SQL
	private String selectPasswdUsuario = "SELECT PWD FROM USERS WHERE USR = ?";

	public void loginConfirmacion(String usuario, String passwd) {
		String sql = selectPasswdUsuario;
		try {
			PreparedStatement pstmt = controlador.getConexion().prepareStatement(sql);
			pstmt.setString(1, usuario);
			ResultSet rs = pstmt.executeQuery();
			if (rs.getString(1).equals(passwd)) {
				//LLamar al metodo de la vista para llamar al controlador y cambiar de pantalla
				login.loginExitoso();
			} else {
				contador++;
				if (contador <= 3) {
					login.salir();
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	
}
