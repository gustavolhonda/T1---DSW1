package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Agendamento;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;

public interface IAgendamentoService {

	Agendamento buscarPorId(Long id);
	
	void salvar(Agendamento agendamento);
}
