package br.ufscar.dc.dsw.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


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
		System.out.println("ID recebido pelo PathVariable: " + id);
		Profissional profissional = profissionalService.buscarPorId(id);
    	agendamento.setProfissional(profissional);
    	model.addAttribute("agendamento", agendamento);
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
	public String salvar(@RequestParam("dataHora") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String dataHoraString,
        @ModelAttribute Agendamento agendamento,ModelMap model, BindingResult result, RedirectAttributes attr) {


		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    	LocalDateTime dataHora = LocalDateTime.parse(dataHoraString, formatter);

		if (dataHora.isBefore(LocalDateTime.now())) {
			result.rejectValue("dataHora", "error.agendamento", "A data deve ser futura.");
		}

		LocalTime hora = dataHora.toLocalTime();
		LocalTime inicio = LocalTime.of(8, 0);
		LocalTime fim = LocalTime.of(18, 0);
		if (hora.isBefore(inicio) || hora.isAfter(fim)) {
			result.rejectValue("dataHora", "error.agendamento", "O horário deve ser entre 08:00 e 18:00.");
		}
		if (hora.getMinute() != 0) {
			result.rejectValue("dataHora", "error.agendamento", "O horário deve terminar em 00.");
		}
		
		if (result.hasErrors()) {
			model.addAttribute("agendamento", agendamento);
			return "agendamento/form";
		}

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
