package controlador;

import vista.*;

public class Controlador implements IControlador {

	// Atributos
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

	// Linkeo ventanas

	public void loginToHome() {
		login.setVisible(false);
		home.setVisible(true);
	}

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

	public void ocupacionesToHome() {
//		ocupaciones.setVisible(false);
		home.setVisible(true);
	}

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

	public void informesToHome() {
		informes.setVisible(false);
		home.setVisible(true);
	}

	public void informesToPerfil() {
		informes.setVisible(false);
		perfil.setVisible(true);
	}

	public void infoExtraToHome() {
		infoExtra.setVisible(false);
		home.setVisible(true);
	}

	public void infoExtraToPerfil() {
		infoExtra.setVisible(false);
		perfil.setVisible(true);
	}

	public void crearUsuarioToPerfil() {
		crearUsuario.setVisible(false);
		perfil.setVisible(true);
	}

	public void crearUsuarioToGestionUsuarios() {
		crearUsuario.setVisible(false);
		gestionUsuarios.setVisible(true);
	}

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

	public void gestionAcadToGestion() {
		gestionAcad.setVisible(false);
		gestion.setVisible(true);
	}

	public void gestionAcadToPerfil() {
		gestionAcad.setVisible(false);
		perfil.setVisible(true);
	}

	public void gestionActividadToGestion() {
		gestionActividad.setVisible(false);
		gestion.setVisible(true);
	}

	public void gestionActividadToPerfil() {
		gestionActividad.setVisible(false);
		perfil.setVisible(true);
	}

	public void gestionActoresToGestion() {
		gestionActores.setVisible(false);
		gestion.setVisible(true);
	}

	public void gestionActoresToPerfil() {
		gestionActores.setVisible(false);
		perfil.setVisible(true);
	}

	public void gestionAlumnosToGestion() {
		gestionAlumnos.setVisible(false);
		gestion.setVisible(true);
	}

	public void gestionAlumnosToPerfil() {
		gestionAlumnos.setVisible(false);
		perfil.setVisible(true);
	}

	public void gestionRegistrosToGestion() {
		gestionRegistros.setVisible(false);
		gestion.setVisible(true);
	}

	public void gestionRegistrosToPerfil() {
		gestionRegistros.setVisible(false);
		perfil.setVisible(true);
	}

	public void gestionAsignaturaToGestion() {
		gestionAsignatura.setVisible(false);
		gestion.setVisible(true);
	}

	public void gestionAsignaturaToPerfil() {
		gestionAsignatura.setVisible(false);
		perfil.setVisible(true);
	}

	public void gestionProfesoresToGestion() {
		gestionProfesores.setVisible(false);
		gestion.setVisible(true);
	}

	public void gestionProfesoresToPerfil() {
		gestionProfesores.setVisible(false);
		perfil.setVisible(true);
	}

	public void gestionSalasToGestion() {
		gestionSalas.setVisible(false);
		gestion.setVisible(true);
	}

	public void gestionSalasToPerfil() {
		gestionSalas.setVisible(false);
		perfil.setVisible(true);
	}

	public void verGruposToGestion() {
		verGrupos.setVisible(false);
		gestion.setVisible(true);
	}

	public void verGruposToPerfil() {
		verGrupos.setVisible(false);
		perfil.setVisible(true);
	}

	public void perfilToHome() {
		perfil.setVisible(false);
		home.setVisible(true);
	}
	
	/*
	 * **********************************
	 * Setters
	 * **********************************
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

	@Override
	public void confirmacionSalir() {
		// TODO Auto-generated method stub
		
	}
	
	
}
