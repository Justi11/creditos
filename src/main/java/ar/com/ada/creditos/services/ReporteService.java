package ar.com.ada.creditos.services;

import java.util.List;

import ar.com.ada.creditos.entities.reportes.ReportePrestamoPorCliente;
import ar.com.ada.creditos.managers.ReportePrestamoManager;

public class ReporteService {

    private ReportePrestamoManager rPManager;

    public ReporteService(ReportePrestamoManager rPManager) {
        this.rPManager = rPManager;
    }

    public void mostrarReportePrestamoId(int clienteId) {

        List<ReportePrestamoPorCliente> reportePrestamoId = rPManager.generarReportePrestamoPorCliente(clienteId);
        for (ReportePrestamoPorCliente d : reportePrestamoId) {

            System.out.println("*************  REPORTE DEL CLIENTE Y SUS PRESTAMOS   *************");
            System.out.println("                                                                  ");
            System.out.println("Cliente_id: " + d.getClienteId() + "   " + "  Nombre: " + d.getNombre() + "   "
                    + "  Cantidad de prestamos: " + d.getCantidadPrestamos() + "    " + " El importe mas alto: "
                    + d.getMaximo() + "   " + " La suma total de prestamos " + d.getTotal());
        }
    }

    /*
     * public void mostrarReportePrestamosTotal(){ List<ReporteDePrestamos>
     * listaPrestamo = pRManager.generarReportePrestamoTotal();
     * 
     * for(ReporteDePrestamos Total: listaPrestamo){
     * 
     * System.out.println("Cliente_id " + d.getClienteId() + " Nombre " +
     * d.getNombre() + " Cantidad de prestamos " + d.getCantidadPrestamos() +
     * " El importe mas alto " + d.getMaximo() + " La suma total de prestamos " +
     * d.getTotal()); }} }
     */

}
