package com.bluesky.pattern.strategy.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 获取实现接口的所有类
 */
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class StorageTypeTest2 {
    @Autowired
    private List<IStorageType> list;

    @Test
    public void getFilter() {
        log.info("list.size:"+list.size());
        for (IStorageType storageType : list) {
            storageType.uploadFile("aa.txt");
        }
    }

}

