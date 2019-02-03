
//import com.mysql.jdbc.Connection;

package operacionesbancariasjdbc;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OperacionesBancoJDBC {

	
    Connection conn1 = null;
    Statement st; //consultas
    public ResultSet rs;
    public PreparedStatement psInsertar;
     public ArrayList<Propietario> props;
     public ArrayList<Operaciones> opes; 
     public ArrayList<CuentaBancaria> cuents;
     
    public OperacionesBancoJDBC() {
         // ABRE UNA CONEXIÓN A UNA BASE DE DATOS QUE SE SUPONE MYSQL Y QUE TIENE LAS TABLAS
        // Y LOS USUARIOS CREADOS SEGÚN ESTE EJEMPLO.
        try {
            //RECUERDA: PARA EJECUTAR ESTE CÓDIGO ES NECESARIO TENER mYSQL FUNCIONANDO Y LAS TABLAS Y USUARIOS CREADOS
            String url1 = "jdbc:mysql://localhost/banco";
            String user = "root";
            String password = "";
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Conectado bd banco");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR:La dirección no es válida o el usuario y clave");
            ex.printStackTrace();
        }
    }
    
    public void cerrar_Conexion (){
       //SE CIERRA LA CONEXIÓN
        try {
        conn1.close();
        
        System.out.println("Cerrar conexión");
      } catch (SQLException ex) {
            System.out.println("ERROR:al cerrar la conexión");
            ex.printStackTrace();
      }
    }
    
    public void InsertarNuevaCuenta(CuentaBancaria cuent, Propietario prop){
        try {

            // CREA UN STATEMENT  PARA UNA CONSULTA SQL INSERT.
            
            conn1.setAutoCommit(false);
            
            String insertar="INSERT INTO cuentas_bancarias (Numero_Cuenta, Propietario, Saldo, DNI) VALUES (?, ?, ?, ?)";
            PreparedStatement st = conn1.prepareStatement(insertar);
            
            st.setString(1, cuent.getNumeroCuenta());
            st.setString(2, cuent.getPropietario());
            st.setInt(3, 0);
            st.setString(4, cuent.getDNI());

            int rs=st.executeUpdate();
            
            insertar="INSERT INTO propietarios (DNI, Usuario, Nombre, Primer_Apellido, Segundo_Apellido, Numero_Secreto) VALUES (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement st2 = conn1.prepareStatement(insertar);
            
            st2.setString(1, prop.getDNI());
            st2.setString(2,  prop.getUsuario());
            st2.setString(3,  prop.getNombre());
            st2.setString(4,  prop.getPrimerApellido());
            st2.setString(5,  prop.getSegundoApellido());
            st2.setString(6,  prop.getNumeroSecreto());
            
            rs=st2.executeUpdate();
            
            conn1.commit();
           
            st.close();
        } catch (SQLException ex) {
            try {
                conn1.commit();
            } catch (SQLException ex1) {
                Logger.getLogger(OperacionesBancoJDBC.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println("ERROR:al hacer un Insert");
            ex.printStackTrace();
        }
    }
    
    
    public boolean verificarUsuario(Propietario prop, Historial login) throws SQLException{
          
        int cont=0;
        boolean bulean=false;
        
        try {
        
            Statement st = conn1.createStatement();
            String consulta = "SELECT * from propietarios where DNI = '"+prop.getDNI()+"' and Numero_Secreto = '"+prop.getNumeroSecreto()+"'";
            rs=st.executeQuery(consulta);
               
            while(rs.next()){
                cont++;
            }
            
            if(cont==1){
                bulean=true;
            
            String insertar="INSERT INTO historial (Tipo_Evento, Fecha_Hora, Numero_Cuenta) VALUES (?, ?, ? )";
            PreparedStatement ps = conn1.prepareStatement(insertar);
            
            ps.setString(1, login.getTipoEvento());
            ps.setString(2, login.getFechaHora());
            ps.setString(3, login.getNumeroCuenta());
            
            int rs=ps.executeUpdate();
            }
             
            } catch (SQLException ex) {
            System.out.println("ERROR:al hacer un Insert");
            ex.printStackTrace();
        }
            return bulean;
        }
    
    
     public String SacarNumCuentaUsuario(Propietario dni) throws SQLException{
          
        int cont=0;
        String numCuent="";
        
        try {
        
            Statement st = conn1.createStatement();
            String consulta = "SELECT Numero_Cuenta from cuentas_bancarias where DNI = '"+dni.getDNI()+"'";
            rs=st.executeQuery(consulta);
               
            
               while(rs.next()){
               numCuent= rs.getString("Numero_Cuenta");
                }
     
            } catch (SQLException ex) {
            System.out.println("ERROR:al hacer un Insert");
            ex.printStackTrace();
        }
            return numCuent;
        }
     
     
     
     public Operaciones verOperacion(int iden) throws SQLException{
          
        int cont=0;
        String numCuent="", fechaHora="", tipoOperacion="", cantidad="",sid="";
        
       Operaciones ope = null;
        
        try {
        
            Statement st = conn1.createStatement();
            String consulta = "SELECT * from operaciones where ID_Operacion = "+iden;
            rs=st.executeQuery(consulta);
               
               while(rs.next()){
                   
               sid= rs.getString("ID_Operacion");   
               int id =Integer.parseInt(sid);
               fechaHora= rs.getString("Fecha_hora");
               numCuent= rs.getString("Num_Cuenta");
               tipoOperacion= rs.getString("Tipo_Operacion");
               cantidad= rs.getString("Cantidad");
               int icantidad =Integer.parseInt(cantidad);
               
               ope = new Operaciones(id,fechaHora,numCuent,tipoOperacion,icantidad);
                }
     
            } catch (SQLException ex) {
            System.out.println("ERROR:al hacer un Insert");
            ex.printStackTrace();
        }
            return ope;
        }
     
     
     
     
     public ArrayList<Operaciones> verOperaciones(CuentaBancaria numCuenta) throws SQLException{
          
        int cont=0;
        String numCuent="", fechaHora="", tipoOperacion="", cantidad="", sid="";
        
        ArrayList<Operaciones> opes = new ArrayList();
        
        try {
        
            Statement st = conn1.createStatement();
            String consulta = "SELECT * from operaciones where Num_Cuenta = '"+numCuenta.getNumeroCuenta()+"'";
            rs=st.executeQuery(consulta);
               
            
               while(rs.next()){
                   
               sid= rs.getString("ID_Operacion");   
               int id =Integer.parseInt(sid);
               fechaHora= rs.getString("Fecha_hora");
               numCuent= rs.getString("Num_Cuenta");
               tipoOperacion= rs.getString("Tipo_Operacion");
               cantidad= rs.getString("Cantidad");
               
               int icantidad =Integer.parseInt(cantidad);
               
               Operaciones ope = new Operaciones(id,fechaHora,numCuent,tipoOperacion,icantidad);
               
               opes.add(ope);
               
                }
     
            } catch (SQLException ex) {
            System.out.println("ERROR:al hacer un Insert");
            ex.printStackTrace();
        }
            return opes;
        }
     
     
     public ArrayList<Operaciones> verOperaciones() throws SQLException{
          
        int cont=0;
        String numCuent="", fechaHora="", tipoOperacion="", cantidad="",sid="";
        
        ArrayList<Operaciones> opes = new ArrayList();
        
        try {
        
            Statement st = conn1.createStatement();
            String consulta = "SELECT * from operaciones";
            rs=st.executeQuery(consulta);
               
            
               while(rs.next()){
                   
               sid= rs.getString("ID_Operacion");   
               int id =Integer.parseInt(sid);
               fechaHora= rs.getString("Fecha_hora");
               numCuent= rs.getString("Num_Cuenta");
               tipoOperacion= rs.getString("Tipo_Operacion");
               cantidad= rs.getString("Cantidad");
               int icantidad =Integer.parseInt(cantidad);
               
               Operaciones ope = new Operaciones(id,fechaHora,numCuent,tipoOperacion,icantidad);
               
               opes.add(ope);
               
                }
     
            } catch (SQLException ex) {
            System.out.println("ERROR:al hacer un Insert");
            ex.printStackTrace();
        }
            return opes;
        }
     
     
     public int verMiRanking(Propietario dni) throws SQLException{
          
        int cont=0;
                
        try {
        
            Statement st = conn1.createStatement();
            String consulta = "SELECT * from cuentas_bancarias order by Saldo";
            rs=st.executeQuery(consulta);
              
               while(rs.next()){
                   cont++;
                  if(dni.getDNI().equals(rs.getString("DNI"))){
                      break;
                  }                
                }
     
            } catch (SQLException ex) {
            System.out.println("ERROR:al hacer un Insert");
            ex.printStackTrace();
        }
            return cont;
        }
     
     
     
     public Historial verFechaLogin(Historial fecha) throws SQLException{
          
        int cont=0;
        Historial fech=null; 
        
        try {
        
            Statement st = conn1.createStatement();
            String consulta = "SELECT * from historial where Fecha_Hora = '"+fecha.getFechaHora()+"'";
            rs=st.executeQuery(consulta);
              
               while(rs.next()){
                   cont++;
                String fe= rs.getString("Fecha_Hora");
                      
                  fech = new Historial(fe);                
                }
     
            } catch (SQLException ex) {
            System.out.println("ERROR:al hacer un Insert");
            ex.printStackTrace();
        }
            return fech;
        }
     
     
     
     public ArrayList<Operaciones> generadoNumerosRojos() throws SQLException{
          
        int cont=0;
        String numCuent="", fechaHora="", tipoOperacion="", cantidad="", sid="";
        
        ArrayList<Operaciones> opes = new ArrayList();
        
        try {
        
            Statement st = conn1.createStatement();
            String consulta = "SELECT * from historial, Operaciones where historial.Tipo_Evento = 'R' and historial.Fecha_Hora = Operaciones.Fecha_hora";
            rs=st.executeQuery(consulta);
            
               while(rs.next()){
               
               sid= rs.getString("ID_Operacion");   
               int id =Integer.parseInt(sid);
               fechaHora= rs.getString("Fecha_hora");
               numCuent= rs.getString("Num_Cuenta");
               tipoOperacion= rs.getString("Tipo_Operacion");
               cantidad= rs.getString("Cantidad");
               
               int icantidad =Integer.parseInt(cantidad);
               
               Operaciones ope = new Operaciones(id,fechaHora,numCuent,tipoOperacion,icantidad);
               
               opes.add(ope);
               
                }
     
            } catch (SQLException ex) {
            System.out.println("ERROR:al hacer un Insert");
            ex.printStackTrace();
        }
            return opes;
        }
     
     
     
     public ArrayList<CuentaBancaria> rankingNumeroRojos() throws SQLException{
          
        int cont=0;
        String numCuent="", propietario="", saldo="", dni="";
        
        ArrayList<CuentaBancaria> cuents = new ArrayList();
        
        try {
        
            Statement st = conn1.createStatement();
            String consulta = "SELECT * from cuentas_bancarias where Saldo < 0 order by Saldo";
            rs=st.executeQuery(consulta);
               
            
               while(rs.next()){
                   
               numCuent= rs.getString("Numero_Cuenta");
               propietario= rs.getString("Propietario");
               saldo= rs.getString("Saldo");
               dni= rs.getString("DNI");
               
               int isaldo =Integer.parseInt(saldo);
               
               CuentaBancaria cuent = new CuentaBancaria(numCuent,propietario,isaldo,dni);
               
               cuents.add(cuent);
               
                }
     
            } catch (SQLException ex) {
            System.out.println("ERROR:al hacer un Insert");
            ex.printStackTrace();
        }
            return cuents;
        }
     
     
     
     public void hacerOperacion(Operaciones operacion, Propietario dni, Historial event) throws SQLException{
          
        boolean bulean =false; 
        String saldo="";
        int iSaldo=0;
        
        try {
        
            Statement st = conn1.createStatement();
            String consulta = "SELECT Saldo from cuentas_bancarias where DNI = '"+dni.getDNI()+"'";
            rs=st.executeQuery(consulta);
               
               while(rs.next()){
               saldo= rs.getString("Saldo");
                
               iSaldo= Integer.parseInt(saldo);
               
                if(iSaldo>=0){
                    bulean=true;
                }
               }
               
              iSaldo= Integer.parseInt(saldo);
               
              String insertar="";
              
               if(operacion.getTipoOperacion().equals("e")){
                   iSaldo = iSaldo - operacion.getCantidad();                  
               }
               else{
                   iSaldo = iSaldo + operacion.getCantidad();
                                      
               }
               
               
            
            conn1.setAutoCommit(false);
               
            insertar="INSERT INTO historial (Tipo_Evento, Fecha_Hora, Numero_Cuenta) VALUES (?, ?, ?)";
   
            PreparedStatement pt = conn1.prepareStatement(insertar);
            
            pt.setString(1, event.getTipoEvento());
            pt.setString(2, event.getFechaHora());
            pt.setString(3, event.getNumeroCuenta());
               
            int rs2=pt.executeUpdate();
               
            
            insertar="INSERT INTO operaciones (Fecha_hora, Num_Cuenta, Tipo_Operacion, Cantidad) VALUES (?, ?, ?, ?)";
            
            PreparedStatement pt2 = conn1.prepareStatement(insertar);
            
            pt2.setString(1, operacion.getFechaHora());
            pt2.setString(2, operacion.getNumeroCuenta());
            pt2.setString(3, operacion.getTipoOperacion());
            pt2.setInt(4, operacion.getCantidad());
           
            int rs3=pt2.executeUpdate();
               
            conn1.commit();
            
            
            st.executeUpdate("UPDATE cuentas_bancarias SET Saldo='"+iSaldo+"' WHERE DNI='"+dni.getDNI()+"'");
            
            Statement st2 = conn1.createStatement();
            consulta = "SELECT Saldo from cuentas_bancarias where DNI = '"+dni.getDNI()+"'";
            rs=st2.executeQuery(consulta);
            
            while(rs.next()){
               saldo= rs.getString("Saldo");
                
               iSaldo= Integer.parseInt(saldo);
               
               if(iSaldo<0&&bulean){
                                      
                   insertar="INSERT INTO historial (Tipo_Evento, Fecha_Hora, Numero_Cuenta) VALUES (?, ?, ?)";
                   
                   PreparedStatement pt3 = conn1.prepareStatement(insertar);
                   
                   pt3.setString(1, "R");
                   pt3.setString(2, event.getFechaHora());
                   pt3.setString(3, event.getNumeroCuenta());
                   
                   rs2=pt3.executeUpdate();
                   
               }
            }
            
            st.close();   

            
            } catch (SQLException ex) {
                conn1.rollback();
            System.out.println("ERROR:al hacer un Insert");
            ex.printStackTrace();
        }
           
        }
    
    
    
     public void ActualizarDatosPropietario(Propietario prop, Propietario dni){
        try {
            // CREA UN STATEMENT PARA UNA CONSULTA SQL UPDATE.
            Statement st = conn1.createStatement();
            st.executeUpdate("UPDATE propietarios SET Nombre='"+prop.getNombre()+"',Primer_Apellido='"+prop.getPrimerApellido()+"', Segundo_Apellido='"+prop.getSegundoApellido()+"' WHERE DNI='"+dni.getDNI()+"'");
            st.close();
        } catch (SQLException ex) {
            System.out.println("ERROR:al hacer un Update");
            ex.printStackTrace();
        }
    }
     
     
     public void ActualizarNumeroSecreto(Propietario prop){
        try {
            // CREA UN STATEMENT PARA UNA CONSULTA SQL UPDATE.
            Statement st = conn1.createStatement();
            st.executeUpdate("UPDATE propietarios SET Numero_Secreto='"+prop.getNumeroSecreto()+"' WHERE DNI='"+prop.getDNI()+"'");
            st.close();
        } catch (SQLException ex) {
            System.out.println("ERROR:al hacer un Update");
            ex.printStackTrace();
        }
    }
     
     
     public void BorrarCuenta(Propietario dni){
        try {
            conn1.setAutoCommit(false);
            
            Statement sta = conn1.createStatement();
            
            sta.executeUpdate("DELETE FROM propietarios WHERE DNI = '"+dni.getDNI()+"'");
            sta.executeUpdate("DELETE FROM cuentas_bancarias WHERE DNI = '"+dni.getDNI()+"'");
            
            conn1.commit();
            
            sta.close();
        } catch (SQLException ex) {
            try {
                conn1.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OperacionesBancoJDBC.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println("ERROR:al hacer un Delete");
            ex.printStackTrace();
        }
    }
    
}
