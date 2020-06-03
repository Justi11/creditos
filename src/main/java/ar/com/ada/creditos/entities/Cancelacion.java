package ar.com.ada.creditos.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "cancelacion")
public class Cancelacion {
    @Id
    @Column(name = "cancelacion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cancelacionId;
    
    @Column(name= "fecha_cancelacion")
    private int fechaCancelacion;
    private BigDecimal importe;
    private int cuota;

   @ManyToOne
    @JoinColumn(name="prestamo_id", referencedColumnName="prestamo_id")
    private Prestamo prestamo;

    @OneToMany(mappedBy = "prestamo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cancelacion> cancelacion= new ArrayList<>();

    public int getCancelacionId() {
        return cancelacionId;
    }

    public void setCancelacionId(int cancelacionId) {
        this.cancelacionId = cancelacionId;
    }
    

    public int getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(int fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

 
    }


