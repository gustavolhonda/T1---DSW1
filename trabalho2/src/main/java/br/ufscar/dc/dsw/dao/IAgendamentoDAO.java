package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Agendamento;
import br.ufscar.dc.dsw.domain.Usuario;

@SuppressWarnings("unchecked")
public interface IAgendamentoDAO extends CrudRepository<Agendamento, Long>{

	Agendamento findById(long id);

	List<Agendamento> findAllByUsuario(Usuario u);
	
	Agendamento save(Agendamento agendamento);
}