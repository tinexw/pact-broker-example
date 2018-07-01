package de.tine.providerbar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarController {

    @GetMapping("/bar1")
    public String bar1() {
        return "bar1";
    }

    @GetMapping("/bar2")
    public String bar2() {
        return "wrong-value";
    }

    @GetMapping("/bar3")
    public String bar3() {
        return "bar3";
    }
}
