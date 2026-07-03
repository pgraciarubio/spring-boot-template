package es.nextdigital.services.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.nextdigital.dto.MovimientoDto;
import es.nextdigital.entity.Cuenta;
import es.nextdigital.entity.Movimiento;
import es.nextdigital.entity.Tarjeta;
import es.nextdigital.enums.TipoMovimiento;
import es.nextdigital.enums.TipoTarjeta;
import es.nextdigital.mapper.MovimientoMapper;
import es.nextdigital.repository.CuentaRepository;
import es.nextdigital.repository.MovimientosRepository;
import es.nextdigital.repository.TarjetaRepository;
import es.nextdigital.request.CrearMovimientoRequest;
import es.nextdigital.services.MovimientosService;
import jakarta.transaction.Transactional;

@Service
public class MovimientosServiceImpl implements MovimientosService {

    private final MovimientosRepository movimientosRepository;
    private final TarjetaRepository tarjetaRepository;
    private final CuentaRepository cuentaRepository;
    private final MovimientoMapper movimientoMapper;

    public MovimientosServiceImpl(
            MovimientosRepository movimientosRepository,
            MovimientoMapper movimientoMapper,
            TarjetaRepository tarjetaRepository,
            CuentaRepository cuentaRepository) {
        this.movimientosRepository = movimientosRepository;
        this.movimientoMapper = movimientoMapper;
        this.tarjetaRepository = tarjetaRepository;
        this.cuentaRepository = cuentaRepository;
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
    
    
    @Override
    @Transactional
    public MovimientoDto retiradaEfectivo(Long idTarjeta, BigDecimal importe) {

    	Movimiento movimientoGuardado = null;
    	boolean continuar = true;
    	Movimiento movimiento = new Movimiento();
    	Optional<Tarjeta> tarjeta = tarjetaRepository.findById(idTarjeta);
    	Optional<Cuenta> cuenta = cuentaRepository.findById(tarjeta.get().getCuenta().getIdCuenta()); 
    	
    	
    	if(tarjeta.get().getTipoTarjeta().equals(TipoTarjeta.DEBITO)
    		&& importe.compareTo(cuenta.get().getImporte()) == 1) { 
    			continuar = false;
    	}else if(tarjeta.get().getTipoTarjeta().equals(TipoTarjeta.CREDITO)
    		&& importe.compareTo(tarjeta.get().getLimiteTarjeta()) == 1) {
    		continuar = false;
    	}
    	
    	if(continuar) {
        	movimiento.setCuenta(tarjeta.get().getCuenta());
            movimiento.setFechaMovimiento(LocalDate.now());
            movimientoGuardado = movimientosRepository.save(movimiento);
    		
    	}


        return movimientoMapper.toDto(movimientoGuardado);
    }
}