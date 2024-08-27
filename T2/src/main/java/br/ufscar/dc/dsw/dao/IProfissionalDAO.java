package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Profissional;

@SuppressWarnings("unchecked")
public interface IProfissionalDAO extends CrudRepository<Profissional, Long>{

	Profissional findById(long id);
	
	Profissional findByEspecialidade(String especialidade);

	List<Profissional> findAllByEspecialidade(String especialidade);

    @Query("SELECT DISTINCT p.especialidade FROM Profissional p")
    List<String> findDistinctEspecialidades();
	
	List<Profissional> findAll();
	
	Profissional save(Profissional profissional);

	void deleteById(Long id);
}
