package Controlador;

import Modelo.Cliente;
import Conexion.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JTable;

public class CtrlCliente {
    
    ConexionSQL SQL = new ConexionSQL();
    
    Connection cn = SQL.getConexionSQL();
    
    PreparedStatement ps;
    
    //Metodo Registrar
    public void RegistrarCliente(Cliente cliente){
        String sql = "INSERT INTO cliente VALUES (?, ?, ?, ?, ?, ?)";
        
        try{
            ps = cn.prepareStatement(sql);
            
            ps.setString(1,cliente.getNombre_cliente());
            ps.setString(2,cliente.getApellido_cliente());
            ps.setString(3,cliente.getDni_cliente());
            ps.setString(4,cliente.getDireccion_cliente());
            ps.setString(5,cliente.getTelefono_cliente());
            ps.setString(6,cliente.getCorreo_cliente());
            
            ps.executeUpdate();

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error: "+e.toString());
        }
    }
    
    public void EliminarCliente(Cliente cliente){
        String sql = "DELETE FROM cliente Where id_cliente = ?";
        
        try{
            ps = cn.prepareStatement(sql);
            
            ps.setInt(1,(cliente.getId_cliente()));
            
            ps.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error: "+e.toString());
        }
    }
    
    public void EditarCliente(Cliente cliente){
        String sql = "UPDATE cliente SET nombre=?, apellido = ?, dni = ?, direccion = ?, telefono = ?, correo = ? WHERE id_cliente = ?";
        
        try{
            ps = cn.prepareStatement(sql);

            ps.setString(1,cliente.getNombre_cliente());
            ps.setString(2,cliente.getApellido_cliente());
            ps.setString(3,cliente.getDni_cliente());
            ps.setString(4,cliente.getDireccion_cliente());
            ps.setString(5,cliente.getTelefono_cliente());
            ps.setString(6,cliente.getCorreo_cliente());
            ps.setInt(7,cliente.getId_cliente());
            
            ps.executeUpdate();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error: "+e.toString());
        }
    }
    
    public void MostrarCliente(JTable tabla){
        String sql = "SELECT * FROM cliente";

        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        
        model.setRowCount(0);
 
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            String [] datoCliente = new String[7];
            
            while(rs.next()){
                datoCliente[0] = rs.getString(1);
                datoCliente[1] = rs.getString(2);
                datoCliente[2] = rs.getString(3);
                datoCliente[3] = rs.getString(4);
                datoCliente[4] = rs.getString(5);
                datoCliente[5] = rs.getString(6);
                datoCliente[6] = rs.getString(7);
                
                model.addRow(datoCliente);
            }
            tabla.setModel(model);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error: "+e.toString());
        }
    }
}
