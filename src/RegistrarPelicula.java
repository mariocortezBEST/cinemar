import java.util.ArrayList;
import java.util.Scanner;

public class RegistrarPelicula {
	
	public boolean registrarPeli() {
		
		boolean rta=true;
		Scanner leeScanner=new Scanner(System.in);
		
		System.out.println("Formulario de Registro para una Pelicula");
		System.out.println("* Campos Obligatorios");
		
		String[] campoStrings= {"Nombre *:","Genero *:","Duracion *:","Idioma *:","Descripcion *:"};
		String[] datoStrings=new String[5];
		
		for(int i=0;i<campoStrings.length;i++) {
			System.out.println(campoStrings[i]);
			datoStrings[i]=leeScanner.nextLine();
		}
		
		Crud c=new Crud("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/cinemar","root","154199jt");
		
		Pelicula nuevaPelicula=new Pelicula(datoStrings[0], datoStrings[1], Integer.parseInt(datoStrings[2]), datoStrings[3], datoStrings[4]);
		
		if(c.conectar()) {
			String registroString=c.select("select * from Peliculas;");
			
			if(registroString.equalsIgnoreCase("")==false) {
				String [] rs=registroString.split(";");
				String [] registro;
				ArrayList<Pelicula> peliculas=new ArrayList<Pelicula>();
				//c.select("select * from Peliculas where nombre='"+datoStrings[0]+"' and genero='"+datoStrings[1]+"' and duracion="+datoStrings[2]+"and idioma='"+datoStrings[3]+"' and descripcion='"+datoStrings[4]+"';");
				
				for(int i=0;i<rs.length;i++) {
					registro=rs[i].split(",");
					peliculas.add(new Pelicula(registro[0], registro[1], Integer.parseInt(registro[2]), registro[3], registro[4]));
					
				}
				
				int i=0;
				
				while(i<peliculas.size() && peliculas.get(i).iguales(nuevaPelicula)==false) {
					i++;
				}
				
				if(i<peliculas.size()) {
					System.out.println("La pelicula "+datoStrings[0]+" ya se encuentra registrada");
				}
				else c.insertar(nuevaPelicula.creaQueryInsert());
			}
			else c.insertar(nuevaPelicula.creaQueryInsert());
			
			
			
		}
		
		return true;
	}

}
