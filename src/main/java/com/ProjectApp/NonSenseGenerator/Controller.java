package com.ProjectApp.NonSenseGenerator;

import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/")
	public String index(){
		return "Sto tutto SpringBoot";
	}
}


/* 
@SpringBootApplication
public class NonSenseGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(NonSenseGeneratorApplication.class, args);
	}

}

*/
