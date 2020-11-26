package com.fatec.scel.servico;

import java.util.List;

import com.fatec.scel.model.Aluno;
import com.fatec.scel.model.Emprestimo;
import com.fatec.scel.model.EmprestimoRepository;
import com.fatec.scel.model.Livro;

public interface EmprestimoServico {
	public String save(Emprestimo emprestimo);

	public String devolucao(Emprestimo emprestimo);

	public Iterable<Emprestimo> findAll();

	public void deleteById(Long id);
	
	public List<Emprestimo> findByRa(String ra);

	public boolean emprestimosEmAberto(List<Emprestimo> emprestimos);

	public String registraDevolucao(String ra); // supoe que ra refere-se ao tombo

	public void entregue();
	
	public Emprestimo findById(Long id); 
}