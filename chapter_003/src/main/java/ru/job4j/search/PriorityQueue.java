package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
            int count = 0;
            for (int i = 0; i != tasks.size(); i++) {
                if (task.getPriority() < tasks.get(i).getPriority()) {
                    count = i;
                    break;
                }
                count++;
            }
            tasks.add(count, task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}
