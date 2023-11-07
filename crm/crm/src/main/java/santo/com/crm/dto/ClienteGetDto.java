package santo.com.crm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class ClienteGetDto {

    @Schema(description = "Primary key", example = "1")
    private Long clienteId;

    @NotBlank
    @Schema(description = "Contrasena del cliente.", nullable = false, example = "Clave2023")
    private String contrasena;

    @NotBlank
    @Size(min = 1, max = 1)
    @Schema(description = "Estado del cliente, TRUE= activo, FALSE= inactivo", nullable = false, example = "true")
    private Boolean estado;

    @NotNull
    @Schema(description = "Persona", nullable = false)
    private PersonaDto persona;

}
