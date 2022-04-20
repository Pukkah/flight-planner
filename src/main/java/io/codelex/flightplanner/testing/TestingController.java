package io.codelex.flightplanner.testing;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
@RequiredArgsConstructor
public class TestingController {
    private final TestingService testingService;

    @PostMapping("/clear")
    public void clear() {
        testingService.clear();
    }

}
