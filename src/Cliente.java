

public class Cliente {
	
	private int id;
	private String nombreString="";
	private String apellString="";
	private String emailString="";
	private String fecha_nacimientoString="";
	private String celularString="";
	public Cliente(String nombreString, String apellString,String fecha_nacimientoString,String celularString, String emailString 
			) {
		super();
		this.nombreString = nombreString;
		this.apellString = apellString;
		this.emailString = emailString;
		this.fecha_nacimientoString = fecha_nacimientoString;
		this.celularString = celularString;
	}
	
	
	public Cliente(int id, String nombreString, String apellString, String emailString, String fecha_nacimientoString,
			String celularString) {
		super();
		this.id = id;
		this.nombreString = nombreString;
		this.apellString = apellString;
		this.emailString = emailString;
		this.fecha_nacimientoString = fecha_nacimientoString;
		this.celularString = celularString;
	}
	
	public Cliente() {}

	public String getNombreString() {
		return nombreString;
	}
	public void setNombreString(String nombreString) {
		this.nombreString = nombreString;
	}
	public String getApellString() {
		return apellString;
	}
	public void setApellString(String apellString) {
		this.apellString = apellString;
	}
	public String getEmailString() {
		return emailString;
	}
	public void setEmailString(String emailString) {
		this.emailString = emailString;
	}
	public String getFecha_nacimientoString() {
		return fecha_nacimientoString;
	}
	public void setFecha_nacimientoString(String fecha_nacimientoString) {
		this.fecha_nacimientoString = fecha_nacimientoString;
	}
	public String getCelularString() {
		return celularString;
	}
	public void setCelularString(String celularString) {
		this.celularString = celularString;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void mostrar() {
		System.out.println("Nombre:"+this.nombreString);
		System.out.println("Apellido:"+this.apellString);
		System.out.println("Fecha de Nacimiento:"+this.fecha_nacimientoString);
		System.out.println("Celular:"+this.celularString);
		System.out.println("Email:"+this.emailString);
	}
	
	public String Query() {
		return "insert into Clientes (fecha,nombre,apellido,fecha_nacimiento,celular,email) values (now(),'2" + nombreString + "','" + apellString + "','"+fecha_nacimientoString + "','"+ celularString+"','"+emailString+ "')";
	}
	
	
	

}
