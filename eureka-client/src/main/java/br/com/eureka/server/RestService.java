package br.com.eureka.server;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class RestService {

    private final FeignClient2 feignClient2;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> sayHy() {
        return new ResponseEntity<>(feignClient2.sayHyByClient2() + " using client1", HttpStatus.OK);
    }
}
