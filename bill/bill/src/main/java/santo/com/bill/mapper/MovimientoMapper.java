package santo.com.bill.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import santo.com.bill.dto.MovimientoGetDto;
import santo.com.bill.dto.MovimientoPostDto;
import santo.com.bill.entity.Movimiento;

/**
 *
 * @author santo
 */
@Mapper(componentModel = "spring")
public interface MovimientoMapper {

    @Mapping(target = "idMovimiento", expression = "java(0L)")
    @Mapping(target = "numeroCuenta", expression = "java(new santo.com.bill.entity.Cuenta(s.getNumeroCuenta()))")
    Movimiento mapPostToMovimiento(MovimientoPostDto s);

    @Mapping(target = "numeroCuenta", expression = "java(s.getNumeroCuenta().getNumeroCuenta())")
    MovimientoGetDto mapMovimientoToGet(Movimiento s); 

    List<MovimientoGetDto> mapListMovimientosToListGet(List<Movimiento> s);

}
