
public class Butaca {
	private int fila;
	private int columna;
	private boolean estado;
	
	public Butaca(int fila, int columna, boolean estado) {
		super();
		this.fila = fila;
		this.columna = columna;
		this.estado = estado;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	

	
}
