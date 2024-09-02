package br.ufscar.dc.dsw.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.dc.dsw.domain.Agendamento;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IAgendamentoService;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;

@RestController
public class AgendamentoRestController {

	@Autowired
	private IAgendamentoService service;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IProfissionalService profissionalService;
	
	@GetMapping(path = "/api/consultas/")
	public ResponseEntity<List<Agendamento>> lista() {
		List<Agendamento> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

    @GetMapping(path = "/api/consultas/{id}")
	public ResponseEntity<Agendamento> listaPorId(@PathVariable("id") long id) {
		Agendamento agendamento = service.buscarPorId(id);
		if (agendamento.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(agendamento);
	}

    @GetMapping(path = "/api/consultas/clientes/{id}")
	public ResponseEntity<List<Agendamento>> listaClientesPorId(@PathVariable("id") long id) {
		Cliente cliente = clienteService.buscarPorId(id);
		if (cliente.getAgendamentos().isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente.getAgendamentos());
	}

    @GetMapping(path = "/api/consultas/profissionais/{id}")
	public ResponseEntity<List<Agendamento>> listaProfissionaisPorId(@PathVariable("id") long id) {
		Profissional profissional = profissionalService.buscarPorId(id);
		if (profissional.getAgendamentos().isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(profissional.getAgendamentos());
	}
}
