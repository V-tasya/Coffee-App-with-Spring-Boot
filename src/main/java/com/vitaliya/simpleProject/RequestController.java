import com.vitaliya.simpleProject.RequestReady;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request")
public class RequestController {
    private final RequestReady requestReady;

    public RequestController(RequestReady rr){
        this.requestReady = rr;
    }

    @GetMapping("/name")
    String getRequestReady() {
        return "Name: " + requestReady.getName();
    }

    @GetMapping("/order")
    String getNameAndRequest() {
        return requestReady.getRequest();
    }
}