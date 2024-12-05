package com.vitaliya.simpleProject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@SpringBootApplication
public class SimpleProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleProjectApplication.class, args);
	}

}

@Entity
class Coffee {
	@Id
	private String id;
	private String name;

	public Coffee(){}

	public Coffee(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Coffee(String name) {
		this(UUID.randomUUID().toString(), name);
	} // calling other constructor with unique id(generated id) and the name

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {this.id = id;}
}

@RestController
@RequestMapping("/coffees")
class RestApiSimpleProjectController {
	private final CoffeeRepository coffeeRepository;

	public RestApiSimpleProjectController(CoffeeRepository coffeeRepository) {
		this.coffeeRepository = coffeeRepository;

		this.coffeeRepository.saveAll(List.of(
				new Coffee("Cereza"),
				new Coffee("Ganador"),
				new Coffee("Lareno"),
				new Coffee("Tres Pontas")
		));
	}

	@GetMapping
	Iterable<Coffee> getCoffees() {
		return  coffeeRepository.findAll();
	}

	@GetMapping("/{id}")
		//@PathVariable points that the value of variable in method is taken from the part of the request (@GetMapping("/coffees/{id}"))
		Optional<Coffee> getCoffeeById(@PathVariable String id) {
			return  coffeeRepository.findById(id);
		}


	@PostMapping
	Coffee postCoffee(@RequestBody Coffee coffee) {
		//Here coffee was automaticaly created from body's request
		return coffeeRepository.save(coffee);
	}

	@PutMapping("/{id}")
	ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
		// object ResponseEntity contains object coffee and the code of stay HTTP:201 if object was created or HTTP: 200 if object was updated
		return (!coffeeRepository.existsById(id)) ? new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.CREATED)
				: new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	void deleteCoffee(@PathVariable String id) {
		coffeeRepository.deleteById(id);
	}
}
