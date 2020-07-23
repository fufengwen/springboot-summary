package com.ffw.junit;

import com.ffw.junit.controller.demo.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JunitApplicationTests {

    @Autowired
    private DemoService demoService;
    @Test
    void contextLoads() {
        System.out.println("aa");
        demoService.test();
    }

}
