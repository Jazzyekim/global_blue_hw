package com.gb.task2;

import lombok.extern.slf4j.*;
import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.*;


@Slf4j
@Listeners(TestThresholdListener.class)
public class ThresholdTest {

    @Test(priority = 1)
    public void test1() {
        log.info("test1");
        fail();
    }

    @Test(priority = 1)
    public void test2() {
        log.info("test2");
    }

    @Test(priority = 1)
    public void test3() {
        log.info("test3");
    }

    @Test(priority = 1)
    public void test4() {
        log.info("test4");
    }

    @Test(priority = 1)
    public void test5() {
        log.info("test5");
    }

    @Test(priority = 3)
    public void test6() {
        log.info("test6");
        fail();
    }

    @Test(priority = 4)
    public void test7() {
        log.info("test7");
    }

    @Test(priority = 4)
    public void test8() {
        log.info("test8");
    }

}
