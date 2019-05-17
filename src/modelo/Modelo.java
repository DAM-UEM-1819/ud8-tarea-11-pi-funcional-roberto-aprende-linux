package modelo;

import java.io.File;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

public class Modelo {

	// Atributos para relacionar
	private ModeloConsultas modeloConsultas;
	private ModeloGestionDatos modeloGestionDatos;

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

	// Atributos correo electrónico
	private String remitente;
	private String servidor;
	private String clave;
	private String autenticacion;
	private String conexionSegura;
	private String puerto;
	private String asunto;
	private String cuerpo;

	/**
	 * Constructor que recoge los datos de la conexion a la BBDD y realiza la
	 * conexion con la BBDD
	 */
	public Modelo() {
		conexionBBDD();
		getDatosEmail();
	}

	// INICIO SETTERS

	public void setModeloConsultas(ModeloConsultas modeloConsultas) {
		this.modeloConsultas = modeloConsultas;
	}

	public void setModeloGestionDatos(ModeloGestionDatos modeloGestionDatos) {
		this.modeloGestionDatos = modeloGestionDatos;
	}

	// INICIO GETTERS

	public Connection getConexion() {
		return conexion;
	}

	/**
	 * Metodo que recoge los datos de conexion de configuracion.ini
	 */
	private void conexionBBDD() {
		propiedades = new Properties();
		fichero = new File("./conf/configuracion.ini");

		try {
			entrada = new FileInputStream(fichero);
			propiedades.load(entrada);
		} catch (Exception e) {
			e.printStackTrace();
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

	/**
	 * Metodo que recoge los datos de configuracion relacionados con el email de
	 * email.ini
	 */
	public void getDatosEmail() {
		propiedades = new Properties();
		fichero = new File("./conf/email.ini");

		try {
			entrada = new FileInputStream(fichero);
			propiedades.load(entrada);
		} catch (Exception e) {
			e.printStackTrace();
		}

		remitente = propiedades.getProperty("remitente");
		servidor = propiedades.getProperty("servidor");
		clave = propiedades.getProperty("clave");
		autenticacion = propiedades.getProperty("autenticacion");
		conexionSegura = propiedades.getProperty("conexionSegura");
		puerto = propiedades.getProperty("puerto");
		asunto = propiedades.getProperty("asunto");
		cuerpo = propiedades.getProperty("cuerpo");
	}

	public void enviarCorreoGmail(String destinatario,String user, String passwd) {
		Properties propiedades = System.getProperties();
		
		propiedades.put("mail.smtp.host", servidor);
		propiedades.put("mail.smtp.user", remitente);
		propiedades.put("mail.smtp.clave", clave);
		propiedades.put("mail.smtp.auth", autenticacion);
		propiedades.put("mail.smtp.starttls.enable", conexionSegura);
		propiedades.put("mail.smtp.port", puerto);
		
		Session sesion = Session.getDefaultInstance(propiedades);
		MimeMessage mensaje = new MimeMessage(sesion);
		
		try {
			
			mensaje.setFrom(new InternetAddress(remitente));
			mensaje.addRecipients(Message.RecipientType.TO, destinatario);
			mensaje.setSubject(asunto);
			mensaje.setText(cuerpo + user + passwd);
			Transport transport = sesion.getTransport("smtp");
			transport.connect(servidor, remitente, clave);
			transport.sendMessage(mensaje, mensaje.getAllRecipients());
			transport.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
