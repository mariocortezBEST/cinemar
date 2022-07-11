
public class Usuario {

	private String emailString;
	private String claveString;
	private String tipoString;
	
	public Usuario(String emailString, String claveString,String tipoString) {
		super();
		this.emailString = emailString;
		this.claveString = claveString;
		this.tipoString=tipoString;
	}
	
	public Usuario(String emaiString,String clave) {
		
	}
	
	public String getEmailString() {
		return emailString;
	}
	public void setEmailString(String emailString) {
		this.emailString = emailString;
	}
	public String getClaveString() {
		return claveString;
	}
	public void setClaveString(String claveString) {
		this.claveString = claveString;
	}
	
	public String creaQueryInsert() {
		return "insert into Usuarios (email,clave,tipo) values ('"+this.emailString+"','"+this.claveString+"','"+this.tipoString+"');";
	}
	public String getTipoString() {
		return tipoString;
	}
	public void setTipoString(String tipoString) {
		this.tipoString = tipoString;
	}
	
}
