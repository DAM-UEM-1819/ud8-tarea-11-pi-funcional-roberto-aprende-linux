package controlador;

import modelo.Modelo;
import vista.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Creamos el controlador
		Controlador controlador = new Controlador();
		//Creariamos el modelo
		Modelo modelo = new Modelo();
		// Creamos las vistas
		Login login = new Login();
		Home home = new Home();
		InformacionExtra infoExtra = new InformacionExtra();
		Informes informes = new Informes();
		CrearUsuario crearUser = new CrearUsuario();
		Gestion gestion = new Gestion();
		GestionAcad gestionAcad = new GestionAcad();
		GestionActividad gestionActividad = new GestionActividad();
		GestionActores gestionActores = new GestionActores();
		GestionAlumnos gestionAlumnos = new GestionAlumnos();
		GestionRegistros gestionRegistros = new GestionRegistros();
		GestionAsignatura gestionAsignaturas = new GestionAsignatura();
		GestionProfesores gestionProfesores = new GestionProfesores();
		GestionSalas gestionSalas = new GestionSalas();
		GestionUsuarios gestionUsuarios = new GestionUsuarios();
		Perfil perfil = new Perfil();
		VerGrupos verGrupos = new VerGrupos();
		//

		// Asignamos el controlador a las vistas
		controlador.setLogin(login);
		controlador.setHome(home);
		controlador.setInfoExtra(infoExtra);
		controlador.setInformes(informes);
		controlador.setCrearUsuario(crearUser);
		controlador.setGestion(gestion);
		controlador.setGestionAcad(gestionAcad);
		controlador.setGestionActividad(gestionActividad);
		controlador.setGestionActores(gestionActores);
		controlador.setGestionAlumnos(gestionAlumnos);
		controlador.setGestionRegistros(gestionRegistros);
		controlador.setGestionAsignatura(gestionAsignaturas);
		controlador.setGestionProfesores(gestionProfesores);
		controlador.setGestionSalas(gestionSalas);
		controlador.setGestionUsuarios(gestionUsuarios);
		controlador.setPerfil(perfil);
		controlador.setVerGrupos(verGrupos);

		// Asignamos las vistas al controlador
		login.setControlador(controlador);
		home.setControlador(controlador);
		infoExtra.setControlador(controlador);
		informes.setControlador(controlador);
		crearUser.setControlador(controlador);
		gestion.setControlador(controlador);
		gestionAcad.setControlador(controlador);
		gestionActividad.setControlador(controlador);
		gestionActores.setControlador(controlador);
		gestionAlumnos.setControlador(controlador);
		gestionRegistros.setControlador(controlador);
		gestionAsignaturas.setControlador(controlador);
		gestionProfesores.setControlador(controlador);
		gestionSalas.setControlador(controlador);
		gestionUsuarios.setControlador(controlador);
		perfil.setControlador(controlador);
		verGrupos.setControlador(controlador);

		// Vista de nuestro login
		login.setVisible(true);

		// Creariamos el modelo que le pasa la vista de login como parámetro
		modelo.setLogin(login);
		// Asignariamos el modelo a las vistas		modelo.setLogin(login);
		modelo.setHome(home);
		modelo.setInfoExtra(infoExtra);
		modelo.setInformes(informes);
		modelo.setCrearUsuario(crearUser);
		modelo.setGestion(gestion);
		modelo.setGestionAcad(gestionAcad);
		modelo.setGestionActividad(gestionActividad);
		modelo.setGestionActores(gestionActores);
		modelo.setGestionAlumnos(gestionAlumnos);
		modelo.setGestionRegistros(gestionRegistros);
		modelo.setGestionAsignatura(gestionAsignaturas);
		modelo.setGestionProfesores(gestionProfesores);
		modelo.setGestionSalas(gestionSalas);
		modelo.setGestionUsuarios(gestionUsuarios);
		modelo.setPerfil(perfil);
		modelo.setVerGrupos(verGrupos);

		// Asignariamos el modelo al controlador
		modelo.setControlador(controlador);

		// Asignariamos las vistas al modelo
		

	}

}
