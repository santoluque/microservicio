package santo.com.bill.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class MovimientoPostDto {

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Fecha de movimiento", nullable = false, example = "2023-11-04 00:00:00")
    private Date fecha;

    @NotBlank
    @Schema(description = "Tipo de movimiento, CR= Crédito, DB= Débito", nullable = false, example = "CR")
    private String tipoMovimiento;

    @NotNull
    @Schema(description = "Valor del movimiento", nullable = false, example = "0.00")
    private BigDecimal valor;

    @Schema(description = "Saldo del movimiento", nullable = false, example = "0.00")
    private BigDecimal saldo;

    @NotBlank
    @Schema(description = "Numero de cuenta")
    private String numeroCuenta;

}
