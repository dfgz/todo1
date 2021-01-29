package com.tienda.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/home")
	public String index(Model model) {
		model.addAttribute("name", "jancsi");
		return "index";
	}

}
