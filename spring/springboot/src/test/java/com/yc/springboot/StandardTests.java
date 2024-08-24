package com.yc.springboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * 测试方法的生命周期
 */
@Slf4j
public class StandardTests {

    @BeforeAll
    static void initAll() {
        log.info("initAll");
    }

    @BeforeEach
    void init() {
        log.info("init");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        log.info("skippedTest");
        // not executed
    }

    @Test
    void succeedingTest() {
        log.info("succeedingTest");
    }

    @Test
    void failingTest() {
        log.info("failingTest");
    }

    @Test
    void abortedTest() {
        assumeTrue("abc".contains("Z"));
        fail("test should have been aborted");
    }

    @AfterEach
    void tearDown() {
        log.info("tearDown");
    }

    @AfterAll
    static void tearDownAll() {
        log.info("tearDownAll");
    }

}
