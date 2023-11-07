package santo.com.crm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDto {

    @NotBlank
    @Schema(description = "Primary key", example = "1")
    private String identificacion;

    @NotBlank
    @Schema(description = "Nombre persona", maxLength = 60, nullable = false, example = "Santiago Luque")
    private String nombre;

    @Size(min = 1, max = 1)
    @NotBlank
    @Schema(description = "Genero persona, M= masculino, F= femenino", nullable = false, example = "M")
    private Character genero;

    @NotNull
    @Min(value = 1)
    @Max(value = 150)
    @Schema(description = "Edad de la persona", nullable = true, example = "25")
    private Integer edad;

    @NotBlank
    @Size(min = 2, max = 150)
    @Schema(description = "Dirección de la persona", maxLength = 150, nullable = true, example = "Quito")
    private String direccion;

    @NotBlank
    @Size(min = 7, max = 20)
    @Schema(description = "Telefono de la persona", maxLength = 20, nullable = true, example = "25")
    private String telefono;

}
