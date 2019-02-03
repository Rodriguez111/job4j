package ru.job4j.map;

/**
 * Simple ru.job4j.map with hash collisions handling.
 */

import java.util.*;

public class SimpleMapCollisions<K, V>  implements Iterable<SimpleMapCollisions.Entry<K, V>> {
    private static final int DEFAULT_CAPACITY = 4;
    private int previousCapacity;
    private int currentCapacity = DEFAULT_CAPACITY;
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;
    private int size;
    private int modCount;
    private List<Entry<K, V>>[] array = new LinkedList[DEFAULT_CAPACITY];


    /**
     *
     * @param key - key of the entry (should be unique).
     * @param value - value of the entry.
     * @return boolean result of the operation.
     */
    public boolean insert(K key, V value) {
        if (size >= currentCapacity * DEFAULT_LOAD_FACTOR) {
            growUp();
        }
        Entry<K, V> entry = new Entry(key, value);
        size++;
        return addEntry(entry);
    }

    /**
     *Add entry to the ru.job4j.map.
     * @param entry - entry to add to the ru.job4j.map.
     * @return boolean result of the operation.
     */
    private boolean addEntry(Entry<K, V> entry) {
        int index = defineBucket(entry.key);
        boolean result = false;
        if (array[index] == null) {
            array[index] = new LinkedList<>();
            array[index].add(entry);
            result = true;
        } else {
            if (!array[index].contains(entry)) {
                array[index].add(entry);
            }
        }
        return result;
    }

    /**
     * Updates hash-based indexes and reallocate entries based to new indexes.
     */
    private void redistribute() {
      List<Entry<K, V>> tempList = new LinkedList<>();
      for (int i = 0; i < previousCapacity; i++) {
          if (array[i] != null) {
              for (Entry<K, V> eachEntry : array[i]) {
                  tempList.add(eachEntry);
              }
          }
      }
      array = new LinkedList[currentCapacity];
      for (Entry<K, V> eachEntry : tempList) {
          addEntry(eachEntry);
      }
       modCount++;
    }

    /**
     * Calculates hash-based index for the entry.
     * @param key - key of the entry on which hash index is based.
     * @return hash-based index for the entry.
     */
    private int defineBucket(K key) {
        return Math.abs(key.hashCode()) % currentCapacity;
    }

    /**
     * Increase ru.job4j.map size (array size) and invokes redistribute method.
     */
    private void growUp() {
        previousCapacity = currentCapacity;
        currentCapacity = currentCapacity + currentCapacity / 2;
        array = Arrays.copyOf(array, currentCapacity);
        redistribute();
    }

    /**
     * Generate and return set of all keys of the ru.job4j.map.
     * @return set of keys.
     */
    public Set<K> keySet() {
        Set<K> set = new LinkedHashSet<>();
        for (List<Entry<K, V>> each : array) {
            if (each != null) {
                for (Entry<K, V> eachEntry : each) {
                    set.add(eachEntry.key);
                }
            }
        }
        return set;
    }



    static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<SimpleMapCollisions.Entry<K, V>>() {
            int indexOfArray;
            int indexOfBuckets;
            int countOfEntriesInTheBucket;
            int indexOfEntryInCurrentBucket;
            int indexOfAllEntries;
            int expectedModCount = modCount;
            List<Entry<K, V>> currentBucket = null;
            SimpleMapCollisions.Entry<K, V> currentEntry = null;
            @Override
            public boolean hasNext() {
                return indexOfAllEntries < size;
            }

            public SimpleMapCollisions.Entry<K, V> next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (currentBucket != null) {
                    currentEntry = currentBucket.get(indexOfEntryInCurrentBucket++);
                    checkIfNoMoreEntriesInTheBucket();
                } else {
                    currentBucket = array[indexOfArray++];
                    if (currentBucket == null) {
                        while (currentBucket == null && indexOfBuckets < currentCapacity) {
                            currentBucket = array[indexOfArray++];
                        }
                    }
                    countOfEntriesInTheBucket = currentBucket.size();
                    currentEntry = currentBucket.get(indexOfEntryInCurrentBucket++);
                    checkIfNoMoreEntriesInTheBucket();
                }
                indexOfAllEntries++;
                return currentEntry;
            }

            /**
             * Check if there is no more entries remain in current bucket.
             */
            void checkIfNoMoreEntriesInTheBucket() {
                if (indexOfEntryInCurrentBucket == countOfEntriesInTheBucket) {
                    currentBucket = null;
                    countOfEntriesInTheBucket = 0;
                    indexOfEntryInCurrentBucket = 0;
                    indexOfBuckets++;
                }
            }

        };
    }

}
