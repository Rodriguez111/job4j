package ru.job4j.map;

/**
 * Simple ru.job4j.map without hash collisions handling.
 */

import java.util.*;

public class SimpleMap<K, V> implements Iterable<SimpleMap.Entry<K, V>> {
    private static final int DEFAULT_CAPACITY = 4;
    private int previousCapacity;
    private int currentCapacity = DEFAULT_CAPACITY;
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;
    private int size;
    private int modCount;

    private Entry[] array = new Entry[DEFAULT_CAPACITY];


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
      previousCapacity = currentCapacity;
      currentCapacity = currentCapacity + currentCapacity / 2;
      array = Arrays.copyOf(array, currentCapacity);
      redistribute();
    }

    /**
     * Updates hash-based indexes and reallocate entries based to new indexes.
     * Loses elements if collisions occur.
     */
    void redistribute() {
        Entry<K, V> buffer;
      for (int i = 0; i < previousCapacity; i++) {
          if (array[i] != null) {
              Entry<K, V> current = array[i];

              int newIndex = defineBucket(current.key);
             if (newIndex == i) {
                 continue;
             }
              if (array[newIndex] == null) {
                  array[newIndex] = current;
                  array[i] = null;
              } else  {
                  buffer = array[newIndex];
                  array[newIndex] = current;
                  array[i] = null;
                  while (buffer != null) {
                      newIndex = defineBucket(buffer.key);
                      if (array[newIndex] == null) {
                          array[newIndex] = buffer;
                          buffer = null;
                      } else {
                          Entry<K, V> temp = array[newIndex];
                          if (defineBucket(temp.key) == newIndex) {
                              size--;
                              break;
                          }
                          array[newIndex] = buffer;
                          buffer = temp;
                      }
                  }
              }
          }
      }
      modCount++;
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
