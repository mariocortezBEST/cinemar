import java.util.Scanner;

public class Registro {
	
	public boolean registrarme() {
		
		String nombreString="";
		String apellString="";
		String emailString="";
		String claveString="";
		String fecha_nacimientoString="";
		String celularString="";
		
		String[] campos= {"Nombre *:","Apellido *:","Fecha de Nacimiento *(DD/MM/AAAA):","Celular *:","Email *:","Clave *:"};
		String [] datoStrings=new String[6];
		Scanner leerScanner=new Scanner(System.in);
		
		
		
		System.out.println("Formulario de Registro");
		System.out.println("* Campos Obligatorios");
		
		for(int i=0;i<campos.length;i++) {
			System.out.print(campos[i]);
			datoStrings[i]=leerScanner.nextLine();
			System.out.println();
		}
		
		
		
		
		leerScanner.close();
		
		Crud c=new Crud("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/cinemar","root","154199jt");
		
	
		
		if(c.conectar()==false) {
			System.out.println("Error en la conexion...");
		}
		else {
				String registroString;
				registroString=c.select("select email from Clientes where email='"+datoStrings[4]+"';");
			//	System.out.println("select email from Clientes where email like '"+datoStrings[4]+"';");
				if(registroString.equalsIgnoreCase("")) {
					Cliente nuevoCliente=new Cliente(datoStrings[0],datoStrings[1],datoStrings[2],datoStrings[3],datoStrings[4]);
					Usuario nuevoUsuario=new Usuario(datoStrings[4], datoStrings[5], "C");
					
					if(c.insertar(nuevoCliente.Query())==false) {
						System.out.println("Error Intente mas tarde");
					}
					else {
						if(c.insertar(nuevoUsuario.creaQueryInsert())) {
							System.out.println("Felicidades Usted ya se encuentra registrado. Ingrese nuevamente");
						}
						else System.out.println("Error");
					}
						
						
					
				}
				else System.out.println("El email ya se encuentra registrado");
		}
	
		
		c.cerrarConexion();
		
		return true;
	}

}
