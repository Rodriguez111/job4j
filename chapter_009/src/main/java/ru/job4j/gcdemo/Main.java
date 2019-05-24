package ru.job4j.gcdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Set in VM options -Xmx10m memory limit.
 * When creating 500 objects (first createObjects(int objectsCount) method usage) GC do not work,
 * because 500 objects take only 63 kb of memory.
 * When creating 80000 objects, and free memory comes to end, GC starts up and destroys some objects.
 */


public class Main {
    public static void main(String[] args) {

        createObjects(500);

        createObjects(80000);
    }

    private static long calculateFreeMemory() {
        Runtime runtime = Runtime.getRuntime();
    return  runtime.freeMemory();
    }

    private static void createObjects(int objectsCount) {
        List<User> users = new ArrayList<>();
        long freeMemoryStart = calculateFreeMemory();

        for (int i = 0; i < objectsCount; i++) {
            User user = new User();
            users.add(user);
            user = null;
        }

        long freeMemoryEnd = calculateFreeMemory();

        long totalMemoryEngaged = freeMemoryStart - freeMemoryEnd;
        long memoryPerObject = totalMemoryEngaged / objectsCount;

        System.out.println("Total memory engaged: " +  totalMemoryEngaged);
        System.out.println("Memory Per Object: " +  memoryPerObject);
        System.out.println("------------");
    }



}
