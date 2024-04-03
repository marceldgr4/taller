package com.unimag.Tienda.Mapper;


import com.unimag.Tienda.Dto.PagoDto;
import com.unimag.Tienda.Entidad.Pago;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PagoMapper {

    PagoDto pagoToPagoDto(Pago pago);


    Pago pagoDtoToPago(PagoDto pagoDto);


   default List<PagoDto> pagosToPagoDtos(List<Pago>pagos){
        return pagos.stream().map(this::pagoToPagoDto).collect(Collectors.toList());
    }
}