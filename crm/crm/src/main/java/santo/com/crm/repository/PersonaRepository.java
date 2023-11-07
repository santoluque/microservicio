package santo.com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import santo.com.crm.entity.Persona;

/**
 *
 * @author santo
 */
public interface PersonaRepository extends JpaRepository<Persona, String> {

}
