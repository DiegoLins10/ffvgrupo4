package com.fatec.scel.controller;

import java.util.List;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.scel.model.Aluno;
import com.fatec.scel.model.Emprestimo;
import com.fatec.scel.model.Livro;
import com.fatec.scel.servico.EmprestimoServico;

@Controller
@RequestMapping(path = "/emprestimos")
public class EmprestimoController {
	Logger logger = LogManager.getLogger(EmprestimoController.class);
	@Autowired
	EmprestimoServico emprestimoServico;

	@GetMapping("/registrar")
	public ModelAndView registrarEmprestimo(Emprestimo emprestimo) {
		logger.info("==============> chamada do menu para classe controller");
		ModelAndView mv = new ModelAndView("registrarEmprestimo");
		mv.addObject("emprestimo", emprestimo);
		return mv;
	}

	@GetMapping("/registrarDevolucao")
	public ModelAndView registrarDevolucao(Emprestimo emprestimo) {
		logger.info("==============> chamada do menu para classe controller");
		ModelAndView mv = new ModelAndView("devolucaoEmprestimo");
		mv.addObject("emprestimo", emprestimo);
		return mv;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Emprestimo emprestimo, BindingResult result) {
		logger.info("=================> chamada da pagina registrar emprestimo para controller");
		String mensagem = "";
		ModelAndView modelAndView = new ModelAndView("registrarEmprestimo");
		mensagem = emprestimoServico.save(emprestimo);
		modelAndView.addObject("message", mensagem);
		return modelAndView;
	}

	@PostMapping("/atualizarDevolucao")
	public ModelAndView atualizarDevolucao(@Valid Emprestimo emprestimo, BindingResult result) {
		logger.info("=================> chamada da pagina registrar emprestimo para controller");
		String mensagem = "";
		ModelAndView modelAndView = new ModelAndView("devolucaoEmprestimo");
		mensagem = emprestimoServico.devolucao(emprestimo);
		modelAndView.addObject("message", mensagem);
		return modelAndView;
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		emprestimoServico.deleteById(id);
		ModelAndView modelAndView = new ModelAndView("consultarEmprestimo");
		modelAndView.addObject("emprestimos", emprestimoServico.findAll());
		return modelAndView;
	}

	@GetMapping("/consultar")
	public ModelAndView retornaFormDeConsultaTodosEmprestimos() {
		ModelAndView modelAndView = new ModelAndView("consultarEmprestimo");
		modelAndView.addObject("emprestimos", emprestimoServico.findAll());
		return modelAndView;
	}

	/*
	 * @GetMapping("/consultarDevolucao") public ModelAndView
	 * retornaFormDeConsultarEmprestimosAbertos(@PathVariable("ra") String ra) {
	 * ModelAndView modelAndView = new ModelAndView("devolucaoEmprestimo");
	 * modelAndView.addObject("emprestimos",
	 * emprestimoServico.emprestimosEmAberto(emprestimos)); return modelAndView; }
	 */
	@Controller
	public class DevolucaoController {
		Emprestimo emp = new Emprestimo();

		@RequestMapping(value = "/devolucao", method = RequestMethod.GET)

		public String fim() {
			return "devolucao";
		}

	}

	@GetMapping("/entregue")
	public ModelAndView entrega() {
		ModelAndView modelAndView = new ModelAndView("ConsultarEmprestimo");
		modelAndView.addObject("emprestimos", emprestimoServico.findAll());
		return modelAndView;

	}

}