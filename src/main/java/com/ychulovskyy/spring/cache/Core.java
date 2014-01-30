package com.ychulovskyy.spring.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Core {

    @Cacheable(value = "task")
    public String getTask(Integer taskId) {
        System.out.println("Getting task with id:" + taskId);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return taskId.toString();
    }

    @CachePut(value = "task", key = "#taskId")
    public String saveTask(Integer taskId, String taskDescription) {
        System.out.println("Saving task with id:" + taskId + " and description:" + taskDescription);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return taskDescription;
    }

    @CacheEvict(value = "task", allEntries = true)
    public void cleanupTasks() {
        System.out.println("Cleanup tasks and cache");
    }
}