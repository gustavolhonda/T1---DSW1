package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Agendamento;

public interface IAgendamentoService {

	Agendamento buscarPorId(Long id);
	
	void salvar(Agendamento agendamento);

	void excluir(Long id);

	List<Agendamento> buscarTodos();
	
}
