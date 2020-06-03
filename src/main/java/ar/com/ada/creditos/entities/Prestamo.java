package ar.com.ada.creditos.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import javax.persistence.ManyToOne;

//@Entity indica que esa clase actuara como entidad
//@Table indica a que tabla va a persisturse ese objeto
@Entity
@Table(name= "prestamo")

public class Prestamo {
    @Id //que es una pk
    @Column(name="prestamo_id")
    @GeneratedValue(strategy =GenerationType.IDENTITY) //autoincremental
    private int prestamoId;
    private BigDecimal importe;
    private Date fecha;
    private int cuotas;
    @Column(name="fecha_alta")
    private Date fechaAlta;
   
    @ManyToOne
    @JoinColumn(name="cliente_id", referencedColumnName="cliente_id")
    private Cliente cliente;

    public int getPrestamoId(){
        return prestamoId;
    }
    public void setPrestamoId(int prestamoId){
        this.prestamoId = prestamoId;
    
    }
    public BigDecimal getImporte() {
        return importe;
    }
    public void setImporte(BigDecimal importe){ 
        this.importe = importe;
    }
    public Date getFecha(){
        return fecha;
    }
    public void setFecha(Date fecha){
        this.fecha = fecha;
    }

    public int getCuotas(){
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }
    
    public Date getFechaAlta(){
        return fechaAlta;
    }
    public void setFechaAlta(Date fechaAlta){
        this.fechaAlta = fechaAlta;
    }

    


    
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
        this.cliente.getPrestamos().add(this);
    }

    public Cliente getCliente() {
        return cliente;
    }
	public String getNombre() {
		return cliente.getNombre();
	}
	public Integer getClienteId() {
		return cliente.getClienteId();
    }
}