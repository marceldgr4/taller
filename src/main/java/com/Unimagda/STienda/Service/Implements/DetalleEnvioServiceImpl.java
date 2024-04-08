package com.Unimagda.STienda.Service.Implements;


import com.Unimagda.STienda.DTO.Send.DetalleEnvioDtoSend;
import com.Unimagda.STienda.Entity.Enum.EstadoDePedido;
import com.Unimagda.STienda.Mapper.Mappers.DetalleEnvioMapper;
import com.Unimagda.STienda.Repository.Repositorys.DetalleEnvioRepository;
import com.Unimagda.STienda.Service.Services.DetalleEnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleEnvioServiceImpl implements DetalleEnvioService {

    private final DetalleEnvioRepository detalleEnvioRepository;
    private final DetalleEnvioMapper detalleEnvioMapper;

    @Autowired
    public DetalleEnvioServiceImpl(DetalleEnvioRepository detalleEnvioRepository,DetalleEnvioMapper detalleEnvioMapper){
        this.detalleEnvioRepository = detalleEnvioRepository;
        this.detalleEnvioMapper = detalleEnvioMapper;
    }
    @Override
    public List<DetalleEnvioDtoSend> ObtenerDetalleEnvioPorId(Long idPedido){
        return   detalleEnvioRepository.findByPedidoId(idPedido)
                .stream()
                .map(detalleEnvioMapper::detalleEnvioToDetalleEnvioDtoSend)
                .collect(Collectors.toList());
    }
    @Override
    public List<DetalleEnvioDtoSend>ObtenerDetalleDeEnvioPorTransportadora(String transportadora){
        return detalleEnvioRepository.findByTransportadora(transportadora)
                .stream()
                .map(detalleEnvioMapper::detalleEnvioToDetalleEnvioDtoSend)
                .collect(Collectors.toList());
    }
    @Override
    public List<DetalleEnvioDtoSend> ObtenerDetalleDeEnvioPorEstado(EstadoDePedido estadoDePedido){
        return detalleEnvioRepository.findByEstadoDePedido(estadoDePedido)
                .stream()
                .map(detalleEnvioMapper::detalleEnvioToDetalleEnvioDtoSend)
                .collect(Collectors.toList());
    }

    @Override
    public DetalleEnvioDtoSend save(DetalleEnvioDtoSend detalleEnvioDtoSend) {
        return null;
    }

    @Override
    public DetalleEnvioDtoSend Update(DetalleEnvioDtoSend detalleEnvioDtoSend) {
        return null;
    }


}
