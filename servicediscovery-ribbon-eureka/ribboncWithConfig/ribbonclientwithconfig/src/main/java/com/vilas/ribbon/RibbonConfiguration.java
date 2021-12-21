package com.vilas.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.WeightedResponseTimeRule;

public class RibbonConfiguration {


    @Autowired
    IClientConfig ribbonClientConfig;

    @Bean
    public IPing ribbonPing(IClientConfig config) {
    	System.out.println("Inside Ribbon Ping "+config);
        return new PingUrl();
    }

    @Bean
    public IRule ribbonRule(IClientConfig config) {
       	System.out.println("Inside Ribbon Rule "+config);
    	return new WeightedResponseTimeRule();
    }
}
