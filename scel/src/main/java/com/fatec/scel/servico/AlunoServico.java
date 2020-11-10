package com.fatec.scel.servico;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.scel.model.Aluno;
import com.fatec.scel.model.AlunoRepository;

@Service
public class AlunoServico {

	Logger logger = LogManager.getLogger(AlunoServico.class);
	@Autowired
	private AlunoRepository repository;

	public Iterable<Aluno> findAll() {
		return repository.findAll();
	}

	public Aluno findByRa(String ra) {
		return repository.findByRa(ra);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Aluno findById(Long id) {
		return repository.findById(id).get();
	}

	public void save(Aluno aluno) {
		repository.save(aluno);
	}

	public ModelAndView verificaRaJaExiste(Aluno aluno) {
		ModelAndView modelAndView = new ModelAndView("consultarAluno");
		try {
			Aluno jaExiste = null;
			jaExiste = repository.findByRa(aluno.getRa());
			logger.info("=======> Verifica se o aluno ja existe = " + aluno.getRa());
			if (jaExiste == null) {
				logger.info("=======> Aluno nao cadastrado");
				repository.save(aluno);
				modelAndView.addObject("alunos", repository.findAll());
			} else {
				logger.info("=======> Aluno ja cadastrado");
				modelAndView.setViewName("cadastrarAluno");
				modelAndView.addObject("message", "Aluno ja cadastrado");
			}
		} catch (Exception e) {
			logger.error("========> Exceçao nao prevista - save() cadastra aluno");
			modelAndView.setViewName("cadastrarAluno");
			modelAndView.addObject("message", "Exceçao nao prevista");
		}
		return modelAndView;
	}
}
