package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.Agendamento;

public interface IAgendamentoService {

	Agendamento buscarPorId(Long id);
	
	void salvar(Agendamento agendamento);

	void excluir(Long id);
}
