package br.ufscar.dc.dsw.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Agendamento;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.IAgendamentoService;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {
    
    @Autowired
	private IAgendamentoService agendamentoService;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IProfissionalService profissionalService;

    @GetMapping("/cadastrar")
	public String cadastrar(Agendamento agendamento, ModelMap model) {
		model.addAttribute("profissionais",profissionalService.buscarTodos());
		return "agendamento/cadastro";
	}

    @GetMapping("/adicionar/{id}")
	public String adicionar(@PathVariable("id") Long id, ModelMap model, Agendamento agendamento) {
		Profissional profissional = profissionalService.buscarPorId(id);
		model.addAttribute("profissional", profissional);
		return "agendamento/form";
	}

    @GetMapping("/listar")
	public String listar(ModelMap model) {
		Usuario user = getUsuario();

		if ("ROLE_CLIENT".equals(user.getRole())) {
			Cliente cliente = clienteService.buscarPorId(user.getId());
			model.addAttribute("agendamentos", cliente.getAgendamentos());
		} else {
			Profissional profissional = profissionalService.buscarPorId(user.getId());
			model.addAttribute("agendamentos", profissional.getAgendamentos());
		}
		return "agendamento/lista";
	}

    @PostMapping("/salvar")
	public String salvar(Agendamento agendamento,ModelMap model, BindingResult result, RedirectAttributes attr) {

		Usuario user = getUsuario();

		Cliente cliente = clienteService.buscarPorId(user.getId());
		agendamento.setCliente(cliente);

		Profissional profissional = profissionalService.buscarPorId(agendamento.getProfissional().getId());
    agendamento.setProfissional(profissional);
		
		agendamento.setLinkVideoConferencia("https://videoconferencia.example.com/" + new java.util.Random().nextInt(1000000));

		agendamentoService.salvar(agendamento);
		attr.addFlashAttribute("sucess", "agendamento.create.sucess");
		return "redirect:/agendamentos/listar";
	}

    private Usuario getUsuario() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioDetails.getUsuario();
	}

}
