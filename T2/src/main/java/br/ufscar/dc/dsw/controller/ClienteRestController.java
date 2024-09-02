package br.ufscar.dc.dsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import jakarta.validation.Valid;

@RestController
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    // Cria cliente
    @PostMapping(path = "/api/clientes")
    @ResponseBody
    public ResponseEntity<Cliente> cria(@Valid @RequestBody Cliente cliente, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return ResponseEntity.badRequest().body(null);
        } else {
            cliente.setRole("ROLE_CLIENT");
            cliente.setEnabled(true);
            cliente.setPassword(encoder.encode(cliente.getPassword()));
            clienteService.salvar(cliente);
            return ResponseEntity.ok(cliente);
        }
    }

    // Retorna a lista de clientes
    @GetMapping(path = "/api/clientes")
    public ResponseEntity<List<Cliente>> listaClientes() {
        List<Cliente> lista = clienteService.buscarTodos();

        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(lista);
    }

    // Retorna o cliente com o id especificado
    @GetMapping(path = "/api/clientes/{id}")
    public ResponseEntity<Cliente> listaClientesPorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarPorId(id);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente);
    }

    // Deleta o cliente com o id especificado
    @DeleteMapping(path = "/api/clientes/{id}")
    public ResponseEntity<Boolean> deletaCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarPorId(id);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        clienteService.excluir(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    // Edita o cliente com o id especificado
    @PutMapping(path = "/api/clientes/{id}")
    public ResponseEntity<Cliente> atualizaCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente,
            BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return ResponseEntity.badRequest().body(null);
        } else {
            Cliente clienteAntigo = clienteService.buscarPorId(id);

            if (clienteAntigo == null) {
                return ResponseEntity.notFound().build();
            }

            clienteAntigo.setName(cliente.getName());
            clienteAntigo.setUsername(cliente.getUsername());
            clienteAntigo.setPassword(encoder.encode(cliente.getPassword()));
            clienteAntigo.setTelefone(cliente.getTelefone());
            clienteAntigo.setSexo(cliente.getSexo());
            clienteAntigo.setDataNasc(cliente.getDataNasc());
            clienteService.salvar(clienteAntigo);
            return ResponseEntity.ok(clienteAntigo);
        }
    }
}
