package br.com.thiago.cadastro_cliente.repository;

import br.com.thiago.cadastro_cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
