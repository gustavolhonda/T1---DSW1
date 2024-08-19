package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Agendamento;
import br.ufscar.dc.dsw.domain.Usuario;

public interface IAgendamentoService {

	Agendamento buscarPorId(Long id);

	List<Agendamento> buscarTodosPorUsuario(Usuario u);
	
	void salvar(Agendamento agendamento);
}
