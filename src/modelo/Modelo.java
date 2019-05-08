package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.Properties;
import java.util.TreeSet;

import javax.swing.table.DefaultTableModel;

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

	// Atributos Fichero
	private Properties propiedades;
	private InputStream entrada;
	private OutputStream salida;
	private File fichero;

	// Atributos internos
	private int contador;
	private String respuesta;
	private DefaultTableModel tableModel;
	private String[] nombreColumnasTabla;
	private Object[] datosFilasTabla;
	private String rolUsuario;
	private TreeSet<String> listadoGrupos;
	private String grupo;

	// Sentencia Select SQL LOGIN
	private String selectPasswdUsuario = "SELECT PWD, ROL FROM HOSPITAL.USERS WHERE USR = ?";

	// Sentencias Select SQL TABLAS
	private String selectHome = "SELECT distinct sala.tipo_sala as Sala,  SUBSTR(registro.hora, 10, 5) as Inicio, SUBSTR(registro.hora, 10, 5) as Fin, actividad.tipo_actividad as actividad, asignatura.titulacion as titulacion, matricula.cod_grupo as grupo, profesor.nombre || profesor.apellido1 as Profesor FROM HOSPITAL.sala, HOSPITAL.registro, HOSPITAL.ocupa, HOSPITAL.actividad, HOSPITAL.asignatura, HOSPITAL.matricula, HOSPITAL.realiza, HOSPITAL.profesor where registro.cod_registro = ocupa.registro_cod_registro  and ocupa.cod_sala = sala.cod_sala  and registro.actividad_nombre = actividad.nombre and actividad.asignatura_codigo = asignatura.codigo and asignatura.codigo = matricula.asignatura_codigo and realiza.registro_cod_registro = registro.cod_registro and realiza.profesor_numero  = profesor.numero";
	private String selectTodosUsuarios = "SELECT USR as Usuario, ROL as Rol FROM HOSPITAL.USERS";
	private String selectTodosRegistros = "SELECT cod_registro , SUBSTR(fecha,1,9) as Fecha , horas_profesor , actividad_nombre FROM HOSPITAL.Registro";
	private String selectTodosAlumnos = "SELECT * FROM HOSPITAL.alumno";
	private String selectTodasActividades = "SELECT nombre , tipo_actividad , tipo_sala, simulador , documentacion_tecnica , horas_actividad , acad  FROM hospital.actividad";
	private String selectTodasAsignaturas = "SELECT codigo , nombre , titulacion , curso FROM HOSPITAL.asignatura";
	private String selectTodosProfesores = "SELECT numero , nombre || apellido1 || apellido2 as Nombre , titulacion , dni, activo, relacion_laboral,tlf1,tlf2 , mail1, mail2 from HOSPITAL.profesor";
	private String selectTodosActores = "SELECT  cod_actor , nombre , edad , genero , idioma , complexion , activo FROM HOSPITAL.actor";
	private String selectTodasSalas = "SELECT cod_sala , tipo_sala , numero , capacidad FROM HOSPITAL.sala";
	private String selectTodosAcad = "SELECT * FROM HOSPITAL.acad";

	private String selectTodosCodigoGrupo = "SELECT cod_grupo FROM HOSPITAL.matricula";

	// Sentencias Select SQL LISTADOS
	private String selectListadoAlumnosPorGrupo = "SELECT  cod_grupo, nombre FROM HOSPITAL.matricula, HOSPITAL.alumno where alumno.exp = matricula.alumno_exp AND cod_grupo = ?";

	// Sentencias Select SQL BUSCADOR
	private String selectBuscadorHome = "SELECT USR, ROL FROM HOSPITAL.USERS WHERE USR = ? or ROL= ?";
	private String selectBuscadorUsuarios = "SELECT USR, ROL FROM HOSPITAL.USERS WHERE USR = ? or ROL= ?";
	private String selectBuscadorRegistros = "SELECT cod_registro, fecha, horas_profesor, actividad_nombre FROM HOSPITAL.REGISTRO WHERE cod_registro = ? or fecha= ? or horas_profesor=? or actividad_nombre=?";
	private String selectBuscadorActividades = "SELECT nombre , tipo_actividad , tipo_sala, simulador , documentacion_tecnica , horas_actividad , acad  FROM hospital.actividad WHERE nombre=? or tipo_actividad=? or tipo_sala=? or simulador=? or documentacion_tecnica=? or horas_actividad=? or acad=?";
	private String selectBuscadorAsignatura = "SELECT codigo , nombre , titulacion , curso FROM HOSPITAL.asignatura WHERE codigo=? or nombre=? or titulacion=? or curso=?";
	private String selectBuscadorAlumnos = "SELECT * FROM HOSPITAL.alumno WHERE exp=? or nombre=? or activo=?";
	private String selectBuscadorProfesores = "SELECT * from HOSPITAL.profesor WHERE numero=? or nombre=? or apellido1=? or apellido2=? or titulacion=? or dni=? or activo=? or relacion_laboral=? or tlf1=? or tlf2=? or mail1=? or mail2=?";
	private String selectBuscadorActores = "SELECT cod_actor, nombre, edad, genero, idioma, complexion, activo FROM HOSPITAL.actor WHERE cod_actor=? or nombre=? or edad=? or genero=? or idioma=? or complexion=? or activo=?";
	private String selectBuscadorSalas = "SELECT cod_sala , tipo_sala , numero , capacidad FROM HOSPITAL.sala WHERE cod_sala=? or tipo_sala=? or numero=? or capacidad=?";
	private String selectBuscadorAcad = "SELECT * FROM HOSPITAL.acad WHERE acad=? or sem1=? or sem2=?";

	// Sentencias Insertado SQL
	private String insertUsuario = "INSERT INTO HOSPITAL.users (usr, pwd, rol) VALUES (?,?,?)";

	// Sentencias Borrado SQL

	public Modelo() {
		propiedades = new Properties();
		fichero = new File("./conf/configuracion.ini");

		try {
			entrada = new FileInputStream(fichero);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			propiedades.load(entrada);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		baseDatos = propiedades.getProperty("baseDatos");
		usuarioDB = propiedades.getProperty("usuario");
		passwdDB = propiedades.getProperty("passwd");
		urlDB = propiedades.getProperty("url");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection(urlDB, usuarioDB, passwdDB);
			System.out.println("La conexion ha sido exitosa");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("La conexion ha fallado");
			e.printStackTrace();
		}

	}

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

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	// INICIO GETTERS

	public String getRespuesta() {
		return respuesta;
	}
	
	public String getGrupo() {
		return grupo;
	}

	// INICIO METODOS BASE DATOS

	public void loginConfirmacion(String usuario, String passwd) {
		String sql = selectPasswdUsuario;
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			pstmt.setString(1, usuario);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next() && rs.getString(1).equals(passwd)) {
				// CAmbiar solo a ADMIN
				if (rs.getString(2).equalsIgnoreCase("Admin") || rs.getString(2).equalsIgnoreCase("Administrador")) {
					login.loginExitoso();
				} else {
					login.loginExitosoLectura();
				}

			} else {
				contador++;
				if (contador >= 3) {
					login.salir();
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void crearUsuario(String user, String passwd, String rol) {
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
	
	public void getTablaHome(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectHome;
		getDatos(sql);
	}

	public void getTablaUsuarios(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosUsuarios;
		getDatos(sql);
	}

	public void getTablaAcad(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosAcad;
		getDatos(sql);
	}

	public void getTablaActividad(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodasActividades;
		getDatos(sql);
	}

	public void getTablaActores(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosActores;
		getDatos(sql);
	}

	public void getTablaAlumnos(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosAlumnos;
		getDatos(sql);
	}

	public void getTablaAsignatura(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodasAsignaturas;
		getDatos(sql);
	}

	public void getTablaProfesores(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosProfesores;
		getDatos(sql);
	}

	public void getTablaRegistros(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodosRegistros;
		getDatos(sql);
	}

	public void getTablaSalas(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		String sql = selectTodasSalas;
		getDatos(sql);
	}

	public void listadoGrupos() {
		String sql = selectTodosCodigoGrupo;
		listadoGrupos = new TreeSet<String>();
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				listadoGrupos.add(rs.getString(1));
			}

			for (String elemento : listadoGrupos) {
				grupo = elemento;
				verGrupos.addGrupo();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getListadoAlumnosPorGrupo(DefaultTableModel tableModel, String grupo) {
		this.tableModel = tableModel;
		String sql = selectListadoAlumnosPorGrupo;

		if (!grupo.equalsIgnoreCase("Selecciona un grupo")) {
			tableModel.setColumnCount(0);
			tableModel.setRowCount(0);
			try {
				PreparedStatement pstmt = conexion.prepareStatement(sql);
				pstmt.setString(1, grupo);
				ResultSet rs = pstmt.executeQuery();
				ResultSetMetaData metadatos = rs.getMetaData();

				int numColumnas = metadatos.getColumnCount();
				datosFilasTabla = new Object[numColumnas];

				for (int i = 0; i < numColumnas; i++) {
					tableModel.addColumn(metadatos.getColumnName(i + 1));
				}

				// metadatos.getColumnName(1);

				while (rs.next()) {
					for (int i = 0; i < numColumnas; i++) {
						datosFilasTabla[i] = rs.getObject(i + 1);
					}
					tableModel.addRow(datosFilasTabla);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void getDatos(String sql) {
		tableModel.setColumnCount(0);
		tableModel.setRowCount(0);
		try {
			PreparedStatement pstmt = conexion.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData metadatos = rs.getMetaData();

			int numColumnas = metadatos.getColumnCount();
			datosFilasTabla = new Object[numColumnas];

			for (int i = 0; i < numColumnas; i++) {
				tableModel.addColumn(metadatos.getColumnName(i + 1));
			}

			// metadatos.getColumnName(1);

			while (rs.next()) {
				for (int i = 0; i < numColumnas; i++) {
					datosFilasTabla[i] = rs.getObject(i + 1);
				}
				tableModel.addRow(datosFilasTabla);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
