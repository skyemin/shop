package com.wei.rdmserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author skye
 * @version 1.0
 * @description:
 * @date 2021/3/25 15:50
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RdmServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RdmServerApplication.class, args);
    }
    @RestController
    class EchoController {
        @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
        public String echo(@PathVariable String string) {
            return "Hello Nacos Discovery " + string;
        }
    }
}
