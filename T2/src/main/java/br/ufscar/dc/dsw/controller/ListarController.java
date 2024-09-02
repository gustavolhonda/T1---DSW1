package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufscar.dc.dsw.service.spec.IProfissionalService;

@Controller
@RequestMapping("/listar")
public class ListarController {
	
	@Autowired
	private IProfissionalService service;
	
	@GetMapping
	public String listar(ModelMap model) {
		model.addAttribute("profissionais",service.buscarTodos());
		return "lista";
	}
}
