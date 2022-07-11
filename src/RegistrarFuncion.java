import java.util.ArrayList;
import java.util.Scanner;

public class RegistrarFuncion {

	public boolean registrar() {
		 boolean rta=true;
		 Scanner leerScanner=new Scanner(System.in);
		 ArrayList<Pelicula> peliculas=new ArrayList<Pelicula>();
		 Crud c=new Crud("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/cinemar","root","154199jt");
		c.conectar();
		 String string=c.select("select * from Peliculas;");
		 String [] registros=string.split(";");
		 String [] registro;
		 
		
		 for(int i=0;i<registros.length;i++) {
				registro=registros[i].split(",");
				peliculas.add(new Pelicula(Integer.parseInt(registro[0]), registro[2], registro[3],Integer.parseInt(registro[4]),registro[5],registro[6]));
		 }
		 
		 for(int i=0;i<peliculas.size();i++) {
			 System.out.println("----------------------");
			 System.out.println("         "+i+"        ");
			 peliculas.get(i).mostrar();
			 System.out.println("______________________");
			 
		 }
		 System.out.print("Ingrese opcion:");
		 string=leerScanner.nextLine();
		 
		 Funcion funcion=new Funcion();
		 
		 funcion.setPelicula(peliculas.get(Integer.parseInt(string)));
		 
		 string=c.select("select * from Salas;");
		 
		 String [] registrosSalas=string.split(";");
		 String [] registroSala;
		 
		
		 ArrayList <Sala> salas=new ArrayList<Sala>();
		 for(int i=0;i<registrosSalas.length;i++) {
				registroSala=registrosSalas[i].split(",");
				salas.add(new Sala(Integer.parseInt(registroSala[0]),Integer.parseInt(registroSala[2]),Integer.parseInt(registroSala[3]),registroSala[1]));
		 }
		 
		 
		 for(int i=0;i<salas.size();i++) {
			 System.out.println("----------------------");
			 System.out.println("         "+i+"        ");
			 salas.get(i).mostrar();
			 System.out.println("______________________");
			 
		 }
		 System.out.print("Ingrese opcion:");
		 string=leerScanner.nextLine();
		 
		 funcion.setSala(salas.get(Integer.parseInt(string)));
		 
		 System.out.print("Ingrese Fecha de la funcion (AAAA-MM-DD):");
		 string=leerScanner.nextLine();
		 funcion.setFechaString(string);
		 System.out.print("Ingrese Hora (HH:MM:00):");
		 string=leerScanner.nextLine();
		 funcion.setHora(string);
		 
		 if(c.insertar(funcion.crearQueryInsert())) {
			 rta=false;
		 }
		 
		return rta;
	}
}
