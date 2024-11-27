package br.com.thiago.cadastro_cliente.service;

import br.com.thiago.cadastro_cliente.model.Cliente;
import br.com.thiago.cadastro_cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Cliente cliente) {
        if (cliente.getId() != null) {
            // Caso o cliente tenha um ID, ele será atualizado
            return clienteRepository.save(cliente);
        } else {
            throw new IllegalArgumentException("Cliente sem ID não pode ser atualizado.");
        }
    }

    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }
}
