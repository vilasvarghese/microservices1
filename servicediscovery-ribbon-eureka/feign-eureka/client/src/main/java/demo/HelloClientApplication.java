package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @modifiedBy Vilas Varghese
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class HelloClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloClientApplication.class, args);
	}

}

@RestController
class HelloController{
	@Autowired
	HelloClient client;

	@RequestMapping("/")
	public String hello() {
		return client.hello();
	}
	
	@RequestMapping("/vilas")
	public String vilas() {
		return client.vilasp();
	}
	
}

@FeignClient("HelloServer")
interface HelloClient {
	@RequestMapping(value = "/", method = GET)
	String hello();
	
	@RequestMapping(value = "/vilasp", method = GET)
	String vilasp();
}