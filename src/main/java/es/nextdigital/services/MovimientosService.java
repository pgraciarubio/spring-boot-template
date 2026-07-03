package es.nextdigital.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.nextdigital.dto.MovimientoDto;
import es.nextdigital.enums.TipoMovimiento;

public interface MovimientosService {

    Page<MovimientoDto> obtenerMovimientos(
            Long idCuenta,
            TipoMovimiento tipoMovimiento,
            Pageable pageable
    );
}