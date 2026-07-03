package es.nextdigital.request;

import java.math.BigDecimal;

import es.nextdigital.enums.TipoMovimiento;

public record CrearMovimientoRequest(
    Long idCuenta,
    BigDecimal importe,
    TipoMovimiento tipoMovimiento,
    String concepto,
    Long idTarjeta
) {}