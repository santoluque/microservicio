package santo.com.bill.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import santo.com.bill.entity.Movimiento;

/**
 *
 * @author santo
 */
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    /**
     * Lista de movimientos por cuentas y fecha.
     *
     * @param cuenta
     * @param desde,Date YYYY-MM-DD HH:mm:ss
     * @param hasta,Date YYYY-MM-DD HH:mm:ss
     * @return
     */
    @Query("SELECT t FROM Movimiento t WHERE numeroCuenta.numeroCuenta = ?1 AND fecha BETWEEN ?2 AND ?3 ORDER BY fecha ASC")
    public List<Movimiento> findByCuentaAndDates(String cuenta, Date desde, Date hasta);

}
