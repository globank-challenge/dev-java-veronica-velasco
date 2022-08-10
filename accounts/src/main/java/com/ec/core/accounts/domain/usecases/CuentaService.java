package com.ec.core.accounts.domain.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.core.accounts.domain.enums.EstadoClienteEnum;
import com.ec.core.accounts.domain.enums.EstadoCuentaEnum;
import com.ec.core.accounts.domain.enums.TipoCuentaEnum;
import com.ec.core.accounts.domain.models.Cliente;
import com.ec.core.accounts.domain.models.Cuenta;
import com.ec.core.accounts.domain.models.dtos.ClienteDto;
import com.ec.core.accounts.domain.models.dtos.CuentaDto;
import com.ec.core.accounts.domain.ports.repositories.ICuentaRepository;
import com.ec.core.accounts.domain.ports.services.ICuentaService;
import com.ec.core.accounts.infrastructure.config.exception.NotFoundException;

@Service
public class CuentaService implements ICuentaService {
	
	@Autowired
	ICuentaRepository iCuentaRepository;

	@Override
	public CuentaDto obtenerCuenta(String numeroCuenta) {
		Cuenta cuenta = iCuentaRepository.obtenerCuentaPorNumero(numeroCuenta);
		if (cuenta == null) {
			throw new NotFoundException("Numero de cuenta no encontrada" + numeroCuenta);
		}
		return obtenerDtoFromCuenta(cuenta);
	}

	@Override
	public CuentaDto guardarCuent(CuentaDto cuentaDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CuentaDto actualizarCuenta(String numeroCuenta, CuentaDto cuentaDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarCuenta(String numeroCuenta) {
		// TODO Auto-generated method stub
		
	}

	private Cuenta obtenerCuentaFromDto(CuentaDto cuentaDto) {
		return  Cuenta.builder().id(cuentaDto.getId()).numeroCuenta(cuentaDto.getNumeroCuenta())
				.tipoCuenta(TipoCuentaEnum.obtenerEnumeracion(cuentaDto.getTipoCuenta())).saldoInicial(cuentaDto.getSaldoInicial())
				.estado(EstadoCuentaEnum.obtenerEnumeracion(cuentaDto.getEstado()))
				.cliente(obtenerClienteFromDto(cuentaDto.getClienteDto()))
				.build();
	}
	
	private CuentaDto obtenerDtoFromCuenta(Cuenta cuenta) {
		return CuentaDto.builder().id(cuenta.getId()).numeroCuenta(cuenta.getNumeroCuenta())
				.tipoCuenta(cuenta.getTipoCuenta().toString()).saldoInicial(cuenta.getSaldoInicial())
				.estado(cuenta.getEstado().toString())
				.clienteDto(obtenerDtoFromCliente(cuenta.getCliente()))
				.build();
	}
	
	private ClienteDto obtenerDtoFromCliente(Cliente cliente) {
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
