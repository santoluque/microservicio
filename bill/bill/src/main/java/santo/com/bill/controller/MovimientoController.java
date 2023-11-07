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
import santo.com.bill.dto.MovimientoPostDto;
import santo.com.bill.service.CuentaMovimientoService;
import santo.com.bill.util.ApiResponseH;

/**
 *
 * @author santo
 */
@RestController
@RequestMapping("/bill/v1/movimientos")
public class MovimientoController {

    @Autowired
    private CuentaMovimientoService cuenmoviService;

    /**
     * Create movimiento
     *
     * @param dto
     * @return
     */
    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody MovimientoPostDto dto) {
        return ApiResponseH.generateResponse("Movimiento registrado.",
                HttpStatus.OK, 200, cuenmoviService.createMovimiento(dto));
    }

    /**
     * Update movimiento
     *
     * @param id
     * @param dto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable(name = "id", required = true) Long id,
            @RequestBody MovimientoPostDto dto) {
        return ApiResponseH.generateResponse("Movimiento actualizado.",
                HttpStatus.OK, 200, cuenmoviService.updateMovimiento(id, dto));
    }

    /**
     * Delete movimiento
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable(name = "id", required = true) Long id) {
        cuenmoviService.deleteMovimiento(id);
        return ApiResponseH.generateResponse("Movimiento eliminado.",
                HttpStatus.OK, 200, null);
    }

    /**
     * Find by id.
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(
            @Valid
            @PathVariable(name = "id", required = true) Long id) {

        return ApiResponseH.generateResponse("Success.",
                HttpStatus.OK, 200, cuenmoviService.findMovimientoById(id));
//        } else {
//            return ApiResponseH.generateResponse(User.class.getName() + ApiCodeStatus.MSG00404.getDescripcion(),
//                    HttpStatus.NOT_FOUND, 404, null);
//        }
    }

}
