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
public class Operaciones {
    
    private int id;
    private String fechaHora;
    private String numeroCuenta;
    private String tipoOperacion;
    private int cantidad;

    public Operaciones() {
    }

    public Operaciones(int id, String fechaHora, String numeroCuenta, String tipoOperacion, int cantidad) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.numeroCuenta = numeroCuenta;
        this.tipoOperacion = tipoOperacion;
        this.cantidad = cantidad;
    }

    
    public Operaciones(String fechaHora, String numeroCuenta, String tipoOperacion, int cantidad) {
        this.fechaHora = fechaHora;
        this.numeroCuenta = numeroCuenta;
        this.tipoOperacion = tipoOperacion;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Operaciones{" + "fechaHora=" + fechaHora + ", numeroCuenta=" + numeroCuenta + ", tipoOperacion=" + tipoOperacion + ", cantidad=" + cantidad + '}';
    }
    
    
    
}
