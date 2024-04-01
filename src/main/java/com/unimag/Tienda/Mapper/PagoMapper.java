package com.unimag.Tienda.Mapper;


import com.unimag.Tienda.Dto.PagoDto;
import com.unimag.Tienda.Entidad.Pago;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PagoMapper {
    PagoMapper INSTACE = Mappers.getMapper(PagoMapper.class);

    @Mapping(source = "fechaPago", target = "fechaPago")
    PagoDto pagoToPagoDto(Pago pago);

    @Mapping(source = "fechaPago",target = "fechaPago")
    Pago pagoDtoToPag(PagoDto pagoDto);
}