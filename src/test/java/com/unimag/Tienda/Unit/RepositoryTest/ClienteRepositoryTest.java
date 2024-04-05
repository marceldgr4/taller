package com.unimag.Tienda.Unit.RepositoryTest;


import com.unimag.Tienda.AbstractIntegrationBDTest;
import com.unimag.Tienda.Entidad.Cliente;
import com.unimag.Tienda.Repository.ClienteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class ClienteRepositoryTest extends AbstractIntegrationBDTest {
    @Autowired
    ClienteRepository clienteRepository;


   @BeforeEach
   void setUp() {
        clienteRepository.deleteAll();
   }
   @Test
   void testGuardarCliente() {
       Cliente cliente = Cliente.builder()
               .Nombre("nombre")
               .Email("example@email.com")
               .Direccion("123-345")
               .build();
       Cliente clienteSalvo = clienteRepository.save(cliente);
       Cliente clienteEncontrado = clienteRepository.findById(cliente.getId()).orElse(null);
       assertThat(clienteEncontrado).isNotNull();
       assertThat(clienteEncontrado.getNombre()).isEqualTo(cliente.getNombre());
       assertThat(clienteEncontrado.getEmail()).isEqualTo(cliente.getEmail());
       assertThat(clienteEncontrado.getDireccion()).isEqualTo(cliente.getDireccion());

   }

}
