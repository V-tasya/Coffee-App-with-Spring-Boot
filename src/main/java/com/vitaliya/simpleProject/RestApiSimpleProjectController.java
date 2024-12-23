import com.vitaliya.simpleProject.Coffee;
import com.vitaliya.simpleProject.CoffeeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coffees")
public class RestApiSimpleProjectController {
    private final CoffeeRepository coffeeRepository;
    @Value("${app.version}")
    private String appVersion;

    public RestApiSimpleProjectController(CoffeeRepository coffeeRepository,  @Value("${app.default-coffee}") String defaultCoffee) {
        this.coffeeRepository = coffeeRepository;

        this.coffeeRepository.saveAll(List.of(
                new Coffee(defaultCoffee),
                new Coffee("Ganador"),
                new Coffee("Lareno"),
                new Coffee("Tres Pontas")
        ));
    }

    @GetMapping("/version")
    String getAppVersion() {
        return "Application version: " + appVersion;
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