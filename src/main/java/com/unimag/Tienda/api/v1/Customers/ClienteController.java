package com.unimag.Tienda.api.v1.Customers;

import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Dto.ClienteDto;
import com.unimag.Tienda.Mapper.ClienteMapper;
import com.unimag.Tienda.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Customers")
public class ClienteController {
    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteController(ClienteService clienteService , ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }
//------------------CRUD-------------------------------

    @DeleteMapping("/api/v1/Customers/{id}")
    public ResponseEntity<Void>eliminarCliente(@PathVariable Long id){
        clienteService.EliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
//-------------------ObtenerLIST------------------------------

    @GetMapping("/api/v1/Customers/city?/CityName=")
    public ResponseEntity<List<Cliente>> ObtenerClientesPorCiudad(@RequestParam("CityName") String CityName){
        List<Cliente> clienteDtos = clienteService.ObtenerClientePorCity(CityName);
        return ResponseEntity.ok(clienteDtos);

    }
    @GetMapping("/api/v1/Customrs/email/{email}")
    public ResponseEntity<List<Cliente>> ObtenerClientePorEmail(@PathVariable String email){
        List<Cliente> clienteDto = clienteService.BuscarPorEmail(email);
        if (clienteDto !=null){
            return ResponseEntity.ok(clienteDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
@GetMapping
public ResponseEntity<List<Cliente>> ObtenerClientesPorDireccion(@RequestParam("Direccion") String Direccion){
        List<Cliente> clienteDto = clienteService.BuscarPorDireccion(Direccion);
        if (clienteDto !=null){
            return ResponseEntity.ok(clienteDto);
        }else {
            return ResponseEntity.notFound().build();
        }
}
@GetMapping
    public ResponseEntity<List<Cliente>> ObtenerNombreQueEmpiecePor(@RequestParam("Nombre")String Nombre){
       List<Cliente>clientesDto =clienteService.BuscarPorNombreQueEmpiecePor(Nombre);
       if (clientesDto !=null){
           return ResponseEntity.ok(clientesDto);
       }else {
           return ResponseEntity.notFound().build();
       }
    }

    //-------------------DTO-------------------
   @GetMapping
   public ResponseEntity<List<ClienteDto>> ObtenerTodoLosClientes(){
        List<ClienteDto> clienteDtos =clienteService.ObtenerTodoLosClientes();
        return  ResponseEntity.ok(clienteDtos);
   }
    @GetMapping("/api/v1/Customers/{id}")
    public ResponseEntity<ClienteDto> ObtenerClientePorId(@PathVariable Long id) {
        ClienteDto clienteDto = clienteService.ObtenerClientePorID(id);
        if (clienteDto != null) {
            return ResponseEntity.ok(clienteDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<ClienteDto> CrearClientes(@RequestBody ClienteDto clienteDto){
        ClienteDto NuevoCliente = clienteService.CrearCliente(clienteDto);
        return ResponseEntity.ok(NuevoCliente);
    }

    @PutMapping("/api/v1/Customrs7{id}")
    public ResponseEntity<ClienteDto> ActualizarClienteDto (@PathVariable Long  id, @RequestBody ClienteDto clienteDto ){
        Cliente cliente =clienteMapper.clienteDtoToCliente(clienteDto);
        ClienteDto clienteActulizado = clienteService.ActualizarClienteDto(id,clienteDto);
        if (clienteActulizado != null) {
            return ResponseEntity.ok(clienteActulizado);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


}
