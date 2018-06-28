package de.tine.providerbaz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BazController {

    @GetMapping("/baz")
    public String foo() {
        return "baz";
    }
}
