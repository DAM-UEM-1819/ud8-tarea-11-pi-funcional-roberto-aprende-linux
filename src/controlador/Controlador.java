package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import modelo.Modelo;
import vista.*;

public class Controlador implements IControlador {

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
	private Modelo modelo;

	/*
	 * *********************************************
	 * LINKEADO DE VENTANAS
	 * *********************************************
	 */

	public void loginToHome() {
		login.setVisible(false);
		home.setVisible(true);
	}
	
	//Ventana Home

	public void homeToLogin() {
		home.setVisible(false);
		login.setVisible(true);

	}

	public void homeToInfoExtra() {
		home.setVisible(false);
		infoExtra.setVisible(true);
	}

	public void homeToInformes() {
		home.setVisible(false);
		informes.setVisible(true);
	}

	public void homeToGestion() {
		home.setVisible(false);
		gestion.setVisible(true);
	}

	public void homeToOcupaciones() {
		home.setVisible(false);
//		ocupaciones.setVisible(true);
	}

	public void homeToPerfil() {
		home.setVisible(false);
		perfil.setVisible(true);
	}
	
	//Ventana ocupaciones

	public void ocupacionesToHome() {
//		ocupaciones.setVisible(false);
		home.setVisible(true);
	}
	
	//Ventana Gestion

	public void gestionToPerfil() {
		gestion.setVisible(false);
		perfil.setVisible(true);
	}

	public void gestionToGestionarUsuarios() {
		gestion.setVisible(false);
		gestionUsuarios.setVisible(true);
	}

	public void gestionToGestionarRegistros() {
		gestion.setVisible(false);
		gestionRegistros.setVisible(true);
	}

	public void gestionToGestionarActividades() {
		gestion.setVisible(false);
		gestionActividad.setVisible(true);
	}

	public void gestionToGestionarAsignatura() {
		gestion.setVisible(false);
		gestionAsignatura.setVisible(true);
	}

	public void gestionToGestionarAlumnos() {
		gestion.setVisible(false);
		gestionAlumnos.setVisible(true);
	}

	public void gestionToGestionarProfesores() {
		gestion.setVisible(false);
		gestionProfesores.setVisible(true);
	}

	public void gestionToGestionarActores() {
		gestion.setVisible(false);
		gestionActores.setVisible(true);
	}

	public void gestionToGestionarSalas() {
		gestion.setVisible(false);
		gestionSalas.setVisible(true);
	}

	public void gestionToVerGrupos() {
		gestion.setVisible(false);
		verGrupos.setVisible(true);
	}

	public void gestionToGestionarAcad() {
		gestion.setVisible(false);
		gestionAcad.setVisible(true);
	}

	public void gestionToHome() {
		gestion.setVisible(false);
		home.setVisible(true);
	}
	
	//Ventana Informes

	public void informesToHome() {
		informes.setVisible(false);
		home.setVisible(true);
	}

	public void informesToPerfil() {
		informes.setVisible(false);
		perfil.setVisible(true);
	}
	
	//Venata Informacion extra

	public void infoExtraToHome() {
		infoExtra.setVisible(false);
		home.setVisible(true);
	}

	public void infoExtraToPerfil() {
		infoExtra.setVisible(false);
		perfil.setVisible(true);
	}
	
	//Ventana crear usuario
	
	public void crearUsuarioToPerfil() {
		crearUsuario.setVisible(false);
		perfil.setVisible(true);
	}

	public void crearUsuarioToGestionUsuarios() {
		crearUsuario.setVisible(false);
		gestionUsuarios.setVisible(true);
	}
	
	//Ventana gestion usuarios

	public void gestionUsuariosToGestion() {
		gestionUsuarios.setVisible(false);
		gestion.setVisible(true);
	}

	public void gestionUsuariosToPerfil() {
		gestionUsuarios.setVisible(false);
		perfil.setVisible(true);
	}

	public void gestionUsuariosToCrearUsuario() {
		gestionUsuarios.setVisible(false);
		crearUsuario.setVisible(true);
	}
	
	//Ventana gestion acad

	public void gestionAcadToGestion() {
		gestionAcad.setVisible(false);
		gestion.setVisible(true);
	}

	public void gestionAcadToPerfil() {
		gestionAcad.setVisible(false);
		perfil.setVisible(true);
	}

	//Ventana gestion actividad
	
	public void gestionActividadToGestion() {
		gestionActividad.setVisible(false);
		gestion.setVisible(true);
	}

