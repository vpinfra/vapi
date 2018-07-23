package com.vpinfra.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

	@GetMapping("/index")
	public String index(ModelMap model) {
		model.put("username", "张三");
		return "index";
	}
}
