package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Agendamento;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Profissional;

@SuppressWarnings("unchecked")
public interface IAgendamentoDAO extends CrudRepository<Agendamento, Long>{

	Agendamento findById(long id);
	
	Agendamento save(Agendamento agendamento);
}