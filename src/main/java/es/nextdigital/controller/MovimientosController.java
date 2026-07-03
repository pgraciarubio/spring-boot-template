package es.nextdigital.controller;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.nextdigital.dto.MovimientoDto;
import es.nextdigital.enums.TipoMovimiento;
import es.nextdigital.services.MovimientosService;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientosController {

    private final MovimientosService movimientosService;

    public MovimientosController(MovimientosService movimientosService) {
        this.movimientosService = movimientosService;
    }

    @GetMapping("/{idCuenta}/movimientos")
    public Page<MovimientoDto> obtenerMovimientos(
            @PathVariable Long idCuenta,
            @RequestParam(required = false) TipoMovimiento tipoMovimiento,
            Pageable pageable) {

        return movimientosService.obtenerMovimientos(
                idCuenta,
                tipoMovimiento,
                pageable);
    }
}