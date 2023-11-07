package santo.com.bill.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author santo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoGetDto {

    @Schema(description = "Primary key", example = "1")
    private Long idMovimiento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Fecha de movimiento", nullable = false, example = "2023-11-04 00:00:00")
    private Date fecha;

    @Schema(description = "Tipo de movimiento, CR= Crédito, DB= Débito", nullable = false, example = "CR")
    private String tipoMovimiento;

    @Schema(description = "Valor del movimiento", nullable = false, example = "0.00")
    private BigDecimal valor;

    @Schema(description = "Saldo del movimiento", nullable = false, example = "0.00")
    private BigDecimal saldo;

    @Schema(description = "Numero de cuenta")
    private String numeroCuenta;

}
