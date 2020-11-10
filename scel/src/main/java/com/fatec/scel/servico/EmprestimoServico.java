package com.fatec.scel.servico;

import java.util.List;
import com.fatec.scel.model.Emprestimo;

public interface EmprestimoServico {
	public String save(Emprestimo emprestimo);

	public Iterable<Emprestimo> findAll();

	public void deleteById(Long id);

	public List<Emprestimo> findByRa(String ra);

	public boolean emprestimosEmAberto(List<Emprestimo> emprestimos);
}