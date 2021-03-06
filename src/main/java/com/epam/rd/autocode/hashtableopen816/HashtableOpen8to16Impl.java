package com.epam.rd.autocode.hashtableopen816;

import java.util.Arrays;

public class HashtableOpen8to16Impl implements HashtableOpen8to16 {
    private static final int INITIAL_CAPACITY = 8;
    private static final int MAX_CAPACITY = 16;
    private static final int MULTIPLIER = 2;
    private static final int DIVISOR = 2;
    private static final int RATIO = 4;
    private HashTableEntry[] table;
    private int size;
    private int searchCounter;

    public HashtableOpen8to16Impl() {
        table = new HashTableEntry[INITIAL_CAPACITY];
    }

    @Override
    public void insert(int key, Object value) {
        increaseCapacity(key);
        int hashCode = Integer.valueOf(key).hashCode();
        int index = indexFor(hashCode);

        addEntry(key, value, index);
    }

    @Override
    public Object search(int key) {
        int hashCode = Integer.valueOf(key).hashCode();
        int index = indexFor(hashCode);
        searchCounter = 0;
        HashTableEntry hashTableEntry = checkBucketForEntry(key, index);

        if (hashTableEntry != null) {
            return hashTableEntry.getValue();
        } else {
            return null;
        }
    }

    @Override
    public void remove(int key) {
        int hashCode = Integer.valueOf(key).hashCode();
        int index = indexFor(hashCode);
        searchCounter = 0;
        HashTableEntry hashTableEntry = checkBucketForEntry(key, index);

        if (hashTableEntry != null) {
            hashTableEntry.remove();
            hashTableEntry.setKey(null);
            hashTableEntry.setValue(null);
            size--;
            decreaseSize();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int[] keys() {
        int[] keys = new int[table.length];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && table[i].notRemoved()) {
                keys[i] = table[i].getKey();
            }
        }
        return keys;
    }

    private int indexFor(int hashCode) {
        return Math.abs(hashCode) % table.length;
    }

    private void addEntry(int key, Object value, int index) {
        if (table[index] != null && table[index].notRemoved()) {
            if (table[index].getKey() == key) {
                table[index].setValue(value);
            } else {
                addEntry(key, value, indexLinerIncrease(index));
            }
        } else {
            table[index] = new HashTableEntry(key, value);
            size++;
        }
    }

    private int indexLinerIncrease(int index) {
        index++;
        if (index != table.length) {
            return index;
        } else {
            return 0;
        }
    }

    private void increaseCapacity(int key) {
        if (size == table.length && search(key) == null) {
            if (table.length != MAX_CAPACITY) {
                changeCapacity(table.length * MULTIPLIER);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    private void changeCapacity(int newCapacity) {
        HashTableEntry[] oldTable = table;
        table = new HashTableEntry[newCapacity];
        size = 0;
        Arrays.stream(oldTable)
                .filter(hashTableEntry -> hashTableEntry != null && hashTableEntry.notRemoved())
                .forEach(hashTableEntry -> insert(hashTableEntry.getKey(), hashTableEntry.getValue()));
    }

    private void decreaseSize() {
        if (size != 0 && size * RATIO <= table.length) {
            changeCapacity(table.length / DIVISOR);
        }
    }


    private HashTableEntry checkBucketForEntry(int key, int index) {
        if (searchCounter == table.length) {
            return null;
        }
        searchCounter++;

        if (table[index] != null) {
            if (table[index].getKey() == key && table[index].notRemoved()) {
                return table[index];
            } else {
                return checkBucketForEntry(key, indexLinerIncrease(index));
            }
        }

        return null;
    }
}