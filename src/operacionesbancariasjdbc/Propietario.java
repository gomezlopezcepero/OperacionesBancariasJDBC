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
public class Propietario {
    
    private String DNI;
    private String usuario;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String numeroSecreto;

    public Propietario() {
    }

    public Propietario(String DNI, String usuario, String nombre, String primerApellido, String segundoApellido, String numeroSecreto) {
        this.DNI = DNI;
        this.usuario = usuario;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.numeroSecreto = numeroSecreto;
    }

    public Propietario(String DNI, String numeroSecreto) {
        this.DNI = DNI;
        this.numeroSecreto = numeroSecreto;
    }

    public Propietario(String nombre, String primerApellido, String segundoApellido) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
    }

    public Propietario(String DNI) {
        this.DNI = DNI;
    }
    
    

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getNumeroSecreto() {
        return numeroSecreto;
    }

    public void setNumeroSecreto(String numeroSecreto) {
        this.numeroSecreto = numeroSecreto;
    }

    @Override
    public String toString() {
        return "Propietario{" + "DNI=" + DNI + ", usuario=" + usuario + ", nombre=" + nombre + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + ", numeroSecreto=" + numeroSecreto + '}';
    }
    
    
    public int comprobarNumSecreto(String numSecreto){
        
        int cont=0;
       
        Pattern p = Pattern.compile("[0-9]{4}");
        Matcher m = p.matcher(numSecreto);
        boolean b = m.matches();
                
        if(!b){
          cont++;
          System.err.println("El número secreto es incorrecto");
              }
        
        return cont;   
    }
    
    public int comprobarUsuario(String nombre){
        
        int cont=0;
       
        Pattern p = Pattern.compile("[A-Za-z0-9]{3,15}");
        Matcher m = p.matcher(nombre);
        boolean b = m.matches();
                
        if(!b){
          cont++;
          System.err.println("El nombre de usuario es incorrecto");
              }
        
        return cont;   
    }
    
    
    
        public int comprobarNombreApellidos(String nombre){
    
           
         int cont=0;
       
        Pattern p = Pattern.compile("[A-Za-z]{3,15}");
        Matcher m = p.matcher(nombre);
        boolean b = m.matches();
                
        if(!b){
          cont++;
          System.err.println("El nombre y los apellidos no son correctos");
              }
        
        return cont; 
            
    }
    
        
    }
    
   
    
    
  