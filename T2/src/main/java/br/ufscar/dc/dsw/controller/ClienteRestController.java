package br.ufscar.dc.dsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IClienteService;

@RestController
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

    
    
    @GetMapping(path = "/api/clientes")
    public ResponseEntity<List<Cliente>> listaClientes() {
        List<Cliente> lista = clienteService.buscarTodos();

        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/api/clientes/{id}")
    public ResponseEntity<Cliente> listaClientesPorId(Long id) {
        Cliente cliente = clienteService.buscarPorId(id);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente);
    }
    
}
