package es.nextdigital.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.nextdigital.dto.MovimientoDto;
import es.nextdigital.entity.Movimiento;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {

    @Mapping(source = "cuenta.idCuenta", target = "idCuenta")
    MovimientoDto toDto(Movimiento movimiento);

    
}