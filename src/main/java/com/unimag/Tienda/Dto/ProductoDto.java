package com.unimag.Tienda.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProductoDto {
    private Long id;
    private String nombreProducto;
    private double precio;
    private int stock;
}
