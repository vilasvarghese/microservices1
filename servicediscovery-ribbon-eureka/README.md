# Spring-cloud-eureka
How to use Eureak registry service and how can we register our application on Eureak server that we can access microservices using endpoint url


Create a normal euereka server 
	Reference1: servicediscovery\eureka\eurekaserver
	Reference2: servicediscovery-ribbon-eureka\eureka-server

Create a normal eureka producer
	Reference1: servicediscovery\eureka\companyservice
	Reference2: servicediscovery-ribbon-eureka\eureka-producer

		start multiple instances of eureka server
			java -jar -Dserver.port=<port number> <jar>
			
Create a eureka client
	Reference: servicediscovery-ribbon-eureka\ribbon-eureka-client
	
	pom.xml should include
---------------------------------------------------	
			<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-netflix-ribbon -->
		<dependency>
		    <groupId>com.netflix.ribbon</groupId>
		    <artifactId>ribbon</artifactId>
		    <version>2.2.2</version>
---------------------------------------------------

Application.java should 
	NOT have @EurekaClient
	have
		@EnableDiscoveryClient
	//@LoadBalanced is NOT required from RestTemplate. Can be a normal instance
	
---------------------------------------------------
Controller should
	Autowire loadbalancer
		@Autowired
		private LoadBalancerClient loadBalancer;
		
		
	get the url using the following method


	@GetMapping("/amazon-payment/{price}")
	public String invokePaymentService(@PathVariable int price) {
		//String url = "http://PAYMENT-SERVICE/payment-provider/payNow/" + price;
		String url = getUrl() + price;
		System.out.println("Entire url "+url);
		return template.getForObject(url, String.class);
	}
	
	
	Here PAYMENT-SERVICE is the application name in eureka producer.
	Eureka server would be listing this name on UI.
	
	Hit the url and keep refreshing few times to see the magic.
	