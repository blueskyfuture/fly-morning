package service;

import com.bluesky.tech.spring.Application;
import com.bluesky.tech.spring.Service.impl.HelloService;
import com.bluesky.tech.spring.Service.impl.LoginService;
import com.bluesky.tech.spring.handle.vo.Rsres;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class LoginServiceTest {
    Logger logger = LoggerFactory.getLogger(HelloService.class);
    @Autowired
    LoginService loginService;

    @Test
    public void login() {
        String userInfo = "zhangsan";
        long start = Instant.now().toEpochMilli();
        loginService.login(userInfo);
        long end = Instant.now().toEpochMilli();
        logger.info("login spend time:{}",end - start);
    }
}
