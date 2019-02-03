/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacionesbancariasjdbc;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Paco
 */
public class TestJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
     OperacionesBancoJDBC conn = new OperacionesBancoJDBC();
     
     int elec = 0, elecUsuario=0;
     
     CuentaBancaria bonc = new CuentaBancaria();
     Propietario prep = new Propietario();
                 
     int cont=0;
     String numCuenta="",dni="",usuario="",numSecreto="",nombre="",primerApellido="",segundoApellido="";
                 

     do{
         
         System.out.println("==========================");
         System.out.println("1- Insertar una nueva cuenta bancaria");
         System.out.println("2- Entrar en una cuenta bancaria");
         System.out.println("3- Lista de todas las operaciones bancarias");
         System.out.println("4- Lista de operaciones bancarias que han generado números rojos");
         System.out.println("5- Ranking de cuentas en números rojos");
         System.out.println("6- Obtener operación bancaria a partir de su ID");
         System.out.println("0- Salir");
         System.out.println("==========================");
         elec= Datos.entero();
         
         switch(elec){
             case 1:
                
                 
                 do{
                     System.out.println("Introduce el número de cuenta (formato xxxx-xxxx-xxxx-xxxx)");
                     numCuenta= Datos.cadena();
                     cont= bonc.comprobarNumCuenta(numCuenta); 
                 }while(cont!=0); 
                do{
                    System.out.println("Introduce el DNI (formato xxxxxxxxA)");
                    dni= Datos.cadena();
                    cont= bonc.comprobarDNI(dni);
                }while(cont!=0);
                do{
                    System.out.println("Introduce el Nombre de usuario");
                    usuario= Datos.cadena();
                    cont= prep.comprobarUsuario(usuario);
               }while(cont!=0); 
                do{
                    System.out.println("Introduce el número secreto (4 dígitos)");
                    numSecreto= Datos.cadena();
                    cont= prep.comprobarNumSecreto(numSecreto);
                }while(cont!=0); 
                do{
                    System.out.println("Introduce el nombre");
                    nombre= Datos.cadena();
                    cont= prep.comprobarNombreApellidos(nombre);
                }while(cont!=0);
                do{
                    System.out.println("Introduce el primer apellido");
                    primerApellido= Datos.cadena();
                    cont= prep.comprobarNombreApellidos(primerApellido);
                }while(cont!=0);
                do{
                    System.out.println("Introduce el segundo apellido");
                    segundoApellido= Datos.cadena();
                    cont= prep.comprobarNombreApellidos(segundoApellido);
                 }while(cont!=0);
                
                
                CuentaBancaria banc = new CuentaBancaria(numCuenta,usuario,0,dni);
                Propietario prop = new Propietario(dni,usuario,nombre,primerApellido,segundoApellido,numSecreto);
                
                conn.InsertarNuevaCuenta(banc, prop);
                
                System.out.println("La cuenta bancaria ha sido creada correctamente");
                
                 break;
                 
             case 2: 
                 
                do{
                    System.out.println("Introduce el DNI (formato xxxxxxxxA)");
                    dni= Datos.cadena();
                    cont= bonc.comprobarDNI(dni);
                }while(cont!=0); 
                do{
                    System.out.println("Introduce el número secreto (4 dígitos)");
                    numSecreto= Datos.cadena();
                    cont= prep.comprobarNumSecreto(numSecreto);
                }while(cont!=0); 
                
                Propietario val = new Propietario(dni, numSecreto);
                Propietario dn = new Propietario(dni);
                
                DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now2 = LocalDateTime.now();
                String fecha=  dt.format(now2).toString();
                
                String numcuenta= conn.SacarNumCuentaUsuario(dn);
                
                Historial login = new Historial("L",fecha,numcuenta);
                
                boolean bulean=conn.verificarUsuario(val, login);
                 
                Historial fech = new Historial(fecha);
                
                if(bulean){
                    
                    
                    CuentaBancaria account = new CuentaBancaria(numcuenta);
                    
                    System.out.println("HA ENTRADO CORRECTAMENTE A SU CUENTA BANCARIA");
                    
                    
                 do{    
                   System.out.println("-------------------------------");  
                   System.out.println("1- Modificar datos del propietario");
                   System.out.println("2- Cambiar número secreto");
                   System.out.println("3- Eliminar cuenta bancaria");
                   System.out.println("4- Hacer una operación bancaria");
                   System.out.println("5- Ver mis operaciones bancarias");
                   System.out.println("6- Ver fecha de mi último inicio de sesión");
                   System.out.println("7- Ver mi posición en el ranking de cuentas");
                   System.out.println("0- Volver");
                   System.out.println("-------------------------------");
                   elecUsuario= Datos.entero();  
                   
                   switch(elecUsuario){
                       
                       case 1:
                           
                           do{
                             System.out.println("Introduce el nuevo nombre");
                             nombre= Datos.cadena();
                             cont= prep.comprobarNombreApellidos(nombre);
                          }while(cont!=0);
                           do{
                              System.out.println("Introduce el nuevo primer apellido");
                              primerApellido= Datos.cadena();
                              cont= prep.comprobarNombreApellidos(primerApellido);
                            }while(cont!=0);
                           do{
                              System.out.println("Introduce el nuevo segundo apellido");
                              segundoApellido= Datos.cadena();
                              cont= prep.comprobarNombreApellidos(segundoApellido);
                           }while(cont!=0);
                
                           Propietario actu = new Propietario(nombre,primerApellido, segundoApellido);
                           conn.ActualizarDatosPropietario(actu, dn);
                           
                           System.out.println("Se han actualizado los datos del propietario correctamente");
                           
                           break;
                           
                       case 2:
                       
                           do{
                             System.out.println("Introduce el nuevo número secreto (4 dígitos)");
                             numSecreto= Datos.cadena();
                             cont= prep.comprobarNumSecreto(numSecreto);
                            }while(cont!=0);
                           
                           conn.ActualizarNumeroSecreto(val);
                           
                           break;
                           
                       case 3:
                           
                           conn.BorrarCuenta(dn);
                           
                           System.out.println("La cuenta bancaria ha sido eliminada");
                           
                           elecUsuario=0;
                           break;
                         
                       case 4:
                           
                           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                           LocalDateTime now = LocalDateTime.now();
                         
                          String fecha2=  dtf.format(now).toString();
                           
                           System.out.println("e- hacer una extracción");
                            System.out.println("i- hacer un ingreso");
                            
                           String ope= Datos.cadena();
                           
                           if(ope.equals("e")){
                               
                               System.out.println("Introduce la cantidad que quieres extraer");
                               int ext= Datos.entero();
                               
                               Historial extraccion = new Historial("E",fecha2,numcuenta);
                               Operaciones operacion = new Operaciones(fecha2, numcuenta, ope, ext);   
                               conn.hacerOperacion(operacion, dn, extraccion);
                               
                               System.out.println("Se ha hecho la extracción correctamente");
                           }
                           else if(ope.equals("i")){
                               
                               System.out.println("Introduce la cantidad que quieres ingresar");
                               int ing= Datos.entero();
                           
                               Historial ingreso = new Historial("I",fecha2,numcuenta);
                               Operaciones operacion = new Operaciones(fecha, numcuenta, ope, ing);
                               conn.hacerOperacion(operacion, dn, ingreso);
                               
                               System.out.println("Se ha hecho el ingreso correctamente");
                           }
                           else{
                               System.out.println("Tienes que introducir una opción correcta");
                           }
                           
                           break;
                           
                       case 5:
                           
                         System.out.format("%-4s%-23s%-23s%-7s%-7s","#","NumCuenta","Fecha","Tipo","Cantidad");

                         System.out.println("");
                         System.out.println("============================================================");
                         System.out.println(""); 
                         
                          for(int i=0; i< conn.verOperaciones(account).size();i++){
                              System.out.format("%-4s%-23s%-23s%-7s%-7s\n",
                              conn.verOperaciones(account).get(i).getId(),
                              conn.verOperaciones(account).get(i).getNumeroCuenta(),
                              conn.verOperaciones(account).get(i).getFechaHora(),
                              conn.verOperaciones(account).get(i).getTipoOperacion(),
                              conn.verOperaciones(account).get(i).getCantidad());

                              System.out.println(""); 
                          }
                           
                           break;
                           
                       case 6:
                           
                           System.out.println(conn.verFechaLogin(fech).getFechaHora());
                           
                           
                           break;
                           
                       case 7:
                           
                          int pos= conn.verMiRanking(dn);
                           
                          System.out.println("Mi posición dentro del ranking de cuentas es "+pos);
                           
                           break;
                           
                       case 0:
                           
                           break;
                       
                   }
                   
                   
                 }while(elecUsuario!=0);

                 
                }
                else{
                    System.err.println("El DNI o el numero secreto no coinciden");
                }
                
                break;
                 
             case 3:
                 
                 System.out.format("%-4s%-23s%-23s%-7s%-7s","#","NumCuenta","Fecha","Tipo","Cantidad");

                         System.out.println("");
                         System.out.println("============================================================");
                         System.out.println(""); 
                         
                          for(int i=0; i< conn.verOperaciones().size();i++){
                              System.out.format("%-4d%-23s%-23s%-7s%-7s\n",
                              conn.verOperaciones().get(i).getId(),
                              conn.verOperaciones().get(i).getNumeroCuenta(),
                              conn.verOperaciones().get(i).getFechaHora(),
                              conn.verOperaciones().get(i).getTipoOperacion(),
                              conn.verOperaciones().get(i).getCantidad());

                              System.out.println(""); 
                          }
                 
                 
                 break;
                 
             case 4:
                 
                 System.out.format("%-4s%-23s%-23s%-7s%-7s","#","NumCuenta","Fecha","Tipo","Cantidad");

                         System.out.println("");
                         System.out.println("============================================================");
                         System.out.println(""); 
                         
                          for(int i=0; i< conn.generadoNumerosRojos().size();i++){
                              System.out.format("%-4s%-23s%-23s%-7s%-7s\n",
                              conn.generadoNumerosRojos().get(i).getId(),
                              conn.generadoNumerosRojos().get(i).getNumeroCuenta(),
                              conn.generadoNumerosRojos().get(i).getFechaHora(),
                              conn.generadoNumerosRojos().get(i).getTipoOperacion(),
                              conn.generadoNumerosRojos().get(i).getCantidad());

                              System.out.println(""); 
                          }
                 
                 
                 break;
                 
             case 5:
                 
                 
                 System.out.format("%-4s%-23s%-15s%-7s%-7s","#","NumCuenta","Propietario","Saldo","DNI");

                         System.out.println("");
                         System.out.println("============================================================");
                         System.out.println(""); 
                         
                          for(int i=0; i< conn.rankingNumeroRojos().size();i++){
                              System.out.format("%-4d%-23s%-15s%-7s%-7s\n",i+1,
                              conn.rankingNumeroRojos().get(i).getNumeroCuenta(),
                              conn.rankingNumeroRojos().get(i).getPropietario(),
                              conn.rankingNumeroRojos().get(i).getSaldo(),
                              conn.rankingNumeroRojos().get(i).getDNI());

                              System.out.println(""); 
                          }
                 
                 
                 break;
                 
                 
             case 6:
                 
                 System.out.println("Introduce el ID(#) de la operación");
                 int id= Datos.entero();
                 
                 System.out.format("%-4s%-23s%-23s%-7s%-7s","#","NumCuenta","Fecha","Tipo","Cantidad");

                         System.out.println("");
                         System.out.println("============================================================");
                         System.out.println(""); 
                 
                         if(conn.verOperacion(id)!=null){
                         System.out.format("%-4s%-23s%-23s%-7s%-7s",
                                 conn.verOperacion(id).getId(),
                                 conn.verOperacion(id).getNumeroCuenta(),
                                 conn.verOperacion(id).getFechaHora(),
                                 conn.verOperacion(id).getTipoOperacion(),
                                 conn.verOperacion(id).getCantidad());
                         System.out.println("");
                         }
                 break;
         
                
             case 0: 
                 System.out.println("se ha salido de la aplicación");
                    
         }
         
     }while(elec!=0);
     
     
     conn.cerrar_Conexion();
     

        
        
    }
}
