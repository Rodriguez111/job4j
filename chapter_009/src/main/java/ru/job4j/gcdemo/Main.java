package ru.job4j.gcdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Set in VM options -Xmx10m memory limit.
 * When creating 500 objects (first createObjects(int objectsCount) method usage) GC do not work,
 * because 500 objects take only 63 kb of memory.
 * When creating 80000 objects, and free memory comes to end, GC starts up and destroys some objects.
 *
 * When set -XX:+UseSerialGC, GC do not start if 80000 objects created.
 *
 * When set -XX:+UseParallelGC, GC starts when invoke createObjects(80000) and then
 * OutOfMemoryError occurs.
 *
 *When set -XX:+UseConcMarkSweepGC GC starts when invoke createObjects(80000) with no errors.
 * Compiler issues a warning that Option UseConcMarkSweepGC was deprecated in version 9.0
 *
 *
 *When set -XX:+UseG1GC, GC starts both in the first case and in the second.
 */


public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        createObjects(500);

        createObjects(80000);

        long endTime = System.currentTimeMillis();
        printDuration(startTime, endTime);
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


        System.out.println("Memory start: " +  freeMemoryStart + ", Memory end: " +  freeMemoryEnd);
        System.out.println("Total memory engaged: " +  totalMemoryEngaged);
        System.out.println("Memory Per Object: " +  memoryPerObject);
        System.out.println("------------");
    }

    private static void printDuration(long start, long end) {
        System.out.printf("Total executing time: %d ms.", (end - start));
    }
}
