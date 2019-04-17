package pt.unl.fct.di.apdc.firstwebapp.util;

public class LoginData {
	
	public String username;
	public String password;
	
	/*
	 * este construtor vazio e essencial pq esta classe vai ter de ser
	 * instanciada automaticamente
	 * tambem por este motivo, as variaveis de instancia tem d ser publicas
	 */
	public LoginData() {}
	
	public LoginData(String username, String password) {
		this.username = username;
		this.password = password;
	}

}
