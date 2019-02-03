/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacionesbancariasjdbc;

/**
 *
 * @author Paco
 */
public class Historial {
    
    private String tipoEvento;
    private String fechaHora;
    private String numeroCuenta;

    public Historial() {
    }

    public Historial(String tipoEvento, String fechaHora, String numeroCuenta) {
        this.tipoEvento = tipoEvento;
        this.fechaHora = fechaHora;
        this.numeroCuenta = numeroCuenta;
    }

    public Historial(String fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    @Override
    public String toString() {
        return "Historial{" + "tipoEvento=" + tipoEvento + ", fechaHora=" + fechaHora + ", numeroCuenta=" + numeroCuenta + '}';
    }
    
    
    
}
