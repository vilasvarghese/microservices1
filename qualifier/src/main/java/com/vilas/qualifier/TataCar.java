package com.vilas.qualifier;

import org.springframework.stereotype.Component;

@Component("tataCar")
public class TataCar implements Car{
	@Override
	public String drive() {
		return "Drive Tata Car";	
	}
}
