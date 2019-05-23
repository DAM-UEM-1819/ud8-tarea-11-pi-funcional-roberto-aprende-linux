package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import modelo.Modelo;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;
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
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;

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

//		public void setOcupaciones(Ocupaciones ocupaciones) {
//			this.ocupaciones = ocupaciones;
//		}

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

	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas = modeloConsultas;
	}

	public void setModeloGestionDatos(ModeloGestionDatos modeloGestionDatos) {
		this.modeloGestionDatos = modeloGestionDatos;
	}

	// LINKEO DE VENTANAS
	public void loginToHome() {
		home.setVisible(true);
	}

	public void loginToHomeLectura() {
		home.vistaUsuarioLectura();
		home.setVisible(true);
	}

	// VENTANA HOME
	public void homeToLogin() {
		home.vistaDefault();
		login.setVisible(true);

	}

	public void homeToInfoExtra() {
		infoExtra.setVisible(true);
	}

	public void homeToInformes() {
		informes.setVisible(true);
	}

	public void homeToGestion() {
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

	// VENTANA OCUPACIONES
	public void ocupacionesToHome() {
//		ocupaciones.setVisible(false);
		home.setVisible(true);
	}

	// VENTANA GESTION
	public void gestionToPerfil() {
		perfil.setVisible(true);
	}

	public void gestionToGestionarUsuarios() {
		gestionUsuarios.setVisible(true);
	}

	public void gestionToGestionarRegistros() {
		gestionRegistros.setVisible(true);
	}

	public void gestionToGestionarActividades() {
		gestionActividad.setVisible(true);
	}

	public void gestionToGestionarAsignatura() {
		gestionAsignatura.setVisible(true);
	}

	public void gestionToGestionarAlumnos() {
		gestionAlumnos.setVisible(true);
	}

	public void gestionToGestionarProfesores() {
		gestionProfesores.setVisible(true);
	}

	public void gestionToGestionarActores() {
		gestionActores.setVisible(true);
	}

	public void gestionToGestionarSalas() {
		gestionSalas.setVisible(true);
	}

	public void gestionToVerGrupos() {
		verGrupos.setVisible(true);
	}

	public void gestionToGestionarAcad() {
		gestionAcad.setVisible(true);
	}

	public void gestionToHome() {
		home.setVisible(true);
	}

	// VENTANA INFORMES
	public void informesToHome() {
		home.setVisible(true);
	}

	public void informesToPerfil() {
		perfil.setVisible(true);
	}

	// VENTANA INFORMACION EXTRA
	public void infoExtraToHome() {
		home.setVisible(true);
	}

	public void infoExtraToPerfil() {
		perfil.setVisible(true);
	}

	// VENTANA CREAR USUARIO
	public void crearUsuarioToPerfil() {
		perfil.setVisible(true);
	}

	public void crearUsuarioToGestionUsuarios() {
		gestionUsuarios.setVisible(true);
	}

	// VENTANA GESTION USUARIOS
	public void gestionUsuariosToGestion() {
		gestion.setVisible(true);
	}

	public void gestionUsuariosToPerfil() {
		perfil.setVisible(true);
	}

	public void gestionUsuariosToCrearUsuario() {
		crearUsuario.setVisible(true);
	}

	public void solicitudDatosUsuarios() {
		modeloConsultas.getTablaUsuarios(gestionUsuarios.getModel());
	}
	
	public void solicitudBuscadorUsuario() {
		modeloConsultas.buscadorUsuarios(gestionUsuarios.getModel(), gestionUsuarios.getPalabraBuscador());
	}

	// VENTANA GESTION ACAD
	public void gestionAcadToGestion() {
		gestion.setVisible(true);
	}

	public void gestionAcadToPerfil() {
		perfil.setVisible(true);
	}

	public void solicitudDatosAcad() {
		modeloConsultas.getTablaAcad(gestionAcad.getModel());
	}

	// VENTANA GESTION ACTIVIDAD
	public void gestionActividadToGestion() {
		gestion.setVisible(true);
	}

	public void gestionActividadToPerfil() {
		perfil.setVisible(true);
	}

	public void solicitudDatosActividad() {
		modeloConsultas.getTablaActividad(gestionActividad.getModel());
	}

	// VENTANA GESTION ACTORES
	public void gestionActoresToGestion() {
		gestion.setVisible(true);
	}

	public void gestionActoresToPerfil() {
		perfil.setVisible(true);
	}

	public void solicitudDatosActores() {
		modeloConsultas.getTablaActores(gestionActores.getModel());
	}

	// VENTANA GESTION ALUMNOS
	public void gestionAlumnosToGestion() {
		gestion.setVisible(true);
	}

	public void gestionAlumnosToPerfil() {
		perfil.setVisible(true);
	}

	public void solicitudDatosAlumnos() {
		modeloConsultas.getTablaAlumnos(gestionAlumnos.getModel());
	}

	public void solicitudcrearAlumno() {
		modeloGestionDatos.crearAlumno(gestionAlumnos.getExp(), gestionAlumnos.getNombre());
	}

	public void solicitudBorrar(Object obj) {
		String clase = obj.getClass().toString(); 
		String opcion = clase.substring(12, clase.length()-2).toUpperCase();
		switch (opcion) {
		case "GESTIONALUMNOS":
			modeloGestionDatos.opcionesBorrarDatos(gestionAlumnos.getPrimaryKey(), "A");
			break;
		case "GESTIONUSUARIOS":
			modeloGestionDatos.opcionesBorrarDatos(gestionUsuarios.getPrimaryKey(), "B");
			break;
		case "GESTIONACTIVIDAD":
			modeloGestionDatos.opcionesBorrarDatos(gestionActividad.getPrimaryKey(), "C");
			break;
		case "GESTIONASIGNATURA":
			modeloGestionDatos.opcionesBorrarDatos(gestionAsignatura.getPrimaryKey(), "D");
			break;
		case "GESTIONSALAS":
			modeloGestionDatos.opcionesBorrarDatos(gestionSalas.getPrimaryKey(), "E");
			break;
		case "GESTIONREGISTROS":
			modeloGestionDatos.opcionesBorrarDatos(gestionRegistros.getPrimaryKey(), "F");
			break;
		}
		
	}

	public void solicitudModificarAlumno() {
		modeloGestionDatos.modificarAlumno(gestionAlumnos.getExp(), gestionAlumnos.getNombre(),
				gestionAlumnos.estadoCheckBox());
	}
	
	public void solicitudBuscadorAlumno() {
		modeloConsultas.buscadorAlumnos(gestionAlumnos.getModel(), gestionAlumnos.getPalabraBuscador());
	}
	


	// VENTANA GESTION REGISTROS
	public void gestionRegistrosToGestion() {
		gestion.setVisible(true);
	}

	public void gestionRegistrosToPerfil() {
		perfil.setVisible(true);
	}

	public void solicitudDatosRegistros() {
		modeloConsultas.getTablaRegistros(gestionRegistros.getModel());
	}
	
	public void solicitudModificarRegistro() {
		modeloGestionDatos.modificarRegistro(gestionRegistros.getCod_registro(),gestionRegistros.getFecha(),gestionRegistros.getHora(),gestionRegistros.getHorasProfesor(),gestionRegistros.getActividadNombre());
	}

	// VENTANA GESTION ASIGNATURA
	public void gestionAsignaturaToGestion() {
		gestion.setVisible(true);
	}

	public void gestionAsignaturaToPerfil() {
		perfil.setVisible(true);
	}

	public void solicitudDatosAsignatura() {
		modeloConsultas.getTablaAsignatura(gestionAsignatura.getModel());
	}

	// VENTANA GESTION PROFESORES
	public void gestionProfesoresToGestion() {
		gestion.setVisible(true);
	}

	public void gestionProfesoresToPerfil() {
		perfil.setVisible(true);
	}

	public void solicitudDatosProfesores() {
		modeloConsultas.getTablaProfesores(gestionProfesores.getModel());
	}
	
//	public void solicitusCrearPorfesor() {
//		modeloGestionDatos.crearProfesor();
//	}

	// VENTANA GESTION SALAS
	public void gestionSalasToGestion() {
		gestion.setVisible(true);
	}

	public void gestionSalasToPerfil() {
		perfil.setVisible(true);
	}

	public void solicitudDatosSalas() {
		modeloConsultas.getTablaSalas(gestionSalas.getModel());
	}
	
	public void solicitudCrearSala() {
		modeloConsultas.comprobarSala(gestionSalas.getCodigo());
		modeloGestionDatos.crearSala(gestionSalas.getCodigo(), gestionSalas.getTipoSala(), gestionSalas.getNumero(), gestionSalas.getCapacidad());
	}
	
	public void solicutudModificarSala() {
		modeloGestionDatos.modificarSala(gestionSalas.getCodigo(), gestionSalas.getTipoSala(), gestionSalas.getNumero(), gestionSalas.getCapacidad());
		
	}
	
	public void solicitudBuscadorSala() {
		modeloConsultas.buscadorSalas(gestionSalas.getModel(), gestionSalas.getPalabraBuscador());
	}

	// VENTANA GESTION GRUPOS
	public void verGruposToGestion() {
		gestion.setVisible(true);
	}

	public void verGruposToPerfil() {
		perfil.setVisible(true);
	}

	public void solicitudListadoGrupos() {
		modeloConsultas.listadoGrupos();
	}

	public void solicitudListadoAlumnosPorGrupo() {
		modeloConsultas.getListadoAlumnosPorGrupo(verGrupos.getModel(), verGrupos.getGrupoComboBox());
	}

	// VENTANA PERFIL
	public void perfilToHome() {
		home.setVisible(true);
	}
	
	public void solicitudDatosUsuarioActual() {
		modeloConsultas.getDatosUsuarioPerfil();
	}
	
	public void solicitudActualizarUsuario() {
		modeloGestionDatos.actualizarUsuario(perfil.getUsuario(), perfil.getEmail(), perfil.getPasswdActual(), perfil.getPasswdNueva(), perfil.getPasswdComprobacion());
	}

	// VENTANA LOGIN
	public void loginSolicitud() {
		modeloConsultas.loginConfirmacion(login.getTxtUser(), login.getTextPasswd());
	}

	// VENTANA HOME
	@Override
	public void confirmacionSalir() {
		home.confirmacionSalir();
	}

	public void solicitudDatosHome() {
		modeloConsultas.getTablaHome(home.getModel());
	}

	public void solicitudDatosExtraHome() {
		modeloConsultas.getDatosExtraHome(home.getDatosFilaTabla());
	}

	// VENTANA CREAR USUARIOS
	public void solicitudCrearUsuario() {
		modeloConsultas.crearUsuario(crearUsuario.getNombreUsuario(), crearUsuario.getRol(), crearUsuario.getEmail());
	}
	
	public void solicitudBuscador(Object obj) {
		String clase = obj.getClass().toString(); 
		String opcion = clase.substring(12, clase.indexOf("$")).toUpperCase(); 
		switch (opcion) {
		case "GESTIONALUMNOS":
			modeloConsultas.buscador(gestionAlumnos.getModel(), gestionAlumnos.getPalabraBuscador(), "A");
			break;
		case "GESTIONUSUARIOS":
			modeloConsultas.buscador(gestionUsuarios.getModel(), gestionUsuarios.getPalabraBuscador(), "B");
			break;
		case "GESTIONACTIVIDAD":
			modeloConsultas.buscador(gestionActividad.getModel(), gestionActividad.getPalabraBuscador(), "C");
			break;
		case "GESTIONASIGNATURA":
			modeloConsultas.buscador(gestionAsignatura.getModel(), gestionAsignatura.getPalabraBuscador(), "D");
			break;
		case "GESTIONSALAS":
			modeloConsultas.buscador(gestionSalas.getModel(), gestionSalas.getPalabraBuscador(), "E");
			break;
		case "GESTIONPROFESORES":
			modeloConsultas.buscador(gestionProfesores.getModel(), gestionProfesores.getPalabraBuscador(), "F");
			break;
		case "GESTIONACAD":
			modeloConsultas.buscador(gestionAcad.getModel(), gestionAcad.getPalabraBuscador(), "G");
			break;
		case "GESTIONACTORES":
			modeloConsultas.buscador(gestionActores.getModel(), gestionActores.getPalabraBuscador(), "H");
			break;
		case "GESTIONREGISTROS":
			modeloConsultas.buscador(gestionRegistros.getModel(), gestionRegistros.getPalabraBuscador(), "I");
			break;
		case "VERGRUPOS":
			modeloConsultas.buscador(verGrupos.getModel(), verGrupos.getPalabraBuscador(), "J");
			break;
		}
		
	}


}
