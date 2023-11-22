package santo.com.crm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import santo.com.crm.dto.ClienteGetDto;
import santo.com.crm.dto.ClientePostDto;
import santo.com.crm.entity.Cliente;
import santo.com.crm.entity.Persona;
import santo.com.crm.exception.ApiException400;
import santo.com.crm.mapper.ClienteMapper;
import santo.com.crm.repository.ClienteRepository;
import santo.com.crm.repository.PersonaRepository;

/**
 *
 * @author santo
 */
@Service
public class ClienteService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    /**
     * Create Cliente
     *
     * @param dtoCliente
     * @return
     */
    public ClienteGetDto createCliente(ClientePostDto dtoCliente) {

        Cliente cliente = new Cliente();
        cliente.setContrasena(dtoCliente.getContrasena());
        cliente.setEstado(dtoCliente.getEstado());

        Optional<Persona> persona = personaRepository.findById(dtoCliente.getPersona().getIdentificacion());
        Persona newPerson;
        if (!persona.isPresent()) {
            newPerson = new Persona();
            newPerson.setIdentificacion(dtoCliente.getPersona().getIdentificacion());
            newPerson.setNombre(dtoCliente.getPersona().getNombre().toUpperCase());
            newPerson.setEdad(dtoCliente.getPersona().getEdad());
            newPerson.setGenero(dtoCliente.getPersona().getGenero());
            newPerson.setTelefono(dtoCliente.getPersona().getTelefono());
            newPerson.setDireccion(dtoCliente.getPersona().getDireccion().toUpperCase());
            newPerson = personaRepository.save(newPerson);

        } else {
            newPerson = persona.get();
        }
        cliente.setIdentificacion(newPerson);
        clienteRepository.save(cliente);
        ClienteGetDto clienteGet = clienteMapper.mapClienteToGet(cliente);
        clienteGet.setPersona(clienteMapper.mapPersonaToDto(newPerson));
        return clienteGet;
    }

    /**
     * Update Cliente
     *
     * @param id
     * @param dtoCliente
     * @return
     */
    public ClienteGetDto updateCliente(Long id, ClientePostDto dtoCliente) {

        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            cliente.get().setContrasena(dtoCliente.getContrasena());
            cliente.get().setEstado(dtoCliente.getEstado());

            Optional<Persona> persona = personaRepository.findById(dtoCliente.getPersona().getIdentificacion());
            Persona newPerson;
            if (!persona.isPresent()) {
                newPerson = new Persona();
                newPerson.setIdentificacion(dtoCliente.getPersona().getIdentificacion());
                newPerson.setNombre(dtoCliente.getPersona().getNombre().toUpperCase());
                newPerson.setEdad(dtoCliente.getPersona().getEdad());
                newPerson.setGenero(dtoCliente.getPersona().getGenero());
                newPerson.setTelefono(dtoCliente.getPersona().getTelefono());
                newPerson.setDireccion(dtoCliente.getPersona().getDireccion().toUpperCase());
            } else {
                newPerson = persona.get();
                newPerson.setNombre(dtoCliente.getPersona().getNombre().toUpperCase());
                newPerson.setEdad(dtoCliente.getPersona().getEdad());
                newPerson.setGenero(dtoCliente.getPersona().getGenero());
                newPerson.setTelefono(dtoCliente.getPersona().getTelefono());
                newPerson.setDireccion(dtoCliente.getPersona().getDireccion().toUpperCase());
            }
            newPerson = personaRepository.save(newPerson);
            cliente.get().setIdentificacion(newPerson);
            clienteRepository.save(cliente.get());

            ClienteGetDto clienteGet = clienteMapper.mapClienteToGet(cliente.get());
            clienteGet.setPersona(clienteMapper.mapPersonaToDto(newPerson));
            return clienteGet;

        } else {
            //Cliente no existe.
            throw new ApiException400(HttpStatus.BAD_REQUEST.value(),
                    "Cliente no existe.");
        }
    }

    /**
     * Delete Cliente
     *
     * @param id
     */
    public void deleteCliente(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {

            clienteRepository.delete(cliente.get());
        } else {
            //Cliente no existe.
            throw new ApiException400(HttpStatus.BAD_REQUEST.value(),
                    "Cliente no existe.");
        }
    }

    /**
     * Find cliente
     *
     * @param id
     * @return
     */
    public ClienteGetDto findClienteById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            ClienteGetDto clienteGet = clienteMapper.mapClienteToGet(cliente.get());
            clienteGet.setPersona(clienteMapper.mapPersonaToDto(cliente.get().getIdentificacion()));
            return clienteGet;
        } else {
            throw new ApiException400(HttpStatus.BAD_REQUEST.value(),
                    "Cliente no existe.");
        }
    }

    /**
     * List all clients.
     *
     * @return
     */
    public List<ClienteGetDto> listAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteGetDto> lista = new ArrayList<>();
        ClienteGetDto cliente;
        for (Cliente c : clientes) {
            cliente = clienteMapper.mapClienteToGet(c);
            cliente.setPersona(clienteMapper.mapPersonaToDto(c.getIdentificacion()));
            lista.add(cliente);
        }
        return lista;
    }
}
