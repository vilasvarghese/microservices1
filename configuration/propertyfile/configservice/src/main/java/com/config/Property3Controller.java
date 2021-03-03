package com.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Property3Controller {


	@Value("${states.list}")
	private List<String> statesList;
	
	@RequestMapping("/stateList")
	public String listStates() {
		return statesList.toString();
	}
	
	
}
