package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService service;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Usuario usuario) {
		return "usuario/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("usuarios",service.buscarTodos());
		return "usuario/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "usuario/cadastro";
		}

		System.out.println("password = " + usuario.getPassword());
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		service.salvar(usuario);
		attr.addFlashAttribute("success", "usuario.create.success");
		return "redirect:/usuarios/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("usuario", service.buscarPorId(id));
		return "usuario/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Usuario usuario, String novoPassword, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "usuario/cadastro";
		}

		if (novoPassword != null && !novoPassword.trim().isEmpty()) {
			usuario.setPassword(encoder.encode(novoPassword));
		} else {
			System.out.println("Senha não foi editada");
		}
		service.salvar(usuario);
		attr.addFlashAttribute("success", "usuario.edit.success");
		return "redirect:/usuarios/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		Usuario usuario = service.buscarPorId(id);
	
		// Verifica se é um Cliente ou Profissional
		if ("ROLE_CLIENT".equals(usuario.getRole()) || "ROLE_PROFESSIONAL".equals(usuario.getRole())) {
			// Verifica se há agendamentos atrelados ao cliente ou profissional
			if (usuario instanceof Cliente cliente) {
				if (cliente.getAgendamentos() != null && !cliente.getAgendamentos().isEmpty()) {
					attr.addFlashAttribute("fail", "usuario.delete.fail");  // Mensagem de erro
					return "redirect:/usuarios/listar";
				}
			} else if (usuario instanceof Profissional profissional) {
				if (profissional.getAgendamentos() != null && !profissional.getAgendamentos().isEmpty()) {
					attr.addFlashAttribute("fail", "usuario.delete.fail");  // Mensagem de erro
					return "redirect:/usuarios/listar";
				}
			}
		}
	
		// Se não houver agendamentos, procede com a exclusão
		service.excluir(id);
		attr.addFlashAttribute("success", "usuario.delete.success");
		return "redirect:/usuarios/listar";
	}
	
}
