package santo.com.bill.util;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import santo.com.bill.struct.ApiRestHeader;

/**
 *
 * @author santo
 */
@Schema(description = "Model API response")
public class ApiResponseH {

    private ApiResponseH() {
    }

    /**
     * CODES FOR RESPONSE
     *
     * @200 OK Respuesta estándar para peticiones correctas.
     *
     * @201 Created La petición ha sido completada y ha resultado en la creación
     * de un nuevo recurso.
     *
     * @202 Accepted La petición ha sido aceptada para procesamiento, pero este
     * no ha sido completado. La petición eventualmente puede no haber sido
     * satisfecha, ya que podría ser no permitida o prohibida cuando el
     * procesamiento tenga lugar.
     *
     * @203 Non-Authoritative Information (desde HTTP/1.1) La petición se ha
     * completado con éxito, pero su contenido no se ha obtenido de la fuente
     * originalmente solicitada sino de otro servidor.
     *
     * @204 No Content La petición se ha completado con éxito pero su respuesta
     * no tiene ningún contenido (la respuesta puede incluir información en sus
     * cabeceras HTTP).
     *
     */
    /**
     * Generator base response object for API REST
     *
     * @param message, message of status
     * @param status, status HTTP of invocation
     * @param code, code response execution
     * @param responseObj, Object data response
     * @return
     */
    public static ResponseEntity<Object> generateResponse(String message,
            HttpStatus status, Integer code, Object responseObj) {

        return new ResponseEntity<>(new ApiRestHeader(code, message,
                responseObj), status);
    }

}
