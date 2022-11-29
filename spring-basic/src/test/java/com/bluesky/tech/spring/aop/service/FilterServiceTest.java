package com.bluesky.tech.spring.aop.service;

import com.bluesky.tech.spring.handle.filter.IFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class FilterServiceTest {
    @Autowired
    private List<IFilter> filterList;

    @Test
    public void getFilter() {
        log.info("filterList.size:"+filterList.size());
        for (IFilter filter : filterList) {
            log.info("filter.filterName:{}", filter.filterName());
        }
    }

}
