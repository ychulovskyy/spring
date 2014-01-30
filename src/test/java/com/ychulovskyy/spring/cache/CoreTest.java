package com.ychulovskyy.spring.cache;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:testContext.xml", "classpath*:applicationContext.xml"})
public class CoreTest extends TestCase {

    @Autowired
    Core core;

    @Test
    public void testCache() throws Exception {
        System.out.println("First call for a value is slow because core.getTask(i) is slow");
        printTaskDescription(1);
        System.out.println("Second call for the value is quick because core.getTask(i) is now executed. Spring takes value from cache.");
        printTaskDescription(1);
        System.out.println("This will update value in cache as well");
        core.saveTask(1, "test 1");
        printTaskDescription(1);
        printTaskDescription(1);

        System.out.println("First call for a value is slow because core.getTask(i) is slow for new value");
        printTaskDescription(2);
        printTaskDescription(2);

        core.cleanupTasks();
        System.out.println("Cache is empty, that is why first call is slow");
        printTaskDescription(1);
        printTaskDescription(1);
    }

    private void printTaskDescription(int i) {
        System.out.println("id:" + i + " value:" + core.getTask(i));
    }
}