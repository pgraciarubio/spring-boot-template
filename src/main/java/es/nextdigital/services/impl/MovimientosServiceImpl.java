package es.nextdigital.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.nextdigital.dto.MovimientoDto;
import es.nextdigital.enums.TipoMovimiento;
import es.nextdigital.mapper.MovimientoMapper;
import es.nextdigital.repository.MovimientosRepository;
import es.nextdigital.services.MovimientosService;

@Service
public class MovimientosServiceImpl implements MovimientosService {

    private final MovimientosRepository movimientosRepository;
    private final MovimientoMapper movimientoMapper;

    public MovimientosServiceImpl(
            MovimientosRepository movimientosRepository,
            MovimientoMapper movimientoMapper) {
        this.movimientosRepository = movimientosRepository;
        this.movimientoMapper = movimientoMapper;
    }

    @Override
    public Page<MovimientoDto> obtenerMovimientos(
            Long idCuenta,
            TipoMovimiento tipoMovimiento,
            Pageable pageable) {

        return movimientosRepository
                .buscarPorCuentaYTipo(idCuenta, tipoMovimiento, pageable)
                .map(movimientoMapper::toDto);
    }
}