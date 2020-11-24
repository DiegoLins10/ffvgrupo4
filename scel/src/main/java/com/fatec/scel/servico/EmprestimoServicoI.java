package com.fatec.scel.servico;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fatec.scel.controller.EmprestimoController;
import com.fatec.scel.model.Aluno;
import com.fatec.scel.model.AlunoRepository;
import com.fatec.scel.model.Emprestimo;
import com.fatec.scel.model.EmprestimoRepository;
import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;

@Service
public class EmprestimoServicoI implements EmprestimoServico {
	Logger logger = LogManager.getLogger(EmprestimoController.class);
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private AlunoRepository alunoRepository;

	public String save(Emprestimo emprestimo) {
		String mensagem = "";
		try {
			List<Emprestimo> emprestimos = findByRa(emprestimo.getRa());
			if (emprestimosEmAberto(emprestimos) == false
					&& existeAlunoLivro(emprestimo.getRa(), emprestimo.getIsbn()) == true) {
				logger.info(
						"======================> achou livro/aluno no db e nao existe emprestimo em aberto/cadastrado");
				DateTime dataAtual = new DateTime();
				emprestimo.setDataEmprestimo(dataAtual);
				emprestimoRepository.save(emprestimo);
				mensagem = "Emprestimo registrado";
			} else {
				logger.info("======================> não achou livro/aluno no db");
				mensagem = "Livro/Aluno não localizado ou emprestimo em aberto";
			}
		} catch (Exception e) {
			logger.info("erro nao esperado no cadastro de emprestimo ===> " + e.getMessage());
			mensagem = "Erro não esperado contacte o administrador";
		}
		return mensagem;
	}
	public String devolucao(Emprestimo emprestimo) {
		String mensagem = "";
		try {
			List<Emprestimo> emprestimos = findByRa(emprestimo.getRa());
			if (emprestimosEmAberto(emprestimos) == true) {
				logger.info(
						"======================> existe emprestimo em aberto");
				DateTime dataAtual = new DateTime();
				emprestimo.setDataDevolucao(dataAtual);
				emprestimoRepository.save(emprestimo);
				mensagem = "Devolucao registrada";
			} else {
				logger.info("======================> não achou livro/aluno no db");
				mensagem = "Livro/Aluno não localizado ou emprestimo não há emprestimo em aberto";
			}
		} catch (Exception e) {
			logger.info("erro nao esperado no cadastro de emprestimo ===> " + e.getMessage());
			mensagem = "Erro não esperado contacte o administrador";
		}
		return mensagem;
	}

	public Iterable<Emprestimo> findAll() {
		return emprestimoRepository.findAll();
	}

	public void deleteById(Long id) {
		emprestimoRepository.deleteById(id);
	}

	public List<Emprestimo> findByRa(String ra) {
		return emprestimoRepository.findByRa(ra);
	}

	public boolean emprestimosEmAberto(List<Emprestimo> emprestimos) {
		boolean existe = false;
		for (Emprestimo umEmprestimo : emprestimos) {
			if (umEmprestimo.getDataDevolucao() == null) {
				existe = true;
			}
		}
		logger.info("=====================> achei emprestimos em aberto?" + existe);
		return existe;
	}

	public boolean existeAlunoLivro(String ra, String isbn) {
		Aluno aluno = alunoRepository.findByRa(ra);
		Livro livro = livroRepository.findByIsbn(isbn);
		if (aluno != null && livro != null) {
			return true;
		}
		return false;
	}
}