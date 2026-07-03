package es.nextdigital.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import es.nextdigital.entity.Movimiento;
import es.nextdigital.enums.TipoMovimiento;

public interface MovimientosRepository extends JpaRepository<Movimiento, Long> {

    @Query("""
           SELECT m
           FROM Movimientos m
           WHERE m.cuenta.idCuenta = :idCuenta
             AND (:tipoMovimiento IS NULL OR m.tipoMovimiento = :tipoMovimiento)
           ORDER BY m.fechaMovimiento DESC
           """)
    Page<Movimiento> buscarPorCuentaYTipo(
            @Param("idCuenta") Long idCuenta,
            @Param("tipoMovimiento") TipoMovimiento tipoMovimiento,
            Pageable pageable);


}