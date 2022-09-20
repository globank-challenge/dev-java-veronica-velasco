package com.ec.core.accounts.domain.usecases;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.core.accounts.domain.constantes.Constantes;
import com.ec.core.accounts.domain.enums.TipoMovimientoEnum;
import com.ec.core.accounts.domain.models.Movimiento;
import com.ec.core.accounts.domain.models.dtos.MovimientoDto;
import com.ec.core.accounts.domain.ports.repositories.IMovimientoRepository;
import com.ec.core.accounts.domain.ports.services.ICuentaService;
import com.ec.core.accounts.domain.ports.services.IMovimientoService;
import com.ec.core.accounts.infrastructure.config.exception.ErrorSaldoException;

@Service
public class MovimientoService implements IMovimientoService {

	@Autowired
	IMovimientoRepository iMovimientoRepository;
	
	@Autowired
	ICuentaService iCuentaService;
	
	@Override
	public List<MovimientoDto> obtenerMovimientosPorCuenta(String numeroCuenta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MovimientoDto guardarMovimiento(MovimientoDto movimientoDto) {
		Movimiento movimiento = obtenerMovimientoFromDto(movimientoDto);
		movimiento.setFecha(new Date());
		try {
			movimiento.setSaldo(calcularSaldo(movimiento.getValor(), movimiento.getCuenta().getSaldoInicial(),
					movimiento.getTipoMovimiento()));
		} catch (Exception e) {
			throw new ErrorSaldoException(e.getMessage());
		}
		movimiento.getCuenta().setSaldoInicial(movimiento.getSaldo());
		movimiento = iMovimientoRepository.guardarMovimiento(movimiento);
		return obtenerMovimientoDtoFromMovimiento(movimiento);
		
	}
	
	private BigDecimal calcularSaldo(BigDecimal valor, BigDecimal saldoInicial, TipoMovimientoEnum tipoMovimiento) throws Exception {
		BigDecimal saldo = BigDecimal.ZERO;
		try {
			if (tipoMovimiento.getValor().equals(Constantes.RETIRO)) {
				if(saldoInicial.equals(BigDecimal.ZERO)) {
					throw new ErrorSaldoException("El saldo de la cuenta es 0.00");
				}
				saldo = saldoInicial.subtract(valor);
			} else {
				saldo = saldoInicial.add(valor);
			}
		} catch (Exception e) {
			throw new Exception("No se pudo realizar el retiro. " + e.getMessage());
		}
		return saldo;
	}


	private Movimiento obtenerMovimientoFromDto(MovimientoDto movimientoDto) {
		return Movimiento.builder().id(movimientoDto.getId()).fecha(movimientoDto.getFecha())
				.saldo(movimientoDto.getSaldo())
				.valor(movimientoDto.getValor())
				.tipoMovimiento(TipoMovimientoEnum.obtenerEnumeracion(movimientoDto.getTipoMovimiento()))
				.cuenta(iCuentaService.obtenerCuentaFromDto(movimientoDto.getCuentaDto()))
				.build();
	}

	private MovimientoDto obtenerMovimientoDtoFromMovimiento(Movimiento movimiento) {
		return MovimientoDto.builder().id(movimiento.getId()).fecha(movimiento.getFecha())
				.saldo(movimiento.getSaldo())
				.valor(movimiento.getValor())
				.tipoMovimiento(movimiento.getTipoMovimiento().toString()).cuentaDto(iCuentaService.obtenerCuentaDtoFromCuenta(movimiento.getCuenta())).build();
	}

}
