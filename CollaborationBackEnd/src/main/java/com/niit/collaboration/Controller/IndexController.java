package com.niit.collaboration.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class IndexController {

	@CrossOrigin(origins="http://localhost:8082")
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String getIndexPage() {
		return "index";
	}
		
}
