package ar.com.ada.creditos.entities.reportes;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity

    public class ReportePrestamoTotales {
        @Id
        
        private int cantidadTotal;
        private BigDecimal importeTotal;

    

        public int getCantidadTotal() {
            return cantidadTotal;
        }

        public void setCantidadTotal(int cantidadTotal) {
            this.cantidadTotal = cantidadTotal;
        }

        public BigDecimal getImporteTotal() {
            return importeTotal;
        }

        public void setImporteTotal(BigDecimal importeTotal) {
            this.importeTotal = importeTotal;
        }

		public String getNombre() {
			return null;
		}


}