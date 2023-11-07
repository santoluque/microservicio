package santo.com.bill.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import santo.com.bill.service.CuentaMovimientoService;
import santo.com.bill.util.ApiResponseH;

/**
 *
 * @author santo
 */
@RestController
@RequestMapping("/bill/v1/reportes")
public class ReporteController {

    @Autowired
    private CuentaMovimientoService cuenmoviService;

    @GetMapping(value = "/clientes/{clienteId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> reportsMovements(
            @Valid
            @PathVariable(name = "clienteId", required = true) Long clienteId,
            @Parameter(name = "desde", required = true, description = "Fecha inicio, YYYY-MM-DD") String desde,
            @Parameter(name = "hasta", required = true, description = "Fecha fin, YYYY-MM-DD") String hasta) {
        return ApiResponseH.generateResponse("Success.",
                HttpStatus.OK, 200,
                cuenmoviService.findByClienteIdAndDates(clienteId, desde, hasta));
    }

}
