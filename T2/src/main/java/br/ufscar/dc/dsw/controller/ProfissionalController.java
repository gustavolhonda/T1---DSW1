package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.FileEntity;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IFileService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {
	
	@Autowired
	private IProfissionalService service;

	@Autowired
	private IFileService fileService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping("/cadastrar")
	public String cadastrar(Profissional profissional) {
		return "profissional/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("profissionais",service.buscarTodos());
		return "profissional/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {
		
		//if (result.hasErrors()) {
		//	return "profissional/cadastro";
		//}
		//System.out.println(profissional.getCPF());

		profissional.setRole("ROLE_PROFESSIONAL");
		profissional.setEnabled(true);
		profissional.setPassword(encoder.encode(profissional.getPassword()));
		service.salvar(profissional);
		attr.addFlashAttribute("success", "profissionais.create.success");
		return "redirect:/profissionais/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("profissional", service.buscarPorId(id));
		return "profissional/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Profissional profissional, String novoPassword, BindingResult result, RedirectAttributes attr) {
		
		//if (result.hasErrors()) {
		//	return "profissional/cadastro";
		//}

		if (novoPassword != null && !novoPassword.trim().isEmpty()) {
			profissional.setPassword(encoder.encode(novoPassword));
		} else {
			System.out.println("Senha não foi editada");
		}
		
		profissional.setRole("ROLE_PROFESSIONAL");
		profissional.setEnabled(true);
		service.salvar(profissional);
		attr.addFlashAttribute("success", "profissionais.edit.success");
		return "redirect:/profissionais/listar";
	}

	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attr) throws IOException {
				
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileEntity entity = new FileEntity(fileName, file.getContentType(), file.getBytes());
		
		fileService.salvar(entity);
		
		attr.addFlashAttribute("success", "File " + fileName + " has uploaded successfully!");
		return "redirect:/";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		Profissional profissional = service.buscarPorId(id);
		
		// Verifica se o cliente tem agendamentos
		if (profissional.getAgendamentos() != null && !profissional.getAgendamentos().isEmpty()) {
			// Adiciona uma mensagem de erro se houver agendamentos
			attr.addFlashAttribute("fail", "profissionais.delete.fail");
			return "redirect:/profissionais/listar";
		}
	
		// Se não houver agendamentos, procede com a exclusão
		service.excluir(id);
		attr.addFlashAttribute("success", "profissionais.delete.success");
		return "redirect:/profissionais/listar";
	}
}
