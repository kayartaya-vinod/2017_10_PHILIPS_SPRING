package training.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public String sayHello() {
		System.out.println("Got a request from the client!");
		return "/WEB-INF/pages/hello.jsp";
	}
}
