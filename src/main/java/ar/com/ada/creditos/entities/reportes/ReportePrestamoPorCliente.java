package ar.com.ada.creditos.entities.reportes;

import java.math.BigDecimal;

import javax.persistence.*;


/*1) por cliente, saber cuantos prestamos tiene, cual el importe mas alto y cuanto en total de prestamos.
2) obtenemos la cantidad de prestamos y cuanta plata en total es.*/


@Entity

public class ReportePrestamoPorCliente {
    @Id
    @Column(name = "cliente_id")
    private int clienteId;
    private int cantidadPrestamos;
    private String nombre;
    private BigDecimal maximo;
    private BigDecimal total;

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getCantidadPrestamos() {
        return cantidadPrestamos;
    }

    public void setCantidadPrestamos(int cantidadPrestamos) {
        this.cantidadPrestamos = cantidadPrestamos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getMaximo() {
        return maximo;
    }

    public void setMaximo(BigDecimal maximo) {
        this.maximo = maximo;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
