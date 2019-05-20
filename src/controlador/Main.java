package controlador;

import modelo.Modelo;
import modelo.ModeloConsultas;
import modelo.ModeloGestionDatos;
import vista.*;

public class Main {

	public static void main(String[] args) {
		Controlador controlador = new Controlador();
		Modelo modelo = new Modelo();
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

		// CONTROLADOR - VISTAS
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
		
		controlador.setModelo(modelo);
		controlador.setModeloConsultas(modeloConsultas);
		controlador.setModeloGestionDatos(modeloGestionDatos);

		// VISTAS - CONTROLADOR
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

		//MODELO CONSULTAS
		modeloConsultas.setLogin(login);
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
		modeloConsultas.setModelo(modelo);
		modeloConsultas.setModeloGestionDatos(modeloGestionDatos);
		
		// MODELO GESTION DATOS
		modeloGestionDatos.setModelo(modelo);
		modeloGestionDatos.setControlador(controlador);
		modeloGestionDatos.setModeloConsultas(modeloConsultas);
		modeloGestionDatos.setCrearUsuario(crearUser);
		modeloGestionDatos.setGestionAcad(gestionAcad);
		modeloGestionDatos.setGestionActividad(gestionActividad);
		modeloGestionDatos.setGestionActores(gestionActores);
		modeloGestionDatos.setGestionAlumnos(gestionAlumnos);
		modeloGestionDatos.setGestionRegistros(gestionRegistros);
		modeloGestionDatos.setGestionAsignatura(gestionAsignaturas);
		modeloGestionDatos.setGestionProfesores(gestionProfesores);
		modeloGestionDatos.setGestionSalas(gestionSalas);
		modeloGestionDatos.setGestionUsuarios(gestionUsuarios);
		modeloGestionDatos.setPerfil(perfil);

		// Asignariamos el modelo al controlador
		modeloConsultas.setControlador(controlador);

		// VISTAS - MODELO CONSULTAS
		login.setModeloConsultas(modeloConsultas);
		home.setModeloConsultas(modeloConsultas);
		infoExtra.setModeloConsultas(modeloConsultas);
		informes.setModeloConsultas(modeloConsultas);
		crearUser.setModeloConsultas(modeloConsultas);
		gestion.setModeloConsultas(modeloConsultas);
		gestionAcad.setModeloConsultas(modeloConsultas);
		gestionActividad.setModeloConsultas(modeloConsultas);
		gestionActores.setModeloConsultas(modeloConsultas);
		gestionAlumnos.setModeloConsultas(modeloConsultas);
		gestionRegistros.setModeloConsultas(modeloConsultas);
		gestionAsignaturas.setModeloConsultas(modeloConsultas);
		gestionProfesores.setModeloConsultas(modeloConsultas);
		gestionSalas.setModeloConsultas(modeloConsultas);
		gestionUsuarios.setModeloConsultas(modeloConsultas);
		perfil.setModeloConsultas(modeloConsultas);
		verGrupos.setModeloConsultas(modeloConsultas);
		
		//VISTAS - MODELO GESTION DATOS
		gestionAcad.setModeloGestionDatos(modeloGestionDatos);
		gestionActividad.setModeloGestionDatos(modeloGestionDatos);
		gestionActores.setModeloGestionDatos(modeloGestionDatos);
		gestionAlumnos.setModeloGestionDatos(modeloGestionDatos);
		gestionRegistros.setModeloGestionDatos(modeloGestionDatos);
		gestionAsignaturas.setModeloGestionDatos(modeloGestionDatos);
		gestionProfesores.setModeloGestionDatos(modeloGestionDatos);
		gestionSalas.setModeloGestionDatos(modeloGestionDatos);
		gestionUsuarios.setModeloGestionDatos(modeloGestionDatos);
		perfil.setModeloGestionDatos(modeloGestionDatos);
		
		//ASIGNAMOS LA CONEXION AL MODELO GESTION DATOS
		modeloGestionDatos.setConexion(modelo.getConexion());
		login.setVisible(true);
		
		
		

	}

}