	public void gestionActividadToPerfil() {
		gestionActividad.setVisible(false);
		perfil.setVisible(true);
	}
	
	//Ventana gestion actores

	public void gestionActoresToGestion() {
		gestionActores.setVisible(false);
		gestion.setVisible(true);
	}
	
	public void gestionActoresToPerfil() {
		gestionActores.setVisible(false);
		perfil.setVisible(true);
	}
	
	//Ventana gestion alumnos

	public void gestionAlumnosToGestion() {
		gestionAlumnos.setVisible(false);
		gestion.setVisible(true);
	}

	public void gestionAlumnosToPerfil() {
		gestionAlumnos.setVisible(false);
		perfil.setVisible(true);
	}
	
	//Ventana gestion registros

	public void gestionRegistrosToGestion() {
		gestionRegistros.setVisible(false);
		gestion.setVisible(true);
	}

	public void gestionRegistrosToPerfil() {
		gestionRegistros.setVisible(false);
		perfil.setVisible(true);
	}
	
	//Ventana gestion asignatura

	public void gestionAsignaturaToGestion() {
		gestionAsignatura.setVisible(false);
		gestion.setVisible(true);
	}

	public void gestionAsignaturaToPerfil() {
		gestionAsignatura.setVisible(false);
		perfil.setVisible(true);
	}
	
	//Ventana gestion profesores

	public void gestionProfesoresToGestion() {
		gestionProfesores.setVisible(false);
		gestion.setVisible(true);
	}

	public void gestionProfesoresToPerfil() {
		gestionProfesores.setVisible(false);
		perfil.setVisible(true);
	}
	
	//Ventana gestion salas

	public void gestionSalasToGestion() {
		gestionSalas.setVisible(false);
		gestion.setVisible(true);
	}

	public void gestionSalasToPerfil() {
		gestionSalas.setVisible(false);
		perfil.setVisible(true);
	}
	
	//Ventana gestion grupos

	public void verGruposToGestion() {
		verGrupos.setVisible(false);
		gestion.setVisible(true);
	}

	public void verGruposToPerfil() {
		verGrupos.setVisible(false);
		perfil.setVisible(true);
	}
	
	//Ventana perfil

	public void perfilToHome() {
		perfil.setVisible(false);
		home.setVisible(true);
	}
	
	/*
	 * *********************************************
	 * INICIO SETTERS
	 * *********************************************
	 */

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

//	public void setOcupaciones(Ocupaciones ocupaciones) {
//		this.ocupaciones = ocupaciones;
//	}

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

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	/*
	 * *********************************************
	 * VENTANA LOGIN
	 * *********************************************
	 */
	
	public void loginSolicitud() {
		modelo.loginConfirmacion(login.getTxtUser(), login.getTextPasswd());
	}
	
	/*
	 * *********************************************
	 * VENTANA HOME
	 * *********************************************
	 */

	@Override
	public void confirmacionSalir() {
		home.confirmacionSalir();
	}
	
	/*
	 * *********************************************
	 * VENTANA CREAR USUARIOS
	 * *********************************************
	 */
	
	public void solicitudCrearUsuario() {
		modelo.crearUsuario(crearUsuario.getNombreUsuario(), crearUsuario.getPasswd(), crearUsuario.getRol());
	}
	
	public void solicitudDatosAcad() {
		modelo.getTablaAcad(gestionAcad.getModel());
	}
	
	public void solicitudDatosActividad() {
		modelo.getTablaActividad(gestionActividad.getModel());
	}
	
	public void solicitudDatosActores() {
		modelo.getTablaActores(gestionActores.getModel());
	}
	
	public void solicitudDatosAlumnos() {
		modelo.getTablaAlumnos(gestionAlumnos.getModel());
	}
	
	public void solicitudDatosAsignatura() {
		modelo.getTablaAsignatura(gestionAsignatura.getModel());
	}
	
	public void solicitudDatosProfesores() {
		modelo.getTablaProfesores(gestionProfesores.getModel());
	}
	
	public void solicitudDatosRegistros() {
		modelo.getTablaRegistros(gestionRegistros.getModel());
	}
	
	public void solicitudDatosSalas() {
		modelo.getTablaSalas(gestionSalas.getModel());
	}

	public void solicitudDatosUsuarios() {
		modelo.getTablaUsuarios(gestionUsuarios.getModel());		
	}



}
