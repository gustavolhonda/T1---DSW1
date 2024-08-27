package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/cadastrar")
	public String cadastrar(Cliente cliente) {
		return "cliente/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("clientes", clienteService.buscarTodos());
		return "cliente/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {

		//if (result.hasErrors()) {
		//	return "cliente/cadastro";
		//}

		cliente.setRole("ROLE_CLIENT");
		cliente.setEnabled(true);
		clienteService.salvar(cliente);
		attr.addFlashAttribute("success", "cliente.create.success");
		return "redirect:/clientes/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cliente", clienteService.buscarPorId(id));
		return "cliente/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {

		//if (result.hasErrors()) {
		//	return "cliente/cadastro";
		//}

		cliente.setRole("ROLE_CLIENT");
		cliente.setEnabled(true);
		clienteService.salvar(cliente);
		attr.addFlashAttribute("success", "cliente.edit.suscess");
		return "redirect:/clientes/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		Cliente cliente = clienteService.buscarPorId(id);
		
		// Verifica se o cliente tem agendamentos
		if (cliente.getAgendamentos() != null && !cliente.getAgendamentos().isEmpty()) {
			// Adiciona uma mensagem de erro se houver agendamentos
			attr.addFlashAttribute("fail", "cliente.delete.fail");
			return "redirect:/clientes/listar";
		}
	
		// Se não houver agendamentos, procede com a exclusão
		clienteService.excluir(id);
		attr.addFlashAttribute("success", "cliente.delete.success");
		return "redirect:/clientes/listar";
	}
	
}
