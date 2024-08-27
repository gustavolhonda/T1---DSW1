package br.ufscar.dc.dsw.dao;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Agendamento;

@SuppressWarnings("unchecked")
public interface IAgendamentoDAO extends CrudRepository<Agendamento, Long>{

	Agendamento findById(long id);
	
	Agendamento save(Agendamento agendamento);

	Agendamento deleteById(long id);

}