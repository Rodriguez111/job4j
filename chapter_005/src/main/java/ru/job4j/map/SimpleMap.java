package ru.job4j.map;

/**
 * Simple ru.job4j.map without hash collisions handling.
 */

import java.util.*;

public class SimpleMap<K, V> implements Iterable<SimpleMap.Entry<K, V>> {
    private static final int DEFAULT_CAPACITY = 4;
    private int currentCapacity = DEFAULT_CAPACITY;
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;
    private int size;
    private int modCount;

    private Entry<K, V>[] array = new Entry[DEFAULT_CAPACITY];

    /**
     *
     * @param key - key of the entry (should be unique).
     * @param value - value of the entry.
     * @return boolean result of the operation.
     */
  public boolean insert(K key, V value) {
      boolean result = false;
      if (size >= currentCapacity * DEFAULT_LOAD_FACTOR) {
          growUp();
      }
      int index = defineBucket(key);
      if (array[index] == null) {
          array[index] = new Entry(key, value);
          result = true;
          size++;
      }
        return result;
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
      currentCapacity = currentCapacity + currentCapacity / 2;
      final Entry<K, V>[] oldArray = array;
      array = new Entry[currentCapacity];
      for (int i = 0; i < oldArray.length; i++) {
          if (oldArray[i] != null) {
              addEntry(oldArray[i]);
          }
      }
      modCount++;
    }

    /**
     * Add entry while array resize.
     * @param entry
     */
    private void addEntry(Entry<K, V> entry) {
        int index = defineBucket(entry.key);
        if (array[index] == null) {
            array[index] = entry;
        }
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
        return new Iterator<Entry<K, V>>() {
            int indexOfArray;
            int indexOfValues;
            int expectedModCount = modCount;
            Entry<K, V> result = null;
            @Override
            public boolean hasNext() {
                return indexOfValues < size;
            }

            public Entry<K, V> next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }
                result = array[indexOfArray++];
                if (result == null) {
                    while (result == null && indexOfValues < size) {
                        result = array[indexOfArray++];
                    }
                }
                indexOfValues++;
                return result;
            }
        };
    }

    /**
     * Generate and return set of all keys of the ru.job4j.map.
     * @return set of keys.
     */
    public Set<K> keySet() {
        Set<K> set = new LinkedHashSet<>();
        for (Entry<K, V> each : array) {
            if (each != null) {
               set.add(each.key);
            }
        }
      return set;
    }

}
