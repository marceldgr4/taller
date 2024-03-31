package com.unimag.Tienda.Service;

import com.unimag.Tienda.Entidad.DetalleEnvio;
import com.unimag.Tienda.Repository.DetalleEnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class DetalleEnvioService {
@Autowired
    private DetalleEnvioRepository detalleEnvioRepository;

    public DetalleEnvio GuardarDetalleEnvio(DetalleEnvio detalleEnvio){
        return detalleEnvioRepository.save(detalleEnvio);

    }
    public Optional<DetalleEnvio> ObtenerDetalleEnvioPorId(Long id) {
        return detalleEnvioRepository.findById(id);
    }

    public List<DetalleEnvio> ObtenerTodosLosDetallesDeEnvio() {
        return detalleEnvioRepository.findAll();
    }

    public Optional<DetalleEnvio> BuscarPorPedidoId(Long pedidoId) {
        return detalleEnvioRepository.findByidPedido(pedidoId);
    }

    public List<DetalleEnvio> BuscarPorTransportadora(String transportadora) {
        return detalleEnvioRepository.findByTransportadora(transportadora);
    }

    public List<DetalleEnvio> BuscarPorEstado(String estado) {
        return detalleEnvioRepository.findByEstadoPedido(estado);
    }

    public void EliminarDetalleEnvio(Long id) {
        detalleEnvioRepository.deleteById(id);
    }
}
