package santo.com.bill.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import santo.com.bill.entity.Cuenta;

/**
 *
 * @author santo
 */
public interface CuentaRepository extends JpaRepository<Cuenta, String> {

    /**
     * Lista cuentas por id de cliente.
     *
     * @param clienteId
     * @return
     */
    public List<Cuenta> findByClienteId(Long clienteId);

    /**
     * Find by numero cuenta.
     *
     * @param numeroCuenta
     * @return
     */
    public Cuenta findByNumeroCuenta(String numeroCuenta);
}
