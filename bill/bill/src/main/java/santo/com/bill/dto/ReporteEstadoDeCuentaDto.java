package santo.com.bill.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author santo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteEstadoDeCuentaDto {

    private CuentaDto cuenta;
    private List<MovimientoGetDto> movimientos;
}
