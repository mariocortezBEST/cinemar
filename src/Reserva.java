import java.util.ArrayList;


public class Reserva {
	
	private Cliente cliente;
	private Pelicula pelicula;
	private Sala sala;
	private ArrayList<Butaca> butacas;
	private String fechaString;
	private String horaString;
	private int funcion_id;
	private int butaca_id;
	
	
	
	public Reserva(Cliente cliente, Pelicula pelicula, Sala sala,String fechaString,String hora) {
		super();
		this.cliente = cliente;
		this.pelicula = pelicula;
		this.sala = sala;
		this.butacas=new ArrayList<Butaca>();
		this.fechaString=fechaString;
		this.horaString=hora;
		
	}
	
	public Reserva() {
		this.cliente=new Cliente();
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public ArrayList<Butaca> getButacas() {
		return butacas;
	}
	public void setButacas(ArrayList<Butaca> butacas) {
		this.butacas = butacas;
	}

	public String getFechaString() {
		return fechaString;
	}

	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}

	public String getHoraString() {
		return horaString;
	}

	public void setHoraString(String horaString) {
		this.horaString = horaString;
	}

	public int getFuncion_id() {
		return funcion_id;
	}

	public void setFuncion_id(int funcion_id) {
		this.funcion_id = funcion_id;
	}

	public String queryInsert() {
		return "insert into reservas (cliente_id,fecha,funcion_id,butaca_id) values ("+this.cliente.getId()+",now()"+","+this.funcion_id+","+this.butaca_id+");";
	}

	public int getButaca_id() {
		return butaca_id;
	}

	public void setButaca_id(int butaca_id) {
		this.butaca_id = butaca_id;
	}
	
	
}
