package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {
    
    String db = "DB_Riga";
    String usuario = "Riga";
    String password = "databaseriga";
    
    Connection con;
    
    public Connection getConexionSQL(){
        String url = "jdbc:sqlserver://localhost:1433;"
                + "databaseName="+db+";"
                + "user="+usuario+";"
                + "password="+password+";"
                + "encrypt=true;trustServerCertificate=true;";
        
        try{
            con = DriverManager.getConnection(url);
            return con;
        }catch(SQLException e){
            System.out.println(e.toString());
            return null;
        }
    }
    
    public static void main(String[] args) {
        
        ConexionSQL conexion = new ConexionSQL();
        
        conexion.getConexionSQL();
    }
}
