package santo.com.bill.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
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
public class CuentaDto {

    @NotBlank
    @Schema(description = "Primary key", example = "1002030001")
    private String numeroCuenta;

    @NotBlank
    @Schema(description = "Tipo de cuenta, A= Ahorros, C= Corriente", nullable = false, example = "A")
    private Character tipoCuenta;

    @NotNull
    @Schema(description = "Saldo inicial de la cuenta", nullable = false, example = "0.00")
    private BigDecimal saldoInicial;

    @NotBlank
    @Schema(description = "Estado de la cuenta, true= activa, false= Inactiva", nullable = false, example = "true")
    private Boolean estado;

    @NotNull
    @Schema(description = "Cliente id", example = "1")
    private Long clienteId;

}
