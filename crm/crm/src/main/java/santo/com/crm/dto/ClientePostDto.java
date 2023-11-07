package santo.com.crm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ClientePostDto {

    @NotBlank
    @Schema(description = "Contrasena del cliente.", nullable = false, example = "Clave2023")
    private String contrasena;

    @NotBlank
    @Schema(description = "Estado del cliente, TRUE= activo, FALSE= inactivo", nullable = false, example = "true")
    private Boolean estado;

    @NotNull
    @Schema(description = "Persona", nullable = false)
    private PersonaDto persona;

}
