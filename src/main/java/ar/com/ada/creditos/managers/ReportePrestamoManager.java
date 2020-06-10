package ar.com.ada.creditos.managers;

import java.util.List;
import java.util.logging.Level;
import ar.com.ada.creditos.entities.Cliente;
import ar.com.ada.creditos.entities.Prestamo;
import ar.com.ada.creditos.entities.reportes.ReportePrestamoPorCliente;
import ar.com.ada.creditos.entities.reportes.ReportePrestamoTotales;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class ReportePrestamoManager {
    protected SessionFactory sessionFactory;

    public void setup() {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() // configures settings
                                                                                                  // from
                                                                                                  // hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw ex;
        }
    }

    /*
     * 1) por cliente, saber cuantos prestamos tiene, cual el importe mas alto y
     * cuanto en total de prestamos. 2) obtenemos la cantidad de prestamos y cuanta
     * plata en total es.
     */

    public List<ReportePrestamoPorCliente> generarReportePrestamoPorCliente(int clienteId) {

        Session session = sessionFactory.openSession();

        Query queryReportePorCliente = session.createNativeQuery(
                "SELECT c.cliente_id, c.nombre,count(*) cantidadPrestamos, max(p.importe) maximo, sum(p.importe) total FROM cliente c "
                        + "inner join prestamo p on c.cliente_id = p.cliente_id where c.cliente_id=? group by c.cliente_id, c.nombre ",
                ReportePrestamoPorCliente.class);
        queryReportePorCliente.setParameter(1, clienteId);
        List<ReportePrestamoPorCliente> reportePrestamoid = queryReportePorCliente.getResultList();

        return reportePrestamoid;

    }

    /*
     * public List<Prestamo> buscarPoridCliente (int numcli) {
     * 
     * Session session = sessionFactory.openSession(); Query query = session.
     * createQuery("SELECT p From Prestamo p where p.cliente.clienteId=:numcli" );
     * query.setParameter("numcli", numcli); List<Prestamo> prestamos=
     * query.getResultList(); return prestamos; }
     */


     public List<ReportePrestamoTotales> generarReportePrestamoTotales (){
        Session session = sessionFactory.openSession();

        Query queryReportePrestamoTotales = session.createNativeQuery(" Select count(*) cantidadTotal, sum(p.importe) importeTotal FROM prestamo p ", ReportePrestamoTotales.class);
        
        List<ReportePrestamoTotales> reportePrestamoTotalesid = queryReportePrestamoTotales.getResultList();

        return reportePrestamoTotalesid;

     }
}
