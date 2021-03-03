package com.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Property4Controller {

	@Value("#{${electionRule}}")
	Map<String, String> electionRules;
	
	@RequestMapping("/electionRules")
	public String electionRule() {
		return electionRules.toString();
	}
}
