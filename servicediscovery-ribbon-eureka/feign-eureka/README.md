# feign-eureka
Example of using feign with eureka

Eureka server should be running on http://localhost:8761

## building

mvn clean install

## hello server

run `java -jar server/target/feign-eureka-hello-server-0.0.1-SNAPSHOT.jar`

verify 
	it is functioning at 
		[http://localhost:7111](http://localhost:7111)
	Eureka is listing
		HelloServer
		
You should see `Hello World: HelloServer:myhostname:7111`

## hello client

run `java -jar client/target/feign-eureka-hello-client-0.0.1-SNAPSHOT.jar`

verify it is functioning at [http://localhost:7211](http://localhost:7211)

You should see `Hello World: HelloServer:myhostname:7111`

### hello client error

You may see an error while the eureka/loadbalancer caches warm up similar to the following:

    Load balancer does not contain an instance for the service HelloServer

It should go away shortly.

## See round robin load-balancing in action

run `java -jar server/target/feign-eureka-hello-server-0.0.1-SNAPSHOT.jar --server.port=7112`

Go back to [http://localhost:7211](http://localhost:7211) and you should see both ports `7111` and `7112` in the output after a minute or two as you keep refreshing.


# How do you build this.
This includes two repo's - two pom.xml inside
	client
	server
	
	Both the above classes are 
		EurekaClients
			application.yml is coded for the same.
		Have application and controller coded together
		
	Server is a simple producer - doesn't have anything related to Feign.
	
	Client
		Has an interface
-----------------------		
	@FeignClient("HelloServer") //HelloServer: name with which producer is listed on Eureka.
	interface HelloClient { //Can be any name
		@RequestMapping(value = "/", method = GET) //end point exposed by the producer
		String hello();//Can be any name
		
		@RequestMapping(value = "/vilasp", method = GET)//end point exposed by the producer
		String vilasp();//Can be any name
	}
-----------------------	
			
	The implementation of this interface is done by Feign
	We can autowire a FeignClient 
	
@Why password in application.yml is not working
	https://github.com/SteeltoeOSS/Discovery/issues/12
