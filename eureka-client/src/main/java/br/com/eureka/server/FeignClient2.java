package br.com.eureka.server;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("CLIENT2")
public interface FeignClient2 {

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    String sayHyByClient2();
}
