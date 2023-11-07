package santo.com.bill.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import santo.com.bill.dto.CuentaDto;
import santo.com.bill.service.CuentaMovimientoService;
import santo.com.bill.util.ApiResponseH;

/**
 *
 * @author santo
 */
@RestController
@RequestMapping("/bill/v1/cuentas")
public class CuentaController {

    @Autowired
    private CuentaMovimientoService cuenmoviService;

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody CuentaDto dto) {
        return ApiResponseH.generateResponse("Cuenta creada.",
                HttpStatus.OK, 200, cuenmoviService.createCuenta(dto));
    }

    @PutMapping()
    public ResponseEntity<Object> update(@RequestBody CuentaDto dto) {
        return ApiResponseH.generateResponse("Cuenta actualizada.",
                HttpStatus.OK, 200, cuenmoviService.updateCuenta(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id", required = true) String id) {
        cuenmoviService.deleteCuenta(id);
        return ApiResponseH.generateResponse("Cuenta eliminada.",
                HttpStatus.OK, 200, null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(
            @Valid
            @PathVariable(name = "id", required = true) String id) {

        return ApiResponseH.generateResponse("Success.",
                HttpStatus.OK, 200, cuenmoviService.findCuentaById(id));

    }

}
