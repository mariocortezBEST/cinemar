
public class Pelicula {
	
	private int id;
	private String nombreString;
	private String generoString;
	private int duracion;
	private String idiomaString;
	private String descripString;
	
	
	
	
	public Pelicula(int id, String nombreString, String generoString, int duracion, String idiomaString,
			String descripString) {
		super();
		this.id = id;
		this.nombreString = nombreString;
		this.generoString = generoString;
		this.duracion = duracion;
		this.idiomaString = idiomaString;
		this.descripString = descripString;
	}
	public Pelicula(String nombreString, String generoString, int duracion, String idiomaString, String descripString) {
		super();
		this.nombreString = nombreString;
		this.generoString = generoString;
		this.duracion = duracion;
		this.idiomaString = idiomaString;
		this.descripString = descripString;
	}
	public String getNombreString() {
		return nombreString;
	}
	public void setNombreString(String nombreString) {
		this.nombreString = nombreString;
	}
	public String getGeneroString() {
		return generoString;
	}
	public void setGeneroString(String generoString) {
		this.generoString = generoString;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getIdiomaString() {
		return idiomaString;
	}
	public void setIdiomaString(String idiomaString) {
		this.idiomaString = idiomaString;
	}
	public String getDescripString() {
		return descripString;
	}
	public void setDescripString(String descripString) {
		this.descripString = descripString;
	}
	
	public String creaQueryInsert() {
		return "insert into Peliculas(fecha,nombre,genero,duracion,idioma,descripcion,enCartelera) values(now(),'"+this.nombreString+"','"+this.generoString+"','"+this.duracion+"','"+this.idiomaString+"','"+this.descripString+"',true);";
	}
	
	public boolean iguales(Pelicula p) {
		if(this.nombreString.equalsIgnoreCase(p.getNombreString())) {
			if(this.generoString.equalsIgnoreCase(p.getGeneroString())) {
				if(this.duracion==p.getDuracion()) {
					if(this.getIdiomaString().equalsIgnoreCase(p.getIdiomaString())) {
						if(this.descripString.equalsIgnoreCase(p.getDescripString())) {
							return true;
						}
						else return false;
					}else return false;
				}else return false;
			}else return false;
		}else return false;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void mostrar() {
		System.out.println("Nombre:"+this.nombreString);
		System.out.println("Genero:"+this.generoString);
		System.out.println("Duracion:"+this.duracion);
		System.out.println("Idioma:"+this.idiomaString);
		System.out.println("Descripcion:"+this.descripString);
	}

}
