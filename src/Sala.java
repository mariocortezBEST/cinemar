import java.lang.invoke.StringConcatFactory;

public class Sala {

	private int numero;
	private String tipo;
	
	private Butaca[][] butacas;
	private int filas;
	private int columnas;
	
	public Sala(int numero,int filas,int columnas,String tipo) {
		this.numero=numero;
		this.filas=filas;
		this.columnas=columnas;
		this.tipo=tipo;
		
	}
	
	
	
	public void setButaca(int fila,int columna,boolean estado) {
		if(fila>=0 && fila<=this.filas && columna>=0 && columna <=columnas) {
			this.butacas[fila][columna].setEstado(estado);
		}
		else System.out.println("Error");
		
	}
	
	public void mostrar() {
		System.out.println("Numero:"+this.numero);
		System.out.println("Tipo:"+this.tipo);
	}



	public int getNumero() {
		return numero;
	}



	public void setNumero(int numero) {
		this.numero = numero;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public Butaca[][] getButacas() {
		return butacas;
	}



	public void setButacas(Butaca[][] butacas) {
		this.butacas = butacas;
	}



	public int getFilas() {
		return filas;
	}



	public void setFilas(int filas) {
		this.filas = filas;
	}



	public int getColumnas() {
		return columnas;
	}



	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}
	
	

}
