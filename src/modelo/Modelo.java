package modelo;

import java.awt.Label;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

import jxl.Workbook;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

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
	private String cuerpoSaludo;
	private String cuerpoUsuario;
	private String cuerpoPasswd;
	private String cuerpoDespedida;

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
		cuerpoSaludo = propiedades.getProperty("cuerpoSaludo");
		cuerpoUsuario = propiedades.getProperty("cuerpoUsuario");
		cuerpoPasswd = propiedades.getProperty("cuerpoPasswd");
		cuerpoDespedida = propiedades.getProperty("cuerpoDespedida");
	}

	/**
	 * Este metodo envia un correo electr�nico al destinatario con su usario y su
	 * contrase�a Primero configuramos los datos esenciales, despues creamos el
	 * mensaje
	 * 
	 * @param destinatario El destinatario del correo
	 * @param user         El usuario creado
	 * @param passwd       La contrase�a generada
	 */
	public void enviarCorreoGmail(String destinatario, String user, String passwd) {
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
			mensaje.setText(
					cuerpoSaludo + "\n" + cuerpoUsuario + user + "\n" + cuerpoPasswd + passwd + "\n" + cuerpoDespedida);
			Transport transport = sesion.getTransport("smtp");
			transport.connect(servidor, remitente, clave);
			transport.sendMessage(mensaje, mensaje.getAllRecipients());
			transport.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Este m�todo genera una contrase�a aleatoria con letras y numeros con una
	 * longitud de 9 caracteres Selecciona al azar 3 datos de los 3 tipos y los suma
	 * a la cadena
	 * 
	 * @return la contrase�a generada
	 */
	public String generadorPasswd() {
		String passwd = "";
		final String numeros = "0123456789";
		final String mayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String minusculas = mayusculas.toLowerCase();

		for (int i = 0; i < 3; i++) {
			passwd += numeros.charAt((int) (Math.random() * numeros.length()));
			passwd += mayusculas.charAt((int) (Math.random() * mayusculas.length()));
			;
			passwd += minusculas.charAt((int) (Math.random() * minusculas.length()));
			;
		}

		return passwd;
	}

	/**
	 * Metodo que sirve para encriptar una contraseña dada
	 * 
	 * @param passwd La contraseña a encriptar
	 * @return La contraseña encriptada
	 */

	public String generarMD5(String passwd) {

		try {
			// Indicamos el algoritmo a utilizar
			MessageDigest md = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);

			// Convertimos la contrase�a a bytes y se la pasamos al MessageDigest
			md.update(passwd.getBytes());

			// La contrase�a en un array de bytes
			byte[] digest = md.digest();

			// Lo codificamos en base 64.
			byte[] encoded = Base64.encodeBase64(digest);

			// Convertimos la codificacion a un String
			passwd = new String(encoded);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return passwd;

	}

	public void generarExcel() {
		// ESTABLECEMOS LA RUTA
		final String RUTA = System.getProperty("user.home");

		// RECOGEMOS LOS INFORMES CON TODOS LOS DATOS DEL MODELO CONSULTAS
		ArrayList<String[][]> informes = modeloConsultas.getTodosInformesConDatos();

		// INICIALIZAMOS UN OBJETO DE TIPO WritableWorkbook QUE NOS PERMITIRA CREAR EL
		// EXCEL
		WritableWorkbook excel = null;

		int contador = 1;

		try {

			// CREAMOS EL EXCEL
			excel = Workbook.createWorkbook(new File(RUTA));

		} catch (IOException e) {
			e.printStackTrace();
		}

		// ITERAMOS LOS INFORMES
		for (String[][] strings : informes) {

			// CREAMOS LA HOJA DE EXCEL
			WritableSheet hojaExcel = excel.createSheet("Hoja " + contador, 0);
			contador++;

			for (int row = 0; row < strings.length; row++) {
				for (int col = 0; col < strings[row].length; col++) {

					System.out.println(strings[row][col]);

					// CREAMOS LA LABEL PARA AÑADIR EL VALOR QUE SE ENCUENTRA EN LA FILA X Y LA
					// COLUMNA Y
					jxl.write.Label label = new jxl.write.Label(row, col, strings[row][col]);

					try {

						// AÑADIMOS LA CELDA A LA HOJA EXCEL
						hojaExcel.addCell(label);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

}
