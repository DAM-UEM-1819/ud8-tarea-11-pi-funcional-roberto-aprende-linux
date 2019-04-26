package modelo;

public interface IModelo {

	public void loginConfirmacion(String usuario, String passwd);
	public void crearUsuario(String user, String passwd, String rol);
}
