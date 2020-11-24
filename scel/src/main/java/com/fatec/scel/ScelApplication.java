package com.fatec.scel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fatec.scel.model.Aluno;
import com.fatec.scel.model.AlunoRepository;
import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;

@SpringBootApplication
public class ScelApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(ScelApplication.class);
	@Autowired
	LivroRepository repositoryL;
	@Autowired
	AlunoRepository repositoryA;

	public static void main(String[] args) {
		SpringApplication.run(ScelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
// cadastrar alguns alunos
		repositoryA.save(new Aluno("1111", "Jose Silva", "jose@gmail.com", "08545160", "Rua Carlos de Carvalho, 200"));
// cadastra alguns livros
		repositoryL.save(new Livro("1111", "Engenharia de Software", "Pressman"));
		repositoryL.save(new Livro("2222", "Engenharia de Software", "Sommerville"));
// consultar todos os livros
		log.info("Livros encontrados utilizando findAll():");
		log.info("-------------------------------");
		for (Livro livro : repositoryL.findAll()) {
			log.info(livro.toString());
		}
		log.info("");
	}
}