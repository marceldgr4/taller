package com.unimag.Tienda.Dto;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ClienteDto {
    private Long id;
    private String Nombre;
    private String Email;
    private String Direccion;
    private String CityName;


}
