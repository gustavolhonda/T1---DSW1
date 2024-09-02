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

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import jakarta.validation.Valid;

@RestController
public class ProfissionalRestController {

    @Autowired
    private IProfissionalService profissionalService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    // Cria profissional
    @PostMapping(path = "/api/profissionais")
    @ResponseBody
    public ResponseEntity<Profissional> cria(@Valid @RequestBody Profissional profissional, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return ResponseEntity.badRequest().body(null);
        } else {
            profissional.setRole("ROLE_PROFESSIONAL");
            profissional.setEnabled(true);
            profissional.setPassword(encoder.encode(profissional.getPassword()));
            profissionalService.salvar(profissional);
            return ResponseEntity.ok(profissional);
        }
    }

    // Retorna a lista de profissionais
    @GetMapping(path = "/api/profissionais")
    public ResponseEntity<List<Profissional>> listaProfissionais() {
        List<Profissional> lista = profissionalService.buscarTodos();

        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(lista);
    }

    // Retorna o profissional com o id especificado
    @GetMapping(path = "/api/profissionais/{id}")
    public ResponseEntity<Profissional> listaProfissionaisPorId(@PathVariable Long id) {
        Profissional profissional = profissionalService.buscarPorId(id);

        if (profissional == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(profissional);
    }

    // Retorna a lista de profissionais com a especialidade especificada
    @GetMapping(path = "/api/profissionais/especialidades/{especialidade}")
    public ResponseEntity<List<Profissional>>listaProfissionaisPorEspecialidade(@PathVariable String especialidade) {
        List<Profissional> listaProfissionais = profissionalService.buscarPorEspecialidade(especialidade);

        if (listaProfissionais.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listaProfissionais);
    }

    // Deleta o profissional com o id especificado
    @DeleteMapping(path = "/api/profissionais/{id}")
    public ResponseEntity<Boolean> deletaProfissional(@PathVariable Long id) {
        Profissional profissional = profissionalService.buscarPorId(id);

        if (profissional == null) {
            return ResponseEntity.notFound().build();
        }

        profissionalService.excluir(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    // Edita o profissional com o id especificado
    @PutMapping(path = "/api/profissionais/{id}")
    public ResponseEntity<Profissional> atualizaProfissional(@PathVariable Long id, @Valid @RequestBody Profissional profissional,
            BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return ResponseEntity.badRequest().body(null);
        } else {
            Profissional profissionalAntigo = profissionalService.buscarPorId(id);

            if (profissionalAntigo == null) {
                return ResponseEntity.notFound().build();
            }

            profissionalAntigo.setName(profissional.getName());
            profissionalAntigo.setUsername(profissional.getUsername());
            profissionalAntigo.setPassword(encoder.encode(profissional.getPassword()));
            profissionalAntigo.setCPF(profissional.getCPF());
            profissionalAntigo.setEspecialidade(profissional.getEspecialidade());
            profissionalService.salvar(profissionalAntigo);
            return ResponseEntity.ok(profissionalAntigo);
        }
    }
}
