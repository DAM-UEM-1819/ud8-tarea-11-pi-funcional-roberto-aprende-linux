package controlador;

import modelo.Modelo;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;
import vista.*;

public class Main {

	public static void main(String[] args) {
		Controlador controlador = new Controlador();
		Modelo modeloConsultas = new Modelo();
		ModeloConsultas modeloConsultas = new ModeloConsultas();
		ModeloGestionDatos modeloGestionDatos = new ModeloGestionDatos();
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
		
		controlador.setModelo(modeloConsultas);

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
		

		// Creariamos el modelo que le pasa la vista de login como par�metro
		modeloConsultas.setLogin(login);
		// Asignariamos el modelo a las vistas		modelo.setLogin(login);
		modeloConsultas.setHome(home);
		modeloConsultas.setInfoExtra(infoExtra);
		modeloConsultas.setInformes(informes);
		modeloConsultas.setCrearUsuario(crearUser);
		modeloConsultas.setGestion(gestion);
		modeloConsultas.setGestionAcad(gestionAcad);
		modeloConsultas.setGestionActividad(gestionActividad);
		modeloConsultas.setGestionActores(gestionActores);
		modeloConsultas.setGestionAlumnos(gestionAlumnos);
		modeloConsultas.setGestionRegistros(gestionRegistros);
		modeloConsultas.setGestionAsignatura(gestionAsignaturas);
		modeloConsultas.setGestionProfesores(gestionProfesores);
		modeloConsultas.setGestionSalas(gestionSalas);
		modeloConsultas.setGestionUsuarios(gestionUsuarios);
		modeloConsultas.setPerfil(perfil);
		modeloConsultas.setVerGrupos(verGrupos);

		// Asignariamos el modelo al controlador
		modeloConsultas.setControlador(controlador);

		// Asignariamos las vistas al modelo
		login.setModelo(modeloConsultas);
		home.setModelo(modeloConsultas);
		infoExtra.setModelo(modeloConsultas);
		informes.setModelo(modeloConsultas);
		crearUser.setModelo(modeloConsultas);
		gestion.setModelo(modeloConsultas);
		gestionAcad.setModelo(modeloConsultas);
		gestionActividad.setModelo(modeloConsultas);
		gestionActores.setModelo(modeloConsultas);
		gestionAlumnos.setModelo(modeloConsultas);
		gestionRegistros.setModelo(modeloConsultas);
		gestionAsignaturas.setModelo(modeloConsultas);
		gestionProfesores.setModelo(modeloConsultas);
		gestionSalas.setModelo(modeloConsultas);
		gestionUsuarios.setModelo(modeloConsultas);
		perfil.setModelo(modeloConsultas);
		verGrupos.setModelo(modeloConsultas);
		
		login.setVisible(true);
		
		
		

	}

}
