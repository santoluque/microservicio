package santo.com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import santo.com.crm.entity.Cliente;

/**
 *
 * @author santo
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
