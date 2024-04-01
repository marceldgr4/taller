package com.unimag.Tienda.Controller;

import com.unimag.Tienda.Dto.ClienteDto;
import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Mapper.ClienteMapper;
import com.unimag.Tienda.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customrs")

public class ClienteController {
@Autowired
    private ClienteService clienteService;

@Autowired
private ClienteMapper clienteMapper;
    @PostMapping("/api/v1/customers")
    public ResponseEntity<ClienteDto> crearCliente(@RequestBody ClienteDto clienteDto) {
        Cliente NuevoCliente = clienteMapper.clienteDtoToCliente(clienteDto);
        ClienteDto NuevoClienteDto = clienteMapper.clienteToClienteDto(clienteService.CrearCliente(NuevoCliente));
        return new ResponseEntity<>(NuevoClienteDto,HttpStatus.CREATED);

    }

    @GetMapping("/api/v1/customers{id}")
    public ResponseEntity<ClienteDto> obtenerClientePorId(@PathVariable Long id) {
        ClienteDto clienteDto = clienteMapper.clienteToClienteDto(clienteService.ObtenerClientePorID(id));
        return ResponseEntity.ok(clienteDto);
    }

    @PutMapping("/api/v1/customers/{id}")
    public ResponseEntity<ClienteDto> ActualizarCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
        Cliente cliente =clienteMapper.clienteDtoToCliente(clienteDto);
        ClienteDto clienteActualizadoDto = clienteMapper.clienteToClienteDto(clienteService.ActulizarCliente(id,cliente));
        return ResponseEntity.ok(clienteActualizadoDto);
    }

    @DeleteMapping("/api/v1/customers/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.EliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/v1/customers/email/{email}")
    public ResponseEntity<List<ClienteDto>> BuscarClientesPorEmail(@RequestParam String email) {
        List<ClienteDto> clienteDto = clienteMapper.clienteToClientesDto(clienteService.BuscarPorEmail(email));
        return ResponseEntity.ok(clienteDto);
    }

    @GetMapping("/buscar/direccion")
    public ResponseEntity<List<Cliente>> BuscarClientesPorDireccion(@RequestParam String direccion) {
        List<Cliente> clientesEncontrados = clienteService.BuscarPorDireccion(direccion);
        return ResponseEntity.ok(clientesEncontrados);
    }
    @GetMapping("/api/v1/customrs/city/cityName=")
    public ResponseEntity<List<ClienteDto>> ObtenerClientePorCiudad (@RequestParam("CityName")String CityName){
        List<ClienteDto> clienteDto =clienteMapper.clientesToClientesDto(clienteService.ObtenerClientePorCiudad(CityName));
        return ResponseEntity.ok(clienteDto);
    }


    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<Cliente>> BuscarClientesPorNombreQueEmpieceCon(@RequestParam String nombre) {
        List<Cliente> clientesEncontrados = clienteService.BuscarPorNombreQueEmpieceCon(nombre);
        return ResponseEntity.ok(clientesEncontrados);
    }
}
