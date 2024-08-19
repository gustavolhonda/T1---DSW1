package br.ufscar.dc.dsw.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Agendamento;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IAgendamentoService;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;


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
	public String cadastrar(Agendamento agendamento) {
		return "agendamento/cadastro";
	}

    @GetMapping("/listar")
	public String listar(ModelMap model) {
    Usuario user = getUsuario();

		if (user.getRole() == "ROLE_CLIENT") {
            Cliente cliente = clienteService.buscarPorId(user.getId());
			model.addAttribute("agendamento", cliente.getAgendamentos());
		} else {
			Profissional profissional = profissionalService.buscarPorId(user.getId());
			model.addAttribute("agendamento", profissional.getAgendamentos());
		}
		return "agendamento/lista";
	}

    @PostMapping("/salvar")
	public String salvar(Agendamento agendamento, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "agendamento/cadastro";
		}
		
		agendamentoService.salvar(agendamento);
		attr.addFlashAttribute("sucess", "agendamentos.create.sucess");
		return "redirect:/agendamentos/listar";
	}

    private Usuario getUsuario() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioDetails.getUsuario();
	}

}
