import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Crud {
	
	private String JDBC_DRIVER;
	private String DB_URL;
	
	private String USER;
	private String PASS;
	private Connection connection;
	private Statement stmt;
	private ResultSet r;
	
	
	public Crud(String jDBC_DRIVER, String dB_URL, String uSER, String pASS) {
		super();
		JDBC_DRIVER = jDBC_DRIVER;
		DB_URL = dB_URL;
		USER = uSER;
		PASS = pASS;
		connection=null;
		stmt=null;
		r=null;
	}
	public String getJDBC_DRIVER() {
		return JDBC_DRIVER;
	}
	public void setJDBC_DRIVER(String jDBC_DRIVER) {
		JDBC_DRIVER = jDBC_DRIVER;
	}
	public String getDB_URL() {
		return DB_URL;
	}
	public void setDB_URL(String dB_URL) {
		DB_URL = dB_URL;
	}
	public String getUSER() {
		return USER;
	}
	public void setUSER(String uSER) {
		USER = uSER;
	}
	public String getPASS() {
		return PASS;
	}
	public void setPASS(String pASS) {
		PASS = pASS;
	}
	
	public boolean conectar() {
		boolean rta=true;
		this.connection=null;
		this.stmt=null;
		
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			rta=false;
		}
		
		
		try {
			connection=DriverManager.getConnection(DB_URL,USER,PASS);
			stmt=connection.createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			rta=false;
		}
		
		return rta;
	}

	public boolean insertar(String query) {
		boolean rta=true;
		try {
			
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			rta=false;
		}
		
		return rta;
	}
	
	
	public String select(String query) {
		 
		 String registros="";
		 ResultSetMetaData rsmd;
		
		try {
			
			this.r=this.stmt.executeQuery(query);
			rsmd=this.r.getMetaData();
			
			while(this.r.next()) {
				
				for(int i=1;i<=rsmd.getColumnCount();i++) {
					//System.out.println("Tipo:"+rsmd.getColumnType(i));
					switch (rsmd.getColumnType(i)) {
					case 12:
						registros=registros+this.r.getString(i);
					break;
					case 4:
						registros=registros+this.r.getInt(i);
					break;
					case 93:
						registros=registros+this.r.getDate(i).toString();
					break;
					case 92:
						registros=registros+this.r.getTime(i).toString();
					break;
					case 91:
						registros=registros+this.r.getDate(i).toString();
					break;
					case -7:
						registros=registros+this.r.getBoolean(i);
					break;
					default:
						System.out.println("Tipo:"+rsmd.getColumnType(i));
					}
					
					if(rsmd.getColumnCount()>1) {
						registros=registros+",";
					}
					
				}
				registros=registros+";";
			}
			
			//System.out.println(registros);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
	System.out.println(e.getMessage());
		}
		
		return registros;
		
	}
	
	public boolean cerrarConexion() {
		boolean rta=true;
		try {
			if(this.stmt!=null) {
				this.stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			rta=false;
		}
		
		
		
			try {
				if(this.connection!=null) {
				this.connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				rta=false;
			}
		
		return rta;
	}
	

}
