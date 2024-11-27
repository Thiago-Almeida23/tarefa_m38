package br.com.thiago.cadastro_cliente.controller;

import br.com.thiago.cadastro_cliente.model.Cliente;
import br.com.thiago.cadastro_cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Adiciona o cliente no modelo para ser usado nos formulários
    @ModelAttribute("cliente")
    public Cliente populateCliente() {
        return new Cliente(); // Retorna um cliente vazio para o formulário de novo cliente
    }

    // Método para exibir a lista de clientes
    @GetMapping
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.listarTodos();
        model.addAttribute("clientes", clientes);
        return "clientes";  // Retorna a página que lista os clientes
    }

    // Método para exibir o formulário para um novo cliente
    @GetMapping("/novo")
    public String novoClienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());  // Adiciona um cliente vazio para o formulário
        return "cliente_form";  // A página do formulário
    }

    @PostMapping
    public String salvarCliente(@ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "cliente_form";  // Se houver erro, retorna ao formulário
        }

        if (cliente.getId() != null) {
            // Se o cliente já tem ID, significa que estamos editando, então chamamos o método de atualização
            clienteService.atualizar(cliente);
        } else {
            // Caso contrário, é um novo cliente, então chamamos o método de salvar
            clienteService.salvar(cliente);
        }

        return "redirect:/clientes";  // Redireciona para a lista de clientes
    }

    // Método para editar um cliente
    @GetMapping("/{id}/editar")
    public String editarCliente(@PathVariable Long id, Model model) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());  // Adiciona o cliente encontrado ao modelo
            return "cliente_form";  // Retorna para o formulário de edição
        } else {
            return "redirect:/clientes";  // Se não encontrado, redireciona para a lista
        }
    }

    // Método para excluir um cliente
    @GetMapping("/{id}/excluir")
    public String excluirCliente(@PathVariable Long id) {
        clienteService.excluir(id);  // Exclui o cliente
        return "redirect:/clientes";  // Redireciona para a lista de clientes
    }
}
