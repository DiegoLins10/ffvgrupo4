package com.fatec.scel.model;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends CrudRepository<Emprestimo, Long> {
	@Query("SELECT e FROM Emprestimo e WHERE e.ra = :ra")
	public List<Emprestimo> findByRa(@Param("ra") String ra);
}