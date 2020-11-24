package com.fatec.scel.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scel.model.Aluno;
import com.fatec.scel.model.Endereco;
import com.fatec.scel.servico.AlunoServico;

@Controller
@RequestMapping(path = "/aluno")
public class AlunoController {
	Logger logger = LogManager.getLogger(AlunoController.class);
	@Autowired
	AlunoServico servico;

	@GetMapping("/consultar")
	public ModelAndView retornaFormDeConsultaTodosAlunos() {
		ModelAndView modelAndView = new ModelAndView("consultarAluno");
		modelAndView.addObject("alunos", servico.findAll());
		return modelAndView;
	}

	@GetMapping("/cadastrar")
	public ModelAndView retornaFormCadastraDe(Aluno aluno) {
		ModelAndView mv = new ModelAndView("cadastrarAluno");
		mv.addObject("aluno", aluno);
		return mv;
	}

	@GetMapping("/edit/{ra}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarAluno(@PathVariable("ra") String ra) {
		ModelAndView modelAndView = new ModelAndView("atualizarAluno");
		modelAndView.addObject("aluno", servico.findByRa(ra)); // o repositorio e injetado no controller
		return modelAndView; // addObject adiciona objetos para view
	}

	@GetMapping("/delete/{id}")
	public ModelAndView excluirNoFormDeConsultaTodosAlunos(@PathVariable("id") Long id) {
		servico.deleteById(id);
		ModelAndView modelAndView = new ModelAndView("consultarAluno");
		modelAndView.addObject("alunos", servico.findAll());
		return modelAndView;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Aluno aluno, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarAluno");
		if (result.hasErrors()) {
			modelAndView.setViewName("cadastrarAluno");
		} else {
			try {
				String endereco = obtemEndereco(aluno.getCep());
				if (endereco != "") {
					logger.info("consulta cep valido antes do save ==> " + endereco.toString());
					aluno.setEndereco(endereco);
					servico.save(aluno);
					modelAndView.addObject("alunos", servico.findAll());
				}
			} catch (Exception e) { // captura validacoes na camada de persistencia
				modelAndView.setViewName("cadastrarAluno");
				modelAndView.addObject("message", "Aluno já cadastrado");
				logger.error("erro nao esperado ==> " + e.getMessage());
			}
		}
		return modelAndView;
	}

	@PostMapping("/update/{id}")
	public ModelAndView atualizaAluno(@PathVariable("id") Long id, @Valid Aluno aluno, BindingResult result) {
		if (result.hasErrors()) {
			aluno.setId(id);
			return new ModelAndView("atualizarAluno");
		}
		Aluno umAluno = servico.findById(id);
		umAluno.setRa(aluno.getRa());
		umAluno.setNome(aluno.getNome());
		umAluno.setEmail(aluno.getEmail());
		umAluno.setCep(aluno.getCep());
        umAluno.setEndereco(obtemEndereco(aluno.getCep()));
		servico.save(umAluno);
		ModelAndView modelAndView = new ModelAndView("consultarAluno");
		modelAndView.addObject("alunos", servico.findAll());
		return modelAndView;
	}

	public String obtemEndereco(String cep) {
		RestTemplate template = new RestTemplate();
		String url = "https://viacep.com.br/ws/{cep}/json/";
		Endereco endereco = template.getForObject(url, Endereco.class, cep);
		logger.info("obtem endereco ==> " + endereco.toString());
		return endereco.getLogradouro();
	}
}