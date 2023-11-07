package santo.com.bill.mapper;

import org.mapstruct.Mapper;
import santo.com.bill.dto.CuentaDto;
import santo.com.bill.entity.Cuenta;

/**
 *
 * @author santo
 */
@Mapper(componentModel = "spring")
public interface CuentaMapper {

    CuentaDto mapCuentaToDto(Cuenta s);

    Cuenta mapDtoToCuenta(CuentaDto s);

}
