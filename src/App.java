import java.lang.invoke.StringConcatFactory;
import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.Scanner;

import javax.management.relation.InvalidRelationServiceException;

import com.mysql.cj.protocol.a.NativeConstants.StringLengthDataType;



public class App {
	static Scanner leer = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int op=menu();
		
		switch (op) {
		case 1:
			//System.out.println("Voy a iniciar sesion");
			Login login=new Login();
			Usuario usuario=login.loguearme();
			
			Crud crud=new Crud("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/cinemar" ,"root","154199jt");
			
			
			if(usuario!=null) {
				if(usuario.getTipoString().equalsIgnoreCase("c")) {
					System.out.println("Bienvenido a Cinemar...");
					
					switch (menuCliente()){
					case 1:
						System.out.println("Email:"+usuario.getEmailString());
					break;
					case 2:
						System.out.println("Reserva");
						
						ArrayList <Pelicula> peliculas=new ArrayList<Pelicula>();
						
						
						Crud c=new Crud("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/cinemar","root","154199jt");
						if(c.conectar()) {
							String r=c.select("select pelicula_id,nombre,genero,duracion,idioma,descripcion from Peliculas where enCartelera=true;");
							
							String [] registros=r.split(";");
							
							String [] registro;
							Reserva nuevaReserva=new Reserva();
							
							c.cerrarConexion();
							for(int i=0;i<registros.length;i++) {
								registro=registros[i].split(",");
								peliculas.add(new Pelicula(Integer.parseInt(registro[0]), registro[1], registro[2],Integer.parseInt(registro[3]),registro[4],registro[5]));
							}
							
							System.out.println("Peliculas en Cartelera");
							for(int i=0;i<peliculas.size();i++) {
								System.out.println(i);
								peliculas.get(i).mostrar();
							}
							int o;
							System.out.print("Ingrese el numero de la pelicula que desea reservar:");
							
							o=leer.nextInt();
							
							nuevaReserva.setPelicula(peliculas.get(o));
							
							if(c.conectar()) {
								r=c.select("select * from Funciones where pelicula_id="+peliculas.get(o).getId()+" and fecha>=current_date() order by fecha,hora;");
								//c.cerrarConexion();
								registros=r.split(";");
								
								System.out.println("hola");
								//ArrayList<Funcion> funciones=new ArrayList<Funcion>(); 
								
								for(int i=0;i<registros.length;i++) {
									registro=registros[i].split(",");
									System.out.println(i+".Fecha:"+registro[3]);
									System.out.println("   Hora: "+registro[4]);
									
								}
								
								System.out.print("Ingrese la opcion de la fecha:");
								o=leer.nextInt();
								nuevaReserva.setFuncion_id(Integer.parseInt(registros[o].split(",")[0]));
								nuevaReserva.setFechaString(registros[o].split(",")[3]);
								nuevaReserva.setHoraString(registros[o].split(",")[4]);
								r=c.select("select * from salas where sala_id="+registros[o].split(",")[1]);
								r=r.substring(0, r.length()-1);
								nuevaReserva.setSala(new Sala(Integer.parseInt(r.split(",")[0]), Integer.parseInt(r.split(",")[2]), Integer.parseInt(r.split(",")[3]), r.split(",")[1]));
								
								String cString=c.select("select cliente_id from Clientes where email='"+usuario.getEmailString()+"';");
								
								
								nuevaReserva.getCliente().setId(Integer.parseInt(cString.substring(0, cString.length()-1)));
								
								r=c.select("select * from butacas where sala_id="+nuevaReserva.getSala().getNumero());
								String [] butacasStrings=r.split(";");
								String [] butacaString;
								r=c.select("select butaca_id from reservas where funcion_id="+nuevaReserva.getFuncion_id());
								String [] bucatacasComprada=r.split(";");
								
								for(int i=0;i<butacasStrings.length;i++) {
									butacaString=butacasStrings[i].split(",");
									int j=0;
									while(j<bucatacasComprada.length && bucatacasComprada[j].equalsIgnoreCase(butacaString[0])==false) {
										j++;
									}
									
									if(j>=bucatacasComprada.length) {
										
										System.out.println(i+".["+butacaString[3]+"]["+butacaString[4]+"]");
									}
									
								}
								System.out.print("Ingrese una opcion:");
								o=leer.nextInt();
								nuevaReserva.setButaca_id(Integer.parseInt(butacasStrings[o].split(",")[0]));
								//System.out.println(nuevaReserva.queryInsert());
								if(c.insertar(nuevaReserva.queryInsert())){
									System.out.println("La reserva se realizo con exito!");
								}
								else System.out.println("Error intente mas tarde...");
								
								
							}
							
							
							
						}
						
					break;
					case 3:
						if(crud.conectar()) {
							String cliente=crud.select("select cliente_id from clientes where email='"+usuario.getEmailString()+"';");
							cliente=cliente.substring(0,cliente.length()-1);
							String s=crud.select("select reservas.fecha,peliculas.nombre,funciones.sala_id,funciones.fecha,funciones.hora  from reservas inner join funciones using(funcion_id) inner join peliculas using(pelicula_id) where cliente_id="+cliente);
							
							if(s.equalsIgnoreCase("")==false) {
								String [] reservas=s.split(";");
								
								for(int i=0;i<reservas.length;i++) {
									System.out.println("Fecha de Transaccion:"+reservas[i].split(",")[0]);
									System.out.println("Pelicula:"+reservas[i].split(",")[1]);
									System.out.println("Sala:"+reservas[i].split(",")[2]);
									System.out.println("Fecha de la funcion:"+reservas[i].split(",")[3]);
									System.out.println("Hora de la funcion:"+reservas[i].split(",")[4]);
								}
								
							}
							else System.out.println("Nada para mostrar");
							
						}
					break;
					default:
						System.out.println("Usted esta saliendo de Cinemar");
					}
				}
				else {
						System.out.println("Comencemos a trabajar");
						switch (menuAdministrador()) {
						case 1: 
							System.out.println("Clientes");
							
							crud.conectar();
							
							String [] registros=crud.select("select * from Clientes;").split(";");
							String [] registro;
							ArrayList <Cliente> clientes=new ArrayList<Cliente>();
						
							
							for(int i=0;i<registros.length;i++) {
								registro=registros[i].split(",");
								clientes.add(new Cliente(Integer.parseInt(registro[0]),registro[1],registro[2],registro[3],registro[4],registro[5]));
							}
							
							if(clientes.size()!=0) {
								for(int i=0;i<clientes.size();i++) {
									clientes.get(i).mostrar();
								}
							}
							else System.out.println("No hay nada para mostrar");
							
						break;
						case 2:
						break;
						case 3:
							System.out.println("Formulario de Registro Funcion");
							RegistrarFuncion rf=new RegistrarFuncion();
							
							if(rf.registrar()) {
								System.out.println("Registro se realizo con existo!");
							}
							else System.out.println("Error al registrar la funcion...");
							
						break;
						
						case 4:
							System.out.println("Formulacio de Registro Pelicula");
							RegistrarPelicula npPelicula=new RegistrarPelicula();
							String rta="";
							
							do {
								if(npPelicula.registrarPeli()) {
									System.out.println("La carga fue exitosa...");
									System.out.println("Desea seguir agregando?[(s/si)|(n/no)");
									rta=leer.nextLine();
								}
								else {
									System.out.println("Error en la carga...");
									rta="n";
								}
								
							}while(rta.equalsIgnoreCase("s"));
							
						break;
						default:
							
						}
				}
				
			}
			else System.out.println("Error");
					
			break;
		case 2:
			Registro registro=new Registro();
			registro.registrarme();
			break;
		default:
			System.out.println("Saliendo de Cinemar");
		}

	}
	
	public static int menu() {
		
		int rta;
		
		do {
			System.out.println("1. Iniciar sesion");
			System.out.println("2. Registrarme");
			System.out.println("0. Salir");
			System.out.print("Ingrese Opcion:");
			rta=leer.nextInt();
			if(rta<0 || rta >2) {
				System.out.println("Ingrese una opcion valida...");
			}
		}while(rta<0 || rta >2);
		
		return rta;
		
	}
	
	public static int menuCliente() {
		
		
		int rta;
		
		do {
			System.out.println("1. Ver usuario");
			System.out.println("2. Reservar");
			System.out.println("3. Ver Historial");
			
			System.out.println("0. Salir");
			System.out.print("Ingrese Opcion:");
			rta=leer.nextInt();
			if(rta<0 || rta >3) {
				System.out.println("Ingrese una opcion valida...");
			}
		}while(rta<0 || rta >3);
		
		return rta;
		
		
	}
	
	public static int menuAdministrador() {
int rta;
		
		do {
			System.out.println("1. Ver Clientes");
			System.out.println("2. Ver Reservas");
			System.out.println("3. Registrar con Funcion");
			System.out.println("4. Registrar con Peliculas");
			System.out.println("0. Salir");
			System.out.print("Ingrese Opcion:");
			rta=leer.nextInt();
			if(rta<0 || rta >4) {
				System.out.println("Ingrese una opcion valida...");
			}
		}while(rta<0 || rta >4);
		
		return rta;
		
	}

}
