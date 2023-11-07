package santo.com.bill.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import santo.com.bill.dto.CuentaDto;
import santo.com.bill.dto.MovimientoGetDto;
import santo.com.bill.dto.MovimientoPostDto;
import santo.com.bill.dto.ReporteEstadoDeCuentaDto;
import santo.com.bill.entity.Cuenta;
import santo.com.bill.entity.Movimiento;
import santo.com.bill.exception.ApiException400;
import santo.com.bill.exception.ApiException404;
import santo.com.bill.exception.ApiException409;
import santo.com.bill.mapper.CuentaMapper;
import santo.com.bill.mapper.MovimientoMapper;
import santo.com.bill.repository.CuentaRepository;
import santo.com.bill.repository.MovimientoRepository;
import santo.com.bill.struct.ApiRestHeader;

/**
 *
 * @author santo
 */
@Service
public class CuentaMovimientoService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaMapper cuentaMapper;

    @Autowired
    private MovimientoMapper movimientoMapper;

    /**
     * Create cuenta.
     *
     * @param dto
     * @return
     */
    public CuentaDto createCuenta(CuentaDto dto) {
        Cuenta cuenta = cuentaMapper.mapDtoToCuenta(dto);
        Cuenta cuentaTemp = cuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta());
        if (cuentaTemp != null) {
            throw new ApiException400(
                    HttpStatus.BAD_REQUEST.value(),
                    "Cuenta ya existe.");
        }
        /**
         * --Validate if client exist--*
         */
        RestTemplate restTemplate = new RestTemplate();
        ApiRestHeader respuesta = restTemplate.getForObject("http://localhost:8082/crm/v1/clientes/"
                + dto.getClienteId(), ApiRestHeader.class);
        if (respuesta != null) {
            if (!(respuesta.getCode() == 200 && respuesta.getData() != null)) {
                throw new ApiException409(HttpStatus.CONFLICT.value(),
                        "Cliente no existe.");
            }
        } else {
            /*No client*/
            throw new ApiException409(HttpStatus.CONFLICT.value(),
                    "Cliente no existe.");
        }
        return cuentaMapper.mapCuentaToDto(cuentaRepository.save(cuenta));
    }

    /**
     * Update cuenta.
     *
     * @param dto
     * @return
     */
    public CuentaDto updateCuenta(CuentaDto dto) {
        Optional<Cuenta> cuenta = cuentaRepository.findById(dto.getNumeroCuenta());
        if (cuenta.isPresent()) {
            cuenta.get().setEstado(dto.getEstado());
            cuenta.get().setSaldoInicial(dto.getSaldoInicial());
            cuenta.get().setTipoCuenta(dto.getTipoCuenta());
            /**
             * --Validate if client exist--*
             */
            RestTemplate restTemplate = new RestTemplate();
            ApiRestHeader respuesta = restTemplate.getForObject("http://localhost:8082/crm/v1/clientes/"
                    + dto.getClienteId(), ApiRestHeader.class);
            if (respuesta != null) {
                if (respuesta.getCode() == 200 && respuesta.getData() != null) {
                    cuenta.get().setClienteId(dto.getClienteId());
                } else {
                    throw new ApiException409(HttpStatus.CONFLICT.value(),
                            "Cliente no existe.");
                }
            } else {
                /*No client*/
                throw new ApiException409(HttpStatus.CONFLICT.value(),
                        "Cliente no existe.");
            }

            return cuentaMapper.mapCuentaToDto(cuentaRepository.save(cuenta.get()));
        } else {
            throw new ApiException409(HttpStatus.CONFLICT.value(),
                    "Cuenta no registrada.");
        }
    }

    public void deleteCuenta(String numeroCuenta) {
        Optional<Cuenta> cuenta = cuentaRepository.findById(numeroCuenta);
        if (cuenta.isPresent()) {
            cuentaRepository.delete(cuenta.get());
        } else {
            throw new ApiException409(HttpStatus.CONFLICT.value(),
                    "Cuenta no registrada.");
        }

    }

    /**
     * Find cuenta by Id
     *
     * @param numeroCuenta
     * @return
     */
    public CuentaDto findCuentaById(String numeroCuenta) {
        Optional<Cuenta> cuenta = cuentaRepository.findById(numeroCuenta);
        if (cuenta.isPresent()) {
            return cuentaMapper.mapCuentaToDto(cuenta.get());
        } else {
            throw new ApiException409(HttpStatus.CONFLICT.value(),
                    "Cuenta no registrada.");
        }
    }

    /**
     * Create movimiento.
     *
     * @param dto
     * @return
     */
    public MovimientoGetDto createMovimiento(MovimientoPostDto dto) {
        Movimiento movimiento = movimientoMapper.mapPostToMovimiento(dto);
        Optional<Cuenta> cuenta = cuentaRepository.findById(dto.getNumeroCuenta());
        if (!cuenta.isPresent()) {
            //Cuenta no existe
            throw new ApiException409(HttpStatus.CONFLICT.value(),
                    "Cuenta no registrada.");
        }

        switch (movimiento.getTipoMovimiento()) {
            case "CR" -> {
                cuenta.get().setSaldoInicial(cuenta.get().getSaldoInicial().add(movimiento.getValor()));
            }
            case "DB" -> {
                if (cuenta.get().getSaldoInicial().compareTo(movimiento.getValor()) >= 0) {
                    cuenta.get().setSaldoInicial(cuenta.get().getSaldoInicial().subtract(movimiento.getValor()));
                } else {
                    //  saldo insuficiente
                    throw new ApiException409(HttpStatus.CONFLICT.value(),
                            "Saldo no disponible!!.");
                }
            }
            default -> {
                //Movimiento no autorizado
                throw new ApiException409(HttpStatus.CONFLICT.value(),
                        "Operación no permitida.");
            }
        }
        movimiento.setSaldo(cuenta.get().getSaldoInicial());
        movimiento.setNumeroCuenta(cuenta.get());
        return movimientoMapper.mapMovimientoToGet(movimientoRepository.save(movimiento));
    }

    /**
     * Update movimiento.
     *
     * @param id
     * @param dto
     * @return
     */
    public MovimientoGetDto updateMovimiento(Long id, MovimientoPostDto dto) {
        Optional<Movimiento> movimiento = movimientoRepository.findById(id);
        if (movimiento.isPresent()) {

            switch (movimiento.get().getTipoMovimiento()) {
                case "CR" -> {
                    movimiento.get().getNumeroCuenta().setSaldoInicial(
                            movimiento.get().getNumeroCuenta().getSaldoInicial()
                                    .subtract(movimiento.get().getValor()));
                }
                case "DB" -> {
                    movimiento.get().getNumeroCuenta().setSaldoInicial(
                            movimiento.get().getNumeroCuenta().getSaldoInicial()
                                    .add(movimiento.get().getValor()));
                }
                default -> {
                    throw new ApiException409(HttpStatus.CONFLICT.value(),
                            "Operación no permitida.");
                }
            }

            cuentaRepository.save(movimiento.get().getNumeroCuenta());

            movimiento.get().setFecha(dto.getFecha());
            movimiento.get().setSaldo(dto.getSaldo());
            movimiento.get().setValor(dto.getValor());
            movimiento.get().setTipoMovimiento(dto.getTipoMovimiento());

            switch (movimiento.get().getTipoMovimiento()) {
                case "CR" -> {
                    movimiento.get().getNumeroCuenta().setSaldoInicial(
                            movimiento.get().getNumeroCuenta().getSaldoInicial()
                                    .add(movimiento.get().getValor()));
                }
                case "DB" -> {
                    if (movimiento.get().getNumeroCuenta().getSaldoInicial().compareTo(movimiento.get().getValor()) >= 0) {
                        movimiento.get().getNumeroCuenta().setSaldoInicial(
                                movimiento.get().getNumeroCuenta()
                                        .getSaldoInicial().subtract(movimiento.get().getValor()));
                    } else {
                        //  saldo insuficiente
                        throw new ApiException409(HttpStatus.CONFLICT.value(),
                                "Saldo no disponible.");
                    }
                }
                default -> {
                    //Movimiento no autorizado
                    throw new ApiException409(HttpStatus.CONFLICT.value(),
                            "Operación no permitida.");
                }
            }
            movimiento.get().setSaldo(movimiento.get().getNumeroCuenta().getSaldoInicial());
            return movimientoMapper.mapMovimientoToGet(movimientoRepository.save(movimiento.get()));
        } else {
            throw new ApiException404(HttpStatus.NOT_FOUND.value(),
                    "Movimiento con id " + id + " no encontrado.");
        }
    }

    /**
     * Delete movimiento by id.
     *
     * @param id
     */
    public void deleteMovimiento(Long id) {
        Optional<Movimiento> movimiento = movimientoRepository.findById(id);
        if (movimiento.isPresent()) {
            movimientoRepository.delete(movimiento.get());
        } else {
            throw new ApiException404(HttpStatus.NOT_FOUND.value(),
                    "Movimiento con id " + id + " no encontrado.");
        }
    }

    /**
     * Find movimiento by Id
     *
     * @param id
     * @return
     */
    public MovimientoGetDto findMovimientoById(Long id) {
        Optional<Movimiento> movimiento = movimientoRepository.findById(id);
        if (movimiento.isPresent()) {
            return movimientoMapper.mapMovimientoToGet(movimiento.get());
        } else {
            throw new ApiException404(HttpStatus.NOT_FOUND.value(),
                    "Movimiento con id " + id + " no encontrado.");
        }
    }

    /**
     * Lista de movimientos por id de cliente y rango de fechas.
     *
     * @param clienteId
     * @param desde
     * @param hasta
     * @return
     */
    public List<ReporteEstadoDeCuentaDto> findByClienteIdAndDates(Long clienteId, String desde, String hasta) {
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
        List<Movimiento> movimientos;
        List<ReporteEstadoDeCuentaDto> reportes = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date inicio;
        Date fin;

        try {
            inicio = sdf.parse(desde + " 00:00:00");
            fin = sdf.parse(hasta + " 23:59:59");
        } catch (ParseException ex) {
            Logger.getLogger(CuentaMovimientoService.class.getName()).log(Level.SEVERE, "BAD REQUEST", ex);
            throw new ApiException400(HttpStatus.BAD_REQUEST.value(),
                    "Fechas en formato incorrecto, formato esperado YYYY-MM-DD");
        }

        for (Cuenta c : cuentas) {
            movimientos = movimientoRepository.findByCuentaAndDates(c.getNumeroCuenta(), inicio, fin);

            reportes.add(
                    new ReporteEstadoDeCuentaDto(
                            cuentaMapper.mapCuentaToDto(c),
                            movimientoMapper.mapListMovimientosToListGet(movimientos)
                    ));
        }

        return reportes;

    }

}
