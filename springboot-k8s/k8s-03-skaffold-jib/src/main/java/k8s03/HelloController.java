package k8s03;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    @RequestMapping("/")
    public String index() {
        String msg = "Hello, World!123  dynamic deploy";

        for (int i = 0; i < 10; i++) {
            msg += "" + i;
        }

        return msg;
    }
}