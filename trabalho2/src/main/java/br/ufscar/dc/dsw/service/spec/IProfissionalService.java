package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Profissional;

public interface IProfissionalService {

	Profissional buscarPorId(Long id);

	Profissional buscarPorEspecialidade(String especialidade);

	List<Profissional> buscarTodos();

	void salvar(Profissional editora);

	void excluir(Long id);
}
