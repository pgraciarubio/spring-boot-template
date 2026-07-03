package es.nextdigital.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import es.nextdigital.enums.TipoMovimiento;

public record MovimientoDto(
	    Long idMovimiento,
	    LocalDate fechaMovimiento,
	    TipoMovimiento tipoMovimiento,
	    BigDecimal importe,
	    String concepto,
	    Long idCuenta
	) {}