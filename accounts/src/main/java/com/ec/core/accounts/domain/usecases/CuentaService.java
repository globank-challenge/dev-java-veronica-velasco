package com.ec.core.accounts.domain.usecases;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.core.accounts.domain.enums.EstadoCuentaEnum;
import com.ec.core.accounts.domain.enums.TipoCuentaEnum;
import com.ec.core.accounts.domain.enums.TipoMovimientoEnum;
import com.ec.core.accounts.domain.models.Cuenta;
import com.ec.core.accounts.domain.models.Movimiento;
import com.ec.core.accounts.domain.models.dtos.CuentaDto;
import com.ec.core.accounts.domain.models.dtos.EstadoCuentaDto;
import com.ec.core.accounts.domain.ports.repositories.ICuentaRepository;
import com.ec.core.accounts.domain.ports.services.IClienteService;
import com.ec.core.accounts.domain.ports.services.ICuentaService;
import com.ec.core.accounts.infrastructure.config.exception.NotFoundException;

@Service
public class CuentaService implements ICuentaService {

	@Autowired
	ICuentaRepository iCuentaRepository;

	@Autowired
	IClienteService iClienteService;

	@Override
	public CuentaDto obtenerCuenta(String numeroCuenta) {
		Cuenta cuenta = iCuentaRepository.obtenerCuentaPorNumero(numeroCuenta);
		if (cuenta == null) {
			throw new NotFoundException("Numero de cuenta no encontrada");
		}
		return obtenerCuentaDtoFromCuenta(cuenta);
	}

	@Override
	public CuentaDto guardarCuenta(CuentaDto cuentaDto) {
		Cuenta cuenta = obtenerCuentaFromDto(cuentaDto);
		cuenta = iCuentaRepository.guardarCuenta(cuenta);
		return obtenerCuentaDtoFromCuenta(cuenta);
	}

	@Override
	public CuentaDto actualizarCuenta(String numeroCuenta, CuentaDto cuentaDto) {
		Cuenta cuentaEncontrada = iCuentaRepository.obtenerCuentaPorNumero(numeroCuenta);
		if (cuentaEncontrada == null) {
			throw new NotFoundException("Cuenta no encontrada ");
		}
		Cuenta cuenta = obtenerCuentaFromDto(cuentaDto);
		cuenta.setId(cuentaEncontrada.getId());
		cuenta = iCuentaRepository.guardarCuenta(cuenta);
		return obtenerCuentaDtoFromCuenta(cuenta);
	}

	@Override
	public void eliminarCuenta(String numeroCuenta) {
		Cuenta cuentaEncontrada = iCuentaRepository.obtenerCuentaPorNumero(numeroCuenta);
		if (cuentaEncontrada == null) {
			throw new NotFoundException("Numero de cuenta no encontrado");
		}
		iCuentaRepository.eliminarCuenta(cuentaEncontrada);
	}

	@Override
	public Cuenta obtenerCuentaFromDto(CuentaDto cuentaDto) {
		return Cuenta.builder().id(cuentaDto.getId()).numeroCuenta(cuentaDto.getNumeroCuenta())
				.tipoCuenta(TipoCuentaEnum.obtenerEnumeracion(cuentaDto.getTipoCuenta()))
				.saldoInicial(cuentaDto.getSaldoInicial())
				.estado(EstadoCuentaEnum.obtenerEnumeracion(cuentaDto.getEstado()))
				.cliente(iClienteService.obtenerClienteFromDto(cuentaDto.getClienteDto())).build();
	}

	@Override
	public CuentaDto obtenerCuentaDtoFromCuenta(Cuenta cuenta) {
		return CuentaDto.builder().id(cuenta.getId()).numeroCuenta(cuenta.getNumeroCuenta())
				.tipoCuenta(cuenta.getTipoCuenta().toString()).saldoInicial(cuenta.getSaldoInicial())
				.estado(cuenta.getEstado().toString())
				.clienteDto(iClienteService.obtenerDtoFromCliente(cuenta.getCliente())).build();
	}

	@Override
	public List<EstadoCuentaDto> obtenerCuentasPorIdentificacion(String identificacion) {
		List<Cuenta> cuentas = iCuentaRepository.obtenerCuentaPorIdentificacion(identificacion);
		if (cuentas == null) {
			throw new NotFoundException("No existen datos para esa identificacion");
		}

		Map<String, List<Cuenta>> cuentasPorNumero = cuentas.stream()
				.collect(Collectors.groupingBy(Cuenta::getNumeroCuenta));
		List<EstadoCuentaDto> listaEstadosCuenta = new ArrayList<>();
		for (var entry : cuentasPorNumero.entrySet()) {

			EstadoCuentaDto estadoCuenta = new EstadoCuentaDto();
			Cuenta cuenta = entry.getValue().get(0);
			List<Movimiento> movimientosCuenta = cuenta.getMovimientos();
			List<Movimiento> movimienosCredito = movimientosCuenta.stream()
					.filter(movimiento -> movimiento.getTipoMovimiento().equals(TipoMovimientoEnum.DEPOSITO))
					.collect(Collectors.toList());

			List<Movimiento> movimienosDebito = movimientosCuenta.stream()
					.filter(movimiento -> movimiento.getTipoMovimiento().equals(TipoMovimientoEnum.RETIRO))
					.collect(Collectors.toList());

			estadoCuenta.setNumeroCuenta(entry.getKey());
			estadoCuenta.setTotalCreditos(movimienosCredito.size());
			estadoCuenta.setTotalDebitos(movimienosDebito.size());
			estadoCuenta.setSaldo(cuenta.getSaldoInicial());
			listaEstadosCuenta.add(estadoCuenta);
		}

		return listaEstadosCuenta;
	}

}
