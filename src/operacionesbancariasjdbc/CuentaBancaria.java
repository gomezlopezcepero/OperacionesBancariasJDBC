/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacionesbancariasjdbc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Paco
 */
public class CuentaBancaria {
    
    private String numeroCuenta;
    private String propietario;
    private int saldo;
    private String DNI;

    public CuentaBancaria() {
    }

    public CuentaBancaria(String numeroCuenta, String propietario, int saldo, String DNI) {
        this.numeroCuenta = numeroCuenta;
        this.propietario = propietario;
        this.saldo = saldo;
        this.DNI = DNI;
    }

    public CuentaBancaria(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    
    

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
        
        
        
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" + "numeroCuenta=" + numeroCuenta + ", propietario=" + propietario + ", saldo=" + saldo + ", DNI=" + DNI + '}';
    }
    
    
    public int comprobarNumCuenta(String numcuenta){
        
        int cont=0;
        
       
        Pattern p = Pattern.compile("[0-9]{4}[-]{1}[0-9]{4}[-]{1}[0-9]{4}[-]{1}[0-9]{4}");
        Matcher m = p.matcher(numcuenta);
        boolean b = m.matches();
                
        if(!b){
          cont++;
          System.err.println("El número de cuenta es incorrecto");
              }
        
        
        return cont;
        
    }
    
    
    public int comprobarDNI(String textdni){
        
        int dni, restodni, cont=0;
       
        textdni = textdni.toUpperCase();
        Pattern p = Pattern.compile("[0-9]{8}{1}[A-Za-z]");
        Matcher m = p.matcher(textdni);
        boolean b = m.matches();
        
        if(b){
        /*
        String letra, nif;
        
        textdni = textdni.toUpperCase();
				
	dni=Integer.parseInt(textdni.substring(0,8));
				
				
	String[]letrasDNI={"T","R","W","A","G",",M","Y","F","P","D","X","B","N","J","Z","S"
			                ,"Q","V","H","L","C","K","E"};
				 
	restodni= dni%23;
				 
	letra = letrasDNI[restodni];
                                 
        letra = letra.toUpperCase();
				 
        nif=Integer.toString(dni)+letra;
				 
	 if(!nif.equals(textdni)){
					 
	 cont++;
         System.err.println("El DNI no es correcto");
        }
         */
        }
        else{
            cont++;
          System.err.println("El DNI no es correcto");  
        }

        return cont;
    }
    
    
    
}
