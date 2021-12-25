package service;

import com.bluesky.tech.spring.Application;
import com.bluesky.tech.spring.Service.impl.HelloService;
import com.bluesky.tech.spring.handle.vo.Rsres;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class HelloServiceTest {
    Logger logger = LoggerFactory.getLogger(HelloService.class);
    @Autowired
    HelloService helloService;

    @Test
    public void testSayHello() throws Exception {
        logger.info("testSayHello--begin");
        helloService.sayHello();
        logger.info("testSayHello--end");
    }

    @Test
    public void testGetHelloString() throws Exception {
        logger.info("testGetHelloString--begin");
        Future<Rsres<String>> helloString = helloService.getHelloString("zhangsan");
        Rsres<String> helloEntity = helloString.get();
        logger.info("testGetHelloString--end");
        logger.info("testGetHelloString result:"+helloEntity.getResult());
    }
}
