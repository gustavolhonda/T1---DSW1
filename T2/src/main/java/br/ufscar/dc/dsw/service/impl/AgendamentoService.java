package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IAgendamentoDAO;
import br.ufscar.dc.dsw.domain.Agendamento;
import br.ufscar.dc.dsw.service.spec.IAgendamentoService;

@Service
@Transactional(readOnly = false)
public class AgendamentoService implements IAgendamentoService {

	@Autowired
	IAgendamentoDAO dao;
	
    @Override
	public void salvar(Agendamento agendamento) {
		dao.save(agendamento);
	}

	@Transactional(readOnly = true)
	@Override
	public Agendamento buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

    @Override
	public void excluir(Long id) {
		dao.deleteById(id.longValue());
	}

	@Transactional(readOnly = true)
    @Override
	public List<Agendamento> buscarTodos(){
		return dao.findAll();
	}
}
