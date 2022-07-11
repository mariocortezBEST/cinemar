
public class Funcion {
	private String fechaString;
	private Pelicula pelicula;
	private String hora;
	private Sala sala;
	
	public Funcion(String fechaString, Pelicula pelicula, String hora, Sala sala) {
		super();
		this.fechaString = fechaString;
		this.pelicula = pelicula;
		this.hora = hora;
		this.sala = sala;
	}
	
	public Funcion() {}
	
	public String getFechaString() {
		return fechaString;
	}

	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	public void mostrar() {
		System.out.println("Fecha:"+this.fechaString);
		System.out.println("Hora:"+this.hora);
		System.out.println("Pelicula:"+this.pelicula.getNombreString());
		System.out.println("Genero:"+this.pelicula.getGeneroString());
		System.out.println("Sala:"+this.sala.getNumero());
		System.out.println("Tipo:"+this.sala.getTipo());
	}
	
	public String crearQueryInsert() {
		return "insert into funciones (sala_id,pelicula_id,fecha,hora) values ("+this.sala.getNumero()+","+this.pelicula.getId()+",'"+this.fechaString+"','"+this.hora+"');";
	}
}
