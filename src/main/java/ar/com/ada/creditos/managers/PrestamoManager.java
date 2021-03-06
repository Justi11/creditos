package ar.com.ada.creditos.managers;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import ar.com.ada.creditos.entities.Prestamo;

public class PrestamoManager {

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

    public void exit() {
        sessionFactory.close();
    }

    public void create(Prestamo prestamo) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(prestamo);

        session.getTransaction().commit();
        session.close();
    }

    public Prestamo read(int clienteId) {
        Session session = sessionFactory.openSession();

        Prestamo cliente = session.get(Prestamo.class, clienteId);

        session.close();

        return cliente;
    }

    public Prestamo readByDNI(String dni) {
        int nuevoDni = Integer.parseInt(dni);
        Session session = sessionFactory.openSession();

        Prestamo prestamo = session.byNaturalId(Prestamo.class).using("dni", nuevoDni).load();

        session.close();

        return prestamo;

    }

    public void update(Prestamo cliente) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(cliente);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(Prestamo prestamo) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(prestamo);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Este metodo en la vida real no debe existir ya qeu puede haber miles de
     * usuarios
     * 
     * @return
     */

    /**
     * Este metodo en la vida real no debe existir ya qeu puede haber miles de
     * usuarios
     * 
     * @return
     **/
    public List<Prestamo> buscarTodos() {

        Session session = sessionFactory.openSession();

        /// NUNCA HARCODEAR SQLs nativos en la aplicacion.
        // ESTO es solo para nivel educativo
        Query query = session.createNativeQuery("SELECT * FROM prestamo", Prestamo.class);

        List<Prestamo> todos = query.getResultList();

        return todos;
    }

    public List<Prestamo> buscarPorDNICliente(int dni) {

        Session session = sessionFactory.openSession();

        // String nuevoDni;//
        /// NUNCA HARCODEAR SQLs nativos en la aplicacion.
        // ESTO es solo para nivel educativo
        Query query = session.createQuery("SELECT p FROM Prestamo p WHERE p.cliente.dni=:dni");

        query.setParameter("dni", dni);
        List<Prestamo> prestamos = query.getResultList();

        return prestamos;
    }

    public List<Prestamo> buscarPrestamosPorCliente(int numcli) {
        Session session = sessionFactory.openSession();

        Query queryBuscarPrestamosPorCliente = session.createQuery(
                "SELECT cliente_id, nombre, COUNT(c) CANTIDAD, MAX(p.importe) MAXIMO, SUM(p.importe) TOTAL FROM cliente c INNER JOIN prestamo p on c.cliente_id = p.cliente_id GROUP BY c.cliente_id, c.nombre = ?",
                Prestamo.class); 
        queryBuscarPrestamosPorCliente.setParameter("numcli", numcli);
        List<Prestamo> prestamos = queryBuscarPrestamosPorCliente.getResultList();
        return prestamos;
    }
}

   /* public List<Prestamo> buscarTotalPrestamos() {
        Session session = sessionFactory.openSession();

    Query queryBuscarTotalPrestamos = session.createNativeQuery
    ("select count(*) as cantidad, sum(p.importe) as total from prestamo p;", Prestamo.class);

    queryBuscarTotalPrestamos.setParameter(1, prestamoId ;
    List<Prestamo> prestamos = query.getResultList();

    return prestamos;
}*/