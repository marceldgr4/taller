package com.unimag.Tienda.Service;

import com.unimag.Tienda.Dto.PagoDto;
import com.unimag.Tienda.Entidad.Enum.MetodoPago;
import com.unimag.Tienda.Entidad.Pago;
import com.unimag.Tienda.Mapper.PagoMapper;
import com.unimag.Tienda.Repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;
    private final PagoMapper pagoMapper;
    @Autowired
    public PagoService(PagoRepository pagoRepository,PagoMapper pagoMapper){
        this.pagoRepository=pagoRepository;
        this.pagoMapper=pagoMapper;
    }

    public  PagoDto CrearPago(PagoDto pagoDto) {
        Pago pago = pagoMapper.pagoDtoToPago(pagoDto);
        pago = pagoRepository.save(pago);
        return pagoMapper.pagoToPagoDto(pago);

    }
    public List<PagoDto> ObtenerTodoLosPagos(){
        return pagoRepository.findAll().stream().map(pagoMapper::pagoToPagoDto).collect(Collectors.toList());
    }

    public List<Pago> obtenerTodoLosPagos() {
    return pagoRepository.findAll();
    }

    public  List<Pago> ObtenerPagosPorRangoFecha(LocalDateTime startDate, LocalDateTime endDate) {
        return pagoRepository.findByFechaPagoBetween(startDate, endDate);
    }

    public List<Pago> ObtenerPagoPorOrdenYMetodoPago(Long idPedido, MetodoPago metodoPago) {
        return pagoRepository.findByPedidoIdAndMetodoPago(idPedido, MetodoPago.PSE);
    }
    public Pago ObtenerPagoPorId(Long id) {
        Optional<Pago> pagoOptional = pagoRepository.findById(id);
        return pagoOptional.orElse(null);
    }
    public Pago actualizarPago(Long id, Pago pago) {

        if (pagoRepository.existsById(pago.getId())) {
            return pagoRepository.save(pago);
        } else {
            return null;
        }
    }
    public Boolean EliminarPago(Long id) {

        if (pagoRepository.existsById(id)) {
            pagoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}
