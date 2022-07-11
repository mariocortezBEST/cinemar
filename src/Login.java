import java.util.Scanner;

public class Login {

	public Usuario loguearme() {
		
		Scanner leerScanner=new Scanner(System.in);
		String emailString;
		String claveString;
		
		
		System.out.println("Iniciar Sesion");
		System.out.print("Email:");
		emailString=leerScanner.nextLine();
		
		System.out.print("Clave:");
		claveString=leerScanner.nextLine();
		
		Crud c=new Crud("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/cinemar","root","154199jt");
		
		if(c.conectar()) {
			String registroString=c.select("select clave,tipo from Usuarios where email='"+emailString+"';");
			if(registroString.equalsIgnoreCase("")) {
				System.out.println("El usuario "+emailString+" no se encuentra registrado. Registrese.");
				return null;
			}
			else {
				//System.out.println(registroString);
				registroString=registroString.substring(0, registroString.length()-1);
				String[] cString=registroString.split(",");
				
			
				if(cString[0].compareTo(claveString.trim())==0) {
					
					return new Usuario(emailString,claveString,cString[1]);
				}
				else {
					System.out.println("El usuario o clave es incorrecto");
					return null;
				}
			}
		}
		else return null;
		
		
	}
	
}
