package com.fatec.scel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/login")
	public String autenticacao() {
		return "/paginaLogin";
	}

	@GetMapping("/")
	public String home() {
		return "/paginaMenu";
	}
}