package v.show.backend.config.swagger;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
@Api(value = "Swagger-API", tags = {"Index"})
public class SwaggerController {

    @GetMapping("/docs")
    public String swagger() {
        return "redirect:/swagger-ui.html";
    }

    @GetMapping("/")
    public String knif4j() {
        return "redirect:/doc.html";
    }
}
