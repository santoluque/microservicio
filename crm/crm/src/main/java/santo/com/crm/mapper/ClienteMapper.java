package santo.com.crm.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import santo.com.crm.dto.ClienteGetDto;
import santo.com.crm.dto.ClientePostDto;
import santo.com.crm.dto.PersonaDto;
import santo.com.crm.entity.Cliente;
import santo.com.crm.entity.Persona;

/**
 *
 * @author santo
 */
@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "persona", expression = "java(new santo.com.crm.dto.PersonaDto())")
    ClienteGetDto mapClienteToGet(Cliente s);

    @Mapping(target = "clienteId", expression = "java(0L)")
    @Mapping(target = "identificacion", expression = "java(new santo.com.crm.entity.Persona())")
    Cliente mapPostToCliente(ClientePostDto s);

    List<ClienteGetDto> listClientesToListGet(List<Cliente> s);

    PersonaDto mapPersonaToDto(Persona s);

}
