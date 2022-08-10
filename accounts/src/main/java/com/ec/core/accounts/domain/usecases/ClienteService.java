package com.ec.core.accounts.domain.usecases;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.core.accounts.domain.enums.EstadoClienteEnum;
import com.ec.core.accounts.domain.models.Cliente;
import com.ec.core.accounts.domain.models.dtos.ClienteDto;
import com.ec.core.accounts.domain.ports.repositories.IClienteRepository;
import com.ec.core.accounts.domain.ports.services.IClienteService;
import com.ec.core.accounts.infrastructure.config.exception.NotFoundException;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	IClienteRepository iClienteRepository;

	@Override
	public ClienteDto obtenerClientePorIdentificacion(String identificacion) {
		Cliente clienteOptional = iClienteRepository.findByIdentificacion(identificacion);
		if (clienteOptional == null) {
			throw new NotFoundException("Identificacion no encontrada" + identificacion);
		}
		return obtenerDtoFromCliente(clienteOptional);
	}

	@Override
	public ClienteDto guardarCliente(ClienteDto clienteDto) {
		Cliente cliente = obtenerClienteFromDto(clienteDto);
		cliente = iClienteRepository.guardarCliente(cliente);
		return obtenerDtoFromCliente(cliente);
	}


	@Override
	@Transactional
	public ClienteDto actualizarCliente(String identificacion, ClienteDto clienteDto) {
		Cliente clienteEncontrado = iClienteRepository.findByIdentificacion(identificacion);
		if (clienteEncontrado == null) {
			throw new NotFoundException("Cliente con identificacion no encontrado: " + identificacion);
		}
		Cliente cliente = obtenerClienteFromDto(clienteDto);
		cliente.setId(clienteEncontrado.getId());
		cliente = iClienteRepository.guardarCliente(cliente);
		return obtenerDtoFromCliente(cliente);
	}
	
	@Override
	public void eliminarCliente(String identificacion) {
		Cliente clienteEncontrado = iClienteRepository.findByIdentificacion(identificacion);
		if (clienteEncontrado == null) {
			throw new NotFoundException("Cliente con identificacion no encontrado: " + identificacion);
		}
		iClienteRepository.eliminarUsuario(clienteEncontrado);
	}
	
	public ClienteDto obtenerDtoFromCliente(Cliente cliente) {
		return ClienteDto.builder().id(cliente.getId()).contrasenia(cliente.getContrasenia())
				.nombre(cliente.getNombre()).edad(cliente.getEdad()).telefono(cliente.getTelefono())
				.genero(cliente.getGenero()).clienteId(cliente.getClienteId())
				.identificacion(cliente.getIdentificacion()).estado(cliente.getEstado().toString())
				.direccion(cliente.getDireccion()).build();
	}
	
	public Cliente obtenerClienteFromDto(ClienteDto clienteDto) {
		return  Cliente.builder().id(clienteDto.getId()).clienteId(clienteDto.getClienteId())
				.contrasenia(clienteDto.getContrasenia())
				.estado(EstadoClienteEnum.obtenerEnumeracion(clienteDto.getEstado())).nombre(clienteDto.getNombre())
				.direccion(clienteDto.getDireccion()).genero(clienteDto.getGenero()).edad(clienteDto.getEdad())
				.identificacion(clienteDto.getIdentificacion()).telefono(clienteDto.getTelefono()).build();
	}


}
