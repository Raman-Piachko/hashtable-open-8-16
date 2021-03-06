package com.epam.rd.autocode.hashtableopen816;

public class HashTableEntry {
    private int key;
    private Object value;
    private boolean isRemoved;

    public HashTableEntry(int key, Object value) {
        this.key = key;
        this.value = value;
    }

    public void remove() {
        isRemoved = true;
    }

    public boolean notRemoved() {
        return !isRemoved;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
