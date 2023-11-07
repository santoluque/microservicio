package santo.com.crm.controller;

//import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.util.Optional;
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
import santo.com.crm.dto.ClientePostDto;
import santo.com.crm.entity.Cliente;
import santo.com.crm.service.ClienteService;
import santo.com.crm.util.ApiResponseH;

/**
 *
 * @author santo
 */
@RestController
@RequestMapping("/crm/v1/clientes")
public class ClientController {

    @Autowired
    private ClienteService clienteService;

    /**
     * Create cliente
     *
     * @param dto
     * @return
     */
    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody(required = true) ClientePostDto dto) {
        return ApiResponseH.generateResponse("Cliente creado.",
                HttpStatus.OK, 200, clienteService.createCliente(dto));

    }

    /**
     * Update cliente
     *
     * @param id
     * @param dto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable(name = "id", required = true) Long id,
            @RequestBody ClientePostDto dto) {
        return ApiResponseH.generateResponse("Cliente actualizado.",
                HttpStatus.OK, 200, clienteService.updateCliente(id, dto));
    }

    /**
     * Delete cliente
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable(name = "id", required = true) Long id) {
        clienteService.deleteCliente(id);
        return ApiResponseH.generateResponse("Cliente eliminado.",
                HttpStatus.OK, 200, null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(
            @Valid
            @PathVariable(name = "id", required = true) Long id) {

        return ApiResponseH.generateResponse("Success.",
                HttpStatus.OK, 200, clienteService.findClienteById(id));
//        } else {
//            return ApiResponseH.generateResponse(User.class.getName() + ApiCodeStatus.MSG00404.getDescripcion(),
//                    HttpStatus.NOT_FOUND, 404, null);
//        }
    }

    /**
     * List all clients
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<Object> listAll() {

        return ApiResponseH.generateResponse("Success.",
                HttpStatus.OK, 200, clienteService.listAllClientes());
//        } else {
//            return ApiResponseH.generateResponse(User.class.getName() + ApiCodeStatus.MSG00404.getDescripcion(),
//                    HttpStatus.NOT_FOUND, 404, null);
//        }
    }

}
