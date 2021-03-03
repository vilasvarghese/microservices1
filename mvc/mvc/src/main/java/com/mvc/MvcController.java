package com.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MvcController {

	@RequestMapping("/request")
	public String request() {
		return "first.jsp";
	}
	
	@RequestMapping("/second")
	public String second(HttpServletRequest req, HttpServletResponse res) {
		String name = req.getParameter("name");
		System.out.println(name);
		req.getSession().setAttribute("name", name);
		return "second.jsp";
	}
	
	@RequestMapping("/mvcRequest")
	//This method can take @PathVariable ect..
	public ModelAndView mvcRequest()
	{
		ModelAndView mv = new ModelAndView("mvcview.jsp");
		Employee emp = new Employee("1", "Vilas", 42);
		mv.addObject("empId", emp.getId());
		mv.addObject("empName", emp.getName());
		mv.addObject("empAge", emp.getAge());
		
		return mv;
	}
}
