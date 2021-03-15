package com.min.distributedlock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/12/3 10:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DistributedLockTest {

    @Autowired
    private DistributedLock distributedLock;


}
