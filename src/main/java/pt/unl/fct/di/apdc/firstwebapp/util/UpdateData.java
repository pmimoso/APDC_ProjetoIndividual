package pt.unl.fct.di.apdc.firstwebapp.util;

public class UpdateData {
	
	public String username;
	public String role;
	public String email;
	public String profileType;
	public int homePhone;
	public int mobilePhone;
	public String address;
	public String altAddress;
	public String postalCode;
	public String password;
	public String city;
	
	/*
	 * este construtor vazio e essencial pq esta classe vai ter de ser
	 * instanciada automaticamente
	 * tambem por este motivo, as variaveis de instancia tem d ser publicas
	 */
	public UpdateData() {}
	
	public UpdateData(String username, String role, String email, String profileType, int homePhone, int mobilePhone, String address, String altAddress, String postalCode, String city) {
		this.username = username;
		this.role = role;
		this.email = email;
		this.profileType = profileType;
		this.homePhone = homePhone;
		this.mobilePhone = mobilePhone;
		this.address = address;
		this.altAddress = altAddress;
		this.postalCode = postalCode;
		this.city = city;
	}
	
	public UpdateData(String username, String password, String role, String email, String profileType, int homePhone, int mobilePhone, String address, String altAddress, String postalCode, String city) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
		this.profileType = profileType;
		this.homePhone = homePhone;
		this.mobilePhone = mobilePhone;
		this.address = address;
		this.altAddress = altAddress;
		this.postalCode = postalCode;
		this.city = city;
	}

}
